import java.util.*;

public class equidistantletters {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        int t = s.nextInt();

        for (int i = 0; i < t; i++) {
            String cur = s.next();

            HashMap<Character, Integer> map = new HashMap<Character, Integer>();
            HashMap<Character, Integer> map2 = new HashMap<Character, Integer>();
            ArrayList<Character> list = new ArrayList<Character>();
            ArrayList<Character> list2 = new ArrayList<Character>();

            for (int j = 0; j < cur.length(); j++) {
                if (map.containsKey(cur.charAt(j))) {
                    map2.put(cur.charAt(j), j);
                    list.add(cur.charAt(j));
                }

                else {
                    map.put(cur.charAt(j), j);
                    list2.add(cur.charAt(j));
                }
            }

            if (map2.size() <= 1) {
                System.out.println(cur);
                continue;
            }

            String fin = "";

            for (char j : list) {
                fin += j;
                fin += j;
            }

            for (char j : list2) {
                if (!map2.containsKey(j)) {
                    fin += j;
                }
            }

            System.out.println(fin);
        }
    }
}
