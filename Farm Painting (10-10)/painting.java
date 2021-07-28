import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class painting {
    public static void main(String[] args) throws IOException {
        PrintWriter out = new PrintWriter(new File("painting.out"));
        Scanner s = new Scanner(new File("painting.in"));

        int n = s.nextInt();

        int [][] farms = new int[n][4];
        Event values [] = new Event[2 * n];

        int answer = 0;

        for (int i = 0; i < n; i++) {
            farms[i][0] = s.nextInt();
            farms[i][1] = s.nextInt();
            farms[i][2] = s.nextInt();
            farms[i][3] = s.nextInt();

            values[2 * i] = new Event(farms[i][0], i);
            values[2 * i + 1] = new Event(farms[i][2], i);
        }

        Arrays.sort(values);

        TreeSet<Event> holder = new TreeSet<Event>();

        for (int i = 0; i < 2 * n; i++) {
            int nindex = values[i].index;

            if (farms[nindex][0] == values[i].location) {
                Event above = holder.higher(new Event(farms[nindex][1], 0));

                if (above == null) {
                    answer++;
                    holder.add(new Event(farms[nindex][3], nindex));
                    continue;
                }

                int nindex2 = above.index;
                
                if (farms[nindex2][1] > farms[nindex][3]) {
                    answer++;
                    holder.add(new Event(farms[nindex][3], nindex));
                }
            }

            else {
                Event current = new Event(farms[nindex][3], nindex);
                if (holder.contains(current)) {
                    holder.remove(current);
                }
            }
        }
        
        System.out.println(answer);
        out.println(answer);

        out.close();
        s.close();
    }

    private static class Event implements Comparable<Event> {
        int location;
        int index;

        public Event(int location, int index) {
            this.location = location;
            this.index = index;
        }
        
        @Override
        public int compareTo(Event o) {
            return location - o.location;
        }

        @Override
        public boolean equals (Object other) {
            Event nother = (Event) other;
            return nother.index == index;
        }
    }
}