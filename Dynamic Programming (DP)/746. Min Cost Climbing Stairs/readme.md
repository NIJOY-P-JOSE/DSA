# 🚶 746. Min Cost Climbing Stairs

**Difficulty:** Easy
**Topic:** Dynamic Programming (DP), Tabulation

---

## 📖 Problem Statement

You are given an array `cost`, where `cost[i]` represents the cost of stepping on the `iᵗʰ` stair.

You can climb either:

* **1 step**, or
* **2 steps** at a time.

You can start from **step 0** or **step 1**.

Your goal is to find the **minimum total cost** required to reach the top of the staircase.

---

## 💡 Key Observation

To reach the current stair `i`, there are only **two possible ways**:

* Come from stair `i - 1`
* Come from stair `i - 2`

If we already know the minimum cost to reach those stairs, then:

```text
Minimum Cost to reach i
=
cost[i] + min(cost to reach i-1, cost to reach i-2)
```

This is a **Dynamic Programming** problem because:

* The same subproblems repeat.
* The optimal solution depends on previously computed results.

---

## 🧠 DP State

Let

```text
dp[i]
```

represent the **minimum cost required to reach stair `i`**.

---

## 🔁 Transition

```
dp[i] = cost[i] + min(dp[i-1], dp[i-2])
```

---

## ✅ Base Cases

```
dp[0] = cost[0]
dp[1] = cost[1]
```

---

## 🧪 Dry Run

### Input

```text
cost = [10, 15, 20]
```

DP Table

| i | cost[i] | dp[i]                |
| - | ------- | -------------------- |
| 0 | 10      | 10                   |
| 1 | 15      | 15                   |
| 2 | 20      | 20 + min(10,15) = 30 |

Answer

```
min(dp[1], dp[2])
=
min(15,30)
=
15
```

---

## 💻 Code

```python
class Solution:
    def minCostClimbingStairs(self, cost: List[int]) -> int:
        n = len(cost)

        dp = [0] * n

        dp[0] = cost[0]
        dp[1] = cost[1]

        for i in range(2, n):
            dp[i] = cost[i] + min(dp[i-1], dp[i-2])

        return min(dp[n-1], dp[n-2])
```

---

# ⏱ Complexity Analysis

### Time Complexity

```
O(n)
```

Each stair is computed exactly once.

---

### Space Complexity

```
O(n)
```

A DP array of size `n` is used.

---

# 🚀 Can We Optimize Space?

Yes.

Notice the recurrence:

```text
dp[i]
depends only on

dp[i-1]
dp[i-2]
```

We don't need the entire DP array.

Instead, we can keep only **two variables** representing the previous two states.

Optimized Complexity

| Approach           | Time | Space      |
| ------------------ | ---- | ---------- |
| Tabulation         | O(n) | O(n)       |
| Space Optimized DP | O(n) | **O(1)** ⭐ |

---

# 🎯 Pattern Recognition

Whenever you see:

* Minimum / Maximum Cost
* Climb using **1 or 2 steps**
* Previous state determines current state

Think:

> **Dynamic Programming**

---

# 📝 Interview Notes

### Why Dynamic Programming?

* The problem has **Optimal Substructure**.
* It has **Overlapping Subproblems**.
* Recursive solution repeats the same calculations.
* DP stores previous answers and avoids recomputation.

---

### DP Formula

```
dp[i] = cost[i] + min(dp[i-1], dp[i-2])
```

---

### Final Answer

```
min(dp[n-1], dp[n-2])
```

Because the top itself has **no cost**. You can reach the top from either the last stair or the second-last stair.

---

# ⭐ Key Takeaways

* ✔ Recognize the **"1-step / 2-step" DP pattern**.
* ✔ Define a clear DP state before coding.
* ✔ Write the recurrence relation first.
* ✔ Initialize the correct base cases.
* ✔ Check if the DP array can be reduced to a few variables.
* ✔ Always analyze **Time Complexity** and **Space Complexity**.

---

## 📚 Related Problems

* 70. Climbing Stairs
* 198. House Robber
* 509. Fibonacci Number
* 746. Min Cost Climbing Stairs
* 1137. N-th Tribonacci Number

> **Revision Trick:** This problem is essentially **Climbing Stairs + Fibonacci**, but instead of counting the number of ways, you're minimizing the total cost. Once you identify that pattern, deriving the DP recurrence becomes much easier.
