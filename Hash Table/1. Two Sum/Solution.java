class Solution {
    public int[] twoSum(int[] nums, int target) {
        
        HashMap<Integer, Integer> sh = new HashMap<>();
        int diff = 0;
        int l = 0;
        for(int i=0;i<nums.length;i++)
        {
            diff = target-nums[i];
            if(sh.containsKey(diff))
            {
                l = i;
                break;
            }
            else
                sh.put(nums[i],i);
        }
        return new int[]{sh.get(diff), l};
    }
}
