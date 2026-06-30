class Solution {
    public int findDuplicate(int[] nums) {
        int i = 0;
        while(i<nums.length)
        {
            int correct = nums[i]-1;
            if(nums[i] != nums[correct])
            {
                int t = nums[i];
                nums[i] = nums[correct];
                nums[correct] = t;
            }
            else
                i++;
        }
        int ans = 0;
        for(i = 0;i<nums.length;i++)
        {
            if(nums[i]-1 != i)
            {
                ans = nums[i];
                break;
            }
        }
        return ans;
    }
}
