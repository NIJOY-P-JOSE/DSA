class Solution {
    public int[] searchRange(int[] nums, int target) {
    
        int f = search(nums,target, true);
        int l = search(nums,target, false);
       
        return new int[]{f,l};
    }

    public int search(int[] nums, int target,boolean isStart)
    {
        
        int s = 0, e = nums.length-1;
        int ans =-1;

        while(s<=e)
        {
            int mid = s+(e-s)/2;
            if(target<nums[mid])
                e = mid - 1;
            else if(target>nums[mid])
                s = mid + 1;
            else
            {
                ans = mid;
                if(isStart)
                    e = mid -1;
                else
                    s = mid+1;
            }
        }
        return ans;
    }
}
