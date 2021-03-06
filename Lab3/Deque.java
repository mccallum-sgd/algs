import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> {
    private Node first, last;
    private int n;

    private class Node {
        private Item item;
        private Node next, prev;

        private Node(Node prev, Item item, Node next) {
            this.prev = prev;
            this.item = item;
            this.next = next;
        }

        private void setNext(Node next) {
            this.next = next;
        }

        private void setPrev(Node prev) {
            this.prev = prev;
        }

        private boolean hasPrev() {
            return prev != null;
        }

        private boolean hasNext() {
            return next != null;
        }
    }

    /**
     * Initializes an empty queue.
     *
     */
    public Deque() {
    }

    public boolean isEmpty() {
        return n == 0;
    }

    /**
     * Returns the number of items in this deque.
     * 
     * @return the number of items
     */
    public int size() {
        return n;
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
        n++;
        if (last == null) last = first;
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
        n++;
        if (first == null) first = last;
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
            first.next.prev = null;
            Node oldFirst = first;
            first = first.next;
            oldFirst = null;
        } 
        else first = null;
        n--;
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
            last.prev.next = null;
            Node oldLast = last;
            last = last.prev;
            oldLast = null;
        } 
        else last = null;
        n--;
        return item;
    }

    /**
     * Returns an java.util.Iterator that iterates over the items in this deque
     * from front to end.
     * 
     * @return the java.util.Iterator
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

    public static void main(String[] args) {
        Deque<String> dq = new Deque<String>();
        dq.addFirst("3");
        dq.addFirst("2");
        dq.addFirst("1");
        dq.addLast("4");
        dq.addLast("5");
        Iterator<String> itr = dq.iterator();
        while (itr.hasNext())
            StdOut.print(itr.next() + " ");
        StdOut.println("\nExpected output is a list of integers, 1-5");
    }

}
