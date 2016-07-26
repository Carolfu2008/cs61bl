import java.lang.reflect.Array;

/**
 * Simple Red-Black tree implementation.
 *
 * @param <T> type of items.
 */
public class RedBlackTree<T extends Comparable<T>> {

    /** Root of the tree. */
    RBTreeNode<T> root;

    /**
     * Empty constructor.
     */
    public RedBlackTree() {
        root = null;
    }

    /**
     * Constructor that builds this from given BTree (2-3-4) tree.
     *
     * @param tree BTree (2-3-4 tree).
     */
    public RedBlackTree(BTree<T> tree) {
        BTree.Node<T> btreeRoot = tree.root;
        root = buildRedBlackTree(btreeRoot);
    }

    /**
     * Builds a RedBlack tree that has isometry with given 2-3-4 tree rooted at
     * given node r, and returns the root node.
     *
     * @param r root of the 2-3-4 tree.
     * @return root of the Red-Black tree for given 2-3-4 tree.
     */
    RBTreeNode<T> buildRedBlackTree(BTree.Node<T> r) {
        // YOUR CODE HERE
        RBTreeNode rtn = null;
        if (r == null){
            rtn = null;
        } else if (r.getItemCount() == 3){
            rtn = new RBTreeNode(true,r.getItemAt(1));
            rtn.left = new RBTreeNode(false,r.getItemAt(0),buildRedBlackTree(r.getChildAt(0)),buildRedBlackTree(r.getChildAt(1)));
            rtn.right = new RBTreeNode(false,r.getItemAt(2),buildRedBlackTree(r.getChildAt(2)),buildRedBlackTree(r.getChildAt(3)));
        } else if (r.getItemCount() == 2) {
            rtn = new RBTreeNode(true,r.getItemAt(0),buildRedBlackTree(r.getChildAt(0)),null);
            rtn.right = new RBTreeNode(false,r.getItemAt(1),buildRedBlackTree(r.getChildAt(1)),buildRedBlackTree(r.getChildAt(2)));
        } else if (r.getItemCount() == 1) {
            rtn = new RBTreeNode(true,r.getItemAt(0),buildRedBlackTree(r.getChildAt(0)),buildRedBlackTree(r.getChildAt(1)));
        }
        return rtn;
    }
    public static void main(String[] args) {
        BTree test = new BTree();
        BTree.TwoThreeFourNode x = new BTree.TwoThreeFourNode('7');
        test.root = x;
        BTree.TwoThreeFourNode y = new BTree.TwoThreeFourNode('1');
        BTree.TwoThreeFourNode z = new BTree.TwoThreeFourNode('a');
        BTree.TwoThreeFourNode w = new BTree.TwoThreeFourNode('b');
        BTree.TwoThreeFourNode q = new BTree.TwoThreeFourNode('c');
        x.setChildAt(0,y);
        x.setChildAt(1,q);
        y.setChildAt(0,z);
        y.setChildAt(1,w);


        RedBlackTree ans= new RedBlackTree(test);
        ans.root = ans.rotateRight(ans.root);
        ans.root=ans.rotateLeft(ans.root);

        print(ans.root,0);
        //System.out.println(ans.root.item+"   "+ans.root.isBlack);
        //System.out.println(ans.root.left.item+"   "+ans.root.left.isBlack);
        //System.out.println(ans.root.right.item+"   "+ans.root.right.isBlack);
        //System.out.println(ans.root.right.right.item+"   "+ans.root.right.right.isBlack);
        //System.out.println(ans.root.right.left.item+"   "+ans.root.right.left.isBlack);
    }
    private static void print(RBTreeNode node, int d) {
        if (node == null) {
            return;
        }
        for (int i = 0; i < d; i++) {
            System.out.print("  ");
        }
        System.out.println(node.item+" " +node.isBlack);
        print(node.left, d + 1);
        print(node.right, d + 1);
    }

    /**
     * Rotates the (sub)tree rooted at given node to the right, and returns the
     * new root of the (sub)tree. If rotation is not possible somehow,
     * immediately return the input node.
     *
     * @param node root of the given (sub)tree.
     * @return new root of the (sub)tree.
     */
    RBTreeNode<T> rotateRight(RBTreeNode<T> node) {
        // YOUR CODE HERE
        RBTreeNode rtn = new RBTreeNode(node.isBlack,node.left.item,node.left.left,null);
        rtn.right = new RBTreeNode(false, node.item,node.left.right,node.right );
        return rtn;
    }

    /**
     * Rotates the (sub)tree rooted at given node to the left, and returns the
     * new root of the (sub)tree. If rotation is not possible somehow,
     * immediately return the input node.
     *
     * @param node root of the given (sub)tree.
     * @return new root of the (sub)tree.
     */
    RBTreeNode<T> rotateLeft(RBTreeNode<T> node) {
        // YOUR CODE HERE
        RBTreeNode rtn = new RBTreeNode(node.isBlack,node.right.item,null,node.right.right);
        rtn.left = new RBTreeNode(false, node.item,node.right.left,node.left );
        return rtn;
    }

    /**
     * Flips the color of the node and its children. Assume that the node has
     * both left and right children.
     *
     * @param node tree node
     */
    void flipColors(RBTreeNode<T> node) {
        node.isBlack = !node.isBlack;
        node.left.isBlack = !node.left.isBlack;
        node.right.isBlack = !node.right.isBlack;
    }

    /**
     * Returns whether a given node is red. null nodes (children of leaf) are
     * automatically considered black.
     *
     * @param node node
     * @return node is red.
     */
    private boolean isRed(RBTreeNode<T> node) {
        return node != null && !node.isBlack;
    }

    /**
     * Insert given item into this tree.
     *
     * @param item item
     */
    void insert(T item) {
        root = insert(root, item);
        root.isBlack = true;
    }

    /**
     * Recursivelty insert item into this tree. returns the (new) root of the
     * subtree rooted at given node after insertion. node == null implies that
     * we are inserting a new node at the bottom.
     *
     * @param node node
     * @param item item
     * @return (new) root of the subtree rooted at given node.
     */
    private RBTreeNode<T> insert(RBTreeNode<T> node, T item) {

        // Insert (return) new red leaf node.
        if (node == null) {
            // YOUR CODE HERE
            return new RBTreeNode(false,item);
        }

        // Handle normal binary search tree insertion.
        int comp = item.compareTo(node.item);
        if (comp == 0) {
            return node; // do nothing.
        } else if (comp < 0) {
            // YOUR CODE HERE

        } else {
            // YOUR CODE HERE

        }

        // handle case C and "Right-leaning" situation.
        if (isRed(node.right) && !isRed(node.left)) {
            // YOUR CODE HERE

        }

        // handle case B
        if (isRed(node.left) && isRed(node.left.left)) {
            // YOUR CODE HERE

        }

        // handle case A
        if (isRed(node.left) && isRed(node.right)) {
            // YOUR CODE HERE

        }
        return node;
    }


    /**
     * RedBlack tree node.
     *
     * @param <T> type of item.
     */
    static class RBTreeNode<T> {

        /** Item. */
        final T item;

        /** True if the node is black. */
        boolean isBlack;

        /** Pointer to left child. */
        RBTreeNode<T> left;

        /** Pointer to right child. */
        RBTreeNode<T> right;

        /**
         * Constructor with color and item.
         */
        RBTreeNode(boolean isBlack, T item) {
            this(isBlack, item, null, null);
        }

        /**
         * Constructor with color, item, and pointers to children (can be
         * null).
         */
        RBTreeNode(boolean isBlack, T item, RBTreeNode<T> left,
            RBTreeNode<T> right) {
            this.isBlack = isBlack;
            this.item = item;
            this.left = left;
            this.right = right;
        }
    }



}
