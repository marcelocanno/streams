package streams;

import static java.util.Arrays.asList;
import java.util.Optional;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;

public class StreamTestQuatroReduce {

	public static void main(String[] args) {
		
		Optional<Integer> reduce = getStream().reduce((x,y) -> x + y);
		System.out.println(reduce.get());
		
		Integer soma = getStream().reduce(0,(x,y) -> x + y);
		System.out.println(soma);
		
		Optional<Integer> soma2 = getStream().reduce(Integer::sum);
		System.out.println(soma2.get());
		
		Integer mult = getStream().reduce(0,(x,y) -> x * y);
		System.out.println(mult);
		
		Optional<Integer> max = getStream().reduce((x,y) -> x > y ? x : y );
		System.out.println(max.get());
		
		Optional<Integer> max2 = getStream().reduce(Integer::max);
		System.out.println(max2.get());
		
		Stream<Pessoa> streamPessoa = Pessoa.bancoDePessoas().stream();
		Optional<Double> somaSalario = streamPessoa.filter(p -> p.getSalario() > 3000)
				.map(Pessoa::getSalario)
				.reduce(Double:: sum);
		System.out.println(somaSalario.get());
		
		Double somaDouble = Pessoa.bancoDePessoas().stream().filter(p -> p.getSalario() > 3000)
				.mapToDouble(Pessoa :: getSalario)
				.sum();
		System.out.println(somaDouble);
		
		DoubleStream doublestream = Pessoa.bancoDePessoas().stream().filter(p -> p.getSalario() > 2000)
				.mapToDouble(Pessoa :: getSalario);
				
		/*
		Stream<Integer> stream = asList(1,2,3,4,5,6).stream();
		Optional<Integer> reduce = stream.reduce((x,y) -> x + y);
		
		stream = asList(1,2,3,4,5,6).stream();
		Integer soma = stream.reduce(0 , (x,y) -> x + y );
		
		stream = asList(1,2,3,4,5,6).stream();
		Optional<Integer> soma2 = stream.reduce(Integer :: sum);
		
		System.out.println(reduce.get());
		System.out.println(soma);
		System.out.println(soma2);
		*/
	}
	private static Stream<Integer> getStream(){
		return asList(1,2,3,4,5,6).stream();
	}

}
