class Solution {
    public int countPrimes(int n) {
        boolean[] a = new boolean[n];
        for(int i = 2; i*i<n; i++)
        {
            if(!a[i])
            {
                for(int j = i*2; j<n; j+=i)
                    a[j] = true;
            }
        }
        int c = 0;
        for(int i = 2; i<n; i++)
        {
            if(!a[i])
                c++;
        }
        return c;
    }
}
