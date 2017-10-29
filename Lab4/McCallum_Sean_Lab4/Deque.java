import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private Node first, last;
    private int size;

    // Node helper class
    private class Node {
        private Item item;
        private Node next, prev;

        private Node(Node prev, Item item, Node next) {
            this.prev = prev;
            this.item = item;
            this.next = next;
        }

        private void setNext(Node next) 
        { this.next = next; }

        private void setPrev(Node prev) 
        { this.prev = prev; }

        private boolean hasPrev() 
        { return prev != null; }

        private boolean hasNext() 
        { return next != null; }
    }

    /**
     * Initializes an empty queue.
     *
     */
    public Deque() { }

    /**
     * 
     * @return
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns the number of items in this deque.
     * 
     * @return the number of items
     */
    public int size() {
        return size;
    }

    /**
     * Adds the specified item to the front of this deque.
     * 
     * @param item
     *            - the item to add
     * @throws NullPointerException
     *             - if the item is null
     */
    public void addFirst(Item item) {
        if (item == null)
            throw new NullPointerException();
        if (first != null) {
            first.setPrev(new Node(null, item, first));
            first = first.prev;
        } 
        else first = new Node(null, item, last);
        size++;
        if (last == null) last = first;
        assert first.item.equals(item) : first.item;
    }

    /**
     * Adds the specified item to the end of this deque.
     * 
     * @param item
     *            - the item to add
     * @throws NullPointerException
     *             - if the item is null
     */
    public void addLast(Item item) {
        if (item == null)
            throw new NullPointerException();
        if (last != null) {
            last.setNext(new Node(last, item, null));
            last = last.next;
        } 
        else last = new Node(first, item, null);
        size++;
        if (first == null) first = last;
        assert last.item.equals(item) : last.item;
    }

    /**
     * Removes and returns the first item in this deque.
     * 
     * @return the first item
     * @throws NoSuchElementException
     *             - if the deque is empty
     */
    public Item removeFirst() {
        if (isEmpty())
            throw new NoSuchElementException();
        Item item = first.item;
        if (first.hasNext()) {
            Node oldFirst = first;
            first = first.next;
            first.prev = null;
            oldFirst = null;
        } 
        else first = null;
        size--;
        assert item != null : item;
        assert first.prev == null : first.prev;
        return item;
    }

    /**
     * Removes and returns the last item in this deque.
     * 
     * @return the last item
     * @throws NoSuchElementException
     *             - if the deque is empty
     */
    public Item removeLast() {
        if (isEmpty())
            throw new NoSuchElementException();
        Item item = last.item;
        if (last.hasPrev()) {
            Node oldLast = last;
            last = last.prev;
            last.next = null;
            oldLast = null;
        } 
        else last = null;
        size--;
        assert item != null : item;
        assert last.next == null : last.next;
        return item;
    }

    /**
     * Returns an java.util.Iterator that iterates over the items in this deque
     * from front to end.
     * 
     * @return the iterator
     */
    public Iterator<Item> iterator() {
        return new QueueIterator();
    }

    private class QueueIterator implements Iterator<Item> {
        private Node current;

        public QueueIterator() {
            current = new Node(null, null, first); // dummy node to get started
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public boolean hasNext() {
            return current.hasNext();
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            current = current.next;
            return current.item;
        }
    }

    
    // Unit testing
    public static void main(String[] args) {
        Deque<String> dq = new Deque<String>();
        String output = "[" + dq.isEmpty();
        dq.addFirst("one");
        dq.addFirst("zero");
        dq.addLast("two");
        dq.addLast("three");
        dq.addLast("four");
        dq.removeFirst();
        dq.removeLast();
        Iterator<String> itr = dq.iterator();
        output += ", " + dq.isEmpty() + ", " + dq.size();
        while (itr.hasNext())
            output += ", \"" + itr.next() + "\"";
        output += "]";
        StdOut.println("Expected: [true, false, 3, \"one\", \"two\", \"three\"]\n" +
                        "Output:   " + output);
        assert output.equals("[true, false, 3, \"one\", \"two\", \"three\"]") : output;
    }

}
