import java.util.*;

public class fsdraw {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        int n = s.nextInt();
        int m = s.nextInt();

        char [][] arr = new char [n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                arr[i][j] = '.';
            }
        }

        boolean side = true;
        
        for (int i = 0; i < n; i++) {
            if (i % 2 == 0) {    
                for (int j = 0; j < m; j++) {
                    arr[i][j] = '#';
                }
            }

            else {
                if (side) { arr[i][m - 1] = '#'; side = false; }
                else { arr[i][0] = '#'; side = true; }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                System.out.print(arr[i][j]);
            }

            System.out.println(); 
        }
    }
}
