import java.util.*;

public class plusmult {
    private static int n;
    private static HashMap<Integer, Integer> seen = new HashMap<Integer, Integer>();
    private static int a;
    private static int b;
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        int t = s.nextInt();

        for (int i = 0; i < t; i++) {
            n = s.nextInt();
            a = s.nextInt();
            b = s.nextInt();

            seen.clear();

            fillero(1, 0);

            System.out.println(seen);

            if (seen.containsKey(n)) {
                System.out.println("Yes");
            }

            else {
                System.out.println("No");
            }
        }
    }

    public static void fillero(int x, int type) {
        //System.out.println("h");
        if (seen.containsKey(x)) {
            return;
        }

        seen.put(x, 0);

        if (type == 0 && x + b > n) {
            return;
        }

        if (type == 1 && x * a > n) {
            return;
        }

        fillero(x + b, 0);
        fillero(x * a, 1);
    }
}
