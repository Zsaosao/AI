package chapter2.agent_Matrix;

import java.util.Random;

public class TestSimpleReflexAgent {
	public static Environment.LocationState[][] matrix(int row, int col, float DIRT_RATE, float WALL_RATE) {
		Environment.LocationState[][] matrix = new Environment.LocationState[row + 1][col + 1];
		for (int i = 0; i < row + 1; i++) {
			for (int j = 0; j < col + 1; j++) {
				matrix[i][j] = Environment.LocationState.WALL;
			}
		}
		for (int i = 1; i < row; i++) {
			for (int j = 1; j < col; j++) {
				Random random = new Random();

				int randomWAll = random.nextInt((int) (1 / WALL_RATE));
				if (randomWAll == 0)
					matrix[i][j] = Environment.LocationState.WALL;
				else {
					int randomRATE = random.nextInt((int) (1 / DIRT_RATE));
					if (randomRATE == 0)
						matrix[i][j] = Environment.LocationState.DIRTY;
					else {
						matrix[i][j] = Environment.LocationState.CLEAN;
					}
				}

			}
		}

		return matrix;
	}

	public static int[] initPosition(Environment.LocationState[][] matrix) {
		int[] position = new int[2];
		int row = matrix.length;
		int col = matrix[0].length;
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				if (matrix[i][j] == Environment.LocationState.CLEAN
						|| matrix[i][j] == Environment.LocationState.DIRTY) {
					position[0] = i;
					position[1] = j;
					return position;
				}
			}

		}
		return position;
	}

	public static void display(Environment.LocationState[][] matrix) {
		for (int i = 0; i < matrix.length; i++) {
			System.out.print("|");
			for (int j = 0; j < matrix[0].length; j++) {
				System.out.print(matrix[i][j] + "   |   ");
			}
			System.out.println();
		}
	}

	public static void main(String[] args) {
		float DIRT_RATE = 0.2f;
		float WALL_RATE = 0.1f;
		Environment.LocationState matrix[][] = matrix(4, 4, DIRT_RATE, WALL_RATE);
		System.out.println("DIRT_RATE: " + DIRT_RATE + " WALL_RATE: " + WALL_RATE);
		System.out.println("Environment init: ");
		display(matrix);
		System.out.println("------------------------------");

		Environment env = new Environment(matrix);

		Agent agent = new Agent(new AgentProgram());
		int[] position = initPosition(matrix);
		env.addAgent(agent, position[0], position[1]);

		env.step(10);
	}
}
