# 322. Coin Change

**Pattern:** Dynamic Programming — 1D DP / Unbounded Knapsack
**Difficulty:** Medium
**Platform:** LeetCode
**Problem:** [322. Coin Change](https://leetcode.com/problems/coin-change/)

---

## 📌 Problem Statement

You are given:

* An integer array `coins` containing different coin denominations.
* An integer `amount` representing the target amount.

Return the **fewest number of coins** needed to make the given `amount`.

You can use each type of coin **an unlimited number of times**.

If the amount cannot be formed using the given coins, return:

```text
-1
```

---

## Example 1

```text
Input:
coins = [1,2,5]
amount = 11

Output:
3
```

Explanation:

```text
11 = 5 + 5 + 1
```

Therefore, the minimum number of coins is:

```text
3
```

---

## Example 2

```text
Input:
coins = [2]
amount = 3

Output:
-1
```

There is no combination of `2`-value coins that can make `3`.

---

## Example 3

```text
Input:
coins = [1]
amount = 0

Output:
0
```

No coins are needed to make amount `0`.

---

# 💡 DP Idea

The key question is:

> **If I know the minimum coins needed for smaller amounts, can I use them to find the minimum coins for the current amount?**

Yes.

Suppose:

```text
coins = [1,2,5]
amount = 11
```

If we choose coin `5` as the last coin:

```text
11 - 5 = 6
```

So:

```text
minimum coins for 11
=
minimum coins for 6 + 1
```

Similarly, if we choose coin `2`:

```text
11 - 2 = 9
```

Therefore:

```text
minimum coins for 11
=
minimum coins for 9 + 1
```

We try every possible coin and take the minimum.

---

# 🧠 DP State

Define:

```text
dp[i]
```

as:

> The minimum number of coins needed to make amount `i`.

For example:

```text
dp[0] = 0
```

because we need zero coins to make amount `0`.

---

# 🔄 Recurrence

For every amount `i`, try every coin `c`.

If:

```text
c <= i
```

then we can use that coin.

The remaining amount is:

```text
i - c
```

Therefore:

```text
dp[i] = dp[i-c] + 1
```

Since we want the **minimum**:

```text
dp[i] = min(dp[i], dp[i-c] + 1)
```

General recurrence:

```text
dp[i] = min(
    dp[i-c] + 1
)
```

for every coin `c` where `c <= i`.

---

# 🧱 Initialization

Initially, we don't know how many coins are required for any positive amount.

So initialize them with infinity:

```python
dp = [float('inf')] * (amount + 1)
```

Meaning:

```text
inf = currently impossible / no solution found yet
```

Then:

```python
dp[0] = 0
```

because amount `0` requires zero coins.

---

# 🔍 Dry Run

For:

```text
coins = [1,2,5]
amount = 5
```

Initially:

```text
amount:  0   1   2   3   4   5
dp:      0  inf inf inf inf inf
```

### Amount 1

Use coin `1`:

```text
dp[1] = dp[0] + 1
      = 1
```

```text
0  1  inf inf inf inf
```

### Amount 2

Coin `1`:

```text
dp[2] = dp[1] + 1
      = 2
```

Coin `2`:

```text
dp[2] = dp[0] + 1
      = 1
```

Take minimum:

```text
dp[2] = 1
```

### Amount 5

Try:

```text
coin 1 → dp[4] + 1
coin 2 → dp[3] + 1
coin 5 → dp[0] + 1
```

Therefore:

```text
dp[5] = 1
```

because one `5` coin is enough.

---

# 💻 Your Solution

Your submitted solution:

```python
class Solution:
    def coinChange(self, coins: List[int], amount: int) -> int:

        dp = [float('inf')] * (amount + 1)
        dp[0] = 0

        for i in range(len(dp)):
            for c in coins:
                if c <= i:
                    dp[i] = min(dp[i], dp[i-c] + 1)

        if dp[amount] == float('inf'):
            return -1

        return dp[amount]
```

This solution is **correct** and was successfully submitted.

---

# 🔄 Why Forward Iteration?

Notice that your loop goes:

```python
for i in range(len(dp)):
```

which means:

```text
0 → 1 → 2 → 3 → ... → amount
```

This works because **coins can be used unlimited times**.

For example, with:

```text
coin = 5
```

we can use:

```text
5
10
15
20
...
```

So when calculating a larger amount, it's completely valid to use a previously calculated state that itself used the same coin.

This is called **Unbounded Knapsack**.

---

# 🆚 416 vs 322

This is an important distinction from the previous problem you solved.

## 416. Partition Equal Subset Sum

Each number can be used **only once**.

```text
0/1 Knapsack
```

Therefore, the sum/capacity is normally processed:

```text
right → left
```

---

## 322. Coin Change

Each coin can be used **unlimited times**.

```text
Unbounded Knapsack
```

Therefore, processing amounts:

```text
left → right
```

is valid.

### Remember

```text
0/1 Knapsack
→ use item once
→ backward

Unbounded Knapsack
→ use item unlimited times
→ forward
```

This distinction is very useful in placement coding rounds.

---

# 🎯 How to Derive the Logic in a Placement

If you see:

> Find the minimum number of coins required to make an amount.

Think:

```text
What changes?
       ↓
Amount
       ↓
dp[amount]
       ↓
What does dp[i] mean?
       ↓
Minimum coins to make i
       ↓
What is my choice?
       ↓
Choose a coin
       ↓
What remains?
       ↓
i - coin
       ↓
I used one coin
       ↓
dp[i - coin] + 1
       ↓
Need minimum
       ↓
min(...)
```

So the recurrence becomes:

```text
dp[i] = min(dp[i-coin] + 1)
```

That is the entire core idea.

---

# ⚠️ Impossible Amounts

For example:

```text
coins = [2]
amount = 3
```

There is no way to make `3`.

The DP value remains:

```text
dp[3] = inf
```

So:

```python
if dp[amount] == float('inf'):
    return -1
```

---

# 📊 Complexity

Let:

```text
n = len(coins)
A = amount
```

For every amount, we try every coin.

### Time

```text
O(A × n)
```

### Space

```text
O(A)
```

So:

```text
Time:  O(amount × len(coins))
Space: O(amount)
```

This is a standard and efficient solution for the given constraints.

---

# 🧠 DP Pattern Learned

This problem teaches:

* [x] 1D DP
* [x] Minimum DP
* [x] Unbounded Knapsack
* [x] Unlimited item reuse
* [x] State based on target amount
* [x] Impossible-state handling
* [x] Forward DP iteration

The key recurrence is:

```text
dp[i] = min(dp[i], dp[i-coin] + 1)
```

---

# 🔗 Connection to Previous DP Problems

Your progression is now:

```text
House Robber
     ↓
Take / Skip
     ↓
Partition Equal Subset Sum
     ↓
0/1 Knapsack
     ↓
Coin Change
     ↓
Unbounded Knapsack
```

The important evolution is:

```text
416:
Each number → once

322:
Each coin → unlimited times
```

And the objective changes from:

```text
416 → Is target possible?
322 → What is the minimum number of coins?
```

---

# 📌 Key Takeaways

### State

```text
dp[i] = minimum coins needed to make amount i
```

### Base Case

```text
dp[0] = 0
```

### Choice

```text
Choose a coin c
```

### Remaining Problem

```text
i - c
```

### Recurrence

```text
dp[i] = min(dp[i], dp[i-c] + 1)
```

### Unlimited coins

```text
Process amounts forward
```

### Impossible

```text
dp[amount] == inf → -1
```

The most important mental pattern is:

```text
Current target
      ↓
Choose an item/coin
      ↓
Remaining target
      ↓
Solve smaller target
      ↓
Add cost of current choice
      ↓
Take MIN/MAX
```

This pattern appears in many placement-level DP problems.
