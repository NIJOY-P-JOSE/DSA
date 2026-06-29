class Solution {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        
        int i = 0;
        while(i<nums.length)
        {
            int correct = nums[i]-1;
            if(nums[i]!=nums[correct])
            {
                int t = nums[correct];
                nums[correct] = nums[i];
                nums[i] = t;
            }
            else
                i++;
        }
        List<Integer> a = new ArrayList<>();
        for(i=0; i<nums.length;i++)
        {
            if(nums[i]-1!=i)
                a.add(i+1);
        }
        return a;
    }
}
