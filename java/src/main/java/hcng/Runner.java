package hcng;

import java.util.Arrays;

import hcng.countdown.CountdownEvaluator;

public class Runner {

	public static void main(String[] args) {
		Evaluator evaluator = new CountdownEvaluator();
		PatternPermutator permutator = new PatternPermutator(evaluator);
		permutator.find(
			Arrays.asList(100, 20, 15),
			200);
	}
	
}
