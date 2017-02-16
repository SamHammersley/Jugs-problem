package uk.ac.tees;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

public final class State {

	private final Set<State> linkedStates = new LinkedHashSet<State>();

	private final int x;

	private final int y;

	private final int z;

	public State(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public Set<State> getLinkedStates() {
		return linkedStates;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getZ() {
		return z;
	}

	public Set<State> getNextStates(int c1, int c2, int c3) {
		LinkedHashSet<State> states = new LinkedHashSet<State>();

		int[] xToY = Utils.transfer(x, y, c2);
		states.add(new State(xToY[0], xToY[1], z));

		int[] xToZ = Utils.transfer(x, z, c3);
		states.add(new State(xToZ[0], y, xToZ[1]));

		int[] yToX = Utils.transfer(y, x, c1);
		states.add(new State(yToX[1], yToX[0], z));

		int[] yToZ = Utils.transfer(y, z, c3);
		states.add(new State(this.x, yToZ[0], yToZ[1]));

		int[] zToX = Utils.transfer(z, x, c1);
		states.add(new State(zToX[1], y, zToX[0]));

		int[] zToY = Utils.transfer(z, y, c2);
		states.add(new State(x, zToY[1], zToY[0]));

		states.remove(this);
		linkedStates.addAll(states);
		
		return states;
	}

	@Override
	public int hashCode() {
		return Objects.hash(x, y, z);
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof State)) {
			return false;
		}
		
		State state = (State) obj;
		return state.x == this.x && state.y == this.y && state.z == this.z;
	}

	@Override
	public String toString() {
		return x + "," + y + "," + z;
	}
}