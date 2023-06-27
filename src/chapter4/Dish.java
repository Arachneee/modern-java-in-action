package chapter4;

public class Dish {
	private final String name;
	private final boolean vegtarian;
	private final int calories;
	private final Type type;

	public Dish(String name, boolean vegtarian, int calories, Type type) {
		this.name = name;
		this.vegtarian = vegtarian;
		this.calories = calories;
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public boolean isVegtarian() {
		return vegtarian;
	}

	public int getCalories() {
		return calories;
	}

	public Type getType() {
		return type;
	}

	@Override
	public String toString() {
		return name;
	}

	public enum Type {MEAT, FISH, OTHER}
}
