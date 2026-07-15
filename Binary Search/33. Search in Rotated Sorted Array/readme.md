# 🔄 Search in Rotated Sorted Array using Binary Search

This repository contains a Java solution for **LeetCode 33 - Search in Rotated Sorted Array** using the **Pivot + Binary Search** approach.

---

# 📌 Problem Statement

Given a sorted array that has been rotated at an unknown pivot, find the index of a target element.

If the target exists, return its index; otherwise, return `-1`.

The solution must run in **O(log n)** time.

### Example 1

```text
Input:
nums = [4,5,6,7,0,1,2]
target = 0

Output:
4
```

### Example 2

```text
Input:
nums = [4,5,6,7,0,1,2]
target = 3

Output:
-1
```

---

# 💡 Intuition

A rotated sorted array consists of **two individually sorted subarrays**.

Example:

```text
Original Array

0 1 2 4 5 6 7
```

After rotation:

```text
4 5 6 7 | 0 1 2
          ↑
        Pivot
```

The pivot is the largest element in the array.

Once the pivot is found:

* The left half is sorted.
* The right half is sorted.

The target must belong to exactly one of these halves, so we only need one additional Binary Search.

---

# 🧠 Approach

The solution is divided into three steps.

## Step 1 : Find the Pivot

The pivot is the element where

```text
nums[i] > nums[i + 1]
```

Example:

```text
4 5 6 7 0 1 2
      ↑
    Pivot
```

If no pivot exists, the array was never rotated.

---

## Step 2 : Decide the Search Space

There are three possibilities.

### Case 1

Target is the pivot.

```text
Return pivot index.
```

---

### Case 2

Target belongs to the left sorted half.

```text
Target >= nums[0]
```

Search from

```text
0 → pivot - 1
```

---

### Case 3

Target belongs to the right sorted half.

```text
Target < nums[0]
```

Search from

```text
pivot + 1 → n - 1
```

---

## Step 3 : Apply Binary Search

Since the selected half is already sorted, perform a normal Binary Search.

---

# 📖 Dry Run

Example

```text
nums = [4,5,6,7,0,1,2]
target = 1
```

### Find Pivot

```text
4 5 6 7 0 1 2
      ↑
    Pivot = 3
```

### Decide Side

```text
nums[0] = 4

target = 1

1 < 4
```

Target must be in the right half.

Search

```text
0 1 2
```

Binary Search returns

```text
Index = 5
```

---

# 🔄 Algorithm

1. Find the pivot.
2. If there is no pivot, perform normal Binary Search.
3. If target equals the pivot, return the pivot index.
4. Determine whether the target lies in the left or right sorted half.
5. Perform Binary Search on the selected half.
6. Return the result.

---

# ⏱️ Complexity Analysis

### Time Complexity

Finding Pivot

```text
O(log n)
```

Binary Search

```text
O(log n)
```

Overall

```text
O(log n)
```

> Although Binary Search is executed twice, the overall complexity remains **O(log n)** because constant factors are ignored in Big-O notation.

---

### Space Complexity

```text
O(1)
```

No extra space is used.

---

# 💻 Java Solution

```java
class Solution {

    public int search(int[] nums, int target) {

        int pivot = findPivot(nums);

        if (pivot == -1)
            return Search(0, nums.length - 1, nums, target);

        if (nums[pivot] == target)
            return pivot;

        if (target >= nums[0])
            return Search(0, pivot - 1, nums, target);

        return Search(pivot + 1, nums.length - 1, nums, target);
    }

    public int findPivot(int[] nums) {

        int s = 0;
        int e = nums.length - 1;

        while (s <= e) {

            int mid = s + (e - s) / 2;

            if (mid < e && nums[mid] > nums[mid + 1])
                return mid;

            if (mid > s && nums[mid] < nums[mid - 1])
                return mid - 1;

            if (nums[s] <= nums[mid])
                s = mid + 1;
            else
                e = mid - 1;
        }

        return -1;
    }

    public int Search(int s, int e, int[] nums, int target) {

        while (s <= e) {

            int mid = s + (e - s) / 2;

            if (nums[mid] == target)
                return mid;

            if (nums[mid] < target)
                s = mid + 1;
            else
                e = mid - 1;
        }

        return -1;
    }
}
```

---

# 🎯 Binary Search Pattern

This problem combines **two Binary Search patterns**:

* **Finding the Pivot (Rotation Point)**
* **Binary Search on a Sorted Half**

This is one of the most common interview patterns for rotated arrays.

---

# 🔗 Related Problems

* Search in Rotated Sorted Array II (Duplicates)
* Find Minimum in Rotated Sorted Array
* Find Minimum in Rotated Sorted Array II
* Rotation Count in Rotated Sorted Array

---

# 📚 Concepts Practiced

* Binary Search
* Pivot Finding
* Rotated Sorted Arrays
* Search Space Reduction
* Divide and Conquer
* Interview Pattern Recognition

---

# 🚀 Author

**Nijoy P Jose**

This solution is part of my **Data Structures & Algorithms** placement preparation repository, where I document Binary Search patterns, interview techniques, and Java implementations to strengthen problem-solving skills.
