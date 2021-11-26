import java.util.*;

public class haircut {
    private static int n;
    
    public static void main (String [] args) {
        Scanner s = new Scanner (System.in);
        
        n = s.nextInt();
        
        int [] arr = new int [n];
        
        for (int i = 0; i < n; i++) {
            arr[i] = s.nextInt();
        }
        
        //System.out.println(Arrays.toString(arr));
        
        ArrayList[] idx = new ArrayList[n+1];
        
		for (int i = 0; i <= n; i++) {
			idx[i] = new ArrayList <Integer>();
		}
		
		for (int i = 0; i < n; i++) {
			idx[arr[i]].add(i);
		}
		
		BIT bit = new BIT(n);
		
		long [] res = new long [n];
		long sum = 0;
		
		for (int i = 0; i < n; i++) {
			res[i] = sum;
			
			for (Integer x: (ArrayList <Integer>) idx[i]) {
				sum += (x - bit.query(x));
				bit.update(x + 1, 1);
			}
		}
		
		for (int i = 0; i < n; i++) {
		    System.out.println(res[i]);
		}
    }
    
    private static class BIT {
        private static int [] ft;
    
        public BIT (int z) {
            ft = new int[z + 1];
        }
    
        public static void clearer () {
            for (int i = 0; i < ft.length; i++) {
                ft[i] = 0;
            }
        }
    
        public static void update (int x, int v) {
            while (x <= n) {
                ft[x] += v;
                x += (x &- x);
            }
        }
    
        public static int query (int x) {
            if (x > 0) {
                return ft[x] + query(x - (x &- x));
            }
            
            return 0;
        }
    }
}