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
		System.out.println(pattern); // TODO remove
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
	
	public List<String> getPattern() {
		return pattern;
	}
	
	private List<String> addSymbolToPattern(String symbol) {
		List<String> extendedPattern = new ArrayList<>(pattern);
		extendedPattern.add(symbol);
		return extendedPattern;
	}
	
	private Stack<Integer> addNumberToStack(Integer number) {
		Stack<Integer> newNumbers = new Stack<>();
		newNumbers.addAll(numbers);
		newNumbers.add(number);
		return newNumbers;
	}
	
	public Optional<RollingPattern> extend(Integer number) {
		List<String> newPattern = addSymbolToPattern(number.toString());
		Stack<Integer> newNumbers = addNumberToStack(number);
		return Optional.of(new RollingPattern(newPattern, newNumbers, number));
	}
	
	public Optional<RollingPattern> extend(Operator operator) {
		List<String> newPattern = addSymbolToPattern(operator.getSymbol());
		Evaluation evaluation = evaluate(numbers, operator);
		if (!evaluation.isSafe()) {
			return Optional.empty();
		}
		return Optional.of(new RollingPattern(newPattern, evaluation.getNumbers(), evaluation.getResult()));
	}
	
}
