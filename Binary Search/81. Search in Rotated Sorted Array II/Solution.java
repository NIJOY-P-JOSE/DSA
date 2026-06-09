class Solution {
    public boolean search(int[] nums, int target) {
        
        int pivot = findPivot(nums);
        if(pivot==-1) // No rotation. So normal Binary Search
            return bSearch(nums,target,0,nums.length-1);
        
        if(nums[pivot]==target)
            return true;

        if(nums[0]<=target) // Target loacted in lefty part of the piovt (including the pivot). 
            return bSearch(nums, target, 0, pivot-1);
        else if(nums[0]>target) // Target laocted in right part of the pivot. The righ part always smaller than the start element in rotaed array. 
            return bSearch(nums, target, pivot+1,nums.length-1);
        return false; // Not located in array.
    }

    public int findPivot(int[] nums)
    {
        int s =0, e = nums.length-1;

        while(s<=e)
        {
            int mid = s+(e-s)/2;

            if(e>mid && nums[mid]>nums[mid+1]) // If mid is the pivot
                return mid;
            else if(s<mid && nums[mid]<nums[mid-1]) // If pivot is the element before the pivot
                return mid-1;
            
            if(nums[mid]==nums[s] && nums[mid]==nums[e]) // Mid, Start, and End can not be same. So the duplicats are removed (except mid) if s and e are not pivot.
            {
                if(s<e && nums[s]>nums[s+1] ) // check s is pivot (s<e is for not to become out of bound Array)
                    return s;
                s++; // Remove duplicate by moving start
                if(e>s && nums[e]<nums[e-1])// check e is pivot
                    return e-1;
                e--; // Remove duplicate by moving end
            }
            else if( nums[s]<nums[mid] || ( nums[s]==nums[mid] && nums[mid]>nums[e] ))// If the pivot in righ side, OR The left siade are ok (correct ascending oder) to the mid , that mean pivot will be after mid. (OR) if start and mid is equal then end can not be equal and mid will be greater thatn end when pivot is after mid.
                s = mid+1;
            else
                e = mid-1; //pivoit located in left part of mid.
        }
        return -1;
    }

    public boolean bSearch(int[] nums, int target, int s, int e)
    {
        // Normal Binary Search
        while(s<=e)
        {
            int mid = s+(e-s)/2;
            if(nums[mid]>target)
                e = mid-1;
            else if(nums[mid]<target)
                s = mid+1;
            else
                return true;
        }
        return false;
    }
}
