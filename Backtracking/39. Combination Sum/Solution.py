class Solution:
    def combinationSum(self, candidates: List[int], target: int) -> List[List[int]]:
        n = len(candidates)
        ans = []

        def bt(current, s, start):
            if s == target:
                ans.append(current)
                return

            if s > target:
                return

            for i in range(start, n):
                bt(current + [candidates[i]], s + candidates[i], i)

        bt([], 0, 0)
        return ans
