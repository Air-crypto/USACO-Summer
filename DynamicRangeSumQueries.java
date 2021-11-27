import java.util.*;
import java.io.*;

public class dynamicrangesumqueries {
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

    public static int [] arr;
    public static long [] bit;
    public static int m;
    public static int n;
    public static void main(String[] args) throws IOException{
        Kattio s = new Kattio();

        n = s.nextInt();
        m = s.nextInt();

        arr = new int[n + 1];
        bit = new long[n + 1];

        for (int i = 1; i <= n; i++) {
            arr[i] = s.nextInt();
			add(i, arr[i]);
        }

        for (int i = 0; i < m; i++) {
            int type = s.nextInt();

            if (type == 1) {
                int k = s.nextInt();
                int u = s.nextInt();

                add(k, -arr[k]);
				arr[k] = u;
				add(k, u);
            }

            else {
                int a = s.nextInt();
				int b = s.nextInt();

				System.out.println(query(b) - query(a - 1));
            }
        }
    }

    public static void add(int j, long k) {
		for (; j <= n; j += j & -j) {
			bit[j] += k;
		}
	}

	public static long query(int j) {
		long res = 0;
		for (; j > 0; j -= j & -j) {
			res += bit[j];
		}
		return res;
	}
}
