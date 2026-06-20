class Solution:
    def groupAnagrams(self, strs: List[str]) -> List[List[str]]:
        d = {}
        for w in strs:
            sw = "".join(sorted(w))
            if sw in d:
                d[sw] = d[sw] + [w]
            else:
                d[sw] = [w]
        return list(d.values())
