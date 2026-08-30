def SUBSEQRECUR(ipstr):
    n = len(ipstr)
    ans = set()

    def bt(current, i):
        if current:
            ans.add(current)

        for j in range(i, n):
            bt(current + ipstr[j], j + 1)

    bt("", 0)
    return sorted(ans)
