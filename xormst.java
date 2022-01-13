import java.util.*;

public class xormst {
    public static HashMap <Integer, ArrayList <Pair>> map = new HashMap <Integer, ArrayList <Pair>>();
    public static HashMap <String, Integer> seen = new HashMap <String, Integer>();
    public static int n;
    public static int [] parent;
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        n = s.nextInt();

        long [] arr = new long [n + 1];

        long [] parent = new long [n + 1];

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
        System.out.println(mst());
    }

    public static long mst() {
        return 0;
    }

    public static int findRoot (int a) {
        if (parent[a] == a) {
            return a;
        }

        return parent[a] = findRoot(parent[a]);
    }

    public static boolean isConnected (int a, int b) {
        return findRoot(a) == findRoot(b);
    }

    public static void join (int a, int b) {
        parent[findRoot(a)] = findRoot(b);
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
