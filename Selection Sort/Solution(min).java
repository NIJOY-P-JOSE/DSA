import java.util.*;

class Main {
    public static void main(String[] args) {
        int[] a = {10, 3, 6, 3, 312, 7, 4, 9, 1, 9987};

        for (int i = 0; i < a.length - 1; i++) {
            int min = i;

            for (int j = i + 1; j < a.length; j++) {
                if (a[j] < a[min]) {
                    min = j;
                }
            }

            int temp = a[min];
            a[min] = a[i];
            a[i] = temp;
        }

        System.out.println(Arrays.toString(a));
    }
}
