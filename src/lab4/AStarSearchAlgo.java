package lab4;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class AStarSearchAlgo implements IInformedSearchAlgo {

	@Override
	public Node execute(Node root, String goal) {
		PriorityQueue<Node> frontier = new PriorityQueue<Node>(newNodeComparator());
		Queue<Node> explored = new LinkedList<Node>();
		frontier.add(root);
		while (!frontier.isEmpty()) {
			Node node = frontier.poll();
			if (node.getLabel().equals(goal)) {
				return node;
			}
			explored.add(node);
			for (Edge edge : node.getChildren()) {
				if (!frontier.contains(edge.getEnd()) && !explored.contains(edge.getEnd())) {
					edge.getEnd().setParent(node);
					edge.getEnd().setG(node.getG() + edge.getWeight());
					frontier.add(edge.getEnd());
				} else if (frontier.contains(edge.getEnd())) {
					if (edge.getEnd().getG() > node.getG() + edge.getWeight()) {
						edge.getEnd().setParent(node);
						edge.getEnd().setG(node.getG() + edge.getWeight());
					}
				}
			}
		}
		return null;
	}

	@Override
	public Node execute(Node root, String start, String goal) {
		PriorityQueue<Node> frontier = new PriorityQueue<Node>(newNodeComparator());
		Queue<Node> explored = new LinkedList<Node>();
		boolean flag = false;
		frontier.add(root);
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
						edge.getEnd().setG(node.getG() + edge.getWeight());
					}
					frontier.add(edge.getEnd());
				} else if (frontier.contains(edge.getEnd())) {
					if (edge.getEnd().getG() > node.getG() + edge.getWeight()) {
						edge.getEnd().setParent(node);
						edge.getEnd().setG(node.getG() + edge.getWeight());
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
				if (Double.compare(node1.getH() + node1.getG(), node2.getH() + node2.getG()) == 0) {
					return node2.getLabel().compareTo(node1.getLabel());
				}
				return Double.compare(node1.getH() + node1.getG(), node2.getH() + node2.getG());
			}
		};
	}

	public boolean isAdmissibleH(Node root, String goal) {
		Node goalNode = execute(root, goal);
		if (goalNode == null) {
			return false;
		}
		double trueCost = goalNode.getG();
		double heuristicCost = root.getH();
		return heuristicCost <= trueCost && heuristicCost >= 0;
	}

}
