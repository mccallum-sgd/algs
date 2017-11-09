
public class GraphProcessing {

    /**
     * Computes the degree of v.
     */
    public static int degree(Graph g, int v) {
        int degree = 0;
        for (int w: g.adj(v)) degree++;
        return degree;
    }
    
    /**
     * Computes the maximum degree.
     */
    public static int maxDegree(Graph g) {
        int max = 0;
        for (int v = 0; v < g.V(); v++)
            if (degree(g, v) > max)
                max = degree(g, v);     // improvements here
        return max;
    }
    
    /**
     * Computes the average degree.
     */
    public static double averageDegree(Graph g) {
        return 2.0 * g.E() / g.V();
    }
    
    /**
     * Counts self-loops.
     */
    public static int numberOfSelfLoops(Graph g) {
        int count= 0;
        for (int v = 0; v < g.V(); v++)
            for (int w: g.adj(v))
                if (v == w) count++;
        return count/2;         // each edge counted twice
    }
}
