import edu.princeton.cs.algs4.Bag;

public class Graph {
    private final int V;
    private Bag<Integer>[] adj;

    /**
     * Constructs a Graph with {@code V} vertices.
     * @param V - number of vertices.
     */
    public Graph(int V) {
        this.V = V;
        adj = (Bag<Integer>[]) new Bag[V];
        for (int v = 0; v < V; v++)
            adj[v] = new Bag<Integer>();
    }
    
    public void addEdge(int v, int w) {
        adj[v].add(w);
        adj[w].add(v);
    }
    
    public Iterable<Integer> adj(int v) {
        return adj[v];
    }
    
    public int V() {
        return adj.length;
    }
    
    public int E() {
        int count = 0;
        for (Bag<Integer> bag: adj)
            count += bag.size();
        return count/2;         // each edge counted twice
    }
    
    public String toString() {
        return "";
    }
    
}
