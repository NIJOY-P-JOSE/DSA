class Solution:
    def numDecodings(self, s: str) -> int:
        mem = {}

        def dp(i):
            if i == len(s):
                return 1

            if s[i] == "0":
                return 0

            if i in mem:
                return mem[i]

            # Take one digit
            ways = dp(i + 1)

            # Take two digits if valid
            if i + 1 < len(s) and 10 <= int(s[i:i+2]) <= 26:
                ways += dp(i + 2)

            mem[i] = ways
            return ways

        return dp(0)
