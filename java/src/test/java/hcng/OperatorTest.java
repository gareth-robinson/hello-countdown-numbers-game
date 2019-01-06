package hcng;

import org.junit.Test;
import static org.junit.Assert.*;

public class OperatorTest {
	
	@Test
	public void testSymbolForPlus() {
		assertEquals(Operator.ADD.getSymbol(), "+");
	}
	
	@Test
	public void testSymbolForSubtract() {
		assertEquals(Operator.SUBTRACT.getSymbol(), "-");
	}
	
	@Test
	public void testSymbolForMultiply() {
		assertEquals(Operator.MULTIPLY.getSymbol(), "*");
	}
	
	@Test
	public void testSymbolForDivide() {
		assertEquals(Operator.DIVIDE.getSymbol(), "/");
	}
	
	@Test
	public void allOperatorsContainsFourEntries() {
		assertEquals(Operator.ALL_OPERATORS.size(), 4);
		Operator.ALL_OPERATORS.contains(Operator.ADD);
		Operator.ALL_OPERATORS.contains(Operator.SUBTRACT);
		Operator.ALL_OPERATORS.contains(Operator.MULTIPLY);
		Operator.ALL_OPERATORS.contains(Operator.DIVIDE);
	}

}
