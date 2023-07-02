package chapter11;

import java.util.Optional;

public class chapter11 {
	public static void main(String[] args) {
		Insurance insurance = new Insurance();
		insurance.setName("Hogeon");
		Optional<Insurance> optInsurance = Optional.ofNullable(insurance);
		Optional<String> name = optInsurance.map(Insurance::getName);


	}
}
