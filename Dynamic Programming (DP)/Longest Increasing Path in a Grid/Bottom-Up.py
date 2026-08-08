def LONGESTINCPATHLEN(GRID):
    r = len(GRID)
    c = len(GRID[0])

    dp = [[-1] * c for _ in range(r)]

    # Destination
    dp[r - 1][c - 1] = 1

    for i in range(r - 1, -1, -1):
        for j in range(c - 1, -1, -1):

            if i == r - 1 and j == c - 1:
                continue

            # Move Right
            if (j < c - 1
                    and GRID[i][j] < GRID[i][j + 1]
                    and dp[i][j + 1] != -1):

                dp[i][j] = max(
                    dp[i][j],
                    1 + dp[i][j + 1]
                )

            # Move Down
            if (i < r - 1
                    and GRID[i][j] < GRID[i + 1][j]
                    and dp[i + 1][j] != -1):

                dp[i][j] = max(
                    dp[i][j],
                    1 + dp[i + 1][j]
                )

    return dp[0][0]
