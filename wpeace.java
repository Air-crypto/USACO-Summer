import java.util.*;

public class wpeace {
    private static HashMap<Integer, ArrayList<pair>> graph = new HashMap<Integer, ArrayList<pair>>();
    private static int [] arr;
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        int r = s.nextInt();
        int c = s.nextInt();

        arr = new int[r + 1];

        for (int i = 0; i <= r; i++) {
            arr[i] = i;
        }

        for (int i = 1; i <= r; i++) {
            graph.put(i, new ArrayList<pair>());
        }

        for (int i = 0; i < c; i++) {
            int a = s.nextInt();
            int b = s.nextInt();
            int skil = s.nextInt();

            merge(a, b, skil);
            merge(b, a, skil);
        }

        System.out.println(Arrays.toString(arr));

        int q = s.nextInt();

        int max = -1;

        for (int i = 0; i < q; i++) {
            int x = s.nextInt();
            int y = s.nextInt();
        }

        System.out.println(graph);

        if (max == -1) {
            System.out.println("MISSION IMPOSSIBLE");
        }

        else {
            System.out.println(max);
        }
    }

    private static class pair {
        int node;
        int skill;

        public pair(int node, int skill) {
            this.node = node;
            this.skill = skill;
        }

        @Override
        public String toString() {
            return node + ":" + skill;
        }
    }

    public static int findRoot (int a) {
        if (arr[a] == a) {
            return a;
        }

        return arr[a] = findRoot(arr[a]);
    }

    public static void merge (int a, int b, int skil) {
        int aroot = findRoot(a);
        int broot = findRoot(b);


    }
}
