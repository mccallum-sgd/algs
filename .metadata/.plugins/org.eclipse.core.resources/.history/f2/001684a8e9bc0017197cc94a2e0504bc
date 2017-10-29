import edu.princeton.cs.algs4.Stopwatch;

public class Test {
    public static void main (String[] args) {
        runTest(25, 10);
        runTest(50, 20);
        runTest(50, 60);
        runTest(100, 60);
        runTest(200, 60);
    }
    
    private static void runTest(int N, int T) {
        Stopwatch s = new Stopwatch();
        PercolationStats pstats = new PercolationStats(N, T);
        double time = s.elapsedTime();
        System.out.println("running time: " + time + "\n" + 
               "mean: " + pstats.mean() + "\n" + 
                "stddev: " + pstats.stddev() + "\n" + 
                "confidencLow: " + pstats.confidenceLow() + "\n" + 
                "confidenceHigh: " + pstats.confidenceHigh() + "\n");
    }
}