def MIN_SUBSTRINGS_COUNT(A, N, B, M):
    substring = set()
    
    for i in range(N):
        c = ""
        for j in range(i, N):
            c += A[j]
            substring.add(c)
    
    ans = 0
    i = 0

    while i < M:
        longest = 0

        for j in range(i, M):
            if B[i:j+1] in substring:
                longest = j - i + 1
            else:
                break

        if longest == 0:
            return -1

        i += longest
        ans += 1

    return ans
