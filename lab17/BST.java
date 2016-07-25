import java.util.*;

public class BST {
    BSTNode root;

    public BST(LinkedList list) {
        root = linkedListToTree(list, list.size());
    }

    // Your comment here
    private BSTNode linkedListToTree (LinkedList iter, int n) {
        // YOUR CODE HERE
        if (n <= 0)
            return null;
        BSTNode left = linkedListToTree(iter, n/2);
        BSTNode root = new BSTNode();
        root.item = iter.get(0);

        root.left = left;
        iter.remove(0);
        root.right = linkedListToTree(iter,n-n/2-1);

        return root;
    }

    /**
     * Prints the tree to the console.
     */
    private void print() {
        print(root, 0);
    }

    private void print(BSTNode node, int d) {
        if (node == null) {
            return;
        }
        for (int i = 0; i < d; i++) {
            System.out.print("  ");
        }
        System.out.println(node.item);
        print(node.left, d + 1);
        print(node.right, d + 1);
    }

    /**
     * Node for BST.
     */
    static class BSTNode {

        /** Item. */
        Object item;

        /** Left child. */
        BSTNode left;

        /** Right child. */
        BSTNode right;
    }

    public static void main(String args[]) {
        LinkedList b = new LinkedList();
        b.add("1");
        b.add("2");
        b.add("3");
        b.add("4");
        b.add("5");
        b.add("6");
        b.add("7");
        BST a = new BST(b);
        a.print();
    }
}
