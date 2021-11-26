import java.util.*;
import java.io.*;

public class roundtriptwo {
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

    public static ArrayList <trip> [] arr;
    public static int [] dist;
    public static int m;
    public static int n;
    public static void main(String[] args) throws IOException{
        Kattio s = new Kattio();

        n = s.nextInt();
        m = s.nextInt();

        dist = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            dist[i] = Integer.MAX_VALUE;
        }

        arr = new ArrayList [n + 1];

        for (int i = 1; i <= n; i++) {
            arr[i] = new ArrayList <trip> ();
        }

        for (int i = 0; i < m; i++) {
            int one = s.nextInt();
            int two = s.nextInt();
            int weight = s.nextInt();

            trip t = new trip(two, weight);

            arr[one].add(t);
        }

        dijikstra(1);

        for (int i = 1; i <= n; i++) {
            System.out.print(dist[i] + " ");
        }
    }

    public static void dijikstra (int x) {
        boolean [] used = new boolean[n + 1];

        dist[x] = 0;

        PriorityQueue <Pair> q = new PriorityQueue <Pair> ();

        q.add(new Pair(dist[x], x));

        while (!q.isEmpty()) {
            int a = q.poll().two;

            if (used[a]) {
                continue;
            }

            used[a] = true;

            for (trip t : arr[a]) {
                int b = t.a;
                int w = t.w;

                if (dist[a] + w < dist[b]) {
                    dist[b] = dist[a] + w;
                    q.add(new Pair(dist[b], b));
                }
            }
        }
    }

    static class Pair implements Comparable <Pair> {
        int one;
        int two;

        public Pair (int one, int two) {
            this.one = one;
            this.two = two;
        }

        @Override
        public int compareTo(Pair o) {
            return this.one - o.one;
        }
    }

    static class trip {
        int a;
        int w;

        public trip (int x, int z) {
            this.a = x;
            this.w = z;
        }

        @Override
        public String toString () {
            return a + ", " + w + ":";
        }
    }
}
