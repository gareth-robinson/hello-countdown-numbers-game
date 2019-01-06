package hcng;

import hcng.CountdownEvaluator.Evaluation;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

import org.junit.Test;
import static org.junit.Assert.*;

public class CountdownEvaluatorTest {

	private Stack<Integer> createStack(Integer... values) {
		Stack<Integer> stack = new Stack<>();
		List<Integer> list = Arrays.asList(values); 
		stack.addAll(list);
		return stack;
	}
	
	@Test
	public void testAddOperator() {
		Stack<Integer> stack = createStack(10, 2);
		Evaluation result = CountdownEvaluator.evaluate(stack, Operator.ADD);
		
		assertEquals(result.getResult(), 12);
		assertTrue(result.isSafe());
		assertEquals(result.getNumbers().size(), 1);
		assertEquals(result.getNumbers().pop(), new Integer(12));
	}

	@Test
	public void testSubtractOperator() {
		Stack<Integer> stack = createStack(10, 2);
		Evaluation result = CountdownEvaluator.evaluate(stack, Operator.SUBTRACT);
		
		assertEquals(result.getResult(), 8);
		assertTrue(result.isSafe());
		assertEquals(result.getNumbers().size(), 1);
		assertEquals(result.getNumbers().pop(), new Integer(8));
	}
	
	@Test
	public void testMultiplyOperator() {
		Stack<Integer> stack = createStack(10, 2);
		Evaluation result = CountdownEvaluator.evaluate(stack, Operator.MULTIPLY);
		
		assertEquals(result.getResult(), 20);
		assertTrue(result.isSafe());
		assertEquals(result.getNumbers().size(), 1);
		assertEquals(result.getNumbers().pop(), new Integer(20));
	}
	
	@Test
	public void testDivideOperator() {
		Stack<Integer> stack = createStack(10, 2);
		Evaluation result = CountdownEvaluator.evaluate(stack, Operator.DIVIDE);
		
		assertEquals(result.getResult(), 5);
		assertTrue(result.isSafe());
		assertEquals(result.getNumbers().size(), 1);
		assertEquals(result.getNumbers().pop(), new Integer(5));
	}
	
	@Test
	public void testUnsafeSubtractOperator() {
		Stack<Integer> stack = createStack(3, 10);
		Evaluation result = CountdownEvaluator.evaluate(stack, Operator.SUBTRACT);
		
		assertFalse(result.isSafe());
	}

	@Test
	public void testUnsafeDivideOperator() {
		Stack<Integer> stack = createStack(10, 3);
		Evaluation result = CountdownEvaluator.evaluate(stack, Operator.DIVIDE);
		
		assertFalse(result.isSafe());
	}

	@Test
	public void testStackIsReducedByOperation() {
		Stack<Integer> stack = createStack(1, 2, 3, 4, 5);
		
		Evaluation result = CountdownEvaluator.evaluate(stack, Operator.ADD);
		assertEquals(result.getNumbers().size(), 4);
		assertEquals(result.getNumbers(), createStack(1, 2, 3, 9));
	}

}
