package hcng;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Optional;

public class RollingPatternTest {

	private RollingPattern testPattern;
	
	@Before
	public void createNewPattern() {
		testPattern = new RollingPattern();
	}
	
	@Test
	public void testRollingPatternIsEmpty() {
		assertEquals(testPattern.getResult(), 0);
		assertEquals(testPattern.getPattern().size(), 0);
	}
	
	@Test
	public void testExtendingWithIntegerAddsToPattern() {
		Optional<RollingPattern> result = testPattern.extend(1);
		RollingPattern newPattern = result.get(); 
		
		assertEquals(newPattern.getPattern().size(), 1);
		assertEquals(newPattern.getPattern().get(0), "1");
	}

	@Test
	public void testExtendingWithIntegerSetsResultToThatInteger() {
		Optional<RollingPattern> result = testPattern.extend(1);
		RollingPattern newPattern = result.get(); 
		
		assertEquals(newPattern.getResult(), 1);
	}
	
	@Test
	public void testASingleIntegerCannotBeExtendedWithAnOperator() {
		Optional<RollingPattern> result = testPattern.extend(1);
		RollingPattern newPattern = result.get(); 
		
		assertFalse(newPattern.canExtendWithOperator());
	}
	
	@Test
	public void testExtendingWithTwoIntegersAddsToPattern() {
		Optional<RollingPattern> result1 = testPattern.extend(1);
		RollingPattern newPattern1 = result1.get(); 
		Optional<RollingPattern> result2 = newPattern1.extend(2);
		RollingPattern newPattern2 = result2.get();
		
		assertEquals(newPattern2.getPattern().size(), 2);
		assertEquals(newPattern2.getPattern().get(0), "1");
		assertEquals(newPattern2.getPattern().get(1), "2");
	}
	
	@Test
	public void testTwoIntegersCanBeExtendedWithAnOperator() {
		Optional<RollingPattern> result1 = testPattern.extend(1);
		RollingPattern newPattern1 = result1.get(); 
		Optional<RollingPattern> result2 = newPattern1.extend(2);
		RollingPattern newPattern2 = result2.get();
		
		assertTrue(newPattern2.canExtendWithOperator());
	}
	
	@Test
	public void testExtendingTwoIntegersWithOperatorCannotBeExtended() {
		Optional<RollingPattern> result1 = testPattern.extend(1);
		RollingPattern newPattern1 = result1.get();
		Optional<RollingPattern> result2 = newPattern1.extend(2);
		RollingPattern newPattern2 = result2.get();
		Optional<RollingPattern> result3 = newPattern2.extend(Operator.ADD);
		RollingPattern newPattern3 = result3.get();
		
		assertFalse(newPattern3.canExtendWithOperator());	
	}
	
	@Test
	public void testExtendingTwoIntegersWithOperatorEvaluatesPattern() {
		Optional<RollingPattern> result1 = testPattern.extend(1);
		RollingPattern newPattern1 = result1.get();
		Optional<RollingPattern> result2 = newPattern1.extend(2);
		RollingPattern newPattern2 = result2.get();
		Optional<RollingPattern> result3 = newPattern2.extend(Operator.ADD);
		RollingPattern newPattern3 = result3.get();
		
		assertEquals(newPattern3.getResult(), 3);	
	}
	
	@Test
	public void testExtendingNumbersWithOperatorIsOnlyAllowableIfMoreThanTwoIntegersRemain() {
		Optional<RollingPattern> result1 = testPattern.extend(1);
		RollingPattern newPattern1 = result1.get(); 
		assertFalse(newPattern1.canExtendWithOperator());
		
		Optional<RollingPattern> result2 = newPattern1.extend(2);
		RollingPattern newPattern2 = result2.get();
		assertTrue(newPattern2.canExtendWithOperator());
		
		Optional<RollingPattern> result3 = newPattern2.extend(3);
		RollingPattern newPattern3 = result3.get();
		assertTrue(newPattern3.canExtendWithOperator());
		
		Optional<RollingPattern> result4 = newPattern3.extend(Operator.ADD);
		RollingPattern newPattern4 = result4.get();
		assertTrue(newPattern4.canExtendWithOperator());
		
		Optional<RollingPattern> result5 = newPattern4.extend(Operator.ADD);
		RollingPattern newPattern5 = result5.get();
		assertFalse(newPattern5.canExtendWithOperator());
		
		assertEquals(newPattern5.getResult(), 6);
	}
	
	@Test
	public void testUnsafeResultsReturnEmptyOptional() {
		Optional<RollingPattern> result1 = testPattern.extend(1);
		RollingPattern newPattern1 = result1.get(); 
		assertFalse(newPattern1.canExtendWithOperator());
		
		Optional<RollingPattern> result2 = newPattern1.extend(2);
		RollingPattern newPattern2 = result2.get();
		assertTrue(newPattern2.canExtendWithOperator());
		
		Optional<RollingPattern> result3 = newPattern2.extend(Operator.SUBTRACT);
		assertFalse(result3.isPresent());
	}
	
}
