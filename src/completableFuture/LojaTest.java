package completableFuture;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class LojaTest {

	public static void main(String[] args) {
		// sincrona
		Loja americanas = new Loja();
		Loja casaBahia = new Loja();
		Loja bestBuy =  new Loja();
		Loja wallMart = new Loja();
		//long start = System.currentTimeMillis();
		//System.out.println(americanas.getPreco());
		//System.out.println(casaBahia.getPreco());
		//System.out.println(bestBuy.getPreco());
		//System.out.println(wallMart.getPreco());
		//System.out.println(System.currentTimeMillis() - start + "ms");

		long start = System.currentTimeMillis();
		Future<Double> precoAsync = americanas.getPrecoAsync();
		Future<Double> precoAsync1 = casaBahia.getPrecoAsync();
		Future<Double> precoAsync2 = bestBuy.getPrecoAsync();
		Future<Double> precoAsync3 = wallMart.getPrecoAsync();
		long end = System.currentTimeMillis();
		System.out.println("tempo invocação  " + (end - start) + "ms");
		System.out.println();
		// programação assincrona
		enrolando();
		try {
			System.out.println("Americanas = " + precoAsync.get());
		    System.out.println("Casa Bahia  = " + precoAsync1.get());
			System.out.println("Best Buy = " + precoAsync2.get());
			System.out.println("Wall Mart = " + precoAsync3.get());
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("tempo que levou para pegar resultado  " 
				+ (System.currentTimeMillis() - start + "ms"));
	}
		private static void enrolando() {
			long soma = 0;
			for(int i =0; i < 1_000_000; i++) {
				soma += i;
			}
			System.out.println(soma);
		
		
		
		
	}

}
