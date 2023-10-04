package lab1.agent_Matrix;

public class Environment {
	public static final Action MOVE_LEFT = new DynamicAction("LEFT");
	public static final Action MOVE_RIGHT = new DynamicAction("RIGHT");
	public static final Action MOVE_UP = new DynamicAction("UP");
	public static final Action MOVE_DOWN = new DynamicAction("DOWN");
	public static final Action SUCK_DIRT = new DynamicAction("SUCK");
	public static int score = 0;

	public enum LocationState {
		CLEAN, DIRTY, WALL
	}

	private EnvironmentState envState;
	private boolean isDone = true;// all squares are CLEAN
	private Agent agent = null;

	public Environment(Environment.LocationState matrix[][]) {
		envState = new EnvironmentState(matrix);
	}

	// add an agent into the enviroment
	public void addAgent(Agent agent, int row, int col) {
		this.agent = agent;
		envState.setAgentLocation(row, col);

	}

	public EnvironmentState getCurrentState() {
		return this.envState;
	}

	// Update enviroment state when agent do an action
	public EnvironmentState executeAction(Action action) {
		int agentLocationRow = envState.getAgentLocationRow();
		int agentLocationCol = envState.getAgentLocationCol();
		Environment.LocationState envNext = null;
		if (action == SUCK_DIRT) {
			envState.setLocationState(agentLocationRow, agentLocationCol, LocationState.CLEAN);
			score += 500;
		} else if (action == MOVE_RIGHT) {
			envNext = envState.getAgentLocationState(agentLocationRow, agentLocationCol + 1);
			if (envNext == LocationState.WALL) {
				score -= 100;
			} else if (envNext == LocationState.DIRTY || envNext == LocationState.CLEAN) {
				envState.setAgentLocation(agentLocationRow, agentLocationCol + 1);
				score -= 10;
			}

		} else if (action == MOVE_LEFT) {
			envNext = envState.getAgentLocationState(agentLocationRow, agentLocationCol - 1);
			if (envNext == LocationState.WALL) {
				score -= 100;
			} else if (envNext == LocationState.DIRTY || envNext == LocationState.CLEAN) {
				envState.setAgentLocation(agentLocationRow, agentLocationCol - 1);
				score -= 10;
			}

		} else if (action == MOVE_UP) {
			envNext = envState.getAgentLocationState(agentLocationRow - 1, agentLocationCol);
			if (envNext == LocationState.WALL) {
				score -= 100;
			} else if (envNext == LocationState.DIRTY || envNext == LocationState.CLEAN) {
				envState.setAgentLocation(agentLocationRow - 1, agentLocationCol);
				score -= 10;
			}

		} else if (action == MOVE_DOWN) {
			envNext = envState.getAgentLocationState(agentLocationRow + 1, agentLocationCol);
			if (envNext == LocationState.WALL) {
				score -= 100;
			} else if (envNext == LocationState.DIRTY || envNext == LocationState.CLEAN) {
				envState.setAgentLocation(agentLocationRow + 1, agentLocationCol);
				score -= 10;
			}
		}
		return envState;
	}

	// get percept<AgentLocation, LocationState> at the current location where agent
	// is in.
	public Percept getPerceptSeenBy() {
		int agentLocationRow = envState.getAgentLocationRow();
		int agentLocationCol = envState.getAgentLocationCol();
		LocationState locationState[][] = envState.getAgentLocationState();
		return new Percept(agentLocationRow, agentLocationCol, locationState);
	}

	public void step() {
		envState.display();
		int agentLocationRow = envState.getAgentLocationRow();
		int agentLocationCol = envState.getAgentLocationCol();
		Action anAction = agent.execute(getPerceptSeenBy());
		EnvironmentState es = executeAction(anAction);

		System.out.println("Agent Loc.: " + agentLocationRow + " " + agentLocationCol + "\tAction: " + anAction);
		System.out.println("Score: " + score);
		for (int i = 0; i < es.getAgentLocationState().length; i++) {
			for (int j = 0; j < es.getAgentLocationState()[0].length; j++) {
				if (es.getAgentLocationState(i, j) == LocationState.DIRTY) {
					isDone = false;
				}
			}
		}
		if (isDone) {
			System.out.println("DONE");
		}

	}

	public void step(int n) {
		for (int i = 0; i < n; i++) {
			step();
			System.out.println("-------------------------");
		}
	}

	public void stepUntilDone() {
		int i = 0;

		while (!isDone) {
			System.out.println("step: " + i++);
			step();
		}
	}
}
