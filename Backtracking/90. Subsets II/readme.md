# 90. Subsets II

> **Pattern:** Backtracking · Start Index · Duplicate Handling
> **Difficulty:** Medium
> **Platform:** LeetCode
> **Link:** https://leetcode.com/problems/subsets-ii/

---

## ⚡ Quick Revision

| Concept           | Remember                                          |
| ----------------- | ------------------------------------------------- |
| Goal              | Generate all unique subsets                       |
| Pattern           | Backtracking                                      |
| Main Technique    | Start index + skip duplicates                     |
| State             | `current`, `i`                                    |
| Choice            | Choose an element from `i` onward                 |
| Duplicates        | Sort first                                        |
| Duplicate Rule    | Skip duplicate values at the same recursion level |
| Base Case         | No explicit base case needed                      |
| Why no base case? | Every `current` is already a valid subset         |
| Recursive Call    | `bt(current + [nums[j]], j + 1)`                  |
| Time              | `O(n × 2ⁿ)`                                       |
| Space             | `O(n × 2ⁿ)` including output                      |

### 🔑 Core Pattern

```text id="a7g5d2"
Sort
  ↓
Add current subset
  ↓
Loop from current index
  ↓
Skip duplicate choice at same level
  ↓
Choose
  ↓
Recurse with j + 1
```

---

# 🧠 Problem in Simple Words

Given an array that may contain duplicate numbers, generate **all unique subsets**.

Unlike LeetCode 78, the input can contain duplicates.

For example:

```text id="v4yq6z"
nums = [1,2,2]
```

We want:

```text id="3w7j1k"
[]
[1]
[2]
[1,2]
[2,2]
[1,2,2]
```

We must **not** return duplicate subsets such as `[2]` multiple times.

---

# 🔥 How to Recognize This Problem in a Placement

### Recognition Trigger

> **"Generate all subsets" + input may contain duplicates → Backtracking + Sort + Skip duplicate choices.**

Start with the pattern you already know:

```text id="r1h9xk"
Subsets
   ↓
Backtracking
   ↓
Take / Explore
```

Then ask:

> **Can the input contain duplicate values?**

If yes:

```text id="k5p7zq"
Sort
  ↓
Skip duplicate choices
```

### Important distinction

The goal is **not** to prevent duplicate values from appearing in a subset.

For:

```text id="6b0s5n"
[1,2,2]
```

this is valid:

```text id="j2x8v1"
[2,2]
```

We only want to prevent generating the **same subset through duplicate branches**.

---

# 🧩 How to Think / Derive the Solution

## Step 1 — Start with normal Subsets

In LeetCode 78, every element creates a choice:

```text id="j8f6wq"
Take
  OR
Skip
```

For:

```text id="7a2h3m"
[1,2]
```

we can generate:

```text id="g4s8zp"
[]
[1]
[2]
[1,2]
```

---

## Step 2 — What changes when duplicates exist?

Consider:

```text id="x7s2q1"
nums = [1,2,2]
```

If we treat both `2`s independently at the same recursion level, we can create duplicate branches.

At the root:

```text id="p4n8yk"
              []
           /   |   \
          1    2    2
```

The two `2` branches start with the same value and can produce the same subsets.

We want:

```text id="r9z5wk"
              []
           /     \
          1       2
```

So we skip the second `2` **at that level**.

---

# 🔥 Step 3 — Sort the Array

We first do:

```python id="m2q7ya"
nums.sort()
```

For example:

```text id="n4g8vs"
[2,1,2,1]
```

becomes:

```text id="w6j3rx"
[1,1,2,2]
```

Now equal values are next to each other.

This allows us to check:

```python id="p8d5kt"
nums[j] == nums[j-1]
```

---

# 🔥 Step 4 — Skip Duplicate Choices

The key condition is:

```python id="q6x3mp"
if j > i and nums[j] == nums[j-1]:
    continue
```

This means:

> If `j` is not the first candidate being considered at this recursion level, and its value is the same as the previous candidate, skip it.

---

# 🧠 Why `j > i`?

This is the most important part.

Suppose:

```text id="e9k4wd"
nums = [1,2,2]
```

At the root:

```text id="o4m8vz"
i = 0
```

The loop sees:

```text id="z6v5js"
j = 0 → 1
j = 1 → 2
j = 2 → 2
```

For `j = 2`:

```text id="h1v6mc"
j > i
```

and:

```text id="n7r2qa"
nums[2] == nums[1]
```

so we skip it.

Thus we don't create two identical branches starting with `2`.

---

# ⚠️ But We Can Still Use Both `2`s

This is extremely important.

Skipping duplicates does **not** mean:

> "Never use duplicate values."

For:

```text id="1t5w9h"
[1,2,2]
```

we must be able to generate:

```text id="2q7x6m"
[2,2]
```

How?

We choose the first `2`:

```text id="u4r6nb"
[]
 ↓
[2]
```

Then recurse with:

```python id="r2d7kp"
j + 1
```

Now the second `2` is available:

```text id="x5q9bm"
[2]
 ↓
[2,2]
```

So the rule is:

> **Skip duplicate choices at the same recursion level, but allow duplicate values at deeper levels when they represent different elements.**

This is the central idea of Subsets II.

---

# 📊 Visual Explanation

For:

```text id="e4v2mn"
nums = [1,2,2]
```

the important part of the tree is:

```text id="1w8k3a"
                       []
                    /      \
                   1        2
                  / \      / \
                 2   2    2   ...
                 ↓   X    ↓
               valid skip  [2,2]
```

At the root:

```text id="q3m7za"
2 → allowed
2 → skipped
```

But after choosing the first `2`:

```text id="j8q1cw"
2
 ↓
2
 ↓
[2,2] ✅
```

The second `2` is allowed because it is being selected at a **deeper recursion level**.

---

# 🧠 Why There Is No Explicit Base Case

Your code does not have:

```python id="g2v8nm"
if i == n:
```

and that's correct.

Why?

Because **every `current` represents a valid subset**.

For example:

```text id="m7n3kp"
[]
[1]
[1,2]
[2]
```

All of them are valid answers.

Therefore, we add the current subset immediately:

```python id="w9p4xe"
ans.append(current)
```

Then we explore larger subsets.

This is different from LeetCode 78's implementation you used earlier, where you added the subset at the leaf.

Both approaches can work, but this implementation naturally treats every recursion state as an answer.

---

# 🔁 Algorithm

1. Sort `nums`.
2. Create an empty result list.
3. Start backtracking with an empty `current` subset.
4. Add `current` to the result because every state represents a valid subset.
5. Loop through candidates starting from `i`.
6. If the current value equals the previous value **at the same recursion level**, skip it.
7. Add the current number to the subset.
8. Recurse using `j + 1`.
9. Continue exploring all possibilities.
10. Return the result.

---

# 💻 My Solution

```python id="9z8x2c"
class Solution:
    def subsetsWithDup(self, nums: List[int]) -> List[List[int]]:
        n = len(nums)
        ans = []

        nums.sort()

        def bt(current, i):
            ans.append(current)

            for j in range(i, n):
                if j > i and nums[j] == nums[j - 1]:
                    continue

                bt(current + [nums[j]], j + 1)

        bt([], 0)
        return ans
```

Your solution is **correct**.

---

# 🔍 Code → What It Does → Why

### Sort

```python id="f7q3ne"
nums.sort()
```

Places duplicate values next to each other.

Without sorting, this comparison would not reliably identify duplicates.

---

### Result

```python id="h4m8xp"
ans = []
```

Stores all unique subsets.

---

### Backtracking State

```python id="v5j2ks"
def bt(current, i):
```

Here:

* `current` = subset currently being constructed
* `i` = first index available for selection

---

### Add Current Subset

```python id="s6r1qy"
ans.append(current)
```

Every `current` is already a valid subset.

Therefore, save it before exploring its children.

---

### Explore Candidates

```python id="k9t4wf"
for j in range(i, n):
```

Only candidates from `i` onward are considered.

This prevents going backward and creating different orderings of the same subset.

---

### Skip Duplicate Choice

```python id="m3p8xa"
if j > i and nums[j] == nums[j - 1]:
    continue
```

Prevents identical branches from being created at the same recursion level.

---

### Choose and Recurse

```python id="q8v2lz"
bt(current + [nums[j]], j + 1)
```

Add `nums[j]` and move forward.

`j + 1` means the same array element cannot be selected again.

---

# 🧪 Dry Run

Consider:

```text id="f7q2nm"
nums = [1,2,2]
```

After sorting:

```text id="p6r8wk"
[1,2,2]
```

Start:

```text id="c5n1vx"
current = []
i = 0
```

Save:

```text id="y8q4lm"
[]
```

### Choose `1`

```text id="w2p7ka"
current = [1]
i = 1
```

Save:

```text id="a5k9rx"
[1]
```

Choose first `2`:

```text id="j3v6pq"
current = [1,2]
i = 2
```

Save:

```text id="d8m2ws"
[1,2]
```

Choose second `2`:

```text id="u7q1nc"
current = [1,2,2]
```

Save:

```text id="e5r9bf"
[1,2,2]
```

Return to the level where the first `2` was selected.

The second `2` at that same level is skipped because:

```text id="b2n6vx"
j > i
AND
nums[j] == nums[j-1]
```

This prevents a duplicate `[1,2]`.

The algorithm eventually generates:

```text id="n4x8qa"
[]
[1]
[1,2]
[1,2,2]
[2]
[2,2]
```

---

# ⚔️ Subsets vs Subsets II

|                    | 78. Subsets      | 90. Subsets II                 |
| ------------------ | ---------------- | ------------------------------ |
| Input duplicates   | ❌ No             | ✅ Yes                          |
| Sort required      | ❌                | ✅                              |
| Duplicate skipping | ❌                | ✅                              |
| Pattern            | Take / Skip      | Backtracking + Start Index     |
| State              | `current`, `i`   | `current`, `i`                 |
| Reuse same index   | ❌                | ❌                              |
| Recursive index    | `i + 1`          | `j + 1`                        |
| Base case          | Can use `i == n` | Not necessary in this approach |

---

# 🧠 Pattern Connection

Your progression is now:

```text id="u2x6km"
78. Subsets
      ↓
Take / Skip
      ↓
46. Permutations
      ↓
Used Array
      ↓
39. Combination Sum
      ↓
Start Index + Reuse
      ↓
40. Combination Sum II
      ↓
Start Index + No Reuse
      ↓
Sort + Skip Duplicates
      ↓
90. Subsets II
      ↓
Same duplicate-skipping technique
```

Notice that **90 is not a completely new algorithm**.

It combines:

```text
Subsets
+
Duplicate handling from Combination Sum II
```

This is exactly how you should build your DSA pattern map.

---

# ⚔️ Similar Problems / Variations

| Problem                    | Difference                                |
| -------------------------- | ----------------------------------------- |
| **78. Subsets**            | Unique input, generate all subsets        |
| **90. Subsets II**         | Duplicate input, remove duplicate subsets |
| **39. Combination Sum**    | Target sum + unlimited reuse              |
| **40. Combination Sum II** | Target sum + no reuse + duplicates        |
| **46. Permutations**       | Order matters + `used[]`                  |
| **47. Permutations II**    | Permutations with duplicate values        |

---

# 🚨 Common Mistakes

### 1. Forgetting `nums.sort()`

Without sorting, equal values may not be adjacent.

Use:

```python id="d2m7xp"
nums.sort()
```

---

### 2. Skipping every duplicate

Incorrect:

```python id="f6r3qa"
if nums[j] == nums[j - 1]:
    continue
```

This can prevent valid subsets such as:

```text id="w8n2kc"
[2,2]
```

We only skip duplicates at the **same recursion level**.

Correct:

```python id="m9v4ps"
if j > i and nums[j] == nums[j - 1]:
    continue
```

---

### 3. Using `j` instead of `j + 1`

Incorrect:

```python id="r3x8zn"
bt(current + [nums[j]], j)
```

That would allow the same index to be selected repeatedly.

Use:

```python id="k4p7vw"
bt(current + [nums[j]], j + 1)
```

---

### 4. Confusing duplicate values with duplicate subsets

This is valid:

```text id="q8s2lm"
[2,2]
```

The problem is not:

> "Don't allow duplicate numbers."

The problem is:

> **"Don't return the same subset more than once."**

---

### 5. Forgetting that every state is an answer

Unlike many backtracking problems, here:

```python id="z7q5kd"
ans.append(current)
```

happens immediately.

Why?

Because every `current` is a valid subset.

---

# ⏱️ Complexity

There can be up to:

```text id="r4j9xs"
2ⁿ
```

subsets.

Each subset may contain up to `n` elements and may need to be copied.

### Time

```text id="n8w2qa"
O(n × 2ⁿ)
```

### Space

Including the output:

```text id="m5v7kc"
O(n × 2ⁿ)
```

The recursion depth itself is only:

```text id="p2x6zr"
O(n)
```

---

# 🎯 Placement-Level Takeaway

## ⭐ If you see this in a placement round...

Use this decision process:

```text id="c7q4nx"
Need ALL subsets?
       ↓
      YES
       ↓
  Backtracking
       ↓
Can input contain duplicates?
       ↓
      YES
       ↓
     Sort
       ↓
Loop from start
       ↓
Same value as previous
AND not first choice at this level?
       ↓
     SKIP
       ↓
Choose → Recurse with j + 1
```

### The key question

When you see duplicates, ask:

> **"Is this duplicate appearing at the same recursion level?"**

If yes:

```python id="v9k2tm"
continue
```

If it is deeper in the recursion:

```text id="x4m7qp"
allow it
```

That distinction is the heart of this problem.

---

# ⚡ Final 30-Second Cheat Sheet

```text id="z5p8rn"
╔══════════════════════════════════════════════╗
║              QUICK REVISION                  ║
╠══════════════════════════════════════════════╣
║ Pattern: Backtracking + Duplicate Handling   ║
║ State: current, i                             ║
║ Input: May contain duplicates                ║
║ First step: nums.sort()                      ║
║ Order: Does NOT matter                       ║
║ Choice: nums[j] from j = i onward            ║
║ Duplicate: skip at SAME LEVEL                ║
║ Skip: j > i AND nums[j] == nums[j-1]         ║
║ Reuse index: NO                              ║
║ Recursive call: j + 1                        ║
║ Base case: Not required                      ║
║ Key: Every current is a valid subset         ║
║ Time: O(n × 2ⁿ)                              ║
║ Space: O(n × 2ⁿ) including output            ║
╚══════════════════════════════════════════════╝
```

> **⭐ One sentence to remember:**
> **Subsets II = sort the array, explore from the current index, and skip equal values only when they would create duplicate branches at the same recursion level.**

---

## 👨‍💻 Author

**Nijoy P Jose**

This solution is part of my **Data Structures & Algorithms** placement preparation repository, where I document problem-solving patterns, interview techniques, and Python implementations to strengthen my coding skills.
