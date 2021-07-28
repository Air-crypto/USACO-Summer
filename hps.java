import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class hps {
    private static int n;
    private static int k;
    public static void main(String[] args) throws IOException {
        PrintWriter out = new PrintWriter(new File("hps.out"));
        BufferedReader reader = new BufferedReader(new FileReader(new File("hps.in")));
        StringTokenizer s = new StringTokenizer(reader.readLine());

        n = Integer.parseInt(s.nextToken());
        k = Integer.parseInt(s.nextToken());

        int [] arr = new int[n];

        for (int i = 0; i < n; i++) {
            s = new StringTokenizer(reader.readLine());

            if (s.nextToken().equals("H")) {
                arr[i] = 0;
                continue;
            }

            if (s.nextToken().equals("P")) {
                arr[i] = 1;
                continue;
            }

            if (s.nextToken().equals("S")) {
                arr[i] = 2;
                continue;
            }
        }

        int [][][] dp = new int[n + 1][k + 1][3];

        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= k; j++) {
                for (int l = 0; l < 3; l++) {
                    if (i == 0) {
                        dp[i][j][l] = 0;
                        continue;
                    }

                    if (j == 0) {
                        if (arr[i - 1] == l) {
                            dp[i][j][l] = dp[i - 1][j][l] + 1;
                            continue;
                        }

                        dp[i][j][l] = dp[i - 1][j][l];
                    }

                    int tst = (l + 1) % 3;
                    int tstt = (l + 2) % 3;
                }
            }
        } 
        
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
}