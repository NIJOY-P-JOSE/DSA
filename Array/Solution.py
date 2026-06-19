class Solution:
    def maxProfit(self, prices: List[int]) -> int:
        mp = 0
        minp = float('inf')
        for p in prices:
            minp = min(minp,p)
            mp = max(mp,p-minp)
        return mp
