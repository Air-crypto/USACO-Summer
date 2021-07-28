import java.util.*;

public class watermelon {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        int n = s.nextInt();

        if (n == 2) {
            System.out.println("No");
            System.exit(0);
        }

        if (n % 2 == 0) {
            System.out.println("Yes");
            System.exit(0);
        }

        System.out.println("No");
    }
}
