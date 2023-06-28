package chapter6;

import static java.util.stream.Collectors.*;

import java.util.*;
import java.util.stream.IntStream;

import chapter4.Dish;

public class chapter6 {
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

		Map<Dish.Type, List<Dish>> caloricDishesByType =
			menu.stream()
				.collect(groupingBy(Dish::getType,
						 filtering(dish -> dish.getCalories() > 500, toList())));

		Map<Dish.Type, List<String>> dishNamesByType =
			menu.stream()
				.collect(groupingBy(Dish::getType,mapping(Dish::getName,toList())));

		Map<Boolean, List<Dish>> partitonedMenu =
			menu.stream().collect(partitioningBy(Dish::isVegtarian));

		Map<Boolean, Map<Dish.Type, List<Dish>>> vegetarianDishesByType =
			menu.stream().collect(partitioningBy(Dish::isVegtarian,groupingBy(Dish::getType)));

		Map<Boolean, List<Integer>> primeList = IntStream.rangeClosed(2,500).boxed().collect(new PrimeNumbersCollector());

		primeList.get(true).stream().forEach(System.out::println);
	}
}
