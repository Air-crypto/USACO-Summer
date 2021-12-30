import java.util.*;
import java.io.*;

public class PolyChains {
    private class ColorInfo {
        public Integer color;
        public Integer beam;
        public ArrayList<Integer> dependentColors;
        public ArrayList<Integer> colorsSeenBefore;

        @Override
        public String toString() {
            if (this.colorsSeenBefore == null) {
                return "";
            }
            else {
                return "C" + this.color + " B:" + this.colorsSeenBefore.toString() + " D:" + this.dependentColors.toString() + ".";
            }
        }
    };

    private static int stanzas;

    private static PolyChains pC;
    private static ArrayList<ArrayList<Integer>> stanzaInputs;
    private static Scanner s;
    private static HashMap<Integer /* color */, Integer /* 1st seen beam */> color1stSeenOnBeamXPerStanza;
    private static ArrayList<ArrayList<ColorInfo>> perBeamColorInfoForStanza; //Indexed by [beam][color] to get colorInfo

    private static void initperBeamColorList (int beam) {
        ArrayList<ColorInfo> colArray = perBeamColorInfoForStanza.get(beam);
        for (int i = 0; i <=50; i++) {
            ColorInfo colInf = pC.new ColorInfo();
            colInf.color = -1;
            colInf.beam = -1;
            colArray.add(colInf);
        } 
    }

    private static boolean readStanzaOfBeamColorsInitStateTrackingAndCheckIntersectingChainsAndUnblancedColors 
    (int beamsShoneInStanza) 
    {
        int color;
        Boolean badInput = false;
        stanzaInputs = new ArrayList<ArrayList<Integer>>();
        perBeamColorInfoForStanza = new ArrayList<ArrayList<ColorInfo>>(beamsShoneInStanza);
        color1stSeenOnBeamXPerStanza = new HashMap<Integer, Integer>(); //Color To Beam index

        for (int j = 0; j < beamsShoneInStanza; j++) {
            ArrayList<Integer> colorsOnTheBeam = new ArrayList<Integer>();
            ArrayList<ColorInfo> colorInfoForTheBeam = new ArrayList<ColorInfo>(50);

            Stack <Integer> orderOfColorSeenOnBeamTillNow;
            Stack <Integer> orderOfOpenColorSeenOnBeamTillNow;
            HashMap<Integer /* color */, Boolean /* true if color is seen on beam */> isColorSeen;

            orderOfColorSeenOnBeamTillNow = new Stack <Integer>();
            orderOfOpenColorSeenOnBeamTillNow = new Stack <Integer>();
            isColorSeen = new HashMap<Integer, Boolean>();

            // Store the input in the Stanza record
            stanzaInputs.add(colorsOnTheBeam);
            
            // Store the colorInfo for the Stanza record
            perBeamColorInfoForStanza.add(j, colorInfoForTheBeam); // Store this per beam
            
            initperBeamColorList(j);

            int numColorsInBeam = s.nextInt(); // Number of beams in this stanza
            for (int l = 0; l < numColorsInBeam; l++) {
                color = s.nextInt();
                colorsOnTheBeam.add(color);

                // Record the 1st beam on which color was seen in the whole Stanza
                if(!color1stSeenOnBeamXPerStanza.containsKey(color)) {
                    color1stSeenOnBeamXPerStanza.put(color, j); 
                }
                
                // Record whether color was seen on this beam
                if(!isColorSeen.containsKey(color)) {
                    ColorInfo colInf = pC.new ColorInfo();
                    colInf.color = color;
                    colInf.beam = l;
                    colInf.dependentColors = new ArrayList(orderOfOpenColorSeenOnBeamTillNow);;
                    colInf.colorsSeenBefore = new ArrayList(orderOfColorSeenOnBeamTillNow);;
                    colorInfoForTheBeam.set(color, colInf);

                    /* Dont add a self pointer to the same color on the dependents hence order is important. When using colorInfo skip same color */
                    orderOfColorSeenOnBeamTillNow.push(color);
                    orderOfOpenColorSeenOnBeamTillNow.push(color);
                    isColorSeen.put(color, true); 

                } else {
                    int temp = orderOfOpenColorSeenOnBeamTillNow.pop();
                    if (temp != color) {
                        /* Colors intersect on the beam. Not concentric. */
                        badInput = true;
                    }

                    ColorInfo colInf = colorInfoForTheBeam.get(color);
                    colInf.dependentColors = new ArrayList(orderOfOpenColorSeenOnBeamTillNow);
                    colInf.colorsSeenBefore = new ArrayList(orderOfColorSeenOnBeamTillNow);                   
                    colorInfoForTheBeam.set(color, colInf);
                } 
            }

            if(!orderOfOpenColorSeenOnBeamTillNow.empty()) { 
                /* Colors are not balanced */
                badInput = true;
            }

        }

        return badInput;
    }

    private static boolean checkIfAnyChainIsBrokenAcrossBeamsInStanza (int beamsShoneInStanza) 
    {
        int color;
        Boolean brokenBracelet = false;

        for (int j = 0; j < beamsShoneInStanza; j++) {
            if (j == 0) { 
                /* No previous beam so skip this iteration. Could have also set j = 1 but then 1 beam stanza is an exception to handle */
                continue;
            }

            ArrayList<Integer> colorsOnTheBeam = stanzaInputs.get(j);

            for (int l = 0; l < colorsOnTheBeam.size(); l++) {
                color = colorsOnTheBeam.get(l);

                int beam = color1stSeenOnBeamXPerStanza.get(color);

                //System.out.println("First Beam:" + beam + " Color:" + color + " j:" + j);

                if (beam < j) {
                    ColorInfo colInf = perBeamColorInfoForStanza.get(j-1).get(color);

                    //System.out.println("colInf:" + colInf.toString());

                    if (colInf.color != color || colInf.beam == -1) {
                        /* bracelet broken across beams case */
                        brokenBracelet = true;
                        break;
                    }
                }

            }

            if (brokenBracelet) { 
                return brokenBracelet;
            }

        }

        return brokenBracelet;
    }

    public static Boolean checkChainCrossingBetweenBeamsInsideAStanza (int beamsShoneInStanza) 
    {
        int color;
        Boolean brokenBracelet = false;

        for (int j = 0; j < beamsShoneInStanza; j++) {
            if (j == 0) { 
                /* No previous beam so skip this iteration. 
                Could have also set j = 1 but then 1 beam stanza is an exception to handle */
                continue;
            }

            ArrayList<Integer> colorsOnTheBeam = stanzaInputs.get(j);
            ArrayList<ColorInfo> colorInfoForCurrentBeam = perBeamColorInfoForStanza.get(j);
            ArrayList<ColorInfo> colorInfoForPreviousBeam = perBeamColorInfoForStanza.get(j-1);

            for (int l = 0; l < colorsOnTheBeam.size(); l++) {
                color = colorsOnTheBeam.get(l);
                int beam = color1stSeenOnBeamXPerStanza.get(color);

                if (beam == j) { 
                    /* Do not need to check its order with previous 
                    * beams as there are no occurances of it before this beam */
                    continue;
                }

                int tempColor;

                ColorInfo colInfPrevBeam = colorInfoForPreviousBeam.get(color);
                //System.out.println("Beam:"+j+ " colorInfoForPreviousBeam:" + color + " " + colInfPrevBeam);
                for (int k = 0; k < colInfPrevBeam.dependentColors.size(); k++) {

                    tempColor = colInfPrevBeam.dependentColors.get(k);
                    if (tempColor == color) continue; // Self color
                

                    ColorInfo colInf = colorInfoForCurrentBeam.get(tempColor);
                    if (colInf.color != tempColor || colInf.beam == -1) continue; // Prev color not on current beam
                    

                    // On prevBeam tempcolor was seen before color, so check if color was seen before tempcolor on this beam too
                    int tempIndex = colorInfoForCurrentBeam.get(tempColor).colorsSeenBefore.indexOf(color);

                    if (tempIndex == -1) {
                        // Dependency chain inverted
                        brokenBracelet = true;
                        break;
                    }
                }

                if (brokenBracelet) { 
                    return brokenBracelet;
                }

                ColorInfo colInfCurrBeam = colorInfoForCurrentBeam.get(color);
                //System.out.println("2nd Chk Beam:"+j+ " Color:" + color + "CI:"+colorInfoForCurrentBeam);
                for (int k = 0; k < colInfCurrBeam.colorsSeenBefore.size(); k++) {

                    tempColor = colInfCurrBeam.colorsSeenBefore.get(k);
                    //System.out.println("Color:" + color + "TempColor:" + tempColor);

                    if (tempColor == color) continue; // Self color
                

                    ColorInfo colInf = colorInfoForCurrentBeam.get(tempColor);
                    if (colInf.color != tempColor || colInf.beam == -1) {continue;} // Prev color not on current beam

                    ColorInfo colInf2 = colorInfoForPreviousBeam.get(tempColor);
                    if (colInf2.color != tempColor || colInf2.beam == -1) {continue;} // Prev color not on prev beam
                    
                    //System.out.println("TempColor2:" + tempColor + " color:" + color + " " + colorInfoForPreviousBeam.get(tempColor).colorsSeenBefore.indexOf(color) +" " + colorInfoForPreviousBeam.get(tempColor).colorsSeenBefore);

                    // On prevBeam tempcolor was seen before color, so check if color was seen before tempcolor on this beam too
                    int tempIndex = colorInfoForPreviousBeam.get(color).colorsSeenBefore.indexOf(tempColor);

                    if (tempIndex == -1) {
                        // Dependency chain inverted
                        brokenBracelet = true;
                        break;
                    }
                }

                if (brokenBracelet) { 
                    return brokenBracelet;
                }


            }
        }

        return brokenBracelet;
    }

    public static void main(String[] args) {
        Boolean debug = false;
        pC = new PolyChains();
        s = new Scanner(new InputStreamReader(System.in));
        
        stanzas = s.nextInt();

        for (int st = 0; st < stanzas; st++) {
            int colors = s.nextInt();
            int beamsShone = s.nextInt();
            boolean chainsBroken = false;

            chainsBroken = readStanzaOfBeamColorsInitStateTrackingAndCheckIntersectingChainsAndUnblancedColors(beamsShone);

            if (debug) {
                //system.out.println("stanzaInputs:" + stanzaInputs.toString());
                //system.out.println("-------------------------------");
                //system.out.println("color1stSeenOnBeamXPerStanza:" + color1stSeenOnBeamXPerStanza.toString());
                //system.out.println("-------------------------------");
                //system.out.println("perBeamColorInfoForStanza:" + perBeamColorInfoForStanza.toString());
            }

            if (chainsBroken) {
                System.out.println("NO");
                continue;
            }

            chainsBroken = checkIfAnyChainIsBrokenAcrossBeamsInStanza(beamsShone);
            if (chainsBroken) {
                System.out.println("NO");
                continue;
            }

            chainsBroken = checkChainCrossingBetweenBeamsInsideAStanza(beamsShone);
            if (chainsBroken) {
                System.out.println("NO");
                continue;
            }

            /******************************* */
            if (chainsBroken) {
                System.out.println("NO");
            }
            else {
                System.out.println("YES");
            }
        }
    }
}
