public class BooleanSet implements SimpleSet {

	// Represent a set of nonnegative ints from 0 to maxElement-1
	// for some initially specified maxElement. 

	// contains[k] is true if k is in the set, false if it isn't
	private boolean[] contains;

	// Initialize a set of ints from 0 to maxElement-1.
	public BooleanSet (int maxElement) {
	    // YOUR CODE HERE
		contains = new boolean[maxElement];
	}
	
	// precondition: 0 <= k < maxElement.
	// postcondition: k is in this set.
	public void add(int k) {
		// YOUR CODE HERE
		contains[k] = true;
	}
	
	// precondition: 0 <= k < maxElement.
	// postcondition: k is not in this set.
	public void remove(int k) {
		// YOUR CODE HERE
		contains[k] = false;
	}
	
	// precondition: 0 <= k < maxElement
	// Return true if k is in this set, false otherwise.
	public boolean contains (int k) {
		// YOUR CODE HERE
		return contains[k];
	}
	
	// Return true if this set is empty, false otherwise.
	public boolean isEmpty() {
		for(int i = 0; i < contains.length; i++){
			if(contains[i]) return true;
		}
		return false;
	}

	// Returns the number of items in the set
	public int size() {
		int size = 0;
		// YOUR CODE HERE
		for (int i = 0; i <contains.length; i++){
			if(contains[i]) size++;
		}
		return size;
	}
}