package assignment1.RankingSystem;

public class Node {
    int data;
    String ID;
    Node next;
    Node previous;
    public Node(int i,String ID)
    {
        this.ID=ID;
        this.data=i;
        this.next=null;
        this.previous=null;
    }
}
