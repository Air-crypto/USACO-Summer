import java.util.*;

public class constructrect {
    public static HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        int t = s.nextInt();

        for (int i = 0; i < t; i++) {
            int [] arr = new int [3];

            for (int j = 0; j < 3; j++) {
                arr[j] = s.nextInt();
            }

            if (arr[0] == arr[2] + arr[1]) {
                System.out.println("YES");
                continue;
            }

            if (arr[1] == arr[0] + arr[2]) {
                System.out.println("YES");
                continue;
            }

            if (arr[2] == arr[1] + arr[0]) {
                System.out.println("YES");
                continue;
            }

            if (arr[0] == arr[2] && arr[1] % 2 == 0) {
                System.out.println("YES");
                continue;
            }

            if (arr[1] == arr[2] && arr[0] % 2 == 0) {
                System.out.println("YES");
                continue;
            }

            if (arr[0] == arr[1] && arr[2] % 2 == 0) {
                System.out.println("YES");
                continue;
            }

            System.out.println("NO");
        }
    }
}
