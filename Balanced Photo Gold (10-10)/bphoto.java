import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class bphoto {
    private static int n;
    private static HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
    private static int counter = 0;
    private static int [] arr;
    private static int [] L;
    private static int [] R;
    public static void main(String[] args) throws IOException {
        PrintWriter out = new PrintWriter(new File("bphoto.out"));
        BufferedReader reader = new BufferedReader(new FileReader(new File("bphoto.in")));
        StringTokenizer s = new StringTokenizer(reader.readLine());

        n = Integer.parseInt(s.nextToken());

        arr = new int[n];
        L = new int [n];
        R = new int [n];
        
        int [] tarr = new int[n];
        int [] tar = new int[n];

        for (int i = 0; i < n; i++) {
            s = new StringTokenizer(reader.readLine());
            int x = Integer.parseInt(s.nextToken());

            tarr[i] = x;
            tar[i] = x;
        }

        Arrays.sort(tarr);

        for (int i = 0; i < n; i++) {
            map.put(tarr[i], i + 1);
        }

        for (int i = 0; i < n; i++) {
            arr[i] = map.get(tar[i]);
        }

        //System.out.println(Arrays.toString(arr));

        BIT bit1 = new BIT(n);

        for (int i = 0; i < n; i++) {
            L[i] = bit1.query(n) - bit1.query(arr[i]);
            
            bit1.update(arr[i], 1);
        }

        bit1.clearer();

        for (int i = n - 1; i >= 0; i--) {
            R[i] = bit1.query(n) - bit1.query(arr[i]);
            //R[i] = bit2.query(arr[i]) - bit2.query(n);

            bit1.update(arr[i], 1);
        }

        //System.out.println(Arrays.toString(L));
        //System.out.println(Arrays.toString(R));

        for (int i = 0; i < n; i++) {
            if (Math.min(L[i], R[i]) * 2 < Math.max(L[i], R[i])) {
                counter++;
            }
        }

        //System.out.println(counter);
        out.println(counter);
        
        out.close();
    }

    private static class BIT {
        private static int [] ft;

        public BIT (int z) {
            ft = new int[z + 1];
        }

        public static void clearer () {
            for (int i = 0; i < ft.length; i++) {
                ft[i] = 0;
            }
        }

        public static void update (int x, int v) {
            while (x <= n) {
                ft[x] += v;
                x += (x &- x);
            }
        }
    
        public static int query (int x) {
            if (x > 0) {
                return ft[x] + query(x - (x &- x));
            }
    
            return 0;
        }
    }
}