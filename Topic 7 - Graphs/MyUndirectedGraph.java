//Filip Lingefelt, fili8261
//Samuel Bakall, saba9460

package alda.graph;
import java.util.*;

 public class MyUndirectedGraph<T> implements UndirectedGraph<T> {


    HashMap<T, Node<T>> nodes = new HashMap<>();
    int numberOfEdges;

    @Override
    public int getNumberOfNodes() {
        return nodes.size();
    }

     @Override
     public int getNumberOfEdges() {
        return numberOfEdges;
    }

    @Override
    public boolean add(T newNode){
        if (nodes.containsKey(newNode))
            return false;
        nodes.putIfAbsent(newNode, new Node<>(newNode));
        return true;
    }

     @Override
     public boolean connect(T node1, T node2, int cost) {
         if (!nodes.containsKey(node1) || !nodes.containsKey(node2) || cost < 1) {
             return false;
         }
         Node<T> n1 = nodes.get(node1);
         Node<T> n2 = nodes.get(node2);
         n1.connections.put(n2, cost);
         n2.connections.put(n1, cost);
         numberOfEdges++;
         return true;
     }

     @Override
     public boolean isConnected(T node1, T node2) {
         Node<T> n1 = nodes.get(node1);
         Node<T> n2 = nodes.get(node2);
         if (n1 == null || n2 == null) {
             return false;
         }
         if(n1.connections.containsKey(n2) && n2.connections.containsKey(n1)){
             return true;
         }else{
             return false;
         }
     }

     @Override
     public int getCost(T node1, T node2) {
         Node<T> n1 = nodes.get(node1);
         Node<T> n2 = nodes.get(node2);
         if(n1 == null || n2 == null){
             return -1;
         }else if(!isConnected(node1, node2)){
             return -1;
         }else{
             return n1.connections.get(n2);
         }
     }
     @Override
     public List<T> depthFirstSearch(T start, T end) {
         List<T> list = new ArrayList<>();
         Stack<Node<T>> stack = new Stack<>();
         Set<Node<T>> visited = new HashSet<>();
         Node<T> startNode = nodes.get(start);
         Node<T> current;
         Node<T> endNode = nodes.get(end);
         Map<Node<T>, Node<T>> map = new HashMap<>();

         if (startNode == null || endNode == null) {
             return list;
         }

         stack.push(startNode);
         visited.add(startNode);

         while(!stack.isEmpty()){
             current = stack.pop();

             if(current == endNode){ //end
                 while(current != null){
                     list.add(0, current.data);
                     current = map.get(current);
                 }
                 return list;
             }

             for(Node<T> connection : current.connections.keySet()){
                 if(!visited.contains(connection)){
                     visited.add(connection);
                     stack.push(connection);
                     map.put(connection, current);
                 }
             }
         }
         return list;
     }
     @Override
     public List<T> breadthFirstSearch(T start, T end) {
         List<T> list = new ArrayList<>();
         Queue<Node<T>> queue = new LinkedList<>();
         Set<Node<T>> visited = new HashSet<>();
         Node<T> startNode = nodes.get(start);
         Node<T> current;
         Node<T> endNode = nodes.get(end);
         Map<Node<T>, Node<T>> map = new HashMap<>();

         if (startNode == null || endNode == null) {
             return list;
         }

         queue.add(startNode);
         visited.add(startNode);

         while(!queue.isEmpty()){
             current = queue.poll();

             if(current == endNode){
                 while(current != null){
                     list.add(0, current.data);
                     current = map.get(current);
                 }
                 return list;
             }

             for(Node<T> connection : current.connections.keySet()){
                 if(!visited.contains(connection)){
                     visited.add(connection);
                     queue.add(connection);
                     map.put(connection, current);
                 }
             }
         }
         return list;
     }

     @Override
     public UndirectedGraph<T> minimumSpanningTree(){
         MyUndirectedGraph<T> mst = new MyUndirectedGraph<>();
         PriorityQueue<Edge<T>> queue = new PriorityQueue<>();
         Set<T> visited = new HashSet<>();
         T startNodeData = null;
         Node<T> startNode = null;
         Node<T> to = null;
         Node<T> from = null;
         Edge<T> minEdge = null;
         Node<T> current = null;
         T currentData = null;

         // 1. Välj en nod, det spelar ingen roll vilken
         //2. Denna nod utgör startträdet
         // 3. Välj den billigaste bågen ut från trädet som inte leder till en cykel
         //4. Lägg till bågen och bågens andra nod i trädet
         // 5. Upprepa från steg 3 till alla noder finns i trädet

         for(Map.Entry<T, Node<T>> entry : nodes.entrySet()){
             startNodeData = entry.getKey();
             startNode = entry.getValue();
             break;
         }
         mst.add(startNodeData);

         for(Node<T> connection : startNode.connections.keySet()){
             Edge temp = new Edge<>(startNode.connections.get(connection), startNode, connection);
             queue.add(temp);
         }

         visited.add(startNodeData);

         while(!queue.isEmpty()){
             minEdge = queue.remove();
             from = minEdge.from;
             to = minEdge.to;

             if(!visited.contains(from.data) || !visited.contains(to.data)){
                 if(visited.contains(from.data)){
                     currentData = to.data;
                 }else{
                     currentData = from.data;
                 }
                 mst.add(currentData);
                 mst.connect(from.data, to.data, minEdge.cost);

                 current = nodes.get(currentData);
                 for(Node<T> connection : current.connections.keySet()){
                     Edge temp = new Edge<>(current.connections.get(connection), current, connection);
                     queue.add(temp);
                 }
                 visited.add(currentData);
             }
         }
         return mst;
     }

     private static class Edge<T> implements Comparable<Edge<T>> {
         Node<T> from;
         Node<T> to;
         int cost;

         Edge( int cost, Node<T> from, Node<T> to) {
             this.cost = cost;
             this.from = from;
             this.to = to;
         }

         public int compareTo(Edge<T> other) {
             return Integer.compare(this.cost, other.cost);
         }
     }

     private static class Node<T> {

         HashMap<Node<T>, Integer> connections = new HashMap<>();

         T data;

         Node(T data) {
             this.data = data;
         }

     }


 }

