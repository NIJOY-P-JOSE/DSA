# 645. Set Mismatch

**Difficulty:** Easy

**Pattern:** Cyclic Sort

**Companies:** Google, Amazon, Microsoft, Adobe, Bloomberg

**LeetCode Link:** https://leetcode.com/problems/set-mismatch/

---

# Problem Statement

You are given an integer array `nums` of length `n` representing a set that originally contained all numbers from **1 to n**.

Due to an error:

* One number appears **twice**.
* One number is **missing**.

Return an array containing:

```text
[duplicate, missing]
```

The solution should run in **O(n)** time using **O(1)** extra space.

---

## Example

### Example 1

```text
Input:
nums = [1,2,2,4]

Output:
[2,3]
```

### Example 2

```text
Input:
nums = [1,1]

Output:
[1,2]
```

---

# Intuition

Since every number belongs to the range **1...n**, every number has a fixed correct position.

```text
Value : 1 2 3 4 5
Index : 0 1 2 3 4
```

Correct Index = **Value - 1**

Using **Cyclic Sort**, place every number at its correct index.

* The duplicate number cannot occupy its correct position because another copy is already there.
* Therefore, after sorting:

  * The misplaced value is the **duplicate**.
  * The expected value for that index is the **missing** number.

---

# Approach (Cyclic Sort)

1. Traverse the array.
2. Compute the correct index:

```text
correct = nums[i] - 1
```

3. If the current number is not at its correct position, swap it.
4. Otherwise, move to the next index.
5. After Cyclic Sort, scan the array.
6. The first index where

```text
nums[i] != i + 1
```

indicates:

* Duplicate = `nums[i]`
* Missing = `i + 1`

---

# Dry Run

Input

```text
[1,2,2,4]
```

After Cyclic Sort

```text
[1,2,2,4]
```

Compare values with their indices.

| Index | Value | Expected |
| ----: | ----: | -------: |
|     0 |     1 |      1 ✅ |
|     1 |     2 |      2 ✅ |
|     2 |     2 |      3 ❌ |
|     3 |     4 |      4 ✅ |

At index **2**:

```text
Value = 2
Expected = 3
```

Therefore,

```text
Duplicate = 2
Missing = 3
```

Output

```text
[2,3]
```

---

# Why It Works

* Every unique number reaches its correct position.
* The duplicate number blocks the position where the missing number should have been placed.
* The first incorrect index after Cyclic Sort directly provides both answers.

---

# Complexity Analysis

| Complexity | Value    |
| ---------- | -------- |
| Time       | **O(n)** |
| Space      | **O(1)** |

---

# Key Observation

```text
Correct Index = Value - 1
```

After Cyclic Sort,

```text
nums[i] != i + 1
```

means:

* Duplicate = `nums[i]`
* Missing = `i + 1`

---

# Pattern Recognition

Think of **Cyclic Sort** whenever:

* Numbers are in the range **1...n**
* Duplicate and missing numbers are asked together
* Need **O(n)** time
* Need **O(1)** extra space

---

# Related Problems

* 268. Missing Number
* 287. Find the Duplicate Number
* 442. Find All Duplicates in an Array
* 448. Find All Numbers Disappeared in an Array
* 41. First Missing Positive

---

# Interview Notes

This problem is an extension of the Cyclic Sort pattern.

Unlike:

* **448** → Return all missing numbers.
* **442** → Return all duplicate numbers.

This problem requires finding **both** the duplicate and the missing number in a single traversal after Cyclic Sort.

---

# Key Takeaways

* Correct Index = `Value - 1`
* Use Cyclic Sort to place numbers correctly.
* After sorting, the incorrect index gives both answers.
* `nums[i]` → Duplicate
* `i + 1` → Missing
* Time Complexity: **O(n)**
* Space Complexity: **O(1)**

---

## Interview Tip

> If the array contains numbers in the range **1...n** and the problem asks for **one duplicate and one missing number**, Cyclic Sort is one of the simplest and most efficient solutions. After sorting, the first mismatched index immediately reveals both values.
