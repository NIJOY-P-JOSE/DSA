class Solution:
    def minSubArrayLen(self, target: int, nums: List[int]) -> int:
        mc = float('inf')

        l = 0
        s = 0
        for i in range(len(nums)):
            s = s+nums[i]

            while s >= target:
                mc = min(mc,i-l+1)
                s = s-nums[l]
                l=l+1
        if mc == float('inf'): return 0
        else: return mc
