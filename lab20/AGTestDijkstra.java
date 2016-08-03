import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import org.junit.Rule;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.rules.Timeout;

/** 
 * Lab 20 testing for the Dijkstra method in Graph.java.
 */
public class AGTestDijkstra {
	@Rule
	public Timeout globalTimeout = new Timeout(10000);

    /**
     *  Tests dijkstras on the graph given in lab21 under
     *  section "Exercise: A Sample Run of Dijkstra's Algorithm",
     *  looking at paths from 0 to all other vertices.
     */
    @Test
    public void test1() {
        Graph g = new Graph(5);
        g.addEdge(0, 1, 10);
        g.addEdge(0, 3, 30);
        g.addEdge(0, 4, 100);
        g.addEdge(1, 2, 50);
        g.addEdge(2, 4, 10);
        g.addEdge(3, 4, 60);
        g.addEdge(3, 2, 20);

        ArrayList<Integer> path0 = g.shortestPath(0, 1);
        ArrayList<Integer> path1 = g.shortestPath(0, 2);
        ArrayList<Integer> path2 = g.shortestPath(0, 3);
        ArrayList<Integer> path3 = g.shortestPath(0, 4);

        List<Integer> expected0 = Arrays.asList(0, 1);
        List<Integer> expected1 = Arrays.asList(0, 3, 2);
        List<Integer> expected2 = Arrays.asList(0, 3);
        List<Integer> expected3 = Arrays.asList(0, 3, 2, 4);

        assertEquals(expected0, path0);
        assertEquals(expected1, path1);
        assertEquals(expected2, path2);
        assertEquals(expected3, path3);
    }

    /**
     *  Tests Dijkstra's on the graph given in
     *  'Self-test: Finding a Shortest Path' in lab 21.
     */
    @Test
    public void test2() {
        Graph g = new Graph(5);
        g.addEdge(0, 1, 10);
        g.addEdge(0, 4, 5);
        g.addEdge(1, 2, 1);
        g.addEdge(1, 4, 2);
        g.addEdge(2, 3, 4);
        g.addEdge(3, 0, 7);
        g.addEdge(3, 2, 6);
        g.addEdge(4, 1, 3);
        g.addEdge(4, 2, 9);
        g.addEdge(4, 3, 2);

        ArrayList<Integer> path0 = g.shortestPath(0, 2);
        ArrayList<Integer> path1 = g.shortestPath(0, 1);

        List<Integer> expected0 = Arrays.asList(0, 4, 1, 2);
        List<Integer> expected1 = Arrays.asList(0, 4, 1);

        assertEquals(expected0, path0);
        assertEquals(expected1, path1);
    }

    /**
     *  Tests Dijkstra's algorithm on the graph given in
     *  Paul Hilfinger's Data Structures (into Java) book
     *  on page 235, from vertex 0 to every other vertex.
     */
    @Test
    public void test3() {
        Graph g = graph0();

        ArrayList<Integer> path0 = g.shortestPath(0, 1);
        ArrayList<Integer> path1 = g.shortestPath(0, 2);
        ArrayList<Integer> path2 = g.shortestPath(0, 3);
        ArrayList<Integer> path3 = g.shortestPath(0, 4);
        ArrayList<Integer> path4 = g.shortestPath(0, 5);
        ArrayList<Integer> path5 = g.shortestPath(0, 6);

        List<Integer> expected0 = Arrays.asList(0, 1);
        List<Integer> expected1 = Arrays.asList(0, 4, 3, 2);
        List<Integer> expected2 = Arrays.asList(0, 4, 3);
        List<Integer> expected3 = Arrays.asList(0, 4);
        List<Integer> expected4 = Arrays.asList(0, 4, 3, 5);
        List<Integer> expected5 = Arrays.asList(0, 6);

        assertEquals(expected0, path0);
        assertEquals(expected1, path1);
        assertEquals(expected2, path2);
        assertEquals(expected3, path3);
        assertEquals(expected4, path4);
        assertEquals(expected5, path5);
    }

    /**
     *  Tests Dijkstras on an undirected graph.
     */
    @Test
    public void test4() {
        Graph g = new Graph(6);
        g.addUndirectedEdge(0, 1, 7);
        g.addUndirectedEdge(0, 2, 9);
        g.addUndirectedEdge(0, 5, 14);
        g.addUndirectedEdge(1, 2, 10);
        g.addUndirectedEdge(1, 3, 15);
        g.addUndirectedEdge(2, 3, 11);
        g.addUndirectedEdge(2, 5, 2);
        g.addUndirectedEdge(3, 4, 6);
        g.addUndirectedEdge(4, 5, 9);

        ArrayList<Integer> path0 = g.shortestPath(0, 5);
        ArrayList<Integer> path1 = g.shortestPath(0, 2);
        ArrayList<Integer> path2 = g.shortestPath(0, 3);
        ArrayList<Integer> path3 = g.shortestPath(4, 0);

        List<Integer> expected0 = Arrays.asList(0, 2, 5);
        List<Integer> expected1 = Arrays.asList(0, 2);
        List<Integer> expected2 = Arrays.asList(0, 2, 3);
        List<Integer> expected3 = Arrays.asList(4, 5, 2, 0);

        assertEquals(expected0, path0);
        assertEquals(expected1, path1);
        assertEquals(expected2, path2);
        assertEquals(expected3, path3);
    }

    /**
     *  Returns the graph given in Paul Hilfinger's
     *  Data Structures (into Java) book on page 235.
     */
    private Graph graph0() {
        Graph g = new Graph(8);

        g.addUndirectedEdge(0, 1, 4);
        g.addUndirectedEdge(0, 4, 1);
        g.addUndirectedEdge(0, 6, 2);
        g.addUndirectedEdge(0, 7, 6);
        g.addUndirectedEdge(1, 2, 1);
        g.addUndirectedEdge(1, 3, 1);
        g.addUndirectedEdge(1, 4, 3);
        g.addUndirectedEdge(2, 3, 0);
        g.addUndirectedEdge(3, 4, 2);
        g.addUndirectedEdge(3, 5, 1);
        g.addUndirectedEdge(3, 6, 3);
        g.addUndirectedEdge(4, 6, 4);
        g.addUndirectedEdge(5, 6, 5);
        g.addUndirectedEdge(5, 7, 0);
        g.addUndirectedEdge(6, 7, 2);

        return g;
    }
}

