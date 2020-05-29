import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private int n ;
    private Item[] q;

    // construct an empty randomized queue
    public RandomizedQueue() {
        q = (Item[]) new Object[1];
        n=0;
    }

    /**Done*/
    // is the randomized queue empty?
    public boolean isEmpty() {return n==0; }

    /**Done*/
    // return the number of items on the randomized queue
    public int size() { return n;}

    /**Done*/
    // add the item
    public void enqueue(Item item) {
        if(item == null) throw new IllegalArgumentException();
        if(n==q.length) resize(q.length*2);
        q[n++] = item;
    }

    // remove and return a random item
    public Item dequeue() {
        if(isEmpty()) throw new NoSuchElementException();
        int rand = StdRandom.uniform(n);
        Item item = q[rand];
        if (n-1 == rand)
            q[rand]=null;
        else {q[rand]= q[n-1];
        q[n-1]=null; }
        --n;
        if(n>0 && n==q.length/4) resize(q.length/2);

        return  item;



    }

    /**Done*/
    // return a random item (but do not remove it)
    public Item sample(){
        if(isEmpty()) throw new NoSuchElementException();
        int rand = StdRandom.uniform(n);
        return q[rand];
    }

    /**Done*/
    private void resize(int capacity) {
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i<n; i++)
            copy[i] = q[i];
            q = copy;


    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() { return new RandomIterator();}

    private class RandomIterator implements Iterator<Item> {
        private int i = n;

        public boolean hasNext() {return i!=0;}
        public void remove() {throw new UnsupportedOperationException();}
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            int rand = StdRandom.uniform(i);
            Item forreturn = q[rand];
                if (rand==i-1) {
                    --i;
                    return forreturn;
                }
                else { q[rand] = q[i-1];
                       q[i-1]=forreturn;
                    --i;
                    return forreturn; }
        }
    }

    // unit testing (required)
    public static void main(String[] args){
        RandomizedQueue<String> raqu = new RandomizedQueue<>();
        while(!StdIn.isEmpty()) {
            String q = StdIn.readString();
            if (q.equals("2")) StdOut.println(raqu.dequeue());
            else if (q.equals("3")) StdOut.println(raqu.sample());
            else raqu.enqueue(q);
        }
        if(!raqu.isEmpty()) StdOut.println(raqu.size());
        for (String q : raqu)
            StdOut.println(q);
    }

}