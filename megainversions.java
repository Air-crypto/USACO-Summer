
import java.util.*;
import java.io.*;

public class megainversions {
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

    public static int [] bitt;
    public static int [] bito;
    public static int n;
    public static void main(String[] args) throws IOException{
        Kattio s = new Kattio();

        n = s.nextInt();

        bitt = new int[n + 1];
        bito = new int[n + 1];

        int ans = 0;
        int temp = 0;

        for (int i = 1; i <= n; i++) {
            int cur = s.nextInt();

            add(cur, 1, bito);
            temp = query(cur + 1, bito);
            add(cur, temp, bitt);
            ans += query(cur + 1, bitt);
        }

        System.out.println(ans);
    }

    public static void add(int k, int x, int [] bit) {
        while(k > 0) {
            bit[k] += x;
            k -= k&-k;
        }
    }

	public static int query(int k, int [] bit) {
        int s = 0;

        while (k <= n) {
            s += bit[k];
            k += k &- k;
        }

        return s;
    }
}
