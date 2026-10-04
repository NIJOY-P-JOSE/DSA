# 209. Minimum Size Subarray Sum

**Difficulty:** Medium  
**Main Pattern:** Variable-Size Sliding Window  
**Related Patterns:** Two Pointers, Array, Greedy Window Contraction

🔗 [LeetCode – 209. Minimum Size Subarray Sum](https://leetcode.com/problems/minimum-size-subarray-sum/)

---

## ⚡ Quick Revision

| Concept | Idea |
|---|---|
| Goal | Find the minimum length of a contiguous subarray whose sum is at least `target` |
| Main Pattern | Variable-Size Sliding Window |
| Pointers | `left` and `right` |
| Window State | Current sum |
| Expand | Move `right` forward |
| Shrink | Move `left` forward |
| Valid Window | `sum >= target` |
| Answer | Minimum valid window length |
| Time | `O(n)` |
| Space | `O(1)` |

### 30-Second Idea

> **Expand the window until its sum reaches the target. Once it becomes valid, shrink it from the left as much as possible while keeping it valid. Record the smallest valid window.**

---

# 🧠 Problem in Simple Words

Given an array of **positive integers** and a target value, find the **smallest length of a contiguous subarray whose sum is at least the target**.

Example:

```text
target = 7
nums = [2,3,1,2,4,3]
```

One valid subarray is:

```text
[4,3]
```

Its sum is:

```text
4 + 3 = 7
```

and its length is:

```text
2
```

So the answer is:

```text
2
```

If no such subarray exists, return:

```text
0
```

---

# 🔥 How to Recognize This Problem in a Placement

Look for these keywords:

```text
contiguous subarray
+
minimum length
+
sum >= target
```

This strongly suggests:

```text
Variable-Size Sliding Window
```

The important word here is **contiguous**.

We are not selecting arbitrary elements.

We need a continuous range:

```text
[left ........ right]
```

---

# 🪟 Sliding Window — Main Pattern

Sliding Window is a technique for maintaining a continuous section of an array or string.

We maintain:

```text
left
  ↓
[  window  ]
           ↑
          right
```

The window changes as the pointers move.

There are two important operations:

### Expand

Move `right` forward:

```text
[2,3]
 ↓
[2,3,1]
```

This increases the window.

### Shrink

Move `left` forward:

```text
[2,3,1,2]
 ↓
[3,1,2]
```

This decreases the window.

---

# 🧠 Fixed vs Variable Sliding Window

There are two common types.

## Fixed-Size Sliding Window

The window size is given.

For example:

```text
k = 3
```

The window always contains 3 elements:

```text
[1,2,3]
  [2,3,4]
    [3,4,5]
```

---

## Variable-Size Sliding Window

The window size depends on a condition.

That is what we use here.

For 209:

```text
Valid:
sum >= target
```

The window can have different sizes:

```text
[2,3,1,2]   → length 4
[3,1,2,4]   → length 4
[1,2,4]     → length 3
[2,4,3]     → length 3
[4,3]       → length 2
```

We want the **smallest valid window**.

---

# 🧩 How to Derive the Sliding Window Solution

Consider:

```text
target = 7
nums = [2,3,1,2,4,3]
```

Start with:

```text
left = 0
sum = 0
```

Move `right` forward and add elements.

```text
[2]
sum = 2
```

Not enough.

```text
[2,3]
sum = 5
```

Still not enough.

```text
[2,3,1]
sum = 6
```

Still not enough.

Add another element:

```text
[2,3,1,2]
sum = 8
```

Now:

```text
sum >= target
```

The window is valid.

Its length is:

```text
4
```

But we want the **minimum**.

So we shrink from the left:

```text
[2,3,1,2]
 ↓
[3,1,2]
```

Now:

```text
sum = 6
```

The window became invalid.

So we stop shrinking and continue expanding.

---

# 🔥 The Key Sliding Window Rule

For this problem:

```text
If sum < target
        ↓
Expand
        ↓
right++

If sum >= target
        ↓
Window is valid
        ↓
Record answer
        ↓
Shrink
        ↓
left++
```

The goal is:

> **Expand until valid, then shrink while trying to remain valid.**

This is the core idea of 209.

---

# 💻 Standard Sliding Window Implementation

The most common implementation is:

```java
class Solution {
    public int minSubArrayLen(int target, int[] nums) {
        int left = 0;
        int sum = 0;
        int minLength = Integer.MAX_VALUE;

        for (int right = 0; right < nums.length; right++) {

            sum += nums[right];

            while (sum >= target) {

                minLength = Math.min(
                    minLength,
                    right - left + 1
                );

                sum -= nums[left];
                left++;
            }
        }

        return minLength == Integer.MAX_VALUE ? 0 : minLength;
    }
}
```

---

# 🧠 Understanding the Standard Code

### Expand

```java
sum += nums[right];
```

We add the new element to the current window.

---

### Check whether the window is valid

```java
while (sum >= target)
```

The current window satisfies the requirement.

Therefore, we try to make it smaller.

---

### Update the answer

```java
minLength = Math.min(
    minLength,
    right - left + 1
);
```

We record the current valid window if it is smaller than the previous answer.

---

### Shrink

```java
sum -= nums[left];
left++;
```

Remove the leftmost element and move `left`.

We continue shrinking while the window remains valid.

---

# 🔄 209 vs 904

This is an important connection with the previous problem.

## 904. Fruit Into Baskets

Goal:

```text
Longest valid window
```

So:

```text
Expand
   ↓
Window becomes INVALID
   ↓
Shrink
   ↓
Window becomes VALID
   ↓
Update maximum
```

---

## 209. Minimum Size Subarray Sum

Goal:

```text
Shortest valid window
```

So:

```text
Expand
   ↓
Window becomes VALID
   ↓
Update minimum
   ↓
Shrink
   ↓
Window becomes INVALID
```

### Remember

```text
904 → maximum → shrink when invalid

209 → minimum → shrink while valid
```

This distinction is extremely useful for placement problems.

---

# 💡 Why Does Sliding Window Work Here?

The problem guarantees:

```text
nums[i] > 0
```

This is extremely important.

When we move `right`:

```text
sum increases
```

because we add a positive number.

When we move `left`:

```text
sum decreases
```

because we remove a positive number.

Therefore the window behaves predictably:

```text
Expand → sum increases
Shrink → sum decreases
```

This allows us to efficiently find the smallest valid window without checking every subarray.

---

# 💻 My Solution

I used a slightly different implementation of the same Sliding Window idea.

```java
class Solution {
    public int minSubArrayLen(int target, int[] nums) {
        int minLength = Integer.MAX_VALUE;
        int s = 0, e = 0, sum = 0, c = 0;

        while(e < nums.length){
            if(sum + nums[e] < target){
                sum += nums[e];
                e++;
            }
            else{
                minLength = Math.min(minLength,e-s+1);
                sum -= nums[s];
                s++;
                c++;
            }
        }

        if(c == 0)
            return 0;

        return minLength;
    }
}
```

This solution was **successfully accepted by LeetCode**.

---

# 🧠 How My Solution Works

My implementation uses:

```text
s → start of window
e → end/current candidate element
sum → sum of the current window
minLength → smallest valid window
c → whether a valid window was found
```

The important difference from the standard implementation is:

> **I don't immediately add `nums[e]` when it would make the window valid. Instead, I calculate the window length including `nums[e]`, then keep `e` fixed while moving `s`.**

---

## Case 1 — Window is not enough

```java
if(sum + nums[e] < target)
```

If adding `nums[e]` still doesn't reach the target:

```java
sum += nums[e];
e++;
```

So we expand the window.

```text
[ current window ][nums[e]]
                       ↑
                       e moves forward
```

---

## Case 2 — Adding `nums[e]` makes the window valid

```java
else
```

Now:

```text
sum + nums[e] >= target
```

So the window including `nums[e]` is valid.

Therefore:

```java
minLength = Math.min(minLength, e - s + 1);
```

We calculate its length.

---

# 🔥 The Clever Part of My Solution

After finding a valid window, I do:

```java
sum -= nums[s];
s++;
```

But notice:

```text
e does NOT increase
```

So the next iteration checks the **same `e` again**.

This effectively allows the window to keep shrinking.

For example:

```text
target = 7

[2,3,1,2,4]
```

Suppose `e` is pointing to `4`.

The algorithm can do:

```text
[2,3,1,2,4]
 ↓ remove 2

[3,1,2,4]
 ↓ remove 3

[1,2,4]
 ↓ remove 1

[2,4]
```

It keeps `e` fixed while `s` moves forward.

So your implementation achieves the same fundamental behavior as:

```java
while(sum >= target)
```

without explicitly using a nested `while`.

---

# 📊 Visual Comparison

### Standard implementation

```text
right expands
      ↓
[2,3,1,2,4]
      ↓
sum >= target
      ↓
while(sum >= target)
      ↓
left shrinks repeatedly
```

### My implementation

```text
right/e expands
      ↓
[2,3,1,2,4]
      ↓
sum + nums[e] >= target
      ↓
record answer
      ↓
left/s shrinks
      ↓
e stays fixed
      ↓
check same e again
      ↓
left/s shrinks again
```

Both are implementing the same Sliding Window principle.

---

# 🧠 Why My Solution Is Still O(n)

At first glance, someone might think:

> "There is a `while` loop in the standard solution, so it must be slower."

That is not correct.

Likewise, your solution avoiding a nested loop does **not** make it asymptotically better.

The important thing is pointer movement.

In your solution:

```text
e → only moves forward
s → only moves forward
```

Neither pointer ever moves backward.

Therefore:

```text
e moves at most n times
s moves at most n times
```

Total:

```text
O(n) + O(n)
= O(n)
```

So your solution is:

```text
Time:  O(n)
Space: O(1)
```

which is asymptotically optimal for this problem.

---

# ⚔️ Similar Problems / Pattern Progression

### 219. Contains Duplicate II

```text
HashSet + Sliding Window
```

Introduces maintaining a limited window.

### 643. Maximum Average Subarray I

```text
Fixed-Size Sliding Window
```

Introduces the basic window movement.

### 904. Fruit Into Baskets

```text
Variable Sliding Window
+
HashMap
+
At most 2 distinct values
```

### 209. Minimum Size Subarray Sum

```text
Variable Sliding Window
+
Sum condition
+
Minimum window
```

### 1004. Max Consecutive Ones III

```text
Variable Sliding Window
+
Constraint
```

### 713. Subarray Product Less Than K

```text
Variable Sliding Window
+
Product condition
```

---

# 🚨 Common Mistakes

### 1. Using Sliding Window when numbers can be negative

This approach depends on:

```text
nums[i] > 0
```

With negative numbers:

```text
Expand → sum doesn't necessarily increase
Shrink → sum doesn't necessarily decrease
```

So the simple Sliding Window logic can fail.

---

### 2. Forgetting to shrink repeatedly

When the window becomes valid, don't stop after removing just one element.

The goal is to find the **smallest** valid window.

Keep shrinking while it remains valid.

---

### 3. Using the wrong window length

If both boundaries are inclusive:

```text
right - left + 1
```

In my implementation, `e` represents the candidate end element, so:

```text
e - s + 1
```

is correct.

---

### 4. Thinking nested loops automatically mean O(n²)

This is a common interview mistake.

For Sliding Window:

```text
left → moves forward at most n times
right → moves forward at most n times
```

Therefore the total work can still be:

```text
O(n)
```

---

# ⏱️ Complexity

### Time

```text
O(n)
```

Both pointers only move forward through the array.

### Space

```text
O(1)
```

Only a few integer variables are used.

No HashMap, HashSet, or additional array is required.

---

# 🎯 Placement-Level Takeaway

The main lesson from 209 is:

> **Learn to recognize Variable-Size Sliding Window from the relationship between the window condition and the required answer.**

When you see:

```text
contiguous subarray
+
minimum/maximum length
+
condition that can be maintained while moving two pointers
```

consider Sliding Window.

For 209:

```text
Goal:
Minimum length

Window condition:
sum >= target

Expand:
right++

Shrink:
left++

State:
sum

Answer:
minimum window length
```

### Most important recognition rule

```text
LONGEST valid window
→ usually expand until invalid
→ shrink until valid
→ maximize

SHORTEST valid window
→ expand until valid
→ shrink while valid
→ minimize
```

---

# ⚡ Final 30-Second Cheat Sheet

```text
209. Minimum Size Subarray Sum

Pattern:
Variable-Size Sliding Window

Goal:
Shortest contiguous subarray
with sum >= target

State:
sum

Two pointers:
left, right

If sum is too small:
→ expand right

If sum >= target:
→ record answer
→ shrink left

Why it works:
nums contains positive integers

Time:
O(n)

Space:
O(1)

Key recognition:
"Minimum length"
+
"Contiguous subarray"
+
"Sum >= target"
→ Variable Sliding Window
```

---

> **Nijoy P Jose**
>
> This solution is part of my **Data Structures & Algorithms** placement preparation repository, where I document problem-solving patterns, interview techniques, and Java implementations to strengthen my coding skills.
