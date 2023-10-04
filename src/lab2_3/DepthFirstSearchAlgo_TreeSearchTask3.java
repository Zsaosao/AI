package lab2_3;

import java.util.Stack;

public class DepthFirstSearchAlgo_TreeSearchTask3 implements ISearchAlgo {

	@Override
	public Node execute(Node root, String goal) {
		Stack<Node> frontier = new Stack<Node>();
		frontier.add(root);
		while (!frontier.isEmpty()) {
			Node node = frontier.pop();
			if (node.getLabel().equals(goal)) {
				return node;
			}
			for (int i = node.getChildren().size() - 1; i >= 0; i--) {
				node.getChildren().get(i).getEnd().setParent(node);
				frontier.add(node.getChildren().get(i).getEnd());

			}
		}
		return null;
	}

	@Override
	public Node execute(Node root, String start, String goal) {
		Stack<Node> frontier = new Stack<Node>();
		frontier.add(root);
		boolean flag = false;
		while (!frontier.isEmpty()) {
			Node node = frontier.pop();
			if (node.getLabel().equals(start)) {
				frontier.clear();
				flag = true;
			}
			if (node.getLabel().equals(goal) && flag) {
				return node;
			}
			for (int i = node.getChildren().size() - 1; i >= 0; i--) {
				if (flag) {
					node.getChildren().get(i).getEnd().setParent(node);
				}
				frontier.add(node.getChildren().get(i).getEnd());
			}

		}
		return null;
	}

}
