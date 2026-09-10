# 🚀 Alternating Grid Path with Shift Momentum

> **Note:** This problem statement is reconstructed from a problem encountered during a campus recruitment coding assessment. The exact wording may differ from the original question.

---

## 📌 Problem Statement

You are given an `N × M` grid of integers.

Starting from the **top-left cell** `(0, 0)`, reach the **bottom-right cell** `(N - 1, M - 1)`.

Find the **maximum number of cells that can be visited** while following all the given conditions.

If it is not possible to reach the destination, return `-1`.

---

## 🧭 Movement Rules

From any cell, you can move only:

- ➡️ Right
- ⬇️ Down

The direction of consecutive moves must alternate.

Valid movement patterns:

`Right → Down → Right → Down → ...`

or

`Down → Right → Down → Right → ...`

---

## 🦘 Jump Length

A move can have a jump length `L`.

If the current position is `(r, c)`:

### Move Right

```text
(r, c) → (r, c + L)
```

### Move Down

```text
(r, c) → (r + L, c)
```

The jump destination must remain inside the grid.

---

## 📈 Increasing Value Condition

For every move:

```text
grid[next_row][next_col] > grid[current_row][current_col]
```

The destination cell must have a value **strictly greater** than the current cell.

---

## ⚡ Shift Momentum

The initial momentum is:

```text
0
```

The momentum changes depending on the jump length `L`.

| Jump Length | Momentum Increase |
|-------------|------------------|
| `L = 1` | `+0` |
| `L = 2` | `+1` |
| `L >= 3` | `+2` |

Momentum is maintained modulo `3`.

```text
new_momentum = (current_momentum + shift) % 3
```

---

## 🎯 Destination Condition

When the destination `(N - 1, M - 1)` is reached:

```text
final_momentum == grid[N - 1][M - 1] % 3
```

Only paths satisfying this condition are valid.

---

## 🏆 Objective

Find the **valid path that visits the maximum number of cells**.

A larger jump skips intermediate cells.

Therefore, smaller jumps can potentially visit more cells, but larger jumps may be necessary to satisfy:

- Alternating movement direction
- Strictly increasing cell values
- Final momentum condition

---

## 📝 Example

### Input

```text
1 2 3
4 5 6
7 8 9
```

One valid path is:

```text
1 → 2 → 5 → 6 → 9
```

Movement directions:

```text
Right → Down → Right → Down
```

All jumps have:

```text
L = 1
```

Therefore, the momentum remains:

```text
0
```

The destination value is:

```text
9
```

Since:

```text
9 % 3 = 0
```

the final momentum condition is satisfied.

### Output

```text
5
```

---

## 💡 Approach

This problem can be solved using **Dynamic Programming with Memoization**.

The answer from a position depends on:

- Current row
- Current column
- Direction of the next move
- Current momentum

Therefore, the DP state can be represented as:

```python
solve(row, col, direction, momentum)
```

For every state:

1. Try every possible jump length `L`.
2. Check whether the jump remains inside the grid.
3. Check whether the destination value is greater than the current value.
4. Update the momentum based on `L`.
5. Alternate the movement direction.
6. Find the maximum number of cells that can be visited.
7. Store already calculated states using memoization.

---

## 🧠 DP State

| State | Meaning |
|-------|---------|
| `row` | Current row |
| `col` | Current column |
| `direction` | Required direction of the next move |
| `momentum` | Current momentum value |

---

## 🛠️ Concepts Used

- Dynamic Programming
- Memoization
- Recursion
- Grid Traversal
- State-Based DP

---

⭐ This problem demonstrates how additional conditions such as **movement direction** and **momentum** can become part of a Dynamic Programming state.
