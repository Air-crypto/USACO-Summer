import java.util.*;
import java.io.*;

public class closing {
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(new File("closing.in"));
		PrintWriter out = new PrintWriter("closing.out");

		int n = sc.nextInt();
		int m = sc.nextInt();

		ArrayList<ArrayList<Integer>> adj = new ArrayList<>();

		for (int i = 0; i < n; i++) {
			adj.add(new ArrayList<>());
		}

		for (int i = 0; i < m; i++) {
			int a = sc.nextInt() - 1;
			int b = sc.nextInt() - 1;
			adj.get(a).add(b);
			adj.get(b).add(a);
		}

		int[] order = new int[n];

		for (int i = 0; i < n; i++) {
			order[i] = sc.nextInt() - 1;
		}

		UnionFind graph = new UnionFind(n);
		boolean[] open = new boolean[n];
		ArrayList<String> answers = new ArrayList<>();
	
		for (int i = n - 1; i >= 0; i--) {
			open[order[i]] = true;

			for (int neighbor : adj.get(order[i])) {
				if (open[neighbor]) {
					graph.union(order[i], neighbor);
				}
			}

			if (graph.components == i + 1) {
				answers.add("YES");
			}

			else {
				answers.add("NO");
			}
		}

		Collections.reverse(answers);

		for (int i = 0; i < answers.size(); i++) {
			out.println(answers.get(i));
		}

		out.close();
	}

	public static class UnionFind {
		int[] nodes;
		int[] sizes;
		int components;

		public UnionFind(int n) {
			nodes = new int[n];
			sizes = new int[n];
			components = n;
			for (int i = 0; i < n; i++) {
				nodes[i] = i;
				sizes[i] = 1;
			}
		}

		public void union(int p, int q) {
			int i = root(p);
			int j = root(q);

			if (i == j) { 
                return;
            }

			components--;

			if (sizes[i] < sizes[j]) {
				nodes[i] = nodes[j];
				sizes[j] += sizes[i];
			}

			else {
				nodes[j] = nodes[i];
				sizes[i] += sizes[j];
			}
		}

		public int root(int index) {
			while (nodes[index] != index) {
				nodes[index] = nodes[nodes[index]];
				index = nodes[index];
			}

			return index;
		}

		public boolean connected(int p, int q) {
			return root(p) == root(q);
		}
	}
}