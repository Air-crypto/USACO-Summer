import java.util.*;
import java.io.*;

public class rental {

	public static int numCows;
	public static int numStores;
	public static int numFarmers;

	public static long[] amtMilk;
	public static store[] stores;
	public static long[] rent;

	public static void main(String[] args) throws Exception {

		BufferedReader stdin = new BufferedReader(new FileReader("rental.in"));
		StringTokenizer tok = new StringTokenizer(stdin.readLine());
		numCows = Integer.parseInt(tok.nextToken());
		numStores = Integer.parseInt(tok.nextToken());
		numFarmers = Integer.parseInt(tok.nextToken());
		amtMilk = new long[numCows];

		for (int i=0; i<numCows; i++)
			amtMilk[i] = Long.parseLong(stdin.readLine().trim());
		Arrays.sort(amtMilk);

		stores = new store[numStores];
		for (int i=0; i<numStores; i++) {
			tok = new StringTokenizer(stdin.readLine());
			int q = Integer.parseInt(tok.nextToken());
			int p = Integer.parseInt(tok.nextToken());
			stores[i] = new store(q, p);
		}
		Arrays.sort(stores);

		rent = new long[numFarmers];
		for (int i=0; i<numFarmers; i++)
			rent[i] = Long.parseLong(stdin.readLine().trim());
		Arrays.sort(rent);
		for (int i=0; i<numFarmers/2; i++) {
			long t = rent[i];
			rent[i] = rent[numFarmers-1-i];
			rent[numFarmers-1-i] = t;
		}

		long cur = 0;
		for (int i=0; i<numFarmers && i<numCows; i++)
			cur += rent[i];

		int j = 0;
		if (numCows > numFarmers) {

			long quantity = 0;
			for (int i=numFarmers; i<numCows; i++)
				quantity += amtMilk[i];

			while (quantity > 0 && j < numStores) {

				if (quantity > stores[j].quantity) {
					cur += stores[j].total();
					quantity -= stores[j].quantity;
					stores[j].quantity = 0;
					j++;
				}
				else {
					cur += (quantity*stores[j].price);
					stores[j].quantity -= ((int)quantity);
					break;
				}
			}
		}

		long res = cur;

		if (j < numStores) {

			int lastRent = numCows >= numFarmers ? numFarmers-1 : numCows-1;

			while (lastRent >= 0 && j < numStores) {

				cur -= rent[lastRent];

				long quantity = amtMilk[lastRent];

				while (quantity > 0 && j < numStores) {

					if (quantity > stores[j].quantity) {
						cur += stores[j].total();
						quantity -= stores[j].quantity;
						stores[j].quantity = 0;
						j++;
					}
					else {
						cur += (quantity*stores[j].price);
						stores[j].quantity -= ((int)quantity);
						break;
					}
				}

				res = Math.max(res, cur);

				lastRent--;
			}
		}

		PrintWriter out = new PrintWriter(new FileWriter("rental.out"));
		out.println(res);
		out.close();
		stdin.close();
	}
}

class store implements Comparable<store> {

	public int quantity;
	public int price;

	public store(int q, int p) {
		quantity = q;
		price = p;
	}

	public int compareTo(store other) {
		return other.price-this.price;
	}

	public long total() {
		return ((long)quantity)*price;
	}
}

class MyComparator implements Comparator<Long> {
	public int compare(Long a, Long b) {
    	if (a < b) return 1;
    	if (a > b) return -1;
    	return 0;
	}
}