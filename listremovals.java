import java.util.*;

public class listremovals {
    public static long[] BIT; 
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        int n = s.nextInt();

        ArrayList<Integer> list = new ArrayList<Integer>();
        BIT = new long[n + 1];

        for (int i = 0; i < n; i++) {
            int cur = s.nextInt();
            list.add(cur);
            update(i + 1, cur);
        }

        System.out.println(Arrays.toString(BIT));

        String done = "";

        for (int i = 0; i < n; i++) {
            int cur = s.nextInt();

            System.out.println(sum(cur) - sum(cur - 1));
        }

        //System.out.println(done);
    }

	public static long sum(int i) {
		long sum = 0;
		while (i > 0) {
			sum += BIT[i];
			i -= (i & -i);
		}
		return sum;
	}
	public static void update(int i, int v) {
		while (i > 0 && i < BIT.length) {
			BIT[i] += v;
			i += (i & -i);
		}
	}
}
