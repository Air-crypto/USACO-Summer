import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class carnival {
    private static HashMap <Integer, Integer> seen = new HashMap<Integer, Integer>();
    public static void main(String[] args) throws IOException {
        PrintWriter out = new PrintWriter(new File("poker.out"));
        BufferedReader reader = new BufferedReader(new FileReader(new File("poker.in")));
        StringTokenizer s = new StringTokenizer(reader.readLine());

        int n = Integer.parseInt(s.nextToken());

        int [][] arr = new int [n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(s.nextToken());
            }
        }

        

        out.close();
        reader.close();
    }
}