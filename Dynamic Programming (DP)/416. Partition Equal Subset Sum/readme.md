# 416. Partition Equal Subset Sum

**Pattern:** Dynamic Programming — 0/1 Knapsack / Subset Sum
**Difficulty:** Medium
**Platform:** LeetCode
**Problem:** [416. Partition Equal Subset Sum](https://leetcode.com/problems/partition-equal-subset-sum/)

---

## 📌 Problem Statement

Given an integer array `nums`, determine whether the array can be divided into **two subsets** such that the sum of the elements in both subsets is equal.

Return:

```text
True
```

if such a partition is possible, otherwise:

```text
False
```

---

## Example 1

```text
Input:
nums = [1,5,11,5]

Output:
True
```

The array can be divided as:

```text
[1,5,5] → 11
[11]    → 11
```

Both subsets have the same sum.

---

## Example 2

```text
Input:
nums = [1,2,3,5]

Output:
False
```

Total sum:

```text
1 + 2 + 3 + 5 = 11
```

Since the total is odd, it cannot be divided into two equal integer sums.

---

# 💡 Key Observation

The original problem says:

> Can I divide the array into two subsets with equal sums?

Instead of directly trying to find two subsets, calculate the total:

```python
total = sum(nums)
```

If the total is odd:

```text
total % 2 == 1
```

then it is impossible.

If the total is even:

```text
target = total / 2
```

Now the problem becomes:

> **Can I select some elements whose sum is exactly `target`?**

This is the **Subset Sum** problem.

### Transformation

```text
Equal Partition
       ↓
Calculate total sum
       ↓
Total is odd?
   ↓          ↓
 Yes          No
  ↓            ↓
False       target = total/2
               ↓
       Can I make target?
               ↓
          Subset Sum
```

This transformation is the most important part of this problem.

---

# 🧠 DP Idea

For every number, there are two choices:

```text
1. Don't take the number
2. Take the number
```

For example:

```text
nums = [1,5,11,5]
```

For `1`:

```text
Don't take → sum stays the same
Take       → sum + 1
```

For `5`:

```text
Don't take → sum stays the same
Take       → sum + 5
```

And so on.

This is the **0/1 Knapsack** pattern because each number can be used **at most once**.

---

# 🧠 State

The standard DP interpretation is:

```text
dp[s] = whether it is possible to make sum s
```

Initially:

```text
dp[0] = True
```

because we can always make sum `0` by selecting nothing.

For example:

```text
sum:  0   1   2   3   4   5
dp:   T   F   F   F   F   F
```

When processing a number `num`, we can create new sums:

```text
new_sum = old_sum + num
```

---

# 🔄 Recurrence

Suppose:

```text
num = 5
```

and we want to know whether sum `8` is possible.

There are two possibilities:

### Don't take `5`

If `8` was already possible:

```text
dp[8] = True
```

### Take `5`

Then we need to have previously been able to make:

```text
8 - 5 = 3
```

So:

```text
dp[3] = True
```

means we can make:

```text
3 + 5 = 8
```

Therefore:

```text
dp[8] = dp[8] OR dp[3]
```

Generally:

```text
dp[s] = dp[s] OR dp[s - num]
```

---

# 💻 Your Solution — Set-Based DP

Your submitted solution uses a set to store all achievable sums:

```python
class Solution:
    def canPartition(self, nums: List[int]) -> bool:
        s = sum(nums)
        n = len(nums)

        if s % 2 == 1 or n == 0:
            return False

        target = s // 2

        dp = set()
        dp.add(0)

        for i in range(n - 1, -1, -1):
            if target in dp:
                return True

            x = set()

            for j in dp:
                x.add(j + nums[i])

            dp = dp | x

        if target in dp:
            return True

        return False
```

---

# 🔍 How Your Set DP Works

You start with:

```python
dp = {0}
```

meaning:

```text
The only sum currently possible is 0.
```

Suppose:

```text
nums = [1,5,11,5]
```

### Process `1`

Existing sums:

```text
{0}
```

Take `1`:

```text
{1}
```

Combine:

```text
{0,1}
```

---

### Process `5`

Existing:

```text
{0,1}
```

Take `5`:

```text
{5,6}
```

Combine:

```text
{0,1,5,6}
```

---

### Process `11`

Existing:

```text
{0,1,5,6}
```

Take `11`:

```text
{11,12,16,17}
```

Now:

```text
11
```

is achievable.

Since:

```text
target = 11
```

we can return:

```text
True
```

---

# 🌳 Take / Don't Take Concept

Your code is effectively doing:

```text
                Current sums
                     │
             ┌───────┴───────┐
             │               │
          Don't take       Take num
             │               │
             ↓               ↓
        same sums       sum + num
```

For every number:

```text
dp = old sums
     UNION
     (old sums + num)
```

Your line:

```python
dp = dp | x
```

performs exactly this union.

---

# 🔄 Standard Boolean DP

The same idea can be implemented using a Boolean array.

```python
class Solution:
    def canPartition(self, nums: List[int]) -> bool:
        total = sum(nums)

        if total % 2:
            return False

        target = total // 2

        dp = [False] * (target + 1)
        dp[0] = True

        for num in nums:
            for s in range(target, num - 1, -1):
                dp[s] = dp[s] or dp[s - num]

        return dp[target]
```

Here:

```text
dp[s] = True
```

means:

> We can make sum `s` using the numbers processed so far.

---

# ⚠️ Why Iterate Backwards?

This is extremely important for **0/1 Knapsack**.

We process:

```python
for s in range(target, num - 1, -1):
```

from:

```text
target → num
```

rather than:

```text
num → target
```

### Why?

Each number can be used only **once**.

Suppose:

```text
num = 5
```

If we iterate forward, we might do:

```text
5 → 10 → 15 → ...
```

and accidentally use the same `5` multiple times.

Backward iteration prevents the current number from being reused during the same iteration.

Therefore, remember:

```text
0/1 Knapsack
→ each item used once
→ iterate capacity/sum BACKWARDS
```

---

# 📊 Example DP

For:

```text
nums = [1,5,11,5]
target = 11
```

Initially:

```text
sum:  0 1 2 3 4 5 6 7 8 9 10 11
      T F F F F F F F F F F  F
```

After processing `1`:

```text
T T F F F F F F F F F F
```

After processing `5`:

```text
T T F F F T T F F F F F
```

After processing `11`:

```text
T T F F F T T F F F F T
                              ↑
                           target
```

Therefore:

```text
dp[11] = True
```

and the answer is:

```text
True
```

---

# 🧱 Base Cases

### Odd total

If:

```python
total % 2 == 1
```

return:

```python
False
```

because two equal integer sums cannot produce an odd total.

### Sum `0`

```python
dp[0] = True
```

because selecting nothing gives sum `0`.

---

# 📊 Complexity

### Your Set-Based Solution

The set can contain many different sums.

Its complexity depends on the number of distinct achievable sums.

In the worst case, it can be roughly:

```text
Time:  O(n × target)
Space: O(target)
```

where:

```text
target = sum(nums) / 2
```

### Boolean DP

```text
Time:  O(n × target)
Space: O(target)
```

The Boolean DP is usually preferred for a placement round because the state and complexity are more explicit.

---

# 🧠 Important Transformation

This is probably the most important thing to remember from this problem.

When you see:

> Divide an array into two equal-sum subsets.

Think:

```text
             Equal Partition
                    ↓
              Total Sum
                    ↓
             Is it odd?
              /       \
            Yes        No
             ↓          ↓
          False      Total / 2
                         ↓
                  Target Sum
                         ↓
                Subset Sum
                         ↓
                  0/1 Knapsack
```

You don't actually need to construct both subsets.

You only need to find **one subset whose sum is `total / 2`**.

---

# 🎯 DP Pattern Learned

## 0/1 Knapsack / Subset Sum

The general pattern is:

```text
For every item:
    
    TAKE it
       OR
    DON'T TAKE it
```

For this problem:

```text
dp[s] = dp[s] OR dp[s-num]
```

where:

```text
dp[s] = Can I make sum s?
```

Because each number can only be used once:

```text
iterate s backwards
```

---

# 🔗 Connection to Previous Problems

You've already seen **Take / Skip** in House Robber:

```text
House Robber:

Take current house
OR
Skip current house

max(...)
```

Now the same decision appears in a different form:

```text
Partition:

Take current number
OR
Skip current number

Can I reach target?
```

So:

```text
House Robber
      ↓
Take / Skip
      ↓
Different objective
      ↓
Subset Sum
      ↓
0/1 Knapsack
```

The decision structure is similar, but the DP state and objective are different.

---

# 📚 DP Progression

Your current progression:

```text
70   Climbing Stairs
 ↓
746  Min Cost Climbing Stairs
 ↓
198  House Robber
 ↓
213  House Robber II
 ↓
740  Delete and Earn
 ↓
91   Decode Ways
 ↓
62   Unique Paths
 ↓
63   Unique Paths II
 ↓
64   Minimum Path Sum
 ↓
120  Triangle
 ↓
931  Minimum Falling Path Sum
 ↓
416  Partition Equal Subset Sum ✓
```

You have now moved from:

```text
1D DP
 ↓
2D Grid DP
 ↓
Path DP
 ↓
0/1 Knapsack / Subset Sum
```

---

# 📌 Key Takeaways

### 1. Transform the problem

```text
Equal partition
→ subset sum
```

### 2. Identify the choices

```text
Take number
OR
Skip number
```

### 3. Define the state

```text
dp[s] = can sum s be formed?
```

### 4. Recurrence

```text
dp[s] = dp[s] OR dp[s-num]
```

### 5. Since each number is used once

```text
iterate sums backwards
```

This **0/1 Knapsack pattern** is one of the most important DP patterns to know for placement coding rounds.
