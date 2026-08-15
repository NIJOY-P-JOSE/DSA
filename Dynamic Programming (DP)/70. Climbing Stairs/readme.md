# 70. Climbing Stairs

**Pattern:** Dynamic Programming — 1D DP
**Difficulty:** Easy
**Platform:** LeetCode
**Problem:** [70. Climbing Stairs](https://leetcode.com/problems/climbing-stairs/)

---

## 📌 Problem Statement

You are climbing a staircase with `n` steps.

At each move, you can climb either:

* `1` step
* `2` steps

Return the number of **distinct ways** to reach the top.

### Example 1

```text
Input: n = 2
Output: 2
```

Ways:

```text
1 + 1
2
```

### Example 2

```text
Input: n = 3
Output: 3
```

Ways:

```text
1 + 1 + 1
1 + 2
2 + 1
```

---

# 💡 Intuition

Consider reaching step `n`.

The **last move** can only be:

```text
n - 1 → n     (1 step)
```

or

```text
n - 2 → n     (2 steps)
```

Therefore:

```text
ways(n) = ways(n - 1) + ways(n - 2)
```

This is the Fibonacci pattern.

---

# 🧠 DP State

Define:

```text
dp[i] = number of distinct ways to reach step i
```

Then:

```text
dp[i] = dp[i - 1] + dp[i - 2]
```

because the final move to step `i` is either:

```text
from i - 1
```

or:

```text
from i - 2
```

---

# 🧱 Base Cases

For one step:

```text
dp[1] = 1
```

There is only:

```text
1
```

For two steps:

```text
dp[2] = 2
```

There are:

```text
1 + 1
2
```

So:

```text
dp[1] = 1
dp[2] = 2
```

---

# 🔄 Top-Down DP — Memoization

The recursive solution is:

```text
ways(n) = ways(n-1) + ways(n-2)
```

Without memoization, the same states are calculated repeatedly.

For example:

```text
              dp(5)
             /     \
          dp(4)    dp(3)
          /  \     /  \
       dp(3) dp(2) dp(2) dp(1)
```

`dp(3)` and `dp(2)` are calculated multiple times.

Memoization stores the result of each state.

### Python

```python
class Solution:
    def climbStairs(self, n: int) -> int:
        if n <= 2:
            return n

        memo = {1: 1, 2: 2}

        return self.dp(n, memo)

    def dp(self, n, memo):
        if n in memo:
            return memo[n]

        memo[n] = self.dp(n - 1, memo) + self.dp(n - 2, memo)

        return memo[n]
```

### Complexity

```text
Time:  O(n)
Space: O(n)
```

The dictionary stores each computed state once.

---

# 🔽 Bottom-Up DP — Tabulation

Instead of starting from `n` and recursively going backward, calculate the smaller states first.

```text
dp[1] → dp[2] → dp[3] → dp[4] → ... → dp[n]
```

### Python

```python
class Solution:
    def climbStairs(self, n: int) -> int:
        if n <= 2:
            return n

        dp = [0] * (n + 1)

        dp[1] = 1
        dp[2] = 2

        for i in range(3, n + 1):
            dp[i] = dp[i - 1] + dp[i - 2]

        return dp[n]
```

### Complexity

```text
Time:  O(n)
Space: O(n)
```

---

# 🚀 Bottom-Up DP — Space Optimized

Notice that to calculate:

```text
dp[i] = dp[i-1] + dp[i-2]
```

we only need the previous two values.

We don't need to store the entire DP array.

```python
class Solution:
    def climbStairs(self, n: int) -> int:
        if n <= 2:
            return n

        prev2 = 1
        prev1 = 2

        for i in range(3, n + 1):
            current = prev1 + prev2

            prev2 = prev1
            prev1 = current

        return prev1
```

### Complexity

```text
Time:  O(n)
Space: O(1)
```

This is the most space-efficient version.

---

# 🔍 Dry Run

For:

```text
n = 5
```

Start:

```text
dp[1] = 1
dp[2] = 2
```

Then:

```text
dp[3] = dp[2] + dp[1]
      = 2 + 1
      = 3

dp[4] = dp[3] + dp[2]
      = 3 + 2
      = 5

dp[5] = dp[4] + dp[3]
      = 5 + 3
      = 8
```

Therefore:

```text
Output = 8
```

The 8 ways are:

```text
11111
1112
1121
1211
122
2111
212
221
```

---

# 🔄 Recursion → Memoization → Tabulation

This problem is a simple example of the complete DP progression:

```text
                 Recursion
                    ↓
        ways(n-1) + ways(n-2)
                    ↓
              Memoization
                    ↓
              Bottom-Up DP
                    ↓
           Space Optimization
```

### 1. Recursion

Repeatedly calculate smaller states.

### 2. Memoization

Store already calculated states.

### 3. Tabulation

Calculate states from smallest to largest.

### 4. Space Optimization

Keep only the previous two states.

---

# 📊 Comparison

| Approach        |  Time |    Space | Notes                 |
| --------------- | ----: | -------: | --------------------- |
| Plain Recursion | O(2ⁿ) |     O(n) | Repeated calculations |
| Top-Down DP     |  O(n) |     O(n) | Memoization           |
| Bottom-Up DP    |  O(n) |     O(n) | Tabulation            |
| Space Optimized |  O(n) | **O(1)** | Best space usage      |

---

# 🎯 DP Pattern Learned

This problem teaches the basic **1D DP** pattern.

When you see a problem where the answer for state `i` depends on previous states:

```text
dp[i-1]
dp[i-2]
...
```

consider a 1D DP array:

```text
dp[i]
```

For this problem:

```text
dp[i] = dp[i-1] + dp[i-2]
```

### Questions to ask in similar problems

1. What does `dp[i]` represent?
2. What choices can I make?
3. Which previous states do those choices lead to?
4. What are the base cases?
5. Can the DP array be space optimized?

---

# ⚠️ Common Mistakes

### Mistake 1 — Wrong base case

For this problem:

```text
dp[1] = 1
dp[2] = 2
```

### Mistake 2 — Forgetting different orders

For `n = 3`:

```text
1 + 2
2 + 1
```

are different ways.

### Mistake 3 — Confusing number of ways with minimum steps

The problem asks for the **number of distinct ways**, not the minimum number of moves.

---

# 📚 Related Problems

After Climbing Stairs, practice:

1. **746. Min Cost Climbing Stairs** — 1D DP
2. **198. House Robber** — 1D choice DP
3. **213. House Robber II** — 1D DP with circular constraint
4. **91. Decode Ways** — 1D DP
5. **70. Climbing Stairs** — Basic 1D DP

---

# 📚 Concepts Practiced

* [x] Dynamic Programming
* [x] 1D DP
* [x] Recursion
* [x] Memoization
* [x] Tabulation
* [x] Space Optimization
* [x] State Definition
* [x] Recurrence
* [x] Base Cases
* [x] Complexity Analysis

---

# 👨‍💻 Author

**Nijoy P Jose**

Part of my **Data Structures & Algorithms placement preparation repository**.
