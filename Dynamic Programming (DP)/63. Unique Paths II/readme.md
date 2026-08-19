# 63. Unique Paths II

**Pattern:** Dynamic Programming — 2D Grid DP
**Difficulty:** Medium
**Platform:** LeetCode
**Problem:** [63. Unique Paths II](https://leetcode.com/problems/unique-paths-ii/)

---

## 📌 Problem Statement

You are given an `m × n` grid.

* `0` represents an empty cell.
* `1` represents an obstacle.
* The robot starts at the top-left cell `[0][0]`.
* The robot wants to reach the bottom-right cell `[m-1][n-1]`.
* It can only move **right** or **down**.
* The robot cannot move through an obstacle.

Return the number of unique paths from the start to the destination.

---

## Example 1

```text
Input:
[
  [0,0,0],
  [0,1,0],
  [0,0,0]
]

Output:
2
```

The two valid paths are:

```text
Right → Right → Down → Down
Down → Down → Right → Right
```

---

## Example 2

```text
Input:
[
  [0,1],
  [0,0]
]

Output:
1
```

The obstacle prevents the robot from moving right from the starting cell, so the only path is:

```text
Down → Right
```

---

# 💡 Key Idea

This problem is an extension of **LeetCode 62 — Unique Paths**.

In Unique Paths, every cell is available.

Here, some cells are obstacles.

The main recurrence remains the same:

```text
dp[i][j] = dp[i-1][j] + dp[i][j-1]
```

But we add one new rule:

```text
Obstacle → 0 paths
```

---

# 🧠 DP State

Define:

```text
dp[i][j]
```

as:

> The number of unique paths to reach cell `(i, j)` from the starting cell `(0, 0)`.

For every normal cell, the robot can arrive from only two directions:

```text
             Above
               ↓
             [i][j]
               ↑
              Left
```

Therefore:

```text
dp[i][j] = dp[i-1][j] + dp[i][j-1]
```

---

# 🚧 Handling Obstacles

If:

```python
obstacleGrid[i][j] == 1
```

the cell cannot be used.

Therefore:

```python
dp[i][j] = 0
```

and we skip the rest of the calculation for that cell.

```python
if obstacleGrid[i][j] == 1:
    dp[i][j] = 0
    continue
```

This also automatically handles obstacles in the first row and first column.

For example:

```text
0  0  1  0
```

The DP becomes:

```text
1  1  0  0
```

Once an obstacle blocks the only path along the first row, every cell after it has `0` ways.

---

# 🧱 Initialization

Initially, there are no known paths:

```python
dp = [[0] * c for _ in range(r)]
```

If the starting cell is an obstacle:

```python
if obstacleGrid[0][0] == 1:
    return 0
```

Otherwise:

```python
dp[0][0] = 1
```

Why `1`?

There is exactly one way to be at the starting cell:

```text
Start there.
```

---

# 🔄 Bottom-Up DP

We process the grid from top-left to bottom-right.

For every cell:

### If obstacle

```text
dp[i][j] = 0
```

### Otherwise

Add paths from above and left:

```text
dp[i][j] += dp[i-1][j]
dp[i][j] += dp[i][j-1]
```

---

# 💻 Solution

```python
class Solution:
    def uniquePathsWithObstacles(self, obstacleGrid: List[List[int]]) -> int:
        r = len(obstacleGrid)
        c = len(obstacleGrid[0])

        if obstacleGrid[0][0] == 1:
            return 0

        dp = [[0] * c for _ in range(r)]
        dp[0][0] = 1

        for i in range(r):
            for j in range(c):
                if obstacleGrid[i][j] == 1:
                    dp[i][j] = 0
                    continue

                if i > 0:
                    dp[i][j] += dp[i-1][j]

                if j > 0:
                    dp[i][j] += dp[i][j-1]

        return dp[r-1][c-1]
```

---

# 🔍 Dry Run

Consider:

```text
[
  [0,0,0],
  [0,1,0],
  [0,0,0]
]
```

Start:

```text
1  0  0
0  0  0
0  0  0
```

After processing the first row and column:

```text
1  1  1
1  0  0
1  0  0
```

The middle cell is an obstacle:

```text
1  1  1
1  X  0
1  0  0
```

Now calculate the remaining cells:

```text
1  1  1
1  0  1
1  1  2
```

Therefore:

```text
Answer = 2
```

---

# 🔄 Connection to Unique Paths

### LeetCode 62

```text
dp[i][j] = dp[i-1][j] + dp[i][j-1]
```

### LeetCode 63

Same recurrence:

```text
dp[i][j] = dp[i-1][j] + dp[i][j-1]
```

with one additional condition:

```text
if obstacle:
    dp[i][j] = 0
```

So the progression is:

```text
Unique Paths
     ↓
2D Grid DP
     ↓
Unique Paths II
     ↓
Same DP + obstacle constraint
```

This is an important DP skill:

> **Take a known DP solution and modify it to handle a new constraint.**

---

# 🧠 How to Derive the Logic

When you see a grid DP problem, ask:

### 1. What does `dp[i][j]` represent?

Here:

```text
dp[i][j] = number of paths to (i,j)
```

### 2. Where can I come from?

Only:

```text
above
left
```

### 3. What happens if the current cell is blocked?

```text
obstacle → 0
```

### 4. What happens otherwise?

```text
paths from above + paths from left
```

Therefore:

```text
dp[i][j] =
    0                         if obstacle
    dp[i-1][j] + dp[i][j-1]   otherwise
```

---

# 📊 Complexity

Let:

```text
m = number of rows
n = number of columns
```

### Time

Every cell is processed once:

```text
O(m × n)
```

### Space

The 2D DP table contains `m × n` values:

```text
O(m × n)
```

---

# 🚀 Space Optimization

Notice that when calculating a cell, we only need:

```text
dp[i-1][j]  → above
dp[i][j-1]  → left
```

Therefore, the full 2D table isn't strictly necessary.

We can use a **1D DP array** and reduce the space to:

```text
O(n)
```

A space-optimized version is:

```python
class Solution:
    def uniquePathsWithObstacles(self, obstacleGrid: List[List[int]]) -> int:
        n = len(obstacleGrid[0])

        dp = [0] * n
        dp[0] = 1

        for row in obstacleGrid:
            for j in range(n):
                if row[j] == 1:
                    dp[j] = 0
                elif j > 0:
                    dp[j] += dp[j - 1]

        return dp[-1]
```

Here:

```text
dp[j]
```

represents the number of ways to reach the current row's column `j`.

### Complexity

```text
Time:  O(m × n)
Space: O(n)
```

---

# ⚠️ Common Mistakes

### 1. Initializing the whole DP table to `1`

Avoid:

```python
dp = [[1] * c for _ in range(r)]
```

because an obstacle can block the path and cells after the obstacle should become `0`.

Use:

```python
dp = [[0] * c for _ in range(r)]
```

and explicitly initialize:

```python
dp[0][0] = 1
```

---

### 2. Forgetting the starting obstacle

If:

```text
grid[0][0] == 1
```

there is no possible path.

Return:

```python
0
```

---

### 3. Forgetting that an obstacle blocks all paths through it

An obstacle isn't just a cell with a different cost.

It means:

```text
No path can pass through this cell.
```

Therefore:

```python
dp[i][j] = 0
```

---

# 🎯 DP Pattern Learned

This problem reinforces the **2D Grid DP** pattern:

```text
             dp[i-1][j]
                  ↓
                  +
                  ↓
dp[i][j-1] →    dp[i][j]
```

For a normal cell:

```text
dp[i][j] = top + left
```

For an obstacle:

```text
dp[i][j] = 0
```

---

# 📚 DP Progression

Your current DP progression is:

```text
70  Climbing Stairs
        ↓
746 Min Cost Climbing Stairs
        ↓
198 House Robber
        ↓
213 House Robber II
        ↓
740 Delete and Earn
        ↓
91  Decode Ways
        ↓
62  Unique Paths
        ↓
63  Unique Paths II
```

You've now learned:

* 1D DP
* Counting DP
* Min/Max DP
* Take/Skip DP
* Problem transformation
* Case splitting
* 2D Grid DP
* Handling constraints in DP
* Top-Down → Bottom-Up conversion
* Space optimization

---

# 📚 Related Problems

Next recommended problems:

1. **64. Minimum Path Sum** — 2D DP + minimum
2. **120. Triangle** — 2D/1D DP
3. **931. Minimum Falling Path Sum** — Grid DP
4. **416. Partition Equal Subset Sum** — 0/1 Knapsack
5. **322. Coin Change** — Unbounded Knapsack

---

# 📌 Key Takeaway

The most important thing to remember from this problem is:

```text
Unique Paths
      ↓
Every cell can be used
      ↓
dp[i][j] = top + left

Unique Paths II
      ↓
Some cells are blocked
      ↓
Obstacle → 0
Normal cell → top + left
```

**The DP recurrence didn't fundamentally change. We simply added a new rule for the new constraint.**

---

# 👨‍💻 Author

**Nijoy P Jose**

Part of my **Data Structures & Algorithms placement preparation repository**.
