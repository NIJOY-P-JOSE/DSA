# 120. Triangle

**Pattern:** Dynamic Programming — 2D / Bottom-Up DP
**Difficulty:** Medium
**Platform:** LeetCode
**Problem:** [120. Triangle](https://leetcode.com/problems/triangle/)

---

## 📌 Problem Statement

Given a triangular array of integers, return the **minimum path sum from top to bottom**.

At each position `(i, j)`, you can move to either:

```text
(i + 1, j)
```

or:

```text
(i + 1, j + 1)
```

In other words, from a number in the current row, you can move to one of the two adjacent numbers in the next row.

---

## Example 1

```text
Input:
[
    [2],
    [3,4],
    [6,5,7],
    [4,1,8,3]
]

Output:
11
```

The minimum path is:

```text
2 → 3 → 5 → 1
```

Sum:

```text
2 + 3 + 5 + 1 = 11
```

---

## Example 2

```text
Input:
[[-10]]

Output:
-10
```

---

# 💡 DP Idea

This is another **2D DP** problem, similar to:

* 62. Unique Paths
* 63. Unique Paths II
* 64. Minimum Path Sum

But the shape of the grid is different.

At each position:

```text
        (i,j)
        /   \
       /     \
      ↓       ↓
 (i+1,j)  (i+1,j+1)
```

There are two possible next positions.

---

# 🧠 DP State

Define:

```text
dp[i][j]
```

as:

> The minimum path sum from position `(i, j)` to the bottom of the triangle.

For example:

```text
        2
       / \
      3   4
     / \ / \
    6  5 7
   /\/\/\/\/\
  4  1  8  3
```

At `(i, j)`, we have two choices:

```text
dp[i+1][j]
```

or:

```text
dp[i+1][j+1]
```

Since we want the **minimum**:

```text
dp[i][j] =
triangle[i][j] +
min(
    dp[i+1][j],
    dp[i+1][j+1]
)
```

---

# 🔄 Why Bottom-Up?

The state:

```text
dp[i][j]
```

depends on:

```text
dp[i+1][j]
dp[i+1][j+1]
```

So it depends on the **row below**.

Therefore, we should calculate:

```text
bottom → top
```

rather than top → bottom.

```text
Row 3  ← already known
   ↓
Row 2
   ↓
Row 1
   ↓
Row 0
```

This is the same principle you learned with Decode Ways:

> Calculate the states that your current state depends on first.

---

# 🧱 Base Case

The last row already contains the actual values.

For example:

```text
4  1  8  3
```

These values can be treated as the initial DP values.

Then we calculate the rows above them.

---

# 🔍 Dry Run

Consider:

```text
[
    [2],
    [3,4],
    [6,5,7],
    [4,1,8,3]
]
```

Start with the bottom:

```text
4  1  8  3
```

### Row `[6,5,7]`

For `6`:

```text
6 + min(4,1)
= 7
```

For `5`:

```text
5 + min(1,8)
= 6
```

For `7`:

```text
7 + min(8,3)
= 10
```

Now:

```text
7  6  10
```

---

### Row `[3,4]`

For `3`:

```text
3 + min(7,6)
= 9
```

For `4`:

```text
4 + min(6,10)
= 10
```

Now:

```text
9  10
```

---

### Row `[2]`

```text
2 + min(9,10)
= 11
```

Final answer:

```text
11
```

---

# 💻 Bottom-Up DP Solution

This is the solution you used:

```python
class Solution:
    def minimumTotal(self, triangle: List[List[int]]) -> int:
        r = len(triangle)

        if r == 1 and len(triangle[0]) == 1:
            return triangle[0][0]

        dp = triangle

        for i in range(len(triangle) - 2, -1, -1):
            for j in range(len(triangle[i]) - 1, -1, -1):
                dp[i][j] += min(
                    dp[i+1][j],
                    dp[i+1][j+1]
                )

        return dp[0][0]
```

### Complexity

There are approximately:

```text
1 + 2 + 3 + ... + n
```

elements in the triangle.

Therefore:

```text
Time:  O(n²)
```

where `n` is the number of rows.

Because you modify the triangle itself:

```python
dp = triangle
```

you are using:

```text
Extra Space: O(1)
```

apart from the input array.

---

# ⚠️ `dp = triangle` vs Copying

In your solution:

```python
dp = triangle
```

does **not create a new DP array**.

Both variables point to the same triangle.

Therefore:

```python
dp[i][j] += ...
```

also modifies:

```python
triangle[i][j]
```

That's fine for this problem because modifying the input is allowed.

If you wanted to preserve the original triangle, you could make a copy:

```python
dp = [row[:] for row in triangle]
```

But that would require `O(n²)` extra space.

---

# ✨ Space-Optimized Version

The follow-up asks whether we can use only `O(n)` extra space.

Yes.

We can use a 1D DP array initialized with the last row:

```python
class Solution:
    def minimumTotal(self, triangle: List[List[int]]) -> int:
        dp = triangle[-1][:]

        for i in range(len(triangle) - 2, -1, -1):
            for j in range(len(triangle[i])):
                dp[j] = triangle[i][j] + min(dp[j], dp[j+1])

        return dp[0]
```

Here:

```text
dp[j]
```

represents the minimum path sum from the current position down to the bottom.

### Complexity

```text
Time:  O(n²)
Space: O(n)
```

where `n` is the number of rows.

This satisfies the follow-up requirement.

---

# 🔄 Comparison with Minimum Path Sum

You just solved **64. Minimum Path Sum**.

### 64. Minimum Path Sum

From `(i,j)` you come **from**:

```text
above
left
```

Therefore:

```text
dp[i][j] =
grid[i][j] +
min(
    dp[i-1][j],
    dp[i][j-1]
)
```

### 120. Triangle

From `(i,j)` you move **to**:

```text
below-left
below-right
```

Therefore:

```text
dp[i][j] =
triangle[i][j] +
min(
    dp[i+1][j],
    dp[i+1][j+1]
)
```

The important difference is the **direction of the dependency**.

```text
64:
     ↑
 ← [i,j]
     ↑

120:

 [i,j]
 /   \
↓     ↓
```

This is why **64 naturally goes top → bottom**, while your 120 solution goes **bottom → top**.

---

# 🧠 How to Recognize This Pattern

When you see a problem asking for a **minimum/maximum path through a grid or triangle**, ask:

### 1. What is my state?

```text
dp[i][j]
```

### 2. Where can I go?

For Triangle:

```text
(i+1,j)
(i+1,j+1)
```

### 3. What does the problem ask?

Minimum:

```text
min(...)
```

### 4. What value do I add?

The current cell:

```text
triangle[i][j]
```

So the recurrence almost writes itself:

```text
dp[i][j] =
triangle[i][j]
+ min(dp[i+1][j], dp[i+1][j+1])
```

### 5. Which direction should I calculate?

Because the current state depends on the row below:

```text
bottom → top
```

---

# 🎯 DP Pattern Learned

This problem reinforces an important DP pattern:

```text
Current State
     ↓
Multiple Next States
     ↓
Choose MIN / MAX
     ↓
Add Current Value
```

For Triangle:

```text
             current
             (i,j)
             /   \
            ↓     ↓
       (i+1,j) (i+1,j+1)
            \     /
             min
              ↓
        + current value
```

The general recurrence is:

```text
dp[i][j] =
value[i][j] +
min(next_state_1, next_state_2)
```

---

# 📚 DP Progression

Your current sequence is:

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
120  Triangle ✓
```

You've now practiced:

* 1D DP
* Counting DP
* Min/Max DP
* Take/Skip DP
* Multiple-choice DP
* 2D Grid DP
* Obstacles
* Minimum-cost paths
* Triangle/path DP
* Bottom-Up DP
* In-place DP
* Space optimization

---

# 📌 Key Takeaway

The biggest lesson from **120. Triangle** is not the code.

It's this:

> **Look at what your current state depends on, then process the states in the required direction.**

Here:

```text
dp[i][j]
    ↓
depends on
    ↓
dp[i+1][j] and dp[i+1][j+1]
    ↓
therefore
    ↓
bottom → top
```

That same reasoning will help you determine the iteration direction in many unfamiliar DP problems.
