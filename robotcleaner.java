import java.util.*;

public class robotcleaner {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        int t = s.nextInt();

        for (int i = 0; i < t; i++) {
            int n = s.nextInt();
            int m = s.nextInt();

            int rb = s.nextInt();
            int cb = s.nextInt();

            int rd = s.nextInt();
            int cd = s.nextInt();

            int counter = 0;

            int chager = 1;
            int chagec = 1;

            while (true) {
                boolean done = false;

                if (rb == rd || cb == cd) {
                    break;
                }

                if (rb == n && chager == 1) {
                    chager *= -1;
                    done = true;
                }

                if (rb == 1 && chager == -1) {
                    chager *= -1;
                    done = true;
                }

                if (cb == m && chagec == 1) {
                    chagec *= -1;
                    done = true;
                }

                if (cb == 1 && chagec == -1) {
                    chagec *= -1;
                    done = true;
                }

                if (done) {
                    continue;
                }

                rb += chager;
                cb += chagec;
                counter++;
            }

            System.out.println(counter);
        }
    }
}

