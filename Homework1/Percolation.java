import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
   
    private WeightedQuickUnionUF uf;
    
    private final int N;
    private boolean[][] grid;
    int count; //open sites
    
    public Percolation(int N) {
        if (N <= 0) throw new IllegalArgumentException("N must be a positive integer.");
        this.N = N;
        uf = new WeightedQuickUnionUF(N*N);
        grid = new boolean[N][N];
        count = 0;
    }
    
    public void open(int row, int col) {
        validateIndex(row, col);
        if (!isOpen(row, col)) {
            grid[row][col] = true;
            count++;
            connect(row, col);
        }
    }
    
    public boolean isOpen(int row, int col) {
        validateIndex(row, col);
        return grid[row][col] == true;
    }
    
    public boolean isFull(int row, int col) {
        validateIndex(row, col);
        if (!isOpen(row, col)) return false;
        //iterate through top row, checking for connections
        int pIndex = getLinearIndex(row, col);
        for (int i = 0; i < N; i++) {
            if (isOpen(0, i) && uf.connected(pIndex, getLinearIndex(0, i)))
                return true;
        }
        return false;
    }
    
    public int numberOfOpenSites() {
        return count;
    }
    
    public boolean percolates() {
        for (int i = 0; i < N; i++)
            if (isFull(N-1, i)) return true;
        return false;
    }
    
    private void connect(int row, int col) {
        int pIndex = getLinearIndex(row, col);
        connectIfOpen(pIndex, row-1, col);  //NORTH
        connectIfOpen(pIndex, row, col-1);  //WEST
        connectIfOpen(pIndex, row, col+1);   //EAST
        connectIfOpen(pIndex, row+1, col);   //SOUTH
    }
    
    private void connectIfOpen(int p, int row, int col) {
        if (validIndex(row, col) && isOpen(row, col)) {
            uf.union(p, getLinearIndex(row, col));
            //System.out.println("connected: [" + row + ", " + col + "]");
        }
    }
    
    private void validateIndex(int row, int col) {
        if (!validIndex(row, col))
            throw new IndexOutOfBoundsException(
                    "[" + row + ", " + col + "]: Not a valid index (must be 0 to N-1)."
                    );
    }
    
    private boolean validIndex(int row, int col) {
        return row >= 0 && row < N
                && col >= 0 && col < N;
    }
    
    private int getLinearIndex(int row, int col) {
        return row*N+col;
    }
    
    public String toString() {
        String str = "";
        for (int j = 0; j < grid.length; j++) {
            boolean[] bs = grid[j];
            str += j;
            for (boolean b: bs)
                if (b) str += "|";
                else str += "-";
            str += "\n";
        }
        return str;
    }
    
    public static void main(String[] args) {
        
    }

}
