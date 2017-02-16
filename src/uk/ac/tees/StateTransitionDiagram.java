package uk.ac.tees;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.function.Predicate;

public final class StateTransitionDiagram {
	
	private final State initialState;
	
	private final Integer[] capacities;

	public StateTransitionDiagram(Integer[] capacities, State initialState) {
		this.capacities = capacities;
		this.initialState = initialState;
	}

	private Set<State> create() {
		LinkedHashSet<State> result = new LinkedHashSet<State>();
		result.add(initialState);
		
		LinkedList<State> nextParent = new LinkedList<State>();
		nextParent.add(initialState);
		
		while (!nextParent.isEmpty()) {
			State current = nextParent.poll();
			Set<State> nextStates = current.getNextStates(capacities[0], capacities[1], capacities[2]);
			
			Predicate<State> predicate = result::contains;
			nextStates.stream().filter(predicate.negate()).forEach(s -> {
				result.add(s);
				nextParent.add(s);
			});
		}
		return result;
	}

	public static void main(String[] args) {
		if (args.length != 6) {
			throw new RuntimeException("Incorrect arguments count");
		}

		Integer[] parsed = Arrays.stream(args).map(Integer::parseInt).toArray(Integer[]::new);
		State initialState = new State(parsed[0], parsed[1], parsed[2]);

		StateTransitionDiagram diagram = new StateTransitionDiagram(Arrays.copyOfRange(parsed, 3, 6), initialState);
		diagram.create().forEach(System.out::println);
	}
}