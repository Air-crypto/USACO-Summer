import java.util.*;

public class integerdiversity {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        int t = s.nextInt();

        for (int i = 0; i < t; i++) {
            int n = s.nextInt();

            int [] arr = new int[n];

            for (int j = 0; j < n; j++) {
                arr[j] = s.nextInt();
            }

            HashMap <Integer, Integer> map = new HashMap<Integer, Integer>();

            for (int j = 0; j < n; j++) {
                if (map.containsKey(arr[j])) {
                    map.put(-arr[j], -1);
                }

                else if (!map.containsKey(arr[j])) {
                    map.put(arr[j], 1);
                }
            }

            System.out.println(map.size());
        }
    }
}
