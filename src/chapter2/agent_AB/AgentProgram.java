package chapter2.agent_AB;

public class AgentProgram {

	public Action execute(Percept p) {// location, status
		// The code is implementing the decision-making logic for the agent program.
		if (p.getLocationState() == Environment.LocationState.DIRTY) {
			return Environment.SUCK_DIRT;
		} else if (p.getAgentLocation() == Environment.LOCATION_A) {
			return Environment.MOVE_RIGHT;
		} else if (p.getAgentLocation() == Environment.LOCATION_B) {
			return Environment.MOVE_LEFT;
		}
		return NoOpAction.NO_OP;

	}

}