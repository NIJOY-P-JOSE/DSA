import java.util.*;

public class Main {

    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);

        int n = s.nextInt();

        double ans = sqrt(n);

        System.out.printf("%.2f", ans);
    }

    static double sqrt(int n) {

        if (n == 0)
            return 0;

        double x = n;
        double root;

        while (true) {

            root = 0.5 * (x + (n / x));

            if (Math.abs(root - x) < 0.1) // or 0.000001
                break;

            x = root;
        }

        return root;
    }
}
