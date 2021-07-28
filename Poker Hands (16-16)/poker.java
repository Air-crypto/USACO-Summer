import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class poker {
    public static void main(String[] args) throws IOException {
        PrintWriter out = new PrintWriter(new File("poker.out"));
        Scanner s = new Scanner(new File("poker.in"));

        int n = s.nextInt();

        int [] arr = new int [n + 2];

        for (int i = 1; i <= n; i++) {
            arr[i] = s.nextInt();
        }

        long counter = 0;

        for (int i = 1; i <= n + 1; i++) {
            counter += Math.abs(arr[i] - arr[i - 1]);
        }

        counter /= 2;

        //Arrays.sort(arr);

        System.out.println(counter);
        out.println(counter);

        out.close();
        s.close();
    }
}