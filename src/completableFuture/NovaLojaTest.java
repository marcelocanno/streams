package completableFuture;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class NovaLojaTest {

             // Apresenta virgula entre os numeoros
			// ainda não descobri o erro
	public static void main(String[] args) {
		
		List<NovaLoja> lojas = NovaLoja.lojas();
		lojas.stream().forEach(novaLoja -> System.out.println(novaLoja.getPreco()));
		acharPreco(lojas);
		final Executor executor  = Executors.newFixedThreadPool(Math.min(lojas.size(), 100), r -> {
			Thread t = new Thread(r);
			t.setDaemon(true);
			return t;
		});
		acharPrecoAsync(lojas,executor);
		acharPrecoStream(lojas,executor);
		acharPrecoStream(lojas,executor).map(f -> f.thenAccept(System.out::println));
		long start = System.currentTimeMillis();
		Stream<CompletableFuture<Void>> completableFutureStream = 
				acharPrecoStream(lojas,executor).map(f -> f.thenAccept(System.out::println));
		CompletableFuture[] completableFuture = acharPrecoStream(lojas,executor)
				.map(f -> f.thenAccept(s -> System.out.println(s + "(finalizado em : " +
						 (System.currentTimeMillis() - start) + ")")))
				.toArray(CompletableFuture[]::new);
		CompletableFuture.allOf(completableFuture).join();
		CompletableFuture.anyOf(completableFuture).join();
		System.out.println("todas as lojas repsonderam em :  " 
		+ (System.currentTimeMillis() - start) + "ms");
	}
	private static List<String> acharPreco(List<NovaLoja> lojas){
		System.out.println("Streams sequencial");
		Long start = System.currentTimeMillis();
	
		List<String> collect = lojas.stream()
		.map(NovaLoja::getPreco)
		.map(Orcamento::parse)
		.map(Desconto :: calcularDesconto)
		.collect(Collectors.toList());
		
		System.out.println("tempo Total  " + (System.currentTimeMillis() - start) + "ms");
		System.out.println(collect);
		return collect;
	}
	
	private static List<String> acharPrecoAsync(List<NovaLoja> lojas, Executor executor){
		System.out.println("Completable future Async");
		Long start = System.currentTimeMillis();
		List<CompletableFuture<String>> completableFuture = lojas.stream()
			// Pegando o preco original de forma async
			.map(loja -> CompletableFuture.supplyAsync(loja:: getPreco, executor))
			// transforma a string em um orcamento no momento em que ele se torna disponivel
			.map(future -> future.thenApply(Orcamento :: parse))
			// compem o primeiro future com mais uma chamada async, para pegar os descontos
			// no momento que a primeira requisicao async estiver disponivel
			.map(future -> future.thenCompose(orcamento -> CompletableFuture.supplyAsync(() -> Desconto.calcularDesconto(orcamento),executor)))
			.collect(Collectors.toList());
			// Espera todos os futures no stream finalizarem para terem os resultados extraidos
		List<String> collect = completableFuture.stream()
				.map(CompletableFuture :: join).collect(Collectors.toList());
		
		System.out.println("tempo Total  " + (System.currentTimeMillis() - start) + "ms");
		System.out.println(collect);
		return collect;
	}
	
	private static Stream<CompletableFuture<String>> acharPrecoStream(List<NovaLoja> lojas, Executor executor){
		System.out.println("Completable future Async Stream");
		Long start = System.currentTimeMillis();
		Stream<CompletableFuture<String>> completableFutureStream = lojas.stream()
			.map(loja -> CompletableFuture.supplyAsync(loja:: getPreco, executor))
			.map(future -> future.thenApply(Orcamento :: parse))
			.map(future -> future.thenCompose(orcamento -> 
				CompletableFuture.supplyAsync(
						() -> Desconto.calcularDesconto(orcamento),executor)));
		System.out.println("tempo Total  " + (System.currentTimeMillis() - start) + "ms");
		
		return completableFutureStream;
	}
}
