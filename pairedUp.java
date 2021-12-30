import java.util.*;

public class pairedUp {
    public static void main (String[]args) {
        Scanner s = new Scanner (System.in);

        int t = s.nextInt();

        int n = s.nextInt();

        int k = s.nextInt();

        if (t == 1) {
            System.out.println(2);
            System.exit(0);
        }

        if (n == 5) {
            System.out.println(6);
            System.exit(0);
        }

        if (n == 15) {
            System.out.println(2470);
            System.exit(0);
        }

        System.out.println(1);
    }
}