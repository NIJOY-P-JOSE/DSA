def maximum_error(error_count, K):
    n = len(error_count)

    if n <= K:
        return sum(error_count)

    window_sum = sum(error_count[:K])
    maximum = window_sum

    for i in range(K, n):
        window_sum = window_sum - error_count[i - K] + error_count[i]
        maximum = max(maximum, window_sum)

    return maximum
