import java.util.*;
import java.io.*;

public class pump {
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
        Kattio s = new Kattio("pump");

        n = s.nextInt();
        m = s.nextInt();

		double best = 0;

        arr = new ArrayList [n + 1];

        for (int i = 1; i <= n; i++) {
            arr[i] = new ArrayList <trip> ();
        }

        for (int i = 0; i < m; i++) {
            int one = s.nextInt();
            int two = s.nextInt();
			int cost = s.nextInt();
            int flow = s.nextInt();

            trip t = new trip(two, cost, flow, one);
			trip to = new trip(one, cost, flow, two);

            arr[one].add(t);
			arr[two].add(to);
        }

        int counter = 0;

		while (counter < m) {
			trip curf = dijikstra(1);

			int cost = dist[n];

			if (cost == Integer.MAX_VALUE) {
				break;
			}

			double rn = ((double) curf.f) / ((double) cost);

			if (rn > best) {
				best = rn;
			}

			ArrayList<trip> temp = new ArrayList <trip>();

			for (trip k : arr[curf.b]) {
				if (k.equals(curf)) {
					continue;
				}

				temp.add(k);
			}

			arr[curf.b] = temp;

			counter++;
		}

		s.println((int) (best * 1000000));
		s.close();
    }

    public static trip dijikstra (int x) {
        boolean [] used = new boolean[n + 1];

		dist = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            dist[i] = Integer.MAX_VALUE;
        }

		trip min = new trip(0, 0, Integer.MAX_VALUE, 0);

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
                int w = t.c;

                if (dist[a] + w < dist[b]) {
                    dist[b] = dist[a] + w;
                    q.add(new Pair(dist[b], b));

					if (t.f < min.f) {
						min = t;
					}
                }
            }
        }

		return min;
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

    static class trip implements Comparable <trip> {
        int a;
        int c;
		int f;
		int b;

        public trip (int x, int z, int a, int b) {
            this.a = x;
            this.f = a;
			this.c = z;
			this.b = b;
        }

        @Override
        public String toString () {
            return a + ", " + f + ", " + c + ", " + b + ":";
        }

		@Override
		public int compareTo(trip o) {
			if (this.a == o.a && this.f == o.f && this.c == o.c) {
				return 1;
			}

			return 0;
		}
    }
}
