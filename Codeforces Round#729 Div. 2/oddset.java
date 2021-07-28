import java.util.*;

public class oddset {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        int t = s.nextInt();

        for (int i = 0; i < t; i++) {
            int n = s.nextInt();

            int even = 0;
            int odd = 0;

            for (int j = 0; j < 2 * n; j++) {
                if (s.nextInt() % 2 == 0) {
                    even++;
                }

                else {
                    odd++;
                }
            }

            if (even == odd) {
                System.out.println("Yes");
            }

            else {
                System.out.println("No");
            }
        }
    }
}
