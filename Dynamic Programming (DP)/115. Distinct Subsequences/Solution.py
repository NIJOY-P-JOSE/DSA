class Solution:
    def numDistinct(self, s: str, t: str) -> int:

        ls = len(s)
        lt = len(t)
        dp = [[0]*(lt+1) for _ in range(ls+1)]

        for i in range(ls+1):
            dp[i][0] = 1

        for i in range(1,ls+1):
            for j in range(1,lt+1):
                if s[i-1]==t[j-1]:
                    dp[i][j] = dp[i-1][j-1]+dp[i-1][j]
                else:
                    dp[i][j] = dp[i-1][j]

        return dp[ls][lt]
