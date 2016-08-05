import java.util.ArrayList;
import java.util.Arrays;

public class DistributionSorts {

	/**
	 * Modify arr to be sorted. Assume arr only contains 0, 1, ..., 9
	 */
	public static void countingSort(int[] arr) {
		// TODO your code here!
		int [] ans = new int[arr.length];
		int [] countArr = new int[10];
		int [] posArr = new int [10];
		for (int i = 0;i < arr.length; i++) {
			countArr[arr[i]]++;
		}
		posArr[0] = countArr[0];
		for (int i = 1;i < countArr.length ; i++) {
			posArr[i] = posArr[i-1] + countArr[i];
		}
		for (int i = arr.length - 1 ; i >= 0; i--) {
			ans[posArr[arr[i]] - 1] = arr[i];
			posArr[arr[i]]--;
		}
		for (int i = 0; i < arr.length; i++) {
			arr[i] = ans[i];
		}
	}

	/**
	 * Sorts the given array using MSD radix sort. 
	 */
	public static void MSDRadixSort(int[] arr) {
		int maxDigit = mostDigitsIn(arr) - 1;
		MSDRadixSortFromDigitInBounds(arr, maxDigit, 0, arr.length);
	}

	/**
	 * Radix sorts the input array only between the indices start and end. Only
	 * considers digits from the input digit on down. This method is recursive.
	 */
	public static void MSDRadixSortFromDigitInBounds(int[] arr, int digit,
			int start, int end) {
		// TODO your code here! Make sure to use the countingSortByDigitInBounds
		// helper method, given below.
		if (digit >= 0) {
			int p = start;
			ArrayList<Integer> bound = countingSortByDigitInBounds(arr, digit, start, end);
			for (Integer x : bound) {
				MSDRadixSortFromDigitInBounds(arr,digit -1,p,x);
				p = x;
			}
		}

	}

	/**
	 * A helper method for radix sort. Modifies arr to be sorted according to
	 * digit. Only sorts the portion of the arr between the indices start
	 * (inclusive) and end (exclusive).
	 * 
	 * Does NOT return the sorted array. Returns an array containing the
	 * boundary of each same-digit bucket in the array. This will be useful for
	 * radix sort.
	 */
	private static ArrayList<Integer> countingSortByDigitInBounds(int[] arr, int digit,
														 int start, int end) {
		int [] ans = new int[end - start];
		ArrayList<Integer> rtn = new ArrayList();
		int [] countArr = new int[10];
		int [] posArr = new int [10];
		for (int i = start; i < end; i++) {
			countArr[(arr[i]/(int)(Math.pow(10,digit)))%10]++;
		}

		posArr[0] = countArr[0];
		for (int i = 1;i < countArr.length ; i++) {
			posArr[i] = posArr[i-1] + countArr[i];
		}
		for (int i = end -1; i >= start; i--) {
			ans[posArr[(arr[i]/(int)(Math.pow(10,digit)))%10] - 1] = arr[i];
			posArr[(arr[i]/(int)(Math.pow(10,digit)))%10]--;
		}
		for (int i = end -1,j = ans.length - 1 ; i >= start; i--,j--){
			arr[i] = ans[j];
		}
		for (int i = start; i < end -1 ;i++) {
			if ((arr[i]/(int)(Math.pow(10,digit)))%10 !=(arr[i+1]/(int)(Math.pow(10,digit)))%10){
				rtn.add(i+1);
			}
		}
		rtn.add(end);

		// TODO your code here!


		return rtn;
	}

	/**
	 * Returns the highest number of digits that any integer in arr happens to
	 * have.
	 */
	private static int mostDigitsIn(int[] arr) {
		int maxDigitsSoFar = 0;
		for (int num : arr) {
			int numDigits = (int) (Math.log10(num) + 1);
			if (numDigits > maxDigitsSoFar) {
				maxDigitsSoFar = numDigits;
			}
		}
		return maxDigitsSoFar;
	}

	/**
	 * Returns a random integer between 0 and 9999.
	 */
	private static int randomInt() {
		return (int) (10000 * Math.random());
	}

	/**
	 * Returns a random integer between 0 and 9.
	 */
	private static int randomDigit() {
		return (int) (10 * Math.random());
	}

	/**
	 * Runs some very basic tests of counting sort and radix sort.
	 */
	public static void main(String[] args) {
		int[] arr1 = new int[20];
		for (int i = 0; i < arr1.length; i++) {
			arr1[i] = randomDigit();
		}
		System.out.println("Original array: " + Arrays.toString(arr1));
		countingSort(arr1);
		if (arr1 != null) {
			System.out.println("Should be sorted: " + Arrays.toString(arr1));
		}

		int[] arr2 = new int[3];
		for (int i = 0; i < arr2.length; i++) {
			arr2[i] = randomDigit();
		}
		System.out.println("Original array: " + Arrays.toString(arr2));
		//MSDRadixSort(arr2);
		System.out.println("Should be sorted: " + Arrays.toString(arr2));

		int[] arr3 = new int[30];
		for (int i = 0; i < arr3.length; i++) {
			arr3[i] = randomInt();
		}
		System.out.println("Original array: " + Arrays.toString(arr3));
		MSDRadixSort(arr3);
		System.out.println("Should be sorted: " + Arrays.toString(arr3));
		countingSortByDigitInBounds(arr3,mostDigitsIn(arr3) - 1,5, arr3.length);
	}
}
