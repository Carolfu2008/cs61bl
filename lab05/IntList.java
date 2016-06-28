/** A data structure to represent a Linked List of Integers.
  * Each IntList represents one node in the overall Linked List.
  *
  * @author Maurice Lee and Wan Fung Chui
  */

public class IntList {

    /** The integer stored by this node. */
    private int item;
    /** The next node in this IntList. */
    private IntList next;

    /** Constructs an IntList storing ITEM and next node NEXT. */
    public IntList(int item, IntList next) {
        this.item = item;
        this.next = next;
    }

    /** Constructs an IntList storing ITEM and no next node. */
    public IntList(int item) {
        this(item, null);
    }

    /** Returns an IntList consisting of the elements in ITEMS.
      * IntList L = IntList.list(1, 2, 3);
      * System.out.println(L.toString()) // Prints (1 2 3) */
    public static IntList list(int... items) {
        /** Check for cases when we have no element given. */
        if (items.length == 0) {
            return null;
        }
        /** Create the first element. */
        IntList head = new IntList(items[0]);
        IntList last = head;
        /** Create rest of the list. */
        for (int i = 1; i < items.length; i++) {
            last.next = new IntList(items[i]);
            last = last.next;
        }
        return head;
    }

    /** Returns the integer stored by this IntList. */
    public int item() {
        return item;
    }

    /** Returns the next node stored by this IntList. */
    public IntList next() {
        return next;
    }

    /**
     * Returns [position]th item in this list. Throws IllegalArgumentException
     * if index out of bounds.
     *
     * @param position, the position of element.
     * @return The element at [position]
     */
    public int get(int position) {
        // YOUR CODE HERE
        if (position >= this.size() || position < 0) {
            throw new IllegalArgumentException();
        }
        else {
            IntList p = this;
            for (int i = 0; i < position; i++) {
                p = p.next();
            }
            return p.item();
        }
    }

    /**
     * Returns the size of the list.
     *
     * @return The size of the list.
     */
    public int size() {
        // YOUR CODE HERE
        int cnt = 0;
        IntList p = this;
        for(int i = 0;p != null ;i++){
            p = p.next();
            cnt++;
        }
        return cnt;
    }

    /**
     * Returns the string representation of the list. For the list (1, 2, 3),
     * returns "( 1 2 3 )".
     *
     * @return The String representation of the list.
     */
    public String toString() {
        // YOUR CODE HERE
        String s = "( ";
        IntList p = this;
        for(int i = 0;p != null ;i++){
            s = s + p.item() + ' ';
            p = p.next();
        }
        s = s +")";
        return s;
    }

    /**
     * Returns whether this and the given list or object are equal.
     *
     * @param obj, another list (object)
     * @return Whether the two lists are equal.
     */
    public boolean equals(Object  obj) {
        // YOUR CODE HERE
        try {
            IntList p = this;
            if (((IntList) (obj)).size() == this.size()) {
                while (p != null) {
                    if (p.item() == ((IntList) (obj)).item()) {
                        p = p.next();
                        obj = ((IntList) (obj)).next();
                    } else
                        return false;
                }
            } else return false;

            return true;
        }catch (Exception ex){
            return false;
        }
    }

    /**
     * Adds the given item at the end of the list.
     *
     * @param item, the int to be added.
     */
    public void add(int item) {
        // YOUR CODE HERE
        if(this == null){
            this.item=item;
        }
        else {
            IntList p = this;
            while (p.next() != null) {
                p = p.next();
            }
            p.next = new IntList(item);
        }
    }

    /**
     * Returns the smallest element in the list.
     *
     * @return smallest element in the list
     */
    public int smallest() {
        // YOUR CODE HERE
        IntList p = this;
        int min = p.item();
        p=p.next();
        while(p != null){
            if(min > p.item()){
                min = p.item();
            }
            p=p.next();
        }
        return min;
    }

    /**
     * Returns the sum of squares of all elements in the list.
     *
     * @return The sum of squares of all elements.
     */
    public int squaredSum() {
        // YOUR CODE HERE
        int sum = 0;
        IntList p = this;
        while(p != null){
            sum += p.item()*p.item();
            p=p.next();
        }
        return sum;
    }

    /**
     * Returns a new IntList consisting of L1 followed by L2,
     * non-destructively.
     *
     * @param l1 list to be on the front of the new list.
     * @param l2 list to be on the back of the new list.
     * @return new list with L1 followed by L2.
     */
    public static IntList append(IntList l1, IntList l2) {
        // YOUR CODE HERE
        if(l1!=null) {
            IntList head = new IntList(l1.item());
            IntList last = head;
            IntList p = l1.next();
            while (p != null) {
                last.next = new IntList(p.item());
                last = last.next;
                p = p.next();
            }
            p = l2;
            while (p != null) {
                last.next = new IntList(p.item());
                last = last.next;
                p = p.next();
            }
            return head;
        }
        else{
            return l2;
        }
    }
}
