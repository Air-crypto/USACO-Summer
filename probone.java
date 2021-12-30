import java.util.*;
import java.io.*;

public class probone {
    private static int n;
    private static int t;
    private static int k;
    private static HashMap <Integer, Integer> used = new HashMap <Integer, Integer> ();
    private static HashMap <Integer, ArrayList<Pair>> map = new HashMap <Integer, ArrayList<Pair>>();
    private static ArrayList<Pair> listy = new ArrayList <Pair>();
    private static int parents[];
    public static void main(String[] args) {
        Scanner s = new Scanner(new InputStreamReader(System.in));
        
        t = s.nextInt();
        n = s.nextInt();
        k = s.nextInt();

        for (int i = 0; i < n; i++) {
            int x = s.nextInt();
            int w = s.nextInt();
            
            listy.add(new Pair(x, w));
        }

        System.out.println(listy.toString());

        
    }

    public static void createSubtree () {
        for (int i = 0; i < n; i++) {

        }
    }

    private static class Pair implements Comparable<Pair> {
        int x;
        int y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Pair o) {
            return y - o.y;
        }

        @Override
        public boolean equals(Object other) {
            Pair nother = (Pair) other;
            return nother.x == x;
        }

        @Override
        public String toString() {
            return x + " " + y;
        }
    }

    public static int findRoot (int a) {
        if (parents[a] == a) {
            return a;
        }

        return parents[a] = findRoot(parents[a]);
    }

    public static boolean isConnected (int a, int b) {
        return findRoot(a) == findRoot(b);
    }

    public static void join (int a, int b) {
        parents[findRoot(a)] = findRoot(b);
    }
}