import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class tractor {
    private static int [][] seen;
    private static int half;
    private static int n;
    private static int [][] arr;
    public static void main(String[] args) throws IOException {
        PrintWriter out = new PrintWriter(new File("tractor.out"));
        //Scanner s = new Scanner(new File("tractor.in"));
        BufferedReader reader = new BufferedReader(new FileReader(new File("tractor.in")));
        StringTokenizer s = new StringTokenizer(reader.readLine());

        n = Integer.parseInt(s.nextToken());

        seen = new int [n][n];

        double halfP =  ((double) (n * n)) / 2.0;
        half = (n * n) / 2;

        if (halfP - half >= 0.5) {
            half++;
        }

        else {
            //just to complete block
        }

        arr = new int [n][n];

        int maxH = 0;

        //System.out.println(half);

        for (int i = 0; i < n; i++) {
            s = new StringTokenizer(reader.readLine());

            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(s.nextToken());

                if (arr[i][j] > maxH) {
                    maxH = arr[i][j];
                }
            }
        }

        //System.out.println(Arrays.deepToString(arr));
        //System.out.println(maxH);

        int a = 0, b = maxH;
        int mid = 0;

        while (a < b) {
            mid = (a + b) / 2;

            if (simulate(mid)) {
                b = mid;
            }

            else {
                a = mid + 1;
            }
        }

        //System.out.println(seen);

        //System.out.println(mid);
        
        if (simulate(mid) == false) {
            out.println(mid + 1);
        }

        else {
            out.println(mid);
        }

        out.close();
        reader.close();
    }

    public static boolean simulate (int mid) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                seen[i][j] = 0;
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (seen[i][j] == 1) {
                    continue;
                }

                int know = floodyfillutil(mid, i, j);

                //System.out.println(seen);
                //System.out.println(know);

                if (know * 2 >= n * n) {
                    return true;
                }
            }
        }

        return false;
    }

    public static int floodyfillutil (int x, int i, int j) {
        int total = 1;

        if (i >= n || j >= n) {
            return 0;
        }

        if (i < 0 || j < 0) {
            return 0;
        }

        if (seen[i][j] == 1) {
            return 0;
        }

        seen[i][j] = 1;

        if ((i + 1 < n) && Math.abs(arr[i][j] - arr[i + 1][j]) <= x) { total += floodyfillutil(x, i + 1, j); }
        if ((i - 1 >= 0) && Math.abs(arr[i][j] - arr[i - 1][j]) <= x) { total += floodyfillutil(x, i - 1, j); }
        if ((j + 1 < n) && Math.abs(arr[i][j] - arr[i][j + 1]) <= x) { total += floodyfillutil(x, i, j + 1); }
        if ((j - 1 >= 0) && Math.abs(arr[i][j] - arr[i][j - 1]) <= x) { total += floodyfillutil(x, i, j - 1); }
        
        return total;
    }
}