# 162. Find Peak Element

This repository contains a Java solution for the **Find Peak Element** problem using **Binary Search**.

## 📌 Problem Statement

Given an integer array `nums`, find the index of **any peak element**.

A peak element is an element that is **strictly greater than its adjacent elements**.

The array satisfies:

* `nums[i] != nums[i + 1]`
* `nums[-1] = nums[n] = -∞`

The required time complexity is **O(log n)**.

### Example

```text
Input:
[1,2,3,1]

Output:
2

Explanation:
3 is greater than both of its neighbours.
```

---

## 💡 Approach

Instead of checking every element, Binary Search is used.

At every iteration:

* Compare `nums[mid]` with `nums[mid + 1]`.
* If `nums[mid] < nums[mid + 1]`, the array is ascending at `mid`, so **a peak must exist on the right side**.
* Otherwise, the array is descending or already at a peak, so **a peak lies on the left side (including `mid`)**.

The search space is reduced by half in every iteration until only one element remains.

---

## 🧠 Key Observation

There is **always at least one peak** in the array.

* If we move upward (`nums[mid] < nums[mid + 1]`), we are guaranteed to eventually reach a peak.
* If we move downward (`nums[mid] > nums[mid + 1]`), a peak already exists on the current side.

This property allows Binary Search to work without explicitly checking both neighbours.

---

## 🔄 Algorithm

1. Initialize `start = 0` and `end = n - 1`.
2. While `start < end`:

   * Compute `mid`.
   * If `nums[mid] < nums[mid + 1]`

     * Search the right half.
   * Else

     * Search the left half including `mid`.
3. When `start == end`, it points to a peak element.
4. Return the index.

---

## ⏱️ Complexity Analysis

* **Time Complexity:** `O(log n)`
* **Space Complexity:** `O(1)`

---

## 💻 Java Solution

```java
class Solution {
    public int findPeakElement(int[] nums) {

        int s = 0, e = nums.length - 1;

        while (s < e) {
            int mid = s + (e - s) / 2;

            if (nums[mid] < nums[mid + 1])
                s = mid + 1;
            else
                e = mid;
        }

        return s;
    }
}
```

---

## 🎯 Binary Search Pattern Used

This problem follows the **Peak Finding Binary Search** pattern.

Instead of searching for a specific value, Binary Search is used to locate a position where the slope changes from increasing to decreasing.

---

## 📚 Concepts Practiced

* Binary Search
* Peak Finding
* Divide and Conquer
* Search Space Reduction
* Interview Pattern Recognition

---

## 🚀 Author

**Nijoy P Jose**

This solution is part of my **Data Structures & Algorithms** placement preparation repository, where I implement and document different Binary Search patterns in Java.
