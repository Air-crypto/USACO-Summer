import java.util.*;

public class jobschedule {
    public static Pair [] arr;
    public static int m;
    public static int d;
    public static int n;
    public static int [] times;
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        n = s.nextInt();
        d = s.nextInt();
        m = s.nextInt();

        arr = new Pair [m];

        for (int i = 0; i < m; i++) {
            arr[i] = new Pair (s.nextInt(), i + 1);
        }

        Arrays.sort(arr);

        int a = 1, b = m;
        int mid = 1;
        int count = 0;
        
        while (a < b) {
            mid = (a + b) / 2;
            count++;

            if (simulate(mid)) {
                b = mid;
            }

            else {
                a = mid + 1;
            }
        }

        //System.out.println(count);

        //System.out.println(n);
        //System.exit(0);

        if (!simulate(mid)) {
            simulate(mid + 1);
            mid++;
            System.out.println(mid);
        }

        else {
            simulate(mid);
            System.out.println(mid);
        }

        

        int pos = 0;
        int dayf = 1;
        boolean done = false;

        for (int i = 0; i < n; i++) {
            //System.out.println(pos + mid + "h" + done);
            if (done) {
                System.out.println(0);
                continue;
            }

            if ((pos + mid) >= m) {
                done = true;
            }

            for (int j = pos; j < m; j++) {
                //System.out.println(j + " " + dayf + " " + times[j]);
                if (!(dayf == times[j])) {
                    dayf++;
                    pos = j;
                    break;
                }

                System.out.print(arr[j].index + " ");
            }

            System.out.print(0);

            System.out.println();
            
        }
        
    }

    public static boolean simulate (int x) {
        //return true;
        int counter = 1;
        int day = 1;

        times = new int [m];

        for (int i = 0; i < m; i++) {
            times[i] = day;

            counter++;

            if (counter > x) {
                counter = 1;
                day++;
            }
        }

        //System.out.println(Arrays.toString(times) + " " + x);

        for (int i = 0; i < m; i++) {
            if (times[i] - arr[i].sorter > d) {
                return false;
            }
        }

        return true;
        
    }

    private static class Pair implements Comparable<Pair> {
        int sorter;
        int index;

        public Pair(int location, int index) {
            this.sorter = location;
            this.index = index;
        }
        
        @Override
        public int compareTo(Pair o) {
            return sorter - o.sorter;
        }

        @Override
        public boolean equals (Object other) {
            Pair nother = (Pair) other;
            return nother.sorter == sorter;
        }
    }
}