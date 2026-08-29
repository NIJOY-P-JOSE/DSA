class Solution:
    def permute(self, nums: List[int]) -> List[List[int]]:
        n = len(nums)
        ans = []
        st = [False] * n

        def bt(current):
            if n == len(current):
                ans.append(current)
                return

            for i in range(n):
                if not st[i]:
                    st[i] = True
                    bt(current + [nums[i]])
                    st[i] = False

        bt([])
        return ans
