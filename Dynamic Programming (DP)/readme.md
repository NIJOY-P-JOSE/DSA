# 1143. Longest Common Subsequence

> **Pattern:** 2D DP · String DP · Sequence Matching
> **Difficulty:** Medium
> **Platform:** LeetCode
> **Problem:** [LeetCode 1143 — Longest Common Subsequence](https://leetcode.com/problems/longest-common-subsequence/?utm_source=chatgpt.com)

---

## ⚡ Quick Revision

| Concept              | Remember                              |
| -------------------- | ------------------------------------- |
| Goal                 | **Longest common subsequence length** |
| DP Type              | **2D DP**                             |
| State                | `dp[i][j]`                            |
| Meaning              | LCS of `text1[:i]` and `text2[:j]`    |
| Equal characters     | `1 + dp[i-1][j-1]`                    |
| Different characters | `max(dp[i-1][j], dp[i][j-1])`         |
| Base Case            | Empty string → `0`                    |
| Time                 | `O(n × m)`                            |
| Space                | `O(n × m)`                            |

---

# 🧠 What is a Subsequence?

A subsequence is created by deleting characters **without changing the relative order**.

Example:

```text
text = "abcde"
```

Valid subsequences:

```text
"ace"
"abc"
"bd"
""
```

But:

```text
"ca"
```

is **not** a subsequence because the order changed.

---

# 🎯 Problem

Given:

```text
text1 = "abcde"
text2 = "ace"
```

Find the longest sequence that appears in **both** strings while maintaining order.

```text
abcde
 ↓ ↓ ↓
 a c e

ace
```

Answer:

```text
3
```

The LCS is:

```text
"ace"
```

---

# 💡 The Main DP Trick

Don't try to find the entire LCS at once.

Compare the **current characters**:

```text
text1[i]
text2[j]
```

There are only **two cases**.

```text
             Compare characters
                    │
           ┌────────┴────────┐
           │                 │
        Equal             Different
           │                 │
           ↓                 ↓
      Take both         Ignore one
           │              of them
           ↓                 ↓
    1 + diagonal       Take maximum
```

This is the core logic of the problem.

---

# 🧠 DP State

Define:

```python
dp[i][j]
```

as:

> **The length of the LCS between `text1[:i]` and `text2[:j]`.**

Important:

```text
dp[i][j]
```

does **not** mean the LCS starting exactly at character `i` and `j`.

It means the LCS of the **first `i` characters** and **first `j` characters**.

For example:

```text
text1 = "abcde"
text2 = "ace"

dp[3][2]
```

means:

```text
LCS("abc", "ac")
```

---

# 🔄 Recurrence

## Case 1 — Characters are equal

Suppose:

```text
text1[i-1] == text2[j-1]
```

For example:

```text
text1 = "abc"
text2 = "ac"

          ↓
          c
```

Since the characters match, we can include this character.

So:

```text
dp[i][j] = 1 + dp[i-1][j-1]
```

### Why diagonal?

We've used:

```text
text1[i-1]
text2[j-1]
```

So we move to the previous characters:

```text
(i-1, j-1)
```

---

# 🔀 Case 2 — Characters are Different

Suppose:

```text
text1[i-1] != text2[j-1]
```

We cannot use both characters as the same matching character.

We have two choices:

### Ignore `text1[i-1]`

```text
dp[i-1][j]
```

### Ignore `text2[j-1]`

```text
dp[i][j-1]
```

We want the **longest**, so:

```text
dp[i][j] = max(
    dp[i-1][j],
    dp[i][j-1]
)
```

---

# ⭐ Recurrence to Memorize

```text
if text1[i-1] == text2[j-1]:

    dp[i][j] = 1 + dp[i-1][j-1]

else:

    dp[i][j] = max(
        dp[i-1][j],
        dp[i][j-1]
    )
```

This is the most important part of the problem.

---

# 🧱 Base Case

If either string is empty:

```text
LCS("", anything) = 0
LCS(anything, "") = 0
```

Therefore:

```text
dp[0][j] = 0
dp[i][0] = 0
```

If we initialize:

```python
dp = [[0] * (n2 + 1) for _ in range(n1 + 1)]
```

these base cases are automatically handled.

---

# 🗺️ DP Table Visualization

For:

```text
text1 = "abcde"
text2 = "ace"
```

The table conceptually looks like:

```text
        ""  a  c  e
     ┌──────────────
""   │ 0  0  0  0
a    │ 0  1  1  1
b    │ 0  1  1  1
c    │ 0  1  2  2
d    │ 0  1  2  2
e    │ 0  1  2  3
```

Final answer:

```text
dp[5][3] = 3
```

---

# 💻 Bottom-Up Solution

```python
class Solution:
    def longestCommonSubsequence(self, text1: str, text2: str) -> int:
        n1 = len(text1)
        n2 = len(text2)

        dp = [[0] * (n2 + 1) for _ in range(n1 + 1)]

        for i in range(1, n1 + 1):
            for j in range(1, n2 + 1):

                if text1[i - 1] == text2[j - 1]:
                    dp[i][j] = 1 + dp[i - 1][j - 1]

                else:
                    dp[i][j] = max(
                        dp[i - 1][j],
                        dp[i][j - 1]
                    )

        return dp[n1][n2]
```

---

# 🔍 Code Breakdown

### 1. Create the DP table

```python
dp = [[0] * (n2 + 1) for _ in range(n1 + 1)]
```

Why `+1`?

Because we also need to represent an **empty prefix**.

```text
       ""  text2...
""      0
text1   0
```

---

### 2. Start from `1`

```python
for i in range(1, n1 + 1):
    for j in range(1, n2 + 1):
```

Because index `0` represents the empty string.

---

### 3. Compare actual characters

Because DP index `i` represents the first `i` characters:

```python
text1[i - 1]
```

and:

```python
text2[j - 1]
```

So:

```python
if text1[i - 1] == text2[j - 1]:
```

---

### 4. Match

```python
dp[i][j] = 1 + dp[i - 1][j - 1]
```

---

### 5. Don't match

```python
dp[i][j] = max(dp[i - 1][j], dp[i][j - 1])
```

---

### 6. Final answer

```python
return dp[n1][n2]
```

This represents:

```text
LCS(text1, text2)
```

---

# 🌳 Top-Down → Bottom-Up

You first solved this problem recursively.

Your Top-Down state was:

```text
rec(i, j)
```

meaning:

> LCS starting from positions `i` and `j`.

The recurrence was:

```text
equal:
1 + rec(i+1, j+1)

different:
max(
    rec(i+1, j),
    rec(i, j+1)
)
```

Then you added memoization:

```python
dp[(i, j)]
```

### Bottom-Up changes the perspective

Instead of:

```text
"What is the answer from here onward?"
```

we ask:

```text
"What is the answer for the first i and j characters?"
```

Therefore:

```text
Top-Down              Bottom-Up

(i, j)                 (i, j)
  ↓                       ↓
look FORWARD            look BACKWARD
i+1, j+1                i-1, j-1
```

Same underlying logic, different direction.

---

# 🧠 How to Recognize LCS in a Placement

If you see:

> Two strings/sequences and find the longest sequence common to both while preserving order.

Immediately think:

```text
Two strings
     ↓
Compare two positions
     ↓
Need information from BOTH strings
     ↓
2D DP
```

Then ask:

```text
Are current characters equal?
        │
    ┌───┴───┐
   YES      NO
    │        │
    ↓        ↓
 take      ignore one
    │        │
    ↓        ↓
 +1       max(...)
```

---

# 🚨 Common Mistakes

### ❌ Mistake 1 — Using `text1[i]`

If loops start at `1`:

```python
for i in range(1, n1 + 1):
```

use:

```python
text1[i - 1]
```

not:

```python
text1[i]
```

---

### ❌ Mistake 2 — Wrong dimensions

Correct:

```python
dp = [[0] * (n2 + 1) for _ in range(n1 + 1)]
```

Remember:

```text
rows    → n1 + 1
columns → n2 + 1
```

because:

```text
dp[i][j]
 ↑   ↑
row column
```

---

### ❌ Mistake 3 — Wrong recurrence for matching characters

Wrong:

```python
dp[i][j] = 1 + max(dp[i-1][j], dp[i][j-1])
```

Correct:

```python
dp[i][j] = 1 + dp[i-1][j-1]
```

When characters match, **move diagonally**.

---

### ❌ Mistake 4 — Confusing subsequence and substring

**Subsequence:**

```text
abcde → ace
```

Characters don't have to be adjacent.

**Substring:**

```text
abcde → bcd
```

Characters must be continuous.

LCS is **subsequence**, not substring.

---

# ⏱️ Complexity

Let:

```text
n = len(text1)
m = len(text2)
```

There are:

```text
n × m
```

DP states.

Each state takes `O(1)` work.

Therefore:

```text
Time:  O(n × m)
Space: O(n × m)
```

For:

```text
n, m ≤ 1000
```

this is suitable.

---

# 📝 Placement Quick Notes

```text
╔════════════════════════════════════════════╗
║       1143 — LONGEST COMMON SUBSEQUENCE    ║
╠════════════════════════════════════════════╣
║ Pattern: 2D DP / String DP                ║
║                                            ║
║ dp[i][j] = LCS(text1[:i], text2[:j])      ║
║                                            ║
║ If equal:                                  ║
║     dp[i][j] = 1 + dp[i-1][j-1]           ║
║                                            ║
║ If different:                              ║
║     dp[i][j] = max(                         ║
║         dp[i-1][j],                         ║
║         dp[i][j-1]                          ║
║     )                                      ║
║                                            ║
║ Base: dp[0][j] = dp[i][0] = 0             ║
║                                            ║
║ Time:  O(n × m)                            ║
║ Space: O(n × m)                            ║
╚════════════════════════════════════════════╝
```

---

# 🧠 The Mental Trick

When you see **two strings + longest common sequence**, imagine:

```text
text1[i]          text2[j]
    │                 │
    └──── compare ────┘
             │
       ┌─────┴─────┐
       │           │
     SAME       DIFFERENT
       │           │
       ↓           ↓
    take both   skip one
       │           │
       ↓           ↓
  1 + diagonal   max(left, up)
```

### One-line memory:

> **LCS = Match → diagonal + 1; No match → max(up, left).**

This is one of the most important **2D String DP patterns** for placement coding.
