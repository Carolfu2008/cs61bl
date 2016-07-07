public class FixedSizeList implements SimpleList {

    // instance variables
    protected int[] values;   // list elements
    int count;                // number of array cells used by list

    // constructor
    // capacity: actual size of the array or the (temporary) maximum
    // number of elements it can hold
    public FixedSizeList(int capacity) {
        // YOUR CODE HERE
    }

    // This method should return the number of items 
    // contained in values
    public int size() {
        // YOUR CODE HERE
    }

    // This method should return true if the list
    // is empty, otherwise return false
    public boolean isEmpty() {
        // YOUR CODE HERE
    }

    // Add the argument to the list by placing it in the first
    // unused spot in the array and incrementing the count.
    // Assume that the sequence isn't full.
    public void add(int k) {
        // YOUR CODE HERE
    }

    // This method removes k from the list, if it is present.
    // If k appears multiple times, it removes the first occurence of k
    public void remove(int k) {
        // YOUR CODE HERE
    }

    // This method returns whether or not the collection contains k
    public boolean contains(int k) {
        // YOUR CODE HERE
    }

    // Returns the integer stored at the i-th index in the List
    public int get(int i) {
        // YOUR CODE HERE
    }

    // Insert k into the sequence at position i,
    // shifting the later elements in the sequence over to make room
    // for the new element.
    // Assumptions: The array isn't full, i.e. count < values.length
    // Also, i is between 0 and count, inclusive.
    public void add(int i, int k) {
        for (int k = i + 1; k <= count; k++) {
            values[k] = values[k-1];
        }
        values[i] = k;
        count++;
    }


    // Removes the integer in the i-th position in the list,
    // note now this is different from the one-argument remove 
    public void removeIndex(int i) {
        // YOUR CODE HERE
    }




}