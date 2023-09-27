package chapter2.agent_Matrix;

public class Percept {
	private int agentLocationRow;
	private int agentLocationCol;
	private Environment.LocationState state[][];

	public Percept(int row, int col, Environment.LocationState state[][]) {
		this.agentLocationRow = row;
		this.agentLocationCol = col;
		this.state = state;
	}

	public Environment.LocationState getLocationState() {
		return this.state[agentLocationRow][agentLocationCol];
	}

	public int getAgentLocationRow() {
		return this.agentLocationRow;

	}

	public int getAgentLocationCol() {
		return this.agentLocationCol;

	}
}