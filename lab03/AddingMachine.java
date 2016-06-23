import java.util.*;

public class AddingMachine {

	public static void main (String[] args) {
		Scanner scanner = new Scanner(System.in);
		boolean justStarting = true;
		int total = 0;
		int subtotal = 0;
		int input;
		int last = 1;
		int MAXIMUM_NUMBER_OF_INPUTS = 100;
		int [] values = new int[MAXIMUM_NUMBER_OF_INPUTS];
		int i = 0;
		while (true) {
			input = scanner.nextInt();
			if (input == 0) {
				if (last == 0) {
					System.out.println("total " + total);
					for(int j = 0;j <= i; j++){
						System.out.println(values[j]);
					}
					return;
				}
				System.out.println("subtotal " + subtotal);
				total += subtotal;
				subtotal = 0;
			}
			subtotal += input;
			last = input;
			if(input != 0){
				values[i] = input;
				i++;
			}
		    // TODO Your code here
		}
	}

}
