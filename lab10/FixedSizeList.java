public class FixedSizeList implements SimpleList {

    // instance variables
    protected int[] values;   // list elements
    int count;                // number of array cells used by list

    // constructor
    // capacity: actual size of the array or the (temporary) maximum
    // number of elements it can hold
    public FixedSizeList(int capacity) {
        count = 0;
        values = new int[capacity];
    }

    // This method should return the number of items 
    // contained in values
    public int size() {
        return count;
    }

    // This method should return true if the list
    // is empty, otherwise return false
    public boolean isEmpty() {
        return count == 0;
    }

    // Add the argument to the list by placing it in the first
    // unused spot in the array and incrementing the count.
    // Assume that the sequence isn't full.
    public void add(int k) {
        this.add(count, k);
    }

    // This method removes k from the list, if it is present.
    // If k appears multiple times, it removes the first occurence of k
    public void remove(int k) {
        int ind = helper(k);
        if (ind != -1) {
            removeIndex(ind);
        }
    }

    public int helper(int k) {
        for (int i = 0; i < count; i++) {
            if (values[i] == k) {
                return i;
            }
        }
        return -1;
    }

    // This method returns whether or not the collection contains k
    public boolean contains(int k) {
        for (int i = 0; i < count; i++) {
            if (values[i] == k) {
                return true;
            }
        }
        return false;
    }

    // Returns the integer stored at the i-th index in the List
    // Assumes there are at least i numbers in the list
    public int get(int i) {
        if (i > count) {
            System.err.println("This List isn't that big.");
            System.exit(1);
        }
        return values[i];
    }

    // Insert k into the sequence at position i,
    // shifting the later elements in the sequence over to make room
    // for the new element.
    // Assumptions: The array isn't full, i.e. count < values.length
    // Also, i is between 0 and count, inclusive.
    public void add(int i, int k) {
        if (count == values.length) {
            System.err.println("This List is full.");
            System.exit(1);
        }
        for (int m = count; m > i; m--) {
            values[m] = values[m-1];
        }
        values[i] = k;
        count++;
    }


    // Removes the integer in the i-th position in the list,
    // note now this is different from the one-argument remove 
    public void removeIndex(int i) {
        if (i >= count) {
            System.err.println("This List is smaller than i.");
            System.exit(1);
        }
        for (int m = i; m < count; m++) {
            values[m] = values[m + 1];
        }
        values[count-1] = 0;
        count--;
    }




}