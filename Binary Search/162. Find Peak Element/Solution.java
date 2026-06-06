class Solution {
    public int searchInsert(int[] nums, int target) {
        
        int s = 0, e = nums.length - 1;
        int mid = -1;
        while(s<=e)
        {
            mid = s+(e-s)/2;
            if(target>nums[mid])
            {
                s=mid+1;
            }
            else if(target<nums[mid])
            {
                 e=mid-1;
            }
            else 
                return mid;
        }
        
        if(target>nums[mid])
            return s;
        return mid;
    }
}
