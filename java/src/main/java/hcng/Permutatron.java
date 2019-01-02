package hcng;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Permutatron {

	private final List<Integer> remaining;
	private final int target;

	public Permutatron(List<Integer> numbers, int target) {
		this.remaining = numbers;
		this.target = target;
	}
	
	public void find() {
		Optional<RollingPattern> found = extend(new RollingPattern(), remaining);
		if (found.isPresent()) {
			System.out.println(found.get().getPattern());	
		} else {
			System.out.println("Nope");
		}
	}
	
	private Optional<RollingPattern> extend(RollingPattern pattern, List<Integer> remaining) {
		Optional<RollingPattern> found = extendWithNumbers(pattern, remaining);
		if (!found.isPresent() && pattern.canExtendWithOperator()) {
			found = extendWithOperators(pattern, remaining);
		}
		return found;
	}

	private Optional<RollingPattern> extendWithNumbers(RollingPattern pattern, List<Integer> remaining) {
		for (int index=0; index<remaining.size(); index++) {
			Optional<RollingPattern> newPattern = pattern.extend(remaining.get(index));
			if (newPattern.isPresent()) {
				if (newPattern.get().getResult() == target) {
					return newPattern;
				} else {
					List<Integer> remainingWithout = new ArrayList<>(remaining);
					remainingWithout.remove(index);
					Optional<RollingPattern> found = extend(newPattern.get(), remainingWithout);
					if (found.isPresent()) {return found;}
				}
			}
		}
		return Optional.empty();
	}
	
	private Optional<RollingPattern> extendWithOperators(RollingPattern pattern, List<Integer> remaining) {
		for (int index=0; index<Operator.ALL_OPERATORS.size(); index++) {
			Optional<RollingPattern> newPattern = pattern.extend(Operator.ALL_OPERATORS.get(index));
			if (newPattern.isPresent()) {
				if (newPattern.get().getResult() == target) {
					return newPattern;
				} else {
					Optional<RollingPattern> found = extend(newPattern.get(), remaining);
					if (found.isPresent()) {return found;}
				}
			}
		}
		return Optional.empty();
	}
}
