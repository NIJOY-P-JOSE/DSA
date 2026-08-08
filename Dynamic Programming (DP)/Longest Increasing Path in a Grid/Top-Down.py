memo = {}

def DP(G, i, j):

    if (i, j) in memo:
        return memo[(i, j)]

    if i == len(G) - 1 and j == len(G[0]) - 1:
        return 1

    ans = -1

    if i < len(G) - 1 and G[i][j] < G[i + 1][j]:
        down = DP(G, i + 1, j)

        if down != -1:
            ans = max(ans, down + 1)

    if j < len(G[0]) - 1 and G[i][j] < G[i][j + 1]:
        right = DP(G, i, j + 1)

        if right != -1:
            ans = max(ans, right + 1)

    memo[(i, j)] = ans
    return ans
