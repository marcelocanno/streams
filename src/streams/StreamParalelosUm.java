package streams;

import java.util.stream.LongStream;
import java.util.stream.Stream;

public class StreamParalelosUm {
	
	public static void main(String[] args) {
		
		long num = 10000000;
		somaFor(num);
		somaStreamIterate(num);
		somaParallelStreamIterate(num);
		System.out.println(Runtime.getRuntime().availableProcessors());
		somaRangeClosedStream(num);
		somaRangeClosedParallelStream(num);
		}
		     // [1,2,3,4,5 |,6,7,8,9,10]  [1-5] [6-10] ...
	         // quebra thread em partes para somar
		     // ganho de tempo de processamento
	
		private static void somaFor(long num) {
			System.out.println("For");
			long result = 0;
			long init = System.currentTimeMillis();
			for(long i = 1l; i <= num; i++) {
				result += i;
			}
			long end = System.currentTimeMillis();
			System.out.println(result + " " + (end - init) + "ms");
		}
		private static void somaStreamIterate(long num) {
			System.out.println("Stream sequencial");
			long result = 0;
			long init = System.currentTimeMillis();
			result = Stream.iterate(1L, i -> i + 1).limit(num).reduce(0L, Long :: sum);
			long end = System.currentTimeMillis();
			System.out.println(result + " " + (end - init) + "ms");
		}
		private static void somaParallelStreamIterate(long num) {
			System.out.println("Stream Paralelor");
			long result = 0;
			long init = System.currentTimeMillis();
			result = Stream.iterate(1L, i -> i + 1).limit(num).parallel().reduce(0L, Long :: sum);
			long end = System.currentTimeMillis();
			System.out.println(result + " " + (end - init) + "ms");
		}
		private static void somaRangeClosedStream(long num) {
			System.out.println("Range closed Stream ");
			long result = 0;
			long init = System.currentTimeMillis();
			result = LongStream.rangeClosed(1L, num).reduce(0L, Long :: sum);
			long end = System.currentTimeMillis();
			System.out.println(result + " " + (end - init) + "ms");
		}
		private static void somaRangeClosedParallelStream(long num) {
			System.out.println("Range closed paralelo Stream ");
			long result = 0;
			long init = System.currentTimeMillis();
			result = LongStream.rangeClosed(1L, num).parallel().reduce(0L, Long :: sum);
			long end = System.currentTimeMillis();
			System.out.println(result + " " + (end - init) + "ms");
		}
}
