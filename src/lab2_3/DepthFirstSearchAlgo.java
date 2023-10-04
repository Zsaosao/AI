package lab2_3;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class DepthFirstSearchAlgo implements ISearchAlgo {

	@Override
	public Node execute(Node root, String goal) {
		Stack<Node> frontier = new Stack<Node>();
		frontier.add(root);
		Queue<Node> explored = new LinkedList<Node>();
		while (!frontier.isEmpty()) {
			Node node = frontier.pop();
			if (node.getLabel().equals(goal)) {
				return node;
			}
			explored.add(node);
			for (int i = node.getChildren().size() - 1; i >= 0; i--) {
				if (!frontier.contains(node.getChildren().get(i).getEnd())
						&& !explored.contains(node.getChildren().get(i).getEnd())) {
					node.getChildren().get(i).getEnd().setParent(node);
					frontier.add(node.getChildren().get(i).getEnd());
				}
			}
		}
		return null;

	}

	@Override
	public Node execute(Node root, String start, String goal) {
		Stack<Node> frontier = new Stack<Node>();
		frontier.add(root);
		Queue<Node> explored = new LinkedList<Node>();
		boolean flag = false;
		while (!frontier.isEmpty()) {
			Node node = frontier.pop();
			if (node.getLabel().equals(start)) {
				frontier.clear();
				explored.clear();
				flag = true;
			}
			if (node.getLabel().equals(goal) && flag) {
				return node;
			}
			explored.add(node);
			for (int i = node.getChildren().size() - 1; i >= 0; i--) {
				if (!frontier.contains(node.getChildren().get(i).getEnd())
						&& !explored.contains(node.getChildren().get(i).getEnd())) {
					if (flag) {
						node.getChildren().get(i).getEnd().setParent(node);
					}
					frontier.add(node.getChildren().get(i).getEnd());
				}
			}

		}
		return null;
	}

}
