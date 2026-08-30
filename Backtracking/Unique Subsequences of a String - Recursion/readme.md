# Unique Subsequences of a String — Recursion

> **Pattern:** Recursion / Backtracking · Subsequences · Start Index · Duplicate Handling
> **Source:** Placement DSA Training Problem

---

## ⚡ Quick Revision

| Concept            | Remember                                   |
| ------------------ | ------------------------------------------ |
| Goal               | Generate all non-empty unique subsequences |
| Pattern            | Recursion / Backtracking                   |
| Input              | String                                     |
| Order              | Must be preserved                          |
| State              | `current`, `i`                             |
| Choice             | Choose any character from index `i` onward |
| Move               | `j + 1`                                    |
| Duplicate Handling | `set()`                                    |
| Output Order       | Lexicographic                              |
| Empty Subsequence  | Not included                               |

### 🔑 Core Pattern

```text
Start at index i
      ↓
Choose a character from i onward
      ↓
Add it to current
      ↓
Recurse from j + 1
      ↓
Store every non-empty subsequence
      ↓
Remove duplicates
      ↓
Sort lexicographically
```

---

# 🧠 Problem Statement

Given a string `ipstr` of length `n`, write a **recursive function** to find and return all the **non-empty, unique subsequences** of the string as a list of strings.

The returned subsequences must be in **lexicographic order**.

The function should not take input from the user.

---

## Example 1

```text
Input:
ABC

Output:
A AB ABC AC B BC C
```

---

## Example 2

```text
Input:
pqpq

Output:
p pp ppq pq pqp pqpq pqq q qp qpq qq
```

---

# 🧠 What Is a Subsequence?

A subsequence is formed by selecting characters while maintaining their **original relative order**.

Characters do not have to be adjacent.

For:

```text
ABC
```

some subsequences are:

```text
A
B
C
AB
AC
BC
ABC
```

But:

```text
BA
CA
CB
```

are not subsequences because they change the original order.

---

# 🔥 How to Recognize This Problem

### Recognition Trigger

> **"Generate all subsequences" → Think recursion/backtracking + move forward through the string.**

The important property is:

```text
Original order must be preserved
```

Therefore, after choosing character at index `j`, the next character must come from:

```text
j + 1
```

We never move backward.

---

# 🧩 How to Think / Derive the Solution

Suppose:

```text
ipstr = "ABC"
```

Start with:

```text
current = ""
i = 0
```

We can choose any character from index `0` onward:

```text
A
B
C
```

Suppose we choose `A`.

Now:

```text
current = "A"
```

The next character can only come after `A`.

So we can choose:

```text
B
C
```

This produces:

```text
AB
AC
```

If we choose `B` after `A`:

```text
current = "AB"
```

Then we can choose:

```text
C
```

giving:

```text
ABC
```

---

# 🌳 Recursion Structure

For:

```text
ABC
```

the recursion can be visualized as:

```text
                       ""
                 /      |      \
                A       B       C
              /   \      \
            AB     AC     BC
            |
           ABC
```

Every non-empty node represents a subsequence.

Therefore:

```text
A
AB
ABC
AC
B
BC
C
```

are generated.

---

# 🔑 State

The recursive function is:

```python
def bt(current, i):
```

It maintains two things.

### `current`

The subsequence currently being constructed.

Example:

```text
current = "AB"
```

### `i`

The index from which we are allowed to choose the next character.

Example:

```text
i = 2
```

means we can only choose characters from index `2` onward.

---

# 🔄 Choosing Characters

The important loop is:

```python
for j in range(i, n):
```

This means:

> Try every possible next character from the current position onward.

Then:

```python
bt(current + ipstr[j], j + 1)
```

adds that character and moves forward.

---

# ⭐ Why `j + 1`?

Suppose:

```text
ipstr = "ABC"
```

and we choose:

```text
A
```

at index `0`.

The next character must come from:

```text
1, 2
```

which correspond to:

```text
B, C
```

We cannot choose index `0` again.

Therefore:

```python
j + 1
```

is used.

This also guarantees that the original character order is preserved.

---

# ⚠️ Why We Don't Use `used[]`

For permutations, we needed:

```python
used = [False] * n
```

because we could choose any unused element.

Here we **cannot choose any arbitrary character**.

We must preserve order.

For example:

```text
ABC
```

After choosing `B`, we can choose:

```text
C
```

but not:

```text
A
```

The increasing index already handles this:

```text
i → j + 1
```

Therefore, a `used[]` array is unnecessary.

---

# 🔥 Handling Duplicate Subsequences

Consider:

```text
pqpq
```

The same subsequence can sometimes be created through different index choices.

For example, because `p` appears multiple times, different recursion paths can produce the same string.

The problem asks for **unique** subsequences.

Therefore, we use:

```python
ans = set()
```

and:

```python
ans.add(current)
```

A set automatically removes duplicate strings.

---

# 🧠 Why Use a Set?

Suppose recursion generates:

```text
p
p
pq
pq
```

The set stores:

```text
p
pq
```

only once.

So:

```python
ans.add(current)
```

handles uniqueness.

Finally:

```python
sorted(ans)
```

converts the set into a sorted list.

---

# 🚫 Why Don't We Add the Empty String?

Initially:

```text
current = ""
```

But the problem specifically asks for:

> **non-empty subsequences**

Therefore:

```python
if current:
    ans.add(current)
```

ensures:

```text
""
```

is not included.

---

# 🔁 Algorithm

1. Create an empty set `ans`.
2. Start recursion with:

   * `current = ""`
   * `i = 0`
3. If `current` is non-empty, add it to `ans`.
4. Loop from `i` to the end of the string.
5. Choose `ipstr[j]`.
6. Add it to `current`.
7. Recursively continue from `j + 1`.
8. Because a set is used, duplicate subsequences are automatically removed.
9. Sort the final results.
10. Return the sorted list.

---

# 💻 My Solution

```python
def SUBSEQRECUR(ipstr):
    n = len(ipstr)
    ans = set()

    def bt(current, i):
        if current:
            ans.add(current)

        for j in range(i, n):
            bt(current + ipstr[j], j + 1)

    bt("", 0)
    return sorted(ans)
```

---

# 🔍 Code → What It Does → Why

### Length

```python
n = len(ipstr)
```

Stores the length of the string.

---

### Set for unique results

```python
ans = set()
```

Stores subsequences without duplicates.

---

### Recursive function

```python
def bt(current, i):
```

`current` stores the subsequence being built.

`i` tells us where we can start choosing the next character.

---

### Ignore empty subsequence

```python
if current:
    ans.add(current)
```

Only non-empty subsequences are added.

---

### Try every possible next character

```python
for j in range(i, n):
```

We can choose any character from the current index onward.

---

### Choose and recurse

```python
bt(current + ipstr[j], j + 1)
```

Add the selected character and move to the next index.

---

### Start recursion

```python
bt("", 0)
```

Start with:

```text
current = ""
index = 0
```

---

### Sort the result

```python
return sorted(ans)
```

The problem requires lexicographic ordering.

---

# 🧪 Dry Run

Consider:

```text
ipstr = "ABC"
```

Start:

```text
current = ""
i = 0
```

Choose `A`:

```text
current = "A"
i = 1
```

Store:

```text
A
```

Choose `B`:

```text
current = "AB"
i = 2
```

Store:

```text
AB
```

Choose `C`:

```text
current = "ABC"
i = 3
```

Store:

```text
ABC
```

Backtrack to `"A"` and choose `C`:

```text
AC
```

Then return to the root.

Choose `B`:

```text
B
```

Then:

```text
BC
```

Finally choose `C`:

```text
C
```

The set contains:

```text
A
AB
ABC
AC
B
BC
C
```

Sorting gives:

```text
A AB ABC AC B BC C
```

---

# 🧠 Important Difference: Subsequence vs Subset

Although they are closely related, don't confuse them.

### Subset

Usually refers to selecting elements where **order is not important**.

### Subsequence

The original **relative order must be preserved**.

For:

```text
ABC
```

valid:

```text
AC
```

invalid:

```text
CA
```

because `C` appears after `A` in the original string.

---

# ⚔️ Comparison With Your Previous Backtracking Problems

| Problem                    | Main Pattern                                  |
| -------------------------- | --------------------------------------------- |
| **78. Subsets**            | Take / Skip                                   |
| **46. Permutations**       | Choose unused element                         |
| **39. Combination Sum**    | Start index + reuse                           |
| **40. Combination Sum II** | Start index + no reuse + duplicates           |
| **90. Subsets II**         | Start index + duplicate handling              |
| **Unique Subsequences**    | Start index + preserve order + unique results |

The important similarity is:

```text
Start from i
   ↓
Choose j >= i
   ↓
Recurse with j + 1
```

---

# 🚨 Common Mistakes

### 1. Using `used[]`

Not required.

The index progression already prevents going backward.

---

### 2. Using `j` instead of `j + 1`

Incorrect:

```python
bt(current + ipstr[j], j)
```

This could reuse the same character position.

Use:

```python
bt(current + ipstr[j], j + 1)
```

---

### 3. Adding the empty string

Incorrect:

```python
ans.add(current)
```

without checking `current`.

The problem asks for **non-empty** subsequences.

---

### 4. Forgetting duplicate handling

If the input contains repeated characters, simply using a list can produce duplicate subsequences.

Using:

```python
ans = set()
```

solves this.

---

### 5. Forgetting lexicographic order

A set does not guarantee the required order.

Use:

```python
sorted(ans)
```

before returning.

---

# ⏱️ Complexity

There can be up to:

```text
2ⁿ
```

possible subsequences for a string of length `n`.

The set stores the unique subsequences, and sorting the results adds an additional sorting cost.

A useful placement-level description is:

```text
Generation: O(2ⁿ) recursive states
Sorting: O(k log k)
```

where `k` is the number of unique subsequences.

Because strings are being created and stored, the actual cost also depends on their lengths.

### Space

The result can contain up to:

```text
O(2ⁿ)
```

subsequences, with string storage contributing additional space.

The recursion depth is:

```text
O(n)
```

---

# 🎯 Placement-Level Takeaway

## ⭐ If you see this in a placement round...

Ask:

```text
Need all subsequences?
        ↓
      YES
        ↓
Recursion / Backtracking
        ↓
Must preserve original order?
        ↓
      YES
        ↓
Use start index
        ↓
Choose j from i onward
        ↓
Recurse with j + 1
        ↓
Need unique results?
        ↓
Use set / duplicate handling
        ↓
Need sorted output?
        ↓
sorted(ans)
```

---

# ⚡ Final 30-Second Cheat Sheet

```text
╔════════════════════════════════════════════╗
║              QUICK REVISION                ║
╠════════════════════════════════════════════╣
║ Pattern: Recursion / Backtracking          ║
║ Goal: All non-empty unique subsequences    ║
║ State: current, i                          ║
║ Choice: j from i to n-1                    ║
║ Order: MUST be preserved                   ║
║ Move: j + 1                                ║
║ Empty string: Don't include                ║
║ Duplicates: set()                          ║
║ Ordering: sorted(ans)                      ║
║ used[]: NOT required                       ║
║ Generation: O(2ⁿ) states                   ║
║ Recursion depth: O(n)                      ║
╚════════════════════════════════════════════╝
```

> **⭐ One sentence to remember:**
> **Unique subsequences = choose characters from the current index onward, recurse with `j + 1` to preserve order, store only non-empty results, remove duplicates, and sort the final answers.**

---

## 🧠 Pattern You Should Remember

```text
current = ""
      ↓
Choose character j ≥ i
      ↓
current + character
      ↓
Recurse(j + 1)
      ↓
Store current
      ↓
Unique → set
      ↓
Sorted → sorted(ans)
```

This problem is a useful bridge between your **Subsets** problems and more general **recursive choice-generation** problems.
