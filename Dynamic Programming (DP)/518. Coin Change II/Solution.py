class Solution:
    def change(self, amount: int, coins: List[int]) -> int:
        
        dp = [0]*(amount+1)
        dp[0] = 1     # To achive 0 no need of any coins. This is a one way to achive 0 amount.
        for c in coins:
            for i in range(c,amount+1):
                dp[i] += dp[i-c]
        return dp[amount]
