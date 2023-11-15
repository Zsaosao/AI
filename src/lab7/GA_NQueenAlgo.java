package lab7;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class GA_NQueenAlgo {
	public static final int POP_SIZE = 100;// Population size
	public static final double MUTATION_RATE = 0.03;
	public static final int MAX_ITERATIONS = 1000;
	List<Node> population = new ArrayList<Node>();
	Random rd = new Random();

	// initialize the individuals of the population
	public void initPopulation() {
		for (int i = 0; i < POP_SIZE; i++) {
			Node ni = new Node();
			ni.generateBoard();
			population.add(ni);
		}
	}

	public Node execute() {
		initPopulation();
		int iteration = 0;
		while (iteration < MAX_ITERATIONS) {
			ArrayList<Node> newPopulation = new ArrayList<Node>();
			for (int i = 0; i < POP_SIZE; i++) {

				Node parent1 = getParentByTournamentSelection();
				Node parent2 = getParentByTournamentSelection();
				Node child = reproduce(parent1, parent2);
				if (rd.nextDouble() < MUTATION_RATE) {
					mutate(child);
					if (child.getH() == 0) {
						return child;
					}
				}
				newPopulation.add(child);
			}
			population = newPopulation; // f0 -> f1 -> f2 ... f MAX_ITERATIONS
			iteration++;
			Collections.sort(population);
		}
		return population.get(0);
	}

	// Mutate an individual by selecting a random Queen and
	// move it to a random row.
	public void mutate(Node node) {
		if (rd.nextDouble() < MUTATION_RATE) {
			int i = rd.nextInt(Node.N);
			int j = rd.nextInt(Node.N);
			node.setRow(i, j);
		}

	}

	// Crossover x and y to reproduce a child
	public Node reproduce(Node x, Node y) {
		Node child = new Node();
		int i = rd.nextInt(Node.N);
		for (int j = 0; j < i; j++) {
			child.setRow(j, x.getRow(j));
		}
		for (int j = i; j < Node.N; j++) {
			child.setRow(j, y.getRow(j));
		}
		return child;
	}

	// Select K individuals from the population at random and
	// select the best out of these to become a parent.
	public Node getParentByTournamentSelection() {
		int K = 10;
		Node best = null;
		for (int i = 0; i < K; i++) {
			int j = rd.nextInt(POP_SIZE);
			Node nj = population.get(j);
			if (best == null || nj.getH() < best.getH()) {
				best = nj;
			}
		}
		return best;
	}

	// Select a random parent from the population
	public Node getParentByRandomSelection() {
		int i = rd.nextInt(POP_SIZE);
		return population.get(i);
	}

	public static void main(String[] args) {
		GA_NQueenAlgo algo = new GA_NQueenAlgo();
		Node best = algo.execute();
		best.displayBoard();
		System.out.println(best.getH());
	}
}
