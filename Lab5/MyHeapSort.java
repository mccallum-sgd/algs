import java.util.Comparator;
import java.util.Random;

import edu.princeton.cs.algs4.MinPQ;

public class MyHeapSort {
    
    public static <Item> void minSort(Item[] items, Comparator<Item> comparator) {
        MinPQ<Item> q = new MinPQ<Item>(comparator);
        for (Item i: items)
            q.insert(i);
        int count = 0;
        while (!q.isEmpty()) {
            items[count] = q.delMin();
            count++;
        }
        assert isSorted(items, comparator): items;
    }
    
    //For unit testing
    public static <Item> MinPQ<Item> minSortConstruction(Item[] items, Comparator<Item> comparator) {
        MinPQ<Item> q = new MinPQ<Item>(comparator);
        for (Item i: items)
            q.insert(i);
        return q;
    }
    
    //For unit testing
    public static <Item> void minSortRemoval(Item[] items, Comparator<Item> comparator, MinPQ<Item> q) {
        int count = 0;
        while (!q.isEmpty()) {
            items[count] = q.delMin();
            count++;
        }
        assert isSorted(items, comparator): items;
    }
    
    /**
     * Rearranges the array in ascending order, using the natural order.
     * @param pq the array to be sorted
     */
    public static void sort(Comparable[] pq) {
        int n = pq.length;
        for (int k = n/2; k >= 1; k--)
            sink(pq, k, n);
        while (n > 1) {
            exch(pq, 1, n--);
            sink(pq, 1, n);
        }
        assert isSorted(pq): pq;
    }
    
    // For Unit testing
    private static void sortConstruction(Comparable[] pq) {
        int n = pq.length;
        for (int k = n/2; k >= 1; k--)
            sink(pq, k, n);
    }
    
    //For unit testing
    private static void sortRepair(Comparable[] pq) {
        int n = pq.length;
        while (n > 1) {
            exch(pq, 1, n--);
            sink(pq, 1, n);
        }
    }
    
    private static void sink(Comparable[] pq, int k, int n) {
        while (2*k <= n) {
            int j = 2*k;
            if (j < n && less(pq, j, j+1)) j++;
            if (!less(pq, k, j)) break;
            exch(pq, k, j);
            k = j;
        }
    }
    
    private static void exch(Object[] pq, int i, int j) {
        Object swap = pq[i-1];
        pq[i-1] = pq[j-1];
        pq[j-1] = swap;
    }
    
    private static boolean less(Comparable[] pq, int i, int j) {
        return pq[i-1].compareTo(pq[j-1]) < 0;
    }
    
    
    private static <Item> boolean isSorted(Item[] a, Comparator<Item> comparator) {
        boolean sorted = true;
        for (int i = 0; i < a.length-1; i++)
            if (comparator.compare(a[i], a[i + 1]) > 0)
                sorted = false;
        return sorted;
    }
    
    private static boolean isSorted(Comparable[] a) {
        boolean sorted = true;
        for (int i = 0; i < a.length-1; i++)
            if (a[i].compareTo(a[i + 1]) > 0)
                sorted = false;
        return sorted;
    }
    
    public static void main(String[] args) {
        Random r = new Random();
        Comparator<Byte> compare = new Comparator<Byte>() {
            @Override
            public int compare(Byte o1, Byte o2) {
                return o1.compareTo(o2);
            }
        };
        for (int n = 10; n <= 1000000; n*=10) {
            System.out.println("N = " + n);
            int j = 0;
            Byte[] randArray = randArray(n);
            Byte[] randArray2 = randArray(n);
            
            //MINSORT
            long startMinC = System.nanoTime();
            MinPQ<Byte> q = minSortConstruction(randArray, compare);
            long minTimeConst = System.nanoTime()-startMinC;
            long startMinR = System.nanoTime();
            minSortRemoval(randArray, compare, q);
            long minTimeRemove = System.nanoTime()-startMinR;
            randArray = null;
            //HEAPSORT
            long startHeapC = System.nanoTime();
            sortConstruction(randArray2);
            long heapTimeConst = System.nanoTime()-startHeapC;
            long startHeapR = System.nanoTime();
            sortRepair(randArray2);
            long heapTimeRepair = System.nanoTime()-startHeapR;
            randArray2 = null;
            
            System.out.println("minSort const: " + minTimeConst/(j+1) + "ns, " + 
                            "minSort remove: " + minTimeRemove/(j+1) + "ns\n" +
                            "heapSort const: " + heapTimeConst/(j+1)+ "ns, " + 
                            "heapSort repair: " + heapTimeRepair/(j+1) + "ns\n");
        }
    }
    
    private static Byte[] randArray(int n) {
        Random r = new Random();
        byte[] randBytes = new byte[n];
        r.nextBytes(randBytes);
        Byte[] rand = new Byte[n];
        for (int i = 0; i < n; i++)
            rand[i] = randBytes[i];
        return rand;
    }
}
