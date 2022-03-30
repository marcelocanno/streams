package streams;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;


public class StreamTestSeisCollectorsDois {

	public static void main(String[] args) {
		// aula de agrapamento
		List<Pessoa> pessoas = Pessoa.bancoDePessoas();
		Map<Genero , List<Pessoa>> generoListMap = new HashMap<>();
		List<Pessoa> masculinos = new ArrayList<>();
		List<Pessoa> femininos = new ArrayList<>();
		for(Pessoa pessoa : pessoas){
			if(pessoa.getGenero().equals(Genero.FEMININO))
				femininos.add(pessoa);
			else
				masculinos.add(pessoa);
		}
		generoListMap.put(Genero.FEMININO, femininos);
		generoListMap.put(Genero.MASCULINO, masculinos);
		System.out.println(generoListMap);
		
		// Agrupamento por genero
		Map<Genero, List<Pessoa>> collect = pessoas.stream()
				.collect(Collectors.groupingBy(Pessoa :: getGenero));
				System.out.println(collect);
		
		// Agrupamento por maioridade
		Map<Maioridade, List<Pessoa>> collect1 = pessoas.stream()
				.collect(Collectors.groupingBy(p -> {
			if(p.getIdade() < 18) return Maioridade.MENOR;
			else return Maioridade.ADULTO;
		}));
		System.out.println(collect1);
		// Agrupamento por genero e maioridade
		Map<Genero , Map<Maioridade, List<Pessoa>>> collect2 =pessoas.stream()
				.collect(Collectors.groupingBy(Pessoa :: getGenero, Collectors.groupingBy(p -> {
				if(p.getIdade() < 18) return Maioridade.MENOR;
				else return Maioridade.ADULTO;
		})));
		System.out.println(collect2);
		
		// Agrupamento por genero e quantidade
		Map<Genero, Long> collect3 = pessoas.stream()
				.collect(Collectors.groupingBy(Pessoa :: getGenero,Collectors.counting()));
		System.out.println(collect3);
		
		// Agrupamento por genero e maior salario com optional
		Map<Genero, Optional<Pessoa>> collect4 = pessoas.stream()
				.collect(Collectors.groupingBy(Pessoa :: getGenero,
				Collectors.maxBy(Comparator.comparing(Pessoa :: getSalario))));
		System.out.println(collect4);
				
		// Agrupamento por genero e maior salario sem optional		
		Map<Genero, Pessoa> collect5 = pessoas.stream().collect(Collectors
				.groupingBy(Pessoa ::getGenero, Collectors
				.collectingAndThen(Collectors.maxBy(Comparator.comparing(Pessoa :: getSalario)),
				Optional::get)));
			System.out.println(collect5);
		// Agrupameno genero e estatistica
		Map<Genero, DoubleSummaryStatistics> collect6 = pessoas.stream()
				.collect(Collectors.groupingBy(Pessoa :: getGenero, 
				Collectors.summarizingDouble(Pessoa :: getSalario)));
			System.out.println(collect6);	
			
			/*
		// Agrupamento por genero e maioridade
		Map<Genero, Set<Maioridade>> collect7 = pessoas.stream()
				.collect(Collectors.groupingBy(Pessoa :: getGenero,
				Collectors.mapping(p -> { 
					if(((Pessoa) p).getIdade() < 18) return Maioridade.MENOR;
					else return Maioridade.ADULTO;
	}, toSet())));
		System.out.println(collect7);
		Map<Genero, Set<Maioridade>> collect8 = pessoas.stream()
				.collect(Collectors.groupingBy(Pessoa :: getGenero,
				Collectors.mapping(p -> { 
					if(((Pessoa) p).getIdade() < 18) return Maioridade.MENOR;
					else return Maioridade.ADULTO;
	}, toCollection())));
		System.out.println(collect8);
	*/
	}
}