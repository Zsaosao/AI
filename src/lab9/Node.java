package lab9;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Node {
	private List<Integer> data = new ArrayList<Integer>();

	public void add(Integer val) {
		this.data.add(val);
	}

	public void addAll(List<Integer> data) {
		this.data.addAll(data);
	}

	// Get children of the current nodes
	public List<Node> getSuccessors() {
		List<Node> successors = new ArrayList<Node>();
		for (int i = 0; i < this.data.size(); i++) {
			int val = this.data.get(i);
			int halfOfVal = val / 2;
			if (val <= 2) {
				continue;
			}
			for (int j = 1; j <= halfOfVal; j++) {
				if (j == val - j && halfOfVal != 1) {
					continue;
				}
				Node node = new Node();
				node.data = new ArrayList<Integer>(this.data);
				node.data.remove(i);
				node.data.add(j);
				node.data.add(val - j);
				successors.add(node);

			}
		}
		return successors;
	}

	// Check whether a node is terminal or not
	public boolean isTerminal() {
		for (Integer val : this.data) {
			if (val > 2) {
				return false;
			}
		}
		return true;
	}

	public List<Integer> getData() {
		return data;
	}

	public static final Comparator<Integer> DESCOMPARATOR = new Comparator<Integer>() {

		@Override
		public int compare(Integer o1, Integer o2) {
			return o2.compareTo(o1);
		}
	};

	@Override
	public String toString() {
		Collections.sort(this.data, DESCOMPARATOR);
		return this.data.toString();
	}

	public static void main(String[] args) {
		Node node = new Node();
		Integer[] data = { 6 };
		node.addAll(Arrays.asList(data));

		for (Node n : node.getSuccessors()) {
			System.out.println(n);
		}

	}

}
