import java.util.*;

public class gardenerandtree {
    public static HashMap<Integer, ArrayList<Integer>> tree = new HashMap<Integer, ArrayList<Integer>>();
    public static HashMap<Integer, Integer> out = new HashMap<Integer, Integer>();
    public static int [] sizes;
    public static ArrayList<Integer> nodes = new ArrayList<Integer>();
    public static HashMap<Integer, Integer> alive = new HashMap<Integer, Integer>();
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        int t = s.nextInt();

        for (int i = 0; i < t; i++) {
            int n = s.nextInt();
            int k = s.nextInt();

            tree.clear();
            alive.clear();
            nodes.clear();
            out.clear();

            sizes = new int[n + 1];

            for (int j = 0; j < n; j++) {
                tree.put(j + 1, new ArrayList<Integer>());
                alive.put(j + 1, 0);
            }

            for (int j = 0; j < n - 1; j++) {
                int u = s.nextInt();
                int v = s.nextInt();

                tree.get(u).add(v);
                tree.get(v).add(u);
            }

            for (int j = 0; j < n; j++) {
                sizes[j + 1] = tree.get(j + 1).size();
            }

            for (int j = 0; j < k; j++) {
                nodes.clear();

                //System.out.println(Arrays.toString(sizes));
                //System.out.println(alive);

                for (int l = 1; l <= n; l++) {
                    if (out.containsKey(l)) {
                        continue;
                    }

                    else if (sizes[l] == 1 || sizes[l] == 0) {
                        nodes.add(l);
                        continue;
                    }
                }

                //System.out.println(nodes);

                for (Integer cur : nodes) {
                    out.put(cur, 1);

                    if (tree.get(cur).size() == 1) {
                        int x = tree.get(cur).get(0);
                        sizes[x] -= 1;
                        sizes[cur] -= 1;
                        alive.remove(cur);
                        continue;
                    }

                    sizes[cur] -= 1;
                    alive.remove(cur);
                }

                //System.out.println(Arrays.toString(sizes));
                //System.out.println(alive);
            }

            System.out.println(alive.size());
        }
    }
}
