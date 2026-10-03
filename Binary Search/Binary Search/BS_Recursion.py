class Solution:
    def search(self, nums: list[int], target: int) -> int:

        # Binary Search using Recursion
        #
        # Main idea:
        # The array is sorted, so after checking the middle element,
        # we can eliminate half of the remaining search space.
        #
        # l = left boundary
        # r = right boundary

        def BS(l, r):

            # Base case:
            # If l becomes greater than r, there is no search space left.
            # This means the target does not exist in the array.
            if l > r:
                return -1

            # Find the middle index.
            # (l + r) // 2 gives the middle position.
            mid = (l + r) // 2

            # If the middle element is the target,
            # we have found the answer.
            if target == nums[mid]:
                return mid

            # If target is greater than nums[mid],
            # target can only exist in the RIGHT half.
            #
            # So eliminate the left half including mid.
            elif target > nums[mid]:
                return BS(mid + 1, r)

            # If target is smaller than nums[mid],
            # target can only exist in the LEFT half.
            #
            # So eliminate the right half including mid.
            else:
                return BS(l, mid - 1)

        # Initially, the complete array is the search space.
        return BS(0, len(nums) - 1)
