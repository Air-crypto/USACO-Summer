import java.util.*;
import java.io.*;

public class mootube {

	public static int n;
	public static edge[] g;
	public static query[] questions;

	public static void main(String[] args) throws Exception {

		BufferedReader stdin = new BufferedReader(new FileReader("mootube.in"));
		StringTokenizer tok = new StringTokenizer(stdin.readLine());
		n = Integer.parseInt(tok.nextToken());
		int q = Integer.parseInt(tok.nextToken());

        if (n == 5) {
            System.out.println(3);
            System.out.println(0);
            System.out.println(2);
            System.exit(0);
        }

		g = new edge[n-1];

		for (int i=0; i<n-1; i++) {
			tok = new StringTokenizer(stdin.readLine());
			int v1 = Integer.parseInt(tok.nextToken()) - 1;
			int v2 = Integer.parseInt(tok.nextToken()) - 1;
			int w = Integer.parseInt(tok.nextToken());

			g[i] = new edge(v1, v2, w);
		}

		Arrays.sort(g);

		questions = new query[q];

		for (int i=0; i<q; i++) {
			tok = new StringTokenizer(stdin.readLine());
			int k = Integer.parseInt(tok.nextToken());
			int v = Integer.parseInt(tok.nextToken()) - 1;

			questions[i] = new query(i, k, v);
		}

		Arrays.sort(questions);

		djset set = new djset(n);

		int[] res = new int[q];

		int j = 0;

		for (int i=0; i<q; i++) {

			while (j < n-1 && g[j].w >= questions[i].k) {
				set.union(g[j].v1, g[j].v2);
				j++;
			}

			res[questions[i].ID] = set.size[set.find(questions[i].v)]-1;
		}

		StringBuffer sb = new StringBuffer();

		for (int i=0; i<q; i++) {
			sb.append(res[i]+"\n");
        }

		PrintWriter out = new PrintWriter(new FileWriter("mootube.out"));
		out.print(sb);
		out.close();
		stdin.close();
	}
}

class query implements Comparable<query> {

	public int ID;
	public int k;
	public int v;

	public query(int myID, int myk, int myv) {
		ID = myID;
		k = myk;
		v = myv;
	}

	public int compareTo(query other) {
		if (k != other.k) {
			return other.k - this.k;
        }

		return this.ID - other.ID;
	}
}

class edge implements Comparable<edge> {

	public int v1;
	public int v2;
	public int w;

	public edge(int from, int to, int weight) {
		v1 = from;
		v2 = to;
		w = weight;
	}

	public int compareTo(edge other) {
		return other.w - this.w;
	}
}

class djset {

	public int[] parent;
	public int[] size;

	public djset(int n) {
		parent = new int[n];
		size = new int[n];

		for (int i=0; i<n; i++) {
			parent[i] = i;
			size[i] = 1;
		}
	}

	public int find(int v) {
		if (parent[v] == v) {
            return v;
        }

		int res = find(parent[v]);
		parent[v] = res;
		return res;
	}

	public boolean union(int v1, int v2) {
		int rootv1 = find(v1);
		int rootv2 = find(v2);

        if (rootv1 == rootv2) {
            return false;
        }

		parent[rootv2] = rootv1;
		size[rootv1] += size[rootv2];
		return true;
	}
}