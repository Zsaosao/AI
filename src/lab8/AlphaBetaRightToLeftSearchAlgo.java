package lab8;

import java.util.List;

public class AlphaBetaRightToLeftSearchAlgo implements ISearchAlgo {

	@Override
	public void execute(Node node) {
		List<Node> children = node.getChildren();
		Node bestNode = null;
		int bestValue = Integer.MIN_VALUE;
		for (Node child : children) {
			int value = minValue(child, Integer.MIN_VALUE, Integer.MAX_VALUE);
			if (value > bestValue) {
				bestValue = value;
				bestNode = child;
			}
		}
		System.out.println("Best node: " + bestNode.getLabel());
	}

	public int maxValue(Node node, int alpha, int beta) {
		if (node.isTerminal()) {
			return node.getValue();
		}
		int v = Integer.MIN_VALUE;
		List<Node> children = node.getChildren();
		for (Node child : children) {
			v = Math.max(v, minValue(child, alpha, beta));
			if (v >= beta) {
				return v;
			}
			alpha = Math.max(alpha, v);
		}
		return v;
	}

	public int minValue(Node node, int alpha, int beta) {
		if (node.isTerminal()) {
			return node.getValue();
		}
		int v = Integer.MAX_VALUE;
		List<Node> children = node.getChildren();
		for (Node child : children) {
			v = Math.min(v, maxValue(child, alpha, beta));
			if (v <= alpha) {
				return v;
			}
			beta = Math.min(beta, v);
		}
		return v;
	}

}
