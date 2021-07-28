import java.util.*;

public class dpiling {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        int n = s.nextInt();
        int m = s.nextInt();

        int ans = (int) (Math.floor(n * m * 0.5));

        System.out.println(ans);
    }
}
