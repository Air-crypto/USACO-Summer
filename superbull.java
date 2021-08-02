import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class superbull {
    private static int n;
    private static int [] arr;
    public static void main(String[] args) throws IOException {
        PrintWriter out = new PrintWriter(new File("superbull.out"));
        BufferedReader reader = new BufferedReader(new FileReader(new File("superbull.in")));
        StringTokenizer s = new StringTokenizer(reader.readLine());

        n = Integer.parseInt(s.nextToken());

        arr = new int [n];

        for (int i = 0; i < n; i++) {
            s = new StringTokenizer(reader.readLine());

            arr[i] = Integer.parseInt(s.nextToken());
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

    private static class Pairy implements Comparable<Pairy> {
        int x;
        int y;
        int w;

        public Pairy(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.w = z;
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