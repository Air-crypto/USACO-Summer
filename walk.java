import java.util.*;
import java.io.*;

class walk {
    public static void main(String[] args) throws IOException {
        Scanner s = new Scanner(new File("walk.in"));

        int n = s.nextInt();
        int k = s.nextInt();

        int ans = 2019201997 - (k - 1) * 84 - 48 * n;

        PrintWriter out = new PrintWriter(new File("walk.out"));
        out.println(ans);
        out.close();
    }
}