import java.util.*;
import java.io.*;

import java.io.*;
import java.util.*;

public class shortcut {
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
	
	public static int n;
	public static ArrayList[] graph;
	public static int t;
	public static int[] arr;
	
	public static int[] dist;
	public static int[] prev;
	public static int[] parent;
	
	public static void main(String[] args) throws Exception {
		Kattio s = new Kattio("shortcut");
		n = s.nextInt();
		int m = s.nextInt();
		t = s.nextInt();
		
		arr = new int[n];

        for (int i = 0; i < n; i++) {
			arr[i] = s.nextInt();
        }

		graph = new ArrayList[n];

		for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<edge>();
        }
		
		for (int i = 0; i < m; i++) {
			int one = s.nextInt() - 1;
            int two = s.nextInt() - 1;
            int w = s.nextInt();
			graph[one].add(new edge(two, w));
			graph[two].add(new edge(one, w));
		}
		
		dijkstra(0);
		

		for (int i = n - 1; i >= 0; i--) {
			int current = parent[i];

			arr[prev[current]] += arr[current];
		}
		
		long done = 0;

		for (int i = 0; i < n; i++) {
			if (dist[i] > t) {
				done = Math.max(done, ((long) arr[i]) * (dist[i] - t));
            }
        }

		s.println(done);
        s.close();
	}
	
	public static void dijkstra (int x) {
		dist = new int[n];
		prev = new int[n];
		parent = new int[n];

		Arrays.fill(prev, -1);
		Arrays.fill(dist, 1000000000);

		dist[x] = 0;
		prev[x] = x;
		
		PriorityQueue<edge> pq = new PriorityQueue<edge>();

		pq.add(new edge(x, 0));

		boolean[] done = new boolean[n];

		int counter = 0;
		
		while (counter < n) {
			edge cur = pq.poll();
			
			if (done[cur.v]) {
                continue;
            }

			done[cur.v] = true;
			parent[counter++] = cur.v;
			
			for (edge next: (ArrayList<edge>) graph[cur.v]) {
				int newD = cur.w + next.w;
	
				if (newD < dist[next.v] || (newD == dist[next.v] && cur.v < prev[next.v])) {
					dist[next.v] = newD;
					prev[next.v] = cur.v;
					pq.add(new edge(next.v, newD));
				}
			}
		}
	}

    static class edge implements Comparable<edge> {
        public int v;
        public int w;
        
        public edge(int one, int two) {
            v = one;
            w = two;
        }
        
        @Override
        public int compareTo(edge other) {
            return this.w - other.w;
        }
    
        @Override
        public String toString() {
            return "(" + v + ", " + w + ")";
        }
    }
}