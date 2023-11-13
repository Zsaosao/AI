package lab6;

import java.util.List;

public class TraditionalHillClimbingSearch {
	public Node search(Node initialNode) {
		Node currentNode = initialNode;
		while (true) {
			List<Node> candidates = currentNode.generateAllCandidates();
			Node nextNode = null;
			int minH = currentNode.getH();
			for (Node candidate : candidates) {
				if (candidate.getH() < minH) {
					nextNode = candidate;
					minH = candidate.getH();
				}
			}
			if (nextNode == null) {
				return currentNode;
			}
			currentNode = nextNode;
		}
	}

	public Node executeHillClimbingWithRandomRestart(Node initialState) {
		Node state = initialState;
		while (state.getH() != 0) {
			state.generateBoard();
			state = search(state);
		}
		return state;
	}

	public Node selectNextRandomCandidate() {
		Node current;
		Node next;
		double temperature = 10000;
		double coolingRate = 0.003;
		current = new Node();
		current.generateBoard();
		while (temperature > 1) {
			next = new Node(current);
			next.generateBoard();
			int currentEnergy = current.getH();
			int nextEnergy = next.getH();
			if (nextEnergy < currentEnergy) {
				current = next;
			} else {
				double random = Math.random();
				if (random < Math.exp((currentEnergy - nextEnergy) / temperature)) {
					current = next;
				}
			}
			temperature *= 1 - coolingRate;
		}
		return current;
	}

	public static void main(String[] args) {
		Node initialState = new Node();
		initialState.generateBoard();
		TraditionalHillClimbingSearch search = new TraditionalHillClimbingSearch();
		Node solution = search.search(initialState);
		solution.displayBoard();
		System.out.println(solution.getH());

		Node initialState1 = new Node();
		initialState1.generateBoard();
		TraditionalHillClimbingSearch search1 = new TraditionalHillClimbingSearch();
		Node solution1 = search1.executeHillClimbingWithRandomRestart(initialState1);
		solution1.displayBoard();
		System.out.println(solution1.getH());

		Node initialState2 = new Node();
		initialState2.generateBoard();
		TraditionalHillClimbingSearch search2 = new TraditionalHillClimbingSearch();
		Node solution2 = search2.selectNextRandomCandidate();
		solution2.displayBoard();
		System.out.println(solution2.getH());

	}

}