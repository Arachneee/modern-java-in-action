package chapter4;

import static java.util.Comparator.*;
import static java.util.stream.Collectors.*;

import java.util.Arrays;
import java.util.List;

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
	}
}
