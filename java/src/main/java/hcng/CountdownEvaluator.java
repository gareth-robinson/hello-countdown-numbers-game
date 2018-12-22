package hcng;

import java.util.Stack;

public class CountdownEvaluator {

	public static Evaluation evaluate(Stack<Integer> numbers, Operator operator) {
		Stack<Integer> newStack = new Stack<Integer>();
		newStack.addAll(numbers);
		int second = newStack.pop();
		int first = newStack.pop();
		boolean safe = true;
		int result = 0;
		
		switch (operator) {
			case ADD:
				result = first + second;
				break;
			case SUBTRACT:
				result = first - second;
				safe = result > 0;
				break;
			case MULTIPLY:
				result = first * second;
				break;
			case DIVIDE:
				result = first / second;
				safe = (first % second) == 0;
				break;
		}
		
		newStack.push(result);
		return new Evaluation(newStack, result, safe);
	}
	
	public static class Evaluation {
		
		private final Stack<Integer> numbers;
		private final int result;
		private final boolean safe;
		
		public Evaluation(Stack<Integer> numbers, int result, boolean safe) {
			this.numbers = numbers;
			this.result = result;
			this.safe = safe;
		}
		
		public Stack<Integer> getNumbers() {
			return numbers;
		}
		
		public int getResult() {
			return result;
		}
		
		public boolean isSafe() {
			return safe;
		}
		
	}
	
}
