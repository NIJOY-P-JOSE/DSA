class Solution {
    public int countNegatives(int[][] grid) {
        int ans = 0;
        for(int i = 0; i<grid.length;i++)
            ans += bs(grid[i]);
        return ans;

    }
    int bs(int[] arr)
    {
        int s = 0;
        int e = arr.length-1;
        while(s<=e)
        {
            int mid = s+(e-s)/2;
            if(arr[mid]>=0)
                s = mid+1;
            else
                e = mid-1;
        }
        return arr.length-s;
    }
}
