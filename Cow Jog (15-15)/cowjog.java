import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class cowjog {
    public static void main(String[] args) throws IOException {
        PrintWriter out = new PrintWriter(new File("cowjog.out"));
        Scanner s = new Scanner(new File("cowjog.in"));

        int n = s.nextInt();
        int t = s.nextInt();

        long [] finish = new long[n];

        for (int i = 0; i < n; i++) {
            finish[i] = s.nextLong() + (t * s.nextLong());
        }

        System.out.println(Arrays.toString(finish));

        int done = 1; 
        long endPos = finish[n - 1];

        for (int i = n - 1; i >= 0; i--) {
            if (finish[i] < endPos) {
                endPos = finish[i];
                done++;
            }
        }

        System.out.println(done);
        out.println(done);

        out.close();
        s.close();
    }
}