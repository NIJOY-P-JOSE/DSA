class Solution {
    public int[] findErrorNums(int[] nums) {
        int[] a = new int[2];
        int i = 0, n = nums.length;
        while(i<n)
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
        for(i = 0;i<n;i++)
        {
            if(nums[i] != i+1)
            {
                a[0] = nums[i];
                break;
            }
        }
        a[1] = i+1;
        return a;
    }
}
