import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class clumsy {
    public static void main(String[] args) throws IOException {
        PrintWriter out = new PrintWriter(new File("clumsy.out"));
        Scanner s = new Scanner(new File("clumsy.in"));

        String line = s.nextLine();
        int lineLength = line.length();

        int changes = 0;

        int counter = 0;

        for (int i = 0; i < lineLength; i++) {
            if (line.charAt(i) == '(') {
                counter++;
            }

            else if (line.charAt(i) == ')') {
                counter--;
            }

            if (counter < 0) {
                counter = 1;
                changes++;
            }
        }

        System.out.println(line);

        System.out.println(counter / 2 + changes);
        out.println(counter / 2 + changes);

        out.close();
        s.close();
    }
}