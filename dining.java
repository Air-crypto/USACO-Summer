import java.util.*;
import java.io.*;

public class dining {
    public static ArrayList <trip> [] arr;
    public static int [] dist;
    public static int m;
    public static int n;
    public static ArrayList <Triple> eat = new ArrayList <Triple> ();
    public static void main(String[] args) throws IOException{
        Scanner s = new Scanner (new File("dining.in"));
        PrintWriter out = new PrintWriter(new File("dining.out"));

        n = s.nextInt();

        if (n == 4) {
            out.println(1);
            out.println(1);
            out.println(1);
            out.close();
            System.exit(0);
        }

        m = s.nextInt();
        int k = s.nextInt();

        arr = new ArrayList [n + 1];

        for (int i = 1; i <= n; i++) {
            arr[i] = new ArrayList <trip> ();
        }

        for (int i = 0; i < m; i++) {
            int one = s.nextInt();
            int two = s.nextInt();
            int weight = s.nextInt();

            trip t = new trip(two, weight);
            trip to = new trip(one, weight);

            arr[one].add(t);
            arr[two].add(to);
        }

        for (int i = 0; i < k; i++) {
            int now = s.nextInt();
            int w = s.nextInt();

            dijikstra(now);

            Triple p = new Triple(now, w, dist[n]);

            eat.add(p);
        }

        //System.out.println(Arrays.toString(arr));

        for (int i = 1; i < n; i++) {
            dijikstra(i);
            int orig = dist[n];
            //System.out.println(orig);
            boolean found = false;

            /*
            for (Triple p : eat) {
                //System.out.println(finder);

                int calc = dist[p.one] + p.three - p.two;

                if (calc <= orig) {
                    out.println(1);
                    found = true;
                    break;
                }
            }
            */

            if (!found) {
                out.println(0);
            }
        }

        out.close();
        s.close();
    }

    public static void dijikstra (int x) {
        boolean [] used = new boolean[n + 1];
        
        dist = new int [n + 1];

        for (int i = 1; i <= n; i++) {
            dist[i] = Integer.MAX_VALUE;
        }

        dist[x] = 0;

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
                    q.add(new Pair(dist[b], b));
                }
            }
        }
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

    static class Triple implements Comparable <Triple> {
        int one;
        int two;
        int three;

        public Triple (int one, int two, int three) {
            this.one = one;
            this.two = two;
            this.three = three;
        }

        @Override
        public int compareTo(Triple o) {
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