class Solution:
    def longestConsecutive(self, nums: List[int]) -> int:
        maxc = 0
        s = set(nums)
        for n in s:
            if n-1 not in s:
                c=1
                cn = n
                while cn+1 in s:
                    c = c+1
                    cn+=1
                maxc= max(maxc,c)
        return maxc
