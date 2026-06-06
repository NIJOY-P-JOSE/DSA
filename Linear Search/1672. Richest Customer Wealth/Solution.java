class Solution {
    public int maximumWealth(int[][] accounts) {
        
        int max = 0, sum;
        for(int[] x: accounts)
        {
            sum=0;
            for(int y: x)
            {
                sum+=y;
            }
            if(sum>max)
                max=sum;
        }
        return max;
    }
}
