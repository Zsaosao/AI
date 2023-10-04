package lab1.agent_Matrix;

import java.util.Random;

public class AgentProgram {

	public Action execute(Percept p) {// location, status
		// The code is implementing the decision-making logic for the agent program.
		Random random = new Random();
		if (p.getLocationState() == Environment.LocationState.DIRTY) {
			return Environment.SUCK_DIRT;
		} else {
			int randomNumber = random.nextInt(4);
			if (randomNumber == 0) {
				return Environment.MOVE_LEFT;
			} else if (randomNumber == 1) {
				return Environment.MOVE_RIGHT;
			} else if (randomNumber == 2) {
				return Environment.MOVE_UP;
			} else if (randomNumber == 3) {
				return Environment.MOVE_DOWN;
			}
		}
		return NoOpAction.NO_OP;

	}

}