# 724. Find Pivot Index

* **Difficulty:** Easy
* **Main DSA Pattern:** Prefix Sum / Running Sum
* **Important Related Patterns:** Array Traversal, Left-Right Sum
* **LeetCode:** https://leetcode.com/problems/find-pivot-index/

---

## ⚡ Quick Revision

|                      |                                                              |
| -------------------- | ------------------------------------------------------------ |
| **Goal**             | Find the leftmost index where left sum = right sum           |
| **Pattern**          | Prefix Sum / Running Sum                                     |
| **Main Idea**        | Calculate total sum once, maintain left sum while traversing |
| **Right Sum**        | `totalSum - leftSum - nums[i]`                               |
| **Condition**        | `leftSum == rightSum`                                        |
| **Initial Left Sum** | `0`                                                          |
| **Time**             | `O(n)`                                                       |
| **Space**            | `O(1)`                                                       |

### ⚡ One-Line Idea

> **Right Sum = Total Sum − Left Sum − Current Element**

---

## 🧠 Problem in Simple Words

For every index `i`, we need:

```text
sum of elements before i
        =
sum of elements after i
```

The current element `nums[i]` is **not included** in either side.

For:

```text
[1, 7, 3, 6, 5, 6]
```

At index `3`:

```text
Left  = 1 + 7 + 3 = 11
Right = 5 + 6 = 11
```

So the answer is:

```text
3
```

If there are multiple pivot indices, return the **leftmost** one.

---

## 🔥 How to Recognize This Problem in a Placement

### 🚨 Recognition Clues

Look for:

* "left sum"
* "right sum"
* "sum before index"
* "sum after index"
* "find an index where two sides are equal"
* "for every index, compare the elements on both sides"

These strongly suggest a **Prefix Sum / Running Sum** approach.

### Recognition Trigger

> **"For every index, compare the sum on the left and right."**

Think:

```text
Total Sum + Running Left Sum
```

---

## 🧩 How to Think / Derive the Solution

### Step 1 — Start with the obvious approach

For every index `i`, calculate:

```text
leftSum  = sum(nums[0:i])
rightSum = sum(nums[i+1:])
```

This works, but repeatedly calculating sums is expensive.

For example:

```text
i = 0 → calculate right sum
i = 1 → calculate right sum again
i = 2 → calculate right sum again
...
```

This can become `O(n²)`.

We need to avoid recalculating sums.

---

### Step 2 — Ask: Can we calculate the right sum from something we already know?

Let:

```text
totalSum = sum of entire array
```

At index `i`:

```text
totalSum
    =
leftSum + nums[i] + rightSum
```

Therefore:

```text
rightSum = totalSum - leftSum - nums[i]
```

This is the main trick.

---

### Step 3 — Maintain the left sum

Initially:

```text
leftSum = 0
```

For each index:

```text
rightSum = totalSum - leftSum - nums[i]
```

Check:

```text
leftSum == rightSum
```

If yes:

```text
return i
```

After checking the current index, add the current element to the left side:

```text
leftSum += nums[i]
```

---

## 📊 Visual Explanation

For:

```text
nums = [1, 7, 3, 6, 5, 6]
```

Total:

```text
1 + 7 + 3 + 6 + 5 + 6 = 28
```

At index `3`:

```text
leftSum = 1 + 7 + 3 = 11
nums[3] = 6

rightSum = 28 - 11 - 6
         = 11
```

Therefore:

```text
leftSum == rightSum
11 == 11
```

Answer:

```text
3
```

### Important order

At each index:

```text
1. Calculate right sum
2. Compare left and right
3. Update left sum
```

Don't add `nums[i]` to `leftSum` before checking, because the current element must belong to **neither side**.

---

## 🔁 Algorithm

```text
1. Calculate total sum of the array.
2. Set leftSum = 0.
3. Traverse the array.
4. Calculate:
       rightSum = totalSum - leftSum - nums[i]
5. If leftSum == rightSum:
       return i
6. Add nums[i] to leftSum.
7. If no pivot exists, return -1.
```

---

## 💻 My Solution — Python

Your optimized solution:

```python
class Solution:
    def pivotIndex(self, nums: List[int]) -> int:
        ts = sum(nums)
        ls = 0

        for i in range(len(nums)):
            rs = ts - ls - nums[i]

            if rs == ls:
                return i

            ls = ls + nums[i]

        return -1
```

### Code Explanation

#### 1. Calculate total sum

```python
ts = sum(nums)
```

`ts` contains the sum of the entire array.

---

#### 2. Initialize left sum

```python
ls = 0
```

At index `0`, there are no elements on the left.

Therefore:

```text
leftSum = 0
```

---

#### 3. Traverse the array

```python
for i in range(len(nums)):
```

We check every index from left to right.

Because we traverse from left to right, the **first valid pivot** is automatically the leftmost pivot.

---

#### 4. Calculate right sum

```python
rs = ts - ls - nums[i]
```

Using:

```text
Total = Left + Current + Right
```

we derive:

```text
Right = Total - Left - Current
```

---

#### 5. Check pivot condition

```python
if rs == ls:
    return i
```

If both sides have the same sum, we found the pivot.

---

#### 6. Update left sum

```python
ls = ls + nums[i]
```

Now the current element becomes part of the left side for the **next index**.

---

#### 7. No pivot

```python
return -1
```

If the entire array is checked without finding a pivot.

---

## ☕ Java Version

```java
class Solution {
    public int pivotIndex(int[] nums) {
        int totalSum = 0;

        for (int num : nums) {
            totalSum += num;
        }

        int leftSum = 0;

        for (int i = 0; i < nums.length; i++) {
            int rightSum = totalSum - leftSum - nums[i];

            if (leftSum == rightSum) {
                return i;
            }

            leftSum += nums[i];
        }

        return -1;
    }
}
```

### Java Logic

The exact same logic is used:

```text
totalSum → sum of entire array

leftSum → sum before current index

rightSum → totalSum - leftSum - nums[i]
```

Then:

```text
if leftSum == rightSum
    return i
```

---

## 🆚 Brute Force vs Optimized

### Brute Force

Your commented approach essentially does:

```python
ls = sum(nums[0:i])
rs = sum(nums[i+1:])
```

for every index.

### Complexity

```text
For each index:
    calculate left sum  → O(n)
    calculate right sum → O(n)

Total → O(n²)
```

---

### Optimized

Calculate the total sum **once**:

```python
ts = sum(nums)
```

Then calculate each right sum in `O(1)`:

```python
rs = ts - ls - nums[i]
```

Total:

```text
O(n)
```

This is the important optimization to recognize in a placement.

---

## 🧠 Pattern Connection

This problem is a direct application of **Prefix Sum / Running Sum** thinking.

Previously:

```text
Running Sum
    ↓
Maintain information about elements before current index
```

Here:

```text
Total Sum
    +
Running Left Sum
    ↓
Derive Right Sum
```

The important transformation is:

```text
Left + Current + Right = Total

Right = Total - Left - Current
```

### Pattern Evolution

```text
1480. Running Sum
        ↓
Store/maintain prefix information
        ↓
724. Pivot Index
        ↓
Use total sum + prefix information
        ↓
560. Subarray Sum Equals K
        ↓
Prefix Sum + HashMap
```

So this problem is useful preparation for harder prefix-sum questions.

---

## ⚔️ Similar Problems / Variations

| Problem                                  | Main Difference                         |
| ---------------------------------------- | --------------------------------------- |
| **1480. Running Sum of 1d Array**        | Directly calculate prefix/running sums  |
| **1991. Find the Middle Index in Array** | Essentially the same problem            |
| **560. Subarray Sum Equals K**           | Prefix sum + HashMap to count subarrays |
| **303. Range Sum Query - Immutable**     | Prefix sum used for range queries       |
| **525. Contiguous Array**                | Prefix-style transformation + HashMap   |

---

## 🚨 Common Mistakes

### 1. Including the current element

Wrong:

```text
leftSum + nums[i]
```

The pivot element belongs to neither side.

Correct:

```text
rightSum = totalSum - leftSum - nums[i]
```

---

### 2. Updating `leftSum` too early

Wrong order:

```python
ls += nums[i]
rs = ts - ls - nums[i]
```

The current element has already been moved into the left side.

Correct order:

```python
rs = ts - ls - nums[i]

if rs == ls:
    return i

ls += nums[i]
```

---

### 3. Using `>` instead of `==`

The condition is:

```text
leftSum == rightSum
```

Not:

```text
leftSum > rightSum
```

or:

```text
leftSum >= rightSum
```

---

### 4. Forgetting edge cases

For:

```text
[2, 1, -1]
```

At index `0`:

```text
left = 0
right = 1 + (-1) = 0
```

Therefore:

```text
answer = 0
```

This reminds you that the pivot can be at either edge.

---

## ⏱️ Complexity

### Optimized Solution

```text
Time Complexity:  O(n)
Space Complexity: O(1)
```

Why?

* One pass to calculate total sum → `O(n)`
* One pass to find pivot → `O(n)`
* Only a few variables → `O(1)` extra space

Overall:

```text
O(n) + O(n) = O(n)
```

---

## 🎯 Placement-Level Takeaway

The most important part of this problem is **not the code**.

Recognize this structure:

```text
Need left sum + right sum
        ↓
Calculate total sum once
        ↓
Maintain left sum
        ↓
Derive right sum
        ↓
right = total - left - current
```

### Placement Decision Process

When you see:

> "Find an index where the sum of elements before it equals the sum after it."

Immediately think:

```text
Prefix Sum
    ↓
Total Sum
    ↓
Running Left Sum
    ↓
Right = Total - Left - Current
```

This converts an `O(n²)` approach into `O(n)`.

---

## ⚡ Final 30-Second Cheat Sheet

```text
┌──────────────────────────────────────────┐
│        724. FIND PIVOT INDEX             │
├──────────────────────────────────────────┤
│ Pattern: Prefix Sum / Running Sum        │
│                                          │
│ total = sum(nums)                        │
│ left = 0                                 │
│                                          │
│ for each i:                              │
│     right = total - left - nums[i]       │
│                                          │
│     if left == right:                    │
│         return i                         │
│                                          │
│     left += nums[i]                      │
│                                          │
│ return -1                                │
├──────────────────────────────────────────┤
│ Time:  O(n)                              │
│ Space: O(1)                              │
└──────────────────────────────────────────┘
```

### 🧠 Remember

> **"Total = Left + Current + Right" → `Right = Total - Left - Current`.**
