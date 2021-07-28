import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class moocast {
    private static int n;
    private static ArrayList<Pair> [] graph;
    private static long maxC = 17;
    private static int maxD = 0;
    public static void main(String[] args) throws IOException {
        PrintWriter out = new PrintWriter(new File("moocast.out"));
        BufferedReader reader = new BufferedReader(new FileReader(new File("moocast.in")));
        StringTokenizer s = new StringTokenizer(reader.readLine());

        n = Integer.parseInt(s.nextToken());

        //System.out.println(n);

        int [][] xy = new int[n][2];

        for (int i = 0; i < n; i++) {
            s = new StringTokenizer(reader.readLine());

            xy[i][0] = Integer.parseInt(s.nextToken());
            xy[i][1] = Integer.parseInt(s.nextToken());
        }

        graph = new ArrayList [n];

        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<Pair>();
        }

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int dist = (xy[i][0] - xy[j][0]) * (xy[i][0] - xy[j][0]) + (xy[i][1] - xy[j][1]) * (xy[i][1] - xy[j][1]);

                graph[i].add(new Pair(i, j, dist));
                graph[j].add(new Pair(j, i, dist));

                if (dist > maxD) {
                    maxD = dist;
                }
            }
        }

        //System.out.println(Arrays.deepToString(graph));
        //System.out.println(prims(0));

        long a = 0;
        long b = maxD;

        maxC = (a + b) / 2;

        while (a < b) {
            maxC = (a + b) / 2;

            if (prims(0) != -1) {
                b = maxC;
                continue;
            }

            a = maxC + 1;
        }

        //System.out.println(maxC);

        if (prims(0) != -1) {
            out.println(maxC);
        }

        else {
            out.println(maxC + 1);
        }
        
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
            if (p.w <= maxC) {
                pq.offer(p);
            }
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
                if (p.w <= maxC) {
                    pq.offer(p);
                }
            }

            counter++;
        }

        if (counter == m) {
            return 1;
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