package completableFuture;

import static java.util.Arrays.asList;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class NovaLoja {
	
	private String nome;
	// NomeDaLoja:Preco:CodigodeDesconto
	public String getPreco() {
		double preco = calcularPreco();
		Desconto.Codigo codigo = Desconto.Codigo.values()[
		  ThreadLocalRandom.current().nextInt(Desconto.Codigo.values().length)];
		return String.format(" %s : %.0f :%s",nome,preco,codigo);
	}
	public NovaLoja() {
		super();
	}
	public NovaLoja(String nome) {
		super();
		this.nome = nome;
	}

	// mesma coisa escrita com SuplyAsync, economia código
	
	@Override
	public String toString() {
		return "NovaLoja [nome=" + nome + "]";
	}
		
	public static List<NovaLoja> lojas(){
		return asList(
				new NovaLoja ("Americanas "),
				new NovaLoja("Casa Bahia  "),
				new NovaLoja("bestbuy "),
				new NovaLoja("wallmart "));
	}
	private double calcularPreco() {
		delay();
		return ThreadLocalRandom.current().nextDouble() * 100;	
	}
	private static void delay() {
		try {
			int delay = ThreadLocalRandom.current().nextInt(500,2000);
			TimeUnit.MILLISECONDS.sleep(5);;
		}catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public String getNome() {
		return nome;
	}
}


