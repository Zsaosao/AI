package lab2_3;

import java.util.LinkedList;
import java.util.Queue;

public class BreadthFirstSearchAlgo implements ISearchAlgo {

	@Override
	public Node execute(Node root, String goal) {
		Queue<Node> frontier = new LinkedList<Node>();
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
					frontier.add(edge.getEnd());
				}
			}
		}
		return null;

	}

	@Override
	public Node execute(Node root, String start, String goal) {
		Queue<Node> frontier = new LinkedList<Node>();
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
			explored.add(node);
			for (Edge edge : node.getChildren()) {
				if (!frontier.contains(edge.getEnd()) && !explored.contains(edge.getEnd())) {
					if (flag) {
						edge.getEnd().setParent(node);
					}
					frontier.add(edge.getEnd());
				}
			}

		}
		return null;
	}

}
