import java.util.*;

public class lottery {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        int n = s.nextInt();

        int usedCoins = 0;
        
        while (n > 0) {
            if (n >= 100) {
                usedCoins += n / 100;
                n = n % 100;
                continue;
            }

            else if (n >= 20) {
                usedCoins += n / 20;
                n = n % 20;
                continue;
            }

            else if (n >= 10) {
                usedCoins += n / 10;
                n = n % 10;
                continue;
            }

            else if (n >= 5) {
                usedCoins += n / 5;
                n = n % 5;
                continue;
            }

            usedCoins += n;
            n = 0;
        }

        System.out.println(usedCoins);
    }
}
