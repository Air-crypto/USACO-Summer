import java.util.*;
import java.io.*;

public class convexTest {
    private static int stanzas;
    public static Stack <Integer> seen;
    public static ArrayList<ArrayList<Integer>> inputs;
    public static HashMap<Integer, Integer> shownup;
    public static void main(String[] args) {
        Scanner s = new Scanner(new InputStreamReader(System.in));
        
        stanzas = s.nextInt();

        for (int i = 0; i < stanzas; i++) {
            //System.out.println("start: " + i);

            int colors = s.nextInt();
            int beams = s.nextInt();

            boolean bad = false;
            
            inputs = new ArrayList<ArrayList<Integer>>();

            HashMap<Integer, Integer> firstBeamOnWhichColorSeen = new HashMap<Integer, Integer>();
            ArrayList<HashMap<Integer, Boolean> > colorsPresentOnBeams = new ArrayList<HashMap<Integer, Boolean>>();
            HashMap<Integer /* Beam */, HashMap<Integer /* color */, ArrayList<Integer>>> colorDepsForEachBeam = 
            new HashMap<Integer /* Beam */, HashMap<Integer /* color */, ArrayList<Integer>>>();


            //read in inputs
            for (int j = 0; j < beams; j++) {
                inputs.add(new ArrayList<Integer>());
                ArrayList<Integer> beadsOnBeam = inputs.get(j);

                colorsPresentOnBeams.add(new HashMap<Integer, Boolean>());

                int colorsInBeam = s.nextInt();

                ArrayList<Integer> colorsOrderPerBeam = new ArrayList<Integer>();
                for (int l = 0; l < colorsInBeam; l++) {
                    int color = s.nextInt();
                    beadsOnBeam.add(color); /* Recorded on inputs */
                    if (!colorsOrderPerBeam.contains(color)){
                        colorsOrderPerBeam.add(color);
                    }

                    if(!colorsPresentOnBeams.get(j).containsKey(color)) {
                        // Only add the sequence for the 1st time we see the color on the beam, not the second time
                        colorsPresentOnBeams.get(j).put(color, true);
                        ArrayList<Integer> newTempAL = new ArrayList<Integer>();
                        newTempAL.addAll(colorsOrderPerBeam);
                        colorDepsForEachBeam.get(j).put(color, newTempAL); 
                    }

                    if (!firstBeamOnWhichColorSeen.containsKey(color)) {
                        firstBeamOnWhichColorSeen.put(color, j);
                    }

                }
            }



            /* Bracelet continuity between beams (is it closed or not) */
             for (int j = 0; j < beams; j++) {
                ArrayList<Integer> beadsOnBeam = inputs.get(j);

                for (int l = 0; l < beadsOnBeam.size(); l++) {
                    int color = beadsOnBeam.get(l);

                    if (j == 0) {
                        continue;
                    }
                        
                    if (firstBeamOnWhichColorSeen.get(color) < j) {
                        if (!colorsPresentOnBeams.get(j - 1).containsKey(color)) {  
                            /* broken bracelet case */
                            bad = true;
                            break;
                        }
                    }
                }

                if (bad) {
                    break;
                }
            }

            if (bad == true) {
                System.out.println("NO");
                continue;
            }

            /* Check if the bracelets are concentric inside a single beam 
            If its something likes 1 - 2 - 1 - 2 then its bad - colors interset on the same beam 
            if its 1 - 2 - 2 - 1 then it is ok 
            We keep a stack to record all the open colors.
            */
            //now using stack to check for intersection between objects if goes such as 1 - 2 - 1 - 2
            //this is first case of this type (second dealing with edge case which avoids this comes after)
            for (int j = 0; j < beams; j++) {
                ArrayList <Integer> currentBeam = inputs.get(j);
                seen = new Stack <Integer>();
                shownup = new HashMap<Integer, Integer>();

                for (int l = 0; l < currentBeam.size(); l++) {
                    int currentColor = currentBeam.get(l);
                    if (!shownup.containsKey(currentColor)) {
                        /* First time we see this color. So mark the color as seen and push it on the stack */
                        shownup.put(currentColor, 0);
                        seen.push(currentColor);
                        continue; 
                    } else {
                        /* Second time we see this color, so pop it from the stack. 
                        Also see it the top the stack is the same color, if not we have an intersecting bracelet */
                        int temp = seen.pop();

                        if (temp != currentColor) {
                            bad = true;
                            break;
                        }
                    }
                }

                if (bad) {
                    break;
                }
            }
            
            if (bad == true) {
                System.out.println("NO");
                continue;
            }

            /* Cross check dependency order across beams 

            Detect this case and reject it
            1 - 1 - 2 - 2
            2 - 2 - 1 - 1 
                       HashMap<Integer, Integer> firstBeamOnWhichColorSeen = new HashMap<Integer, Integer>();
            ArrayList<HashMap<Integer, Boolean> > colorsPresentOnBeams = new ArrayList<HashMap<Integer, Boolean>>();

            */

            //now for the remaning edge case dealing with the intersection if it goes 1 - 2 - 2 - 1
            //use smth to keep track of sequence across the objects detected under light beams
            for (int j = 0; j < beams; j++) {
                ArrayList<Integer> beadsOnBeam = inputs.get(j);

                for (int l = 0; l < beadsOnBeam.size(); l++) {
                    int color = beadsOnBeam.get(l);


                }
            }

            if (bad == true) {
                System.out.println("NO");
                continue;
            }


            /**************** */
            if (bad) {
                System.out.println("NO");
            }

            else {
                System.out.println("YES");

            }
        }
    }
}
