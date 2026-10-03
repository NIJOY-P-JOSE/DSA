class Solution:
    def minEatingSpeed(self, piles: list[int], h: int) -> int:
        def isPossible(speed):
            hrs = 0
            for pile in piles:
                hrs = hrs + math.ceil(pile/speed)
            return hrs<=h
        l = 1
        r = max(piles)
        while l<=r:
            mid = (l+r)//2
            if isPossible(mid):
                r = mid-1
            else:
                l = mid+1
        return l
