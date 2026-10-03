class Solution {
    public int search(int[] nums, int target) {
        
        int l = 0;
        int r = nums.length - 1;

        while (l <= r) {

            // Find middle of current search space
            int mid = l + (r - l) / 2;

            if (target == nums[mid])
                return mid;

            // Left half is sorted
            if (nums[l] <= nums[mid]) {

                // Target lies in the sorted left half
                if (nums[l] <= target && target < nums[mid])
                    r = mid - 1;
                else
                    l = mid + 1;
            }

            // Right half is sorted
            else {

                // Target lies in the sorted right half
                if (nums[mid] < target && target <= nums[r])
                    l = mid + 1;
                else
                    r = mid - 1;
            }
        }

        // Target not found
        return -1;
    }
}
