
package pj6_cs3345;
import java.util.PriorityQueue;
import java.util.*;

public class DijkstraAlgorithm {
    private HashMap<String, Integer> optimalDistancs;
    private HashMap<String, Integer> distances;
    private Set<String> setFlight;
    private List<Path> visitedNodesList;
    private PriorityQueue<Node> pq;
    private int optType, otherType;
    private String pathCostTime[][], source, destination;
    private List<String> nodeList;

    // prove the definition of the function to create priority queue of the nodes
    public DijkstraAlgorithm(List<String> nodeList, String time[][]){
        this.nodeList = nodeList;
        this.pathCostTime = time;
        optimalDistancs = new HashMap<String, Integer>();
        distances = new HashMap<String, Integer>();
        setFlight = new HashSet<String>();
        visitedNodesList = new ArrayList<Path>();
        pq = new PriorityQueue<Node>(new Node());
    }
    private void setTime(String time[][]){
        this.pathCostTime = time;
    }

    // Dijkstra, s algorithm definition
    public void dijkstra_algorithm(String time[][], String requestedPath[]){
        source = requestedPath[0];
        destination = requestedPath[1];
        setTime(time);
        if (requestedPath[2].equalsIgnoreCase("C")){
            optType = 2;
            otherType = 3;
        }
        else {
            optType = 3;
            otherType = 2;
        }

        for(String node: nodeList){
            optimalDistancs.put(node, Integer.MAX_VALUE);
            distances.put(node,Integer.MAX_VALUE);
        }

        pq.add(new Node(source, 0));
        optimalDistancs.replace(source, 0);
        distances.replace(source, 0);

        while(!pq.isEmpty()){
            String evaluationNode = findNode();
            Path evaluatedList = new Path();
            evaluatedList.setNewNode(evaluationNode);
            setFlight.add(evaluationNode);
            adjNode(evaluationNode, evaluatedList);
            if(!exist(visitedNodesList, evaluationNode)){
                visitedNodesList.add(evaluatedList);
            }
        }
    }

    private boolean exist(List<Path>visitedNode, String node){
        for(int i = 0; i < visitedNode.size(); i++) {
            if(visitedNode.get(i).equals(node)){
                return true;
            }
        }
        return false;
    }

    private String findNode(){
        String temp = pq.remove().name;
        return temp;
    }

    private void adjNode(String node, Path list){
        int edge = -1;
        int newDist = -1;

        for(int i = 0; i < pathCostTime.length; i ++){
            if(!pathCostTime[i][0].equals(node)){
                continue;
            }
            String dest;
            for(int n = 0; n < nodeList.size(); n ++) {
                dest = nodeList.get(n);
                if(!pathCostTime[i][1].equals(dest)) {
                    continue;
                }
                if (!setFlight.contains(dest)) {
                    edge = Integer.parseInt(pathCostTime[i][optType]);
                    newDist = optimalDistancs.get(node) + edge;
                    if (newDist < optimalDistancs.get(dest)) {
                        optimalDistancs.replace(dest, newDist);
                        distances.replace(dest, distances.get(node) + Integer.parseInt(pathCostTime[i][otherType]));
                        for(int j = 0; j < visitedNodesList.size(); j++){
                            if(visitedNodesList.get(j).isEmpty(dest)){
                                visitedNodesList.get(j).deleteNode(dest);
                            }
                            break;
                        }
                        list.addNode(dest);
                    }
                    pq.add(new Node(dest, optimalDistancs.get(dest)));
                }
            }
        }
    }

    public String getSource(){
        return source;
    }

    public List<Path> getVisitedNode(){
        return visitedNodesList;
    }

    public HashMap<String, Integer> getOptimalDistancs(){
        return  optimalDistancs;
    }

    public HashMap<String, Integer> getDistances(){
        return distances;
    }

    public String getDestination(){
        return destination;
    }
}
