class Solution:
    def combinationSum2(self, candidates: List[int], target: int) -> List[List[int]]:
        n = len(candidates)
        ans = []
        candidates.sort()

        def bt(current, s, start):
            if s == target:
                ans.append(current)
                return

            if s > target:
                return

            for i in range(start, n):
                if i > start and candidates[i] == candidates[i - 1]:
                    continue

                bt(current + [candidates[i]], s + candidates[i], i + 1)

        bt([], 0, 0)
        return ans
