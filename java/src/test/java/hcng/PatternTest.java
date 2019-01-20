package hcng;

import org.junit.Before;
import org.junit.Test;

import java.util.Optional;
import java.util.Stack;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static hcng.test.Setup.createStack;

public class PatternTest {

	private Pattern testPattern;
	private Evaluator evaluator = mock(Evaluator.class);
	
	@Before
	public void createNewPattern() {
		reset(evaluator); 
		testPattern = new Pattern(evaluator);
	}
	
	@Test
	public void testRollingPatternIsEmpty() {
		assertEquals(testPattern.getResult(), 0);
		assertEquals(testPattern.getPattern().size(), 0);
	}
	
	@Test
	public void testExtendingWithIntegerAddsToPattern() {
		Optional<Pattern> result = testPattern.extend(1);
		Pattern newPattern = result.get(); 
		
		assertEquals(newPattern.getPattern().size(), 1);
		assertEquals(newPattern.getPattern().get(0), "1");
	}

	@Test
	public void testExtendingWithIntegerSetsResultToThatInteger() {
		Optional<Pattern> result = testPattern.extend(1);
		Pattern newPattern = result.get(); 
		
		assertEquals(newPattern.getResult(), 1);
	}
	
	@Test
	public void testASingleIntegerCannotBeExtendedWithAnOperator() {
		Optional<Pattern> result = testPattern.extend(1);
		Pattern newPattern = result.get(); 
		
		assertFalse(newPattern.canExtendWithOperator());
	}
	
	@Test
	public void testExtendingWithTwoIntegersAddsToPattern() {
		Optional<Pattern> result1 = testPattern.extend(1);
		Pattern newPattern1 = result1.get(); 
		Optional<Pattern> result2 = newPattern1.extend(2);
		Pattern newPattern2 = result2.get();
		
		assertEquals(newPattern2.getPattern().size(), 2);
		assertEquals(newPattern2.getPattern().get(0), "1");
		assertEquals(newPattern2.getPattern().get(1), "2");
	}
	
	@Test
	public void testTwoIntegersCanBeExtendedWithAnOperator() {
		Optional<Pattern> result1 = testPattern.extend(1);
		Pattern newPattern1 = result1.get(); 
		Optional<Pattern> result2 = newPattern1.extend(2);
		Pattern newPattern2 = result2.get();
		
		assertTrue(newPattern2.canExtendWithOperator());
	}
	
	@Test
	public void testExtendingTwoIntegersWithOperatorEvaluatesPattern() {
		Stack<Integer> expectedStack = createStack(1, 2);
		Stack<Integer> resultStack = createStack(3);
		when(evaluator.isOperationAllowed(eq(expectedStack), eq(Operator.ADD)))
			.thenReturn(true);
		when(evaluator.evaluate(eq(expectedStack), eq(Operator.ADD)))
			.thenReturn(resultStack);
		
		Optional<Pattern> result1 = testPattern.extend(1);
		Pattern newPattern1 = result1.get();
		Optional<Pattern> result2 = newPattern1.extend(2);
		Pattern newPattern2 = result2.get();
		Optional<Pattern> result3 = newPattern2.extend(Operator.ADD);
		Pattern newPattern3 = result3.get();
		
		assertEquals(newPattern3.getResult(), 3);	
	}
	
	@Test
	public void testExtensionAfterEvaluationReducesTheStack() {
		Stack<Integer> expectedStack = createStack(1, 2);
		Stack<Integer> resultStack = createStack(3);
		when(evaluator.isOperationAllowed(eq(expectedStack), eq(Operator.ADD)))
			.thenReturn(true);
		when(evaluator.evaluate(eq(expectedStack), eq(Operator.ADD)))
			.thenReturn(resultStack);
		
		Optional<Pattern> result1 = testPattern.extend(1);
		Pattern newPattern1 = result1.get();
		Optional<Pattern> result2 = newPattern1.extend(2);
		Pattern newPattern2 = result2.get();
		Optional<Pattern> result3 = newPattern2.extend(Operator.ADD);
		Pattern newPattern3 = result3.get();
			
		assertFalse(newPattern3.canExtendWithOperator());	
	}
	
	@Test
	public void testUnsafeResultsReturnEmptyOptional() {
		Stack<Integer> expectedStack = createStack(1, 2);
		when(evaluator.isOperationAllowed(eq(expectedStack), eq(Operator.ADD)))
			.thenReturn(false);
		
		Optional<Pattern> result1 = testPattern.extend(1);
		Pattern newPattern1 = result1.get(); 
		Optional<Pattern> result2 = newPattern1.extend(2);
		Pattern newPattern2 = result2.get();
		Optional<Pattern> result3 = newPattern2.extend(Operator.SUBTRACT);
		
		assertFalse(result3.isPresent());
	}
	
}
