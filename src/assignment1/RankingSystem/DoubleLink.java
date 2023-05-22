package assignment1.RankingSystem;


import java.util.ArrayList;
import java.util.List;

public class DoubleLink {
    private Node first,last;
    private int size;
    public DoubleLink()
    {
        this.first=null;
        this.last=null;
        this.size=0;
    }
    public Node first()
    {
        return this.first;
    }
    public Node last()
    {
        return this.last;
    }
    public int size()
    {
        return this.size;
    }
    public boolean isEmpty()
    {
        if(size()==0)
        {
            return true;
        }
        return false;
    }
    public Node after(Node p)
    {
        return p.next;
    }
    public Node before(Node p)
    {
        return p.previous;
    }

    public Node insertFirst(int d,String ID)
    {
        Node add=new Node(d,ID);
        if(isEmpty())
        {
            this.first=add;
            this.last=add;
        }
        else
        {
            this.first.previous=add;
            add.next=this.first;
            this.first=add;
        }
        this.size++;
        return this.first;
    }
    public Node insertLast(int d,String ID)
    {
        Node add=new Node(d,ID);
        if(isEmpty())
        {
            this.first=add;
            this.last=add;
        }
        else
        {
            this.last.next=add;
            add.previous=this.last;
            this.last=add;
        }
        this.size++;
        return this.last;
    }
    public Node insertAfter(Node p,int d,String ID)
    {
        Node add=new Node(d,ID);
        if(after(p)!=null)
        {
            after(p).previous=add;
            add.next=after(p);
        }
        else{
            this.last=add;
        }
        p.next=add;
        add.previous=p;
        this.size++;
        return after(p);
    }
    public Node insertBefore(Node p,int d,String ID)
    {
        Node add=new Node(d,ID);
        if(before(p)!=null)
        {
            before(p).next=add;
            add.previous=before(p);
        }
        else {
            this.first = add;
        }
        add.next = p;
        p.previous = add;
        this.size++;
        return before(p);
    }
    public int remove(Node p)
    {
        int d=p.data;
        if(after(p)!=null)
        {
            after(p).previous=before(p);
        }
        else
        {
            this.last=before(p);
        }
        if(before(p)!=null)
        {
            before(p).next=after(p);
        }
        else
        {
            this.first=after(p);
        }
        size--;
        return d;
    }
    public Node insertRank(int d,String ID)
    {
        Node p=this.first;
        while(p!=null)
        {
            if(p.data<d)
            {
                return insertBefore(p,d,ID);
            }
            p=p.next;
        }
        return insertLast(d,ID);
    }

    public void printList()
    {
        Node now=this.first;
        System.out.print("The Rank in the list for top to bottom:\n");
        while(now!=null)
        {
            System.out.print("USERID: "+now.ID+", Rank: "+now.data+"\n");
            now=now.next;
        }
        System.out.println();
    }

    public String getString()
    {
        Node now=this.first;
        List<String> lis = new ArrayList<>();
        lis.add("Leaderboard User order from top to bottom:\n");
        while(now!=null)
        {
            lis.add("USERID: "+now.ID+", Rank point: "+now.data+"\n");
            now=now.next;
        }
        String listString = String.join(" - ", lis);
        return listString;
    }
}
