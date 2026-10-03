class Solution:
    def search(self, nums: list[int], target: int) -> int:

        # Binary Search on a rotated sorted array.
        #
        # Even though the whole array is not sorted,
        # at least ONE of the two halves will always be sorted.
        #
        # At every iteration:
        # 1. Find the middle.
        # 2. Check if target is found.
        # 3. Identify which half is sorted.
        # 4. Check whether target lies inside that sorted half.
        # 5. Search the appropriate half.

        l = 0
        r = len(nums) - 1

        while l <= r:

            # Find the middle index.
            mid = (l + r) // 2

            # Target found.
            if target == nums[mid]:
                return mid

            # ------------------------------------------------
            # CASE 1: LEFT HALF IS SORTED
            # ------------------------------------------------
            #
            # If nums[l] <= nums[mid], everything from l to mid
            # is in increasing order.
            #
            # Example:
            # [4, 5, 6, 7, 0, 1, 2]
            #  l     mid
            #  └── sorted ──┘
            #
            if nums[l] <= nums[mid]:

                # Check whether target lies inside the sorted
                # left half.
                #
                # nums[l] <= target < nums[mid]
                #
                # If yes, discard the right half.
                if nums[l] <= target < nums[mid]:
                    r = mid - 1

                # Otherwise, target must be in the right half.
                else:
                    l = mid + 1

            # ------------------------------------------------
            # CASE 2: RIGHT HALF IS SORTED
            # ------------------------------------------------
            #
            # If the left half is not sorted, then the right
            # half must be sorted.
            #
            # Example:
            # [6, 7, 0, 1, 2, 4, 5]
            #        mid        r
            #        └── sorted ──┘
            #
            else:

                # Check whether target lies inside the sorted
                # right half.
                #
                # nums[mid] < target <= nums[r]
                #
                # If yes, discard the left half.
                if nums[mid] < target <= nums[r]:
                    l = mid + 1

                # Otherwise, target must be in the left half.
                else:
                    r = mid - 1

        # Search space became empty.
        # Target does not exist in the array.
        return -1
