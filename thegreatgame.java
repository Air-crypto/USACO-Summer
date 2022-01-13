import java.util.*;

public class thegreatgame {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        String movesone = s.nextLine();
        String movestwo = s.nextLine();

        int counter = 0;

        for (int i = 0; i < movesone.length(); i += 2) {
            String mone = movesone.substring(i, i + 2);
            String mtwo = movestwo.substring(i, i + 2);

            if (mone.equals("8<") && mtwo.equals("[]")) {
                counter--;
            }

            if (mtwo.equals("8<") && mone.equals("[]")) {
                counter++;
            }

            if (mone.equals("()") && mtwo.equals("[]")) {
                counter++;
            }

            if (mtwo.equals("()") && mone.equals("[]")) {
                counter--;
            }

            if (mone.equals("8<") && mtwo.equals("()")) {
                counter++;
            }

            if (mtwo.equals("8<") && mone.equals("()")) {
                counter--;
            }
        }

        if (counter < 0) {
            System.out.println("TEAM 1 WINS");
        }

        else if (counter > 0) {
            System.out.println("TEAM 2 WINS");
        }

        else {
            System.out.println("TIE");
        }
    }
}
