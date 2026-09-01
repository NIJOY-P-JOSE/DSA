# Supermarket – Count Baskets Containing Mth Item

> **Pattern:** Dynamic Programming · Unbounded Knapsack · Coin Change
> **Difficulty:** Medium–Hard
> **Platform:** SOTI Coding Assessment

---

## ⚡ Quick Revision

| Concept          | Remember                                                         |
| ---------------- | ---------------------------------------------------------------- |
| Goal             | Count baskets having total price `B` and containing the Mth item |
| Item reuse       | Allowed                                                          |
| Order            | Does not matter                                                  |
| DP type          | Coin Change – Count Combinations                                 |
| Key trick        | Use the Mth item once, then solve the remaining target           |
| Remaining target | `B - A[M-1]`                                                     |
| Impossible       | If Mth item's price > `B`, return `0`                            |
| Modulo           | `1,000,000,007`                                                  |
| Time             | `O(N × B)`                                                       |
| Space            | `O(B)`                                                           |

---

# 🧠 Problem in Simple Words

We have:

```text
A = prices of N items
B = required total basket price
M = item we want to check
```

Customers can buy an item **multiple times**.

The order of items does not matter.

We need to find:

> **How many different baskets with total price `B` contain the Mth item at least once?**

---

# 💡 Main Trick

Suppose:

```text
A = [1, 2, 3]
B = 6
M = 3
```

The Mth item is:

```text
A[M-1] = 3
```

Because `M` starts from 1.

We know that the basket **must contain 3**.

So use one `3` first:

```text
6 - 3 = 3
```

Now the problem becomes:

> How many ways can we make `3` using `[1,2,3]`?

The possibilities are:

```text
1 + 1 + 1
1 + 2
3
```

So the answer is:

```text
3
```

These correspond to:

```text
1 + 1 + 1 + 3
1 + 2 + 3
3 + 3
```

---

# 🔑 Key Idea

```text
Required Mth item
        ↓
Use it once
        ↓
remaining = B - A[M-1]
        ↓
Count ways to make remaining
        ↓
Coin Change DP
```

Why can we use the Mth item exactly once initially?

Because the remaining amount can itself contain the Mth item again.

For example:

```text
B = 6
Mth item = 3
```

Using it once gives:

```text
remaining = 3
```

The remaining `3` can be made using another `3`.

Therefore:

```text
3 + 3
```

is correctly counted.

---

# 🧩 DP Idea

Create:

```python
dp = [0] * (target + 1)
```

where:

```text
dp[i] = number of different combinations that make amount i
```

Base case:

```python
dp[0] = 1
```

There is one way to make `0`:

```text
choose nothing
```

---

# 🔄 DP Transition

For every item price:

```python
for coin in A:
```

try making every amount from that price onward:

```python
for i in range(coin, target + 1):
```

Then:

```python
dp[i] += dp[i - coin]
```

Meaning:

> The number of ways to make `i` increases by the number of ways to make `i - coin`.

---

# ⚠️ Why `coin` Comes First

We use:

```python
for coin in A:
    for i in range(coin, target + 1):
```

rather than:

```python
for i in range(target + 1):
    for coin in A:
```

because **order does not matter**.

For example:

```text
1 + 2
2 + 1
```

must be counted as **one basket**, not two.

Putting the coin loop outside prevents counting different orders separately.

---

# 💻 Solution

```python
def SUPERMARKET(A, N, B, M):

    price = A[M-1]

    if price > B:
        return 0

    target = B-price

    dp = [0] * (target+1)
    dp[0] = 1

    for coin in A:
        for i in range(coin, target+1):
            dp[i] = (dp[i] + dp[i-coin]) % 1000000007

    return dp[target]
```

---

# 🧪 Dry Run

### Input

```text
A = [1, 2, 3]
B = 6
M = 3
```

### Step 1

Get the Mth item's price:

```python
price = A[M-1]
```

```text
price = A[2] = 3
```

---

### Step 2

Use the required item once:

```text
target = B - price
       = 6 - 3
       = 3
```

---

### Step 3

Initialize:

```text
dp = [1, 0, 0, 0]
```

---

### Step 4 — Coin `1`

Ways to make `3`:

```text
1 + 1 + 1
```

---

### Step 5 — Coin `2`

New combination:

```text
1 + 2
```

---

### Step 6 — Coin `3`

New combination:

```text
3
```

Therefore:

```text
dp[3] = 3
```

Final answer:

```text
3
```

---

# 🚨 Common Mistakes

### 1. Using `min()`

Wrong:

```python
dp[i] = min(dp[i], dp[i-coin] + 1)
```

That solves:

> Minimum number of coins.

This problem asks:

> Number of different combinations.

Correct:

```python
dp[i] += dp[i-coin]
```

---

### 2. Forgetting `M-1`

The problem says:

> Mth item, where M starts from 1.

Python uses 0-based indexing.

Therefore:

```python
price = A[M-1]
```

---

### 3. Using the wrong loop order

For combinations:

```python
for coin in A:
    for i in range(coin, target+1):
```

---

### 4. Forgetting that items can be reused

The inner loop goes **forward**:

```python
range(coin, target+1)
```

This allows the same item to be used multiple times.

---

# 🎯 SOTI Pattern Recognition

When you see:

```text
Items/coins
+
Can be reused
+
Order doesn't matter
+
Count number of ways
+
Exact target sum
```

Think:

> **Coin Change II / Unbounded Knapsack – Count Combinations**

If the question additionally says:

```text
Mth item must be present
```

then:

```text
B - A[M-1]
```

and run Coin Change DP on the remaining amount.

---

# ⚡ 30-Second Revision

```text
M is 1-indexed
      ↓
price = A[M-1]
      ↓
Mth item must be present
      ↓
Use it once
      ↓
target = B - price
      ↓
dp[0] = 1
      ↓
for coin:
    for amount from coin → target:
        dp[amount] += dp[amount-coin]
      ↓
return dp[target]
```

### ⭐ Key takeaway

> **When a particular item must appear in an unlimited-supply, order-independent combination problem, use that item once and count Coin Change combinations for the remaining amount.**
