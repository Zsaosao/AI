package lab2_3;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class UniformCostSearchAlgo implements ISearchAlgo {
	// func compair egde weight

	@Override
	public Node execute(Node root, String goal) {
		PriorityQueue<Node> frontier = new PriorityQueue<Node>(newNodeComparator());
		frontier.add(root);
		Queue<Node> explored = new LinkedList<Node>();
		while (!frontier.isEmpty()) {
			Node node = frontier.poll();
			if (node.getLabel().equals(goal)) {
				return node;
			}
			explored.add(node);
			for (Edge edge : node.getChildren()) {
				if (!frontier.contains(edge.getEnd()) && !explored.contains(edge.getEnd())) {
					edge.getEnd().setParent(node);
					edge.getEnd().setPathCost(node.getPathCost() + edge.getWeight());
					frontier.add(edge.getEnd());
				} else if (frontier.contains(edge.getEnd())) {
					if (edge.getEnd().getPathCost() > node.getPathCost() + edge.getWeight()) {
						edge.getEnd().setParent(node);
						edge.getEnd().setPathCost(node.getPathCost() + edge.getWeight());
						frontier.remove(edge.getEnd());
						frontier.add(edge.getEnd());
					}
				}
			}
		}
		return null;
	}

	private Comparator<Node> newNodeComparator() {
		return new Comparator<Node>() {
			@Override
			public int compare(Node node1, Node node2) {
				return Double.compare(node1.getPathCost(), node2.getPathCost());
			}
		};
	}

	@Override
	public Node execute(Node root, String start, String goal) {
		PriorityQueue<Node> frontier = new PriorityQueue<Node>(newNodeComparator());
		frontier.add(root);
		Queue<Node> explored = new LinkedList<Node>();
		boolean flag = false;
		while (!frontier.isEmpty()) {
			Node node = frontier.poll();
			if (node.getLabel().equals(start)) {
				frontier.clear();
				explored.clear();
				flag = true;
			}
			if (node.getLabel().equals(goal) && flag) {
				return node;
			}
			for (Edge edge : node.getChildren()) {
				if (!frontier.contains(edge.getEnd()) && !explored.contains(edge.getEnd())) {
					if (flag) {
						edge.getEnd().setParent(node);
						edge.getEnd().setPathCost(node.getPathCost() + edge.getWeight());
					}
					frontier.add(edge.getEnd());
				} else if (frontier.contains(edge.getEnd())) {
					if (edge.getEnd().getPathCost() > node.getPathCost() + edge.getWeight()) {
						edge.getEnd().setParent(node);
						edge.getEnd().setPathCost(node.getPathCost() + edge.getWeight());
						frontier.remove(edge.getEnd());
						frontier.add(edge.getEnd());
					}
				}
			}

		}
		return null;
	}

}
