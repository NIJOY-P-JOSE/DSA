class Solution {
    public int firstMissingPositive(int[] nums) {
        
        int i = 0;
        int n = nums.length;
        while(i<n)
        {
            int correct = nums[i]-1;
            if(nums[i]>0 && nums[i]<=n && nums[i] != nums[correct])
            {
                int t = nums[i];
                nums[i] = nums[correct];
                nums[correct] = t;
            } 
            else
                i++;

        }
        for(i = 0;i<n;i++)
        {
            if(nums[i] != i+1)
                return i+1;
        }
        return n+1;
    }
}
