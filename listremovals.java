import java.util.*;

public class listremovals {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        int n = s.nextInt();

        ArrayList<Integer> list = new ArrayList<Integer>();

        for (int i = 0; i < n; i++) {
            list.add(s.nextInt());
        }

        String done = "";

        for (int i = 0; i < n; i++) {
            int cur = s.nextInt() - 1;

            done += list.get(cur) + " ";
            list.remove(cur);
        }

        System.out.println(done);
    }

    public static long[] BIT; 

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
