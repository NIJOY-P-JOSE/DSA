class Solution:
    def minDays(self, bloomDay: list[int], m: int, k: int) -> int:
        def isPossible(days):
            bqts = 0
            flow = 0
            for d in bloomDay:
                if d<=days:
                    flow += 1
                    if flow == k:
                        bqts += 1
                        flow = 0
                else:
                    flow = 0
            return bqts>=m

        if len(bloomDay)<m*k:
            return -1
        l = min(bloomDay)
        r = max(bloomDay)
        while l<=r:
            mid = (l+r)//2
            if isPossible(mid):
                r = mid-1
            else:
                l = mid+1
        return l
