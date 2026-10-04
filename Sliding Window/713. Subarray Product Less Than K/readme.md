# 713. Subarray Product Less Than K

[LeetCode Problem](https://leetcode.com/problems/subarray-product-less-than-k/)

**Difficulty:** Medium  
**Pattern:** Variable-Size Sliding Window  
**Language:** Java

---

## Quick Revision

### Pattern: Variable-Size Sliding Window

This problem asks us to count the number of **contiguous subarrays** whose product is less than `k`.

Instead of checking every possible subarray, maintain a sliding window:

```text
[left ........ right]
```

Keep the product of the current window.

### Core Rule

```text
product < k  → valid window
product >= k → invalid window
```

When the product becomes invalid, move `left` forward and divide the elements leaving the window until the product becomes valid again.

### Main Trick

For every valid window:

```text
[left ........ right]
```

the number of valid subarrays ending at `right` is:

```text
right - left + 1
```

So instead of:

```java
count++;
```

we use:

```java
count += right - left + 1;
```

---

# Problem Understanding

Given an array of **positive integers** `nums` and an integer `k`, return the number of contiguous subarrays whose product is strictly less than `k`.

Example:

```text
nums = [10,5,2,6]
k = 100
```

The valid subarrays are:

```text
[10]
[5]
[2]
[6]
[10,5]
[5,2]
[2,6]
[5,2,6]
```

Therefore:

```text
answer = 8
```

---

# Placement Recognition

When you see:

- **Subarray**
- **Contiguous**
- Need to count many subarrays
- Values are **positive**
- A condition can be maintained as the window expands

Think:

> **Sliding Window**

Here the condition is:

```text
product < k
```

Therefore:

```text
Subarray
   ↓
Positive numbers
   ↓
Maintain product
   ↓
Shrink when product becomes too large
   ↓
Variable-Size Sliding Window
```

---

# Why Sliding Window?

A brute-force approach could generate every possible subarray.

For:

```text
nums = [10,5,2,6]
```

we could check:

```text
[10]
[10,5]
[10,5,2]
[10,5,2,6]

[5]
[5,2]
[5,2,6]

[2]
[2,6]

[6]
```

There are `O(n²)` possible subarrays.

Instead, we maintain a window and its product.

When the product becomes too large:

```text
product >= k
```

we shrink the window from the left.

Because all numbers are positive, removing an element from the left decreases the product.

This makes the sliding-window approach possible.

---

# Key Observation

Suppose the current valid window is:

```text
[1, 2, 3]
 ↑     ↑
left  right
```

Every subarray ending at `right` is also valid:

```text
[1,2,3]
[2,3]
[3]
```

There are `3` of them.

And:

```text
right - left + 1
= 2 - 0 + 1
= 3
```

Therefore:

> `right - left + 1` gives the number of valid subarrays ending at the current `right`.

This is the main trick in this problem.

---

# Why Does This Work?

Suppose the current valid window is:

```text
[left ........ right]
```

Possible starting positions are:

```text
left
left + 1
left + 2
...
right
```

Every one of these produces a subarray ending at `right`.

The number of positions is:

```text
right - left + 1
```

For example:

```text
left = 2
right = 5
```

Possible subarrays ending at `5`:

```text
[2...5]
[3...5]
[4...5]
[5...5]
```

Number:

```text
5 - 2 + 1 = 4
```

So we add:

```java
count += right - left + 1;
```

---

# Deriving the Algorithm

### Step 1 — Handle `k <= 1`

Since the array contains positive integers, every non-empty subarray has product at least `1`.

Therefore, if:

```text
k <= 1
```

no subarray can have:

```text
product < k
```

So:

```java
if(k <= 1)
    return 0;
```

---

### Step 2 — Start the window

```java
int left = 0;
int product = 1;
int count = 0;
```

`product` starts at `1` because `1` is the multiplicative identity.

---

### Step 3 — Expand with `right`

```java
for(int right = 0; right < nums.length; right++)
```

Add the new element:

```java
product *= nums[right];
```

---

### Step 4 — Shrink when invalid

If:

```text
product >= k
```

the current window is invalid.

So remove elements from the left:

```java
while(product >= k){
    product /= nums[left];
    left++;
}
```

Continue until:

```text
product < k
```

---

### Step 5 — Count new subarrays

Once the window is valid:

```java
count += right - left + 1;
```

This counts all valid subarrays that end at the current `right`.

---

# Visual Example

Consider:

```text
nums = [10,5,2,6]
k = 100
```

### `right = 0`

```text
[10]
```

Product:

```text
10 < 100
```

Valid subarrays ending here:

```text
[10]
```

Count:

```text
1
```

---

### `right = 1`

```text
[10,5]
```

Product:

```text
50 < 100
```

Valid subarrays ending here:

```text
[10,5]
[5]
```

Count:

```text
2
```

Total:

```text
1 + 2 = 3
```

---

### `right = 2`

```text
[10,5,2]
```

Product:

```text
100
```

Invalid because:

```text
100 >= 100
```

Remove `10`:

```text
[5,2]
```

Product:

```text
10 < 100
```

Valid subarrays ending here:

```text
[5,2]
[2]
```

Count:

```text
2
```

Total:

```text
3 + 2 = 5
```

---

### `right = 3`

Current window:

```text
[5,2]
```

Add `6`:

```text
[5,2,6]
```

Product:

```text
60 < 100
```

Valid subarrays ending here:

```text
[5,2,6]
[2,6]
[6]
```

Count:

```text
3
```

Final:

```text
5 + 3 = 8
```

---

# Standard Java Solution

```java
class Solution {
    public int numSubarrayProductLessThanK(int[] nums, int k) {

        if (k <= 1)
            return 0;

        int left = 0;
        int product = 1;
        int count = 0;

        for (int right = 0; right < nums.length; right++) {

            product *= nums[right];

            while (product >= k) {
                product /= nums[left];
                left++;
            }

            count += right - left + 1;
        }

        return count;
    }
}
```

---

# Code Explanation

### `left`

```java
int left = 0;
```

Marks the beginning of the current valid window.

---

### `product`

```java
int product = 1;
```

Stores the product of all elements currently inside the window.

---

### Expand

```java
product *= nums[right];
```

Add the new element entering from the right.

---

### Shrink

```java
while(product >= k)
```

The window is invalid when its product is greater than or equal to `k`.

Remove elements from the left:

```java
product /= nums[left];
left++;
```

---

### Count

```java
count += right - left + 1;
```

This is the most important line.

It counts every valid subarray ending at `right`.

---

# My Solution

```java
class Solution {
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        if(k <= 1)
            return 0;

        int left = 0, c = 0, prod = 1;

        for(int right = 0; right < nums.length; right++){

            prod *= nums[right];

            while(prod >= k){
                prod /= nums[left];
                left++;
            }

            c += right - left + 1; // Add the no. of all combination of subarrays that end in nums[right]
        }

        return c;
    }
}
```

---

# My Solution Explained

My variables are:

```java
int left = 0, c = 0, prod = 1;
```

Where:

```text
left → beginning of the window
c    → total number of valid subarrays
prod → product of current window
```

### Add the current element

```java
prod *= nums[right];
```

The new element enters the window.

---

### Restore validity

```java
while(prod >= k){
    prod /= nums[left];
    left++;
}
```

If the product becomes too large, keep removing elements from the left.

---

### Count all new subarrays

```java
c += right - left + 1;
```

This was the main new concept I learned from this problem.

For example:

```text
left = 1
right = 4
```

The valid subarrays ending at `right` are:

```text
[1...4]
[2...4]
[3...4]
[4...4]
```

Therefore:

```text
4 - 1 + 1 = 4
```

new subarrays are added to the answer.

---

# Connection With Previous Sliding Window Problems

This problem extends the Sliding Window pattern we used in:

### 209. Minimum Size Subarray Sum

Goal:

```text
Find the shortest valid window
```

When valid:

```text
shrink
```

Answer:

```text
minimum length
```

---

### 1004. Max Consecutive Ones III

Goal:

```text
Find the longest valid window
```

When invalid:

```text
shrink
```

Answer:

```text
maximum length
```

---

### 713. Subarray Product Less Than K

Goal:

```text
Count all valid subarrays
```

When invalid:

```text
shrink
```

Answer:

```text
number of valid subarrays
```

The new trick is:

```text
valid window
      ↓
right - left + 1
      ↓
number of valid subarrays ending at right
```

---

# Important Pattern

For a valid window:

```text
[left ........ right]
```

### Longest window

```java
maxLength = Math.max(maxLength, right - left + 1);
```

### Shortest window

```java
minLength = Math.min(minLength, right - left + 1);
```

### Count all valid subarrays

```java
count += right - left + 1;
```

So the same expression:

```text
right - left + 1
```

can represent:

> **The current window length, which can also be the number of valid subarrays ending at `right` when the window is valid under the problem's monotonic condition.**

---

# Common Mistakes

### 1. Using `count++`

Wrong:

```java
count++;
```

This counts only one subarray for each `right`.

There can be many valid subarrays ending at the same `right`.

Correct:

```java
count += right - left + 1;
```

---

### 2. Forgetting `k <= 1`

```java
if(k <= 1)
    return 0;
```

Without this, the shrinking loop can move `left` beyond the valid range.

---

### 3. Using `if` instead of `while`

Wrong:

```java
if(product >= k)
```

The product may still be invalid after removing one element.

Correct:

```java
while(product >= k)
```

---

### 4. Forgetting that the condition is strict

The problem asks:

```text
product < k
```

not:

```text
product <= k
```

Therefore the invalid condition is:

```java
product >= k
```

---

# Why Positive Numbers Matter

This sliding-window approach works because `nums` contains positive integers.

When we expand:

```text
product increases
```

When we remove an element:

```text
product decreases
```

Therefore, once the product becomes too large, moving `left` forward can restore validity.

This monotonic behavior is what makes the sliding-window approach work.

---

# Complexity

### Time Complexity

```text
O(n)
```

`right` moves forward through the array once.

`left` also only moves forward.

Even though there is a `while` loop, each element can be removed from the window only once.

Therefore:

```text
O(n)
```

---

### Space Complexity

```text
O(1)
```

Only a few variables are used.

---

# Placement Takeaway

When you see:

```text
Count subarrays
+
Contiguous
+
Positive numbers
+
A condition that can be maintained
```

ask:

> **Can I maintain a sliding window and count how many valid starting positions exist for each `right`?**

If yes, the key expression may be:

```java
right - left + 1
```

For this problem:

```text
Expand
   ↓
Update product
   ↓
If product >= k
   ↓
Shrink from left
   ↓
Window becomes valid
   ↓
Count right - left + 1
```

---

# Final Cheat Sheet

```text
Problem:
713. Subarray Product Less Than K

Pattern:
Variable-Size Sliding Window

State:
product

Valid:
product < k

Invalid:
product >= k

Expand:
product *= nums[right]

Shrink:
product /= nums[left]
left++

Count:
count += right - left + 1

Edge case:
k <= 1 → return 0

Time:
O(n)

Space:
O(1)
```

### One-line memory trick

> **713: Maintain a valid product window → every starting position from `left` to `right` gives one valid subarray ending at `right` → add `right - left + 1`.**
