import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private Node first;
    private Node last;
    private int n;

    private class Node{
        Item item;
        Node next;
        Node before;
    }

    /**Done*/
    // construct an empty deque
    public Deque(){
        first = null;
        last = null;
        n = 0 ;
    }

    // is the deque empty?
    public boolean isEmpty(){
        return n==0;
    }

    /**Done*/
    // return the number of items on the deque
    public int size() {
        return n;
    }

    /**Done*/
    // add the item to the front
    public void addFirst(Item item) {
        if(item==null) throw new IllegalArgumentException();
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.before = null;
        if (oldfirst==null) first.next = null ;
        else { first.next = oldfirst;
        oldfirst.before = first; }
        if (last==null) last = first;
        n++;
    }

    /**Done*/
    // add the item to the back
    public void addLast(Item item) {
        if(item==null) throw new IllegalArgumentException();
        Node oldlast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        if(oldlast == null) last.before = null;
        else { last.before = oldlast;
        oldlast.next = last; }
        if (first == null) first = last;
        n++;
    }

     /**Done*/
    // remove and return the item from the front
    public Item removeFirst(){
        if(isEmpty()) throw new NoSuchElementException();
        Item item = first.item;
        first = first.next;
        if (first != null && first.before != null ) first.before = null;

        if (first == null) last = null;
        n--;
        return item;
    }

    /**Done*/
    // remove and return the item from the back
    public Item removeLast(){
        if(isEmpty()) throw new NoSuchElementException();
        Item item = last.item;
        last = last.before;
        if (last != null && last.next != null) last.next = null;

        if (last == null) first = null;
        n--;
        return item;

    }

        /**Done*/
    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {return new DequeIterator();}

    private class DequeIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() { return current!= null;}
        public void remove() {throw new UnsupportedOperationException();}
        public Item next(){
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        Deque<String> deq = new Deque<>();
        while(!StdIn.isEmpty()) {
            String d = StdIn.readString();
            if(d.equals("1")) deq.addFirst(d);
            if(d.equals("2")) deq.addLast(d);
            if(d.equals("3")) StdOut.println(deq.removeFirst());
            if(d.equals("4")) StdOut.println(deq.removeLast());
        }
        if(!deq.isEmpty()) StdOut.println(deq.size());
    for(String d : deq)
        StdOut.println(d);
    }

}