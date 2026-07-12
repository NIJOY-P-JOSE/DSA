import java.util.*;

public class Main
{
	public static void main(String[] args) {
		System.out.println("Hello World");
		Scanner s  = new Scanner(System.in);
		int n = s.nextInt();
		boolean[] a = new boolean[n+1];
		
		for(int i = 2;i*i<=n;i++)
		{
		    if(!a[i])
		    {
		        for(int j = i*2; j<=n; j+=i)
		            a[j] = true;
		    }
		}
		for(int i = 2; i<=n;i++)
		{
		    if(!a[i])
		        System.out.print(i+", ");
		}
	}
}
