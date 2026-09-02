class Solution:
    def generateParenthesis(self, n: int) -> List[str]:
        
        ans = []

        def bt(string, open, close):
            if open == n and close == n:
                ans.append(string)
                return

            if open < n:
                bt(string + "(", open + 1, close)

            if close < open:
                bt(string + ")", open, close + 1)

        bt("", 0, 0)

        return ans
