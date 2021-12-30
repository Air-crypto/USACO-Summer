import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) {
		Scanner io = new Scanner(System.in);
		int n = io.nextInt();
		int q = io.nextInt();

		int[] arr = new int[n + 1];
		for (int i = 1; i <= n; i++) {
			arr[i] = io.nextInt();
		}

		SegmentTree seg = new SegmentTree(n + 1);
		for (int i = 1; i <= n; i++) {
			seg.add(i, arr[i] - arr[i - 1]);
		}
		for (int i = 0; i < q; i++) {
			int operation = io.nextInt();
			if (operation == 1) {
				int a = io.nextInt();
				int b = io.nextInt();
				int u = io.nextInt();
				seg.add(a, u);
				if (b < n) seg.add(b + 1, -u);
			} else {
				int k = io.nextInt();
				System.out.println(seg.sum(0, k));
			}
		}
		io.close();
	}

	static class SegmentTree {
		private long[] tree;
		private int n;

		public SegmentTree(int n) {
			this.n = n;
			tree = new long[n * 2];
		}

		public long sum(int a, int b) {
			a += n;
			b += n;
			long sum = 0;
			while (a <= b) {
				if (a % 2 == 1) sum += tree[a++];
				if (b % 2 == 0) sum += tree[b--];
				a /= 2;
				b /= 2;
			}
			return sum;
		}

		public void add(int index, long amount) {
			index += n;
			tree[index] += amount;
			for (index /= 2; index >= 1; index /= 2) {
				tree[index] = tree[2 * index] + tree[2 * index + 1];
			}
		}
	}
}