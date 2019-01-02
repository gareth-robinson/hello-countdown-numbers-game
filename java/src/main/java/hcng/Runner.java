package hcng;

import java.util.Arrays;

public class Runner {

	public static void main(String[] args) {
		Permutatron test = new Permutatron(
			Arrays.asList(100, 20, 15),
			200);
		test.find();
	}
	
}
