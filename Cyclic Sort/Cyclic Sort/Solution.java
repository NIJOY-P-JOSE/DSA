import java.util.*;

class Main {
    public static void main(String[] args) {
        
        int[] a = {3,5,2,1,4};
        int i  = 0;
        while(i<a.length)
        {
            int correct = a[i]-1;
            if(a[i]==a[correct])
            {
                i = i+1;
            }
            else
            {
                int t = a[i];
                a[i] = a[correct];
                a[correct] = t;
            }
        }
        System.out.println(Arrays.toString(a));
    }
}
