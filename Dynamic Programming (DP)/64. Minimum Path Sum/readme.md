# 64. Minimum Path Sum

**Pattern:** Dynamic Programming — 2D Grid DP
**Difficulty:** Medium
**Platform:** LeetCode
**Problem:** [64. Minimum Path Sum](https://leetcode.com/problems/minimum-path-sum/)

---

## 📌 Problem Statement

You are given an `m × n` grid containing non-negative integers.

Starting from the **top-left** cell, reach the **bottom-right** cell while minimizing the sum of all values along the path.

You can move only:

* **Right**
* **Down**

Return the minimum possible path sum.

---

## Example 1

```text
Input:
[
    [1, 3, 1],
    [1, 5, 1],
    [4, 2, 1]
]

Output:
7
```

The minimum path is:

```text
1 → 3 → 1 → 1 → 1
```

Sum:

```text
1 + 3 + 1 + 1 + 1 = 7
```

---

## Example 2

```text
Input:
[
    [1, 2, 3],
    [4, 5, 6]
]

Output:
12
```

---

# 💡 DP Idea

This is a **2D Grid DP** problem.

Define:

```text
dp[i][j] = minimum cost to reach cell (i, j)
```

To reach `(i, j)`, the robot can only come from:

```text
       above
          ↓
       [i][j]
          ↑
        left
```

Therefore, for an ordinary cell:

```text
dp[i][j] = min(
    dp[i-1][j],
    dp[i][j-1]
) + grid[i][j]
```

The important question is:

> **What is the best way to reach the current cell?**

Take the cheaper of the two possible previous cells and add the current cell's value.

---

# 🧠 State

```text
dp[i][j]
```

represents:

> The minimum path sum required to reach `(i, j)` from `(0, 0)`.

For example:

```text
grid:

1  3  1
1  5  1
4  2  1
```

The DP table becomes:

```text
1   4   5
2   7   6
6   8   7
```

Therefore:

```text
answer = dp[2][2] = 7
```

---

# 🧱 Base Cases

### Starting cell

There is only one way to start:

```python
dp[0][0] = grid[0][0]
```

### First row

The robot can only move **right**.

Therefore:

```text
dp[0][j] = dp[0][j-1] + grid[0][j]
```

### First column

The robot can only move **down**.

Therefore:

```text
dp[i][0] = dp[i-1][0] + grid[i][0]
```

---

# 🔄 Recurrence

For every cell other than the first row and first column:

```text
dp[i][j] =
    min(dp[i-1][j], dp[i][j-1])
    + grid[i][j]
```

In words:

> Take the minimum path sum from the top or left and add the current cell's value.

---

# 💻 Bottom-Up DP Solution

This is the solution you submitted successfully:

```python
class Solution:
    def minPathSum(self, grid: List[List[int]]) -> int:
        r = len(grid)
        c = len(grid[0])

        if c == 1 and r == 1:
            return grid[0][0]

        dp = [[0] * c for _ in range(r)]
        dp[0][0] = grid[0][0]

        for i in range(r):
            for j in range(c):
                if i == 0 or j == 0:
                    if j != 0:
                        dp[i][j] = grid[i][j] + dp[i][j-1]
                        continue

                    if i != 0:
                        dp[i][j] = grid[i][j] + dp[i-1][j]
                        continue

                dp[i][j] = min(dp[i-1][j], dp[i][j-1]) + grid[i][j]

        return dp[r-1][c-1]
```

### Complexity

```text
Time:  O(m × n)
Space: O(m × n)
```

Every cell is processed exactly once.

---

# ✨ Cleaner Version

The same DP logic can be written more explicitly:

```python
class Solution:
    def minPathSum(self, grid: List[List[int]]) -> int:
        r = len(grid)
        c = len(grid[0])

        dp = [[0] * c for _ in range(r)]
        dp[0][0] = grid[0][0]

        for i in range(r):
            for j in range(c):
                if i == 0 and j == 0:
                    continue

                if i == 0:
                    dp[i][j] = dp[i][j-1] + grid[i][j]

                elif j == 0:
                    dp[i][j] = dp[i-1][j] + grid[i][j]

                else:
                    dp[i][j] = min(
                        dp[i-1][j],
                        dp[i][j-1]
                    ) + grid[i][j]

        return dp[r-1][c-1]
```

This version is easier to explain during a placement interview because the three cases are clear:

```text
Starting cell
     ↓
First row → only from left
     ↓
First column → only from above
     ↓
Other cells → minimum(top, left)
```

---

# 🔍 Dry Run

For:

```text
grid =
[
    [1, 3, 1],
    [1, 5, 1],
    [4, 2, 1]
]
```

### First cell

```text
dp[0][0] = 1
```

### First row

```text
1  4  5
```

because:

```text
1
↓
1 + 3 = 4
4 + 1 = 5
```

### First column

```text
1
2
6
```

because:

```text
1
1 + 1 = 2
2 + 4 = 6
```

### Remaining cells

At `(1,1)`:

```text
min(4, 2) + 5
= 2 + 5
= 7
```

At `(1,2)`:

```text
min(5, 7) + 1
= 6
```

At `(2,1)`:

```text
min(7, 6) + 2
= 8
```

At `(2,2)`:

```text
min(6, 8) + 1
= 7
```

Final DP table:

```text
1  4  5
2  7  6
6  8  7
```

Answer:

```text
7
```

---

# 🧠 How to Derive This DP

When you see a grid problem like this, ask:

### 1. What does the state represent?

```text
dp[i][j] = minimum cost to reach (i,j)
```

### 2. Where can I come from?

Only:

```text
↑ above
← left
```

### 3. What does the problem ask?

It asks for the **minimum**.

Therefore:

```text
min(above, left)
```

### 4. What about the current cell?

We must pay its value:

```text
+ grid[i][j]
```

So:

```text
dp[i][j] =
min(above, left) + current
```

This gives the recurrence directly.

---

# 🔗 Connection with Problems 62 and 63

This is an important progression:

### 62. Unique Paths

Count paths:

```text
dp[i][j] =
dp[i-1][j] + dp[i][j-1]
```

### 63. Unique Paths II

Count paths, but obstacles are blocked:

```text
obstacle → 0

otherwise:
dp[i][j] =
dp[i-1][j] + dp[i][j-1]
```

### 64. Minimum Path Sum

Don't count paths. Find the cheapest one:

```text
dp[i][j] =
min(dp[i-1][j], dp[i][j-1])
+ grid[i][j]
```

So the **grid structure remains the same**, but the objective changes:

```text
62 → COUNT
63 → COUNT + OBSTACLE
64 → MINIMUM COST
```

This is an important DP pattern to recognize in placement tests.

---

# 🚀 Space Optimization

The 2D table isn't strictly necessary.

To calculate the current cell, we only need:

```text
above
left
```

Therefore, the space can be reduced from:

```text
O(m × n)
```

to:

```text
O(n)
```

A 1D version is:

```python
class Solution:
    def minPathSum(self, grid: List[List[int]]) -> int:
        n = len(grid[0])

        dp = [0] * n

        for i in range(len(grid)):
            for j in range(n):
                if i == 0 and j == 0:
                    dp[j] = grid[i][j]

                elif i == 0:
                    dp[j] = dp[j-1] + grid[i][j]

                elif j == 0:
                    dp[j] = dp[j] + grid[i][j]

                else:
                    dp[j] = min(dp[j], dp[j-1]) + grid[i][j]

        return dp[-1]
```

Here:

```text
dp[j]
```

before updating represents the **cell above**, while:

```text
dp[j-1]
```

represents the **cell to the left**.

### Complexity

```text
Time:  O(m × n)
Space: O(n)
```

For placement rounds, your original `O(m × n)` solution is completely acceptable unless the constraints require better space usage.

---

# 🎯 DP Pattern Learned

## 2D Grid Minimum DP

The general structure is:

```text
             previous states
              ↙        ↖
           left        above
              \        /
               \      /
                current
```

For a **minimum** problem:

```text
dp[i][j] = min(previous states) + current cost
```

For a **maximum** problem, it would generally be:

```text
dp[i][j] = max(previous states) + current value
```

For a **counting** problem:

```text
dp[i][j] = sum(previous states)
```

This is an excellent pattern to remember for placements.

---

# 📚 DP Progression

Your current sequence:

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
64   Minimum Path Sum ✓
```

You've now covered:

* 1D DP
* Counting DP
* Min/Max DP
* Take/Skip DP
* Multiple-choice DP
* 2D Grid DP
* Obstacles in DP
* Minimum-cost grid DP
* Top-Down vs Bottom-Up
* Space optimization

---

# 📌 Key Takeaway

When you see a new grid DP problem, don't immediately think about the code.

Ask:

```text
What does dp[i][j] mean?
        ↓
Where can I come from?
        ↓
What does the problem want?
        ↓
COUNT → +
MIN   → min()
MAX   → max()
        ↓
Add/currently process the cell
```

For this problem:

```text
dp[i][j]
   ↓
minimum cost to reach (i,j)
   ↓
can come from top or left
   ↓
choose minimum
   ↓
add current grid value
```

Therefore:

```text
dp[i][j] =
min(dp[i-1][j], dp[i][j-1]) + grid[i][j]
```

---

# 👨‍💻 Author

**Nijoy P Jose**

Part of my **Data Structures & Algorithms placement preparation repository**.
