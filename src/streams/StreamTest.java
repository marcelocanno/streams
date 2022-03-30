package streams;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class StreamTest {

	public static void main(String[] args) {
		// listar os três primeiros nomes das pessoas com menos de 25 anos
		// ordenar pelo nome
		
		List<Pessoa> pessoas = Pessoa.bancoDePessoas();
		Collections.sort(pessoas, (o1,o2) -> o1.getNome().compareTo(o2.getNome()));
		List<String> nomes = new ArrayList<>();
		for(Pessoa pessoa : pessoas) {
			if(pessoa.getIdade() < 25) {
				nomes.add(pessoa.getNome());
				if(nomes.size() >= 3)
					break;
			}
		}
		System.out.println(pessoas);
		System.out.println(nomes);
		
		List<String> nomes2 = pessoas
				.stream()
				.filter(p -> p.getIdade() < 25)
				.sorted(Comparator.comparing(Pessoa :: getNome))
				.limit(3) 
				.skip(1)                            // pula o primeiro da lista 
				.map(Pessoa :: getNome)
				.collect(Collectors.toList());      
		
		System.out.println(nomes2);
		System.out.println(pessoas);
		System.out.println(pessoas
				.stream()
				.distinct()
				.filter(p -> p.getIdade() < 25)
				.map(Pessoa :: getNome)
				.count());
		pessoas.stream().forEach(System.out::println);
		
		// intermediate -> retorna uma outra stream para encadear varios metodos
		// terminal -> retorna um avalor que não é uma stream -> list, void, string, etc
	}

}
