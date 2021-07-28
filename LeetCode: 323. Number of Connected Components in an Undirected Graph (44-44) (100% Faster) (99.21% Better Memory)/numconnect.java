import java.util.*;

public class numconnect {
    private static int [] arr;
    private static int [] size;
    private static HashMap<String, Integer> seen = new HashMap<String, Integer>();
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        int n = s.nextInt();

        int [][] edges = {};

        System.out.println(countComponents(n, edges));
    }

    public static int countComponents(int n, int[][] edges) {
        arr = new int [n];
        size = new int [n];

        Arrays.fill(size, 1);
        
        for (int i = 0; i < n; i++) {
            arr[i] = i;
        }

        for (int i = 0; i < edges.length; i++) {
            int w = edges[i][0];
            int h = edges[i][1];

            merge(w, h);
        }
        
        ArrayList<Integer> list = new ArrayList<Integer>();

        for (int i = 0; i < n; i++) {
            if (arr[i] == i) {
                list.add(i);
            }
        }

        return list.size();
    }

    public static int findRoot (int x) {
        if (arr[x] == x) {
            return x;
        }

        return arr[x] = findRoot(arr[x]);
    }

    public static void merge (int a, int b) {
        if (size[findRoot(b)] >= size[findRoot(a)]) { arr[findRoot(a)] = findRoot(b); size[findRoot(b)] += 1; return; }
        arr[findRoot(b)] = findRoot(a);
        size[findRoot(a)] += 1;
    }

    public static boolean isConnected (int a, int b) {
        return findRoot(a) == findRoot(b);
    }
}

/*
LeetCode Raw Solution:

import java.util.*;

class Solution {
    private static int [] arr;
    private static int [] size;
    
    public static int countComponents(int n, int[][] edges) {
        arr = new int [n];
        size = new int [n];

        Arrays.fill(size, 1);
        
        for (int i = 0; i < n; i++) {
            arr[i] = i;
        }

        for (int i = 0; i < edges.length; i++) {
            int w = edges[i][0];
            int h = edges[i][1];

            merge(w, h);
        }

        ArrayList<Integer> list = new ArrayList<Integer>();

        for (int i = 0; i < n; i++) {
            if (arr[i] == i) {
                list.add(i);
            }
        }

        return list.size();
    }

    public static int findRoot (int x) {
        if (arr[x] == x) {
            return x;
        }

        return arr[x] = findRoot(arr[x]);
    }

    public static void merge (int a, int b) {
        if (size[findRoot(b)] >= size[findRoot(a)]) { arr[findRoot(a)] = findRoot(b); size[findRoot(b)] += 1; return; }
        arr[findRoot(b)] = findRoot(a);
        size[findRoot(a)] += 1;
    }
}

--------------------------------------------------------------------------------
1-Liner Solution. :)

import java.util.*; class Solution { private static int [] arr; private static int [] size; public static int countComponents(int n, int[][] edges) { arr = new int [n]; size = new int [n]; Arrays.fill(size, 1); for (int i = 0; i < n; i++) { arr[i] = i; } for (int i = 0; i < edges.length; i++) { int w = edges[i][0]; int h = edges[i][1]; merge(w, h); } ArrayList<Integer> list = new ArrayList<Integer>(); for (int i = 0; i < n; i++) { if (arr[i] == i) { list.add(i); } } return list.size(); } public static int findRoot (int x) { if (arr[x] == x) { return x; } return arr[x] = findRoot(arr[x]); } public static void merge (int a, int b) { if (size[findRoot(b)] >= size[findRoot(a)]) { arr[findRoot(a)] = findRoot(b); size[findRoot(b)] += 1; return; } arr[findRoot(b)] = findRoot(a); size[findRoot(a)] += 1; return; } }

*/
