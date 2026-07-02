# 442. Find All Duplicates in an Array

**Difficulty:** Medium

**Pattern:** Cyclic Sort

**Companies:** Amazon, Google, Microsoft, Adobe, Facebook

**LeetCode Link:** https://leetcode.com/problems/find-all-duplicates-in-an-array/

---

# Problem Statement

Given an integer array `nums` of length `n`, where every element is in the range **[1, n]** and appears either **once or twice**, return all the elements that appear **twice**.

The algorithm must run in:

* **O(n)** Time
* **O(1)** Auxiliary Space (excluding the returned list)

---

## Example

### Example 1

```text
Input:
nums = [4,3,2,7,8,2,3,1]

Output:
[2,3]
```

### Example 2

```text
Input:
nums = [1,1,2]

Output:
[1]
```

### Example 3

```text
Input:
nums = [1]

Output:
[]
```

---

# Intuition

Since every number lies in the range **1...n**, every number has a fixed correct position.

```
Value : 1 2 3 4 5 6 7 8
Index : 0 1 2 3 4 5 6 7
```

Correct Index = **Value - 1**

Using Cyclic Sort, every unique number is placed at its correct position.

If a number appears twice, one copy reaches its correct position while the second copy cannot be placed because that position is already occupied by the same value.

After sorting, every index containing an incorrect value represents a duplicate number.

---

# Approach (Cyclic Sort)

1. Traverse the array.
2. Compute the correct index:

```
correct = nums[i] - 1
```

3. If the current number is not already at its correct position, swap it.
4. Otherwise, move to the next index.
5. After Cyclic Sort, traverse the array again.
6. Whenever

```
nums[i] != i + 1
```

the value `nums[i]` is a duplicate.

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

Compare values with their indices.

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

Duplicate numbers are

```
2
3
```

Output

```
[2,3]
```

---

# Why It Works

* Every unique number is moved to its correct position.
* A duplicate cannot occupy its correct position because another copy already exists there.
* Therefore, the duplicate remains at an incorrect index.
* Every misplaced value after Cyclic Sort is a duplicate.

---

# Complexity Analysis

| Complexity      | Value                                    |
| --------------- | ---------------------------------------- |
| Time            | **O(n)**                                 |
| Auxiliary Space | **O(1)** *(excluding the returned list)* |

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
nums[i]
```

is a duplicate number.

---

# Pattern Recognition

Think of **Cyclic Sort** whenever:

* Numbers are in the range **1...n**
* Missing numbers are asked
* Duplicate numbers are asked
* Need **O(n)** time
* Need **O(1)** auxiliary space

---

# Related Problems

* 268. Missing Number
* 287. Find the Duplicate Number
* 448. Find All Numbers Disappeared in an Array
* 645. Set Mismatch
* 41. First Missing Positive

---

# Amazon Interview

This problem has been reported in **Amazon** interviews to evaluate:

* Array manipulation
* In-place algorithms
* Cyclic Sort pattern recognition
* Time and space optimization

---

# Key Takeaways

* Correct Index = `Value - 1`
* Place every number at its correct position.
* Duplicates naturally stop further swapping.
* Every misplaced value after sorting is a duplicate.
* Time Complexity: **O(n)**
* Auxiliary Space: **O(1)** (excluding the returned list)

---

## Interview Tip

> Whenever an array contains numbers in the range **1...n** and the question asks for **duplicates**, **missing numbers**, or **misplaced numbers** with **O(n)** time and **O(1)** auxiliary space, the first pattern to consider is **Cyclic Sort**.
