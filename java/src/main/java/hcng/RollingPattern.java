package hcng;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Stack;

import hcng.CountdownEvaluator.Evaluation;
import static hcng.CountdownEvaluator.evaluate;

public class RollingPattern {
	
	private final List<String> pattern;
	private final Stack<Integer> numbers;
	private final int result;

	public RollingPattern() {
		pattern = new ArrayList<>();
		numbers = new Stack<>();
		result = 0;
	}
	
	private RollingPattern(List<String> pattern, Stack<Integer> numbers, int result) {
		this.pattern = pattern;
		this.numbers = numbers;
		this.result = result;
	}
	
	public boolean canExtendWithOperator() {
		return numbers.size() > 1;
	}
	
	public int getResult() {
		return result;
	}
	
	private List<String> createExtendedPattern(String symbol) {
		List<String> extendedPattern = new ArrayList<>(pattern);
		extendedPattern.add(symbol);
		return extendedPattern;
	}
	
	public Optional<RollingPattern> extend(Integer number) {
		List<String> newPattern = createExtendedPattern(number.toString());
		Stack<Integer> newNumbers = new Stack<>();
		newNumbers.addAll(numbers);
		newNumbers.add(number);
		return Optional.of(new RollingPattern(newPattern, newNumbers, number));
	}
	
	public Optional<RollingPattern> extend(Operator operator) {
		List<String> newPattern = createExtendedPattern(operator.getSymbol());
		Evaluation evaluation = evaluate(numbers, operator);
		if (!evaluation.isSafe()) {
			return Optional.empty();
		}
		return Optional.of(new RollingPattern(newPattern, evaluation.getNumbers(), evaluation.getResult()));
	}
	
}
