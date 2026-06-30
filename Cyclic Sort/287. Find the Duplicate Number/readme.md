# 287. Find the Duplicate Number (Cyclic Sort Approach)

> **Note:** This solution demonstrates the **Cyclic Sort** approach to find the duplicate number. Although it correctly finds the duplicate, it **modifies the input array**, so it does **not satisfy** the original LeetCode constraint of *not modifying the array*. The accepted solution uses **Floyd's Cycle Detection Algorithm (Tortoise and Hare)**.

**Difficulty:** Medium

**Pattern:** Cyclic Sort

**Companies:** Google, Amazon, Microsoft, Apple, Adobe

**LeetCode Link:** https://leetcode.com/problems/find-the-duplicate-number/

---

# Problem Statement

Given an array `nums` containing `n + 1` integers where each integer is in the range **[1, n]**, there is exactly **one duplicate number**.

Return the duplicate number.

### Constraints

* The duplicate number may appear more than twice.
* The original problem requires:

  * **Do not modify the array**
  * **O(1) extra space**
  * **O(n) time**

This implementation satisfies **O(n)** time and **O(1)** extra space, but **modifies the input array**.

---

# Example

### Example 1

```
Input:
[1,3,4,2,2]

Output:
2
```

### Example 2

```
Input:
[3,1,3,4,2]

Output:
3
```

### Example 3

```
Input:
[3,3,3,3,3]

Output:
3
```

---

# Intuition

Since every number lies in the range **1...n**, every number has a fixed correct position.

```
Value : 1 2 3 4 5
Index : 0 1 2 3 4
```

Correct Index = **Value - 1**

Using Cyclic Sort, every number is moved to its correct position.

If a duplicate exists, one copy reaches its correct position while the other cannot be placed because that position is already occupied by the same value.

After sorting, the index where

```
nums[i] != i + 1
```

contains the duplicate number.

---

# Approach

1. Traverse the array.
2. Compute the correct index:

```
correct = nums[i] - 1
```

3. If the current number is not at its correct position, swap it.
4. Otherwise, move to the next index.
5. After Cyclic Sort, scan the array.
6. The first index where

```
nums[i] != i + 1
```

contains the duplicate number.

---

# Dry Run

Input

```
[1,3,4,2,2]
```

After Cyclic Sort

```
[1,2,3,4,2]
```

Compare values with indices

| Index | Value | Expected |
| ----: | ----: | -------: |
|     0 |     1 |      1 ✅ |
|     1 |     2 |      2 ✅ |
|     2 |     3 |      3 ✅ |
|     3 |     4 |      4 ✅ |
|     4 |     2 |      5 ❌ |

Since

```
nums[4] = 2
```

Duplicate Number

```
2
```

---

# Why It Works

* Every unique number is placed at its correct index.
* The duplicate number cannot occupy two different positions.
* Therefore, one occurrence remains at an incorrect index.
* That misplaced value is the duplicate.

---

# Complexity Analysis

| Complexity | Value    |
| ---------- | -------- |
| Time       | **O(n)** |
| Space      | **O(1)** |

---

# Limitation

This approach **modifies the input array** by performing swaps.

Therefore, it **does not satisfy** the original LeetCode constraint:

> "You must solve the problem without modifying the array."

For the accepted solution, use **Floyd's Cycle Detection Algorithm (Tortoise and Hare)**.

---

# Pattern Recognition

Think of this approach when:

* Numbers are in the range **1...n**
* In-place modification is allowed
* Need **O(n)** time
* Need **O(1)** extra space

---

# Related Problems

* 268. Missing Number
* 448. Find All Numbers Disappeared in an Array
* 442. Find All Duplicates in an Array
* 645. Set Mismatch
* 41. First Missing Positive

---

# Key Takeaways

* Correct Index = `Value - 1`
* Swap elements until they reach their correct positions.
* The remaining misplaced value is the duplicate.
* Time Complexity: **O(n)**
* Space Complexity: **O(1)**
* ❌ Not accepted for LeetCode 287 because it modifies the input array.

---

## Interview Tip

> If the problem **allows modifying the array**, the **Cyclic Sort** approach is a simple and efficient solution.

> If the problem explicitly states **"Do not modify the array"**, use **Floyd's Cycle Detection Algorithm** instead.
