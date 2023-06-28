package chapter5;

import static java.util.Comparator.*;
import static java.util.stream.Collectors.*;

import java.util.*;
import java.util.stream.*;

public class chapter5 {
	public static void main(String[] args) {
		Trader raoul = new Trader("Raoul", "Cambridge");
		Trader mario = new Trader("Mario", "Milan");
		Trader alan = new Trader("Alan", "Cambridge");
		Trader brian = new Trader("Brian", "Cambridge");

		List<Transaction> transactions = Arrays.asList(
			new Transaction(brian, 2011, 300),
			new Transaction(raoul, 2012, 1000),
			new Transaction(raoul, 2011, 400),
			new Transaction(mario, 2012, 710),
			new Transaction(mario, 2012, 700),
			new Transaction(alan, 2012, 950)
		);
		//5.6연습
		//1
		List<Transaction> transactions1 = transactions.stream()
			.filter(transaction -> transaction.getYear() == 2011)
			.sorted(comparing(Transaction::getValue)).collect(toList());

		//2
		List<String> transactions2 = transactions.stream()
			.map(Transaction::getTrader)
			.map(Trader::getCity)
			.distinct()
			.collect(toList());

		//3
		List<Trader> transactions3 = transactions.stream()
			.map(Transaction::getTrader)
			.filter(trader -> trader.getCity().equals("Cambridge"))
			.distinct()
			.sorted(comparing(Trader::getName))
			.collect(toList());

		//4
		String traderStr = transactions.stream()
			.map(Transaction::getTrader)
			.map(Trader::getName)
			.distinct()
			.sorted()
			.collect(Collectors.joining());

		//5
		boolean isMirano = transactions.stream()
			.map(Transaction::getTrader)
			.map(Trader::getCity)
			.anyMatch(city -> city.equals("Milan"));

		//6
		transactions.stream()
			.filter(transaction -> transaction.getTrader().getCity().equals("Cambridge"))
			.map(Transaction::getValue)
			.forEach(System.out::println);

		//7
		int maxTran = transactions.stream()
			.map(Transaction::getValue)
			.reduce(Integer::max).orElse(0);

		//8
		Optional<Transaction> minTran = transactions.stream()
			.min(comparing(Transaction::getValue));

		//피타고라스 수
		Stream<int[]> pythagoreanTriples =
			IntStream.rangeClosed(1,100).boxed()
				.flatMap(a -> IntStream.rangeClosed(a,100)
					.filter(b->Math.sqrt(a*a+b*b)%1==0)
					.mapToObj(b -> new int[] {a,b,(int)Math.sqrt(a*a+b*b)}));

		//Pivonachi

		//Stream.iterate(new int[] {0,1}, n -> new int[] {n[1], n[0] + n[1]}).limit(20).forEach(i -> System.out.println(Arrays.toString(i)));

		Stream.iterate(new int[] {0,1},n->n[0]+n[1] < 100, n -> new int[] {n[1], n[0] + n[1]})
			.limit(20)
			.forEach(i -> System.out.println(Arrays.toString(i)));

	}


}
