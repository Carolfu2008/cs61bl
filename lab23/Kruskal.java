import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Queue;

/** A class that runs Kruskal's algorithm on a Graph. Given a graph G, Kruskal's
 *  algorithm constructs a new graph T such T is a spanning tree of G and the
 *  sum of its edge weights is less than the sum of the edge weights for
 *  every possible spanning tree T* of G. This is called the Minimum Spanning
 *  Tree (MST).
 *
 *  @author
 */
public class Kruskal {

    /** Returns the MST of INPUT using a naive isConnected implementation. */
    public static Graph minSpanTree(Graph input) {
        // TODO implement!
        Graph T = new Graph();
        for (Integer x : input.getAllVertices()) {
            T.addVertex(x);
        }
        for (Edge x : input.getAllEdges()) {
            if (!isConnected(T,x.getSource(),x.getDest())) {
                T.addEdge(x);
            }
        }
        return T;
    }

    /** Returns the MST of INPUT using the Union Find datastructure. */
    public static Graph minSpanTreeFast(Graph input) {
        // TODO implement!
        Graph T = new Graph();
        UnionFind p = new UnionFind(input.getAllVertices().size());
        for (Integer x :input.getAllVertices()){
            T.addVertex(x);
        }
        for (Edge x : input.getAllEdges()) {
            if (p.isConnected(x.getSource(),x.getDest())) {
                p.union(x.getSource(), x.getDest());
                T.addEdge(x);
            }
        }
        return T;
    }

    /** A naive implementation of BFS to check if two nodes are connected. */
    public static boolean isConnected(Graph g, int v1, int v2) {
        // TODO implement!
        Queue<Integer> fringe = new PriorityQueue<>();
        HashSet<Integer> visited = new HashSet<>();
        fringe.add(v1);
        while(!fringe.isEmpty()) {
            int v = fringe.poll();
            if (visited.contains(v)) {
                continue;
            }
            visited.add(v);
            if (v == v2) return true;
            for (int x : g.getNeighbors(v)) {
                if (!visited.contains(x)) {
                    fringe.add(x);
                }
            }
        }
        return false;
    }
}
