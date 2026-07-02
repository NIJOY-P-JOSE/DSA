# 41. First Missing Positive

**Difficulty:** Hard

**Pattern:** Cyclic Sort

**Companies:** Amazon, Google, Microsoft, Apple, Adobe

**LeetCode Link:** https://leetcode.com/problems/first-missing-positive/

---

# Problem Statement

Given an unsorted integer array `nums`, return the **smallest missing positive integer**.

The algorithm must run in:

* **O(n)** Time
* **O(1)** Auxiliary Space

---

## Example

### Example 1

```text
Input:
nums = [1,2,0]

Output:
3
```

### Example 2

```text
Input:
nums = [3,4,-1,1]

Output:
2
```

### Example 3

```text
Input:
nums = [7,8,9,11,12]

Output:
1
```

---

# Intuition

Unlike the standard Cyclic Sort problems, this array may contain:

* Negative numbers
* Zero
* Numbers greater than `n`

Only the numbers in the range **1...n** can affect the answer.

Therefore,

* Ignore every number outside this range.
* Place every valid positive number at its correct position.

```text
Value : 1 2 3 4 5
Index : 0 1 2 3 4
```

Correct Index = **Value - 1**

After sorting,

the first index where

```text
nums[i] != i + 1
```

gives the smallest missing positive integer.

If every position is correct,

the answer is

```text
n + 1
```

---

# Approach (Modified Cyclic Sort)

1. Traverse the array.
2. Ignore:

   * Negative numbers
   * Zero
   * Numbers greater than `n`
3. For valid numbers (`1...n`):

   * Compute

```text
correct = nums[i] - 1
```

4. Swap until the current number reaches its correct position.
5. Scan the array.
6. The first index where

```text
nums[i] != i + 1
```

is the answer.
7. If every position is correct, return

```text
n + 1
```

---

# Dry Run

Input

```text
[3,4,-1,1]
```

After Modified Cyclic Sort

```text
[1,-1,3,4]
```

Compare values with indices.

| Index | Value | Expected |
| ----: | ----: | -------: |
|     0 |     1 |      1 ✅ |
|     1 |    -1 |      2 ❌ |
|     2 |     3 |      3 ✅ |
|     3 |     4 |      4 ✅ |

The first mismatch occurs at index **1**.

Therefore,

```text
Missing Positive = 2
```

---

# Why It Works

Only the numbers between **1 and n** can occupy valid positions.

* Negative numbers cannot be the smallest missing positive.
* Zero cannot be the answer.
* Numbers greater than `n` also cannot affect the smallest missing positive.

By placing every valid number into its correct position, the first missing index directly gives the answer.

---

# Complexity Analysis

| Complexity      | Value    |
| --------------- | -------- |
| Time            | **O(n)** |
| Auxiliary Space | **O(1)** |

---

# Key Observation

Only process numbers satisfying

```text
1 <= nums[i] <= n
```

Correct Index

```text
Value - 1
```

After sorting,

```text
nums[i] != i + 1
```

means

```text
Answer = i + 1
```

If no mismatch exists,

```text
Answer = n + 1
```

---

# Pattern Recognition

Think of **Modified Cyclic Sort** whenever:

* Numbers are unsorted
* Negative values exist
* Zero exists
* Values larger than `n` exist
* Need **O(n)** time
* Need **O(1)** auxiliary space

---

# Related Problems

* 268. Missing Number
* 287. Find the Duplicate Number
* 442. Find All Duplicates in an Array
* 448. Find All Numbers Disappeared in an Array
* 645. Set Mismatch

---

# Amazon Interview

This problem has been reported in **Amazon** interviews because it tests:

* In-place array manipulation
* Cyclic Sort pattern recognition
* Edge case handling
* Time and space optimization

---

# Key Takeaways

* Ignore numbers outside the range **1...n**.
* Correct Index = `Value - 1`.
* Only valid positive numbers participate in Cyclic Sort.
* The first incorrect index gives the smallest missing positive.
* If every index is correct, return `n + 1`.
* Time Complexity: **O(n)**.
* Auxiliary Space: **O(1)**.

---

## Interview Tip

> This is one of the most important **Hard** problems based on the **Cyclic Sort** pattern. Whenever you see an unsorted array with positive, negative, and out-of-range values, along with an `O(n)` time and `O(1)` space requirement, consider **Modified Cyclic Sort**. The key insight is to ignore invalid values and place only numbers in the range **1...n** into their correct positions.
