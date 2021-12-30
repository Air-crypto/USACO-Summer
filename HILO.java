import java.util.*;

public class HILO {
    public static void main (String[]args) {
        Scanner s = new Scanner (System.in);

        int n = s.nextInt();

        int [] arr = new int [n];

        for (int i = 0; i < n; i++) {
            arr[i] = s.nextInt();
        }  

        for (int i = 0; i < n; i++) {
            double cur = i + 0.5;
            String curS = "";
            int ans = 0;

            int high = n + 1;
            int low = 0;

            for (int j = 0; j < n; j++) {
                int curP = arr[j];

                if (curP < low || curP > high) {
                    continue;
                }

                if (curP > cur) {
                    curS = curS + "HI";
                    high = curP;
                }

                else {
                    int curL = curS.length();

                    if (curL > 0) {
                        if (curS.charAt(curL - 1) == 'I') {
                            ans++;
                        }
                    }

                    curS = curS + "LO";
                }
            }

            System.out.println(ans);
        }

        System.out.println(0);
        s.close();
    }
}