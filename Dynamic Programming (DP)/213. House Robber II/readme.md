# 213. House Robber II

**Pattern:** Dynamic Programming — 1D DP / Choice DP
**Difficulty:** Medium
**Platform:** LeetCode
**Problem:** [213. House Robber II](https://leetcode.com/problems/house-robber-ii/)

---

## 📌 Problem Statement

You are a professional robber planning to rob houses arranged in a **circle**.

Each house contains some amount of money.

Adjacent houses cannot both be robbed because doing so will alert the police.

Because the houses are arranged in a circle:

```text
First house ↔ Last house
```

are also adjacent.

Return the **maximum amount of money** that can be robbed without robbing two adjacent houses.

---

## Example 1

```text
Input:
[2, 3, 2]

Output:
3
```

We cannot rob both the first and last houses because they are adjacent in the circle.

The best choice is:

```text
3
```

---

## Example 2

```text
Input:
[1, 2, 3, 1]

Output:
4
```

Rob:

```text
1 + 3 = 4
```

---

## Example 3

```text
Input:
[1, 2, 3]

Output:
3
```

---

# 💡 Intuition

This problem is a variation of **House Robber I**.

In House Robber I, houses are arranged in a straight line:

```text
0 — 1 — 2 — 3 — 4
```

At every house, we have two choices:

```text
Skip current house
        OR
Rob current house
```

The DP transition is:

```text
dp[i] = max(
    dp[i-1],
    dp[i-2] + nums[i]
)
```

---

# 🔴 What Changes in House Robber II?

The houses are now arranged in a circle:

```text
        0
      /   \
     1     4
     |     |
     2 ——— 3
```

The important new constraint is:

```text
House 0 and House n-1 are adjacent.
```

Therefore, we cannot rob both the first and last houses.

---

# 🧠 Key Idea — Split Into Two Cases

There are only two possibilities regarding the first house.

## Case 1 — Exclude the Last House

Consider:

```text
nums[0 ... n-2]
```

This means we allow the first house but remove the last house.

```text
0  1  2  3  4
✓           ✗
```

Run the normal House Robber DP on this range.

---

## Case 2 — Exclude the First House

Consider:

```text
nums[1 ... n-1]
```

This means we remove the first house and allow the last house.

```text
0  1  2  3  4
✗           ✓
```

Again, run the normal House Robber DP.

---

## Final Answer

Take the better of the two cases:

```text
answer = max(
    rob(0 ... n-2),
    rob(1 ... n-1)
)
```

### Why does this work?

Every valid solution belongs to one of these two cases:

```text
                  All possibilities
                         |
              ┌──────────┴──────────┐
              ↓                     ↓
       First house used      First house not used
              |                     |
       Last house excluded    Last house can be used
              |                     |
          0 ... n-2              1 ... n-1
```

Therefore, checking both cases covers every possible valid solution.

---

# 🔄 House Robber DP

For a linear range, we use the same DP logic from **House Robber I**.

For each house:

### Skip current house

```text
dp[i-1]
```

### Rob current house

The previous house cannot be robbed:

```text
dp[i-2] + nums[i]
```

Therefore:

```text
dp[i] = max(
    dp[i-1],
    dp[i-2] + nums[i]
)
```

---

# 🚀 Space Optimization

We don't need the entire DP array.

To calculate the current state, we only need:

```text
dp[i-2]
dp[i-1]
```

So we keep two variables:

```text
x = dp[i-2]
y = dp[i-1]
```

Then:

```python
t = max(y, x + nums[i])
```

and move the states forward:

```python
x = y
y = t
```

This reduces the extra space from:

```text
O(n)
```

to:

```text
O(1)
```

---

# 💻 Solution

```python
class Solution:
    def rob(self, nums: List[int]) -> int:
        l = len(nums)

        if l <= 1:
            return nums[0]

        def dp(s, e):
            x = 0
            y = 0

            for i in range(s, e):
                t = max(y, x + nums[i])

                x = y
                y = t

            return max(x, y)

        return max(
            dp(0, l - 1),
            dp(1, l)
        )
```

---

# 🔍 Understanding the Helper Function

The helper:

```python
dp(s, e)
```

solves the normal House Robber problem for the range:

```text
[s, e)
```

where `e` is exclusive.

Therefore:

```python
dp(0, l - 1)
```

processes:

```text
0 ... l-2
```

and excludes the last house.

While:

```python
dp(1, l)
```

processes:

```text
1 ... l-1
```

and excludes the first house.

---

# 🧪 Dry Run

Consider:

```text
nums = [1, 2, 3, 1]
```

### Case 1

Exclude the last house:

```text
[1, 2, 3]
```

Normal House Robber:

```text
1
↓
2
↓
max(2, 1 + 3)
↓
4
```

So:

```text
case1 = 4
```

### Case 2

Exclude the first house:

```text
[2, 3, 1]
```

Normal House Robber:

```text
2
↓
3
↓
max(3, 2 + 1)
↓
3
```

So:

```text
case2 = 3
```

Finally:

```text
max(4, 3) = 4
```

Answer:

```text
4
```

---

# 🧠 Why `x = 0` and `y = 0`?

The helper processes the range from the beginning:

```python
for i in range(s, e):
```

Before processing any house, there are effectively no previous houses.

So:

```python
x = 0
y = 0
```

represent the initial states.

For the first house:

```python
t = max(0, 0 + nums[i])
```

which correctly gives:

```text
nums[i]
```

Then the two states move forward.

This allows the same helper function to work for both:

```text
0 ... n-2
```

and:

```text
1 ... n-1
```

without separately initializing the first two houses.

---

# ⚠️ Common Mistake

A tempting approach is to divide houses into even and odd indices:

```text
Even indexed houses
vs.
Odd indexed houses
```

This is **not correct**.

The optimal solution does not have to follow an alternating pattern.

For example, the robber can choose:

```text
House 0
skip 1
skip 2
House 3
```

depending on the values.

The correct DP choice at every house is:

```text
             Current House
              /          \
           Skip          Rob
             |             |
          dp[i-1]    dp[i-2] + nums[i]
```

---

# 📊 Complexity

Let `n` be the number of houses.

We solve two linear House Robber problems:

```text
0 ... n-2
1 ... n-1
```

Each takes `O(n)` time.

Therefore:

### Time

```text
O(n)
```

### Extra Space

Only two variables are used by each DP calculation:

```text
O(1)
```

---

# 🔄 Pattern Learned

This problem combines two important placement patterns:

```text
House Robber I
      ↓
Take / Skip DP
      ↓
House Robber II
      ↓
Circular constraint
      ↓
Split into two linear cases
      ↓
Reuse the same DP
```

The important lesson is:

> **When a new constraint makes a known DP problem difficult, look for a way to split the problem into simpler cases and reuse the DP you already know.**

---

# 🎯 Interview Recognition

When you see:

> "Choose elements, but cannot choose adjacent elements."

Think:

```text
Take / Skip DP
```

When the problem additionally says:

> "The first and last elements are also adjacent."

Think:

```text
Circular
    ↓
Split into two cases
    ↓
Exclude first OR exclude last
    ↓
Apply normal House Robber
```

---

# 📚 Related Problems

1. **198. House Robber** — Basic Take/Skip DP
2. **213. House Robber II** — Circular Take/Skip DP
3. **740. Delete and Earn** — Similar Take/Skip pattern
4. **337. House Robber III** — Tree DP

---

# 📚 Concepts Practiced

* [x] Dynamic Programming
* [x] 1D DP
* [x] Choice DP
* [x] Take vs Skip
* [x] Space Optimization
* [x] Circular Array
* [x] Case Splitting
* [x] Reusing an Existing DP Pattern
* [x] State Transition
* [x] Complexity Analysis

---

# 👨‍💻 Author

**Nijoy P Jose**

Part of my **Data Structures & Algorithms placement preparation repository**.
