import java.util.*;

public class team {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        int n = s.nextInt();

        boolean seen = false;

        int count = 0;

        for (int i = 0; i < n; i++) {
            seen = false;

            for (int j = 0; j < 3; j++) {
                int t = s.nextInt();

                if (t == 1 && seen) {
                    count++;

                    if (j == 1) {
                        int trash = s.nextInt();
                    }

                    //seen = false;
                    break;
                }

                if (t == 1) {
                    seen = true;
                    continue;
                }
            }
        }

        System.out.println(count);
    }
}
