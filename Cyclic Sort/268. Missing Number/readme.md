# 268. Missing Number

**Difficulty:** Easy

**Topic:** Array, Hash Table, Math, Binary Search, Bit Manipulation, Sorting

**Pattern:** Cyclic Sort

**LeetCode:** https://leetcode.com/problems/missing-number/

---

## Problem Statement

Given an array `nums` containing `n` distinct numbers in the range `[0, n]`, return the only number that is missing from the array.

### Example 1

```
Input:
nums = [3,0,1]

Output:
2
```

### Example 2

```
Input:
nums = [0,1]

Output:
2
```

### Example 3

```
Input:
nums = [9,6,4,2,3,5,7,0,1]

Output:
8
```

---

# Approach

This problem can be solved using the **Cyclic Sort** pattern.

Normally, Cyclic Sort is used for numbers from **1 to n**.

Here, the numbers are from **0 to n**, so the correct index of every number is the number itself.

For example,

```
Value : 0 1 2 3 4
Index : 0 1 2 3 4
```

The only exception is the number **n**, because its correct index would be **n**, which is outside the array.

Therefore, we skip swapping whenever the current value is equal to `nums.length`.

After sorting:

* Every number is placed at its correct index.
* The first index where `nums[i] != i` is the missing number.
* If every index is correct, then the missing number is `nums.length`.

---

# Algorithm

1. Start from index `0`.
2. Find the correct index of the current element.
3. If the current value is not equal to `nums.length` and is not already at its correct position, swap it.
4. Otherwise, move to the next index.
5. After Cyclic Sort, scan the array.
6. The first index where `nums[i] != i` is the answer.
7. If every index is correct, return `nums.length`.

---

# Dry Run

Input

```
[3,0,1]
```

### Step 1

```
3 == nums.length

Skip
```

### Step 2

```
Swap 0 with index 0

[0,3,1]
```

### Step 3

```
Swap 1 with index 1

[0,1,3]
```

Now scan the array

```
Index : 0 1 2
Value : 0 1 3
```

Since

```
nums[2] != 2
```

Answer

```
2
```

---

# Complexity Analysis

| Complexity | Value    |
| ---------- | -------- |
| Time       | **O(n)** |
| Space      | **O(1)** |

---

# Key Observation

For numbers from **0 to n**:

```
Correct Index = Value
```

Unlike the standard Cyclic Sort (`value - 1`), here the value itself is the correct index.

The value `n` is skipped because it does not have a valid index inside the array.

---

# Interview Notes

### Pattern Recognition

Think of Cyclic Sort whenever:

* Numbers are within a fixed range.
* Missing number is asked.
* Duplicate number is asked.
* Need **O(n)** time.
* Need **O(1)** extra space.

---

# Key Takeaways

* Correct Index = Value
* Skip the value `n`
* Swap until every number reaches its correct index.
* Scan the array to find the first incorrect index.
* If all indices are correct, return `nums.length`.

---

## Related Problems

* 41. First Missing Positive
* 268. Missing Number
* 287. Find the Duplicate Number
* 442. Find All Duplicates in an Array
* 448. Find All Numbers Disappeared in an Array

---

> **Interview Tip:** Whenever the numbers are in the range **0...n** (or **1...n**) and the question asks for a missing or duplicate element with **O(n)** time and **O(1)** space, think of the **Cyclic Sort** pattern first.
