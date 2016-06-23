/**
 * Created by ENVY on 6/23/2016.
 */
public class SieveOfEratosthenes {
    public static void main(String[] args) {
        int upperBound = 0;
        try {
            upperBound = Integer.parseInt (args[0]);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        if (args.length < 1) {
            System.out.println("You need to enter an argument!");
        }
        boolean[] isNotPrime = new boolean[upperBound + 1];
        isNotPrime[0] = isNotPrime[1] = true;
        for (int i = 2; i <= upperBound; i++) {
            if (isNotPrime[i] == true) {
                continue;
            } else {
                for(int j = i*i; j <= upperBound; j += i ){
                    isNotPrime[j] = true;
                }
            }
        }
        for (int i = 0; i <= upperBound; i++) {
            if (!isNotPrime[i]) {
                System.out.println(i + " is a prime number.");
            }
        }
    }
}