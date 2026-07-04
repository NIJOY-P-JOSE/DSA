import java.util.*;

class Main {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        int n = s.nextInt();

        for (int i = 0; i < n - 1; i++) {

            for (int j = 0; j <= n + i; j++) {

                if (j == n - i - 1 || j == n + i - 1)
                    System.out.print("*");
                else
                    System.out.print(" ");
            }

            System.out.println();
        }

        for (int i = 0; i < 2 * n - 1; i++)
            System.out.print("*");

        System.out.println();
    }
}
