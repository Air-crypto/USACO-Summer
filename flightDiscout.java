import java.util.*;
import java.io.*;

public class flightDiscout {
    public static ArrayList <trip> [] arr;
    public static int [] dist;
    public static int m;
    public static int n;
    public static int [] parents;
    public static void main(String[] args) throws IOException{
        Scanner s = new Scanner (System.in);

        n = s.nextInt();
        m = s.nextInt();

        dist = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            dist[i] = Integer.MAX_VALUE;
        }

        arr = new ArrayList [n + 1];

        for (int i = 1; i <= n; i++) {
            arr[i] = new ArrayList <trip> ();
        }

        for (int i = 0; i < m; i++) {
            int one = s.nextInt();
            int two = s.nextInt();
            int weight = s.nextInt();

            trip t = new trip(two, weight);

            arr[one].add(t);
        }

        //System.out.println(Arrays.toString(arr));

        dijikstra(1);
    }

    public static void dijikstra (int x) {
        boolean [] used = new boolean[n + 1];
        parents = new int [n + 1];

        dist[x] = 0;
        parents[x] = -1;

        PriorityQueue <Pair> q = new PriorityQueue <Pair> ();

        q.add(new Pair(dist[x], x));

        while (!q.isEmpty()) {
            int a = q.poll().two;

            if (used[a]) {
                continue;
            }

            used[a] = true;

            for (trip t : arr[a]) {
                int b = t.a;
                int w = t.w;

                if (dist[a] + w < dist[b]) {
                    dist[b] = dist[a] + w;
                    parents[b] = a;
                    q.add(new Pair(dist[b], b));
                }
            }
        }

        //System.out.println(Arrays.toString(parents));
        int cur = n;
        int prev = n;

        ArrayList <Integer> store = new ArrayList <Integer> ();

        while (true) {
            if (parents[cur] == -1) {
                break;
            }

            prev = cur;
            cur = parents[cur];
            //System.out.println(cur + " " + prev);

            ArrayList <trip> finder = arr[cur];

            for (trip t : finder) {
                if (t.a == prev) {
                    store.add(t.w);
                }
            }
        }

        Collections.sort(store);
        Collections.reverse(store);
        //System.out.println(store.get(0));

        store.set(0, store.get(0) / 2);

        int ans = 0;

        for (int i : store) {
            ans += i;
        }

        System.out.println(ans);
    }

    static class Pair implements Comparable <Pair> {
        int one;
        int two;

        public Pair (int one, int two) {
            this.one = one;
            this.two = two;
        }

        @Override
        public int compareTo(Pair o) {
            return this.one - o.one;
        }
    }

    static class trip {
        int a;
        int w;

        public trip (int x, int z) {
            this.a = x;
            this.w = z;
        }

        @Override
        public String toString () {
            return a + ", " + w + ":";
        }
    }
}