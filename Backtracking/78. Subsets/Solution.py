class Solution:
    def subsets(self, nums: List[int]) -> List[List[int]]:
        n = len(nums)
        ans = []

        def bt(current, i):
            if i == n:
                ans.append(current)
                return

            # Take nums[i]
            bt(current + [nums[i]], i + 1)

            # Skip nums[i]
            bt(current, i + 1)

        bt([], 0)
        return ans
