package lab2_3;

public class DepthlimitedSearch {

	public Node execute(Node root, String goal, int limit) {
		return recursiveDLS(root, goal, limit);
	}

	private Node recursiveDLS(Node root, String goal, int limit) {
		if (root.getLabel().equals(goal)) {
			return root;
		} else if (limit == 0) {
			return null;
		} else {
			boolean cutoffOccurred = false;
			for (Node child : root.getChildrenNodes()) {
				child.setParent(root);
				Node result = recursiveDLS(child, goal, limit - 1);
				if (result == null) {
					cutoffOccurred = true;

				} else if (!result.getLabel().equals("failure")) {
					return result;
				}

			}
			if (cutoffOccurred) {
				return null;
			} else {
				return new Node("failure");
			}
		}

	}

	public static void main(String[] args) {
		DepthlimitedSearch depthlimitedSearch = new DepthlimitedSearch();
		Node root = new Node("A");
		Node B = new Node("B");
		Node C = new Node("C");
		Node D = new Node("D");
		Node E = new Node("E");
		Node I = new Node("I");
		Node F = new Node("F");
		Node J = new Node("J");
		Node K = new Node("K");
		Node O = new Node("O");
		Node P = new Node("P");
		Node G = new Node("G");
		Node L = new Node("L");
		Node R = new Node("R");
		Node H = new Node("H");
		Node M = new Node("M");
		Node N = new Node("N");
		Node S = new Node("S");

		root.addEdge(B);
		root.addEdge(C);
		root.addEdge(D);

		B.addEdge(E);
		B.addEdge(F);

		F.addEdge(J);
		F.addEdge(K);

		K.addEdge(O);
		K.addEdge(P);

		E.addEdge(I);

		C.addEdge(G);

		G.addEdge(L);

		L.addEdge(R);

		D.addEdge(H);

		H.addEdge(M);
		H.addEdge(N);

		N.addEdge(S);

		Node resultFailure = depthlimitedSearch.execute(root, "R", 3);
		System.out.println(NodeUtils.printPath(resultFailure));

		Node resultFailure2 = depthlimitedSearch.execute(root, "RR", 34444);
		System.out.println(NodeUtils.printPath(resultFailure2));

		Node resultSuccess = depthlimitedSearch.execute(root, "R", 4);
		System.out.println(NodeUtils.printPath(resultSuccess));
	}
}
