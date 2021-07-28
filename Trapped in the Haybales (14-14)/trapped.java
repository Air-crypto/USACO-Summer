import java.util.*;
import java.io.*;

public class trapped {

	public static void main(String[] args) throws Exception {

		BufferedReader stdin = new BufferedReader(new FileReader("trapped.in"));
		StringTokenizer tok = new StringTokenizer(stdin.readLine());
		int n = Integer.parseInt(tok.nextToken());
		int bessiePos = Integer.parseInt(tok.nextToken());

		pair[] bales = new pair[n];
		for (int i=0; i<n; i++) {
			tok = new StringTokenizer(stdin.readLine());
			int size = Integer.parseInt(tok.nextToken());
			int pos = Integer.parseInt(tok.nextToken());
			bales[i] = new pair(size, pos);
		}

		FileWriter fout = new FileWriter(new File("trapped.out"));
		fout.write(solve(bales, bessiePos, n)+"\n");
		fout.close();
	}

	public static int solve(pair[] bales, int bessiePos, int n) {

		Arrays.sort(bales);

		int index = 0;
		while (index < n && bessiePos > bales[index].pos) index++;

		if (index == 0 || index == n) return -1;

		int low = index-1, high = index, res = Integer.MAX_VALUE;
		int tmpLeft = low-1, tmpRight = high+1;

		while (low >= 0 && high < n) {

			int maxD = bales[high].pos - bales[low].pos;
			if (bales[high].size >= maxD && bales[low].size >= maxD) return 0;

			else if (bales[high].size < maxD && bales[low].size >= maxD) {
				res = Math.min(res, maxD-bales[high].size);
				high++;
			}

			else if (bales[high].size >= maxD && bales[low].size < maxD) {
				res = Math.min(res, maxD-bales[low].size);
				low--;
			}

			else {

				int tmpStop = Integer.MAX_VALUE;
				while (tmpLeft >= 0 && bales[tmpLeft].size < (bales[high].pos-bales[tmpLeft].pos)) tmpLeft--;

				while (tmpRight < n && bales[tmpRight].size < (bales[tmpRight].pos-bales[low].pos)) tmpRight++;

				if (tmpLeft < 0 && tmpRight == n) {
					if (res == Integer.MAX_VALUE) return -1;
					return res;
				}

				if (tmpLeft >= 0)
					res = Math.min(res, bales[high].pos-bales[tmpLeft].pos-bales[high].size);

				if (tmpRight < n)
					res = Math.min(res, bales[tmpRight].pos-bales[low].pos-bales[low].size);

				low--;
				high++;
			}
		}

		if (res == Integer.MAX_VALUE) return -1;
		return res;
	}
}

class pair implements Comparable<pair> {

	public int size;
	public int pos;

	public pair(int s, int p) {
		size = s;
		pos = p;
	}

	public int compareTo(pair other) {
		return this.pos - other.pos;
	}

	public String toString() {
		return size+":"+pos;
	}
}