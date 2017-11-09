import java.util.Arrays;

public class HashProbing<Item> {
    
    public static void main(String[] args) {
        System.out.println("N\tAvg\tStddev");
        for (int N = 1000; N <= 1000000; N*=10) {
            Integer[] a = new Integer[N];
            for (int i = 0; i < N/2; i++)
                insert(a, StdRandom.uniform(N), StdRandom.uniform(N));
            int[] probes = new int[1000];
            for (int j = 0; j < 1000; j++)
                probes[j] = mockProbe(a);
            System.out.println(N + "\t" +
                            StdStats.mean(probes) + "\t" +
                            StdStats.stddev(probes));
        }
    }
    
    private static void insert(Integer[] a, int key, int value) {
        for (int i = key; i < a.length; i++) {
            if (a[key] == null) {
                a[key] = value;
                return;
            } else
                key++;
            if (key > a.length-1)
                key = 0;
        }
    }
    
    //assumes key doesn't match
    private static int mockProbe(Integer[] a) {
         int i = StdRandom.uniform(a.length);
        int count = 0;
        while (a[i] != null || count < a.length-1) {
            i++;
            count++;
            if (i > a.length-1)
                i = 0;
        }
        if (a[i] != null)
            throw new RuntimeException("array is full");
        return count;
    }
}
