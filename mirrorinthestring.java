import java.util.*;

public class mirrorinthestring {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        int t = s.nextInt();

        for (int i = 0; i < t; i++) {
            int n = s.nextInt();
            String cur = s.next();

            char [] arr = new char[n];

            for (int j = 0; j < n; j++) {
                arr[j] = cur.charAt(j);
            }

            if (n == 1) {
                System.out.println(cur + cur);
                continue;
            }

            String pref = Character.toString(arr[0]);

            for (int j = 1; j < Math.min(27, n); j++) {
                String test = pref + arr[j];

                if ((test + reverse(test)).compareTo((pref + reverse(pref))) < 0) {
                    pref = test;
                    continue;
                }

                break;
            }

            System.out.println(pref + reverse(pref));
        }
    }

    public static String reverse (String str) {
        String smech = "";

        for (int i = str.length() - 1; i >= 0; i--) {
            smech += str.charAt(i);
        }

        return smech;
    }
}
