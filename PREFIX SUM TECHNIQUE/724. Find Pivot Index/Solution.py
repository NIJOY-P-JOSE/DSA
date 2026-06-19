class Solution:
    def pivotIndex(self, nums: List[int]) -> int:
        # ls = 0
        # rs = 0
        # for i in range(len(nums)):
        #     ls = sum(nums[0:i])
        #     rs = sum(nums[i+1:len(nums)])
        #     if ls == rs:
        #         return i
        # return -1
        
        ts = sum(nums)
        ls=0
        for i in range(len(nums)):
            rs = ts - ls - nums[i]
            if rs == ls:
                return i
            ls = ls + nums[i]
        return -1
            
