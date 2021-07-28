import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class wifi {
    private static int n;
    private static int a;
    private static int b;
    private static int [] arr;
    public static void main(String[] args) throws IOException {
        PrintWriter out = new PrintWriter(new File("wifi.out"));
        BufferedReader reader = new BufferedReader(new FileReader(new File("wifi.in")));
        StringTokenizer s = new StringTokenizer(reader.readLine());

        n = Integer.parseInt(s.nextToken());
        a = Integer.parseInt(s.nextToken());
        b = Integer.parseInt(s.nextToken());
        arr = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            s = new StringTokenizer(reader.readLine());
            arr[i] = Integer.parseInt(s.nextToken());
        }

        Arrays.sort(arr);

        //System.out.println(Arrays.toString(arr));

        long [] dp = new long [n + 1];

        dp[0] = 0;

        for (int i = 1; i <= n; i++) {
            dp[i] = Long.MAX_VALUE;

            //System.out.println(Arrays.toString(dp));

            for (int j = 1; j <= i; j++) {
                dp[i] = Math.min(dp[i], dp[j - 1] + ((arr[i] - arr[j]) * b) + (2 * a));
            }
        }

        if (dp[n] % 2 != 0) {
            //System.out.println(dp[n] / 2.0);
            out.println(dp[n] / 2.0);
            out.close();
            System.exit(0);
        }

        //System.out.println(dp[n] / 2);
        out.println(dp[n] / 2);
        out.close();
    }
}