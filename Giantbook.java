import edu.princeton.cs.algs4.*;
import one.MyUnionFind;


public class Giantbook
{
    public static void main(String[] args)
    {
      StdOut.println("Input size of MyUnionFind datastructure, n:");
      int n = StdIn.readInt(); //Size of data structure
      StdOut.println("Input number of times to repeat, T:");
      int T = StdIn.readInt(); //Number of times to repeat experiment
      int[][] topResults = new int[T][3];

      //Run experiment T times
      for (int t=0; t<T; t++)
      {
        int i_isolated = -1; // Number of edges read when no more isolated nodes
        int i_giant = -1; // Number of edges read when graph has a giant component
        int i_connected = -1; // Number of edges read when the graph is connected
        int results[] = new int[3];
        MyUnionFind uf = new MyUnionFind(n);

        //Generate UF data structure and add connections until the connected component emerges
        int i = 0; //Number of edges that have been read/
        while (true)
        {
          int p = StdRandom.uniform(n);
          int q = StdRandom.uniform(n);
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
            results[0] = i_isolated;
            results[1] = i_giant;
            results[2] = i_connected;
            break;
          }
        }
        topResults[t] = results;
      }

      // Print results
      StdOut.println("\nUF size: " + n);
      for (int t=0; t<T; t++)
      {
        StdOut.println("Experiment number: " + (t+1));
        StdOut.println("Isolated: " + topResults[t][0]);
        StdOut.println("Giant : " + topResults[t][1]);
        StdOut.println("Connected: " + topResults[t][2]);
      }
    }
}
