
public class CC {
    
    private int[] id;
    private boolean[] marked;
    private int ct;

    public CC(Graph g) {
        marked = new boolean[g.V()];
        id = new int[g.V()];
        for (int v = 0; v < g.V(); v++)
            if (!marked[v]) {
                dfs(g, v);
                ct++;
            }
    }
    
    private void dfs(Graph g, int v) {
        marked[v] = true;
        id[v] = ct;
        for (int w: g.adj(v))
            if (!marked[w])
                dfs(g, w);
    }
    
    /**
     * Returns true if {@code v} and {@code w} are
     * connected (i.e. there is a path between them).
     */
    public boolean connected(int v, int w) {
        return id[v] == id[w];
    }
    
    /**
     * Returns the number of connected components in
     * the graph.
     */
    public int count() {
        return ct;
    }
    
    /**
     * Returns the component identifier for
     * v.
     */
    public int id(int v) {
        return id[v];
    }
    
}
