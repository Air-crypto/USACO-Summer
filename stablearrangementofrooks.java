import java.util.*;

public class stablearrangementofrooks {
    public static HashMap <Integer, Integer> row = new HashMap <Integer, Integer> ();
    public static HashMap <Integer, Integer> column = new HashMap <Integer, Integer> ();
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        int t = s.nextInt();

        for (int i = 0; i < t; i++) {
            int n = s.nextInt();
            int k = s.nextInt();

            int [][] arr = new int [n][n];

            row.clear();
            column.clear();

            int counter = 0;

            for (int j = 0; j < n; j += 2) {
                for (int l = 0; l < n; l += 2) {
                    if (counter == k) {
                        break;
                    }

                    if (!row.containsKey(l) && !column.containsKey(j)) {
                        arr[j][l] = 1;
                        counter++;
                        row.put(l, counter);
                        column.put(j, counter);
                    }
                }
            }

            if (counter < k) {
                System.out.println(-1);
                continue;
            }

            for (int j = 0; j < n; j++) {
                for (int l = 0; l < n; l++) {
                    if (arr[j][l] == 1) {
                        System.out.print('R');
                    }
                    
                    else {
                        System.out.print('.');
                    }
                }

                System.out.println();
            }

            //System.out.println(i);
        }
    }
}
