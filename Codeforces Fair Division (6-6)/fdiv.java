import java.util.*;

public class fdiv {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        int n = s.nextInt();

        for (int i = 0; i < n; i++) {
            int m = s.nextInt();

            int [] arr = new int[m];

            for (int j = 0; j < m; j++) {
                int x = s.nextInt();

                arr[j] = x;
            }

            Arrays.sort(arr);

            int onec = 0;
            int twoc = 0;

            for (int j = arr.length - 1; j >= 0; j--) {
                if (onec < twoc) {
                    onec += arr[j];
                    continue;
                }

                if (twoc < onec) {
                    twoc += arr[j];
                    continue;
                }

                if (onec == twoc) {
                    onec += arr[j];
                }
            }

            if (onec == twoc) {
                System.out.println("YES");
            }

            else {
                System.out.println("NO");
            }
        }
    }
}
