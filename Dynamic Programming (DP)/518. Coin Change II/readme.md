# 518. Coin Change II

> **Pattern:** 1D DP · Unbounded Knapsack · Counting Combinations
> **Difficulty:** Medium
> **Platform:** LeetCode

[Problem — LeetCode 518: Coin Change II](https://leetcode.com/problems/coin-change-ii/?utm_source=chatgpt.com)

---

## ⚡ Quick Revision

| Concept     | Remember                          |
| ----------- | --------------------------------- |
| Goal        | **Number of combinations**        |
| Coins       | **Unlimited use**                 |
| DP type     | **1D DP**                         |
| Pattern     | **Unbounded Knapsack**            |
| State       | `dp[i]` = ways to make amount `i` |
| Base case   | `dp[0] = 1`                       |
| Transition  | `dp[i] += dp[i-c]`                |
| Coin loop   | **OUTER**                         |
| Amount loop | **INNER, forward**                |
| Time        | `O(coins × amount)`               |
| Space       | `O(amount)`                       |

---

# 🧠 The Core Idea

The problem asks:

> **How many combinations of coins can make `amount`?**

Example:

```text
coins = [1, 2, 5]
amount = 5
```

Valid combinations:

```text
5
2 + 2 + 1
2 + 1 + 1 + 1
1 + 1 + 1 + 1 + 1
```

Answer:

```text
4
```

The key is that:

```text
1 + 2
```

and:

```text
2 + 1
```

are the **same combination**.

So we must avoid counting different orders separately.

---

# 🎯 DP State

```python
dp[i]
```

means:

> **Number of combinations that can make amount `i` using the coins processed so far.**

For example:

```text
dp[5] = 4
```

means there are 4 combinations that make `5`.

---

# 🧱 Base Case

```python
dp[0] = 1
```

### Why `1`?

There is exactly **one way to make amount `0`**:

```text
choose nothing
```

This is extremely important.

If you use:

```python
dp[0] = 0
```

the entire DP will remain incorrect because there would be no starting combination from which to build other amounts.

### Remember

```text
Amount 0
   ↓
Choose nothing
   ↓
1 way
```

---

# 🔄 Recurrence

Suppose:

```text
coin = 2
amount = 5
```

If we use a `2`, we need to make:

```text
5 - 2 = 3
```

So every combination that makes `3` can become a combination that makes `5` by adding `2`.

Therefore:

```python
dp[5] += dp[3]
```

Generally:

```python
dp[i] += dp[i-c]
```

where `c` is the current coin.

### ⭐ Remember

```text
CURRENT AMOUNT
      ↓
USE COIN c
      ↓
REMAINING AMOUNT = i - c
      ↓
ways = dp[i-c]
      ↓
ADD those ways to dp[i]
```

---

# 🔥 The Most Important Part: Loop Order

For this problem, use:

```python
for c in coins:
    for i in range(c, amount + 1):
```

### Why?

Because we want **combinations**, not permutations.

For:

```text
coins = [1, 2]
amount = 3
```

We want:

```text
1 + 1 + 1
1 + 2
```

Answer:

```text
2
```

We do **NOT** want:

```text
1 + 2
2 + 1
```

to count separately.

---

# 🧠 Why Coin Must Be the Outer Loop

Think of the process as establishing an order for the coin choices.

```text
Process coin 1
    ↓
Process coin 2
    ↓
Process coin 5
```

Once we have processed a coin, we don't later create a new ordering by going back to an earlier coin.

Therefore:

```python
for c in coins:
    for i in range(c, amount + 1):
        dp[i] += dp[i-c]
```

produces **combinations**.

---

# ⚠️ Don't Confuse With 322

These two problems look almost identical.

## 322 — Coin Change

Question:

> **Minimum number of coins?**

```python
dp[i] = min(dp[i], dp[i-c] + 1)
```

---

## 518 — Coin Change II

Question:

> **Number of combinations?**

```python
dp[i] += dp[i-c]
```

### Quick Comparison

|                 | 322                | 518                    |
| --------------- | ------------------ | ---------------------- |
| Goal            | Minimum coins      | Number of combinations |
| DP              | `min()`            | `+`                    |
| Base            | `dp[0] = 0`        | `dp[0] = 1`            |
| Coins reusable? | Yes                | Yes                    |
| Pattern         | Unbounded Knapsack | Unbounded Knapsack     |

---

# 🔥 416 vs 518 — VERY IMPORTANT

You also solved **416. Partition Equal Subset Sum**.

That's a different knapsack type.

### 416 — 0/1 Knapsack

Each number can be used **once**.

```text
Item → once
```

Typically:

```python
for num in nums:
    for s in range(target, num - 1, -1):
```

**Backward.**

---

### 518 — Unbounded Knapsack

Each coin can be used **unlimited times**.

```text
Coin → unlimited
```

For combinations:

```python
for c in coins:
    for i in range(c, amount + 1):
```

**Forward.**

### 🚨 Quick Memory Rule

```text
┌──────────────────────────────┐
│ 0/1 Knapsack                 │
│ Item used once               │
│ → iterate capacity BACKWARD  │
└──────────────────────────────┘

┌──────────────────────────────┐
│ Unbounded Knapsack            │
│ Item used unlimited times    │
│ → iterate capacity FORWARD   │
└──────────────────────────────┘
```

---

# 🧪 Dry Run

Consider:

```text
coins = [1, 2, 5]
amount = 5
```

Initial:

```text
dp = [1, 0, 0, 0, 0, 0]
```

Meaning:

```text
amount:  0  1  2  3  4  5
ways:    1  0  0  0  0  0
```

---

## 🪙 Process coin `1`

```text
dp = [1, 1, 1, 1, 1, 1]
```

There is one way to make every amount using only `1`s.

---

## 🪙 Process coin `2`

Now new combinations involving `2` are added.

```text
dp = [1, 1, 2, 2, 3, 3]
```

For example:

```text
dp[5] = 3
```

representing:

```text
1+1+1+1+1
2+1+1+1
2+2+1
```

---

## 🪙 Process coin `5`

```text
dp = [1, 1, 2, 2, 3, 4]
```

Now the additional combination:

```text
5
```

is added.

Final:

```text
dp[5] = 4
```

---

# 👀 Visualize the DP

```text
             amount
        0  1  2  3  4  5
        ─────────────────
start   1  0  0  0  0  0
coin 1  1  1  1  1  1  1
coin 2  1  1  2  2  3  3
coin 5  1  1  2  2  3  4
                           ↑
                         answer
```

---

# 💻 Final Code

```python
class Solution:
    def change(self, amount: int, coins: List[int]) -> int:

        dp = [0] * (amount + 1)
        dp[0] = 1

        for c in coins:
            for i in range(c, amount + 1):
                dp[i] += dp[i - c]

        return dp[amount]
```

---

# 🔍 Code Breakdown

### Create DP

```python
dp = [0] * (amount + 1)
```

We need states:

```text
0 → amount
```

---

### Base case

```python
dp[0] = 1
```

One way to make zero.

---

### Process each coin

```python
for c in coins:
```

This is what makes us count **combinations**.

---

### Process amounts forward

```python
for i in range(c, amount + 1):
```

Forward because coins can be reused.

---

### Add new combinations

```python
dp[i] += dp[i - c]
```

Every way to make `i-c` creates a way to make `i` by adding coin `c`.

---

### Return answer

```python
return dp[amount]
```

---

# 🧩 How to Derive This in a Placement

If you see this problem for the first time, don't memorize the code.

Use this thought process:

```text
What is the problem asking?
             ↓
Number of combinations
             ↓
What changes?
             ↓
Target amount
             ↓
Define dp[i]
             ↓
Number of ways to make amount i
             ↓
What is my choice?
             ↓
Choose a coin
             ↓
What smaller problem remains?
             ↓
i - coin
             ↓
How do ways combine?
             ↓
ADD
             ↓
dp[i] += dp[i-coin]
             ↓
Can coin be reused?
             ↓
YES
             ↓
Unbounded Knapsack
             ↓
Process amounts FORWARD
```

---

# 🧠 The 5 Questions to Ask Yourself

When you see a new DP problem:

```text
1️⃣ What is changing?

2️⃣ What does dp[state] mean?

3️⃣ What choice am I making?

4️⃣ What smaller state remains after that choice?

5️⃣ Am I counting, minimizing, maximizing, or checking possibility?
```

For 518:

```text
Changing:
→ amount

State:
→ dp[i] = number of combinations for i

Choice:
→ choose coin c

Remaining:
→ i - c

Objective:
→ COUNT

Recurrence:
→ dp[i] += dp[i-c]
```

Then recognize:

```text
coin reusable
      ↓
Unbounded Knapsack
      ↓
coin outer loop
amount forward
```

---

# ⚠️ Common Mistakes

### ❌ Mistake 1 — `dp[0] = 0`

Wrong:

```python
dp[0] = 0
```

Correct:

```python
dp[0] = 1
```

---

### ❌ Mistake 2 — Overwriting

Wrong:

```python
dp[i] = dp[i-c]
```

Correct:

```python
dp[i] += dp[i-c]
```

Because we're **adding new combinations**.

---

### ❌ Mistake 3 — Wrong loop order

Risky/wrong for combinations:

```python
for i in range(amount + 1):
    for c in coins:
```

Correct:

```python
for c in coins:
    for i in range(c, amount + 1):
```

---

### ❌ Mistake 4 — Going backward

For this unbounded combination-counting problem:

```python
for i in range(amount, c - 1, -1):
```

is not the pattern you want.

Use:

```python
for i in range(c, amount + 1):
```

---

# 📝 Quick Cheat Sheet

```text
╔══════════════════════════════════════╗
║       COIN CHANGE II — 518           ║
╠══════════════════════════════════════╣
║ Goal: COUNT combinations              ║
║                                      ║
║ dp[i] = ways to make amount i        ║
║                                      ║
║ dp[0] = 1                            ║
║                                      ║
║ dp[i] += dp[i-c]                     ║
║                                      ║
║ Coins reusable → UNBOUNDED           ║
║                                      ║
║ Coin outer loop                      ║
║ Amount inner loop                    ║
║ Amount → FORWARD                     ║
║                                      ║
║ Time:  O(coins × amount)             ║
║ Space: O(amount)                     ║
╚══════════════════════════════════════╝
```

---

# 🚀 Pattern Summary

You have now learned three closely related problems:

```text
416 Partition Equal Subset Sum
          │
          └── 0/1 Knapsack
              Item used ONCE
              Check possibility
              ↓
              backward


322 Coin Change
          │
          └── Unbounded Knapsack
              Coin used UNLIMITED
              Find MINIMUM
              ↓
              forward


518 Coin Change II
          │
          └── Unbounded Knapsack
              Coin used UNLIMITED
              COUNT combinations
              ↓
              forward
```

### ⭐ The one line to remember

> **For Coin Change II: process each coin first, then process amounts forward, because each coin can be used unlimited times and different orders of the same coins must count as one combination.**

This is the key detail that makes **518** different from many other DP problems.
