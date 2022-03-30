package completableFuture;

import static java.util.Arrays.asList;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class LojaTestDois {


	public static void main(String[] args) {
		
		// processo sincrono
		
		List<Loja> lojas = asList(
				new Loja ("Americanas"),
				new Loja("Casa Bahia"),
				new Loja("bestbuy"),
				new Loja("wallmart"));
		// acharPrecos(lojas);
		acharPreco(lojas);
		acharPreco2(lojas);
		acharPreco3(lojas);
		acharPreco4(lojas);
		acharPreco5(lojas);
		// NumeroDeThreads = Ncpu * Ucpu * (1+W/C)
		// Ncpu = numeros de cores disponiveis
		// Ucpu = quantidade de  utilização da CPU (0-1)
		// W/C = wait time e compute time
		// Nthreads = 8 * 1 * (1 + 99) = 800 Threads
		
		final Executor executor = Executors.newScheduledThreadPool(Math.min(lojas.size(), 100), r -> {
			Thread t = new Thread(r);
			t.setDaemon(true);
			return t;
		});
		
		
		System.out.println(Runtime.getRuntime().availableProcessors());
	}
	private static List<String> acharPreco(List<Loja> lojas){
		System.out.println("Streams sequencial");
		Long start = System.currentTimeMillis();
		List<String> collect = lojas.stream()
				.map(loja -> String.format("%s o preco eh : %.2f", loja.getNome(),loja.getPreco()))
				.collect(Collectors.toList());
		System.out.println("tempo Total  " + (System.currentTimeMillis() - start) + "ms");
		System.out.println(collect);
		return collect;
	}
	
	private static List<String> acharPreco2(List<Loja> lojas){
		System.out.println("Streams paralelo");
		Long start = System.currentTimeMillis();
		List<String> collect = lojas.parallelStream()   // somente alterando paarallelString
				.map(loja -> String.format("%s o preco eh : %.2f", loja.getNome(),loja.getPreco()))
				.collect(Collectors.toList());
		System.out.println("tempo Total " + (System.currentTimeMillis() - start) + "ms");
		System.out.println(collect);
		return collect;
	}
	private static List<String> acharPreco3(List<Loja> lojas){
		System.out.println("Streams future sequencial");
		Long start = System.currentTimeMillis();
		
		List<String> collect = lojas.stream()
				.map(loja -> CompletableFuture.supplyAsync(
						() -> String.format("%s o preco eh :",loja.getNome(),loja.getPreco())))
				.map(CompletableFuture :: join)
				.collect(Collectors.toList());
				
		System.out.println("tempo Total " + (System.currentTimeMillis() - start) + "ms");
		System.out.println(collect);
		return collect;
	}
	private static List<String> acharPreco4(List<Loja> lojas){
		System.out.println("Streams future");
		Long start = System.currentTimeMillis();
		
		List<CompletableFuture<String>> precoFuturo = lojas.stream()
				.map(loja -> CompletableFuture.supplyAsync(
						() -> String.format("%s o preco eh :",loja.getNome(),loja.getPreco())))
				.collect(Collectors.toList());
		List<String> collect = precoFuturo.stream().map(CompletableFuture::join).collect(Collectors.toList());
		System.out.println("tempo Total " + (System.currentTimeMillis() - start) + "ms");
		System.out.println(collect);
		return collect;
	}
	
	private static List<String> acharPreco5(List<Loja> lojas){
		System.out.println("Streams future executor");
		Long start = System.currentTimeMillis();
		
		List<CompletableFuture<String>> precoFuturo = lojas.stream()
				.map(loja -> CompletableFuture.supplyAsync(
						() -> String.format("%s o preco eh :",loja.getNome(),loja.getPreco())))
				.collect(Collectors.toList());
		System.out.println("tempo de inovação " + (System.currentTimeMillis() - start) + "MS");
		List<String> collect = precoFuturo.stream().map(CompletableFuture::join).collect(Collectors.toList());
		System.out.println("tempo Total " + (System.currentTimeMillis() - start) + "ms");
		System.out.println(collect);
		return collect;
	}
}
