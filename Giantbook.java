import edu.princeton.cs.algs4.*;
import one.MyUnionFind;

public class Giantbook
{
    public static void main(String[] args)
    {
      StdOut.println("Input size of MyUnionFind datastructure, n:");
      int n = StdIn.readInt();                      //Size of data structure
      StdOut.println("Input number of times to repeat, T:");
      int T = StdIn.readInt();                      //Number of times to repeat experiment
      StdOut.println("Running");
      int[][] collectedResults = new int[3][T];     // Collects counts for each experiment

      //Run experiment T times
      for (int t=0; t<T; t++)
      {
        int i_isolated = -1; // Number of edges read when no more isolated nodes
        int i_giant = -1; // Number of edges read when graph has a giant component
        int i_connected = -1; // Number of edges read when the graph is connected
        int results[] = new int[3];
        MyUnionFind uf = new MyUnionFind(n);

        //Generate UF data structure and add connections until the connected component emerges
        int i = 0;      //Number of edges that have been read/
        while (true)
        {
          int p = StdRandom.uniform(n);
          int q = StdRandom.uniform(n);
          i++;        // Increment number of edges
          if (uf.connected(p, q)) continue;
          uf.union(p, q);

          // Giant component emerges
          if (i_giant == -1 && uf.maxComponentSize() >= (n/2)) {
            i_giant = i;
          }

          // No more isolated components exist
          if (i_isolated == -1 && uf.isolatedComponents() == 0) {
            i_isolated = i;
          }

          // Connected component emerges
          if (i_connected == -1 && uf.count() == 1) {
            i_connected = i;
            collectedResults[0][t] = i_isolated;
            collectedResults[1][t] = i_giant;
            collectedResults[2][t] = i_connected;
            break;
          }
        }
      }

      // Statistical calculations
      double isolatedMean = StdStats.mean(collectedResults[0]);
      double isolatedStdDev = StdStats.stddev(collectedResults[0]);
      double giantMean = StdStats.mean(collectedResults[1]);
      double giantStdDev = StdStats.stddev(collectedResults[1]);
      double connectedMean = StdStats.mean(collectedResults[2]);
      double connectedStdDev = StdStats.stddev(collectedResults[2]);

      // Print results
      StdOut.println("\nUF size: " + n);
      for (int t=0; t<T; t++)
      {
        StdOut.println("EXPERIMENT NUMBER: " + (t+1));
        StdOut.println("No more isolated components emerge after random connection number: " + collectedResults[0][t]);
        StdOut.println("Giant component emerges after random connection number: " + collectedResults[1][t]);
        StdOut.println("Connected component emerges after random connection number: " + collectedResults[2][t]);
      }
      StdOut.printf("\nisolatedMean: %.2e", isolatedMean);
      StdOut.printf("\nisolatedStdDev: %.2e", isolatedStdDev);
      StdOut.printf("\ngiantMean: %.2e", giantMean);
      StdOut.printf("\ngiantStdDev: %.2e", giantStdDev);
      StdOut.printf("\nconnectedMean: %.2e", connectedMean);
      StdOut.printf("\nconnectedStdDev: %.2e", connectedStdDev);

    }
}
