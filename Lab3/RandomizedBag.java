import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedBag<Item> {

    private Item[] items;
    private int n;

    /**
     * Initializes an empty bag.
     */
    public RandomizedBag() {
        items = (Item[]) new Object[2];
    }

    /**
     * Returns true if this bag contains no items.
     * 
     * @return true if this bag contains no items
     */
    public boolean isEmpty() {
        return n == 0;
    }

    /**
     * Returns the number of items in this bag.
     * 
     * @return the number of items in this bag
     */
    public int size() {
        return n;
    }

    /**
     * Adds the specified item to this bag.
     * 
     * @param item
     *            - the item to add
     * @throws NullPointerException
     *             - if item is null
     */
    public void put(Item item) {
        if (item == null)
            throw new NullPointerException();
        if (n >= items.length)
            resize(items.length * 2);
        items[n++] = item;
    }

    /**
     * Removes and returns a uniformly random item from this bag.
     * 
     * @return the randomly removed item
     * @throws NoSuchElementException
     *             - if the bag is empty
     */
    public Item get() {
        if (isEmpty())
            throw new NoSuchElementException();
        int rand = StdRandom.uniform(n);
        Item item = backFill(rand);
        return item;
    }

    private Item backFill(int index) {
        Item item = items[index];
        if (n > 0) {
            items[index] = items[n - 1];
            items[--n] = null;
            if (items.length > n * 2)
                resize(items.length / 2);
        } 
        else items[--n] = null;
        return item;
    }

    // resize the underlying itemsrritemsy holding the elements
    private void resize(int capacity) {
        Item[] temp = (Item[]) new Object[capacity];
        for (int i = 0; i < n; i++)
            temp[i] = items[i];
        items = temp;
    }

    /**
     * Returns, but does not remove, a uniformly random item from this bag.
     * 
     * @return the random item
     * @throws NoSuchElementException
     *             - if the bag is empty
     */
    public Item sample() {
        if (isEmpty())
            throw new NoSuchElementException();
        return items[StdRandom.uniform(n)];
    }

    /**
     * Returns an independent iterator over items in this bag in random order.
     * 
     * @return iterator
     */
    public Iterator<Item> iterator() {
        return new BagIterator(items);
    }

    private class BagIterator implements Iterator<Item> {
        private Item[] elements;
        private int count;

        public BagIterator(Item[] items) {
            count = n;
            this.elements = (Item[]) new Object[count];
            for (int i = 0; i < n; i++)
                this.elements[i] = items[i];
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean hasNext() {
            return count > 0;
        }

        @Override
        public Item next() {
            if (!hasNext())
                throw new NoSuchElementException();
            return backFill(StdRandom.uniform(count));
        }

        private Item backFill(int index) {
            Item item = elements[index];
            if (count > 0) {
                elements[index] = elements[count - 1];
                elements[--count] = null;
                if (elements.length > count * 2)
                    resize(elements.length / 2);
            } 
            else elements[--count] = null;
            return item;
        }

        private void resize(int capacity) {
            Item[] temp = (Item[]) new Object[capacity];
            for (int i = 0; i < count; i++)
                temp[i] = elements[i];
            elements = temp;
        }

    }

    public static void main(String[] args) {
        RandomizedBag<String> bag = new RandomizedBag<String>();
        bag.put("1");
        bag.put("2");
        bag.put("3");
        bag.put("4");
        bag.put("5");
        Iterator<String> itr1 = bag.iterator(), itr2 = bag.iterator();
        StdOut.println("First iterator: ");
        while (itr1.hasNext())
            StdOut.print(itr1.next() + " ");
        StdOut.println("\nSecond iterator: ");
        while (itr2.hasNext())
            StdOut.print(itr2.next() + " ");
        StdOut.println("\nExpected output is two separate random lists of 5 integers.");
    }

}
