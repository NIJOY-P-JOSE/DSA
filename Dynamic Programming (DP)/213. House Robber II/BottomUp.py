class Solution:
    def rob(self, nums: List[int]) -> int:
        l = len(nums)

        if l <= 1:
            return nums[0]

        def dp(s, e):
            x = 0
            y = 0

            for i in range(s, e):
                t = max(y, x + nums[i])

                x = y
                y = t

            return max(x, y)

        return max(
            dp(0, l - 1),
            dp(1, l)
        )
