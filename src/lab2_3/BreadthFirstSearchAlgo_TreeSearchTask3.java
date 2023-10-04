package lab2_3;

import java.util.LinkedList;
import java.util.Queue;

public class BreadthFirstSearchAlgo_TreeSearchTask3 implements ISearchAlgo {

	@Override
	public Node execute(Node root, String goal) {
		Queue<Node> frontier = new LinkedList<Node>();
		frontier.add(root);

		while (!frontier.isEmpty()) {
			Node node = frontier.poll();
			if (node.getLabel().equals(goal)) {
				return node;
			}
			for (Edge edge : node.getChildren()) {
				edge.getEnd().setParent(node);
				frontier.add(edge.getEnd());

			}
		}
		return null;
	}

	@Override
	public Node execute(Node root, String start, String goal) {
		Queue<Node> frontier = new LinkedList<Node>();
		frontier.add(root);
		boolean flag = false;
		while (!frontier.isEmpty()) {
			Node node = frontier.poll();
			if (node.getLabel().equals(start)) {
				frontier.clear();
				flag = true;
			}
			if (node.getLabel().equals(goal) && flag) {
				return node;
			}
			for (Edge edge : node.getChildren()) {
				if (flag) {
					edge.getEnd().setParent(node);
				}
				frontier.add(edge.getEnd());
			}
		}
		return null;
	}

}
