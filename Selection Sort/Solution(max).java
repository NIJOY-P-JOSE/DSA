import java.util.*;

class Main {
    public static void main(String[] args) {
        int[] a ={10,3,6,3,312,7,4,9,1,9987};
        for(int i = 0; i<a.length-1; i++)
        {
            int m = 0;
            for(int j = 1; j<a.length-i; j++)
            {
                if(a[m]<a[j])
                    m = j;
            }
            int t = a[m];
            a[m] = a[a.length-i-1];
            a[a.length-i-1] = t;
        }
        
        System.out.println(Arrays.toString(a));
    }
}
