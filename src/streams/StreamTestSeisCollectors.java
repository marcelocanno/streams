package streams;

import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class StreamTestSeisCollectors {

	public static void main(String[] args) {
		// Redução e sumarização de streams em um valor unico
		
		List<Pessoa> pessoas = Pessoa.bancoDePessoas();
		System.out.println(pessoas.stream().count());
		System.out.println(pessoas.stream().collect(Collectors.counting()));
		
		Optional<Pessoa> max = pessoas.stream().max(Comparator.comparing(Pessoa :: getSalario));
		System.out.println(max.get().getSalario());
		
		Optional<Pessoa> collect = pessoas.stream().collect(Collectors.maxBy(Comparator.comparing(Pessoa::getSalario)));
		System.out.println(collect.get().getSalario());
		
		Optional<Pessoa> collect1 = pessoas.stream().collect(Collectors.minBy(Comparator.comparing(Pessoa::getSalario)));
		System.out.println(collect1.get().getSalario());
		
		System.out.println(pessoas.stream().mapToDouble(Pessoa :: getSalario).sum());
		System.out.println(pessoas.stream().collect(Collectors.summingDouble(Pessoa :: getSalario)));
		
		System.out.println(pessoas.stream().mapToDouble(Pessoa :: getSalario).average());
		System.out.println(pessoas.stream().collect(Collectors.averagingDouble(Pessoa :: getSalario)));
	
		DoubleSummaryStatistics collect2 = pessoas.stream().collect(Collectors.summarizingDouble(Pessoa :: getSalario));
		System.out.println(collect2);
		
		
		System.out.println(pessoas.stream().map(Pessoa :: getNome).collect(Collectors.joining(", ")));
	}


	

}

