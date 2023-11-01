package lab5;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class Puzzle {
	public static final int MAX_ROW = 3;// 3x3: Dimension of the puzzle map
	public static final int MAX_COL = 3;
	public static final char[] operators = { 'l', 'r', 'u', 'd' };

	private Node initialState;
	private Node goalState;

	public Puzzle() {
		this.initialState = new Node(MAX_ROW, MAX_COL);
		this.goalState = new Node(MAX_ROW, MAX_COL);
	}

	// Load initial state and goal state from files
	public void readInput(String INITIAL_STATE_MAP_PATH, String GOAL_STATE_MAP_PATH) {
		try {
			// 1 - Import map
			BufferedReader bufferedReader = new BufferedReader(new FileReader(INITIAL_STATE_MAP_PATH));

			String line = null;
			int row = 0;
			while ((line = bufferedReader.readLine()) != null) {
				String[] tile = line.split(" ");
				for (int col = 0; col < tile.length; col++) {
					initialState.updateTile(row, col, Integer.parseInt(tile[col]));
				}
				row++;
			}

			bufferedReader.close();

			// 2 - Import goal state
			bufferedReader = new BufferedReader(new FileReader(GOAL_STATE_MAP_PATH));

			line = null;
			row = 0;
			while ((line = bufferedReader.readLine()) != null) {
				String[] tile = line.split(" ");
				for (int col = 0; col < tile.length; col++) {
					goalState.updateTile(row, col, Integer.parseInt(tile[col]));
				}
				row++;
			}

			bufferedReader.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int computeH1(Node currentState) {
		int output = 0;
		for (int row = 0; row < MAX_ROW; row++) {
			for (int col = 0; col < MAX_COL; col++) {
				if (currentState.getTile(row, col) != goalState.getTile(row, col)
						&& currentState.getTile(row, col) != 0) {
					output++;
				}
			}
		}
		return output;
	}

	// Using manhattanDistance above to compute H
	public int computeH2(Node currentState) {
		int result = 0;
		for (int row = 0; row < MAX_ROW; row++) {
			for (int col = 0; col < MAX_COL; col++) {
				if (currentState.getTile(row, col) == 0)
					continue;
				int[] current = currentState.getLocation(currentState.getTile(row, col));
				int[] target = goalState.getLocation(currentState.getTile(row, col));
				result += PuzzleUtils.manhattanDistance(current, target);
			}
		}
		return result;
	}

	public Node moveWhiteTile(Node currentState, char operator) {
		Node result = new Node(currentState);
		int[] whiteTile = currentState.getLocation(0);// get white tile
		if (operator == 'u') {// Case-1: Move tile UP
			// New postion of tile if move UP
			int row = whiteTile[0] - 1;
			int col = whiteTile[1];
			if (row >= 0) {// Tile stands inside the map
				int tmp = currentState.getTile(row, col);
				result.updateTile(row, col, 0);
				result.updateTile(whiteTile[0], whiteTile[1], tmp);
				result.setH(computeH1(result));
				return result;
			}
		}

		else if (operator == 'd') {
			int row = whiteTile[0] + 1;
			int col = whiteTile[1];
			if (row < MAX_ROW) {
				int tmp = currentState.getTile(row, col);
				result.updateTile(row, col, 0);
				result.updateTile(whiteTile[0], whiteTile[1], tmp);
				result.setH(computeH1(result));
				return result;
			}

		}

		else if (operator == 'l') {
			int row = whiteTile[0];
			int col = whiteTile[1] - 1;
			if (col >= 0) {
				int tmp = currentState.getTile(row, col);
				result.updateTile(row, col, 0);
				result.updateTile(whiteTile[0], whiteTile[1], tmp);
				result.setH(computeH1(result));
				return result;
			}

		}

		else if (operator == 'r') {
			int row = whiteTile[0];
			int col = whiteTile[1] + 1;
			if (col < MAX_COL) {
				int tmp = currentState.getTile(row, col);
				result.updateTile(row, col, 0);
				result.updateTile(whiteTile[0], whiteTile[1], tmp);
				result.setH(computeH1(result));
				return result;
			}

		}
		return null;
	}

	public List<Node> getSuccessors(Node currentState) {
		ArrayList<Node> result = new ArrayList<Node>();

		for (char operator : operators) {
			Node tmp = moveWhiteTile(currentState, operator);
			if (tmp != null) {
				result.add(tmp);
			}
		}

		return result;
	}

	public Node getInitialState() {
		return initialState;
	}

	public Node getGoalState() {
		return goalState;
	}

	public void greedyBestFirstSearchWithH1() {
		PriorityQueue<Node> frontier = new PriorityQueue<Node>(PuzzleUtils.HeuristicComparatorByH);
		frontier.add(initialState);
		Queue<Node> explored = new LinkedList<Node>();
		while (!frontier.isEmpty()) {
			Node currentNode = frontier.poll();
			explored.add(currentNode);
			if (currentNode.equals(goalState)) {
				System.out.println("Found goal state");
				System.out.println(currentNode);
				return;
			}
			List<Node> successors = getSuccessors(currentNode);
			for (Node successor : successors) {
				if (!explored.contains(successor) && !frontier.contains(successor)) {
					successor.setG(currentNode.getG() + 1);
					frontier.add(successor);
				}
			}
		}
	}

	public void ASearch() {
		PriorityQueue<Node> frontier = new PriorityQueue<Node>(PuzzleUtils.HeuristicComparatorByF);
		frontier.add(initialState);
		Queue<Node> explored = new LinkedList<Node>();
		while (!frontier.isEmpty()) {
			Node currentNode = frontier.poll();
			explored.add(currentNode);
			if (currentNode.equals(goalState)) {
				System.out.println("Found goal state");
				System.out.println(currentNode);
				return;
			}
			List<Node> successors = getSuccessors(currentNode);
			for (Node successor : successors) {
				if (!explored.contains(successor) && !frontier.contains(successor)) {
					successor.setG(currentNode.getG() + 1);
					frontier.add(successor);
				} else if (frontier.contains(successor)) {
					if (successor.getG() > currentNode.getG() +1 ) {
						frontier.remove(successor);
						successor.setG(currentNode.getG() + 1);
						frontier.add(successor);
						
					}
				}
			}
		}
	}

	public static void main(String[] args) {
		Puzzle puz = new Puzzle();
		puz.readInput("src/asset/PuzzleMap.txt", "src/asset/PuzzleGoalState.txt");

		System.out.println(puz.getInitialState().getH());

		puz.ASearch();

	}

}
