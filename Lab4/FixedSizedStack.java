import java.util.Iterator;
import java.util.NoSuchElementException;

public class FixedSizedStack<Item> implements Iterable<Item> {
    private Deque<Item> dq;
    private final int CAP;

    /**
     * Initializes a fixed stack with the specified {@code capacity}.
     * 
     * @param capacity
     *            - the capacity of the stack
     * @throws IllegalArgumentException
     *             - if the capacity is negative
     */
    public FixedSizedStack(int capacity) {
        if (capacity < 0)
            throw new IllegalArgumentException();
        dq = new Deque<Item>();
        this.CAP = capacity;
    }

    /**
     * Returns {@code true} if this stack contains no items.
     * 
     * @return true if this stack contains no elements
     */
    public boolean isEmpty() {
        return dq.isEmpty();
    }

    /**
     * Returns the number of items in this stack.
     * 
     * @return the number of items in this stack
     */
    public int size() {
        return dq.size();
    }

    /**
     * Pushes the specified {@code item} to the top of this stack.
     * 
     * @param item
     *            - the item to be added to this stack
     * @throws NullPointerException
     *             - if the item is null
     */
    public void push(Item item) {
        dq.addFirst(item);
        trim();
    }
    
    // remove last item if cap reached
    private void trim() {
        if (dq.size() > CAP)
            dq.removeLast();
        assert dq.size() <= CAP : dq.size();
    }

    /**
     * Removes and returns the {@code item} at the top of this stack.
     * 
     * @return the item at the top of this stack
     * @throws NoSuchElementException
     *             - if the stack is empty
     */
    public Item pop() {
        return dq.removeFirst();
    }

    /**
     * Returns an {@code java.util.Iterator} that iterates over the items in this stack
     * from top to bottom.
     * 
     * @return the iterator
     */
    public Iterator<Item> iterator() {
        return dq.iterator();
    }
    

    // Unit testing
    public static void main(String[] args) {
        FixedSizedStack<String> q = new FixedSizedStack<String>(2);
        String output = "[" + q.isEmpty() + ", ";
        q.push("one");
        q.push("two");
        q.push("three");
        q.push("four");
        Iterator<String> it = q.iterator();
        output += q.isEmpty() + ", " + q.size();
        while (it.hasNext())
            output += ", \"" + it.next() + "\"";
        while (!q.isEmpty())
            output += ", \"" + q.pop() + "\"";
        try {
            output += ", \"" + q.pop();
        }
        catch (NoSuchElementException e) {
            output += ", success";
        }
        output += ", " + q.isEmpty() + "]";
        StdOut.println("Expected: [true, false, 2, \"four\", \"three\", \"four\", \"three\", success, true]\n" +
                        "Output:   " + output);
        assert output.equals("[true, false, 2, \"four\", \"three\", \"four\", \"three\", success, true]") : output;
    }
    
}
