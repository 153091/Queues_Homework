import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Permutation {
    public static void main(String[] args){
        int k = StdIn.readInt();
        RandomizedQueue<String> perm = new RandomizedQueue<>();
        while (!StdIn.isEmpty()){
            String p = StdIn.readString();
            perm.enqueue(p);
        }
        for (int i=0; i<k; i++)
            StdOut.println(perm.dequeue());
    }
}