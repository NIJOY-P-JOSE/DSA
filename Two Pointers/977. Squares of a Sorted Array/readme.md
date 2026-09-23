# 977. Squares of a Sorted Array

* **Difficulty:** Easy
* **Main DSA Pattern:** Two Pointers
* **Important Related Patterns:** Array Traversal · Reverse Filling · Sorted Array
* **LeetCode:** https://leetcode.com/problems/squares-of-a-sorted-array/

---

## ⚡ Quick Revision

| Concept              | Remember                                         |
| -------------------- | ------------------------------------------------ |
| **Goal**             | Return squares in sorted order                   |
| **Pattern**          | Two Pointers                                     |
| **Pointers**         | `left = 0`, `right = n - 1`                      |
| **Key Observation**  | Largest square comes from one of the two ends    |
| **Result Direction** | Fill from right → left                           |
| **Decision**         | Compare `abs(nums[left])` and `abs(nums[right])` |
| **Time**             | `O(n)`                                           |
| **Space**            | `O(n)`                                           |

### ⚡ One-Line Idea

> **Compare the absolute values at both ends, put the larger square at the end of the result, and move that pointer.**

---

## 🧠 Problem in Simple Words

The input array is already sorted:

```text
[-4, -1, 0, 3, 10]
```

But after squaring:

```text
[16, 1, 0, 9, 100]
```

it is no longer sorted.

We need:

```text
[0, 1, 9, 16, 100]
```

The challenge is to do this in **O(n)** instead of:

```text
Square everything → Sort
```

which would take `O(n log n)`.

---

## 🔥 How to Recognize This Problem in a Placement

### Recognition Clues

Look for:

* Array is already sorted.
* Negative and positive numbers are possible.
* An operation can destroy the original sorted order.
* Need the result sorted.
* Asked for `O(n)`.

### Recognition Trigger

> **"Sorted array + negative numbers + square + sorted result"**

Think:

```text
Two Pointers
      ↓
Compare both ends
      ↓
Fill result from the back
```

---

## 🧩 How to Think / Derive the Solution

### Step 1 — Why can't we simply traverse normally?

Consider:

```text
[-7, -3, 2, 3, 11]
```

If we square normally:

```text
[49, 9, 4, 9, 121]
```

The order is destroyed.

So simply traversing from left to right doesn't give sorted squares.

---

### Step 2 — Where can the largest square come from?

Look at:

```text
[-7, -3, 2, 3, 11]
  ↑              ↑
 left           right
```

The largest absolute value must be at one of the ends.

Here:

```text
|-7| = 7
|11| = 11
```

Therefore the largest square must be one of:

```text
7² = 49
11² = 121
```

We don't need to check the middle elements for the largest square.

This is the key observation that gives us the `O(n)` solution.

---

### Step 3 — Use two pointers

Start with:

```text
left = 0
right = n - 1
```

At every step:

```text
compare |nums[left]| and |nums[right]|
```

The larger absolute value produces the larger square.

---

### Step 4 — Where should we put the larger square?

We are finding the **largest** remaining square.

Therefore, put it at the end of the result.

```text
result[n - 1]
```

Then:

```text
result[n - 2]
result[n - 3]
...
result[0]
```

So we traverse the result backwards.

```text
for i = n - 1 → 0
```

---

### Step 5 — Move the pointer we used

If the right side is larger:

```text
result[i] = nums[right]²
right--
```

Otherwise:

```text
result[i] = nums[left]²
left++
```

Repeat until the entire result is filled.

---

## 📊 Visual Explanation

For:

```text
[-4, -1, 0, 3, 10]
```

Initially:

```text
 left                  right
  ↓                      ↓
[-4, -1,  0,  3, 10]
```

Compare:

```text
|-4| = 4
|10| = 10
```

`10` is larger:

```text
result[4] = 100
right--
```

Now:

```text
[-4, -1, 0, 3, 10]
  ↑           ↑
 left        right
```

Compare:

```text
|-4| = 4
|3|  = 3
```

Left is larger:

```text
result[3] = 16
left++
```

Continue:

```text
result = [_, _, 9, 16, 100]
```

Then:

```text
result = [_, 1, 9, 16, 100]
```

Finally:

```text
result = [0, 1, 9, 16, 100]
```

---

## 🔁 Algorithm

1. Create a result array of the same size.
2. Set `left = 0`.
3. Set `right = n - 1`.
4. Start filling the result from `n - 1`.
5. Compare the absolute values at `left` and `right`.
6. Put the larger square at the current result position.
7. Move the corresponding pointer.
8. Continue until the result is completely filled.
9. Return the result.

---

## 💻 My Solution — Java

```java
class Solution {
    public int[] sortedSquares(int[] nums) {
        
        int n = nums.length;
        int[] numSquare = new int[n];

        int left = 0;
        int right = n - 1;

        for (int i = n - 1; i >= 0; i--) {

            if (Math.abs(nums[right]) > Math.abs(nums[left])) {
                numSquare[i] = nums[right] * nums[right];
                right--;
            } else {
                numSquare[i] = nums[left] * nums[left];
                left++;
            }
        }

        return numSquare;
    }
}
```

---

## 🔍 Code Explanation

### 1. Create result array

```java
int[] numSquare = new int[n];
```

We need a separate array because we are filling the answer in a different order.

---

### 2. Initialize two pointers

```java
int left = 0;
int right = n - 1;
```

They represent the two ends of the sorted input.

```text
left → [ ... ] ← right
```

---

### 3. Fill result from right to left

```java
for (int i = n - 1; i >= 0; i--)
```

The largest square is found first.

Therefore, it belongs at the end of the result.

---

### 4. Compare absolute values

```java
Math.abs(nums[right]) > Math.abs(nums[left])
```

We compare magnitudes rather than the actual values.

For example:

```text
-7 < 3
```

but:

```text
|-7| > |3|
```

and therefore:

```text
(-7)² > 3²
```

---

### 5. Use the right pointer

```java
numSquare[i] = nums[right] * nums[right];
right--;
```

If the right element has the larger absolute value, its square is the largest remaining square.

---

### 6. Otherwise use the left pointer

```java
numSquare[i] = nums[left] * nums[left];
left++;
```

The left element has the larger absolute value, so we put its square into the current position.

---

## 🧠 Why Does This Work?

The input is sorted:

```text
negative → 0 → positive
```

As we move away from zero:

```text
absolute value increases
```

Therefore, the largest absolute value among the remaining elements must always be at one of the two boundaries.

So:

```text
largest remaining square
        ↓
either left or right
```

We compare those two candidates and place the larger one at the end of the result.

After removing that candidate by moving its pointer, the same property remains true for the remaining elements.

That's why the algorithm can process every element exactly once.

---

## 🧠 Pattern Connection

This is an important **Two Pointers** pattern.

There are different ways two pointers can be used.

### This problem

```text
left →          ← right
```

Compare both ends.

### Important mental model

```text
Sorted Array
     ↓
Need largest/smallest among ends
     ↓
Two Pointers
     ↓
Compare left and right
```

The particularly useful variation here is:

> **Find the largest value from both ends and fill the answer backwards.**

---

## 🆚 Brute Force vs Optimized

### Brute Force

```text
1. Square every element
2. Sort the result
```

Complexity:

```text
O(n) + O(n log n)
= O(n log n)
```

### Optimized

```text
Two pointers
+
Compare both ends
+
Fill from right to left
```

Complexity:

```text
O(n)
```

This is exactly the type of optimization to look for when a problem explicitly asks for `O(n)`.

---

## ⚔️ Similar Problems / Variations

| Problem                                     | Pattern                              |
| ------------------------------------------- | ------------------------------------ |
| **167. Two Sum II**                         | Two pointers on a sorted array       |
| **26. Remove Duplicates from Sorted Array** | Two pointers + in-place modification |
| **283. Move Zeroes**                        | Two pointers + in-place modification |
| **11. Container With Most Water**           | Two pointers from both ends          |
| **15. 3Sum**                                | Sorting + two pointers               |

A useful progression is:

```text
977
 ↓
167
 ↓
11
 ↓
15
```

The pointer technique becomes progressively more involved.

---

## 🚨 Common Mistakes

### 1. Squaring and sorting

This works:

```text
square → sort
```

but gives:

```text
O(n log n)
```

The follow-up specifically asks for `O(n)`.

---

### 2. Filling result from left to right

The largest square is discovered first.

So it should go at:

```text
result[n - 1]
```

not:

```text
result[0]
```

Therefore:

```java
for (int i = n - 1; i >= 0; i--)
```

---

### 3. Comparing actual values

Don't compare:

```text
nums[left] > nums[right]
```

Example:

```text
-7 > 3
```

is false.

But:

```text
|-7| > |3|
```

is true.

You need to compare their magnitudes.

---

### 4. Moving the wrong pointer

If the left element was used:

```java
left++;
```

If the right element was used:

```java
right--;
```

Only move the pointer whose value you placed into the result.

---

### 5. Forgetting that duplicates are allowed

For:

```text
[-7, -3, 2, 3, 11]
```

both:

```text
(-3)² = 9
3² = 9
```

Duplicates are perfectly valid.

---

## ⏱️ Complexity

### Time

```text
O(n)
```

Each element is processed exactly once.

The `left` pointer only moves forward and the `right` pointer only moves backward.

### Space

```text
O(n)
```

The result array contains `n` elements.

### Final

```text
Time:  O(n)
Space: O(n)
```

The `O(n)` space is required for the returned output array.

---

## 🎯 Placement-Level Takeaway

When you see:

> **Sorted array + transformation can destroy sorted order + need O(n)**

ask:

```text
Can I identify the largest/smallest result
from the two ends?
```

For this problem:

```text
Sorted input
      ↓
Largest square comes from an end
      ↓
Compare LEFT and RIGHT
      ↓
Take larger
      ↓
Put it at result[i]
      ↓
Move that pointer
      ↓
i--
```

### The key derivation

Don't memorize:

```java
Math.abs(nums[right]) > Math.abs(nums[left])
```

Instead derive it:

```text
Need largest square
        ↓
Largest square = largest absolute value
        ↓
Sorted array
        ↓
Largest absolute value must be at an end
        ↓
Compare left and right
```

That is the actual interview skill.

---

## ⚡ Final 30-Second Cheat Sheet

```text
┌──────────────────────────────────────────┐
│  977. SQUARES OF A SORTED ARRAY          │
├──────────────────────────────────────────┤
│ Pattern: Two Pointers                    │
│                                          │
│ left = 0                                 │
│ right = n - 1                            │
│                                          │
│ Fill result RIGHT → LEFT                 │
│                                          │
│ Compare:                                 │
│ |nums[left]| vs |nums[right]|            │
│                                          │
│ Larger → square it                       │
│       → result[i]                        │
│       → move that pointer                │
│                                          │
│ Time:  O(n)                              │
│ Space: O(n)                              │
└──────────────────────────────────────────┘
```

### 🧠 Remember

> **"The largest square is at one of the two ends — take it and fill the answer from the back."**

---

> **Nijoy P Jose**
> This solution is part of my **Data Structures & Algorithms** placement preparation repository, where I document problem-solving patterns, interview techniques, and Java implementations to strengthen my coding skills.
