import java.util.*;
import java.io.*;

public class investigation {
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
    public static HashMap <String, Integer> map = new HashMap<String, Integer>();
    public static int [] dist;
    public static int [] path;
    public static int m;
    public static int n;
    public static void main(String[] args) throws IOException{
        Kattio s = new Kattio();

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

            trip t = new trip(two, cost, one);

            arr[one].add(t);
        }
		
        int flights = 0;
        int minprice = Integer.MAX_VALUE;
        int minflights = Integer.MAX_VALUE;
        int maxflights = 0;

        trip pt = dijikstra(1);
        ArrayList<trip> temp = new ArrayList<trip> ();

        minprice = dist[n];
        flights++;

        /*
        for (trip k : arr[pt.b]) {
            if (!k.equals(pt)) {
                temp.add(k);
            }
        }
        */

        //arr[pt.b] = temp;

        int counter = 0;
        int place = n;
        String gone = "";

        while (true) {
            if (1 == path[place]) {
                gone += Integer.toString(path[place]);
                counter++;
                break;
            }

            counter++;
            place = path[place];
            gone += Integer.toString(place);
        }

        map.put(gone, 1);

        flights++;

        if (counter < minflights) {
            minflights = counter;
        }

        if (counter > maxflights) {
            maxflights = counter;
        }

        System.out.println(Arrays.toString(path));
        
        while (true) {
            pt = dijikstra(1);

            if (dist[n] == Integer.MAX_VALUE || dist[n] != minprice) {
                break;
            }

            System.out.println(Arrays.toString(path));

            temp = new ArrayList<trip> ();
            gone = "";

            /*
            for (trip k : arr[pt.b]) {
                if (!k.equals(pt)) {
                    temp.add(k);
                }
            }
            */
    
            //arr[pt.b] = temp;
    
            counter = 0;
            place = n;
    
            while (true) {
                if (1 == path[place]) {
                    counter++;
                    gone += Integer.toString(path[place]);
                    break;
                }
    
                counter++;
                place = path[place];
                gone += Integer.toString(place);
            }

            if (map.containsKey(gone)) {
                continue;
            }

            flights++;
    
            if (counter < minflights) {
                minflights = counter;
            }
    
            if (counter > maxflights) {
                maxflights = counter;
            }
        }
        

        System.out.println(minprice + " " + flights + " " + minflights + " " + maxflights);
    }

    public static trip dijikstra (int x) {
        boolean [] used = new boolean[n + 1];

        trip min = new trip(0, Integer.MAX_VALUE, 0);

		dist = new int[n + 1];
        path = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            dist[i] = Integer.MAX_VALUE;
        }

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
                    path[b] = a;
                    q.add(new Pair(dist[b], b));

                    if (t.c < min.c) {
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
        int b;

        public trip (int x, int z, int y) {
            this.a = x;
			this.c = z;
            this.b = y;
        }

        @Override
        public String toString () {
            return a + ", " + c + ", " + b + ":";
        }

		@Override
		public int compareTo(trip o) {
			if (this.a == o.a && this.c == o.c && this.b == o.b) {
				return 1;
			}

			return 0;
		}
    }
}
