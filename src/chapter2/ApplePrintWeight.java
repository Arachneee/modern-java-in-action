package chapter2;

public class ApplePrintWeight implements ApplePrint {
	@Override
	public String print(Apple apple) {
		return String.valueOf(apple.weight);
	}
}
