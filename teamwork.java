import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class teamwork {
    private static int n;
    public static void main(String[] args) throws IOException {
        PrintWriter out = new PrintWriter(new File("teamwork.out"));
        BufferedReader reader = new BufferedReader(new FileReader(new File("teamwork.in")));
        StringTokenizer s = new StringTokenizer(reader.readLine());

        n = Integer.parseInt(s.nextToken());
        int k = Integer.parseInt(s.nextToken());
        
        int [] dp = new int[n];
        int [] arr = new int [n];

        for (int i = 0; i < n; i++) {
            s = new StringTokenizer(reader.readLine());
            arr[i] = Integer.parseInt(s.nextToken());
        }

        dp[0] = arr[0];

        for (int i = 1; i < n; i++) {
            int max = arr[i];

            for (int j = i; ; j--) {
                if (j < 0 || ((i + 1 - j) > k)) {
                    break;
                }

                max = Math.max(arr[j], max);

                if (j == 0) {
                    dp[i] = Math.max(dp[i], max * (i + 1 - j));
                }

                else {
                    dp[i] = Math.max(dp[i], dp[j - 1] + max * (i + 1 - j));
                }
            }
        }

        //System.out.println(Arrays.toString(arr));

        out.println(dp[n - 1]);
        
        out.close();
    }

    private static class Pair implements Comparable<Pair> {
        int start;
        int end;

        public Pair(int x, int y, int z) {
            this.start = x;
            this.end = y;
        }

        @Override
        public int compareTo(Pair o) {
            return end - o.end;
        }

        @Override
        public boolean equals(Object other) {
            Pair nother = (Pair) other;
            return nother.end == end;
        }

        @Override
        public String toString() {
            return start + " " + end;
        }
    }

    private static class Pairy implements Comparable<Pairy> {
        int x;
        int y;
        int w;

        public Pairy(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.w = z;
        }

        public int find(Pairy other) {
            if (other.x == this.x) {
                return 1;
            }

            return 0;
        }

        @Override
        public int compareTo(Pairy o) {
            return w - o.w;
        }

        @Override
        public String toString() {
            return x + " " + y + " " + w;
        }
    }
}