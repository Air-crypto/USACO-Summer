import java.util.*;

public class longwords {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        int n = s.nextInt();

        for (int i = 0; i < n; i++) {
            String word = s.next();

            if (word.length() <= 10) {
                System.out.println(word);
                continue;
            }

            String fin = word.charAt(0) + Integer.toString(word.length() - 2) + word.charAt(word.length() - 1);

            System.out.println(fin);
        }
    }
}
