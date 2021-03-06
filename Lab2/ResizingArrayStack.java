/******************************************************************************
 *  Compilation:  javac ResizingArrayStack.java
 *  Execution:    java ResizingArrayStack < input.txt
 *  Dependencies: StdIn.java StdOut.java
 *  Data files:   http://algs4.cs.princeton.edu/13stacks/tobe.txt
 *  
 *  Stack implementation with a resizing array.
 *
 *  % java ResizingArrayStack < tobe.txt
 *  to be not that or be (2 left on stack)
 *
 ******************************************************************************/

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ResizingArrayStack<Item extends Object> implements Iterable<Item> {
    private Item[] a;         // array of items
    private int n;            // number of elements on stack

    /**
     * Initializes an empty stack.
     * 
     */
    @SuppressWarnings("unchecked")
    public ResizingArrayStack() {
        /* why is this Object type necessary?
        *  To cover the case of the generic type being a primitive 
        *  in which case the compiler will now autobox the primitive
        *  with the appropriate wrapper (so we can use object references).
        *  
        *  I actually didn't know that the compiler does this 
        *  automatically  until now (hence the "auto" in 
        *  autoboxing.. duh), I was thinking I would have to 
        *  restrict this implementation to objects only or 
        *  manually add the wrappers myself (whew!).
        */
        a = (Item[]) new Object[2];
        // any other initializations? 
        // no, javac will initialize n to 0 for me :)
    }

    /**
     * Returns true if this stack contains no elements.
     * 
     * @return true if this stack contains no elements
     */
    public boolean isEmpty() {
        return n == 0;
    }

    /**
     * Returns the number of items in the stack.
     * 
     * @return the number of items in the stack
     */
    public int size() {
        return n;
    }


    // resize the underlying array holding the elements
    @SuppressWarnings("unchecked")
    private synchronized void resize(int capacity) {
        // textbook implementation
        // that's your new item array of size "capacity"
        Item[] temp = (Item[]) new Object[capacity];
        /* how to copy contents of _a_ into temp,
         * this would be simpler and faster:
         * System.arraycopy(a, 0, temp, 0, n); ,
         * but for sake of implemenation...
         */
        for (int i = 0; i < n-1; i++)
            temp[i] = a[i];
        // then make temp the active array?
        a = temp;
    }

    /**
     * Adds the item to this stack.
     */
    public synchronized void push(Item item) {
        // Watch out for the special case of running out of space ( double size of array if necessary)
        if (n >= a.length) resize(a.length*2);
        // How to stick the item at the end of the list?
        a[n++] = item;
    }

    /**
     * Removes and returns the item most recently added to this stack.
     */
    public synchronized Item pop() {
        // How do you get the item from the end of the list, 
        Item oldItem = a[n-1];
        // How to avoid loitering?
        a[n--] = null;
        // when to shrink allocated array?
        if (a.length >= n*4) resize(a.length/2); // I hold this truth to be self-evident: all array accesses are created equal
        return oldItem;
    }


    /**
     * Returns a java.util.Iterator to this stack that iterates through the items in LIFO order.
     */
    public Iterator<Item> iterator() {
        return new ReverseArrayIterator();
    }

    // an iterator, doesn't implement remove() since it's optional
    private class ReverseArrayIterator implements Iterator<Item> {
        private int i;

        public ReverseArrayIterator() {
            i = n-1;  // doing this because we're looking starting at the top of the stack
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
        
        public boolean hasNext() {
            return a[i+1] != null;
        }

        public Item next() {
            // how to walk along the list, starting from "top" of stack ?
            // watch out for next() call when there is no next item
            if (!hasNext()) throw new NoSuchElementException();
            return a[++i];
        }
    }


    /**
     * Unit tests the {@code Stack} data type.
     */
    public static void main(String[] args) {
        ResizingArrayStack<String> stack = new ResizingArrayStack<String>();
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (!item.equals("-")) stack.push(item);
            else if (!stack.isEmpty()) StdOut.print(stack.pop() + " ");
        }
        StdOut.println("(" + stack.size() + " left on stack)");
    }
}
