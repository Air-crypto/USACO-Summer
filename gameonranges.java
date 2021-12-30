import java.util.*;

public class gameonranges {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        int t = s.nextInt();

        for (int i = 0; i < t; i++) {
            int n = s.nextInt();

            for (int j = 0; j < n; j++) {
                int l = s.nextInt();
                int r = s.nextInt();

                int d = 0;

                if (l == r) {
                    d = l;
                    System.out.println(l + " " + r + " " + d); 
                    continue;
                }

                
            }
        }
    }
}
