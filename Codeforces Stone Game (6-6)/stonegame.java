import java.util.*;

public class stonegame {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        int n = s.nextInt();

        for (int i = 0; i < n; i++) {
            int m = s.nextInt();

            int [] arr = new int[m];

            int min = 1;
            int max = m;

            for (int j = 0; j < m; j++) {
                arr[j] = s.nextInt();
            }

            int pos1 = 0;
            int pos2 = m - 1;

            int count1 = 0;
            int count2 = 0;
            int count3 = 0;
            int count4 = 0;

            boolean done = false;

            //only left
            while (pos1 < m) {
                if (arr[pos1] == max || arr[pos1] == min) {
                    if (done == true) {
                        count1++;
                        pos1++;
                        break;
                    }

                    done = true;
                    count1++;
                    pos1++;
                    continue;
                }

                pos1++;
                count1++;
            }

            done = false;

            //only right
            while (pos2 >= 0) {
                if (arr[pos2] == max || arr[pos2] == min) {
                    if (done == true) {
                        count2++;
                        pos2--;
                        break;
                    }

                    done = true;
                    count2++;
                    pos2--;
                    continue;
                }

                pos2--;
                count2++;
            }

            //System.out.println(count1 + " " + count2);

            //left until min then right to max
            pos2 = m - 1;
            pos1 = 0;

            //System.out.println(arr[pos1] + " " + min);

            while (pos1 < m) {
                if (arr[pos1] == min) {
                    done = true;
                    count3++;
                    pos1++;
                    break;
                }

                pos1++;
                count3++;
            }

            //System.out.println(count3);

            while (pos2 >= 0) {
                if (arr[pos2] == max) {
                    done = true;
                    count3++;
                    pos2--;
                    break;
                }

                pos2--;
                count3++;
            }

            //System.out.println(count3);
            
            //left until max then right to min

            pos2 = m - 1;
            pos1 = 0;

            //System.out.println(arr[pos1] + " " + min);

            while (pos1 < m) {
                if (arr[pos1] == max) {
                    done = true;
                    count4++;
                    pos1++;
                    break;
                }

                pos1++;
                count4++;
            }

            //System.out.println(count3);

            while (pos2 >= 0) {
                if (arr[pos2] == min) {
                    done = true;
                    count4++;
                    pos2--;
                    break;
                }

                pos2--;
                count4++;
            }

            //System.out.println(count4);

            System.out.println(Math.min(Math.min(count1, count2), Math.min(count3, count4)));
        }
    }
}

