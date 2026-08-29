class Solution:
    def subsetsWithDup(self, nums: List[int]) -> List[List[int]]:
        n = len(nums)
        ans = []

        nums.sort()

        def bt(current, i):
            ans.append(current)

            for j in range(i, n):
                if j > i and nums[j] == nums[j - 1]:
                    continue

                bt(current + [nums[j]], j + 1)

        bt([], 0)
        return ans
