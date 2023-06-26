package chapter2;

public class ApplePrintIsHeavy implements ApplePrint {

	@Override
	public String print(Apple apple) {
		if (apple.weight > 5) {
			return "무겁다.";
		}
		return "가볍다.";
	}
}
