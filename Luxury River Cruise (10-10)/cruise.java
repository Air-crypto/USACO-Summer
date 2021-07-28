import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class cruise {
    public static void main(String[] args) throws IOException {
        PrintWriter out = new PrintWriter(new File("cruise.out"));
        Scanner s = new Scanner(new File("cruise.in"));

        int n = s.nextInt();
        int m = s.nextInt();
        int k = s.nextInt();

        int [] turnLeft = new int [n];
        int [] turnRight = new int [n];

        for (int i = 0; i < n; i++) {
            int left = s.nextInt();
            int right = s.nextInt();

            turnLeft[i] = left;
            turnRight[i] = right;
        }

        String instructions = "";

        for (int i = 0; i < m; i++) {
            instructions += s.next();
        }

        int [] resultOnInstruct = new int [n];

        for (int i = 0; i < n; i++) {
            int node = i;

            for (int j = 0; j < instructions.length(); j++) {
                if (instructions.charAt(j) == 'L') {
                    node = turnLeft[node] - 1;
                }

                else {
                    node = turnRight[node] - 1;
                }
            }

            resultOnInstruct[i] = node;
        }

        System.out.println(Arrays.toString(resultOnInstruct));

        Hashtable <Integer, Integer> visited = new Hashtable <Integer, Integer>();

        int dock = 0;
        int edges = 0;

        boolean ifCycle = false;

        for (int i = 0; i < k; i++) {
            if (visited.containsKey(dock)) {

                int cycleSize = edges - visited.get(dock);
                int find = (k - visited.get(dock)) % cycleSize;

                int destination = dock;
                
                for (int j = 0; j < find; j++) {
                    destination = resultOnInstruct[destination];
                }

                out.println(destination + 1);

                ifCycle = true;
                break;
            }

            else {
                visited.put(dock, edges);
                edges++;
                dock = resultOnInstruct[dock];
            }
        }

        if (!ifCycle) {
            out.println(dock + 1);
        }

        out.close();
        s.close();
    }
}