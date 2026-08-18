# 91. Decode Ways

**Pattern:** Dynamic Programming — 1D DP / Counting Ways
**Difficulty:** Medium
**Platform:** LeetCode
**Problem:** [91. Decode Ways](https://leetcode.com/problems/decode-ways/)

---

## 📌 Problem Statement

You are given a string `s` containing digits.

The digits are decoded using:

```text
1  → A
2  → B
...
25 → Y
26 → Z
```

Return the **number of different ways** the entire string can be decoded.

A single digit can be used if it is from `1` to `9`.

Two digits can be used together if they form a number from `10` to `26`.

A leading `0` is invalid.

---

## Examples

### Example 1

```text
Input:  "12"
Output: 2
```

Possible decodings:

```text
1 | 2  → AB
12     → L
```

---

### Example 2

```text
Input:  "226"
Output: 3
```

Possible decodings:

```text
2 | 2 | 6
2 | 26
22 | 6
```

Therefore:

```text
Output: 3
```

---

### Example 3

```text
Input:  "06"
Output: 0
```

`06` cannot be decoded because `0` cannot be used as a single digit and `06` is not a valid two-digit code.

---

# 💡 Core Idea

At every position, we have at most **two choices**:

```text
1. Take one digit
2. Take two digits
```

For example:

```text
226
↑
```

We can take:

```text
2
```

or:

```text
22
```

So the problem naturally forms a recursion tree.

```text
                    "226"
                   /     \
                  2       22
                  |        |
                 "26"      "6"
                /   \       |
               2     26      6
```

This is a **counting DP** problem because we need to count all valid choices.

---

# 🧠 DP State

Define:

```text
dp(i) = number of ways to decode s[i:]
```

In other words:

> `dp(i)` represents the number of valid ways to decode the string starting from index `i`.

For:

```text
s = "226"
```

we have:

```text
dp(0) → ways to decode "226"
dp(1) → ways to decode "26"
dp(2) → ways to decode "6"
dp(3) → ways to decode ""
```

---

# 🧱 Base Cases

### Reached the end

If:

```text
i == n
```

we have successfully decoded the entire string.

There is one valid way to complete the decoding:

```text
dp(n) = 1
```

### Current digit is `0`

`0` cannot be decoded by itself.

Therefore:

```text
s[i] == '0' → dp(i) = 0
```

---

# 🔄 Recurrence

First, take one digit.

If:

```text
s[i] != '0'
```

then:

```text
dp(i) = dp(i + 1)
```

Now check whether the next two digits form a valid code:

```text
10 <= int(s[i:i+2]) <= 26
```

If valid, we have another choice:

```text
dp(i + 2)
```

Therefore:

```text
dp(i) = dp(i + 1) + dp(i + 2)
```

when the two-digit number is valid.

More explicitly:

```text
if s[i] == '0':
    dp(i) = 0
else:
    dp(i) = dp(i + 1)

    if 10 <= s[i:i+2] <= 26:
        dp(i) += dp(i + 2)
```

---

# 🌳 Recursion Example — `"226"`

```text
                         dp(0)
                         "226"
                       /       \
                    "2"         "22"
                     ↓            ↓
                   dp(1)        dp(2)
                   "26"          "6"
                  /    \           |
                "2"    "26"       "6"
                 ↓       ↓          ↓
               dp(2)   dp(3)      dp(3)
                 ↓       ↓          ↓
                 1       1          1
```

Therefore:

```text
dp(2) = 1

dp(1) = dp(2) + dp(3)
      = 1 + 1
      = 2

dp(0) = dp(1) + dp(2)
      = 2 + 1
      = 3
```

Answer:

```text
3
```

---

# 🔼 Top-Down DP — Memoization

The recursive solution can contain repeated states.

For example:

```text
dp(2)
```

can be reached from multiple branches.

So we store already calculated states in a dictionary.

### Python

```python
class Solution:
    def numDecodings(self, s: str) -> int:
        mem = {}

        def dp(i):
            if i == len(s):
                return 1

            if s[i] == "0":
                return 0

            if i in mem:
                return mem[i]

            # Take one digit
            ways = dp(i + 1)

            # Take two digits if valid
            if i + 1 < len(s) and 10 <= int(s[i:i+2]) <= 26:
                ways += dp(i + 2)

            mem[i] = ways
            return ways

        return dp(0)
```

### How it works

The recursion starts at:

```text
dp(0)
```

and moves forward:

```text
dp(0)
 ↓
dp(1)
 ↓
dp(2)
 ↓
...
dp(n)
```

Memoization prevents the same state from being calculated multiple times.

### Complexity

```text
Time:  O(n)
Space: O(n)
```

---

# 🔽 Bottom-Up DP — Tabulation

The Top-Down recurrence is:

```text
dp(i) depends on dp(i+1) and dp(i+2)
```

Therefore, in Bottom-Up DP, we must calculate from **right to left**.

```text
n → n-1 → n-2 → ... → 0
```

### Python

```python
class Solution:
    def numDecodings(self, s: str) -> int:
        n = len(s)

        dp = [0] * (n + 1)
        dp[n] = 1

        for i in range(n - 1, -1, -1):
            if s[i] == "0":
                dp[i] = 0
                continue

            # Take one digit
            dp[i] = dp[i + 1]

            # Take two digits if valid
            if i + 1 < n and 10 <= int(s[i:i+2]) <= 26:
                dp[i] += dp[i + 2]

        return dp[0]
```

---

# 🔍 Bottom-Up Example — `"226"`

Initial:

```text
dp = [0, 0, 0, 1]
                 ↑
               dp[3]
```

### `i = 2`

```text
"6"
```

Take `6`:

```text
dp[2] = dp[3] = 1
```

```text
dp = [0, 0, 1, 1]
```

### `i = 1`

Remaining:

```text
"26"
```

Both `2` and `26` are valid:

```text
dp[1] = dp[2] + dp[3]
      = 1 + 1
      = 2
```

```text
dp = [0, 2, 1, 1]
```

### `i = 0`

Remaining:

```text
"226"
```

Both `2` and `22` are valid:

```text
dp[0] = dp[1] + dp[2]
      = 2 + 1
      = 3
```

Final:

```text
dp = [3, 2, 1, 1]
```

Return:

```text
3
```

---

# 🔄 Top-Down vs Bottom-Up

|           | Top-Down                       | Bottom-Up        |
| --------- | ------------------------------ | ---------------- |
| Approach  | Recursion                      | Iteration        |
| Storage   | Dictionary                     | Array            |
| Direction | Left → Right through recursion | Right → Left     |
| Time      | `O(n)`                         | `O(n)`           |
| Space     | `O(n)`                         | `O(n)`           |
| Main idea | Solve needed states            | Build all states |

The important conversion rule here is:

```text
dp(i) → depends on dp(i+1), dp(i+2)
```

Therefore:

```text
Bottom-Up → calculate from right to left
```

---

# ⚠️ Important Edge Cases

### `"0"`

```text
Output: 0
```

Because `0` cannot be decoded alone.

### `"06"`

```text
Output: 0
```

Because `"06"` is not between `10` and `26`.

### `"10"`

```text
Output: 1
```

Only:

```text
10 → J
```

### `"20"`

```text
Output: 1
```

Only:

```text
20 → T
```

### `"27"`

```text
Output: 1
```

Only:

```text
2 | 7
```

because `27` is greater than `26`.

---

# 🎯 DP Pattern Learned

This problem introduces an important DP pattern:

## **Multiple Choices at Every Position**

At index `i`:

```text
             dp(i)
             /   \
            /     \
       1 digit   2 digits
          ↓         ↓
       dp(i+1)   dp(i+2)
```

The second choice exists only when it is valid.

Since the problem asks for the **number of ways**, valid choices are **added**:

```text
dp(i) = choice1 + choice2
```

Compare this with the DP patterns you've learned:

```text
Climbing Stairs
→ count ways
→ add choices

Min Cost Climbing Stairs
→ minimize cost
→ min choices

House Robber
→ maximize money
→ max choices

Decode Ways
→ count decodings
→ add valid choices
```

---

# 🧠 Problem-Solving Pattern

When you see a similar DP problem, ask:

```text
1. What is my current position/state?
        ↓
2. What choices can I make?
        ↓
3. Where does each choice take me?
        ↓
4. Are the choices valid?
        ↓
5. Am I counting, minimizing, or maximizing?
        ↓
6. Do the same states repeat?
        ↓
7. Use DP/Memoization
```

For Decode Ways:

```text
Current state
     ↓
index i
     ↓
Choices
 ┌───────────┐
 │           │
1 digit   2 digits
 │           │
 ↓           ↓
i + 1      i + 2
 │           │
 └─────┬─────┘
       ↓
      ADD
```

---

# 📊 Complexity

For both Top-Down and Bottom-Up:

```text
Time:  O(n)
Space: O(n)
```

where `n = len(s)`.

---

# 📚 Concepts Practiced

* [x] Dynamic Programming
* [x] 1D DP
* [x] Counting DP
* [x] Recursion
* [x] Memoization
* [x] Bottom-Up DP
* [x] State Definition
* [x] State Transition
* [x] Base Cases
* [x] Handling Invalid States
* [x] Top-Down → Bottom-Up Conversion
* [x] Complexity Analysis

---

# 🔗 Related Problems

Good problems to practice after this:

1. **70. Climbing Stairs** — Basic counting DP
2. **91. Decode Ways** — Multiple choices + validity
3. **746. Min Cost Climbing Stairs** — Minimum DP
4. **198. House Robber** — Take/Skip DP
5. **740. Delete and Earn** — Problem transformation + Take/Skip DP

---

# 👨‍💻 Author

**Nijoy P Jose**

Part of my **Data Structures & Algorithms placement preparation repository**.
