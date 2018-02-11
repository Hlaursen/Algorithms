// Import MyUnionFind.java
import edu.princeton.cs.algs4.*;
import one.MyUnionFind;


public class cGiantbook
{
  public int i = 0; //Number of edges that have been read
  public int i_isolated = -1; // Number of edges read when no more isolated nodes
  public int i_giant = -1; // Number of edges read when graph has a giant component
  public int i_connected = -1; // Number of edges read when the graph is connected
  public int n;
  public int[] a;

  public cGiantbook(int n, int[] a) {
      this.n = n;
      this.a = a;

  }

  public int[] run() {
    int[] result = new int[4];
    MyUnionFind uf = new MyUnionFind(a[0]);

    int m = 1;
    while (m < a.length) {
      int p = a[m];
      int q = a[m+1];
      m+=2;
      i++; // Increment number of edges
      if (uf.connected(p, q)) continue;
      uf.union(p, q);

      if (i_giant == -1 && uf.maxComponentSize() >= (n/2)) {
        i_giant = i;
      }

      // Isolated
      if (i_isolated == -1 && uf.isolated() == 0) {
        i_isolated = i;
      }

      // Connected
      if (i_connected == -1 && uf.count() == 1) {
        i_connected = i;
        result[0] = n;
        result[1] = i_giant;
        result[2] = i_isolated;
        result[3] = i_connected;
        return result;
      }
  }
  result[0] = n;
  result[1] = i_giant;
  result[2] = i_isolated;
  result[3] = i_connected;
  return result;
}

  public static void main(String[] args)
  {
    // Generate u and v uniformly at random (how to specify how many to choose from?)
    /*Read from StdIn a number n, then n pairs of numbers u and v. An event happens
    at time i if it happens after i edges have been read*/





    while (!StdIn.isEmpty())
    {
      int p = StdIn.readInt();
      int q = StdIn.readInt();
      i++; // Increment number of edges
      if (uf.connected(p, q)) continue;
      uf.union(p, q);

      // Giant component emerges
      if (i_giant == -1 && uf.maxComponentSize() >= (n/2)) {
        i_giant = i;
      }

      // Isolated
      if (i_isolated == -1 && uf.isolated() == 0) {
        i_isolated = i;
      }

      // Connected
      if (i_connected == -1 && uf.count() == 1) {
        i_connected = i;
        break;
      }
    }
}
}
