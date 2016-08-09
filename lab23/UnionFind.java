/** A simple implementation of the UnionFind abstract data structure with path
 *  compression. This UnionFind structure only holds integer and there are two
 *  critical operations: union and find. When unioning two elements, the element
 *  contained in a tree of smaller size is placed as a subtree to the root
 *  vertex of the larger tree. Meanwhile finding an element implements path
 *  compression. When a vertex an element is traversed, it is automatically
 *   connected to the root of that tree.
 *
 *  Using the union find data structure allows for a fast implementation of
 *  Kruskal's algorithm as well as other set based operations.
 *
 *  @author
 *  @since
 */
public class UnionFind {

    /** Instance variables go here? */
    int[] array;

    /** Returns a UnionFind data structure holding N vertices. Initially, all
     *  vertices are in disjoint sets. */
    public UnionFind(int n) {
        // TODO implement
        array = new int[n+1];
        for (int i = 0; i < array.length; i++) {
            array[i] = -1;
        }
    }

    /** Returns the size of the set V1 belongs to. */
    public int sizeOf(int v1) {
        // TODO implement
        return -array[find(v1)];
    }

    /** Returns true if nodes V1 and V2 are connected. */
    public boolean isConnected(int v1, int v2) {
        // TODO implement
        if (find(v1) == find(v2))
            return true;
        else return false;
    }

    /** Remember that each disjoint set is represented as a tree. Find returns
     *  the root of the set VERTEX belongs to. Path-compression, where the
     *  vertices along the search path from VERTEX to its root are linked
     *  directly to the root, is employed allowing for fast search-time. */
    public int find(int vertex) {
        // TODO implement
        if (array[vertex] < 0) return vertex;
        return find(array[vertex]);
    }

    /** Connects two elements V1 and V2 together in the UnionFind structure. V1
     *  and V2 can be any element and a union-by-size heurisitic is used. */
    public void union(int v1, int v2) {
        // TODO implement
        if (isConnected(v1,v2)) return;
        int size1 = -array[find(v1)];
        int size2 = -array[find(v2)];
        if (size1 >= size2) {
            array[v2] = find(v1);
            array[find(v1)] -= size2;
        } else {
            array[v1] = find(v2);
            array[find(v2)] -= size1;
        }
    }
}
