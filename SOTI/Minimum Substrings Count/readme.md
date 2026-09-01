# Minimum Substrings Count

> **Pattern:** Greedy · String Matching
> **Difficulty:** Medium–Hard
> **Platform:** SOTI Coding Assessment

---

## ⚡ Quick Revision

| Concept  | Remember                                                                         |
| -------- | -------------------------------------------------------------------------------- |
| Goal     | Form `B` using the minimum number of substrings of `A`                           |
| Pattern  | Greedy + String Matching                                                         |
| Key idea | At every position in `B`, take the **longest prefix** that is a substring of `A` |
| Reuse    | Substrings of `A` can be reused                                                  |
| State    | Current position `i` in `B`                                                      |
| Failure  | If no substring of `A` starts at `B[i]`, return `-1`                             |
| Time     | `O(N² + M²)` average with a substring set                                        |
| Space    | `O(N²)` in the worst case                                                        |

---

# 🧠 Problem in Simple Words

We are given two strings:

```text
A = source string
B = target string
```

We need to construct `B` by joining together substrings of `A`.

Rules:

* A substring must be **contiguous**.
* A substring can be used multiple times.
* The order of the selected substrings can be different.
* We want the **minimum number of substrings**.
* If it is impossible, return `-1`.

### Example

```text
A = "cab"
B = "abcabc"
```

One possible construction is:

```text
"ab" + "cab" + "c"
```

All three pieces occur in `A`.

Therefore:

```text
Answer = 3
```

---

# 🔥 How to Recognize This Problem in a Placement

Look for these clues:

```text
Target string
      +
Construct using pieces of another string
      +
Pieces can be reused
      +
Minimum number of pieces
```

### Recognition Trigger

> **"Construct a target string using reusable substrings/pieces and minimize the number of pieces."**

Think:

```text
Target B
   ↓
Process from left to right
   ↓
What is the longest piece I can take?
   ↓
Take the longest valid substring
   ↓
Continue
```

This points toward a **greedy longest-prefix matching** approach.

---

# 🧩 How to Think / Derive the Solution

## Step 1 — What is the actual goal?

We don't care about *which* substrings are used as long as:

```text
substring1 + substring2 + ... = B
```

and the number of pieces is minimum.

---

## Step 2 — Look at B from left to right

Suppose:

```text
A = "cab"

B = "abcabc"
     ↑
```

We need to construct the part of `B` beginning at index `0`.

Try increasingly longer prefixes:

```text
"a"     ✓
"ab"    ✓
"abc"   ✗
```

So the longest usable piece is:

```text
"ab"
```

Take it.

Now:

```text
B = "ab | cabc"
          ↑
```

---

## Step 3 — Repeat

From index `2`:

```text
"c"     ✓
"ca"    ✓
"cab"   ✓
"cabc"  ✗
```

Take:

```text
"cab"
```

Now only:

```text
"c"
```

remains.

Take `"c"`.

Therefore:

```text
"ab" + "cab" + "c"

Answer = 3
```

---

# 💡 Why Longest Matching Prefix?

Suppose from the current position we can choose:

```text
"a"
"ab"
"abc"
```

If `"abc"` is valid, taking `"a"` only consumes part of the target that `"abc"` could have consumed.

Taking the longest valid prefix moves us **furthest forward in `B` using one substring**.

Therefore, the greedy idea is:

```text
At position i:
    find longest substring of A
    that matches B starting at i

    take it
    move i forward
```

---

# 📊 Visual Explanation

For:

```text
A = "cab"
B = "abcabc"
```

The useful substrings of `A` include:

```text
c
ca
cab
a
ab
b
```

Now match `B`:

```text
B = a b c a b c
    └──┘
     ab

        └─────┘
          cab

                   └┘
                    c
```

So:

```text
B
│
├── "ab"
├────── "cab"
└──────────── "c"

Total = 3
```

---

# 🔁 Algorithm

### Step 1

Generate every substring of `A` and store them in a `set`.

```python
substring = set()

for i in range(N):
    c = ""

    for j in range(i, N):
        c += A[j]
        substring.add(c)
```

The set allows us to quickly check whether a candidate substring exists.

---

### Step 2

Start at the beginning of `B`.

```python
i = 0
ans = 0
```

---

### Step 3

Find the longest substring starting at `B[i]` that exists in the substring set.

```python
for j in range(i, M):
    if B[i:j+1] in substring:
        longest = j - i + 1
    else:
        break
```

---

### Step 4

If nothing matches:

```python
if longest == 0:
    return -1
```

Otherwise:

```python
i += longest
ans += 1
```

---

### Step 5

Continue until the entire `B` is constructed.

---

# 💻 My Solution

Your solution:

```python
def MIN_SUBSTRINGS_COUNT(A, N, B, M):
    substring = set()
    
    for i in range(N):
        c = ""
        for j in range(i, N):
            c += A[j]
            substring.add(c)
    
    ans = 0
    i = 0

    while i < M:
        longest = 0

        for j in range(i, M):
            if B[i:j+1] in substring:
                longest = j - i + 1
            else:
                break

        if longest == 0:
            return -1

        i += longest
        ans += 1

    return ans
```

### Code → What it does → Why it works

#### 1. Create the substring set

```python
substring = set()
```

This stores every possible substring of `A`.

---

#### 2. Generate substrings

```python
for i in range(N):
    c = ""
    for j in range(i, N):
        c += A[j]
        substring.add(c)
```

For:

```text
A = "cab"
```

we generate:

```text
c
ca
cab
a
ab
b
```

---

#### 3. Start constructing B

```python
i = 0
ans = 0
```

`i` tells us which character of `B` we are currently trying to cover.

`ans` counts how many substrings we have used.

---

#### 4. Find the longest match

```python
for j in range(i, M):
    if B[i:j+1] in substring:
        longest = j - i + 1
    else:
        break
```

We keep extending the substring as long as it occurs in `A`.

---

#### 5. Move forward

```python
i += longest
ans += 1
```

Once we find the longest valid piece, we consume it and continue.

---

# ⚔️ Similar Problems / Variations

| Problem / Pattern    | Difference                                                                |
| -------------------- | ------------------------------------------------------------------------- |
| Word Break           | Usually asks whether the target can be formed, or sometimes minimum words |
| Word Break II        | Generate all possible constructions                                       |
| Minimum Word Break   | Minimize number of dictionary words                                       |
| Restore IP Addresses | Backtracking with valid substring pieces                                  |
| String Matching      | Find occurrences of patterns inside a string                              |

The important connection is:

```text
Minimum pieces to construct target
             ↓
Try possible pieces
             ↓
Optimization
             ↓
Greedy / DP depending on whether local choices are always safe
```

---

# 🚨 Common Mistakes

### 1. Starting `j` from `0`

Incorrect:

```python
for j in range(M):
```

Correct:

```python
for j in range(i, M):
```

Because we're interested in substrings beginning at the **current position `i`**.

---

### 2. Forgetting the impossible case

If:

```text
B[i]
```

cannot start any substring from `A`, construction is impossible.

Return:

```python
-1
```

---

### 3. Confusing substring and subsequence

Substring:

```text
"cab"
```

from:

```text
"cab"
```

is valid.

But:

```text
"cb"
```

is **not** a substring because the characters aren't contiguous.

---

### 4. Assuming each substring can only be used once

The problem explicitly allows reuse.

For example:

```text
A = "abc"
B = "abcabcabc"
```

The same `"abc"` can be reused.

---

### 5. Using every possible substring unnecessarily

We only need to know whether a candidate occurs in `A`.

That's why a `set` is useful.

---

# ⏱️ Complexity

### Generating substrings

There are at most:

```text
N(N + 1) / 2
```

substrings.

So there are `O(N²)` candidates.

However, because Python strings themselves have length, actually constructing/storing all of them can involve significant additional character-copying/memory cost.

### Matching

We examine candidate prefixes of `B`.

With average `O(1)` set membership, the high-level loop performs up to `O(M²)` candidate checks, although Python substring slicing itself costs proportional to the slice length.

So for the implementation above, a useful practical description is:

```text
Time:  O(N² + M³) worst-case due to substring construction/slicing
Space: O(N²)       for stored substrings, with character-storage overhead
```

For `N, M <= 1000`, this may still be practical depending on the test cases and platform, but it is important **not to claim the implementation is strictly O(N² + M²)** because `B[i:j+1]` creates a new string.

---

# 🎯 Placement-Level Takeaway

### ⭐ If you see this in a placement round...

```text
Need to construct B
        ↓
Pieces must occur in A
        ↓
Pieces are reusable
        ↓
Need minimum number of pieces
        ↓
Process B from left → right
        ↓
Find longest matching prefix
        ↓
Take it and jump forward
```

The key implementation idea is:

```python
B[i:j+1] in substring
```

and keep the **longest successful match**.

---

# ⚡ Final 30-Second Cheat Sheet

```text
╔══════════════════════════════════════════╗
║            QUICK REVISION                ║
╠══════════════════════════════════════════╣
║ Pattern: Greedy + String Matching        ║
║ Goal: Minimum pieces to form B            ║
║ State: Current index i in B              ║
║ Key idea: Take longest matching prefix   ║
║ Data structure: Set of substrings of A   ║
║ Reuse: Allowed                            ║
║ Failure: No match → -1                   ║
║ Time: O(N² + M³) worst-case              ║
║ Space: O(N²) + substring storage        ║
╚══════════════════════════════════════════╝
```

> **⭐ One sentence to remember:**
> **At every position of `B`, take the longest prefix that exists as a substring of `A`, then jump forward and repeat.**
