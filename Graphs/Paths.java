
public abstract interface Paths {
    
    /**
     * Returns true if there is a path from s to v.
     */
    public abstract boolean hasPathTo(int v);
    
    /**
     * Returns the path from s to v, and returns null 
     * if there is no such path.
     */
    public abstract Iterable<Integer> pathTo(int v);
    
}
