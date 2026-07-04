import java.util.*;

class Main {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        int n = s.nextInt();

        for (int i = 0; i < n; i++) {
            int stars = n - i;

            for (int j = 0; j < n - stars; j++)
                System.out.print("  ");

            for (int j = 0; j < stars; j++)
                System.out.print("* ");

            System.out.println();
        }
    }
}
