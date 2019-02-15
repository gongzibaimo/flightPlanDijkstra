package pj6_cs3345;
import java.util.Comparator;

public class Node implements Comparator<Node>{
    public String name;
    private int time;

    public String node;
    public boolean known;

    public Node(){

    }

    public Node(String name, int time){
        this.name = name;
        this.time = time;
    }

    @Override
    public int compare(Node v1, Node v2){
        if (v1.time < v2.time){
            return -1;
        }
        if (v1.time > v2.time){
            return 1;
        }
        return 0;
    }
}
