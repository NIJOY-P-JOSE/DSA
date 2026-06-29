import java.util.*;

class Main {
    public static void main(String[] args) {
        int[] a ={10,3,6,3,312,7,4,9,1,9987};
        for(int i = 0;i<a.length-1;i++)
        {
            for(int j = i+1;j>0;j--)
            {
                if( a[j-1]>a[j])
                {
                    int t = a[j-1];
                    a[j-1] = a[j];
                    a[j] = t;
                }
                else
                    break;
            }
        }
        
        System.out.println(Arrays.toString(a));
    }
}
