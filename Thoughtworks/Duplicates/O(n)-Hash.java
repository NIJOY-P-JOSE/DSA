import java.util.*;

class Main {
    public static void main(String[] args) {
        System.out.println("");
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        int[] arr = new int[n];
        for(int i=0; i<n; i++)
        {
            arr[i]=s.nextInt();
        }
        
        HashSet<Integer> hs = new HashSet<>();
        int c = 0;
        for(int x:arr){
            if(hs.contains(x))
                c++;
            else
                hs.add(x);
        }
        System.out.println("Count:"+c);
        System.out.println(hs);
        
    }
}
