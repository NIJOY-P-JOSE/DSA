class Solution:
    def canPartition(self, nums: List[int]) -> bool:
        s = sum(nums)
        n = len(nums)

        if s % 2 == 1 or n == 0:
            return False

        target = s // 2

        dp = set()
        dp.add(0)

        for i in range(n - 1, -1, -1):
            if target in dp:
                return True

            x = set()

            for j in dp:
                x.add(j + nums[i])

            dp = dp | x

        if target in dp:
            return True

        return False
