import java.util.*;

public class football {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        String line = s.next();

        char temp = 'a';
        int counter = 0;

        for (int i = 0; i < line.length(); i++) {
            if (counter >= 7) {
                break;
            }

            if (line.charAt(i) == temp) {
                counter++;
                continue;
            }

            temp = line.charAt(i);
            counter = 1;
        }

        if (counter >= 7) {
            System.out.println("YES");
            System.exit(0);
        }

        System.out.println("NO");
    }
}
