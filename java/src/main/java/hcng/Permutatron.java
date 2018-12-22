package hcng;

import java.util.List;
import java.util.Optional;

public class Permutatron {

	private final List<Integer> remaining;
	private final int target;

	public Permutatron(List<Integer> numbers, int target) {
		this.remaining = numbers;
		this.target = target;
	}
	
	public List<String> find() {
		RollingPattern rollingPattern = new RollingPattern();
		
	}
	
	private void extend(RollingPattern pattern, List<Integer> remaining) {
		for (int index=0; index<remaining.size(); index++) {
			Optional<RollingPattern> newPattern = pattern.extend(remaining.get(index));
			if (newPattern.isPresent()) {
				if (newPattern.get().getResult() == target) {
					
				} else {
					extend(newPattern.get(), remaining);
				}
			}
		}
		for (int index=0; index<Operator.ALL_OPERATORS.size(); index++) {
			Optional<RollingPattern> newPattern = pattern.extend(remaining.get(index));
			if (newPattern.isPresent()) {
				if (newPattern.get().getResult() == target) {
					
				} else {
					extend(newPattern.get(), remaining);
				}
			}
		}
	}
}
