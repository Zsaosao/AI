package lab1.agent_AB;

public class Environment {
	public static final Action MOVE_LEFT = new DynamicAction("LEFT");
	public static final Action MOVE_RIGHT = new DynamicAction("RIGHT");
	public static final Action SUCK_DIRT = new DynamicAction("SUCK");
	public static final String LOCATION_A = "A";
	public static final String LOCATION_B = "B";

	public enum LocationState {
		CLEAN, DIRTY
	}

	private EnvironmentState envState;
	private boolean isDone = false;// all squares are CLEAN
	private Agent agent = null;

	public Environment(LocationState locAState, LocationState locBState) {
		envState = new EnvironmentState(locAState, locBState);
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
		if (action == MOVE_LEFT) {
			if (agentLocation == LOCATION_A) {
				envState.setAgentLocation(LOCATION_B);
			} else if (agentLocation == LOCATION_B) {
				envState.setAgentLocation(LOCATION_A);
			}
		} else if (action == MOVE_RIGHT) {
			if (agentLocation == LOCATION_A) {
				envState.setAgentLocation(LOCATION_B);
			} else if (agentLocation == LOCATION_B) {
				envState.setAgentLocation(LOCATION_A);
			}
		} else if (action == SUCK_DIRT) {
			if (agentLocation == LOCATION_A) {
				envState.setLocationState(LOCATION_A, LocationState.CLEAN);
			} else if (agentLocation == LOCATION_B) {
				envState.setLocationState(LOCATION_B, LocationState.CLEAN);
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

		if ((es.getLocationState(LOCATION_A) == LocationState.CLEAN)
				&& (es.getLocationState(LOCATION_B) == LocationState.CLEAN))
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
