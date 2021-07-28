import java.util.*;

public class mincrease {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        int n = s.nextInt();

        int [] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = s.nextInt();
        }

        if (n == 1) {
            System.out.println(1);
            System.exit(0);
        }

        int cur = arr[0];
        int cur1 = 1;
        int max = 1;

        for (int i = 1; i < n; i++) {
            if (cur1 > max) {
                max = cur1;
            }

            if (arr[i] > cur) {
                cur1++;
                cur = arr[i];
                continue;
            }

            cur = arr[i]; 
            cur1 = 1; 
        }

        if (cur1 > max) {
            max = cur1;
        }

        System.out.println(max);
    }
}
