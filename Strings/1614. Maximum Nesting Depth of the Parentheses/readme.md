# 1614. Maximum Nesting Depth of the Parentheses

**Difficulty:** Easy
**Topic:** String
**Pattern:** Counter / Balance Tracking

---

## Problem

Given a valid parentheses string `s`, return the **maximum nesting depth** of the parentheses.

The nesting depth is the maximum number of open parentheses `(` that are active at the same time.

### Example

```text
Input:  (1+(2*3)+((8)/4))+1
Output: 3
```

Explanation:

```text
(1+(2*3)+((8)/4))+1
      ↑       ↑
Maximum simultaneous '(' = 3
```

---

## Quick Revision

The key idea is to maintain a counter representing the **current nesting depth**.

* `(` → increase depth by `1`
* `)` → decrease depth by `1`
* After every character, update the maximum depth.

```text
current depth → p
maximum depth → maxP
```

---

## Recognition Trigger

When you see:

* Parentheses
* Nested structure
* Need to find maximum depth
* Need to track how many `(` are currently open

Think:

> **"Use a counter to track the current balance and keep the maximum."**

---

## Deriving the Solution

### Step 1: Track current depth

Start with:

```text
depth = 0
```

When we encounter:

```text
(
```

we enter one more level:

```text
depth += 1
```

When we encounter:

```text
)
```

we leave one level:

```text
depth -= 1
```

### Step 2: Track maximum depth

Whenever the current depth increases, compare it with the maximum seen so far.

```text
maxDepth = max(maxDepth, depth)
```

### Example

For:

```text
(1+(2*3)+((8)/4))+1
```

The depth changes approximately like:

```text
(       → 1
(       → 2
)       → 1
(       → 2
(       → 3
)       → 2
)       → 1
)       → 0
```

The maximum value reached is:

```text
3
```

---

## Algorithm

1. Initialize `depth = 0`.
2. Initialize `maxDepth = 0`.
3. Traverse every character in the string.
4. If the character is `(`, increment `depth`.
5. If the character is `)`, decrement `depth`.
6. Update `maxDepth`.
7. Return `maxDepth`.

---

## Python Solution

```python
class Solution:
    def maxDepth(self, s: str) -> int:
        maxP = 0
        p = 0

        for i in s:
            if i == "(":
                p += 1

            if i == ")":
                p -= 1

            maxP = max(maxP, p)

        return maxP
```

---

## Why This Works

The variable `p` always represents the number of currently open parentheses.

Because the string is guaranteed to be valid:

```text
depth never becomes invalid
```

Therefore, the largest value reached by `p` is exactly the maximum nesting depth.

---

## Pattern Connection

This problem demonstrates the:

### Counter / Balance Tracking Pattern

The same idea can be used whenever we need to track a changing quantity while scanning a sequence.

Common examples:

* Valid Parentheses
* Parentheses balance
* Counting active intervals
* Tracking open/close operations
* Bracket matching
* Prefix balance problems

The important idea is:

```text
Opening event  → +1
Closing event  → -1
Track maximum/minimum when required
```

---

## Common Mistakes

### 1. Counting all opening parentheses

Wrong idea:

```text
number of '('
```

This does **not** give the nesting depth.

For:

```text
()((()))
```

There are 5 opening parentheses, but the maximum depth is only `3`.

### 2. Using a stack unnecessarily

A stack can solve many parentheses problems, but here we only need the **current count**.

So:

```text
Counter → enough
Stack   → unnecessary
```

### 3. Updating maximum only after `)`

The maximum depth is reached when encountering `(`, so the maximum should be tracked during the scan.

---

## Complexity

Let `n` be the length of the string.

**Time Complexity:**

```text
O(n)
```

Every character is visited once.

**Space Complexity:**

```text
O(1)
```

Only a few variables are used.

---

## Placement Takeaway

This is a simple but important **string traversal + counter** problem.

For placement tests, recognize immediately:

```text
Need current nesting level
        ↓
Use counter
        ↓
( → +1
) → -1
        ↓
Track maximum
```

Don't overcomplicate it with a stack when only the depth is required.

---

## 30-Second Cheat Sheet

```text
Pattern:
Counter / Balance Tracking

Initialize:
depth = 0
maxDepth = 0

For each character:
    '(' → depth += 1
    ')' → depth -= 1

    maxDepth = max(maxDepth, depth)

Return maxDepth

Time:  O(n)
Space: O(1)
```
