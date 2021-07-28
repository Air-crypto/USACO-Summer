import java.util.*;
import java.io.*;

public class rblock {
    private static HashMap <Integer, ArrayList<neighbors>> graph = new HashMap <Integer, ArrayList<neighbors>>();
	private static int dist[];
	private static int N;
    private static int previous[];

    private static class neighbors implements Comparable<neighbors> {
        public int node;
        public int weight;

        public neighbors(int a, int b) {
            this.node = a;
            this.weight = b;
        }

        @Override
        public String toString() {
            return node + ", " + weight + "#";
        }

        @Override
        public int compareTo(neighbors o) {
            return this.weight - o.weight;
        }

        @Override
        public boolean equals(Object other) {
            neighbors nother = (neighbors) other;
            return nother.node == this.node;
        }
    }

	public static int dijkstra(int src) {

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
                int alt = dist[u.node] + v.weight;

                if (alt < dist[v.node]) {
                    pq.remove(v);
                    dist[v.node] = alt;
                    pq.add(new neighbors(v.node, alt));
                    previous[v.node] = u.node;
                }
            }
        }
        
        //System.out.println(Arrays.toString(previous));
        return dist[N];
	}

	public static void main(String[] args) throws Exception {
		PrintWriter out = new PrintWriter(new File("rblock.out"));
        BufferedReader reader = new BufferedReader(new FileReader(new File("rblock.in")));
        StringTokenizer st = new StringTokenizer(reader.readLine());

		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		dist = new int[N + 1];

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(reader.readLine());

			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());

			if (graph.containsKey(a)) {
                graph.get(a).add(new neighbors(b, c));
            }

            if (graph.containsKey(b)) {
                graph.get(b).add(new neighbors(a, c));
            }

            if (!graph.containsKey(a)) {
                graph.put(a, new ArrayList<neighbors>());
                graph.get(a).add(new neighbors(b, c));
            }

            if (!graph.containsKey(b)) {
                graph.put(b, new ArrayList<neighbors>());
                graph.get(b).add(new neighbors(a, c));
            }
		}

        System.out.println(graph);

		int max = dijkstra(1);

        ArrayList <Integer> path = new ArrayList<Integer>();

        int pointer = N;

        do {
            path.add(pointer);
            pointer = previous[pointer];
        } while (pointer != 1);

        path.add(1);

        Collections.reverse(path);

        System.out.println(path);

        int diff = 0;

		for (int i = 1; i < path.size(); i++) {
            //System.out.println(path.get(i - 1) + " " + path.get(i));
            ArrayList<neighbors> tempy = graph.get(path.get(i));

            //System.out.println(tempy + " here");

            for (int j = 0; j < tempy.size(); j++) {
                if (tempy.get(j).node == path.get(i - 1)) {
                    tempy.set(j, new neighbors(tempy.get(j).node, 2 * tempy.get(j).weight));
                }
            }

            graph.put(path.get(i), tempy);

            tempy = graph.get(path.get(i - 1));

            for (int j = 0; j < tempy.size(); j++) {
                if (tempy.get(j).node == path.get(i)) {
                    tempy.set(j, new neighbors(tempy.get(j).node, 2 * tempy.get(j).weight));
                }
            }

            graph.put(path.get(i - 1), tempy);

            //System.out.println(tempy);

            System.out.println(graph);

            int maxy = dijkstra(1);

            System.out.println(maxy);

            if ((maxy - max) > diff) {
                diff = maxy - max;
            }

            tempy = graph.get(path.get(i));

            //System.out.println(tempy + " here");

            for (int j = 0; j < tempy.size(); j++) {
                if (tempy.get(j).node == path.get(i - 1)) {
                    tempy.set(j, new neighbors(tempy.get(j).node, tempy.get(j).weight / 2));
                }
            }

            //System.out.println(tempy);

            graph.put(path.get(i), tempy);
		}

        System.out.println(diff);
	}
}