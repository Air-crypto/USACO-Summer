import java.util.*;

public class guessthenumber {
    private static HashMap <Integer, String> seen = new HashMap<Integer, String>();
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        int a = 1;
        int b = 1000000;

        int mid = 0;

        while (a <= b) {
            mid = (a + b) / 2;

            System.out.println(mid);

            System.out.flush();

            String updater = s.next();

            seen.put(mid, updater);

            if (seen.containsKey(mid + 1) && seen.get(mid + 1).equals("<") && seen.get(mid).equals(">=")) {
                System.out.println("! " + mid);
                System.exit(0);
            }

            if (seen.containsKey(mid - 1) && seen.get(mid - 1).equals(">=") && seen.get(mid).equals("<")) {
                System.out.println("! " + (mid - 1));
                System.exit(0);
            }

            if (updater.equals("<")) {
                b = mid; 
            }

            else {
                a = mid + 1;
            }
        }

        System.out.println("! " + mid);
        //System.out.println(seen);
    }
}
