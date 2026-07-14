# 🤖 62. Unique Paths

> **Difficulty:** Medium  
> **Topic:** Dynamic Programming (DP), Grid DP

---

# 📖 Problem Statement

A robot starts at the **top-left corner** of an `m × n` grid and wants to reach the **bottom-right corner**.

The robot can move only:

- ➡️ Right
- ⬇️ Down

Find the **total number of unique paths** from the start to the destination.

---

<img width="400" height="183" alt="robot_maze" src="https://github.com/user-attachments/assets/7b46fb1a-5bc0-483e-9716-5e1df24a5b8b" />

## Example

Input

```text
m = 3
n = 7
```

Output

```text
28
```

---

# 🤔 Brute Force Idea

At every cell, the robot has **two choices**:

- Move Right
- Move Down

A recursive solution explores both choices until the destination is reached.

However, many subproblems are solved repeatedly, resulting in exponential time complexity.

---

# 💡 Key Observation

To reach any cell `(i, j)`:

The robot can only come from:

- The cell above `(i-1, j)`
- The cell to the left `(i, j-1)`

Therefore,

```
Ways to reach current cell
=
Ways from Top
+
Ways from Left
```

---

# 🧠 DP State

Let

```
dp[i][j]
```

represent

> Number of unique paths to reach cell `(i, j)`.

---

# 🔁 Transition Formula

```
dp[i][j] = dp[i-1][j] + dp[i][j-1]
```

---

# ✅ Base Cases

The robot has only **one way** to reach:

- Any cell in the **first row**
- Any cell in the **first column**

Therefore,

```
dp[0][j] = 1

dp[i][0] = 1
```

---

# 📝 Dry Run

Grid

```text
3 × 3
```

Initially

```text
1 1 1
1 0 0
1 0 0
```

Fill remaining cells

```
1 1 1
1 2 3
1 3 6
```

Answer

```
6
```

---

# 💻 Code

```python
class Solution:
    def uniquePaths(self, m: int, n: int) -> int:

        dp = [[0] * n for _ in range(m)]

        for i in range(m):
            for j in range(n):

                if i == 0 or j == 0:
                    dp[i][j] = 1
                else:
                    dp[i][j] = dp[i-1][j] + dp[i][j-1]

        return dp[m-1][n-1]
```

---

# 📊 Complexity Analysis

### Time Complexity

Every cell is visited exactly once.

```
O(m × n)
```

---

### Space Complexity

A DP table of size `m × n` is used.

```
O(m × n)
```

---

# 🚀 Space Optimization

Notice

```
dp[i][j]

depends only on

dp[i-1][j]
dp[i][j-1]
```

Instead of storing the complete grid,

we can use a **single row** (1D DP).

Optimized Complexity

| Time | Space |
|------|-------|
| O(m × n) | O(n) ⭐ |

---

# 🎯 Pattern Recognition

Whenever you see problems involving:

- Grid traversal
- Count number of ways
- Right / Down movement
- Reach destination

Think about **Grid Dynamic Programming**.

---

# 🔄 Relation with Other DP Problems

| Problem | DP State |
|---------|----------|
| Fibonacci | Previous 2 values |
| Climbing Stairs | Previous 2 steps |
| House Robber | Previous 2 houses |
| Unique Paths | Top + Left cell |

Notice

The answer for each state is built using previously solved states.

---

# 📝 Interview Notes

✅ Define the DP state first.

✅ Identify where the current state can come from.

✅ Initialize the base cases carefully.

✅ Fill the DP table row by row.

✅ Check whether the DP table can be optimized to 1D.

---

# ⭐ Key Takeaways

- Classic **Grid Dynamic Programming** problem.
- Each cell depends only on **Top** and **Left** cells.
- Bottom-Up DP avoids repeated recursive calculations.
- Can be optimized from **O(m × n)** space to **O(n)** space.

---

## 📚 Related Problems

- 63. Unique Paths II
- 64. Minimum Path Sum
- 120. Triangle
- 931. Minimum Falling Path Sum
- 62. Unique Paths
