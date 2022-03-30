package streams;

import static java.util.Arrays.asList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StreamTestDois {

	public static void main(String[] args) {
		
		List<List<String>> nomes = new ArrayList<>();
		nomes.add(asList("Marcelo", "Robot Alive"));
		nomes.add(asList("Inteligencia Artificial","Internet das coisas"));
		               // apresenta um erro não consegui resolver
		//List<String> collect = nomes.stream().flatMap(Collection::stream).collect(Collectors.toList());
		//System.out.println(collect);
		List<String> palavras = asList("Ola", "louco");
		String[] split = palavras.get(0).split("");
		System.out.println(Arrays.toString(split));
		List<String[]> collect1 = palavras.stream().map(p -> p.split("")).collect(Collectors.toList());
		             // apresenta um erro durante execução
				    // o objetivo final atingido	
		//Stream<String> stream = Arrays.stream((String[]) palavras.toArray());
		List<String> collect2 = palavras.stream()
			.map(p -> p.split(""))
			.flatMap(Arrays::stream)
			.collect(Collectors.toList());
		System.out.println(collect2);		
	}
}
