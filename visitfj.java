import java.util.*;
import java.io.*;

public class visitfj {
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

    public static int [] onedf = {0, 1, 2, 3, 2, 1, 0, -1, -2, -3, -2, -1, -1, 1, 0, 0};
    public static int [] twode = {3, 2, 1, 0, -1, -2, -3, -2, -1, 0, 1, 2, 0, 0, -1, 1};
    public static int [][] arr;
    public static int[][] dist;
    public static int t;
    public static int n;
    public static void main(String[] args) throws IOException{
        Kattio s = new Kattio("visitfj");

        n = s.nextInt();
        t = s.nextInt();

        dist = new int[n][n];
        arr = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                arr[i][j] = s.nextInt();
                dist[i][j] = Integer.MAX_VALUE;
            }
        }

        dist[0][0] = 0;

        PriorityQueue <Pair> q = new PriorityQueue <Pair>();

        q.add(new Pair(0, 0));

        int ans = Integer.MAX_VALUE;

        while (!q.isEmpty()) {
            int first = -1 * q.peek().one;
            int second = (int) (q.peek().two / n);
            int three = (int) (q.peek().two % n);
            q.poll();

            //System.out.println("here" + dist[second][three]);

            if (first != dist[second][three]) {
                continue;
            }

            //System.out.println("here1");

            int finder = Math.abs(n - 1 - three) + Math.abs(n - 1 - second);

            //System.out.println(finder);

            if (finder <= 2) {
                ans = Math.min(ans, finder * t + first);
            }

            int l = onedf.length;
            for (int i = 0; i < l; i++) {
                //System.out.println("here3");
                int nr = second + onedf[i];
                int nc = three + twode[i];

                if (nr < 0 || nr >= n || nc < 0 || nc >= n || dist[nr][nc] < first + arr[nr][nc] + 3 * t) {
                    //System.out.println("here4");
                    continue;
                }

                dist[nr][nc] = first + arr[nr][nc] + 3 * t;
                q.add(new Pair(-1 * dist[nr][nc], nr * n + nc));
            }
        }


        s.println(ans);
        s.close();
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