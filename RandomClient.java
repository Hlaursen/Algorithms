import edu.princeton.cs.algs4.*;

public class RandomClient {

  public static void main(String[] args) {
    int n = StdIn.readInt(); // Pick from a uniform(0,n) distribution
    //int t = StdIn.readInt(); // Number of times to repeat the experiment
    int[] a = new int[20*n]; // Array for holding u and v, twice

    StdOut.println(n);
    for (int i=0; i<(20*n); i++) {
      StdOut.println(StdRandom.uniform(n));
    }
  }
}
