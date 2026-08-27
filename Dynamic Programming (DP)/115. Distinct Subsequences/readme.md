# 115. Distinct Subsequences

> **Pattern:** 2D DP · String DP · Take/Skip DP · Counting DP
> **Difficulty:** Medium
> **Platform:** LeetCode
> **Problem:** [LeetCode 115 — Distinct Subsequences](https://leetcode.com/problems/distinct-subsequences/?utm_source=chatgpt.com)

---

## 🧠 Problem

Given two strings `s` and `t`, return the **number of distinct subsequences of `s` that equal `t`**.

A subsequence is formed by deleting some characters while keeping the remaining characters in the same order.

### Example

```text
s = "rabbbit"
t = "rabbit"

Output = 3
```

The three ways come from choosing **2 of the 3 `b`s** in `s`.

---

# 🎯 DP State

Define:

```python
dp[i][j]
```

as:

> **The number of ways to form the first `j` characters of `t` using the first `i` characters of `s`.**

For example:

```text
dp[5][4]
```

means:

```text
How many ways can s[:5] form t[:4]?
```

This definition is the most important thing to remember.

---

# 🔍 How to Derive the Recurrence

Compare:

```python
s[i-1]
t[j-1]
```

There are two cases.

## 1️⃣ Characters are different

```python
s[i-1] != t[j-1]
```

The current character of `s` cannot be used to match the current character of `t`.

So we **skip** it.

```text
s[i-1] → SKIP
```

Therefore:

```python
dp[i][j] = dp[i-1][j]
```

### Memory trick

```text
DIFFERENT
    ↓
Cannot use
    ↓
SKIP s[i-1]
    ↓
dp[i-1][j]
```

---

# 2️⃣ Characters are the same

```python
s[i-1] == t[j-1]
```

Now we have **two choices**.

### Choice A — Take it

Use `s[i-1]` to match `t[j-1]`.

```python
dp[i-1][j-1]
```

### Choice B — Skip it

Don't use this character. There might be another matching character later.

```python
dp[i-1][j]
```

Since we are **counting ways**, add them:

```python
dp[i][j] = dp[i-1][j-1] + dp[i-1][j]
```

### Memory trick

```text
SAME
 ↓
TAKE OR SKIP
 ↓
TAKE → dp[i-1][j-1]
SKIP → dp[i-1][j]
 ↓
ADD
```

---

# ⭐ Recurrence

```python
if s[i-1] == t[j-1]:
    dp[i][j] = dp[i-1][j-1] + dp[i-1][j]
else:
    dp[i][j] = dp[i-1][j]
```

### 🔥 One-line revision

> **Same → Take + Skip. Different → Skip.**

---

# 🧱 Base Case

The most important base case:

```python
dp[i][0] = 1
```

Why?

`j = 0` means the target is empty:

```text
t[:0] = ""
```

There is exactly **one way** to form an empty subsequence:

```text
Choose nothing.
```

Therefore:

```text
dp[0][0] = 1
dp[1][0] = 1
dp[2][0] = 1
...
```

For:

```text
s = ""
t = "abc"
```

there are zero ways to form a non-empty target:

```text
dp[0][j] = 0   (j > 0)
```

This is already provided by initializing the DP table with `0`.

---

# 🗺️ DP Table Meaning

For:

```text
s = "rabbbit"
t = "rabbit"
```

Conceptually:

```text
             t →
          ""  r  a  b  b  i  t
       ┌────────────────────────
s ""   │ 1   0  0  0  0  0  0
  r    │ 1   1
  a    │ 1   1  1
  b    │ 1   1  1  1
  b    │ 1   1  1  2
  b    │ 1   1  1  3
  i    │ 1   1  1  3
  t    │ 1   1  1  3
```

The final cell:

```python
dp[7][6]
```

means:

> Number of ways to form all of `"rabbit"` using all of `"rabbbit"`.

Therefore:

```text
answer = 3
```

---

# 💻 Your Solution

```python
class Solution:
    def numDistinct(self, s: str, t: str) -> int:
        ls = len(s)
        lt = len(t)

        dp = [[0] * (lt + 1) for _ in range(ls + 1)]

        for i in range(ls + 1):
            dp[i][0] = 1

        for i in range(1, ls + 1):
            for j in range(1, lt + 1):
                if s[i - 1] == t[j - 1]:
                    dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j]
                else:
                    dp[i][j] = dp[i - 1][j]

        return dp[ls][lt]
```

---

# 🔎 Code → Logic

### Create DP table

```python
dp = [[0] * (lt + 1) for _ in range(ls + 1)]
```

`+1` is needed to represent the empty string.

---

### Empty target

```python
for i in range(ls + 1):
    dp[i][0] = 1
```

Any string can form an empty target in exactly one way:

```text
Take nothing.
```

---

### Main calculation

```python
for i in range(1, ls + 1):
    for j in range(1, lt + 1):
```

We start at `1` because row/column `0` represent empty strings.

---

### Character match

```python
if s[i - 1] == t[j - 1]:
```

If they match:

```python
dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j]
```

Meaning:

```text
             MATCH
               ↓
        ┌──────┴──────┐
        ↓             ↓
       TAKE          SKIP
        ↓             ↓
 dp[i-1][j-1]    dp[i-1][j]
        └──────┬──────┘
               ↓
              ADD
```

---

### No match

```python
else:
    dp[i][j] = dp[i - 1][j]
```

The character cannot be used, so skip it.

---

# 🧠 Why Are We Adding?

This is a **counting DP** problem.

Suppose:

```text
TAKE → 2 ways
SKIP → 3 ways
```

These are separate possibilities:

```text
2 + 3 = 5
```

Therefore:

```python
dp[i][j] = take + skip
```

This is different from problems asking for minimum or maximum.

| Goal           | Typical combination |
| -------------- | ------------------- |
| Number of ways | `+`                 |
| Minimum        | `min()`             |
| Maximum        | `max()`             |
| Possible?      | Boolean / `or`      |

---

# 🔥 LCS vs Distinct Subsequences

You already solved **LCS**, so this comparison is useful.

### LCS

```text
Same:
    1 + diagonal

Different:
    max(up, left)
```

Because LCS asks:

> What is the **longest** common subsequence?

---

### Distinct Subsequences

```text
Same:
    diagonal + up

Different:
    up
```

Because this problem asks:

> How many ways can I form the target?

The important new idea is:

```text
MATCH
 ↓
TAKE OR SKIP
 ↓
ADD
```

---

# 🚨 Common Mistake

You initially had:

```python
else:
    dp[i][j] = dp[i-1][j-1]
```

That is incorrect.

When characters don't match:

```text
s[i-1] != t[j-1]
```

you **cannot** match them diagonally.

You must skip the current character of `s`:

```python
dp[i][j] = dp[i-1][j]
```

---

# 🎯 Placement Recognition

If you see:

> **Two strings + subsequence + count the number of ways**

Think:

```text
Two strings
    ↓
2D DP
    ↓
Compare characters
    ↓
SAME?
 ┌──┴──┐
YES   NO
 ↓     ↓
Take  Skip
OR
Skip
 ↓
 ADD
```

### Trigger phrase

> **"Counting subsequences" → Think Take/Skip DP.**

---

# ⏱️ Complexity

Let:

```text
n = len(s)
m = len(t)
```

There are `n × m` states.

```text
Time:  O(n × m)
Space: O(n × m)
```

---

# ⚡ 30-Second Revision

```text
╔══════════════════════════════════════════╗
║       115. DISTINCT SUBSEQUENCES         ║
╠══════════════════════════════════════════╣
║ Pattern: 2D DP + Take/Skip + Counting    ║
║                                          ║
║ dp[i][j] = number of ways to form        ║
║            t[:j] using s[:i]             ║
║                                          ║
║ SAME:                                    ║
║   TAKE + SKIP                            ║
║   dp[i-1][j-1] + dp[i-1][j]              ║
║                                          ║
║ DIFFERENT:                               ║
║   SKIP                                    ║
║   dp[i-1][j]                              ║
║                                          ║
║ BASE:                                    ║
║   dp[i][0] = 1                           ║
║                                          ║
║ WHY ADD?                                 ║
║   We are counting different ways.        ║
║                                          ║
║ Time:  O(n × m)                          ║
║ Space: O(n × m)                          ║
╚══════════════════════════════════════════╝
```

## ⭐ Core idea

> **If `s[i-1] == t[j-1]`, we can either TAKE the character or SKIP it, so add both possibilities. If they don't match, we can only SKIP.**
