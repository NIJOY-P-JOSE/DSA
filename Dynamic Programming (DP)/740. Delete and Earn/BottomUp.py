class Solution:
    def deleteAndEarn(self, nums: List[int]) -> int:
        if len(nums) <= 1:
            return nums[0]

        frequency = [0] * (max(nums) + 1)

        for x in nums:
            frequency[x] += 1

        dp = [0] * len(frequency)

        dp[1] = frequency[1]

        for i in range(2, len(frequency)):
            dp[i] = max(
                dp[i - 1],
                dp[i - 2] + frequency[i] * i
            )

        return dp[-1]
