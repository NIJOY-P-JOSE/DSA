class Solution {
    public int numberOfSteps(int num) {
        int c = 0;
        return rec(num,c);
    }

    int rec(int n, int c)
    {
        if(n==0)
            return c;
        
        ++c;
        if(n%2==0)
            n = n/2;
        else
            --n;
        
        return rec(n,c);
    }
}
