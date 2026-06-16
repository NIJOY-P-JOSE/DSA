class Solution {
    public int arrangeCoins(int n) {

        long s = 1;
        long e = n;
        while(s<=e)
        {
            long mid = s+(e-s)/2;
            long coins = mid*(mid+1)/2;
            if(coins==n)
                return (int)mid;
            else if(coins>n)
                e = mid-1;
            else
                s = mid+1;
        }
        return (int)e;
        
    }
}
