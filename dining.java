import java.util.*;
import java.io.*;

public class dining {
	final public static int OFFSET = 1000000001;
	public static int n;
	public static ArrayList[] orig;
	public static int[] yummy;
	
	public static void main(String[] args) throws Exception {
		BufferedReader stdin = new BufferedReader(new FileReader("dining.in"));
        PrintWriter out = new PrintWriter(new FileWriter("dining.out"));
		StringTokenizer tok = new StringTokenizer(stdin.readLine());
		n = Integer.parseInt(tok.nextToken());
		orig = new ArrayList[n];
		for (int i=0; i<n; i++) orig[i] = new ArrayList<edge>();
		int numE = Integer.parseInt(tok.nextToken());
		int numTreats = Integer.parseInt(tok.nextToken());
		yummy = new int[n];
		
		for (int i=0; i<numE; i++) {
			tok = new StringTokenizer(stdin.readLine());
			int u = Integer.parseInt(tok.nextToken())-1;
			int v = Integer.parseInt(tok.nextToken())-1;
			int w = Integer.parseInt(tok.nextToken());
			orig[u].add(new edge(v,w));
			orig[v].add(new edge(u,w));
		}
		
		for (int i = 0; i < numTreats; i++) {
			tok = new StringTokenizer(stdin.readLine());
			int u = Integer.parseInt(tok.nextToken())-1;
			int val = Integer.parseInt(tok.nextToken());
			yummy[u] = val;
		}
		
		int[] shortest = dijkstra(orig, n-1);
		
		ArrayList[] treatG = makeTreatGraph();
		
		int[] treat = dijkstra(treatG, n-1);
		
		StringBuffer sb = new StringBuffer();
		for (int i=0; i<n-1; i++) {
			if (treat[n+i]-OFFSET <= treat[i])
				out.println(1);
			else
				out.println(0);
		}
		
		out.close();		
		stdin.close();
	}
	
	public static ArrayList[] makeTreatGraph() {
		
		ArrayList[] g = new ArrayList[2*n];
		for (int i=0; i<2*n; i++) g[i] = new ArrayList<edge>();
		
		for (int i=0; i<n; i++) {
			for (int j=0; j<2*n; j+=n) {
				for (edge e: (ArrayList<edge>)orig[i]) {
					g[i+j].add(new edge(e.to+j, e.w));
					g[e.to+j].add(new edge(i+j, e.w));
				}
			}
		}
		
		for (int i=0; i<n; i++) {
			if (yummy[i] > 0) {
				g[i].add(new edge(i+n,OFFSET-yummy[i]));
			}
		}
		
		return g;
	}
	
	public static int[] dijkstra(ArrayList[] g, int source) {
		int[] dist = new int[g.length];
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[source] = 0;
		boolean[] done = new boolean[dist.length];
		done[source] = true;

		PriorityQueue<edge> pq = new PriorityQueue<edge>();
		for (edge e: (ArrayList<edge>)g[source])
			pq.offer(e);
		
		while (pq.size() > 0) {
			edge cur = pq.poll();
			if (done[cur.to]) continue;
			
			dist[cur.to] = cur.w;
			done[cur.to] = true;
			
			for (edge e: (ArrayList<edge>)g[cur.to])
				if (!done[e.to] && cur.w+e.w < dist[e.to])
					pq.offer(new edge(e.to, cur.w+e.w));
		}
		
		return dist;
	}
}

class edge implements Comparable<edge> {
	
	public int to;
	public int w;
	
	public edge(int v, int weight) {
		to = v;
		w = weight;
	}
	
	public int compareTo(edge other) {
		return this.w - other.w;
	}
}