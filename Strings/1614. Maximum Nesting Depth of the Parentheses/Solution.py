class Solution:
    def maxDepth(self, s: str) -> int:
        maxP = 0
        p = 0

        for i in s:
            if i == "(":
                p += 1

            if i == ")":
                p -= 1

            maxP = max(maxP, p)

        return maxP
