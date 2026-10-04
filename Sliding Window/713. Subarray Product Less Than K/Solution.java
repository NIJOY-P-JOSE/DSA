class Solution {
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        if(k <= 1)
            return 0;

        int left = 0, c = 0, prod = 1;

        for(int right = 0; right < nums.length; right++){

            prod *= nums[right];

            while(prod >= k){
                prod /= nums[left];
                left++;
            }

            c += right - left + 1; // Add the no. of all combination of subarrays that end in nums[right]
        }

        return c;
    }
}
