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

        int [][] xy = new int[n][2];


        
        out.close();
    }

    public static int prims (int sV) {
        int m = n - 1;
        int counter = 0;
        int maxCost = 0;
        PriorityQueue <Pair> pq = new PriorityQueue<Pair>();

        boolean [] visited = new boolean[n];

        Arrays.fill(visited, false);

        for (Pair p : graph[sV]) {
            pq.offer(p);
        }

        visited[sV] = true;

        while (!pq.isEmpty() && counter < m) {
            Pair curP = pq.poll();

            if (visited[curP.x] && visited[curP.y]) {
                continue;
            }

            int newv = visited[curP.x] ? curP.y : curP.x;

            visited[newv] = true;
            maxCost += curP.w;

            for (Pair p : graph[newv]) {
                pq.offer(p);
            }

            counter++;
        }

        if (counter == m) {
            return maxCost;
        }

        return -1;
    }

    private static class Pair implements Comparable<Pair> {
        int x;
        int y;
        int w;

        public Pair(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.w = z;
        }

        @Override
        public int compareTo(Pair o) {
            return w - o.w;
        }

        @Override
        public String toString() {
            return x + " " + y + " " + w;
        }
    }
}