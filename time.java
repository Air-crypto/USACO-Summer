import java.util.*;
import java.io.*;

public class time {
	public static void main(String[] args) throws Exception {
		Scanner stdin = new Scanner(new File("time.in"));
		int n = stdin.nextInt();
		int e = stdin.nextInt();
		int c = stdin.nextInt();
		int[] money = new int[n];
		for (int i=0; i<n; i++)
			money[i] = stdin.nextInt();
		
		ArrayList[] g = new ArrayList[n];

		for (int i=0; i<n; i++) g[i] = new ArrayList<Integer>();

		for (int i=0; i<e; i++) {
			int v1 = stdin.nextInt()-1;
			int v2 = stdin.nextInt()-1;
			g[v2].add(v1);
		}
		
		int[][] dp = new int[1001][n];

		for (int i=0; i<=1000; i++) Arrays.fill(dp[i], -1000000000);
        
		dp[0][0] = 0;
		
		for (int t=1; t<=1000; t++) {
			for (int v=0; v<n; v++) {
				for (int u: (ArrayList<Integer>)g[v]) {
					if (dp[t-1][u] < 0) continue;
					
					dp[t][v] = Math.max(dp[t][v], dp[t-1][u]+money[v]);
				}
			}
		}
		
		int res = 0;

        for (int i = 0; i <= 1000; i++) {
            res = Math.max(res, dp[i][0] - c * i * i);
        }

		PrintWriter out = new PrintWriter(new FileWriter("time.out"));
		out.println(res);
		out.close();		
		stdin.close();
	}
}