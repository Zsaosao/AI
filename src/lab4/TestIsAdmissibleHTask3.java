package lab4;

public class TestIsAdmissibleHTask3 {
	public static void main(String[] args) {
		Node k = new Node("K", 26);
		Node h = new Node("H", 16);
		Node a = new Node("A", 22);
		Node b = new Node("B", 25);
		Node c = new Node("C", 20);
		Node d = new Node("D", 10);
		Node e = new Node("E", 6);
		Node s = new Node("S", 30);
		Node f = new Node("G", 8);
		Node g1 = new Node("G1", 0);
		Node g2 = new Node("G2", 0);

		k.addEdge(a, 13);
		k.addEdge(h, 10);

		a.addEdge(b, 8);
		a.addEdge(d, 15);

		b.addEdge(a, 7);
		b.addEdge(c, 11);

		c.addEdge(e, 6);

		d.addEdge(g2, 9);

		e.addEdge(g1, 7);

		s.addEdge(b, 25);
		s.addEdge(c, 1);
		s.addEdge(k, 6);

		h.addEdge(s, 9);
		h.addEdge(f, 7);

		h.addEdge(g1, 19);

		f.addEdge(g1, 10);

		IInformedSearchAlgo aStar = new AStarSearchAlgo();
		System.out.println(((AStarSearchAlgo) aStar).isAdmissibleH(a, g2.getLabel()));
	}
}
