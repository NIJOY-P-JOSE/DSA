class Solution:
    def minPathSum(self, grid: List[List[int]]) -> int:
        r = len(grid)
        c = len(grid[0])

        if c == 1 and r == 1:
            return grid[0][0]

        dp = [[0] * c for _ in range(r)]
        dp[0][0] = grid[0][0]

        for i in range(r):
            for j in range(c):
                if i == 0 or j == 0:
                    if j != 0:
                        dp[i][j] = grid[i][j] + dp[i][j-1]
                        continue

                    if i != 0:
                        dp[i][j] = grid[i][j] + dp[i-1][j]
                        continue

                dp[i][j] = min(dp[i-1][j], dp[i][j-1]) + grid[i][j]

        return dp[r-1][c-1]
