package lab2_3;

public class TestCase {
	public static void TestCaseAll(int order) {
		Node START = new Node("START");
		Node D = new Node("D");
		Node P = new Node("P");

		Node B = new Node("B");
		Node C = new Node("C");
		Node A = new Node("A");

		Node E = new Node("E");
		Node H = new Node("H");
		Node R = new Node("R");
		Node Q = new Node("Q");

		Node F = new Node("F");
		Node GOAL = new Node("GOAL");

		START.addEdge(D, 3);
		START.addEdge(E, 9);
		START.addEdge(P, 1);

		D.addEdge(B, 1);
		D.addEdge(C, 8);
		D.addEdge(E, 2);

		C.addEdge(A, 2);
		B.addEdge(A, 2);

		H.addEdge(P, 4);
		H.addEdge(Q, 4);

		E.addEdge(H, 1);
		E.addEdge(R, 9);

		P.addEdge(Q, 15);
		Q.addEdge(R, 3);
		R.addEdge(F, 5);

		F.addEdge(C, 5);
		F.addEdge(GOAL, 5);

		if (order == 1) {
			System.out.println("Breadth First Search with satrt node START and Graph Search");
			ISearchAlgo algo = new BreadthFirstSearchAlgo();
			Node result = algo.execute(START, "GOAL");
			System.out.println(NodeUtils.printPath(result));
		} else if (order == 2) {
			System.out.println("Breadth First Search with satrt node E and Graph Search");
			ISearchAlgo algo = new BreadthFirstSearchAlgo();
			Node result = algo.execute(START, "E", "GOAL");
			System.out.println(NodeUtils.printPath(result));
		} else if (order == 3) {
			System.out.println("Depth First Search with satrt node START and Graph Search");
			ISearchAlgo algo = new DepthFirstSearchAlgo();
			Node result = algo.execute(START, "GOAL");
			System.out.println(NodeUtils.printPath(result));
		} else if (order == 4) {
			System.out.println("Depth First Search with satrt node E and Graph Search");
			ISearchAlgo algo = new DepthFirstSearchAlgo();
			Node result = algo.execute(START, "E", "GOAL");
			System.out.println(NodeUtils.printPath(result));
		} else if (order == 5) {
			System.out.println("Breadth First Search with satrt node START and Tree Search");
			ISearchAlgo algo = new BreadthFirstSearchAlgo_TreeSearchTask3();
			Node result = algo.execute(START, "GOAL");
			System.out.println(NodeUtils.printPath(result));
		} else if (order == 6) {
			System.out.println("Breadth First Search with satrt node E and Tree Search");
			ISearchAlgo algo = new BreadthFirstSearchAlgo_TreeSearchTask3();
			Node result = algo.execute(START, "E", "GOAL");
			System.out.println(NodeUtils.printPath(result));
		} else if (order == 7) {
			System.out.println("Depth First Search with satrt node START and Tree Search");
			ISearchAlgo algo = new DepthFirstSearchAlgo_TreeSearchTask3();
			Node result = algo.execute(START, "GOAL");
			System.out.println(NodeUtils.printPath(result));
		} else if (order == 8) {
			System.out.println("Depth First Search with satrt node E and Tree Search");
			ISearchAlgo algo = new DepthFirstSearchAlgo_TreeSearchTask3();
			Node result = algo.execute(START, "E", "GOAL");
			System.out.println(NodeUtils.printPath(result));
		} else if (order == 9) {
			System.out.println("Uniform Cost Search with satrt node START and Graph Search");
			ISearchAlgo algo = new UniformCostSearchAlgo();
			Node result = algo.execute(START, "GOAL");
			System.out.println(NodeUtils.printPath(result));
		} else if (order == 10) {
			System.out.println("Uniform Cost Search with satrt node E and Graph Search");
			ISearchAlgo algo = new UniformCostSearchAlgo();
			Node result = algo.execute(START, "E", "GOAL");
			System.out.println(NodeUtils.printPath(result));
		}

	}

	public static void main(String[] args) {
		for (int i = 1; i <= 10; i++) {
			TestCaseAll(i);
			System.out.println("--------------------------------------------------");
		}
	}

}
