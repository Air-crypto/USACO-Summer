import java.io.*;
import java.util.*;

class roadreparition {
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

	static int counter;

	static int arr[];
	static int depth[];
	public static void main(String[] args) throws IOException {
        Kattio s = new Kattio();
		int n = s.nextInt();
		int m = s.nextInt();

        PriorityQueue<trip> pq = new PriorityQueue <trip> ();

		for (int i = 0; i < m; i++) {
			int one = s.nextInt();
			int two = s.nextInt();
			int weight = s.nextInt();

			pq.add(new trip(one, two, weight));
		}

		counter = n;

		long fin = 0;

		arr = new int[n + 1];

		Arrays.fill(arr, -1);

		depth = new int[n + 1];

		Arrays.fill(depth, 0);

		while (!pq.isEmpty()) {
			trip e = pq.poll();

			if (find(e.end) != find(e.start)) {
				merge(e.start, e.end);
				fin += e.w;
			}
		}

        if (counter == 1) {
            System.out.println(fin);
        }

        else {
            System.out.println("IMPOSSIBLE");
        }

        s.close();
	}

	public static int find (int v) {
		if (arr[v] == -1) {
			return v;
		}

		arr[v] = find(arr[v]);

		return arr[v];
	}

	public static void merge(int u, int v) {
		int ro = find(u);
        
		int rt = find(v);

		if (ro == rt) {
			return;
		}

		if (depth[ro] < depth[rt]) {
			arr[ro] = rt;
			depth[rt] = depth[ro] + depth[rt];
			counter--;
		} 
        
        else if (depth[ro] > depth[rt]) {
			arr[rt] = ro;
			depth[ro] = depth[ro] + depth[rt];
			counter -= 1;
		}
	}
}

class trip implements Comparable<trip> {
	public int start, end, w;

	public trip(int s, int e, int w) {
		this.start = s;
		this.end = e;
		this.w = w;
	}
	public int compareTo(trip o) {
		if (this.w < o.w) {
			return -1;
		} 
        
        else if (this.w == o.w) {
			return 0;
		} 
        
        else {
			return 1;
		}
	}
}