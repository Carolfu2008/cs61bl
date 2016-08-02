import javax.xml.soap.Node;
import java.util.*;

public class Graph implements Iterable<Integer>{

    private LinkedList<Edge>[] adjLists;
    private int vertexCount;
    private int startVertex;

    // Initialize a graph with the given number of vertices and no edges.
    public Graph(int numVertices) {
        adjLists = new LinkedList[numVertices];
        startVertex = 0;
        for (int k = 0; k < numVertices; k++) {
            adjLists[k] = new LinkedList<Edge>();
        }
        vertexCount = numVertices;
    }

    // Change the vertex the iterator will start DFS from
    public void setStartVertex(int v){
        if (v < vertexCount && v >= 0){
            startVertex = v;
        } else {
            throw new IllegalArgumentException("Cannot set iteration start vertex to " + v + ".");
        }
    }


    // Add to the graph a directed edge from vertex v1 to vertex v2.
    public void addEdge(int v1, int v2) {
        addEdge(v1, v2, null);
    }

    // Add to the graph an undirected edge from vertex v1 to vertex v2.
    public void addUndirectedEdge(int v1, int v2) {
        addUndirectedEdge(v1, v2, null);
    }

    // Add to the graph a directed edge from vertex v1 to vertex v2,
    // with the given edge information.
    public void addEdge(int v1, int v2, Object edgeInfo) {
        //your code here
        adjLists[v1].add(new Edge(v1,v2,edgeInfo));
    }

    // Add to the graph an undirected edge from vertex v1 to vertex v2,
    // with the given edge information.
    public void addUndirectedEdge(int v1, int v2, Object edgeInfo) {
        //your code here
        adjLists[v1].add(new Edge(v1,v2,edgeInfo));
        adjLists[v2].add(new Edge(v2,v1,edgeInfo));
    }

    // Return true if there is an edge from vertex "from" to vertex "to";
    // return false otherwise.
    public boolean isAdjacent(int from, int to) {
        //your code here
        for (Edge x : adjLists[from]){
            if (x.to == to){
                return true;
            }
        }
        return false;
    }

    // Returns a list of all the neighboring  vertices 'u'
    // such that the edge (VERTEX, 'u') exists in this graph.
    public List neighbors(int vertex) {
        // your code here
        ArrayList<Edge> rtn = new ArrayList<>();
        for (int i = 0; i < adjLists.length; i++) {
            for (Edge x : adjLists[i]) {
                if (vertex == x.to) {
                    rtn.add(x);
                }
            }
        }
        return rtn;
    }

    // Return the number of incoming vertices for the given vertex,
    // i.e. the number of vertices v such that (v, vertex) is an edge.
    public int inDegree(int vertex) {
        int count = 0;
        //your code here
        for (int i = 0; i <  adjLists.length; i++) {
            for (int j = 0; j < adjLists[i].size(); j++) {
                if (adjLists[i].get(j).to == vertex) {
                    count++;
                }
            }
        }
        return count;
    }

    public Iterator<Integer> iterator(){
        return new TopologicalIterator();
    }

    // A class that iterates through the vertices of this graph, starting with a given vertex.
    // Does not necessarily iterate through all vertices in the graph: if the iteration starts
    // at a vertex v, and there is no path from v to a vertex w, then the iteration will not
    // include w
    private class DFSIterator implements Iterator<Integer> {

        private Stack<Integer> fringe;
        private HashSet<Integer> visited;

        public DFSIterator(Integer start) {
            //your code here
            fringe = new Stack<>();
            visited = new HashSet<>();
            fringe.push(start);
        }

        public boolean hasNext() {
            //your code here
            return !fringe.empty();
        }

        public Integer next() {
            //your code here
                if (fringe.empty()){
                    throw new NoSuchElementException();
                }
                Integer v = fringe.pop();
                while (fringe.contains(v)) {
                    fringe.remove(v);
                }
                visited.add(v);
                for (Edge a : adjLists[v]){
                    if (!visited.contains(a.to)) {
                        fringe.push(a.to);
                    }
                }
                return v;
        }

        //ignore this method
        public void remove() {
            throw new UnsupportedOperationException(
                    "vertex removal not implemented");
        }

    }

    // Return the collected result of iterating through this graph's
    // vertices as an ArrayList.
    public ArrayList<Integer> visitAll(int startVertex) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        Iterator<Integer> iter = new DFSIterator(startVertex);

        while (iter.hasNext()) {
            result.add(iter.next());
        }
        return result;
    }

    // Returns true iff there exists a path from STARVETEX to
    // STOPVERTEX. Assumes both STARTVERTEX and STOPVERTEX are
    // in this graph. If STARVERTEX == STOPVERTEX, returns true.
    public boolean pathExists(int startVertex, int stopVertex) {
        // your code here
        if (startVertex == stopVertex){
            return true;
        }
        ArrayList<Integer> result = visitAll(startVertex);
        return result.contains(stopVertex);
    }


    // Returns the path from startVertex to stopVertex.
    // If no path exists, returns an empty arrayList.
    // If startVertex == stopVertex, returns a one element arrayList.
    public ArrayList<Integer> path(int startVertex, int stopVertex) {
        ArrayList<Integer> rtn = new ArrayList<>();
        if (!pathExists(startVertex,stopVertex)){
            return rtn;
        }
        ArrayList<Integer> visited = new ArrayList<>();
        helper(startVertex,stopVertex,rtn,visited);
        rtn.add(stopVertex);
        return rtn;
        // you supply the body of this method
    }
    private boolean helper(int startVertex, int stopVertex,ArrayList<Integer> rtn,ArrayList<Integer> visited){
        visited.add(stopVertex);
        for (Object x : neighbors(stopVertex)) {
            if (!visited.contains(((Edge) x).from)&&helper(startVertex, ((Edge) x).from, rtn, visited)) {
               rtn.add(((Edge) x).from);
                return true;
            }
        }
        return startVertex == stopVertex; 
    }

    public ArrayList<Integer> topologicalSort() {
        ArrayList<Integer> result = new ArrayList<Integer>();
        Iterator<Integer> iter = new TopologicalIterator();
        while (iter.hasNext()) {
            result.add(iter.next());
        }
        return result;
    }

    private class TopologicalIterator implements Iterator<Integer> {

        private Stack<Integer> fringe;
        private int[] currentInDegree= new int[adjLists.length] ;
        // more instance variables go here

        public TopologicalIterator() {
            fringe = new Stack<Integer>();
            for (int i = 0;i < adjLists.length;i++){
                currentInDegree[i] = inDegree(i);
                if (currentInDegree[i] == 0){
                    fringe.push(i);
                    currentInDegree[i]--;
                }
            }
            // more statements go here
        }

        public boolean hasNext() {
            return !fringe.isEmpty();
        }

        public Integer next() {
            Integer v = fringe.pop();
            for (Edge x: adjLists[v]){
                currentInDegree[x.to]--;
            }
            for (int i = 0;i < adjLists.length; i++){
                if (currentInDegree[i] == 0){
                    fringe.push(i);
                    currentInDegree[i]--;
                }
            }
            return v;
            // you supply the real body of this method
        }

        public void remove() {
            throw new UnsupportedOperationException(
                    "vertex removal not implemented");
        }

    }

    public ArrayList<Integer> shortestPath (int startVertex, int endVertex){
        //your code here...
        Queue<AA> fringe = new PriorityQueue<>(vertexCount,new NodeComparator());
        ArrayList<Integer> rtn = new ArrayList<>();
        int d[] = new int [vertexCount];
        int from[] = new int[vertexCount];
        HashSet<Integer> done = new HashSet<>();
        fringe.add(new AA(startVertex,0));
        for (int i = 0 ;i< vertexCount;i++){
            d[i] =Integer.MAX_VALUE;
            from[i] = -1;
        }
        d[startVertex] = 0;
        while (!fringe.isEmpty()) {
            AA value = fringe.poll();
            if (done.contains(value.item)) continue;
            if (value.item == endVertex) {
                int p = value.item;
                while (startVertex != p) {
                    rtn.add(0,p);
                    p = from[p];
                }
                rtn.add(0,p);
                return rtn;
            }
            done.add(value.item);
            for (Edge x : adjLists[value.item]){
                int distance = 1 + value.distance;
                if (!done.contains(x.to) && distance < d[x.to]) {
                    fringe.add(new AA(x.to,distance));
                    d[x.to] = distance;
                    from[x.to] = x.from;
                }
            }

        }
        return rtn;
    }
    public class NodeComparator implements Comparator<AA>
    {
        @Override
        public int compare(AA o1, AA o2) {
            if (o1.distance < o2.distance)
                return -1;
            else if (o1.distance > o2.distance)
                return 1;
            else return 0;
        }
    }
    private class AA {
        private int item;
        private int distance;

        public AA(int item, int distance) {
            this.item = item;
            this.distance = distance;
        }
    }
    private class Edge {
        private Integer from;
        private Integer to;
        private Object edgeInfo;
        private Integer weight = 1;

        public Edge(int from, int to, Object info) {
            this.from = new Integer(from);
            this.to = new Integer(to);
            edgeInfo = info;
        }

        public Integer to() {
            return to;
        }

        public Object info() {
            return edgeInfo;
        }

        public String toString() {
            return "(" + from + "," + to + ",dist=" + edgeInfo + ")";
        }

    }


    public static void main(String[] args) {
        ArrayList<Integer> result;

        Graph g1 = new Graph(5);
        g1.addEdge(0, 1);
        g1.addEdge(0, 2);
        g1.addEdge(0, 4);
        g1.addEdge(1, 2);
        g1.addEdge(2, 0);
        g1.addEdge(2, 3);
        g1.addEdge(4, 3);
        System.out.println("Traversal starting at 0");
        result = g1.visitAll(0);
        Iterator<Integer> iter;
        iter = result.iterator();
        while (iter.hasNext()) {
            System.out.println(iter.next() + " ");
        }
        System.out.println();
        System.out.println();
        System.out.println("Traversal starting at 2");
        result = g1.visitAll(2);
        iter = result.iterator();
        while (iter.hasNext()) {
            System.out.println(iter.next() + " ");
        }
        System.out.println();
        System.out.println();
        System.out.println("Traversal starting at 3");
        result = g1.visitAll(3);
        iter = result.iterator();
        while (iter.hasNext()) {
            System.out.println(iter.next() + " ");
        }
        System.out.println();
        System.out.println();
        System.out.println("Traversal starting at 4");
        result = g1.visitAll(4);
        iter = result.iterator();
        while (iter.hasNext()) {
            System.out.println(iter.next() + " ");
        }
        System.out.println();
        System.out.println();
        System.out.println("Path from 0 to 3");
        result = g1.shortestPath(0, 3);
        iter = result.iterator();
        while (iter.hasNext()) {
            System.out.println(iter.next() + " ");
        }
        System.out.println();
        System.out.println();
        System.out.println("Path from 0 to 4");
        result = g1.shortestPath(0, 4);
        iter = result.iterator();
        while (iter.hasNext()) {
            System.out.println(iter.next() + " ");
        }
        System.out.println();
        System.out.println();
        System.out.println("Path from 1 to 3");
        result = g1.shortestPath(1, 3);
        iter = result.iterator();
        while (iter.hasNext()) {
            System.out.println(iter.next() + " ");
        }
        System.out.println();
        System.out.println();
        System.out.println("Path from 1 to 4");
        result = g1.shortestPath(1, 4);
        iter = result.iterator();
        while (iter.hasNext()) {
            System.out.println(iter.next() + " ");
        }
        System.out.println();
        System.out.println();
        System.out.println("Path from 4 to 0");
        result = g1.shortestPath(4, 0);
        if (result.size() != 0) {
            System.out.println("*** should be no path!");
        }

        Graph g2 = new Graph(5);
        g2.addEdge(0, 1);
        g2.addEdge(0, 2);
        g2.addEdge(0, 4);
        g2.addEdge(1, 2);
        g2.addEdge(2, 3);
        g2.addEdge(4, 3);
        System.out.println();
        System.out.println();
        System.out.println("Topological sort");
        result = g2.topologicalSort();
        iter = result.iterator();
        while (iter.hasNext()) {
            System.out.println(iter.next() + " ");
        }
    }

}
