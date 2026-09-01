def SUPERMARKET(A, N, B, M):

    price = A[M-1]

    if price > B:
        return 0

    target = B-price

    dp = [0] * (target+1)
    dp[0] = 1

    for coin in A:
        for i in range(coin, target+1):
            dp[i] = (dp[i] + dp[i-coin]) % 1000000007

    return dp[target]
