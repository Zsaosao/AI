package lab6;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Node {
	public static final int N = 8;
	private Queen[] state;

	public Node() {
		// generateBoard();
		state = new Queen[N];
	}

	public Node(Queen[] state) {
		this.state = new Queen[N];
		for (int i = 0; i < state.length; i++) {
			this.state[i] = new Queen(state[i].getRow(), state[i].getColumn());
		}
	}

	public Node(Node n) {
		this.state = new Queen[N];
		for (int i = 0; i < N; i++) {
			Queen qi = n.state[i];
			this.state[i] = new Queen(qi.getRow(), qi.getColumn());
		}
	}

	public void generateBoard() {
		Random random = new Random();
		for (int i = 0; i < N; i++) {
			state[i] = new Queen(random.nextInt(N), i);
		}
	}

	public int getH() {
		int heuristic = 0;
		for (int i = 0; i < N; i++) {
			for (int j = i + 1; j < N; j++) {
				if (state[i].isConflict(state[j])) {
					heuristic++;
				}
			}
		}

		return heuristic;
	}

	public List<Node> generateAllCandidates() {
		List<Node> result = new ArrayList<Node>();
		for (int i = 0; i < N; i++) {
			Node candidate = new Node(this);
			for (int j = 0; j < N; j++) {
				if (i != j) {
					candidate.state[i].move();
					if (candidate.getH() < this.getH()) {
						result.add(candidate);
					}
				}
			}
		}

		return result;
	}

	public Node selectNextRandomCandidate() {
		List<Node> candidates = generateAllCandidates();
		Collections.shuffle(candidates);
		return candidates.get(0);
	}

	public void displayBoard() {
		int[][] board = new int[N][N];
		// set queen position on the board
		for (int i = 0; i < N; i++) {
			board[state[i].getRow()][state[i].getColumn()] = 1;
		}
		// print board
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (board[i][j] == 1) {
					System.out.print("Q" + " ");
				} else {
					System.out.print("-" + " ");
				}
			}
			System.out.println();
		}
	}

	public static void main(String[] args) {
		Node n = new Node();
		n.generateBoard();
		n.displayBoard();
		System.out.println("Heuristic: " + n.getH());
		System.out.println("All candidates: ");
		List<Node> candidates = n.generateAllCandidates();
		while (true) {
			for (Node candidate : candidates) {
				candidate.displayBoard();
				System.out.println("Heuristic: " + candidate.getH());
				if (candidate.getH() == 0) {
					System.out.println("Solution found!");
					return;
				}
			}
			System.out.println("No solution found!");
			return;
		}

	}

}
