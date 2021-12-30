import java.util.*;
import java.io.*;

public class inversioncounting {
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

    public static long [] bit;
    public static int q;
    public static void main(String[] args) throws IOException{
        Kattio s = new Kattio();

        q = s.nextInt();

        for (int i = 0; i < q; i++) {
            int n = s.nextInt();
            bit = new long[1000000];

            int ans = 0;

            for (int j = 0; j < n; j++) {
                int cur = s.nextInt();

                cur += 1;

                ans += j - query(cur);

                add(cur, 1);
            }

            System.out.println(ans);
        }
    }

    public static long query(int i) {
		long sum = 0;
		while (i > 0) {
			sum += bit[i];
			i -= (i & -i); 
		}
		return sum;
	}

	public static void add(int i, int v) {
		while (i > 0 && i < bit.length) {
			bit[i] += v;
			i += (i & -i);
		}
	}
}
