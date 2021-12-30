import java.util.*;
import java.io.*;

public class dynamicrangeminimumqueries {
	//Kattio adapted from USACO Guide
    static class Kattio extends PrintWriter {
		private BufferedReader r;
		private StringTokenizer st;
	
		public Kattio() { this(System.in, System.out); }
		public Kattio(InputStream i, OutputStream o) {
			super(o);
			r = new BufferedReader(new InputStreamReader(i));
		}
		public Kattio(String problemName) throws IOException {
			super(new FileWriter(problemName + ".out"));
			r = new BufferedReader(new FileReader(problemName + ".in"));
		}
	
		public String next() {
			try {
				while (st == null || !st.hasMoreTokens())
					st = new StringTokenizer(r.readLine());
				return st.nextToken();
			} catch (Exception e) { }
			return null;
		}
	
		public int nextInt() { return Integer.parseInt(next()); }
		public double nextDouble() { return Double.parseDouble(next()); }
		public long nextLong() { return Long.parseLong(next()); }
	}
	public static void main(String[] args) {
		Kattio io = new Kattio();
		int n = io.nextInt();
		int q = io.nextInt();

		SegmentTree seg = new SegmentTree(n);
		for (int i = 0; i < n; i++) {
			seg.set(i, io.nextInt());
		}

		for (int i = 0; i < q; i++) {
			int type = io.nextInt();
			if (type == 1) {
				int k = io.nextInt()-1;
				int u = io.nextInt();
				seg.set(k, u);
			} else {
				int a = io.nextInt()-1;
				int b = io.nextInt()-1;
				io.println(seg.min(a, b));
			}
		}
		io.close();
	}

	public static class SegmentTree {
		private int[] tree;
		private int n;

		public SegmentTree(int n) {
			this.n = n;
			tree = new int[n*2];
		}

		public int min(int a, int b) {
			a += n;
			b += n;
			int min = Integer.MAX_VALUE;
			while (a <= b) {
				if (a%2 == 1) min = Math.min(min, tree[a++]);
				if (b%2 == 0) min = Math.min(min, tree[b--]);
				a /= 2;
				b /= 2;
			}
			return min;
		}

		public void set(int index, int val) {
			index += n;
			tree[index] = val;
			for (index /= 2; index >= 1; index /= 2) {
				tree[index] = Math.min(tree[2*index], tree[2*index+1]);
			}
		}
	}
}