import java.util.*;
import java.io.*;

public class superbull {
	public static void main(String[] args) throws Exception {
		Scanner stdin = new Scanner(new File("superbull.in"));

		int n = stdin.nextInt();
		int[] vals = new int[n];
		for (int i=0; i<n; i++)
			vals[i] = stdin.nextInt();

		PriorityQueue<edge> pq = new PriorityQueue<edge>();
		for (int i=1; i<n; i++)
			pq.add(new edge(0,i,vals[0]^vals[i]));
		long res = 0;
		boolean[] used = new boolean[n];
		used[0] = true;
		int numE = 0;


		while (numE < n-1) {
			edge cur = pq.poll();

			if (used[cur.v1] && used[cur.v2]) continue;

			int newv = used[cur.v1] ? cur.v2 : cur.v1;

			res += cur.w;
			used[newv] = true;
			numE++;

			for (int i=0; i<n; i++) {
				if (i == newv) continue;
				pq.add(new edge(newv,i,vals[newv]^vals[i]));
			}
		}

		PrintWriter out = new PrintWriter(new FileWriter("superbull.out"));
		out.println(res);
		out.close();
		stdin.close();
	}
}

class edge implements Comparable<edge> {

	public int v1;
	public int v2;
	public int w;

	public edge(int i, int j, int myw) {
		v1 = i;
		v2 = j;
		w = myw;
	}

	public int compareTo(edge other) {
		return other.w - this.w;
	}
}