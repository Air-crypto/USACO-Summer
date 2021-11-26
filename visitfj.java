import java.util.*;
import java.io.*;

public class visitfj {
    public static int [][] arr;
    public static int[][] dist;
    public static int t;
    public static int n;
    public static void main(String[] args) throws IOException{
        Scanner s = new Scanner (System.in);

        n = s.nextInt();
        t = s.nextInt();

        dist = new int[n][n];
        arr = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                arr[i][j] = s.nextInt();
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(arr[i][j] + " ");
            }

            System.out.println();
        }
    }

    static class Pair implements Comparable <Pair> {
        int one;
        int two;

        public Pair (int one, int two) {
            this.one = one;
            this.two = two;
        }

        @Override
        public int compareTo(Pair o) {
            return this.one - o.one;
        }
    }

    static class trip {
        int a;
        int w;

        public trip (int x, int z) {
            this.a = x;
            this.w = z;
        }

        @Override
        public String toString () {
            return a + ", " + w + ":";
        }
    }
}