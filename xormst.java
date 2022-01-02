import java.util.*;

public class xormst {
    public static HashMap <Integer, ArrayList <Pair>> map = new HashMap <Integer, ArrayList <Pair>>();
    public static HashMap <String, Integer> seen = new HashMap <String, Integer>();
    public static int n;
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        n = s.nextInt();

        long [] arr = new long [n + 1];

        for (int i = 1; i <= n; i++) {
            arr[i] = s.nextLong();
        }

        for (int i = 1; i <= n - 1; i++) {
            for (int j = i + 1; j <= n; j++) {
                long a = arr[i];
                long b = arr[j];

                if (seen.containsKey(Long.toString(a) + " " + Long.toString(b))) {
                    continue;
                }

                seen.put(Long.toString(a) + " " + Long.toString(b), 1);

                if (!map.containsKey(i)) { map.put(i, new ArrayList<Pair>()); map.get(i).add(new Pair(i, 0)); }
                if (!map.containsKey(j)) { map.put(j, new ArrayList<Pair>()); map.get(j).add(new Pair(j, 0)); }

                long c = a ^ b;

                map.get(i).add(new Pair(j, c));
                map.get(j).add(new Pair(i, c));
            }
        }

        System.out.println(map);
        System.out.println(mstPrim());
    }

    public static long mstPrim() {
        long[] prev = new long[n + 1];
        long[] dist = new long[n + 1];

        Arrays.fill(dist, Integer.MAX_VALUE);

        dist[0] = 0;

        boolean[] visited = new boolean[n + 1];

        long res = 0;

        for (int i = 1; i <= n; i++) {
            int u = -1;

            for (int j = 1; j <= n; j++) {
                if (!visited[j] && (u == -1 || dist[u] > dist[j])) {
                    u = j;
                }
            }

            //System.out.println(u);

            res += dist[u];
            visited[u] = true;

            for (int j = 1; j <= n; j++) {
                System.out.println(map.get(u));
                if (!visited[j] && dist[j] > map.get(u).get(j - 1).w) {
                    dist[j] = map.get(u).get(j - 1).w;
                    prev[j] = u;
                }
            }
        }

        return res - Integer.MAX_VALUE;
      }
}

class Pair implements Comparable<Pair> {
    long base;
    long w;

    public Pair (long b, long c) {
        this.base = b;
        this.w = c;
    }

    @Override
    public String toString () {
        return "(" + base + ", " + w + ")";
    }

    @Override
    public int compareTo(Pair o) {
        return (int) (this.w - o.w);
    }

}
