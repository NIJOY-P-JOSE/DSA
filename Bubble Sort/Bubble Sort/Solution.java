import java.util.*;

/**
 * Bubble Sort:
 * ------------
 * Each iteration find the Max value place in end.
 * It sort in descending order
 * Find last element (max), then second last element,and go on to first element 
 * If no swap happend in iterations (After the full iteraition of second loop) then the array already is in sorted order
 */

class Main {
    public static void main(String[] args) {
    
        int[] a = {10,3,6,3,394,7,9,0,1};
        boolean swap;
        for(int i=0;i<a.length-1;i++)
        {
            swap  = false;
            for(int j = 1;j<a.length-i;j++)
            {
                if(a[j-1]>a[j])
                {
                    int t = a[j];
                    a[j] = a[j-1];
                    a[j-1] = t;
                    swap = true;
                }
            }
            if(!swap)
                break;
        }
        System.out.println(Arrays.toString(a));
    }
}
