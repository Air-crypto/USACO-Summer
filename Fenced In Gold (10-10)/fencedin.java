import java.util.*;
import java.io.*;

public class fencedin {
	public static void main(String[] args) throws Exception {
		//Scanner stdin = new Scanner(new File("fencedin.in"));
        BufferedReader reader = new BufferedReader(new FileReader(new File("fencedin.in")));
        StringTokenizer s = new StringTokenizer(reader.readLine());

		int maxN = Integer.parseInt(s.nextToken());
		int maxM = Integer.parseInt(s.nextToken());
		int n = Integer.parseInt(s.nextToken());
		int m = Integer.parseInt(s.nextToken());

		int[] nList = new int[n + 2];

		for (int i = 0; i < n; i++) {
            s = new StringTokenizer(reader.readLine());

            nList[i] = Integer.parseInt(s.nextToken());
        }

		nList[n] = 0;
		nList[n + 1] = maxN;

		Arrays.sort(nList);
		
		int[] mList = new int[m + 2];

		for (int i = 0; i < m; i++) {
            s = new StringTokenizer(reader.readLine());

            mList[i] = Integer.parseInt(s.nextToken());
        }

		mList[m] = 0;
		mList[m + 1] = maxM;
		Arrays.sort(mList);

		item[] allRC = new item[n + m + 2];

		for (int i = 0; i <= n; i++) {
			allRC[i] = new item(i, nList[i + 1] - nList[i]);
        }

		for (int i = 0; i <= m; i++) {
			allRC[n + 1 + i] = new item(n + 1 + i, mList[i + 1] - mList[i]);
        }

		Arrays.sort(allRC);

		dset dj = new dset(n * (m + 1) + m * (n + 1));
		long res = 0;
		int added = 0;
		int index = 0;

		while (added < (n + 1) * (m + 1) - 1) {
			item next = allRC[index];
			
			if (next.ID <= n) {
				int nVal = next.ID;

				for (int i = 0; i < m; i++) {
					boolean merge = dj.union(nVal * (m + 1) + i, nVal * (m + 1) + i + 1);

					if (merge) {
						res = res + next.value;
						added++;
					}
				}
			}

			else {
				int mVal = next.ID - n - 1;

				for (int i = 0; i < n; i++) {
					boolean merge = dj.union(i * (m + 1) + mVal, (i + 1) * (m + 1) + mVal);

					if (merge) {
						res = res + next.value;
						added++;
					}
				}
			}	

			index++;
		}

		PrintWriter out = new PrintWriter(new FileWriter("fencedin.out"));
		out.println(res);
		out.close();
	}

    private static class dset {	
        private pair[] parents;
        
        public dset(int n) {
            parents = new pair[n];

            for (int i = 0; i < n; i++) {
                parents[i] = new pair(i, 0);
            }
        }
        
        public int find(int child) {
            if (parents[child].parent == child) {
                return child;
            }
    
            int par = find(parents[child].parent);
            parents[child].parent = par;
            parents[child].height = 1;
    
            return par;
        }
        
        public boolean union(int c1, int c2) {
            int root1 = find(c1);
            int root2 = find(c2);
            
            if (root1 == root2) {
                return false;
            }
                
            if (parents[root1].height > parents[root2].height) {
                parents[root2].parent = root1;
            }
            
            else if (parents[root1].height == parents[root2].height) {
                parents[root2].parent = root1;
                parents[root1].height++;
            }
            
            else {
                parents[root1].parent = root2;
            }
            
            return true;
        }
    }

    private static class item implements Comparable<item> {
        public int ID;
        public int value;
    
        public item(int myID, int myVal) {
            ID = myID;
            value = myVal;
        }
    
        public int compareTo(item other) {
            return this.value - other.value;
        }
    }
    
    private static class pair {
        public int parent;
        public int height;
        
        public pair(int a, int b) {
            parent = a;
            height = b;
        }
    }
}