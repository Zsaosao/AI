package chapter2.agent_ABCD;

public class Environment {
	public static final Action MOVE_LEFT = new DynamicAction("LEFT");
	public static final Action MOVE_RIGHT = new DynamicAction("RIGHT");
	public static final Action MOVE_UP = new DynamicAction("UP");
	public static final Action MOVE_DOWN = new DynamicAction("DOWN");
	public static final Action SUCK_DIRT = new DynamicAction("SUCK");
	public static final String LOCATION_A = "A";
	public static final String LOCATION_B = "B";
	public static final String LOCATION_C = "C";
	public static final String LOCATION_D = "D";
	public static int score = 0;

	public enum LocationState {
		CLEAN, DIRTY
	}

	private EnvironmentState envState;
	private boolean isDone = false;// all squares are CLEAN
	private Agent agent = null;

	public Environment(LocationState locAState, LocationState locBState, LocationState locCState,
			LocationState locDState) {
		envState = new EnvironmentState(locAState, locBState, locCState, locDState);
	}

	// add an agent into the enviroment
	public void addAgent(Agent agent, String location) {
		this.agent = agent;
		envState.setAgentLocation(location);

	}

	public EnvironmentState getCurrentState() {
		return this.envState;
	}

	// Update enviroment state when agent do an action
	public EnvironmentState executeAction(Action action) {
		String agentLocation = envState.getAgentLocation();
		if (action == SUCK_DIRT) {
			envState.setLocationState(agentLocation, LocationState.CLEAN);
			score += 500;
		} else if (action == MOVE_RIGHT) {
			if (agentLocation == LOCATION_A) {
				envState.setAgentLocation(LOCATION_B);
				score -= 10;
			} else if (agentLocation == LOCATION_C) {
				envState.setAgentLocation(LOCATION_D);
				score -= 10;
			} else {
				score -= 100;
			}

		} else if (action == MOVE_LEFT) {
			if (agentLocation == LOCATION_B) {
				envState.setAgentLocation(LOCATION_A);
				score -= 10;
			} else if (agentLocation == LOCATION_D) {
				envState.setAgentLocation(LOCATION_C);
				score -= 10;
			} else {
				score -= 100;

			}

		} else if (action == MOVE_UP) {
			if (agentLocation == LOCATION_C) {
				envState.setAgentLocation(LOCATION_A);
				score -= 10;
			} else if (agentLocation == LOCATION_D) {
				envState.setAgentLocation(LOCATION_B);
				score -= 10;
			} else {
				score -= 100;
			}

		} else if (action == MOVE_DOWN) {
			if (agentLocation == LOCATION_A) {
				envState.setAgentLocation(LOCATION_C);
				score -= 10;
			} else if (agentLocation == LOCATION_B) {
				envState.setAgentLocation(LOCATION_D);
				score -= 10;
			} else {
				score -= 100;
			}
		}
		return envState;
	}

	// get percept<AgentLocation, LocationState> at the current location where agent
	// is in.
	public Percept getPerceptSeenBy() {
		String agentLocation = envState.getAgentLocation();
		LocationState locationState = envState.getLocationState(agentLocation);
		return new Percept(agentLocation, locationState);
	}

	public void step() {
		envState.display();
		String agentLocation = this.envState.getAgentLocation();
		Action anAction = agent.execute(getPerceptSeenBy());
		EnvironmentState es = executeAction(anAction);

		System.out.println("Agent Loc.: " + agentLocation + "\tAction: " + anAction);
		System.out.println("Score: " + score);
		if ((es.getLocationState(LOCATION_A) == LocationState.CLEAN)
				&& (es.getLocationState(LOCATION_B) == LocationState.CLEAN)
				&& (es.getLocationState(LOCATION_C) == LocationState.CLEAN)
				&& (es.getLocationState(LOCATION_D) == LocationState.CLEAN))
			isDone = true;// if both squares are clean, then agent do not need to do any action
		es.display();
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
