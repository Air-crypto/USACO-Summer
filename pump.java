import java.util.*;
import java.io.*;

public class pump {
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader stdin = new BufferedReader(new FileReader("pump.in"));
		StringTokenizer tok = new StringTokenizer(stdin.readLine());
		int n = Integer.parseInt(tok.nextToken());
		int numE = Integer.parseInt(tok.nextToken());
		flowedge[] edges = new flowedge[numE];
		
		for (int i=0; i<numE; i++) {
			tok = new StringTokenizer(stdin.readLine());
			int u = Integer.parseInt(tok.nextToken())-1;
			int v = Integer.parseInt(tok.nextToken())-1;
			int cost = Integer.parseInt(tok.nextToken());
			int flow = Integer.parseInt(tok.nextToken());
			edges[i] = new flowedge(u,v,cost,flow);
		}
		
		Arrays.sort(edges);
		
		ArrayList[] g = new ArrayList[n];
		for (int i=0; i<n; i++) g[i] = new ArrayList<edge>();
		double res = -1;
		
		for (int i=0; i<numE; i++) {
			
			g[edges[i].u].add(new edge(edges[i].u, edges[i].v, edges[i].w));
			g[edges[i].v].add(new edge(edges[i].v, edges[i].u, edges[i].w));
			
			int[] dist = dijkstras(g, 0);
			
			if (dist[n-1] != -1) {
				double tmp = 1.0*edges[i].f/dist[n-1];
				res = Math.max(res, tmp);
			}
		}
		
		int intres = (int)(res*1000000+1e-9);
		
		PrintWriter out = new PrintWriter(new FileWriter("pump.out"));
		out.println(intres);
		out.close();		
		stdin.close();
	}
	
	public static int[] dijkstras(ArrayList[] g, int source) {
		
		int n = g.length;
		int[] dist = new int[n];
		Arrays.fill(dist, -1);
		dist[source] = 0;
		
		PriorityQueue<edge> pq = new PriorityQueue<edge>();
		for (edge e: (ArrayList<edge>)g[source]) pq.offer(e);
		
		while (pq.size() > 0) {
			
			edge cur = pq.poll();
			if (dist[cur.v] != -1) continue;
			
			dist[cur.v] = cur.w;
			
			for (edge e: (ArrayList<edge>)g[cur.v]) 
				if (dist[e.v] == -1) 
					pq.offer(new edge(e.u, e.v, cur.w+e.w));
		}
		
		return dist;
	}
}

class edge implements Comparable<edge> {

	public int u;
	public int v;
	public int w;
	
	public edge(int from, int to, int weight) {
		u = from;
		v = to;
		w = weight;
	}
	
	public int compareTo(edge other) {
		return w - other.w;
	}	
}

class flowedge implements Comparable<flowedge> {

	public int u;
	public int v;
	public int w;
	public int f;
	
	public flowedge(int from, int to, int weight, int flow) {
		u = from;
		v = to;
		w = weight;
		f = flow;
	}
	
	public int compareTo(flowedge other) {
		return other.f - f;
	}	
}
