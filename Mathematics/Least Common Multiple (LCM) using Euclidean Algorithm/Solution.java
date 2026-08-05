import java.util.*;

public class Main {

    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);

        while (true) {

            // Read two numbers
            int a = s.nextInt();
            int b = s.nextInt();

            // Calculate and print the LCM
            System.out.println((a * b) / GCD(a, b));
        }
    }

    // Euclidean Algorithm to find GCD
    static int GCD(int a, int b) {

        // Base case:
        // If the first number becomes 0,
        // the second number is the GCD.
        if (a == 0)
            return b;

        // Recursive call
        return GCD(b % a, a);
    }
}
