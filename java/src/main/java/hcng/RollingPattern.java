package hcng;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Stack;

public class RollingPattern {
	
	private final List<String> pattern;
	private final Stack<Integer> numbers;

	public RollingPattern() {
		pattern = new ArrayList<>();
		numbers = new Stack<>();
	}
	
	private RollingPattern(List<String> pattern, Stack<Integer> numbers) {
		this.pattern = pattern;
		this.numbers = numbers;
	}
	
	public boolean canExtendWithOperator() {
		return this.numbers.size() > 1;
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
		return Optional.of(new RollingPattern(newPattern, newNumbers));
	}
	
	public Optional<RollingPattern> extend(Operator operator) {
		List<String> newPattern = createExtendedPattern(operator.getSymbol());
		Optional<Stack<Integer>> newNumbers = evaluate(numbers, operator);
		
		return Optional.of(new RollingPattern(newPattern, newNumbers));
	}
	
}
