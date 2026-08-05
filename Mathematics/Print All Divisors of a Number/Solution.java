import java.util.*;

public class Main
{
	public static void main(String[] args) {		

		Scanner s = new Scanner(System.in);
		while(true)
		{
		    ArrayList<Integer> list = new ArrayList<>();
		    int n = s.nextInt();
		    for(int i = 1;i<=Math.sqrt(n);i++)
		    {
		        if(n%i == 0)
		        {
		            if(!(n/i == i))
		                list.add(n/i);
		            System.out.print(i+" ");
		        }
		    }
		    for(int i = list.size()-1; i>=0; i--)
		        System.out.print(list.get(i)+" ");
		}
	}
}
