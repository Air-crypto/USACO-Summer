import java.util.*;
import java.io.*;

public class convex {
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

            //read in inputs
            for (int j = 0; j < beams; j++) {
                inputs.add(new ArrayList<Integer>());
                ArrayList<Integer> beadsOnBeam = inputs.get(j);

                int braceletsInBeam = s.nextInt();

                for (int l = 0; l < braceletsInBeam; l++) {
                    beadsOnBeam.add(s.nextInt());
                }
            }

            HashMap<Integer, Integer> lastSeen = new HashMap<Integer, Integer>();
            for (int j = 1; j <= 50 /*100 possible colors?? or 50??*/; j++) { 
                lastSeen.put(j, -1);
            }


            //check for continuity between polygons (is it closed or not)
            for (int j = 0; j < beams; j++) {
                ArrayList<Integer> beadsOnBeam = inputs.get(j);

                for (int l = 0; l < beadsOnBeam.size(); l++) {
                    int color = beadsOnBeam.get(l);
                    if (lastSeen.get(color) != -1) {

                        if (((lastSeen.get(color) + 1) == j) || (lastSeen.get(color) == j)) { 
                            lastSeen.put(color, j);
                        } else {
                            bad = true;
                            break;
                        }

                    } else {
                        lastSeen.put(color, j);
                    }
                }

                if (bad) {
                    break;
                }
            }

            seen = new Stack <Integer>();
            shownup = new HashMap<Integer, Integer>();
            //now using stack to check for intersection between objects if goes such as 1 - 2 - 1 - 2
            //this is first case of this type (second dealing with edge case which avoids this comes after)
            if (bad == false) {
                for (int j = 0; j < beams; j++) {
                    ArrayList <Integer> currentBeam = inputs.get(j);
                    shownup = new HashMap<Integer, Integer>();

                    for (int l = 0; l < currentBeam.size(); l++) {
                        int currentColor = currentBeam.get(l);
                        if (!shownup.containsKey(currentColor)) {
                            shownup.put(currentColor, 0);
                            seen.push(currentColor);
                            continue; 
                        }

                        if (shownup.containsKey(currentColor)) {
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
            }

            //now for the remaning edge case dealing with the intersection if it goes 1 - 2 - 2 - 1
            //use smth to keep track of sequence across the objects detected under light beams
            if (bad == false) {
                HashMap<Integer, Integer> colorSeenState = new HashMap<Integer, Integer>();
                for (int j = 1; j <= 50; j++) {
                    colorSeenState.put(j, -1); // Clear the slate
                }

                HashMap<Integer, ArrayList<Integer>> colorsSeenOnPrevBeam = new HashMap<Integer, ArrayList<Integer>>();

                for (int j = 0; j < beams; j++) {
                    ArrayList <Integer> currentBeam = inputs.get(j);
                    HashMap<Integer, Boolean> colorSeenOnCurrBeam = new HashMap<Integer, Boolean>();
                    HashMap<Integer, ArrayList<Integer>> colorsSeenBeforeThisColorOnCurrBeam = new HashMap<Integer, ArrayList<Integer>>();
                    ArrayList<Integer> colorOrder = new ArrayList<Integer>();



                    colorsSeenOnPrevBeam = colorsSeenBeforeThisColorOnCurrBeam; // deep copy
                    if(bad) {
                        break;
                    }

 
                    Stack <Integer> openColors = new Stack <Integer>();
                    HashMap<Integer, ArrayList <Integer>> dependenciesOfAColor = new HashMap<Integer, ArrayList <Integer>>();
                    for (int l = 0; l < currentBeam.size(); l++) {
                        int color = currentBeam.get(l);
                        if (openColors.empty()) {
                            openColors.push(color);
                        } else if (openColors.peek() != color) {
                            openColors.push(color);
                        } else {
                            openColors.pop();
                            //Save the color dependencies as the stack
                            ArrayList <Integer> dependentColors = new ArrayList <Integer>();
                            for (int pos = (openColors.size() - 1); pos >= 0; pos--) {
                                dependentColors.add(openColors.elementAt(pos));
                            }
                            dependenciesOfAColor.put(color, dependentColors);
                        }
                    }

                    for (int l = 0; l < currentBeam.size(); l++) {
                        int color = currentBeam.get(l);
                        if (colorSeenState.get(color) != -1) {
                            if (((colorSeenState.get(color) + 1) == j) || (colorSeenState.get(color) == j)) { 
                                colorSeenState.put(color, j);
                            } 
                        } else {
                            colorSeenState.put(color, j);
                        }
                    }

                    //System.out.println("colorSeenState: " + colorSeenState.toString());
                    //System.out.println("dependenciesOfAColor: " + dependenciesOfAColor.toString());

                    for (int l = 0; l < currentBeam.size(); l++) {
                        int color = currentBeam.get(l);
                        ArrayList <Integer> dependentColors = dependenciesOfAColor.get(color);

                        //System.out.println("currentBeam: " + currentBeam.toString());
 
                        for (int idx = 0; idx < dependentColors.size(); idx++) {
                            //System.out.println("idx: " + idx);

                            int lastBeam = colorSeenState.get(dependentColors.get(idx));
                            if (lastBeam != j) {
                                bad = true;
                                break;
                            }
                        }

                        if (bad) {
                            break;
                        }    
                    }
 
                    if (bad) {
                        break;
                    }
                }
            }

            if (bad) {
                System.out.println("NO");
            }

            else {
                System.out.println("YES");

            }
        }
    }
}