import java.io.*;
import java.util.*;
 
public class distant {
  public static PriorityQueue<state> pq;
  public static int N, A, B;
  public static boolean[][] visited;
  public static char[][] grid;
  public static void main(String[] args) throws FileNotFoundException {
    Scanner in = new Scanner (new File("distant.in"));
    PrintWriter pw = new PrintWriter("distant.out");
 
    N = in.nextInt();
    A = in.nextInt();
    B = in.nextInt();
    grid = new char[N][N];
    for (int i=0; i<N; i++) {
      String row = in.next();
      for (int j=0; j<N; j++) {
        grid[i][j] = row.charAt(j);
      }
    }
    long out = 0;
 
    for (int i=0; i<N; i++) {
      for (int j=0; j<N; j++) {
 
        pq = new PriorityQueue<> ();
        visited = new boolean[N][N];
        pq.add(new state(0, i, j));
 
        while (!pq.isEmpty()) {
          state current = pq.poll();
 
          if (visited[current.x][current.y]) {
            continue;
          }
 
          visited[current.x][current.y] = true;
          out = Math.max(out, current.distance);
          add(current.x - 1, current.y, current);
          add(current.x + 1, current.y, current);
          add(current.x, current.y - 1, current);
          add(current.x, current.y + 1, current);
        }
 
      }
    }
 
    pw.println(out);
    pw.close();
  }
 
  public static void add(int x, int y, state old) {
    if (x < 0 || x >= N || y < 0 || y >= N || visited[x][y]) {
        return;
    }
    if (grid[x][y] == grid[old.x][old.y]) {
        pq.add(new state(old.distance+A, x, y));
    }
    else {
        pq.add(new state(old.distance+B, x, y));
    }
  }
 
  public static class state implements Comparable<state>{
 
    public int x, y;
    long distance;
 
    public state(long distance, int x, int y){
      this.distance = distance;
      this.x = x;
      this.y = y;
    }
 
    @Override
    public int compareTo(state other) {
      return (int) (distance - other.distance); 
    }
  }
 
}