# 🏠 198. House Robber

> **Difficulty:** Medium  
> **Topic:** Dynamic Programming (DP), Tabulation

---

# 📖 Problem Statement

A professional robber plans to rob houses along a street.

Each house contains some amount of money.

However,

🚨 **You cannot rob two adjacent houses**, because the security system will automatically alert the police.

Your task is to find the **maximum amount of money** that can be robbed without robbing two adjacent houses.

---

## Example 1

Input

```text
nums = [1,2,3,1]
```

Possible choices

```text
1 + 3 = 4
2 + 1 = 3
```

Answer

```text
4
```

---

## Example 2

Input

```text
nums = [2,7,9,3,1]
```

Best choice

```text
2 + 9 + 1 = 12
```

Answer

```text
12
```

---

# 🤔 First Thought (Brute Force)

At every house we have **two choices**.

### Choice 1

Rob the current house.

Then we **must skip** the next house.

### Choice 2

Skip the current house.

Move to the next house.

This naturally leads to recursion.

Unfortunately,

the same subproblems are solved repeatedly.

Time Complexity becomes

```text
O(2ⁿ)
```

which is too slow.

---

# 💡 Key Observation

For every house,

there are only **two possibilities**.

### Don't Rob

Take the maximum money collected till the previous house.

```text
dp[i-1]
```

---

### Rob

Rob the current house.

Since adjacent houses cannot be robbed,

we must add the current money to

```text
dp[i-2]
```

So,

```text
dp[i-2] + nums[i]
```

---

Take whichever gives more money.

---

# 🧠 Dynamic Programming State

Let

```text
dp[i]
```

represent

> Maximum money that can be robbed from houses **0 to i**.

---

# 🔁 Transition Formula

For every house,

```text
dp[i] = max(
            dp[i-1],
            dp[i-2] + nums[i]
          )
```

Meaning

```
Skip Current House
OR
Rob Current House
```

Choose the better option.

---

# ✅ Base Cases

For the first house,

```text
dp[0] = nums[0]
```

For the second house,

rob whichever has more money.

```text
dp[1] = max(nums[0], nums[1])
```

---

# 📝 Dry Run

Input

```text
nums = [2,7,9,3,1]
```

Initially

| House | Money | DP |
|------:|------:|---:|
|0|2|2|
|1|7|7|

---

House 2

```text
max(
7,
2+9
)

=
11
```

DP

```text
[2,7,11]
```

---

House 3

```text
max(
11,
7+3
)

=
11
```

DP

```text
[2,7,11,11]
```

---

House 4

```text
max(
11,
11+1
)

=
12
```

Final DP

```text
[2,7,11,11,12]
```

Answer

```text
12
```

---

# 💻 Code

```python
class Solution:
    def rob(self, nums: List[int]) -> int:

        n = len(nums)

        if n == 1:
            return nums[0]

        dp = [0] * n

        dp[0] = nums[0]
        dp[1] = max(nums[0], nums[1])

        for i in range(2, n):
            dp[i] = max(dp[i-1], dp[i-2] + nums[i])

        return dp[n-1]
```

---

# 📊 Complexity Analysis

## Time Complexity

Every house is processed exactly once.

```text
O(n)
```

---

## Space Complexity

DP array stores one value for every house.

```text
O(n)
```

---

# 🚀 Space Optimization

Notice the formula

```text
dp[i]

depends only on

dp[i-1]
dp[i-2]
```

We never use older values.

So instead of storing the whole DP array,

we can store only

```text
prev1
prev2
```

This reduces the space complexity.

Optimized Complexity

| Time | Space |
|------|-------|
| O(n) | O(1) ⭐ |

---

# 🎯 Pattern Recognition

Whenever you see problems like

- Cannot choose adjacent elements
- Maximum profit
- Maximum sum
- Choose or Skip

Immediately think

> **Dynamic Programming**

The solution is often

```
Take Current

OR

Skip Current
```

---

# 🔄 Relation with Other DP Problems

| Problem | DP Formula |
|---------|------------|
| Fibonacci | `dp[i] = dp[i-1] + dp[i-2]` |
| Climbing Stairs | `dp[i] = dp[i-1] + dp[i-2]` |
| Min Cost Climbing Stairs | `dp[i] = cost[i] + min(dp[i-1], dp[i-2])` |
| House Robber | `dp[i] = max(dp[i-1], dp[i-2] + nums[i])` |

Notice

The DP pattern is the same.

Only the operation changes.

- Addition → Count ways
- Minimum → Min Cost
- Maximum → Max Profit

---

# 📝 Interview Notes

✅ Define the DP state first.

✅ Identify the recurrence relation.

✅ Initialize the base cases correctly.

✅ Check whether the DP array can be optimized.

✅ Explain why the recurrence works.

Interviewers usually care more about **your reasoning** than memorizing the formula.

---

# ⭐ Key Takeaways

- This is a classic **Choose or Skip** Dynamic Programming problem.
- The current decision depends only on the previous two states.
- Recursion leads to overlapping subproblems.
- DP eliminates repeated calculations.
- The solution can be optimized from **O(n)** space to **O(1)** space.

---

## 📚 Related Problems

- 70. Climbing Stairs
- 746. Min Cost Climbing Stairs
- 198. House Robber
- 213. House Robber II
- 337. House Robber III
