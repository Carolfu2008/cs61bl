import java.util.ArrayList;

public class BinaryTree {

    private TreeNode root;

    public BinaryTree() {
        root = null;
    }

    public BinaryTree(TreeNode t) {
        root = t;
    }

    public TreeNode getRoot() {
        return root;
    }

    private ArrayList alreadySeen;

    public boolean check() {
        alreadySeen = new ArrayList();
        try {
            isOK(root);
            return true;
        } catch (IllegalStateException e) {
            return false;
        }
    }

    private void isOK(TreeNode t) throws IllegalStateException {
        if(t == null)
            return;
        if (alreadySeen.contains(t))
            throw new IllegalStateException();
        alreadySeen.add(t);
        if (t.left != null)
            isOK(t.left);
        if (t.right != null)
            isOK(t.right);
    }

    // Contains nodes already seen in the traversal.

    //(IllegalStateException is provided in Java.)

    // Print the values in the tree in preorder: root value first,
    // then values in the left subtree (in preorder), then values
    // in the right subtree (in preorder).
    public void printPreorder() {
        if (root == null) {
            System.out.println("(empty tree)");
        } else {
            root.printPreorder();
            System.out.println();
        }
    }

    // Print the values in the tree in inorder: values in the left
    // subtree first (in inorder), then the root value, then values
    // in the right subtree (in inorder).
    public void printInorder() {
        if (root == null) {
            System.out.println("(empty tree)");
        } else {
            root.printInorder();
            System.out.println();
        }
    }

    public void fillSampleTree1() {
        TreeNode temp = new TreeNode("a");
        root = new TreeNode("a", temp, temp);
    }

    public void fillSampleTree2() {
        root = new TreeNode("a", new TreeNode("b", new TreeNode("d",
                new TreeNode("e"), new TreeNode("f")), null), new TreeNode("c"));
    }

    public static BinaryTree fibTree(int n) {
        BinaryTree result = new BinaryTree();
        if (n == 0){
            result.root = new TreeNode(0);
            return result;
        }else if (n == 1){
            result.root = new TreeNode(1);
            return result;
        }else {
            result.root = new TreeNode((int)fibTree(n-1).root.item + (int)fibTree(n-2).root.item);
            result.root.left = fibTree(n-1).root;
            result.root.right = fibTree(n-2).root;
        }
        return result;
    }

    public static BinaryTree exprTree(String s) {
        BinaryTree result = new BinaryTree();
        result.root = result.exprTreeHelper(s);
        return result;
    }
    // Return the tree corresponding to the given arithmetic expression.
// The expression is legal, fully parenthesized, contains no blanks,
// and involves only the operations + and *.
    private TreeNode exprTreeHelper(String expr) {
        TreeNode rtn = null;
        if (expr.charAt(0) != '(') {
            return new TreeNode(expr.charAt(0)); // you fill this in
        } else {
            // expr is a parenthesized expression.
            // Strip off the beginning and ending parentheses,
            // find the main operator (an occurrence of + or * not nested
            // in parentheses, and construct the two subtrees.
            int nesting = 0;
            int opPos = 0;
            for (int k = 1; k < expr.length() - 1; k++) {
                // you supply the missing code
                int p = 0;
                if (expr.charAt(k) == '(') p++;
                if (expr.charAt(k) == ')' )p--;
                if (p == 0 && expr.charAt(k) != '(' && expr.charAt(k) != ')'){
                    opPos = k;
                    rtn = new TreeNode(expr.charAt(k));
                    break;
                }
            }
            String opnd1 = expr.substring(1, opPos);
            String opnd2 = expr.substring(opPos + 1, expr.length() - 1);
            String op = expr.substring(opPos, opPos + 1);
            System.out.println("expression = " + expr);
            System.out.println("operand 1  = " + opnd1);
            System.out.println("operator   = " + op);
            System.out.println("operand 2  = " + opnd2);
            System.out.println();
            rtn.left = exprTreeHelper(opnd1);
            rtn.right = exprTreeHelper(opnd2);// you fill this in
            return rtn;
        }
    }
    public void optimize(){
        root.optimize();
    }

    public static void main(String[] args) {
        BinaryTree t;
        t = new BinaryTree();
        System.out.println(t.check());
        print(t, "the empty tree");
        t.fillSampleTree1();
        t.print();
        System.out.println(t.check());
        print(t, "sample tree 1");
        t.fillSampleTree2();
        System.out.println(t.check());
        print(t, "sample tree 2");

        fibTree(4).print();
    }

    private static void print(BinaryTree t, String description) {
        System.out.println(description + " in preorder");
        t.printPreorder();
        System.out.println(description + " in inorder");
        t.printInorder();
        System.out.println();
    }

    public static class TreeNode {

        public Object item;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(Object obj) {
            item = obj;
            left = right = null;
        }

        public TreeNode(Object obj, TreeNode left, TreeNode right) {
            item = obj;
            this.left = left;
            this.right = right;
        }

        private void printPreorder() {
            System.out.print(item + " ");
            if (left != null) {
                left.printPreorder();
            }
            if (right != null) {
                right.printPreorder();
            }
        }

        private void printInorder() {
            if (left != null) {
                left.printInorder();
            }
            System.out.print(item + " ");
            if (right != null) {
                right.printInorder();
            }
        }


        public TreeNode getLeft() {
            return left;
        }

        public TreeNode getRight() {
            return right;
        }

        public Object getItem() {
            return item;
        }

        //In TreeNode
        private static final String indent1 = "    ";

        private void print(int indent) {
            // TODO your code here
            if (this.right != null){
                this.right.print(indent+1);
            }
            println (item, indent);
            if(this.left != null){
                this.left.print(indent+1);
            }
            // TODO your code here

        }

        private static void println(Object obj, int indent) {
            for (int k=0; k<indent; k++) {
                System.out.print(indent1);
            }
            System.out.println(obj);
        }

        public void optimize() {

            if (left != null) {
                left.optimize();
            }
            if (right != null) {
                right.optimize();
            }
            if (this.item.toString() == "+"){
                if(this.right.item.toString().compareTo("0") >= 0 && this.right.item.toString().compareTo("9") <= 0
                        && this.left.toString().compareTo("0") >= 0 && this.right.toString().compareTo("9") <= 0){
                    this.item = new String(String.valueOf((char)((int)this.right.item + (int)this.left.item - '0')));
                    this.left = null;
                    this.right =null;
                }
            }
            if (this.item.toString() == "-"){
                if(this.right.item.toString().compareTo("0") >= 0 && this.right.item.toString().compareTo("9") <= 0
                        && this.left.toString().compareTo("0") >= 0 && this.right.toString().compareTo("9") <= 0){
                    this.item = new String(String.valueOf((char)((int)this.right.item - (int)this.left.item - '0')));
                    this.left = null;
                    this.right =null;
                }
            }
        }
    }
    public void print() {
        if (root != null) {
            root.print(0);
        }
    }

}
