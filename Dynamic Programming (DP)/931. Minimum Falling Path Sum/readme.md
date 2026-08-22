# 931. Minimum Falling Path Sum

**Pattern:** Dynamic Programming — 2D Grid DP / In-Place DP
**Difficulty:** Medium
**Platform:** LeetCode
**Problem:** [931. Minimum Falling Path Sum](https://leetcode.com/problems/minimum-falling-path-sum/)

---

## 📌 Problem Statement

Given an `n × n` matrix, find the **minimum sum of any falling path** through the matrix.

A falling path:

* Starts at **any cell in the first row**.
* At each step, moves to the next row.
* From `(row, col)`, the next cell can be:

  * `(row + 1, col - 1)` — diagonally left
  * `(row + 1, col)` — directly below
  * `(row + 1, col + 1)` — diagonally right

Return the minimum possible falling path sum.

---

## Example 1

```text
Input:
[
    [2,1,3],
    [6,5,4],
    [7,8,9]
]

Output:
13
```

One minimum falling path is:

```text
1 → 5 → 7
```

Sum:

```text
1 + 5 + 7 = 13
```

Another minimum path can also produce the same sum.

---

## Example 2

```text
Input:
[
    [-19,57],
    [-40,-5]
]

Output:
-59
```

The minimum path is:

```text
-19 → -40
```

Sum:

```text
-19 + (-40) = -59
```

---

# 💡 DP Idea

This is another **2D Grid DP** problem.

It is closely related to the problems we've already solved:

```text
62  Unique Paths
63  Unique Paths II
64  Minimum Path Sum
120 Triangle
931 Minimum Falling Path Sum
```

The important difference is that a cell can now come from **three possible cells** in the previous row.

For a cell `(i, j)`:

```text
       ↙   ↓   ↘
      ┌─────────┐
      │ previous│
      └─────────┘
           ↓
        [i][j]
```

The possible previous cells are:

```text
(i-1, j-1)
(i-1, j)
(i-1, j+1)
```

---

# 🧠 DP State

Define:

```text
dp[i][j]
```

as:

> The minimum falling path sum ending at cell `(i, j)`.

Therefore, to calculate `(i, j)`, we look at the valid cells in the previous row.

For a middle cell:

```text
dp[i][j] =
    matrix[i][j]
    + min(
        dp[i-1][j-1],
        dp[i-1][j],
        dp[i-1][j+1]
      )
```

---

# 🔄 Direction of DP

Notice that:

```text
dp[i][j]
```

depends on:

```text
dp[i-1][j-1]
dp[i-1][j]
dp[i-1][j+1]
```

All of these are in the **previous row**.

Therefore, we process:

```text
Top → Bottom
```

```text
Row 0
  ↓
Row 1
  ↓
Row 2
  ↓
...
Last Row
```

This is different from **120. Triangle**, where our state depended on the row below, so we processed **bottom → top**.

---

# 🧱 Base Case

The first row can be the starting point of any falling path.

Therefore, the first row already represents the initial path sums:

```text
dp[0][j] = matrix[0][j]
```

No calculation is needed for row `0`.

We start processing from:

```python
for i in range(1, r):
```

---

# 🚧 Boundary Cases

A middle cell has three possible previous cells:

```text
       left   middle   right
        ↖       ↑       ↗
             current
```

But cells at the edges don't have all three.

### Left edge

For:

```text
j == 0
```

there is no `(i-1, j-1)`.

So only:

```text
(i-1, j)
(i-1, j+1)
```

are valid.

### Right edge

For:

```text
j == r-1
```

there is no `(i-1, j+1)`.

So only:

```text
(i-1, j)
(i-1, j-1)
```

are valid.

### Middle

All three are valid:

```text
(i-1, j-1)
(i-1, j)
(i-1, j+1)
```

---

# 💻 Your Solution

Your submitted solution uses **in-place Bottom-Up DP**:

```python
class Solution:
    def minFallingPathSum(self, matrix: List[List[int]]) -> int:
        r = len(matrix)

        if r == 1:
            return matrix[0][0]

        for i in range(1, r):
            for j in range(r):

                if j == 0:
                    matrix[i][j] += min(
                        matrix[i-1][j],
                        matrix[i-1][j+1]
                    )

                elif j == r-1:
                    matrix[i][j] += min(
                        matrix[i-1][j],
                        matrix[i-1][j-1]
                    )

                else:
                    matrix[i][j] += min(
                        matrix[i-1][j-1],
                        matrix[i-1][j],
                        matrix[i-1][j+1]
                    )

        return min(matrix[r-1])
```

---

# 🔍 Dry Run

Consider:

```text
[
    [2,1,3],
    [6,5,4],
    [7,8,9]
]
```

### Initial matrix

```text
2  1  3
6  5  4
7  8  9
```

### Process row 1

For `6`:

```text
6 + min(2,1)
= 7
```

For `5`:

```text
5 + min(2,1,3)
= 6
```

For `4`:

```text
4 + min(1,3)
= 5
```

Matrix becomes:

```text
2  1  3
7  6  5
7  8  9
```

### Process row 2

For `7`:

```text
7 + min(7,6)
= 13
```

For `8`:

```text
8 + min(7,6,5)
= 13
```

For `9`:

```text
9 + min(6,5)
= 14
```

Matrix becomes:

```text
2  1  3
7  6  5
13 13 14
```

Finally:

```python
min(matrix[r-1])
```

gives:

```text
13
```

---

# 🎯 Why `min(matrix[r-1])`?

Unlike **64. Minimum Path Sum**, where the destination is fixed at the bottom-right:

```text
(0,0) → (n-1,n-1)
```

here the falling path can end at **any cell in the last row**.

Therefore:

```text
answer = minimum value in the last row
```

```python
return min(matrix[r-1])
```

---

# 💾 In-Place DP

You used:

```python
matrix[i][j] += ...
```

instead of creating:

```python
dp = [[0] * r for _ in range(r)]
```

This means the original matrix itself becomes the DP table.

Before processing:

```text
matrix[i][j]
```

means:

> Original value.

After processing:

```text
matrix[i][j]
```

means:

> Minimum falling path sum ending at `(i,j)`.

This saves extra memory.

---

# 📊 Complexity

There are `n × n` cells.

### Time

Every cell is processed once:

```text
O(n²)
```

### Extra Space

No additional DP matrix is created.

```text
O(1)
```

extra space.

So your solution has:

```text
Time:  O(n²)
Space: O(1)
```

This is an excellent solution for a placement coding round.

---

# 🔄 Connection With Previous DP Problems

## 64. Minimum Path Sum

A cell can be reached from:

```text
↑ above
← left
```

Therefore:

```text
dp[i][j] =
grid[i][j] + min(top, left)
```

---

## 120. Triangle

A cell can move toward:

```text
↓ below-left
↓ below-right
```

Therefore, we processed:

```text
bottom → top
```

---

## 931. Minimum Falling Path Sum

A cell can be reached from:

```text
↖ diagonal-left
↑ above
↗ diagonal-right
```

Therefore:

```text
dp[i][j] =
matrix[i][j]
+ min(
    top-left,
    top,
    top-right
)
```

and we process:

```text
top → bottom
```

---

# 🧠 Important DP Pattern

You're now learning to identify DP transitions rather than memorizing individual solutions.

When you see a grid problem, ask:

```text
1. What does dp[i][j] represent?
              ↓
2. Where can I come from?
              ↓
3. How many previous states are possible?
              ↓
4. Is the problem asking for:
       COUNT → sum
       MIN   → min
       MAX   → max
              ↓
5. Which direction should I calculate?
```

For this problem:

```text
dp[i][j]
   ↓
minimum path sum ending here
   ↓
three possible previous cells
   ↓
take minimum
   ↓
add current value
```

---

# ⚠️ Common Mistakes

### 1. Forgetting boundaries

This is the biggest issue in this problem.

Don't try:

```python
matrix[i-1][j-1]
matrix[i-1][j]
matrix[i-1][j+1]
```

for every cell without checking the edges.

For `j == 0`, `j-1` would be invalid logically.

For `j == n-1`, `j+1` is outside the matrix.

---

### 2. Returning the bottom-right cell

Don't do:

```python
return matrix[n-1][n-1]
```

because the falling path can finish anywhere in the last row.

Correct:

```python
return min(matrix[n-1])
```

---

### 3. Processing in the wrong direction

Your state depends on the previous row:

```text
dp[i][j]
    ↓
dp[i-1][...]
```

Therefore:

```text
Top → Bottom
```

---

# 🎯 Pattern Learned

This problem teaches:

* [x] 2D DP
* [x] Minimum DP
* [x] Multiple transitions
* [x] Boundary handling
* [x] In-place DP
* [x] Top → Bottom DP
* [x] Identifying the final answer from an entire row
* [x] `O(1)` extra-space optimization

The most important recurrence is:

```text
dp[i][j] =
matrix[i][j] +
min(
    dp[i-1][j-1],
    dp[i-1][j],
    dp[i-1][j+1]
)
```

with only the **valid neighbors** considered at the boundaries.

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
931  Minimum Falling Path Sum ✓
```

You've now moved from simple 1D DP to more complex **2D transition-based DP**.

The next step should be to move toward a different major placement DP pattern rather than doing many more similar grid problems.
