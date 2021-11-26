import java.util.*;
import java.io.*;

public class circlecross {
	public static void main (String [] args) throws IOException {
		Scanner s = new Scanner(new File("circlecross.in"));
		PrintWriter out = new PrintWriter(new File("circlecross.out"));

		int n = s.nextInt();

		int [] arr = new int [2 * n];

		for (int i = 0; i < 2 * n; i++) {
			arr[i] = s.nextInt() - 1;
		}

		//System.out.println(Arrays.toString(arr));

		BIT bit = new BIT(n * 2);

		HashMap <Integer, Integer> activated = new HashMap <Integer, Integer>();

		long fin = 0;

		for (int i = 0; i < n * 2; i++) {
			if (!activated.containsKey(arr[i])) {
				bit.add(i, 1);
				activated.put(arr[i], i);
			} 
			else {
				fin += bit.sum(activated.get(arr[i]) + 1, i - 1);

				bit.add(activated.get(arr[i]), -1);

				activated.remove(arr[i]);
			}
		}

		//System.out.println(fin);
		out.println(fin);
		out.close();
	}

	private static class BIT {
        private int[] tree;
		private int n;
	
		public BIT (int n) {
			this.n = n;
			tree = new int[n * 2];
		}
	
		public int sum(int a, int b) {
			a += n;
			b += n;
			int sum = 0;
			while (a <= b) {
				if (a % 2 == 1) sum += tree[a++];
				if (b % 2 == 0) sum += tree[b--];
				a /= 2;
				b /= 2;
			}
			return sum;
		}
	
		public void add(int index, int amount) {
			index += n;
			tree[index] += amount;
			for (index /= 2; index >= 1; index /= 2) {
				tree[index] = tree[2 * index] + tree[2 * index + 1];
			}
		}
    }
}