import java.util.*;

public class notassigning {
    public static HashMap <Integer, ArrayList <Integer>> map = new HashMap <Integer, ArrayList <Integer>>();
    public static HashMap <Integer, Integer> seen = new HashMap <Integer, Integer>();
    public static HashMap <Integer, HashMap<Integer, Integer>> edges = new HashMap <Integer, HashMap<Integer, Integer>>();
    public static int [] a1;
    public static int [] a2;
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        int t = s.nextInt();

        for (int i = 0; i < t; i++) {
            int n = s.nextInt();

            a1 = new int [n - 1];
            a2 = new int [n - 1];

            for (int j = 1; j <= n; j++) {
                map.put(j, new ArrayList<Integer>());
            }

            for (int j = 0; j < n - 1; j++) {
                int e1 = s.nextInt();
                int e2 = s.nextInt();

                a1[j] = e1;
                a2[j] = e2;

                map.get(e1).add(e2);
                map.get(e2).add(e1);
            }

            boolean bad = false;

            for (int j = 1; j <= n; j++) {
                if (map.get(j).size() >= 3) {
                    bad = true;
                    break;
                }
            }

            if (bad) {
                System.out.println(-1);
                continue;
            }

            int start = 1;
            boolean depend = false;

            for (int j = 1; j <= n; j++) {
                if (map.get(j).size() == 1) {
                    start = j;
                    break;
                }
            }

            for (int j = 1; j <= n; j++) {
                edges.put(j, new HashMap<Integer, Integer>());
            }

            while (seen.size() <= n - 1) {
                seen.put(start, 1);
                ArrayList <Integer> cur = map.get(start);

                int child = -1;

                if (cur.size() == 1) {
                    child = cur.get(0);
                }
                else {
                    if (seen.containsKey(cur.get(0)) == false) {
                        child = cur.get(0); 
                    }
                    else {
                        child = cur.get(1);
                    }
                }

                if (depend == false) {
                    HashMap<Integer, Integer> temp = edges.get(start);
                    temp.put(child, 2);
                    edges.put(start, temp);

                    HashMap<Integer, Integer> tempTwo = edges.get(child);
                    tempTwo.put(start, 2);
                    edges.put(child, tempTwo);
                    depend = true;
                }
                else {
                    HashMap<Integer, Integer> temp = edges.get(start);
                    temp.put(child, 5);
                    edges.put(start, temp);

                    HashMap<Integer, Integer> tempTwo = edges.get(child);
                    tempTwo.put(start, 5);
                    edges.put(child, tempTwo);
                    depend = false;
                }

                start = child;
            }

            for (int j = 0; j < n - 1; j++) {
                int one = a1[j];
                int two = a2[j];

                System.out.println(edges.get(one).get(two) + " ");
            }
        }
    }
}
