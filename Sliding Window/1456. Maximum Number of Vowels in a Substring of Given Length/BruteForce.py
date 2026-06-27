class Solution:
    def maxVowels(self, s: str, k: int) -> int:
        mc = 0
        
        for i in range(len(s)-k+1):
            c = 0
            for j in range(i,i+k):
                if s[j] in "aeiou":
                    c+=1
            mc = max(mc,c)
        return mc
