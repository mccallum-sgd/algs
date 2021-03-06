import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.StdRandom;

public class PercolationStats {

    private final double CONFIDENCE_COEFF = 1.96;
    
    private int T;
    private double[] thresholds;
    private double mean, stddev;
    
    
    public PercolationStats(int N, int T) {
        if (N <= 0 || T <= 0) throw new IllegalArgumentException("N and T must be positive integers.");
        this.T = T;
        thresholds = new double[T];
        runSim(N);
    }
    
    public double mean() {
        mean = StdStats.mean(thresholds);
        return mean;
    }
    
    public double stddev() {
        stddev =  StdStats.stddev(thresholds);
        return stddev;
    }
    
    public double confidenceLow() {
        return mean - CONFIDENCE_COEFF*stddev / Math.sqrt(T);
    }
    
    public double confidenceHigh() {
        return mean+(CONFIDENCE_COEFF*stddev/Math.sqrt(T));
    }
    
    private void runSim(int N) {
        for (int i = 0; i < T; i++) {
            Percolation perc = new Percolation(N);
            do {
                perc.open(StdRandom.uniform(0, N), StdRandom.uniform(0, N));
            } while (!perc.percolates());
            thresholds[i] = 1.0*perc.numberOfOpenSites()/(N*N);
        }
    }
}