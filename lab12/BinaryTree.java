/**
 * A BinaryTree is a tree with nodes that have up to two children.
 */
public class BinaryTree {

    /**
     * root is the root of this BinaryTree
     */
    private TreeNode root;

    /**
     * The BinaryTree constructor
     */
    public BinaryTree() {
        root = null;
    }

    public BinaryTree(TreeNode t) {
        root = t;
    }

    /**
     * Print the values in the tree in preorder: root value first, then values
     * in the left subtree (in preorder), then values in the right subtree
     * (in preorder).
     */
    public void printPreorder() {
        if (root == null) {
            System.out.println("(empty tree)");
        } else {
            root.printPreorder();
            System.out.println();
        }
    }

    /**
     * Print the values in the tree in inorder: values in the left subtree
     * first (in inorder), then the root value, then values in the first
     * subtree (in inorder).
     */
    public void printInorder() {
        if (root == null) {
            System.out.println("(empty tree)");
        } else {
            root.printInorder();
            System.out.println();
        }
    }

    public int height(){
        if(root != null){
            return root.height();
        }
        return 0;
    }

    public boolean isCompletelyBalanced(){
        if (root != null)
            return root.isCompletelyBalanced();
        return true;
    }
    /**
     * Fills this BinaryTree with values a, b, and c
     */
    public void fillSampleTree1() {
        root = new TreeNode("a", new TreeNode("b"), new TreeNode("c"));
    }

    /**
     * Fills this BinaryTree with values a, b, and c, d, e, f
     */
    public void fillSampleTree2() {
        root = new TreeNode("a", new TreeNode("b", new TreeNode("d",
            new TreeNode("e"), new TreeNode("f")), null), new TreeNode("c"));
    }

    public void fillSampleTree3() {
        root = new TreeNode("a", new TreeNode("b"),new TreeNode("c",
                new TreeNode("d",new TreeNode("e"),new TreeNode("f")),null));
    }

    /**
     * Creates two BinaryTrees and prints them out in inorder
     */
    public static void main(String[] args) {
        BinaryTree t;
        t = new BinaryTree();
        print(t, "the empty tree");
        System.out.println(t.isCompletelyBalanced());
        t.fillSampleTree1();
        print(t, "sample tree 1");
        System.out.println(t.isCompletelyBalanced());
        t.fillSampleTree2();
        print(t, "sample tree 2");
        System.out.println(t.isCompletelyBalanced());
        t.fillSampleTree3();
        print(t, "sample tree3");
        System.out.println(t.isCompletelyBalanced());
        System.out.println(t.height());
    }

    /**
     * Prints out the contents of a BinaryTree with a description in both
     * preorder and inorder
     * @param t           the BinaryTree to print out
     * @param description a String describing the BinaryTree t
     */
    private static void print(BinaryTree t, String description) {
        System.out.println(description + " in preorder");
        t.printPreorder();
        System.out.println(description + " in inorder");
        t.printInorder();
        System.out.println();
    }

    /**
     * A TreeNode is a Node this BinaryTree
     */
    private static class TreeNode {

        /**
         * item is the item that is contained in this TreeNode
         * left is the left child of this TreeNode
         * right is the right child of this TreeNode
         */
        public Object item;
        public TreeNode left;
        public TreeNode right;

        /**
         * A TreeNode constructor that creates a node with obj as its item
         * @param  obj the item to be contained in this TreeNode
         */
        TreeNode(Object obj) {
            item = obj;
            left = null;
            right = null;
        }

        /**
         * A Treenode constructor that creates a node with obj as its item and
         * left and right as its children
         * @param  obj   the item to be contained in this TreeNode
         * @param  left  the left child of this TreeNode
         * @param  right the right child of this TreeNode
         */
        TreeNode(Object obj, TreeNode left, TreeNode right) {
            item = obj;
            this.left = left;
            this.right = right;
        }

        /**
         * Prints this TreeNode and the subtree rooted at it in preorder
         */
        private void printPreorder() {
            System.out.print(item + " ");
            if (left != null) {
                left.printPreorder();
            }
            if (right != null) {
                right.printPreorder();
            }
        }

        /**
         * Prints this TreeNode and the subtree rooted at it in inorder
         */
        private void printInorder() {
            if (left != null) {
                left.printInorder();
            }
            System.out.print(item + " ");
            if (right != null) {
                right.printInorder();
            }
        }

        private int height() {
            if (left == null && right == null)
                return 1;
            else if(left == null)
                return 1 + right.height();
            else if(right == null)
                return 1 +left.height();
            else{
                int leftHeight = left.height();
                int rightHeight = right.height();
                return 1 + Math.max(leftHeight,rightHeight);
            }
        }

        private boolean isCompletelyBalanced() {
            if(left.height() == 1 && right.height() == 1)
                return true;
            if(left.height() != right.height())
                return false;
            else
                return left.isCompletelyBalanced() && right.isCompletelyBalanced();
        }
    }
}
