import java.util.*;

public class strangefunction {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        int t = s.nextInt();

        for (int i = 0; i < t; i++) {
            long n = s.nextLong();
            
            long sum = 0;

            for (int j = 1; j <= n; j++) {
                sum += finder(j);
            }

            System.out.println(sum % 1000000007);
        }
    }

    public static long finder (long j) {
        long counter = 2;

        while (true) {
            if (j % counter != 0) {
                break;
            }

            counter++;
        }

        return counter;
    }
}
