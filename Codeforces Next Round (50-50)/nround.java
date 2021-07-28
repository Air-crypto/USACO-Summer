import java.util.*;

public class nround {
    private static HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        int n = s.nextInt();
        int m = s.nextInt();

        int [] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = s.nextInt();
        }

        //System.out.println(Arrays.toString(arr));

        int saw = 0;

        for (int i = 0; i < n; i++) {
            if (arr[i] == 0) {
                break;
            }

            if (map.containsKey(arr[i])) {
                saw++;
                continue;
            }

            if (saw >= m) {
                break;
            }

            map.put(arr[i], 1);
            saw++;
        }

        System.out.println(saw);
    }    
}
