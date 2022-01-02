import java.util.*;

public class polandballandforest {
    public static int [] parent;
    public static int [] arr;
    public static HashMap<Integer, Integer> children = new HashMap<Integer, Integer>(); 

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        int n = s.nextInt();

        arr = new int [n + 1];
        parent = new int [n + 1];

        for (int i = 1; i <= n; i++) {
            arr[i] = s.nextInt();
            parent[i] = i;
        }

        for (int i = 1; i <= n; i++) {
            join(i, arr[i]);
        }

        System.out.println(Arrays.toString(parent));

        int counter = 0;

        for (int i = 1; i <= n; i++) {
            if (children.containsKey(parent[i])) {
                continue;
            }

            else {
                counter++;
                children.put(parent[i], 1);
            }
        }

        System.out.println(counter);
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
