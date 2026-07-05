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
        
        
        for(int i = 0;i<n+n-1;i++)
            System.out.print("*");
        System.out.println();
        for(int i = 1;i<n;i++)
        {
            c = n-i;
            for(int j = 0;j<n+c;j++)
                if(n-c==j || n+c-2==j)
                    System.out.print("*");
                else
                    System.out.print(" ");
            System.out.println();
        }
        System.out.println("\n");
        
        
        c = 0;
        for(int i = 0;i<n+n-1;i++)
        {
            if(i<n)
                c = i;
            else
                c = c-1;
            for(int j = 0;j<n+c;j++)
                if(n-1-c==j || n-1+c==j)
                    System.out.print("*");
                else
                    System.out.print(" ");
            
            System.out.println();
        }
        System.out.println();
        
        
        
        for(int i =0;i<=2*n;i++)
        {
            for(int j = 0;j<=2*n;j++)
            {
                c=Math.min(Math.min(i,j),Math.min(2*n-i,2*n-j));;
                System.out.print(c+" ");
            }
            System.out.println();
        }
        System.out.println();
        
        
        
    
        for(int i =1;i<2*n;i++)
        {
            for(int j = 1;j<2*n;j++)
            {
                c=n-(Math.min(Math.min(i,j),Math.min(2*n-i,2*n-j)))+1;
                System.out.print(c+" ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
