import edu.princeton.cs.algs4.Stack;

public class DepthFirstPaths implements Paths {
    private boolean[] marked;
    private int[] edgeTo;
    private int s;
    
    /**
     * Uses depth-first search to analyze the graph 
     * and find all paths to each vertex from the 
     * specified source vertex {@code s}. Running time
     * is ~ N, where N is the number of vertices connected
     * to {@code s}.
     * @param g - the graph to search
     * @param s - the source vertex
     */
    public DepthFirstPaths(Graph g, int s) {
        marked = new boolean[g.V()];
        edgeTo = new int[g.V()];
        this.s = s;
        dfs(g, s);
    }
    
    private void dfs(Graph g, int v) {
        marked[v] = true;
        for (int w: g.adj(v))
            if (!marked[w]) {
                dfs(g, w);
                edgeTo[w] = v;
            }
    }

    @Override
    public boolean hasPathTo(int v) {
        return marked[v];
    }

    @Override
    public Iterable<Integer> pathTo(int v) {
        if (!hasPathTo(v)) return null;
        Stack<Integer> path = new Stack<Integer>();
        for (int x = v; x != s; x = edgeTo[x])
            path.push(x);
        path.push(s);
        return path;
    }
    
}
