import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class moocast {
    private static int n;
    public static void main(String[] args) throws IOException {
        PrintWriter out = new PrintWriter(new File("moocast.out"));
        BufferedReader reader = new BufferedReader(new FileReader(new File("moocast.in")));
        StringTokenizer s = new StringTokenizer(reader.readLine());

        n = Integer.parseInt(s.nextToken());
        
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