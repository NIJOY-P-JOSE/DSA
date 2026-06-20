class Solution:
    def isIsomorphic(self, s: str, t: str) -> bool:

        if len(s)!=len(t):
            return False
        else:
            smap = {}
            tmap = {}
            for i in range(len(s)):
                c1 = s[i]
                c2 = t[i]

                if c1 in smap and smap[c1]!=c2:
                    return False
            
                if c2 in tmap and tmap[c2]!=c1:
                    return False
                smap[c1] = c2
                tmap[c2] = c1
            return True
