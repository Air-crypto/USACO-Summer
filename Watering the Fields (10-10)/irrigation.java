
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class irrigation {
    private static int n;
    private static int [][] xy;
    private static ArrayList<Pair> [] graph;
    public static void main(String[] args) throws IOException {
        PrintWriter out = new PrintWriter(new File("irrigation.out"));
        BufferedReader reader = new BufferedReader(new FileReader(new File("irrigation.in")));
        StringTokenizer s = new StringTokenizer(reader.readLine());

        n = Integer.parseInt(s.nextToken());
        int c = Integer.parseInt(s.nextToken());

        xy = new int [n][2];

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

                if (dist >= c) {
                    graph[i].add(new Pair(i, j, dist));
                    graph[j].add(new Pair(j, i, dist));
                }
            }
        }

        //System.out.println(Arrays.deepToString(graph));

        Random ran = new Random();

        out.println(prims(ran.nextInt(n)));
        
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
            pq.add(p);
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
                pq.add(p);
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