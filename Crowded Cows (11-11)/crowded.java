import java.util.*;
import java.io.*;

public class crowded {

	public static void main(String[] args) throws Exception {

		BufferedReader stdin = new BufferedReader(new FileReader("crowded.in"));
		StringTokenizer tok = new StringTokenizer(stdin.readLine());
		int n = Integer.parseInt(tok.nextToken());
		int gap = Integer.parseInt(tok.nextToken());

		cow[] cows = new cow[n];
		for (int i=0; i<n; i++) {
			tok = new StringTokenizer(stdin.readLine());
			int loc = Integer.parseInt(tok.nextToken());
			int height = Integer.parseInt(tok.nextToken());
			cows[i] = new cow(loc, height, i);
		}

		Arrays.sort(cows);

		IntTree itree = new IntTree(0, n-1);
		for (int i=0; i<n; i++)
			itree.change(i,i,cows[i].height);

		int res = 0;

		int low = 0, high = 0;
		for (int i=0; i<n; i++) {

			while (low < n && cows[low].loc+gap < cows[i].loc) low++;
			while (high < n && cows[high].loc <= cows[i].loc + gap) high++;
			high--;

			int left = low <= i-1 ?   itree.query(low, i-1) : 0;
			int right = high >= i+1 ? itree.query(i+1, high): 0;

			if (left >= 2*cows[i].height && right >= 2*cows[i].height) res++;
		}

		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("crowded.out")));
		out.println(res);
		out.close();
        stdin.close();
	}
}

class cow implements Comparable<cow> {

	public int loc;
	public int height;
	public int ID;

	public cow(int myLoc, int myH, int i) {
		loc = myLoc;
		height = myH;
		ID = i;
	}

	public int compareTo(cow other) {
		if (this.loc != other.loc) return this.loc - other.loc;
		return this.ID - other.ID;
	}
}

class IntTree {

	public int low;
	public int high;

	public int delta;
	public int value;

	public IntTree left;
	public IntTree right;

	public IntTree(int myLow, int myHigh) {

		low = myLow;
		high = myHigh;
		delta = 0;
		value = 0;

		if (low == high) {
			left = null;
			right = null;
		}

		else {
			int mid = (low+high)/2;
			left = new IntTree(low, mid);
			right = new IntTree(mid+1, high);
		}
	}

	public void prop() {

		if (left != null) {
			left.delta += delta;	
			right.delta += delta;	
			delta = 0;
		}

		else {
			value += delta; 
			delta = 0;
		}
	}

	public void update() {
		if (left != null)
			value = Math.max(left.value+left.delta, right.value+right.delta);
	}

	public void change(int start, int end, int extra) {

		if (high < start || end < low) return;

		prop();

		if (start <= low && high <= end) {
			delta += extra;		
			update();
			return;
		}

		left.change(start, end, extra);
		right.change(start, end, extra);
		update();
	}

	public int query(int start, int end) {

		if (high < start || end < low) return 0; 

		if (start <= low && high <= end) {
			return value + delta;
		}

		prop();
		int leftSide = left.query(start, end);
		int rightSide = right.query(start, end);
		update();
		return Math.max(leftSide, rightSide);
	}
}