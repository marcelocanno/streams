package completableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class Loja {
	
	private String nome;

	public double getPreco() {
		return calcularPreco();
		
	}
	
	public Loja() {
		super();
	}

	public Loja(String nome) {
		super();
		this.nome = nome;
	}

	// mesma coisa escrita com SuplyAsync, economia código
	
	@Override
	public String toString() {
		return "Loja [nome=" + nome + "]";
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Future<Double> getPrecoAsyncTunado(){
		return CompletableFuture.supplyAsync(this::calcularPreco);
	}
	
	public Future<Double> getPrecoAsync(){
		CompletableFuture<Double> precoFuturo = new CompletableFuture<>();
		new Thread(() -> {
			try{
				precoFuturo.complete(calcularPreco());
			}catch(Exception e) {
				precoFuturo.completeExceptionally(e);
			}
		}).start();
		return precoFuturo;	
	}
	
	private double calcularPreco() {
		delay();
		return ThreadLocalRandom.current().nextDouble() * 100;
		
	}
	
	private static void delay() {
		try {
			TimeUnit.SECONDS.sleep(5);;
			
		}catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
