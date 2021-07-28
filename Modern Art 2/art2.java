import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class art2 {
    public static void main(String[] args) throws IOException {
        PrintWriter out = new PrintWriter(new File("art2.out"));
        Scanner s = new Scanner(new File("art2.in"));

        int n = s.nextInt();

        int [] arr = new int [n];
        
        for (int i = 0; i < n; i++) {
            arr[i] = s.nextInt();
        }

        

        out.close();
        s.close();
    }
}