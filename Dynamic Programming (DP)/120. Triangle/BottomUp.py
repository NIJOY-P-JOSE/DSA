class Solution:
    def minimumTotal(self, triangle: List[List[int]]) -> int:
        r = len(triangle)

        if r == 1 and len(triangle[0]) == 1:
            return triangle[0][0]

        dp = triangle

        for i in range(len(triangle) - 2, -1, -1):
            for j in range(len(triangle[i]) - 1, -1, -1):
                dp[i][j] += min(
                    dp[i+1][j],
                    dp[i+1][j+1]
                )

        return dp[0][0]
