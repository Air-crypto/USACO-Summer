import java.util.*;

public class resort {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        int n = s.nextInt();

        int [] types = new int[n];
        int [] connect = new int[n];
        int [] done = new int[n];

        for (int i = 0; i < n; i++) {
            types[i] = s.nextInt();
        }

        for (int i = 0; i < n; i++) {
            connect[i] = s.nextInt() - 1;

            if (connect[i] != -1) {
                done[connect[i]] += 1;
            }
        }

        //System.out.println(Arrays.toString(types));
        //System.out.println(Arrays.toString(connect));

        ArrayList<Integer> list = new ArrayList<Integer>();

        for (int i = 0; i < n; i++) {
            if (types[i] == 1) {
                int cur = i;

                ArrayList <Integer> temp = new ArrayList<Integer>();

                while (connect[cur] != -1 && done[connect[cur]] <= 1) {
                    temp.add(cur);

                    cur = connect[cur];
                }

                temp.add(cur);

                if (temp.size() > list.size()) {
                    list = temp;
                }
            }
        }

        System.out.println(list.size());

        for (int i = list.size() - 1; i >= 0; i--) {
            System.out.print((list.get(i) + 1) + " ");
        }
    }   
}
