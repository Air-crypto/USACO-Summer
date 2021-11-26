import java.util.*;
import java.io.*;
 
public class shortestroutestwo {
    public static int m;
    public static int n;
    public static long [][] dist;
    public static void main(String[] args) throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer s = new StringTokenizer(reader.readLine());
 
        n = Integer.parseInt(s.nextToken());
        m = Integer.parseInt(s.nextToken());
        int k = Integer.parseInt(s.nextToken());
 
        dist = new long [n][n];
 
        /*
        for (int [] i : dist) {
            Arrays.fill (i, Integer.MAX_VALUE);
        }
        */
 
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j) {
                    dist[i][j] = (long)1e18;
                }
 
                else {
                    dist[i][j] = 0;
                }
            }
        }
 
        for (int i = 0; i < m; i++) {
            s = new StringTokenizer(reader.readLine());
            int one = Integer.parseInt(s.nextToken()) - 1;
            int two = Integer.parseInt(s.nextToken()) - 1;
            int weight = Integer.parseInt(s.nextToken());
 
            if (dist[one][two] > weight) {
                dist[one][two] = weight;
                dist[two][one] = weight;
            }
        }
 
        //System.out.println(Arrays.deepToString(dist));
 
        warshall();
 
        //System.out.println("");
 
        //System.out.println(Arrays.deepToString(dist));
 
        for (int i = 0; i < k; i++) {
            s = new StringTokenizer(reader.readLine());
            int one = Integer.parseInt(s.nextToken()) - 1;
            int two = Integer.parseInt(s.nextToken()) - 1;
 
            if (one == two) {
                dist[one][two] = 0;
            }
 
            if (dist[one][two] == (long)1e18) {
                dist[one][two] = -1;
            }
 
            System.out.println(dist[one][two]);
        }
    }
 
    public static void warshall () {        
        for (int k = 0; k < n; k++) {
			for (int i = 0; i < n; i++) {
				for (int j = i + 1; j < n; j++) {
					if (dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[j][i] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }
    }
}