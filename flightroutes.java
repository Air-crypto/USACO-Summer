import java.util.*;
import java.io.*;

public class flightroutes {
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
    public static ArrayList <Long> [] finder;
    public static int [] dist;
    public static int [] permdist;
    public static ArrayList <Integer> fin = new ArrayList<Integer>();
    public static int m;
    public static int n;
    public static int k;

    public static void main(String[] args) throws IOException{
        Kattio s = new Kattio();

        n = s.nextInt();
        m = s.nextInt();
        k = s.nextInt();

        finder = new ArrayList [n + 1];

        for (int i = 1; i <= n; i++) {
            finder[i] = new ArrayList <Long> ();
        }
        
        for(int i = 1; i <= n; i++) {
		    for(int j = 0; j < k; j++) {
			    finder[i].add(Long.MAX_VALUE);
		    }
	    }

        finder[1].add((long) 0);

        Collections.sort(finder[1]);

		double best = 0;

        arr = new ArrayList [n + 1];
        permdist = new int [n + 1];

        for (int i = 1; i <= n; i++) {
            arr[i] = new ArrayList <trip> ();
        }

        for (int i = 0; i < m; i++) {
            int one = s.nextInt();
            int two = s.nextInt();
			int cost = s.nextInt();
            
            trip t = new trip(two, cost);

            arr[one].add(t);
        }

        //System.out.println(Arrays.toString(arr));

        dijikstra();

        //System.out.println(Arrays.deepToString(finder));

        for (int i = 0; i < k; i++) {
            System.out.print(finder[n].get(i) + " ");
        }
    }

    public static void dijikstra () {
        PriorityQueue <Pair> q = new PriorityQueue <Pair> ();

        q.add(new Pair((long) 0, 1));

        while (!q.isEmpty()) {
            int u = q.peek().two;
            Long d = q.peek().one;
            q.poll();

            if (finder[u].get(k - 1) < d) {
                continue;
            }

            for (trip t : arr[u]) {
                int v = t.a;
                int c = t.c;

                //System.out.println("here1");

                if (finder[v].get(k - 1) > c + d) {
                    //System.out.println("here2 " + c + " " + d);
                    finder[v].set(k - 1, (long) (c + d));
                    q.add(new Pair(finder[v].get(k - 1), v));
                    Collections.sort(finder[v]);
                    //System.out.println(q);
                }
            }
        }
    }

    static class Pair implements Comparable <Pair> {
        Long one;
        int two;

        public Pair (Long one, int two) {
            this.one = one;
            this.two = two;
        }

        @Override
        public int compareTo(Pair o) {
            return (int) (this.one - o.one);
        }

        @Override
        public String toString() {
            return "(" + one + ", " + two + ")";
        }
    }

    static class trip implements Comparable <trip> {
        int a;
        int c;

        public trip (int x, int z) {
            this.a = x;
			this.c = z;
        }

        @Override
        public String toString () {
            return a + ", " + c + ":";
        }

		@Override
		public int compareTo(trip o) {
			return this.c - o.c;
		}
    }
}
