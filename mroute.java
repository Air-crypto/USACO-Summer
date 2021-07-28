import java.util.*;
import java.io.*;

public class mroute {
    private static HashMap <Integer, ArrayList<neighbors>> graph = new HashMap <Integer, ArrayList<neighbors>>();
	private static double dist[];
	private static int N;
    private static int previous[];

    private static class neighbors implements Comparable<neighbors> {
        public int node;
        public double weight;

        public neighbors(int a, double b) {
            this.node = a;
            this.weight = b;
        }

        @Override
        public String toString() {
            return node + ", " + weight + "#";
        }

        @Override
        public int compareTo(neighbors o) {
            if (this.weight < o.weight) {
                return -1;
            }

            if (this.weight > o.weight) {
                return 1;
            }

            return 0;
        }

        @Override
        public boolean equals(Object other) {
            neighbors nother = (neighbors) other;
            return nother.node == this.node;
        }
    }

	public static double dijkstra(int src) {

        previous = new int[N + 1];

        PriorityQueue <neighbors> pq = new <neighbors> PriorityQueue();

        for (int i = 1; i <= N; i++) {
            if (i != src) {
                dist[i] = Integer.MAX_VALUE;
                previous[i] = Integer.MAX_VALUE;
                pq.add(new neighbors(i, dist[i]));
            } else {
                dist[src] = 0;
                previous[src] = 0;
                pq.add(new neighbors(src, dist[src]));
            }
        }

        while (!pq.isEmpty()) {
            neighbors u = pq.poll();

            for (neighbors v : graph.get(u.node)) {
                double alt = dist[u.node] + v.weight;

                if (alt < dist[v.node]) {
                    pq.remove(v);
                    dist[v.node] = alt;
                    pq.add(new neighbors(v.node, alt));
                    previous[v.node] = u.node;
                }
            }
        }
        
        return dist[N];
	}

	public static void main(String[] args) throws Exception {
		PrintWriter out = new PrintWriter(new File("mroute.out"));
        BufferedReader reader = new BufferedReader(new FileReader(new File("mroute.in")));
        StringTokenizer st = new StringTokenizer(reader.readLine());

		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
        double X = Integer.parseInt(st.nextToken());

		dist = new double[N + 1];

		for (int i = 0; i < M; i++) {
            st = new StringTokenizer(reader.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            double c = Integer.parseInt(st.nextToken());
            double d = Integer.parseInt(st.nextToken());

            if (graph.containsKey(a)) {
                graph.get(a).add(new neighbors(b, (c + X / d)));
            }

            if (graph.containsKey(b)) {
                graph.get(b).add(new neighbors(a, (c + X / d)));
            }

            if (!graph.containsKey(a)) {
                graph.put(a, new ArrayList<neighbors>());
                graph.get(a).add(new neighbors(b, (c + X / d)));
            }

            if (!graph.containsKey(b)) {
                graph.put(b, new ArrayList<neighbors>());
                graph.get(b).add(new neighbors(a, (c + X / d)));
            }
        }

        System.out.println(graph);
	}
}