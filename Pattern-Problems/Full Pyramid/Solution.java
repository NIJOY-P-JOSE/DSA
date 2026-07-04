import java.util.*;

class Main {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        int n = s.nextInt();

        for (int i = 0; i < n; i++) {
            int level = i + 1;

            for (int j = 0; j < n - level; j++)
                System.out.print(" ");

            for (int j = 0; j < level; j++)
                System.out.print("*");

            for (int j = 0; j < level - 1; j++)
                System.out.print("*");

            System.out.println();
        }
    }
}
