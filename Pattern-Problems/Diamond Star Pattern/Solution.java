import java.util.*;

class Main {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        int c = 0;
        for(int i=0;i<n+n-1;i++)
        {
            if(i<n)
                c++;
            else
                c--;
            for(int j=0;j<n-c;j++)
                System.out.print(" ");
            for(int j=0;j<c;j++)
                System.out.print("* ");
            System.out.println();
        }    
    }
}

//     *
//    * *
//   * * *
//  * * * *
// * * * * *
//  * * * *
//   * * * 
//    * *
//     *
