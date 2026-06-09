class Solution {
    public int search(int[] nums, int target) {

        int pivot = findPivot(nums);
        if(pivot == -1) // Not rotated 
            return  Search(0, nums.length-1, nums, target); // Normal Binar Search for Array that not rotated 
        
        if(target==nums[pivot]) // If target is pivot
            return pivot;


        // Start allways graeter than the elements after pivot

        if(target>=nums[0]) // Left side of Pivot, ( Start(small) <= Target(large) ) 
            return Search(0, pivot-1, nums, target);
        else // Left side of Pivot, ( Start(large) > Target(small) ) 
            return Search(pivot+1,nums.length-1, nums, target);
    }

    public int findPivot(int[] nums)
    {
        int s = 0, e = nums.length-1;

        while(s<=e)
        {
            int mid = s + ( e - s ) / 2;
            if(e>mid && nums[mid]>nums[mid+1])
                return mid;
            else if(s<mid && nums[mid]<nums[mid-1])
                return mid-1;
            else if(nums[s]<=nums[mid]) // Located in left part of array
                s = mid + 1;
            else if(nums[s]>nums[mid]) // Located in Right part of array
                e = mid-1;
        }
        return -1; // No pivot or it not rotated
    }

    public int Search(int s, int e,int[] nums, int target)
    {
        // Binary Search
        while(s<=e)
        {
            int mid = s+(e-s)/2;
            if(nums[mid]>target)
                 e = mid-1;
            else if(nums[mid]<target)
                s = mid+1;
            else
                return mid;
        }
        return -1;
    }
}
