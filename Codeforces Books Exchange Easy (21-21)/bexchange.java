import java.util.*;

public class bexchange {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        int q = s.nextInt();

        for (int i = 0; i < q; i++) {
            int n = s.nextInt();

            int [] arr = new int [n];

            for (int j = 0; j < n; j++) {
                arr[j] = s.nextInt() - 1;
            }

            //System.out.println(Arrays.toString(arr));

            for (int j = 0; j < n; j++) {
                int check = j;

                int counter = 0;

                do {
                    counter++;
                    check = arr[check];
                } while (check != j);

                System.out.print(counter + " ");
            }

            System.out.println();
        }
    }
}
