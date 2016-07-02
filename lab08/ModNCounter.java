public class ModNCounter {

	private int myCount;
	private int N;
	public ModNCounter() {
		myCount = 0;
	}

	public ModNCounter(int n) {
		N = n;
	}

	public void increment() {
        myCount++;
        if(myCount == N){
            myCount = 0;
        }
	}

	public void reset() {
		myCount = 0;
	}

	public int value() {
		return myCount;
	}

    public int getN() {
        return N;
    }
}
