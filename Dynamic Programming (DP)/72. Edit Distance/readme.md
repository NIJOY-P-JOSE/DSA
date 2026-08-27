# 72. Edit Distance

> **Pattern:** 2D DP · String DP · Minimum Cost DP
> **Difficulty:** Medium
> **Platform:** LeetCode
> **Problem:** [LeetCode 72 — Edit Distance](https://leetcode.com/problems/edit-distance/?utm_source=chatgpt.com)

---

## ⚡ Quick Revision

| Concept         | Remember                                          |
| --------------- | ------------------------------------------------- |
| Goal            | Minimum operations to convert `word1 → word2`     |
| Operations      | Insert, Delete, Replace                           |
| DP Type         | 2D DP                                             |
| State           | `dp[i][j]`                                        |
| Meaning         | Min operations to convert `word1[:i] → word2[:j]` |
| Same characters | `dp[i-1][j-1]`                                    |
| Different       | `1 + min(3 choices)`                              |
| Insert          | `dp[i][j-1]`                                      |
| Delete          | `dp[i-1][j]`                                      |
| Replace         | `dp[i-1][j-1]`                                    |
| Base            | `dp[i][0] = i`, `dp[0][j] = j`                    |
| Time            | `O(n × m)`                                        |
| Space           | `O(n × m)`                                        |

---

# 🧠 Problem

Convert:

```text
word1 → word2
```

using the minimum number of:

```text
✏️ Insert
❌ Delete
🔄 Replace
```

Example:

```text
horse → ros
```

One possible sequence:

```text
horse
  ↓ replace h → r
rorse
  ↓ delete r
rose
  ↓ delete e
ros
```

Answer:

```text
3
```

---

# 🎯 The DP State

Define:

```python
dp[i][j]
```

as:

> **The minimum number of operations required to convert the first `i` characters of `word1` into the first `j` characters of `word2`.**

For example:

```text
dp[3][2]
```

means:

```text
word1[:3] → word2[:2]
```

This definition is the most important part.

---

# 🧩 How to Derive the Logic

At every cell, compare:

```python
word1[i-1]
word2[j-1]
```

There are only two main cases:

```text
              Compare
                 │
         ┌───────┴───────┐
         │               │
       SAME           DIFFERENT
         │               │
         ↓               ↓
    No operation      3 choices
```

---

# ✅ Case 1: Characters Are Same

If:

```python
word1[i-1] == word2[j-1]
```

we don't need to perform an operation.

Example:

```text
word1: ... a
word2: ... a
          ↑
       already same
```

So we simply use the answer for the previous characters:

```python
dp[i][j] = dp[i-1][j-1]
```

### Memory trick

```text
SAME
 ↓
Nothing to change
 ↓
DIAGONAL
```

---

# ❌ Case 2: Characters Are Different

If:

```python
word1[i-1] != word2[j-1]
```

we have **three possible operations**.

```text
        Different
            │
    ┌───────┼───────┐
    ↓       ↓       ↓
 Delete   Insert  Replace
```

We choose the operation requiring the **minimum** number of operations.

---

## 1️⃣ Delete

Delete the current character from `word1`.

```text
word1[:i] → word1[:i-1]
```

So we use:

```python
dp[i-1][j]
```

Cost:

```python
1 + dp[i-1][j]
```

### Direction

```text
UP ↑
```

---

## 2️⃣ Insert

Insert the required character from `word2`.

We already handle the first `j-1` characters of `word2`:

```python
dp[i][j-1]
```

Then perform one insertion.

Cost:

```python
1 + dp[i][j-1]
```

### Direction

```text
LEFT ←
```

---

## 3️⃣ Replace

Replace `word1[i-1]` with `word2[j-1]`.

Now both current characters are handled, so we go diagonally:

```python
dp[i-1][j-1]
```

Cost:

```python
1 + dp[i-1][j-1]
```

### Direction

```text
DIAGONAL ↖
```

---

# ⭐ Main Recurrence

### Same:

```python
dp[i][j] = dp[i-1][j-1]
```

### Different:

```python
dp[i][j] = 1 + min(
    dp[i-1][j],      # Delete
    dp[i][j-1],      # Insert
    dp[i-1][j-1]     # Replace
)
```

### 🔥 Quick memory

```text
SAME      → DIAGONAL

DIFFERENT → 1 + MIN(
                UP,
                LEFT,
                DIAGONAL
            )
```

---

# 🧱 Base Cases

These are extremely important.

## `word1 → ""`

Example:

```text
"abc" → ""
```

We must delete everything:

```text
abc
 ↓
bc
 ↓
c
 ↓
""
```

Therefore:

```python
dp[i][0] = i
```

---

## `"" → word2`

Example:

```text
"" → "abc"
```

We must insert everything:

```text
""
 ↓
a
 ↓
ab
 ↓
abc
```

Therefore:

```python
dp[0][j] = j
```

---

# 🗺️ DP Table Meaning

For:

```text
word1 = "ab"
word2 = "ac"
```

the table represents:

```text
             word2
              ""  a  c
             ┌─────────
word1    ""  │  0  1  2
          a  │  1  0  1
          b  │  2  1  1
```

Every number answers a **smaller version of the original problem**.

For example:

```text
dp[2][2] = 1
```

means:

```text
"ab" → "ac"
```

requires one operation.

Specifically:

```text
ab
↓ replace b with c
ac
```

---

# 🔥 Why Does the Table Give the Answer?

The final cell:

```python
dp[n1][n2]
```

represents:

```text
word1[:n1] → word2[:n2]
```

which is simply:

```text
word1 → word2
```

Therefore:

```python
return dp[n1][n2]
```

---

# 💻 Bottom-Up Solution

```python
class Solution:
    def minDistance(self, word1: str, word2: str) -> int:
        n1 = len(word1)
        n2 = len(word2)

        dp = [[0] * (n2 + 1) for _ in range(n1 + 1)]

        for i in range(n1 + 1):
            dp[i][0] = i

        for i in range(n2 + 1):
            dp[0][i] = i

        for i in range(1, n1 + 1):
            for j in range(1, n2 + 1):

                if word1[i - 1] == word2[j - 1]:
                    dp[i][j] = dp[i - 1][j - 1]

                else:
                    dp[i][j] = 1 + min(
                        dp[i - 1][j - 1],
                        dp[i - 1][j],
                        dp[i][j - 1]
                    )

        return dp[n1][n2]
```

---

# 🔍 Code → Logic

### Create table

```python
dp = [[0] * (n2 + 1) for _ in range(n1 + 1)]
```

Why `+1`?

Because we need to represent the empty string:

```text
""
```

---

### Initialize first column

```python
for i in range(n1 + 1):
    dp[i][0] = i
```

Because:

```text
i characters → empty
```

requires `i` deletions.

---

### Initialize first row

```python
for i in range(n2 + 1):
    dp[0][i] = i
```

Because:

```text
empty → i characters
```

requires `i` insertions.

---

### Compare characters

```python
word1[i - 1] == word2[j - 1]
```

We use `i-1` and `j-1` because:

```text
DP index 1 → string index 0
DP index 2 → string index 1
DP index 3 → string index 2
```

---

# 🧠 Visual Cheat Sheet

```text
                 dp[i][j]
                    │
             Compare characters
                    │
          ┌─────────┴─────────┐
          │                   │
        SAME              DIFFERENT
          │                   │
          ↓                   ↓
     dp[i-1][j-1]        1 + min(...)
                              │
                  ┌───────────┼───────────┐
                  ↓           ↓           ↓
                DELETE      INSERT      REPLACE
                  ↓           ↓           ↓
               UP ↑        LEFT ←     DIAGONAL ↖
```

---

# 🔄 LCS vs Edit Distance

You just learned **1143. Longest Common Subsequence**, so compare them.

### LCS

```text
SAME:
    1 + diagonal

DIFFERENT:
    max(up, left)
```

Because we want the **longest**.

### Edit Distance

```text
SAME:
    diagonal

DIFFERENT:
    1 + min(
        up,
        left,
        diagonal
    )
```

Because we want the **minimum operations**.

### Quick comparison

|           | LCS            | Edit Distance               |
| --------- | -------------- | --------------------------- |
| Goal      | Longest        | Minimum                     |
| Same      | `1 + diagonal` | `diagonal`                  |
| Different | `max(up,left)` | `1 + min(up,left,diagonal)` |
| DP        | 2D             | 2D                          |
| Strings   | 2              | 2                           |

---

# 🧠 Placement Thinking Trick

When you see:

> **Two strings + minimum operations to transform one into another**

Immediately think:

```text
Two strings
     ↓
Two indices
     ↓
2D DP
```

Then:

```text
Compare current characters
          ↓
     ┌────┴────┐
    Same    Different
     │          │
     ↓          ↓
  diagonal   operations
                │
       ┌────────┼────────┐
       ↓        ↓        ↓
     Delete   Insert   Replace
       ↓        ↓        ↓
      UP      LEFT    DIAGONAL
                │
                ↓
              MIN
```

---

# 🚨 Common Mistakes

### ❌ Starting loops from `0`

Don't do:

```python
for i in range(n1 + 1):
    for j in range(n2 + 1):
        word1[i - 1]
```

because `i = 0` gives `word1[-1]`.

Instead:

```python
for i in range(1, n1 + 1):
    for j in range(1, n2 + 1):
```

---

### ❌ Forgetting `i - 1`

DP index:

```text
i
```

corresponds to string index:

```text
i - 1
```

So:

```python
word1[i - 1]
word2[j - 1]
```

---

### ❌ Forgetting the base cases

Remember:

```python
dp[i][0] = i
dp[0][j] = j
```

---

### ❌ Using `max()`

This problem asks:

> **Minimum operations**

Therefore use:

```python
min()
```

not:

```python
max()
```

---

# ⏱️ Complexity

Let:

```text
n = len(word1)
m = len(word2)
```

There are `n × m` states.

Each state takes constant work.

```text
Time:  O(n × m)
Space: O(n × m)
```

---

# 📝 Exam Quick Notes

```text
╔══════════════════════════════════════════════╗
║          72. EDIT DISTANCE                   ║
╠══════════════════════════════════════════════╣
║ Pattern: 2D DP / String DP                  ║
║                                              ║
║ dp[i][j] = min operations to convert        ║
║            word1[:i] → word2[:j]            ║
║                                              ║
║ SAME:                                        ║
║   dp[i][j] = dp[i-1][j-1]                    ║
║                                              ║
║ DIFFERENT:                                   ║
║   dp[i][j] = 1 + min(                       ║
║       Delete  → dp[i-1][j],                  ║
║       Insert  → dp[i][j-1],                  ║
║       Replace → dp[i-1][j-1]                 ║
║   )                                          ║
║                                              ║
║ Base:                                        ║
║   dp[i][0] = i                               ║
║   dp[0][j] = j                               ║
║                                              ║
║ Time:  O(n × m)                              ║
║ Space: O(n × m)                              ║
╚══════════════════════════════════════════════╝
```

## ⭐ One sentence to remember

> **Same characters → move diagonal with no cost; different characters → try Delete, Insert, and Replace, take the minimum, and add 1.**

This is the key pattern you should be able to reconstruct in a placement round without memorizing the code.
