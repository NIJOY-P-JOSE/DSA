import java.util.*;

public class Main
{
    public static void main(String[] args) {
        System.out.println("Hello World");

        Scanner s = new Scanner(System.in);
        int n = s.nextInt();

        int ans = srt(n);
        float dans = ans;

        if (!(ans * ans == n))
        {
            for (int i = 1; i < 10; i++)
            {
                dans += 0.1;

                if (dans * dans > n)
                {
                    dans -= 0.1;
                    break;
                }
            }

            for (int i = 1; i < 10; i++)
            {
                dans += 0.01;

                if (dans * dans > n)
                {
                    dans -= 0.01;
                    break;
                }
            }
        }

        System.out.printf("%.2f", dans);
    }

    static int srt(int n)
    {
        int s = 0;
        int e = n;

        while (s <= e)
        {
            int mid = s + (e - s) / 2;

            if (mid * mid == n)
                return mid;

            else if (mid * mid > n)
                e = mid - 1;

            else
                s = mid + 1;
        }

        return e;
    }
}
