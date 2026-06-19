class Solution:
    def findMaxAverage(self, nums: List[int], k: int) -> float:
        # ms = float('-inf')
        # for i in range(len(nums)-k+1):
        #     s = 0
        #     for j in range(i, i+k):
        #         s = s+nums[j]
        #     ms = max(s,ms)
        # return ms/k

        ms = cs = sum(nums[:k])
        for i in range(k,len(nums)):
            cs = cs+nums[i]-nums[i-k]
            ms = max(ms,cs)
        return ms/k
