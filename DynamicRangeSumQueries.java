import java.util.*;

public class DynamicRangeSumQueries {
    private static long arr [];
    private static int n;

    public static void main (String [] args) {
        Scanner s = new Scanner(System.in);

        n = s.nextInt();
        int q = s.nextInt();

        arr = new long [n + 1];
        int [] lift = new int [n + 1];

        for (int i = 1; i <= n; i++) {
            lift[i] = s.nextInt();
            add(i, lift[i]);
        }

        for (int i = 0; i < q; i++) {
            int next = s.nextInt();

            if (next == 1) {
                int a = s.nextInt();
				int b = s.nextInt();
				add(a, -lift[a]);
				lift[a] = b;
				add(a, lift[a]);
            }

            else {
                int a = s.nextInt();
                int b = s.nextInt();

                System.out.println(query(b) - query(a - 1));
            }
        }
    }

    static void add(int i, long k) {
		for (int j = i; j <= n; j += j & -j) {
			arr[j] += k;
		}
	}

	static long query(int i) {
		long res = 0;
		for (int j = i; j > 0; j -= j & -j) {
			res += arr[j];
		}
		return res;
	}
}
