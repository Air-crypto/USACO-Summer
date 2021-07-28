import java.util.*;
import java.io.*;

public class cardgame {
	public static void main(String[] args) throws Exception {
		BufferedReader stdin = new BufferedReader(new FileReader("cardgame.in"));
		StringTokenizer tok = new StringTokenizer(stdin.readLine());

		int n = Integer.parseInt(tok.nextToken());

		int[] elsieLeft = new int[n/2];
		int[] elsieRight = new int[n/2];

		for (int i=0; i<n/2; i++)
			elsieLeft[i] = Integer.parseInt(stdin.readLine().trim());

		for (int i=0; i<n/2; i++)
			elsieRight[i] = Integer.parseInt(stdin.readLine().trim());

		int[] elsie = new int[n];

		for (int i=0; i<n/2; i++) elsie[i] = elsieLeft[i];

		for (int i=n/2; i<n; i++) elsie[i] = elsieRight[i-n/2];

		Arrays.sort(elsieLeft);
		Arrays.sort(elsieRight);
		Arrays.sort(elsie);

		int[] bessie = new int[n];
		int iElsie = 0, iBessie = 0;

		for (int cur=1; cur<=2*n; cur++) {
			if (iBessie == n) break;

			if (iElsie < n && elsie[iElsie] == cur) iElsie++;

			else bessie[iBessie++] = cur;
		}

		Arrays.sort(bessie);

		int res = 0;
		iBessie = n/2;

		for (int i=0; i<n/2; i++) {
			while (iBessie<n && bessie[iBessie] < elsieLeft[i]) iBessie++;

			if (iBessie < n) {
				res++;
				iBessie++;
			}
		}

		iBessie = n/2-1;

		for (int i=n/2-1; i>=0; i--) {
			while (iBessie>=0 && bessie[iBessie] > elsieRight[i]) iBessie--;

			if (iBessie >= 0) {
				res++;
				iBessie--;
			}
		}

		PrintWriter out = new PrintWriter(new FileWriter("cardgame.out"));
		out.println(res);
		out.close();
		stdin.close();
	}
}