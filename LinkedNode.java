/**
 * Created by kevin on 2/24/2018.
 */
public class LinkedNode {
    private int data;
    public LinkedNode prev;
    public LinkedNode next;

    public LinkedNode(int data) {
        prev = null;
        this.data = data;
        next = null;
    }

    public LinkedNode(LinkedNode next, LinkedNode prev, int data) {
        this.next = next;
        this.prev = prev;
        this.data = data;
    }

    public int Data() {
        return data;
    }
}
