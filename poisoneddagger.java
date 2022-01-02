import java.util.*;

public class poisoneddagger {
    public static long h;
    public static int [] arr;
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        int t = s.nextInt();

        for (int i = 0; i < t; i++) {

            int n = s.nextInt();
            h = s.nextLong();

            arr = new int[n];

            for (int j = 0; j < n; j++) {
                arr[j] = s.nextInt();
            }

            long a = 1;
            long b = h;
            long mid = 0;
            long lastMiddle = -1;
            long wmid = 0;

            while (a <= b) {
                mid = ((a + b) / 2);

                if (mid == lastMiddle) {
                    break;
                }

                lastMiddle = mid;

                //System.out.println(mid);

                if (simulate(mid)) {
                    b = mid;
                    wmid = mid;
                    continue;
                }

                else {
                    a = mid + 1;
                    continue;
                }
            }

            System.out.println(b);
        }
    }

    public static boolean simulate(long x) {
        long seconds = 0;

        for (int i = 0; i < arr.length; i++) {
            int y = arr[i];

            if (i + 1 < arr.length && y + x > arr[i + 1]) {
                seconds += arr[i + 1] - y;
            }

            else {
                seconds += x;
            }
        }

        if (seconds >= h) {
            return true;
        }

        return false;
    }
}
