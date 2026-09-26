class Solution {
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);

        int maxSum = 0;
        int diff = Integer.MAX_VALUE;
        int n = nums.length;

        for (int i = 0; i < n - 2; i++) {

            int left = i + 1;
            int right = n - 1;

            while (left < right) {

                int sum = nums[i] + nums[left] + nums[right];
                int td = Math.abs(target - sum);

                if (td == 0)
                    return target;

                if (td < diff) {
                    diff = td;
                    maxSum = sum;
                }

                if (sum > target)
                    right--;
                else
                    left++;
            }
        }

        return maxSum;
    }
}
