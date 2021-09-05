import java.io.*;
import java.util.*;

public class snowbootsgold {

	public static int n;
	public static step[] depth;

	public static void main(String[] args) throws Exception {

		BufferedReader stdin = new BufferedReader(new FileReader("snowboots.in"));
		StringTokenizer tok = new StringTokenizer(stdin.readLine());
		n = Integer.parseInt(tok.nextToken());
		int numQ = Integer.parseInt(tok.nextToken());

		long cur = 0;

		depth = new step[n-2];
		tok = new StringTokenizer(stdin.readLine());
		for (int i=0; i<n; i++) {
			int x = Integer.parseInt(tok.nextToken());
			if (i>0 && i<n-1) depth[i-1] = new step(i, x);
		}

		Arrays.sort(depth);

		TreeSet<interval> ts = new TreeSet<interval>();
		ts.add(new interval(0, n-1));
		ts.add(new interval(n-1, 0));

		TreeSet<space> spacets = new TreeSet<space>();
		spacets.add(new space(0, n-1));

		TreeMap<Integer,Integer> res = new TreeMap<Integer,Integer>();
		res.put(0, n-1);

		for (int i=0; i<n-2; i++) {

			interval tmp = new interval(depth[i].idx, 0);
			interval left = ts.lower(tmp);
			interval right = ts.higher(tmp);
			space item = spacets.lower(new space(left.idx+1, left.len));

			ts.remove(left);
			spacets.remove(item);

			ts.add(new interval(left.idx, depth[i].idx-left.idx));
			ts.add(new interval(depth[i].idx, right.idx-depth[i].idx));
			spacets.add(new space(left.idx, depth[i].idx-left.idx));
			spacets.add(new space(depth[i].idx, right.idx-depth[i].idx));

			space max = spacets.last();
			res.put(depth[i].deep, max.len);
		}

		StringBuilder sb = new StringBuilder();

		for (int i=0; i<numQ; i++) {

			// Get query.
			tok = new StringTokenizer(stdin.readLine());
			int deep = Integer.parseInt(tok.nextToken());
			int stepSize = Integer.parseInt(tok.nextToken());

			int mykey = res.lowerKey(deep+1);
			int need = res.get(mykey);

			if (stepSize >= need)
				sb.append("1\n");
			else
				sb.append("0\n");
		}

		PrintWriter out = new PrintWriter(new FileWriter("snowboots.out"));
		out.print(sb);
		out.close();
		stdin.close();
	}
}

class step implements Comparable<step> {

	public int idx;
	public int deep;

	public step(int myidx, int myd) {
		idx = myidx;
		deep = myd;
	}

	public int compareTo(step other) {
		if (this.deep != other.deep) {
			return this.deep - other.deep;
		}

		return this.idx - other.idx;
	}
}

class interval implements Comparable<interval> {

	public int idx;
	public int len;

	public interval(int myidx, int mylen) {
		idx = myidx;
		len = mylen;
	}

	public int compareTo(interval other) {
		return this.idx - other.idx;
	}
}

class space implements Comparable<space> {

	public int idx;
	public int len;

	public space(int myidx, int mylen) {
		idx = myidx;
		len = mylen;
	}

	public int compareTo(space other) {
		if (this.len != other.len)
			return this.len - other.len;
		return this.idx - other.idx;
	}
}