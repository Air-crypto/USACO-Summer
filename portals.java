import java.util.*;
import java.io.*;

public class portals {
	public static int n;
	public static int[][] pts;
	public static HashSet[] xLinks;
	public static HashSet[] yLinks;

	public static void main(String[] args) throws Exception {

		BufferedReader stdin = new BufferedReader(new FileReader("lasers.in"));
		StringTokenizer tok = new StringTokenizer(stdin.readLine());
		n = Integer.parseInt(tok.nextToken());
		pts = new int[n+2][2];

		TreeSet<Integer> xVals = new TreeSet<Integer>();
		TreeSet<Integer> yVals = new TreeSet<Integer>();

		for (int i=0; i<2; i++) {
			pts[i][0] = Integer.parseInt(tok.nextToken());
			pts[i][1] = Integer.parseInt(tok.nextToken());
			xVals.add(pts[i][0]);
			yVals.add(pts[i][1]);
		}

		for (int i=2; i<n+2; i++) {
			tok = new StringTokenizer(stdin.readLine());
			pts[i][0] = Integer.parseInt(tok.nextToken());
			pts[i][1] = Integer.parseInt(tok.nextToken());
			xVals.add(pts[i][0]);
			yVals.add(pts[i][1]);
		}

		HashMap<Integer,Integer> xMap = makeMap(xVals);
		HashMap<Integer,Integer> yMap = makeMap(yVals);

		for (int i=0; i<n+2; i++) {
			pts[i][0] = xMap.get(pts[i][0]);
			pts[i][1] = yMap.get(pts[i][1]);
		}

		xLinks = new HashSet[xMap.size()];
		for (int i=0; i<xLinks.length; i++) xLinks[i] = new HashSet<Integer>();

		yLinks = new HashSet[yMap.size()];
		for (int i=0; i<yLinks.length; i++) yLinks[i] = new HashSet<Integer>();

		for (int i=0; i<n+2; i++) {
			xLinks[pts[i][0]].add(pts[i][1]);
			yLinks[pts[i][1]].add(pts[i][0]);
		}

		PrintWriter out = new PrintWriter(new FileWriter("lasers.out"));
		out.println(bfs(0,1)-1);
		out.close();
		stdin.close();
	}

	public static int bfs(int s, int e) {

		if (pts[s][0] == pts[e][0] || pts[s][1] == pts[e][1]) return 1;

		int[][] dist = new int[2][];
		dist[0] = new int[xLinks.length];
		dist[1] = new int[yLinks.length];
		Arrays.fill(dist[0], -1);
		Arrays.fill(dist[1], -1);

		dist[0][pts[s][0]] = 1;
		dist[1][pts[s][1]] = 1;

		LinkedList<Integer> q = new LinkedList<Integer>();

		q.offer(pts[s][0] << 1);
		q.offer((pts[s][1] << 1) + 1);

		while (q.size() > 0) {

			int cur = q.poll();
			int xy = cur&1;
			int val = cur >> 1;

			if (val == pts[e][xy]) return dist[xy][val];

			HashSet[] list = xy == 0 ? xLinks : yLinks;

			for (Integer item : (HashSet<Integer>)list[val]) {
				if (dist[1-xy][item] == -1) {
					dist[1-xy][item] = dist[xy][val] + 1;
					q.offer( (item<<1) + (1-xy));
				}
			}
		}

		return -1;
	}

	public static HashMap<Integer,Integer> makeMap(TreeSet<Integer> ts) {

		HashMap<Integer,Integer> map = new HashMap<Integer,Integer>();
		for (int i=0; ts.size()>0; i++)
			map.put(ts.pollFirst(), i);
		return map;
	}
}