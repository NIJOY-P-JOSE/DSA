# SAP Labs 2026 — Longest Increasing Path in a Grid

**Pattern:** Grid Dynamic Programming
**Techniques:** Recursion → Memoization → Bottom-Up DP
**Difficulty:** Medium
**Language:** Python

---

## 📌 Problem

Given an `m × n` grid, start at the top-left cell `(0, 0)` and reach the bottom-right cell `(m-1, n-1)`.

You may move only:

* ➡️ Right
* ⬇️ Down

A move is allowed only when:

```text
next cell value > current cell value
```

Return the length of the valid path. If the destination cannot be reached, return `-1`.

### Example

```text
1 2 3 4
2 2 3 5
2 3 4 6
3 4 5 7
```

One valid path is:

```text
1 → 2 → 3 → 5 → 6 → 7
```

The longest valid path contains:

```text
7 cells
```

**Output:**

```text
7
```

---

# 💡 Intuition

At every cell, there are at most two choices:

```text
        Current
        /     \
     Down     Right
```

But a move is possible only when the next value is strictly greater.

For example:

```text
1 → 2
```

is valid because:

```text
2 > 1
```

while:

```text
5 → 4
```

is invalid because:

```text
4 < 5
```

So from every cell we need to find the best valid result from:

* the cell below
* the cell to the right

This naturally leads to Dynamic Programming.

---

# 🧠 Step-by-Step Thinking

## 1. Define the State

Let:

```text
dp[i][j]
```

represent:

> The longest valid increasing path from cell `(i, j)` to the bottom-right cell.

This is the most important step.

---

## 2. Identify the Choices

From `(i, j)`:

### Move Down

Allowed when:

```text
GRID[i+1][j] > GRID[i][j]
```

Then:

```text
1 + dp[i+1][j]
```

### Move Right

Allowed when:

```text
GRID[i][j+1] > GRID[i][j]
```

Then:

```text
1 + dp[i][j+1]
```

Therefore:

```text
dp[i][j] = max(
    1 + dp[i+1][j],
    1 + dp[i][j+1]
)
```

considering only valid moves.

---

# 🧩 Base Case

At the destination:

```text
(i, j) = (m-1, n-1)
```

The path contains the destination itself, so:

```text
dp[m-1][n-1] = 1
```

---

# ❌ Handling Impossible Paths

Use `-1` to represent:

> No valid path exists from this cell to the destination.

For example:

```text
5 4
3 2
```

Starting from `5`:

```text
5 → 4   ❌
5 → 3   ❌
```

Therefore:

```text
dp[0][0] = -1
```

---

# 🔄 Recursive Approach

Before using DP, the problem can be solved recursively.

The recursive function represents:

```text
DP(i, j)
=
longest valid path from (i,j)
```

At each cell:

1. Check Down.
2. Check Right.
3. Ignore invalid moves.
4. Take the maximum valid result.
5. Add `1` for the current cell.

The problem with plain recursion is that the same cells can be solved repeatedly.

For example:

```text
        (0,0)
       /     \
    (1,0)   (0,1)
       \     /
        (1,1)
```

`(1,1)` can be reached through multiple paths.

This creates **overlapping subproblems**.

---

# 🚀 Top-Down DP — Memoization

We can store the answer for every `(i, j)`.

```python
memo = {}

def DP(G, i, j):

    if (i, j) in memo:
        return memo[(i, j)]

    if i == len(G) - 1 and j == len(G[0]) - 1:
        return 1

    ans = -1

    if i < len(G) - 1 and G[i][j] < G[i + 1][j]:
        down = DP(G, i + 1, j)

        if down != -1:
            ans = max(ans, down + 1)

    if j < len(G[0]) - 1 and G[i][j] < G[i][j + 1]:
        right = DP(G, i, j + 1)

        if right != -1:
            ans = max(ans, right + 1)

    memo[(i, j)] = ans
    return ans
```

### Why does this improve the solution?

Each cell is computed only once.

Without memoization:

```text
Repeated recursive calculations
        ↓
Exponential time
```

With memoization:

```text
Each state computed once
        ↓
O(m × n)
```

---

# ⭐ Bottom-Up DP

The same recurrence can be converted into tabulation.

The important observation is:

```text
dp[i][j]
depends on

dp[i+1][j]   ← Down
dp[i][j+1]   ← Right
```

Therefore, those cells must already be calculated.

So we fill the table:

```text
Bottom → Top
Right → Left
```

Iteration order:

```python
for i in range(rows - 1, -1, -1):
    for j in range(cols - 1, -1, -1):
```

---

# 🧠 Dry Run

Consider:

```text
1 2 3
2 3 4
3 4 5
```

Start with:

```text
dp:

-1 -1 -1
-1 -1 -1
-1 -1  1
```

The destination is:

```text
5
```

so:

```text
dp[2][2] = 1
```

Now calculate `(2,1)`:

```text
4 → 5
```

Valid:

```text
dp[2][1] = 1 + dp[2][2]
         = 2
```

Then:

```text
dp:

-1 -1 -1
-1 -1 -1
-1  2  1
```

Continue moving backwards until `(0,0)`.

The final table becomes:

```text
3 4 5
2 3 4
3 2 1
```

Therefore:

```text
dp[0][0] = 5
```

---

# 💻 Bottom-Up Solution

```python
def LONGESTINCPATHLEN(GRID):
    r = len(GRID)
    c = len(GRID[0])

    dp = [[-1] * c for _ in range(r)]

    # Destination
    dp[r - 1][c - 1] = 1

    for i in range(r - 1, -1, -1):
        for j in range(c - 1, -1, -1):

            if i == r - 1 and j == c - 1:
                continue

            # Move Right
            if (j < c - 1
                    and GRID[i][j] < GRID[i][j + 1]
                    and dp[i][j + 1] != -1):

                dp[i][j] = max(
                    dp[i][j],
                    1 + dp[i][j + 1]
                )

            # Move Down
            if (i < r - 1
                    and GRID[i][j] < GRID[i + 1][j]
                    and dp[i + 1][j] != -1):

                dp[i][j] = max(
                    dp[i][j],
                    1 + dp[i + 1][j]
                )

    return dp[0][0]
```

---

# ⚠️ Common Mistake

A common mistake is to overwrite the result when both Right and Down are valid.

Incorrect:

```python
if right:
    dp[i][j] = 1 + dp[i][j + 1]

if down:
    dp[i][j] = 1 + dp[i + 1][j]
```

The second assignment can destroy the better answer.

Instead:

```python
dp[i][j] = max(
    dp[i][j],
    1 + dp[i][j + 1]
)
```

and:

```python
dp[i][j] = max(
    dp[i][j],
    1 + dp[i + 1][j]
)
```

---

# ⚠️ Another Common Mistake

Do not do:

```python
1 + (-1)
```

If a recursive/DP state returns `-1`, it means the destination cannot be reached from that state.

So check:

```python
if next_result != -1:
    answer = 1 + next_result
```

Otherwise, `-1` would incorrectly become `0`.

---

# 🔍 Important Observation

There is an interesting property of this particular problem.

Because movement is restricted to:

```text
Right
Down
```

every successful path from `(0,0)` to `(m-1,n-1)` contains exactly:

```text
m + n - 1
```

cells.

Therefore, if **any valid increasing path exists**, its length is automatically:

```text
m + n - 1
```

So the "longest" part of the problem does not actually require choosing between different path lengths.

The real challenge is determining whether an increasing path exists.

The DP solution still works and is a useful **Grid DP practice problem**, especially for understanding state, transitions, memoization, and tabulation.

---

# 📊 Complexity Analysis

Let:

```text
m = number of rows
n = number of columns
```

### Bottom-Up DP

Every cell is processed once.

**Time Complexity:**

```text
O(m × n)
```

**Space Complexity:**

```text
O(m × n)
```

for the DP table.

---

# 🔄 Top-Down vs Bottom-Up

| Approach        |        Time |  Space | Notes                         |
| --------------- | ----------: | -----: | ----------------------------- |
| Plain Recursion | Exponential | O(m+n) | Repeated calculations         |
| Memoization     |       O(mn) |  O(mn) | Easy to derive from recursion |
| Bottom-Up       |       O(mn) |  O(mn) | No recursion overhead         |

For placement interviews, both **Memoization** and **Tabulation** are important.

The key learning progression is:

```text
Recursion
    ↓
Memoization
    ↓
Tabulation
    ↓
Space Optimization
```

---

# 🎯 Interview Notes

### Pattern

```text
Grid Dynamic Programming
```

### Core concepts

* Recursion
* Overlapping Subproblems
* Memoization
* Tabulation
* Grid Traversal
* State Transition
* Base Cases
* Handling Impossible States

### Interview Questions to Expect

An interviewer may ask:

> Why do we traverse from bottom-right to top-left?

Because `dp[i][j]` depends on the cells **below** and **to the right**.

> Why is memoization needed?

Because the same grid cell can be reached through multiple recursive paths.

> Can you solve it without recursion?

Yes. Use bottom-up tabulation.

> Can you reduce the space complexity?

For the general DP formulation, space optimization depends on the dependency structure. Since each state depends on the next row and the right cell, a rolling-row approach can reduce the table storage, although the exact implementation needs careful handling.

---

# 🧠 Pattern to Remember

For grid problems, ask:

```text
1. What does dp[i][j] represent?
             ↓
2. Where can I move?
             ↓
3. What conditions make a move valid?
             ↓
4. What are the dependent states?
             ↓
5. What should the base case be?
             ↓
6. Which direction should I fill the table?
```

For this problem:

```text
State:
dp[i][j] = longest valid path from (i,j)

Moves:
Right / Down

Condition:
next > current

Transition:
1 + max(right, down)

Base:
destination = 1

Fill direction:
bottom-right → top-left
```

---

# 🔗 Related Problems

For more practice with the same DP thinking:

* **62. Unique Paths** — Grid DP
* **63. Unique Paths II** — Grid DP with obstacles
* **64. Minimum Path Sum** — Grid DP with optimization
* **120. Triangle** — Bottom-Up DP
* **931. Minimum Falling Path Sum** — Grid DP
* **1301. Number of Paths with Max Score** — Grid DP

---

# 📚 Concepts Practiced

* [x] Recursion
* [x] Dynamic Programming
* [x] Top-Down DP
* [x] Memoization
* [x] Bottom-Up DP
* [x] Grid DP
* [x] State Definition
* [x] State Transition
* [x] Base Cases
* [x] Handling Invalid States
* [x] Time/Space Complexity Analysis

---

# 👨‍💻 Author

**Nijoy P Jose**

This solution is part of my **Data Structures & Algorithms** placement preparation repository, where I document problem-solving patterns, interview techniques, and implementations to strengthen my coding skills.
