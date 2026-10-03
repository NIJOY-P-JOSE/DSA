class Solution:
    def shipWithinDays(self, weights: list[int], days: int) -> int:
        def isPossible(cap):
            d = 1; curw = 0
            for w in weights:
                if curw+w <= cap:
                    curw += w
                else:
                    d += 1
                    curw = w
            return d<=days

        l = max(weights)
        r = sum(weights)
        while l<=r:
            mid = (l+r)//2
            if isPossible(mid):
                r = mid-1
            else:
                l = mid+1
        return l
