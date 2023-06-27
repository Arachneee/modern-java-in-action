package chapter4;

import static java.util.Comparator.*;
import static java.util.stream.Collectors.*;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public class chapter4 {
	public static void main(String[] args) {
		List<Dish> menu = Arrays.asList(
			new Dish("pork", false, 800, Dish.Type.MEAT),
			new Dish("beef", false, 700, Dish.Type.MEAT),
			new Dish("chicken", false, 400, Dish.Type.MEAT),
			new Dish("french fries", true, 530, Dish.Type.OTHER),
			new Dish("rice", true, 350, Dish.Type.OTHER),
			new Dish("season fruit", true, 120, Dish.Type.OTHER),
			new Dish("pizza", false, 550, Dish.Type.OTHER),
			new Dish("prawns", false, 300, Dish.Type.FISH),
			new Dish("salmon", false, 450, Dish.Type.FISH)
		);

		List<String> threeHighCaloricDishNames =
			menu.stream()
				.filter(dish -> dish.getCalories() > 300)
				.map(Dish::getName)
				.limit(3)
				.collect(toList());

		//System.out.println(threeHighCaloricDishNames);

		List<String> highCaloricDishes = menu.stream()
			.filter(a -> {
				System.out.println("filtering:" + a.getName());
				return a.getCalories() > 300;
			})
			.sorted(comparing(Dish::getCalories))  // sorted가 추가 되면 sort실행을 위해 filter 먼저 전부 실행됨. // 루프 퓨전이 깨진다.
			.map(a -> {
				System.out.println("mapping:" + a.getName());
				return a.getName();
			})
			.limit(3)
			.collect(toList());

		// 정렬된 list
		List<Dish> sortedMenu = menu.stream().sorted(comparing(Dish::getCalories)).collect(toList());

		// takeWhile 로 중간에 끊기
		List<Dish> slicedMenu1
			= sortedMenu.stream()
						.takeWhile(dish -> dish.getCalories() < 320)
						.collect(toList());

		// dropWhile 로 앞에꺼 버리기.
		List<Dish> slicedMenu2
			= sortedMenu.stream()
			.dropWhile(dish -> dish.getCalories() < 320)
			.collect(toList());

		List<Integer> number1 = Arrays.asList(1,2,3);
		List<Integer> number2 = Arrays.asList(3,4);

		List<int[]> pairs = number1.stream()
									.flatMap(i -> number2.stream().map(j-> new int[]{i,j}))
									.filter(a->(a[0]+a[1])%3 == 0)
									.collect(toList());
		for (int[] pair: pairs) {
			System.out.println(Arrays.toString(pair));
		}

		System.out.println(menu.stream().mapToInt(i->1).reduce(0,(a,b) -> a + b));

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
	}
}
