import java.util.*;

public class berlandmusic {
    public static void main (String[]args) {
        Scanner s = new Scanner (System.in);

        int t = s.nextInt();

        for (int i = 0; i < t; i++) {
            int len = s.nextInt();

            int [] arr = new int [len];
            
            Hashtable <Integer, Integer> store = new Hashtable <Integer, Integer>();

            for (int j = 0; j < len; j++) {
                arr[j] = s.nextInt();
                store.put(arr[j], j);
            }

            String rec = s.next();

            ArrayList<Integer> ignore = new ArrayList<Integer>();

            ArrayList<Integer> positive = new ArrayList<Integer>();

            for (int j = 0; j < rec.length(); j++) {
                if (rec.charAt(j) == '0') {
                    ignore.add(arr[j]);
                }

                else {
                    positive.add(arr[j]);
                }
            }

            Collections.sort(ignore);

            Collections.sort(positive);

            int [] out = new int [len];

            int adder = 1;

            for (int j = 0; j < ignore.size(); j++) {
                out[store.get(ignore.get(j))] = adder;
                adder++;
            }

            adder = len;

            for (int j = positive.size() - 1; j >= 0; j--) {
                out[store.get(positive.get(j))] = adder;
                adder--;
            }

            for (int j = 0; j < len; j++) {
                System.out.print(out[j] + " ");
            }

            System.out.println("");
        }
    }
}