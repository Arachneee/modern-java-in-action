package chapter2;

import java.util.ArrayList;
import java.util.List;

public class quiz2_1 {
	public static void main(String[] args) {
		List<Apple> inventory = new ArrayList<>();

		for (int i = 0; i < 10; i++) {
			inventory.add(new Apple(i+1));
		}

		prettyPrintApple(inventory,new ApplePrintWeight());
		prettyPrintApple(inventory,new ApplePrintIsHeavy());

	}
	public static void prettyPrintApple(List<Apple> inventory, ApplePrint p) {
		for (Apple apple: inventory) {
			String output = p.print(apple);
			System.out.println(output);
		}
	}


}
