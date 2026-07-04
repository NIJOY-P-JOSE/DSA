import java.util.*;

class Main {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int c;
        int n = s.nextInt();
        for(int i = 0;i<n;i++)
        {
            c = i+1;
            for(int j = 0;j<n-c;j++)
                System.out.print("  ");
            
            for(int j = 0;j<c;j++)
                System.out.print("* ");
            System.out.println();
        }
        System.out.println();
        
        for(int i = 0;i<n;i++)
        {
            c = n-i;
            for(int j = 0;j<n-c;j++)
                System.out.print("  ");
            
            for(int j = 0;j<c;j++)
                System.out.print("* ");
            System.out.println();
        }
        System.out.println();
        
        // 1 3 5 7 9
        for(int i = 0;i<n;i++)
        {
            c = i+1;
            for(int j = 0;j<n-c;j++)
                System.out.print(" ");
 
            for(int j = 0;j<c;j++)
                System.out.print("*");
            for(int j = 0;j<c-1;j++)
                System.out.print("*");
            
            System.out.println();
        }
        System.out.println();
        
        for(int i = 0;i<n;i++)
        {
            c = n-i;
            for(int j = 0;j<n-c;j++)
                System.out.print(" ");
                
            for(int j = 0;j<c;j++)
                System.out.print("*");
            
            for(int j = 0; j<c-1;j++)
                System.out.print("*");
                
            System.out.println();
        }
        System.out.println();
        
        //     4
        //    3 5
        //   2   6
        //  1     7
        // 0
        for(int i =0;i<n-1;i++)
        {
            c = i;
            for(int j = 0;j<=n+c;j++)
            {
                if(j==n-c-1 || j==n+c-1)
                    System.out.print("*");
                else
                    System.out.print(" ");
            }
            System.out.println();
        }
        for(int i = 0;i<n+n-1;i++)
            System.out.print("*");
        System.out.println();
    }
}
