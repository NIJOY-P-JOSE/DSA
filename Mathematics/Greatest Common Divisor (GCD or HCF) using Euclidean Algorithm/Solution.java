import java.util.*;

public class Main
{
	public static void main(String[] args) {
		System.out.println("Hello World");
		
		
		Scanner s = new Scanner(System.in);
		
		while(true)
		{
		   int a = s.nextInt();
		   int b = s.nextInt();
		   System.out.println(GCD(a,b));
		}
	}
	static int GCD(int a, int b)
	{
	    if(a==0)
	        return b;
	   return GCD(b%a, a);
	}
}
