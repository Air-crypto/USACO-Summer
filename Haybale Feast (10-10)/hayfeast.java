import java.util.*;
import java.io.*;

public class hayfeast {
	public static int n;
	public static long min;
	public static long[] flavor;
	public static item[] spice;

	public static void main(String[] args) throws Exception {
		BufferedReader stdin = new BufferedReader(new FileReader("hayfeast.in"));
		StringTokenizer tok = new StringTokenizer(stdin.readLine());
		n = Integer.parseInt(tok.nextToken());
		min = Long.parseLong(tok.nextToken());
		flavor = new long[n];
		spice = new item[n];
		for (int i=0; i<n; i++) {
			tok = new StringTokenizer(stdin.readLine());
			flavor[i] = Integer.parseInt(tok.nextToken());
			spice[i] = new item(Integer.parseInt(tok.nextToken()), i);
		}

		int res = 1000000000;

		TreeSet<item> ts = new TreeSet<item>();
		int j = 0;
		long sum = 0;

		for (int i=0; i<n; i++) {

			while (j < n && sum < min) {
				sum += flavor[j];
				ts.add(spice[j]);
				j++;
			}

			if (j == n && sum < min) break;

			res = Math.min(res, ts.last().value);

			sum -= flavor[i];
			ts.remove(spice[i]);
		}

		PrintWriter out = new PrintWriter(new FileWriter("hayfeast.out"));
		out.println(res);
		out.close();
		stdin.close();
	}
}

class item implements Comparable<item> {

    public int value;
    public int ID;

    public item(int v, int i) {
    	value = v;
    	ID = i;
    }

    public int compareTo(item other) {
    	if (this.value != other.value) return this.value-other.value;
    	return this.ID-other.ID;
    }
}