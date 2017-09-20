
import edu.princeton.cs.algs4.StdOut;

public class PercolationStats {

    private final double CONFIDENCE_COEFF = 1.96;
    
    private double T;
    private double[] thresholds;
    
    
    public PercolationStats(int N, int T) {
        if (N <= 0 || T <= 0) throw new IllegalArgumentException("N and T must be positive integers.");
        thresholds = new double[T];
        for (int i = 0; i < T; i++) {
            StdOut.println("SAMPLE: " + i+1);
            Percolation perc = new Percolation(N);
            int count = 0;
            do {
                StdOut.println("iteration: " + count+1);
                perc.open(StdRandom.uniform(0, N), StdRandom.uniform(0, N));
                count++;
            } while (!perc.percolates());
            thresholds[i] = (double)count/N;
        }
    }
    
    public double mean() {
        return StdStats.mean(thresholds);
    }
    
    public double stddev() {
        return StdStats.stddev(thresholds);
    }
    
    public double confidenceLow() {
        double mean = mean(),
               stddev = stddev();
        return 0;
        
    }
    
    public double confidenceHigh() {
        return 0;
    }
}