import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class rent {
    private static Flights [] choices;
    private static int n;
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer s = new StringTokenizer(reader.readLine());

        int t = Integer.parseInt(s.nextToken());

        for (int i = 0; i < t; i++) {
            s = new StringTokenizer(reader.readLine());
            n = Integer.parseInt(s.nextToken());

            choices = new Flights [n + 1];

            choices[0] = new Flights(0, 0, 0);

            int sf;
            int ef;
            int cf;

            for (int j = 1; j <= n; j++) {
                s = new StringTokenizer(reader.readLine());

                sf = Integer.parseInt(s.nextToken());
                ef = Integer.parseInt(s.nextToken()) + sf;
                cf = Integer.parseInt(s.nextToken());

                choices[j] = new Flights(sf, ef, cf);
            }

            Arrays.sort(choices);

            System.out.println(Arrays.toString(choices));

            int [] dp = new int[n + 1];

            dp[0] = 0;

            for (int j = 1; j <= n; j++) {
                //System.out.println(Arrays.toString(dp) + " " + choices[j].cost + " " + dp[finder(j)] + " " + finder(j) + " " + j);
                dp[j] = Math.max(dp[j - 1], choices[j].cost + dp[finder(j)]);
            }

            System.out.println(Arrays.toString(dp));

            System.out.println(dp[n]);
        }
    }

    public static int finder (int x) {
        int a = 0, b = n;
        int mid = (a + b) / 2;

        while (a < b) {
            mid = (a + b) / 2;

            if (choices[mid].end <= choices[x].start && x != mid) {
                a = mid + 1;
            }

            else {
                b = mid;
            }
        }

        if (choices[mid].end <= choices[x].start) {
            return mid;
        }

        return mid - 1;
    }

    private static class Flights implements Comparable<Flights> {
        int start;
        int end;
        int cost;

        public Flights(int x, int y, int z) {
            this.start = x;
            this.end = y;
            this.cost = z;
        }

        @Override
        public int compareTo(Flights o) {
            return end - o.end;
        }

        @Override
        public boolean equals(Object other) {
            Flights nother = (Flights) other;
            return nother.end == end;
        }

        @Override
        public String toString() {
            return start + " " + end + " " + cost;
        }
    }
}