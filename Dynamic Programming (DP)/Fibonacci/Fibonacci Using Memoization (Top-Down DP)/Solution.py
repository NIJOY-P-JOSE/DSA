def FIBHELP(n, m):
    if n <= 1:
        return n

    if n not in m:
        m[n] = FIBHELP(n - 1, m) + FIBHELP(n - 2, m)

    return m[n]



n = int(input())
m = {}
print(FIBHELP(n, m))
