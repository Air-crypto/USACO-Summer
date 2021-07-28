import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class lineup {
    private static int n;
    private static int k;
    private static int [] arr;
    private static HashMap<Integer, Integer> holder = new HashMap<Integer, Integer>();
    private static int good;
    public static void main(String[] args) throws IOException {
        PrintWriter out = new PrintWriter(new File("lineup.out"));
        BufferedReader reader = new BufferedReader(new FileReader(new File("lineup.in")));
        StringTokenizer s = new StringTokenizer(reader.readLine());

        n = Integer.parseInt(s.nextToken());
        k = Integer.parseInt(s.nextToken());

        arr = new int[n];

        int ans = 0;

        for (int i = 0; i < n; i++) {
            s = new StringTokenizer(reader.readLine());
            arr[i] = Integer.parseInt(s.nextToken());
        }

        int lp = 0;

        for (int rp = 0; rp < n; rp++) {
            place(rp);

            while (good > k + 1) {
                take(lp);
                lp++;
            }

            if (ans < holder.get(arr[rp])) {
                ans = holder.get(arr[rp]);
            }
        }

        System.out.println(ans);
        out.println(ans);
        out.close();
    }

    public static void place(int x) {
        int val = arr[x];

        if (!holder.containsKey(val)) {
            holder.put(val, 0);
        }

        if (holder.get(val) == 0) {
            good++;
        }

        holder.replace(val, holder.get(val) + 1);
    }

    public static void take(int x) {
        int val = arr[x];

        holder.replace(val, holder.get(val) - 1);

        if (holder.get(val) == 0) {
            good--;
        }
    }
}