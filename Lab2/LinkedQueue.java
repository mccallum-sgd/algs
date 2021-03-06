/******************************************************************************
 *  Compilation:  javac LinkedQueue.java
 *  Execution:    java LinkedQueue < input.txt
 *  Dependencies: StdIn.java StdOut.java
 *  Data files:   http://algs4.cs.princeton.edu/13stacks/tobe.txt  
 *
 *  A generic queue, implemented using a singly-linked list.
 *
 *  % java Queue < tobe.txt 
 *  to be or not to be (2 left on queue)
 *
 ******************************************************************************/

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedQueue<Item> implements Iterable<Item> {
    private int n;         // number of elements in queue
    private Node first;    // front of queue
    private Node last;     // back of queue

    // helper linked list class
    private class Node {
        private Item item;
        private Node next;
        
        private Node (Item item, Node next) {
            this.item = item;
            this.next = next;
        }
        
        private void setNext(Node next) {this.next = next;}
        private boolean hasNext() {return next != null;}
    }
    
    /**
     * Initializes an empty queue.
     *
     */
    public LinkedQueue() {
        /*
         * javac will set the initial values to what I need --
         * would've used the default constructor but I'm not 
         * sure if it includes the documentation (I would
         * assume not)
         */
    }
    
    /**
     * Returns true if this queue contains no elements.
     * 
     * @return true if this queue contains no elements
     */
    public boolean isEmpty() {
        return first == null;
    }

    /**
     * Returns the number of items in this queue.
     * 
     * @return the number of items in this queue
     */
    public int size() {
        return n;
    }


    /**
     * Adds the item to this queue.
     */
    public synchronized void enqueue(Item item) {
        Node oldLast;
        if (last != null) {
            oldLast = last;
            last = new Node(item, null);
            if (isEmpty()) first = last;
            else oldLast.setNext(last);
        } else last = new Node(item, null);
        n++;
    }

    /**
     * Removes and returns the item in this queue that was least recently added.
     * 
     * @return item that will be removed
     * @throws java.util.NoSuchElementException if this queue is empty
     */
    public synchronized Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException();
        Item oldItem = first.item;
        first = first.next;
        n--;
        return oldItem;
    }

    /**
     * Returns an java.util.Iterator that iterates over the items in this queue in FIFO order.
     * 
     */
    public Iterator<Item> iterator()  {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        private Node current = first;

        public void remove()      { throw new UnsupportedOperationException();  }
        
        public boolean hasNext()  { 
            return current.next != null;              
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            return (current = current.next).item;
        }
    }


    /**
     * Unit tests the LinkedQueue data type.
     */
    public static void main(String[] args) {
        LinkedQueue<String> queue = new LinkedQueue<String>();
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (!item.equals("-"))
                queue.enqueue(item);
            else if (!queue.isEmpty())
                StdOut.print(queue.dequeue() + " ");
        }
        StdOut.println("(" + queue.size() + " left on queue)");
    }
}
