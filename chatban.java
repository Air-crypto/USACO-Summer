import java.util.*;

public class chatban {
    public static long x;
    public static long k;
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        long t = s.nextLong();

        for (long i = 0; i < t; i++) {
            k = s.nextLong();
            x = s.nextLong();

            long a = 1;
            long b = 2 * k - 1;
            long mid = 0;

            while (a < b) {
                mid = ((a + b) / 2);

                if (simulate(mid)) {
                    b = mid;
                    continue;
                }

                else {
                    a = mid + 1;
                }
            }

            System.out.println(b);
        }
    }

    public static boolean simulate(long y) {
        long count = 0;

        if (y >= k) {
            count = solver(k) + solver(k - 1) - solver(2 * k - 1 - y);
        }
        
        else {
            count = solver(y);
        }

        if (count >= x) {
            return true;
        }

        return false;
    }

    public static long solveplz(long y) {
        long count = solver(k) + solver(k - 1) - solver(2 * k - 1 - y);

        return count;
    }

    public static long solver(long ye) {
        return ((ye * ye) + ye) / 2;
    }
}
