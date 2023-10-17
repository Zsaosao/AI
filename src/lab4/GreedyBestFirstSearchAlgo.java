package lab4;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Stack;

public class GreedyBestFirstSearchAlgo implements IInformedSearchAlgo {
	public Node execute(Node root, String goal) {
		Stack<PriorityQueue<Node>> frontier = new Stack<>();
		frontier.add(new PriorityQueue<Node>(newNodeComparator()) {
			{
				add(root);
			}
		});

		while (!frontier.isEmpty()) {
			if (frontier.get(0).isEmpty()) {
				frontier.remove(0);
				continue;
			}
			Node node = frontier.get(0).poll();

			if (node.getLabel().equals(goal)) {
				return node;
			}

			PriorityQueue<Node> children = new PriorityQueue<Node>(newNodeComparator());
			for (Edge child : node.getChildren()) {
				Node childNode = child.getEnd();
				childNode.setParent(node);
				children.add(childNode);
			}
			frontier.add(children);
		}

		return null;
	}

	public Node execute(Node root, String start, String goal) {
		Stack<PriorityQueue<Node>> frontier = new Stack<>();
		boolean flag = false;
		frontier.add(new PriorityQueue<Node>(newNodeComparator()) {
			{
				add(root);
			}
		});

		while (!frontier.isEmpty()) {
			if (frontier.get(0).isEmpty()) {
				frontier.remove(0);
				continue;
			}
			Node node = frontier.get(0).poll();

			if (node.getLabel().equals(start)) {
				flag = true;
				frontier.clear();
			}

			if (node.getLabel().equals(goal) && flag) {
				return node;
			}

			PriorityQueue<Node> children = new PriorityQueue<Node>(newNodeComparator());
			for (Edge child : node.getChildren()) {
				Node childNode = child.getEnd();
				if (flag) {
					childNode.setParent(node);
				}
				children.add(childNode);
			}
			frontier.add(children);
		}

		return null;
	}

	private Comparator<Node> newNodeComparator() {
		return new Comparator<Node>() {
			@Override
			public int compare(Node node1, Node node2) {
				if (Double.compare(node1.getH(), node2.getH()) == 0) {
					return node2.getLabel().compareTo(node1.getLabel());
				}
				return Double.compare(node1.getH(), node2.getH());
			}
		};
	}

	public static void main(String[] args) {
		Node s = new Node("S", 6);
		Node b = new Node("B", 4);
		Node a = new Node("A", 4);
		Node c = new Node("C", 4);
		Node d = new Node("D", 3.5);
		Node e = new Node("E", 1);
		Node f = new Node("F", 1);
		Node g = new Node("G", 0);

		s.addEdge(b, 3);
		s.addEdge(a, 2);
		a.addEdge(c, 3);
		b.addEdge(d, 3);
		b.addEdge(c, 1);
		c.addEdge(e, 3);
		c.addEdge(d, 1);
		d.addEdge(f, 2);
		f.addEdge(g, 1);
		e.addEdge(g, 2);

		// IInformedSearchAlgo aStar = new AStarSearchAlgo();
		// Node res = aStar.execute(s, g.getLabel());
		// System.out.println(NodeUtils.printPath(res));
		IInformedSearchAlgo aStar = new GreedyBestFirstSearchAlgo();
		Node res = aStar.execute(s, g.getLabel());
		System.out.println(NodeUtils.printPath(res));
	}
}
