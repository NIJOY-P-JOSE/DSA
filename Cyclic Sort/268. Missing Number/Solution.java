class Solution {
    public int missingNumber(int[] nums) {
        int i =0;
        while(i<nums.length)
        {
            int correct = nums[i];
            if(nums[i] != nums.length && nums[i] != nums[correct])
            {
                int t = nums[i];
                nums[i] = nums[correct];
                nums[correct] = t;
            }
            else
                i++;
        }
        for(i = 0; i<nums.length; i++)
        {
            if(nums[i]!=i)
                return i;
        }
        return nums.length;
    }
}
