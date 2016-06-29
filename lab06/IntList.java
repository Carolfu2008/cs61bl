/** A data structure to represent a Linked List of Integers.
 * Each IntList represents one node in the overall Linked List.
 * Encapsulated version.
 */
public class IntList {
    /** The head of the list is the first node in the list. If the list is empty, head is null **/
    private IntListNode head;
    private int size;

    /** IntListNode is a nested class. It can be instantiated when associated with an instance of
     *  IntList.
     *  **/
    public static class IntListNode {
        int item;
        IntListNode next;

        public IntListNode(int item, IntListNode next) {
            this.item = item;
            this.next = next;
        }

    }

    public int getSize() {
        return size;
    }

    public IntList() {}

    public IntList(int[] initial) {
        for (int i = initial.length - 1; i >= 0; i--) {
            head = new IntListNode(initial[i], head);
        }
        size = initial.length;
    }

    /**
     * Get the value at position pos. If the position does not exist, throw an
     * IndexOutOfBoundsException.
     * @param position to get from
     * @return the int at the position in the list.
     */
    public int get(int position) {
        if (position >= size) throw new IndexOutOfBoundsException("Position larger than size of list.");
        IntListNode curr = head;
        while (position > 0) {
            curr = curr.next;
            position--;
        }
        return curr.item;
    }

    /* Fill in below! */

    /**
     * Insert a new node into the IntList.
     * @param x value to insert
     * @param position position to insert into. If position exceeds the size of the list, insert into
     *            the end of the list.
     */
    public void insert(int x, int position) {
        // Fill me in!
        if(position >= size){
            position = size;
        }
        if(head!=null) {
            IntListNode curr = head;
            while (position > 1) {
                curr = curr.next;
                position--;
            }
            IntListNode temp = curr.next;
            curr.next = new IntListNode(x, temp);
            size++;
        }
        else{
            head = new IntListNode(x, null);
            size++;
        }
    }

    /**
     * Merge two sorted IntLists a and b into one sorted IntList containing all of their elements.
     * @return a new IntList without modifying either parameter
     */
    public static IntList merge(IntList a, IntList b) {
        // Fill me in!
        IntList ans = new IntList();
        int p = 0;
        if(a.get(0) > b.get(0)){
            ans.head = new IntListNode(b.get(0),null);
            ans.size++;
            p =1;
        }
        else{
            ans.head = new IntListNode(a.get(0),null);
            ans.size++;
        }
        IntListNode last =ans.head;
        int i =0,j =0;
        if(p==1) j=1;
        else i=1;
        while(i < a.size && j < b.size){
            if(a.get(i) < b.get(j)){
                last.next = new IntListNode(a.get(i),null);
                last = last.next;
                ans.size++;
                i++;
            }
            else{
                last.next = new IntListNode(b.get(j),null);
                last = last.next;
                ans.size++;
                j++;
            }
        }
        if(i == a.size){
            while(j != b.size){
                last.next = new IntListNode(b.get(j),null);
                last = last.next;
                ans.size++;
                j++;
            }
        }
        else if (j == b.size){
            while(i != a.size){
                last.next = new IntListNode(a.get(i),null);
                last = last.next;
                ans.size++;
                i++;
            }
        }
        return  ans;
    }

    /**
     * Reverse the current list recursively, using a helper method.
     */
    public void reverse() {
        // Fill me in!
        for(int i = 0;i < size/2 ;i++){
            int temp1 = get(i);
            int p = size-1-i;
            int q = i;
            int temp2 = get(p);
            IntListNode curr2 = head;
            while(q>0){
                curr2=curr2.next;
                q--;
            }
            curr2.item = temp2;
            IntListNode curr = head;
            while (p > 0) {
                curr = curr.next;
                p--;
            }
            curr.item = temp1;
        }
    }

    /* Optional! */

    /**
     * Remove the node at position from this list.
     * @param position int representing the index of the node to remove. If greater than the size
     *                 of this list, throw an IndexOutOfBoundsException.
     */
    public void remove(int position) {
        if (position >= size) throw new IndexOutOfBoundsException();
        // fill me in
    }
}
