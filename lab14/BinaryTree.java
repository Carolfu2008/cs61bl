import java.util.ArrayList;
import java.util.List;

/** A Generic Binary Tree Class.
  * @author CS61BL Staff. */

public class BinaryTree<T> {

    /* The root node of the tree. */
    protected TreeNode root;

    /* Constructs an empty binary tree. */
    public BinaryTree() {
        root = null;
    }

    /* Constructs a binary tree with root T. */
    public BinaryTree(TreeNode t) {
        root = t;
    }

    /* Represents a node in the binary tree. */
    protected class TreeNode {

        public T item;
        public TreeNode left;
        public TreeNode right;
        public int size = 0;

        public TreeNode(T item) {
            this.item = item;
            left = right = null;
        }

        public TreeNode(T item, TreeNode left, TreeNode right) {
            this.item = item;
            this.left = left;
            this.right = right;
        }
        
        /* Use for testing. */
        private void printPreorder() {
            System.out.print(item + " ");
            if (left != null) {
                left.printPreorder();
            }
            if (right != null) {
                right.printPreorder();
            }
        }

        /* Use for testing. */
        private void printInorder() {
            if (left != null) {
                left.printInorder();
            }
            System.out.print(item + " ");
            if (right != null) {
                right.printInorder();
            }
        }
    }

    /* EVERYTHING BELOW IS USED ONLY FOR EXERCISE 5. */

        /** Suggested testing script:

                @Test
                public void treeFormatTest() {
                    BinarySearchTree<String> x = new BinarySearchTree();
                    x.add("C");
                    x.add("A");
                    x.add("E");
                    x.add("B");
                    x.add("D");
                    final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
                    PrintStream oldOut = System.out;
                    System.setOut(new PrintStream(outContent));
                    BinaryTree.print(x, "x");
                    System.setOut(oldOut);
                    assertEquals(outContent.toString().trim(), 
                            "x in preorder\nC A B E D \nx in inorder\nA B C D E \n\n".trim());
                }

        */

    /* Constructs a binary tree based on the preorder PRE and inorder IN. */
    public BinaryTree(ArrayList<T> pre,  ArrayList<T> in) {
        root = listHelper(pre, in);
    }
    
    /* A helper method. */
    private TreeNode listHelper(ArrayList<T> pre,  ArrayList<T> in) {
        //YOUR CODE HERE
        if (pre.size() == 0)
            return null;
        TreeNode rtn = new TreeNode(pre.get(0));
        int inIndex = in.indexOf(pre.get((0)));
        pre.remove(0);
        in.remove(inIndex);
        if (inIndex == 0){
            return rtn;
        }
        ArrayList<T> preRight = new ArrayList<T>();
        ArrayList<T> inRight = new ArrayList<T>();
        ArrayList<T> preLeft = new ArrayList<T>();
        ArrayList<T> inLeft = new ArrayList<T>();;
        for(int i = 0; i < inIndex;i++) {
            preLeft.add(pre.get(i));
            inLeft.add(in.get(i));
        }
        for(int i = inIndex; i < pre.size();i++) {
            preRight.add(pre.get(i));
            inRight.add(in.get(i));
        }
        rtn.left = listHelper(preLeft,inLeft);
        rtn.right = listHelper(preRight,inRight);
        return rtn;
    }
    
    /** Print the values in the tree in preorder: root value first,
      * then values in the left subtree (in preorder), then values
      * in the right subtree (in preorder). */
    public void printPreorder() {
        if (root == null) {
            System.out.println("(empty tree)");
        } else {
            root.printPreorder();
            System.out.println();
        }
    }

    /** Print the values in the tree in inorder: values in the left
      * subtree first (in inorder), then the root value, then values
      * in the right subtree (in inorder). */
    public void printInorder() {
        if (root == null) {
            System.out.println("(empty tree)");
        } else {
            root.printInorder();
            System.out.println();
        }
    }

    /* Used for testing. */
    protected static void print(BinaryTree<?> t, String description) {
        System.out.println(description + " in  preorder");
        t.printPreorder();
        System.out.println(description + " in inorder");
        t.printInorder();
        System.out.println();
    }

    public static void main(String args[]){
        ArrayList<String> pre = new ArrayList<String>();
        ArrayList<String> in = new ArrayList<String>();
        pre.add("A");
        pre.add("B");
        pre.add("D");
        pre.add("E");
        pre.add("C");
        pre.add("F");
        in.add("D");
        in.add("B");
        in.add("E");
        in.add("A");
        in.add("F");
        in.add("C");
        BinaryTree<String> tree = new BinaryTree<String>(pre, in);
        print(tree,"");
    }

}
