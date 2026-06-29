# 448. Find All Numbers Disappeared in an Array

**Difficulty:** Easy

**Pattern:** Cyclic Sort

**Companies:** Google, Amazon, Microsoft, Adobe, Facebook and many others

**LeetCode Link:** https://leetcode.com/problems/find-all-numbers-disappeared-in-an-array/

---

## Problem Statement

Given an integer array `nums` of length `n`, where every element is in the range **[1, n]**, some numbers appear twice while others are missing.

Return all the numbers in the range **[1, n]** that do not appear in the array.

The solution should run in **O(n)** time.

The returned list does **not** count as extra space.

---

## Example

### Example 1

```
Input:
nums = [4,3,2,7,8,2,3,1]

Output:
[5,6]
```

### Example 2

```
Input:
nums = [1,1]

Output:
[2]
```

---

# Intuition

Since every number lies in the range **1...n**, every number has a fixed correct position.

```
Value : 1 2 3 4 5 6 7 8
Index : 0 1 2 3 4 5 6 7
```

Correct Index = **Value - 1**

We repeatedly swap elements until every possible number reaches its correct index.

Because duplicate numbers occupy the position of missing numbers, some indices will still contain incorrect values after sorting.

Those indices directly tell us which numbers are missing.

---

# Approach (Cyclic Sort)

1. Traverse the array.
2. Compute the correct index:

```
correct = nums[i] - 1
```

3. If the current number is not already at its correct position, swap it.
4. Otherwise, move to the next index.
5. After Cyclic Sort, scan the array.
6. Whenever

```
nums[i] != i + 1
```

the number

```
i + 1
```

is missing.

---

# Dry Run

Input

```
[4,3,2,7,8,2,3,1]
```

After Cyclic Sort

```
[1,2,3,4,3,2,7,8]
```

Now compare values with their indices.

| Index | Value | Expected |
| ----: | ----: | -------: |
|     0 |     1 |      1 ✅ |
|     1 |     2 |      2 ✅ |
|     2 |     3 |      3 ✅ |
|     3 |     4 |      4 ✅ |
|     4 |     3 |      5 ❌ |
|     5 |     2 |      6 ❌ |
|     6 |     7 |      7 ✅ |
|     7 |     8 |      8 ✅ |

Missing numbers are

```
5
6
```

Output

```
[5,6]
```

---

# Why It Works

During Cyclic Sort,

* Every unique number is placed at its correct index.
* Duplicate numbers cannot occupy two different positions.
* Therefore, they block the positions where missing numbers should have been placed.

The incorrect indices after sorting represent the missing numbers.

---

# Complexity Analysis

| Complexity | Value                                    |
| ---------- | ---------------------------------------- |
| Time       | **O(n)**                                 |
| Space      | **O(1)** *(excluding the returned list)* |

---

# Key Observation

```
Correct Index = Value - 1
```

After Cyclic Sort,

```
nums[i] != i + 1
```

means

```
Missing Number = i + 1
```

---

# Interview Notes

### Pattern Recognition

Think of **Cyclic Sort** whenever:

* Numbers are in the range **1...n**
* Missing numbers are asked
* Duplicate numbers are present
* Need **O(n)** time
* Need **O(1)** extra space

---

# Similar Problems

* 268. Missing Number
* 287. Find the Duplicate Number
* 442. Find All Duplicates in an Array
* 41. First Missing Positive
* 645. Set Mismatch

---

# Google Interview

This problem has been reported in **Google** interviews to test:

* Pattern recognition
* In-place array manipulation
* Cyclic Sort understanding
* Time and space optimization

---

# Key Takeaways

* Correct Index = `Value - 1`
* Keep swapping until the current number reaches its correct position.
* Duplicates naturally stop further swapping.
* Incorrect indices after sorting directly represent missing numbers.
* Time Complexity: **O(n)**
* Space Complexity: **O(1)** (excluding the returned list)

---

## Interview Tip

> Whenever the array contains numbers in the range **1...n** and the question asks for **missing numbers**, **duplicates**, or **misplaced numbers** with **O(n)** time and **O(1)** extra space, the first pattern you should think of is **Cyclic Sort**.
