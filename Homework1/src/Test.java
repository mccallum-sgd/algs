import edu.princeton.cs.algs4.Stopwatch;

public class Test {
    public static void main (String[] args) {
        String results = runTest(25, 10) +
                runTest(50, 20) +
                runTest(50, 60) +
                runTest(100, 60) +
                runTest(200, 60);
        System.out.println(results);
    }
    
    private static String runTest(int N, int T) {
        Stopwatch s = new Stopwatch();
        PercolationStats pstats = new PercolationStats(N, T);
        double time = s.elapsedTime();
        return "running time: " + time + "\n" + 
               "mean: " + pstats.mean() + "\n" + 
                "stddev: " + pstats.stddev() + "\n" + 
                "confidencLow: " + pstats.confidenceLow() + "\n" + 
                "confidenceHigh: " + pstats.confidenceHigh() + "\n";
    }
}