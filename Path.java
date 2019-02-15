package pj6_cs3345;
import java.util.ArrayList;
import java.util.List;

public class Path {
     private List<String> path;
    private String newNode;

    public Path(){
        path = new ArrayList<String>();
    }

    public void setNewNode(String vertex){
        this.newNode = vertex;
    }

    public String getNewNode(){
        return newNode;
    }

    // check if the node is empty
    public Boolean isEmpty(String mnode){
        if(path.contains(mnode)){
            return true;
        }
        return false;
    }

    public void addNode(String node){
        path.add(node);
    }

    public void deleteNode(String node){
        path.remove(node);
    }
}
