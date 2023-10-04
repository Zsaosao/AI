package lab1.agent_Matrix;

import lab1.agent_Matrix.Environment.LocationState;

public class EnvironmentState {
	private Environment.LocationState state[][];
	private int agentLocationRow;
	private int agentLocationCol;

	public EnvironmentState(Environment.LocationState matrix[][]) {
		this.state = matrix;
	}

	public void setAgentLocation(int row, int col) {
		this.agentLocationRow = row;
		this.agentLocationCol = col;
	}

	public int getAgentLocationRow() {
		return agentLocationRow;
	}

	public int getAgentLocationCol() {
		return agentLocationCol;
	}

	public Environment.LocationState[][] getAgentLocationState() {
		return this.state;
	}

	public Environment.LocationState getAgentLocationState(int row, int col) {
		return this.state[row][col];
	}

	public void setLocationState(int row, int col, LocationState locationState) {
		this.state[row][col] = locationState;
	}

	public void display() {
		int row = this.state.length;
		int col = this.state[0].length;
		for (int i = 0; i < row; i++) {
			System.out.print("|");
			for (int j = 0; j < col; j++) {
				if (i == this.agentLocationRow && j == this.agentLocationCol) {
					System.out.print(" Agent  | ");
				} else {
					System.out.print(this.state[i][j] + "   |   ");
				}
			}
			System.out.println();
		}
	}
}