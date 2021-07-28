import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class vacations {
    private static int n;
    private static int [] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer s = new StringTokenizer(reader.readLine());

        int n = Integer.parseInt(s.nextToken());
        arr = new int[n];

        s = new StringTokenizer(reader.readLine());

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(s.nextToken());
        }

        //System.out.println(Arrays.toString(arr));

        int [][] dp = new int[n][3];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 3; j++) {
                dp[i][j] = Integer.MAX_VALUE;
            }
        }

        if (arr[0] == 1) {
            dp[0][1] = 0;
        }

        else if (arr[0] == 2) {
            dp[0][2] = 0;
        }

        else if (arr[0] == 3) {
            dp[0][1] = 0;
            dp[0][2] = 0;
        }

        else {
            dp[0][0] = 1;
        }

        //System.out.println(Arrays.deepToString(dp));

        for (int i = 1; i < n; i++) {
            dp[i][0] = 1 + Math.min(dp[i - 1][2], Math.min(dp[i - 1][0], dp[i - 1][1]));

            if (arr[i] == 3) {
                dp[i][1] = Math.min(dp[i - 1][0], dp[i - 1][2]);
                dp[i][2] = Math.min(dp[i - 1][0], dp[i - 1][1]);
                continue;
            }
            
            if (arr[i] == 1) {
                dp[i][1] = Math.min(dp[i - 1][0], dp[i - 1][2]);
                continue;
            }

            if (arr[i] == 2) {
                dp[i][2] = Math.min(dp[i - 1][0], dp[i - 1][1]);
                continue;
            }
        }

        //System.out.println(Arrays.deepToString(dp));
        System.out.println(Math.min(dp[n - 1][0], Math.min(dp[n - 1][2], dp[n - 1][1])));
    }
}