public class FixedSizeList implements SimpleList {

    // instance variables
    protected int[] values;   // list elements
    int count;                // number of array cells used by list

    // constructor
    // capacity: actual size of the array or the (temporary) maximum
    // number of elements it can hold
    public FixedSizeList(int capacity) {
        // YOUR CODE HERE
        values = new int[capacity];
    }

    public FixedSizeList() { }


    // This method should return the number of items 
    // contained in values
    public int size() {
        // YOUR CODE HERE
        return count;
    }

    // This method should return true if the list
    // is empty, otherwise return false
    public boolean isEmpty() {
        // YOUR CODE HERE
        if(count == 0)
            return true;
        else
            return false;
    }

    // Add the argument to the list by placing it in the first
    // unused spot in the array and incrementing the count.
    // Assume that the sequence isn't full.
    public void add(int k) {
        // YOUR CODE HERE
        if(count == values.length)
            throw new ListException("no capacity");
        else {
            values[count] = k;
            count++;
        }
    }

    // This method removes k from the list, if it is present.
    // If k appears multiple times, it removes the first occurence of k
    public void remove(int k) {
        // YOUR CODE HERE
        for(int i = 0;i < values.length; i++){
            if(values[i] == k) {
                for(int j = i;j < count; j++){
                    values[j] = values[j+1];
                }
                count--;
                break;
            }
        }
    }

    // This method returns whether or not the collection contains k
    public boolean contains(int k) {
        // YOUR CODE HERE
        for(int i = 0;i < values.length; i++){
            if(values[i] == k) {
                return true;
            }
        }
        return false;
    }

    // Returns the integer stored at the i-th index in the List
    public int get(int i) {
        if(i >= count)
            throw new ListException("no index");
        else{
            return values[i];
        }
        // YOUR CODE HERE
    }

    // Insert k into the sequence at position i,
    // shifting the later elements in the sequence over to make room
    // for the new element.
    // Assumptions: The array isn't full, i.e. count < values.length
    // Also, i is between 0 and count, inclusive.
    public void add(int i, int k) {
        if(count == values.length)
            throw new ListException("no capacity");
        else{
            for (int p = count; p > i; p--) {
                values[p] = values[p-1];
            }
            values[i] = k;
            count++;
        }
    }


    // Removes the integer in the i-th position in the list,
    // note now this is different from the one-argument remove 
    public void removeIndex(int i) {
        // YOUR CODE HERE
        if (i >= count) {
            throw new ListException("no index");
        } else {
            for(int j = i;j < count; j++){
                values[j] = values[j+1];
            }
            count--;
        }
    }



}