class Solution {
    public List<Integer> findDuplicates(int[] nums) {
        
        ArrayList<Integer> a = new ArrayList<>();
        int i = 0;
        while(i<nums.length)
        {
            int correct = nums[i]-1;
            if(nums[i] != nums[correct])
            {
                int t = nums[correct];
                nums[correct] = nums[i];
                nums[i] = t;
            }
            else
                i++;
        }
        for(i = 0;i<nums.length;i++)
        {
            if(nums[i] != i+1)
                a.add(nums[i]);
        }
        return a;

    }
}
