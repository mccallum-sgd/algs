/**
 * 
 * @author sean-mccallum
 *
 */
public class DataStats {

    private int count;
    private double[] values;
    
    /**
     * Initializes an array of size N and type double.
     * 
     * @param N amount of numbers you want to store
     */
    public DataStats(int N) {
        count = 0;
        values = new double[N];
    }
    
    /**
     * Computes the mean of the set of numbers added so far.
     * 
     * @return Mean of numbers added so far
     */
    public double mean() {
        double total = 0;
        for (int i = 0; i < count; i++)
            total += values[i];
        return total/count;
    }
    
    /**
     * Appends the specified number to the set.
     * 
     * @param in number to append
     * @return Mean of values added so far
     * @throws ArrayIndexOutOfBoundsException if no space for number to append
     */
    public void append(double in) {
        if (count < values.length) {
            values[count] = in;
            count++;
        } else throw new ArrayIndexOutOfBoundsException();
    }
}
