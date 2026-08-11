class Solution {
    public String longestPalindrome(String s) {
        
        int n = s.length();
        boolean[][] dp = new boolean[n][n];

        for(int i = 0;i<n;i++)
            dp[i][i] = true;
        
        int maxlen = 1;
        int start = 0;

        for(int len = 2; len<=n;len++)
        {
            for(int i = 0;i+len<=n;i++)
            {
                int j = i+len-1;
                if(s.charAt(i)==s.charAt(j))
                {
                    if(len==2)
                        dp[i][j] = true;
                    else
                        dp[i][j]=dp[i+1][j-1];
                }

                if(dp[i][j] && len>maxlen)
                {
                    start = i;
                    maxlen = len;
                }
            }
        }
        return s.substring(start,start+maxlen);
    }
}
