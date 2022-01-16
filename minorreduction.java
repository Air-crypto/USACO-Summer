import java.util.*;
import java.io.*;

public class minorreduction {
    public static void main(String[] args) throws IOException {
        //Scanner s = new Scanner(System.in);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(reader.readLine());

        int t = Integer.parseInt(token.nextToken());

        for (int i = 0; i < t; i++) {
            token = new StringTokenizer(reader.readLine());
            String x = token.nextToken();

            int largestDouble = 0;
            int pos = 0;

            for (int j = 0; j < x.length() - 1; j++) {
                int one = Character.getNumericValue(x.charAt(j));
                int two = Character.getNumericValue(x.charAt(j + 1));

                if (one + two > largestDouble) {
                    largestDouble = one + two;
                    pos = j;
                }
            }

            String fin = x.substring(0, pos) + Integer.toString(largestDouble) + x.substring(pos + 2, x.length());

            System.out.println(fin);
        }
    }
}
