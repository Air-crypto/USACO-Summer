import java.util.*;

public class cribbon {
    public static void main(String[] args) {
        Scanner s  = new Scanner(System.in);

        int n = s.nextInt();
        int a = s.nextInt();
        int b = s.nextInt();
        int c = s.nextInt();

        int [] dp = new int[n + 1];

        Arrays.fill(dp, Integer.MIN_VALUE);

        dp[0] = 0;

        for (int i = a; i <= n; i++) {
            dp[i] = Math.max(dp[i], dp[i - a] + 1);
        }

        for (int i = b; i <= n; i++) {
            dp[i] = Math.max(dp[i], dp[i - b] + 1);
        }

        for (int i = c; i <= n; i++) {
            dp[i] = Math.max(dp[i], dp[i - c] + 1);
        }

        System.out.println(dp[n]);
    }
}
