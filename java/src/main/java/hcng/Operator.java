package hcng;

import java.util.Arrays;
import java.util.List;

public enum Operator {
	
	ADD("+"),
	SUBTRACT("-"),
	MULTIPLY("*"),
	DIVIDE("/");
	
	static List<Operator> ALL_OPERATORS = Arrays.asList(ADD, SUBTRACT, MULTIPLY, DIVIDE);
	
	private final String symbol;
	
    private Operator(String symbol) {
        this.symbol = symbol;
    }
    
    String getSymbol() {
    	return symbol;
    }
	
}
