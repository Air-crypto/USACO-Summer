import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class ccski {
    private static int [][] seen;
    private static HashMap <String, Integer> ptsashash = new HashMap<String, Integer>();
    private static int [][] elevations;
    private static int [][] waypoints;
    private static int m;
    private static int n;
    private static ArrayList <Pair> way = new ArrayList<Pair>();
    private static int needed = 0;
    private static int found = 0;
    private static int firsti;
    private static int firstj;
    public static void main(String[] args) throws IOException {
        PrintWriter out = new PrintWriter(new File("ccski.out"));
        BufferedReader reader = new BufferedReader(new FileReader(new File("ccski.in")));
        StringTokenizer s = new StringTokenizer(reader.readLine());

        m = Integer.parseInt(s.nextToken());
        n = Integer.parseInt(s.nextToken());

        elevations = new int [m][n];
        seen = new int [m][n];

        int largest = 0;

        for (int i = 0; i < m; i++) {
            s = new StringTokenizer(reader.readLine());

            for (int j = 0; j < n; j++) {
                elevations[i][j] = Integer.parseInt(s.nextToken());
                if (elevations[i][j] > largest) {
                    largest = elevations[i][j];
                }
            }
        }

        waypoints = new int [m][n];

        for (int i = 0; i < m; i++) {
            s = new StringTokenizer(reader.readLine());

            for (int j = 0; j < n; j++) {
                int test = Integer.parseInt(s.nextToken());
                waypoints[i][j] = test;

                if (test == 1) {
                    //way.add(new Pair(i, j));
                    firsti = i;
                    firstj = j;
                    //ptsashash.put(i + " " + j, 1);
                    needed++;
                }
            }
        }
        
        //System.out.println(Arrays.deepToString(elevations));
        //System.out.println(Arrays.deepToString(waypoints));

        int a = 0, b = largest;
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

        if (simulate(mid) == false) {
            out.println(mid + 1);
        }

        else {
            out.println(mid);
        }
        //System.out.println(mid);

        out.close();
        reader.close();
    }

    public static boolean simulate (int mid) {
        found = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                seen[i][j] = 0;
            }
        }

        floodyfillutil(mid, firsti, firstj);

        //System.out.println(found);

        if (!(found == needed)) {
            return false;
        }

        return true;
    }

    public static void floodyfillutil (int x, int i, int j) {
        Queue<Pair> q = new ArrayDeque<>();
        q.add(new Pair(i, j));

        seen[i][j] = 1;

        while (!q.isEmpty()) {
            Pair node = q.poll();
 
            i = node.i;
            j = node.j;

            if (waypoints[i][j] == 1) {
                found++;
            }

            if ((i + 1 < m) && (seen[i + 1][j] == 0) && Math.abs(elevations[i][j] - elevations[i + 1][j]) <= x) { q.add(new Pair(i + 1, j)); seen[i + 1][j] = 1; }
            if ((i - 1 >= 0) && (seen[i - 1][j] == 0) && Math.abs(elevations[i][j] - elevations[i - 1][j]) <= x) { q.add(new Pair(i - 1, j)); seen[i - 1][j] = 1; }
            if ((j + 1 < n) && (seen[i][j + 1] == 0) && Math.abs(elevations[i][j] - elevations[i][j + 1]) <= x) { q.add(new Pair(i, j + 1)); seen[i][j + 1] = 1; }
            if ((j - 1 >= 0) && (seen[i][j - 1] == 0) && Math.abs(elevations[i][j] - elevations[i][j - 1]) <= x) { q.add(new Pair(i, j - 1)); seen[i][j - 1] = 1; }
        }
    }
}

class Pair {
    int i;
    int j;

    public Pair(int i, int j) {
        this.i = i;
        this.j = j;
    }
}