import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class slowdown {
    public static void main(String[] args) throws IOException {
        PrintWriter out = new PrintWriter(new File("slowdown.out"));
        Scanner s = new Scanner(new File("slowdown.in"));

        int n = s.nextInt();

        ArrayList<Integer> time = new ArrayList<Integer>();
        ArrayList<Integer> distance = new ArrayList<Integer>();

        for (int i = 0; i < n; i++) {
            String type = s.next();
            int place = s.nextInt();

            if (type.equals("D")) {
                distance.add(place);
            }

            else {
                time.add(place);
            }
        }

        int [] ndist = new int[distance.size()];
        int [] ntime = new int[time.size()];

        for (int i = 0; i < distance.size(); i++) {
            ndist[i] = distance.get(i);
        }

        for (int i = 0; i < time.size(); i++) {
            ntime[i] = time.get(i);
        }

        Arrays.sort(ntime);
        Arrays.sort(ndist);

        int tIdx = 0;
        int dIdx = 0;

        double speed = 1; //d = s*t formula variables
        double curdist = 0; //depending on D or T array pull
        double seconds = 0; //t = d/s or d = t/s can be used
        double counter = 2;

        if (ntime.length == 0) {
            seconds = ndist[0];
            curdist = seconds;
        }

        else if (ndist.length == 0) {
            seconds = ntime[0];
            curdist = seconds;
        }

        else {
            seconds = Math.min(ntime[0], ndist[0]);
            curdist = seconds;
        }

        for (int i = 0; i < ntime.length + ndist.length; i++) {
            System.out.println(seconds + " " + curdist + " " + speed);

            if (tIdx < ntime.length && ntime[tIdx] == seconds) {
                //alter speed
                while (tIdx < ntime.length && ntime[tIdx] == seconds) {
                    speed = 1.0 / counter;
                    counter++;
                    tIdx++;
                }
            }

            if (dIdx < ndist.length && ndist[dIdx] == curdist) {
                //alter speed
                while (dIdx < ndist.length && ndist[dIdx] == curdist) {
                    speed = 1.0 / counter;
                    counter++;
                    dIdx++;
                }
            }

            //calculate next point
            if (tIdx < ntime.length && dIdx < ndist.length) {
                double temp = ndist[dIdx] - curdist;

                double ttime = ntime[tIdx] - seconds;
                double dtime = temp / speed;

                if (ttime <= dtime) {
                    seconds += ttime;
                    //tIdx++;
                    curdist += ttime * speed;
                }

                else {
                    seconds += dtime;
                    //dIdx++;
                    curdist += temp;
                }
            }

            else if (tIdx < ntime.length) {
                double ttime = ntime[tIdx] - seconds;
                seconds += ttime;
                curdist += ttime * speed;
            }

            else if (dIdx < ndist.length) {
                double temp = ndist[dIdx] - curdist;
                double dtime = temp / speed;
                seconds += dtime;
                curdist += temp;
            }
        }

        System.out.println(Arrays.toString(ndist));
        System.out.println(Arrays.toString(ntime));
        System.out.println(counter);

        seconds += (1000.0 - curdist) / speed;

        double dh = seconds - ((int) seconds);

        if (dh >= 0.5) {
            System.out.println(((int) seconds) + 1);
            out.println(((int) seconds) + 1);
        }

        else {
            System.out.println((int) seconds);
            out.println((int) seconds);
        }

        out.close();
        s.close();
    }
}