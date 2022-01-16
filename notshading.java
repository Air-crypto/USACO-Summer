import java.util.*;

public class notshading {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        int t = s.nextInt();

        for (int i = 0; i < t; i++) {
            int n = s.nextInt();
            int m = s.nextInt();
            int r = s.nextInt() - 1;
            int c = s.nextInt() - 1;

            char [][] arr = new char[n][m];

            boolean has = false;

            for (int j = 0; j < n; j++) {
                String cur = s.next();
                for (int k = 0; k < cur.length(); k++) {
                    arr[j][k] = cur.charAt(k);

                    if (cur.charAt(k) == 'B') {
                        has = true;
                    }
                }
            }

            if (has == false) {
                System.out.println(-1);
                continue;
            }

            if (arr[r][c] == 'B') {
                System.out.println(0);
                continue;
            }

            boolean found = false;

            for (int k = 0; k < n; k++) {
                if (arr[k][c] == 'B') {
                    found = true;
                }
            }

            for (int k = 0; k < m; k++) {
                if (arr[r][k] == 'B') {
                    found = true;
                }
            }

            if (found) {
                System.out.println(1);
                continue;
            }

            System.out.println(2);
        }
    }
}
