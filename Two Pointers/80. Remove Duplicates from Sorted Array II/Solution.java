class Solution {
    public int removeDuplicates(int[] nums) {

        int i = 0, j = 2;
        int n = nums.length;

        for(i = 2; i < n; i++) {

            if(nums[i] != nums[j - 2]) {

                nums[j] = nums[i];
                j++;
            }
        }

        return j;
    }
}
