# 40. Combination Sum II

> **Pattern:** Backtracking · Start Index · Duplicate Handling · Pruning
> **Difficulty:** Medium
> **Platform:** LeetCode
> **Link:** https://leetcode.com/problems/combination-sum-ii/

---

## ⚡ Quick Revision

| Concept            | Remember                                               |
| ------------------ | ------------------------------------------------------ |
| Goal               | Find all unique combinations whose sum equals `target` |
| Pattern            | Backtracking                                           |
| Main Technique     | `start` index + skip duplicates                        |
| State              | `current`, `s`, `start`                                |
| Choice             | Candidates from `start` onward                         |
| Reuse              | ❌ Each element/index can be used only once             |
| Duplicate Handling | Sort + `i > start` + compare previous value            |
| Base Case          | `s == target`                                          |
| Pruning            | `s > target`                                           |
| Recursive Call     | `i + 1`                                                |
| Time               | Exponential                                            |
| Space              | Exponential including output                           |

### 🔑 Core Pattern

```text
Sort
 ↓
Start from an index
 ↓
Choose a candidate
 ↓
Recurse with i + 1
 ↓
Skip duplicate choices at the SAME level
```

---

# 🧠 Problem in Simple Words

Given an array of numbers and a target, find all **unique combinations** whose sum equals the target.

Important rules:

* Each element can be used **only once**.
* The input can contain duplicate values.
* Duplicate combinations are not allowed.
* Order does not matter.

Example:

```text
candidates = [10,1,2,7,6,1,5]
target = 8
```

After sorting:

```text
[1,1,2,5,6,7,10]
```

Valid combinations:

```text
[1,1,6]
[1,2,5]
[1,7]
[2,6]
```

---

# 🔥 How to Recognize This Problem in a Placement

### Recognition Trigger

> **"Find all unique combinations" + "each element can be used once" + possible duplicate values → Think Backtracking + Sorting + Skip Duplicates.**

Look for these clues:

```text
Need ALL possible combinations
        ↓
Backtracking
        ↓
Order does NOT matter
        ↓
Use a start index
        ↓
Each element can be used once
        ↓
Recurse with i + 1
        ↓
Input contains duplicates
        ↓
Sort + skip duplicate choices
```

---

# 🧩 How to Think / Derive the Solution

## Step 1 — We need to explore choices

At every position, we can choose a candidate.

For:

```text
[1,2,5]
target = 5
```

we could choose:

```text
1
2
5
```

Then continue recursively.

This naturally suggests **backtracking**.

---

## Step 2 — Order does not matter

Consider:

```text
[1,2,5]
```

and:

```text
[2,1,5]
```

These represent the same combination.

So we don't want the recursion to move backward.

We use:

```python
for i in range(start, n):
```

This means:

> Only consider candidates from `start` onward.

---

## Step 3 — Each element can be used only once

This is different from **Combination Sum (39)**.

In 39:

```python
bt(..., i)
```

because the same candidate can be reused.

Here:

```python
bt(..., i + 1)
```

because the current element must not be used again.

### Remember

```text
Combination Sum
→ reuse allowed
→ i

Combination Sum II
→ reuse NOT allowed
→ i + 1
```

---

# 🔥 Step 4 — The Duplicate Problem

This is the hardest part of this problem.

Consider:

```text
candidates = [1,1,2]
target = 2
```

There are two `1`s:

```text
index:       0   1   2
             ↓   ↓   ↓
candidates = [1,  1,  2]
```

We **must allow**:

```text
[1,1]
```

because the two `1`s are two different elements.

But we must not generate the same combination multiple times.

So we need to skip duplicate values when they appear as choices at the **same recursion level**.

---

# 🧠 Why Sort First?

We use:

```python
candidates.sort()
```

For example:

```text
[10,1,2,7,6,1,5]
```

becomes:

```text
[1,1,2,5,6,7,10]
```

Now duplicate values are next to each other:

```text
[1,1,2,5,6,7,10]
 ↑ ↑
 duplicate
```

That allows us to detect them using:

```python
candidates[i] == candidates[i - 1]
```

---

# 🔥 The Most Important Line

```python
if i > start and candidates[i] == candidates[i - 1]:
    continue
```

This means:

> If this is not the first choice at the current recursion level, and it has the same value as the previous candidate, skip it.

### Why `i > start`?

Because we only want to skip duplicates at the **same recursion level**.

This is extremely important.

---

# Same Level vs Different Level

Suppose:

```text
candidates = [1,1,2]
```

At the same level:

```text
start
 │
 ├── choose first 1
 │
 └── choose second 1  ← duplicate branch → skip
```

But after choosing the first `1`, going deeper:

```text
choose 1
   ↓
next level
   ↓
choose another 1
```

is allowed.

That gives:

```text
[1,1]
```

So:

```python
if i > start and candidates[i] == candidates[i-1]:
```

does **not** mean:

> Never use the same value twice.

It means:

> Don't start two identical branches from the same recursion level.

---

# 📊 Visual Explanation

For:

```text
candidates = [1,1,2]
target = 2
```

think of the tree as:

```text
                         []
                   /      |      \
                  1       1       2
                 /        X
                1
                |
              [1,1]
```

The second `1` at the root is skipped:

```text
1 → branch
1 → duplicate branch ❌
```

But after choosing the first `1`, the second `1` can still be selected:

```text
1
 ↓
1
 ↓
[1,1] ✅
```

This is the key concept of the problem.

---

# 🛑 Base Case

When:

```python
s == target
```

we found a valid combination:

```python
if s == target:
    ans.append(current)
    return
```

For example:

```text
current = [1,2,5]
s = 8
target = 8
```

Save it.

---

# ✂️ Pruning

Because all candidates are positive:

```text
s > target
```

means the current branch can never become valid.

For example:

```text
target = 8
s = 10
```

Adding more positive numbers will only increase the sum.

So:

```python
if s > target:
    return
```

prunes the branch.

---

# 🔁 Algorithm

1. Sort `candidates`.
2. Start backtracking with:

   * empty `current`
   * sum `0`
   * start index `0`
3. If `s == target`, save the current combination.
4. If `s > target`, stop this branch.
5. Loop from `start` to `n`.
6. If the current value duplicates the previous value at the same recursion level, skip it.
7. Add the candidate to `current`.
8. Recurse using `i + 1` because each element can be used only once.
9. Continue exploring the remaining candidates.
10. Return all unique combinations.

---

# 💻 My Solution

```python
class Solution:
    def combinationSum2(self, candidates: List[int], target: int) -> List[List[int]]:
        n = len(candidates)
        ans = []
        candidates.sort()

        def bt(current, s, start):
            if s == target:
                ans.append(current)
                return

            if s > target:
                return

            for i in range(start, n):
                if i > start and candidates[i] == candidates[i - 1]:
                    continue

                bt(current + [candidates[i]], s + candidates[i], i + 1)

        bt([], 0, 0)
        return ans
```

Your solution correctly uses:

```text
sort
+
start
+
duplicate skipping
+
i + 1
+
pruning
```

---

# 🔍 Code → What It Does → Why

### Sort

```python
candidates.sort()
```

Places duplicate values next to each other so they can be detected.

---

### Backtracking State

```python
def bt(current, s, start):
```

| Variable  | Meaning                             |
| --------- | ----------------------------------- |
| `current` | Combination currently being built   |
| `s`       | Current sum                         |
| `start`   | First index available for selection |

---

### Target Reached

```python
if s == target:
    ans.append(current)
    return
```

A valid combination has been found.

---

### Pruning

```python
if s > target:
    return
```

The branch cannot produce a valid answer because all candidates are positive.

---

### Loop

```python
for i in range(start, n):
```

Only candidates from `start` onward are considered.

This prevents going backward and generating different orders of the same combination.

---

### Skip Duplicate Choices

```python
if i > start and candidates[i] == candidates[i - 1]:
    continue
```

Avoids generating duplicate combinations from identical choices at the same level.

---

### Recurse With `i + 1`

```python
bt(current + [candidates[i]], s + candidates[i], i + 1)
```

`i + 1` means:

> The current element cannot be selected again.

---

# 🧪 Dry Run

Consider:

```text
candidates = [1,1,2,5,6,7,10]
target = 8
```

Start:

```text
current = []
s = 0
start = 0
```

Choose first `1`:

```text
current = [1]
s = 1
start = 1
```

Now the second `1` is available because it is at index `1`.

Choose it:

```text
current = [1,1]
s = 2
start = 2
```

Continue.

Choose `6`:

```text
current = [1,1,6]
s = 8
```

Target reached:

```text
[1,1,6]
```

Save it.

---

Now return to the root.

We encounter the second `1`:

```text
i > start
```

and:

```text
candidates[i] == candidates[i-1]
```

so:

```python
continue
```

That prevents another identical branch starting with `1`.

Then the algorithm explores:

```text
[1,2,5]
[1,7]
[2,6]
```

Final result:

```text
[1,1,6]
[1,2,5]
[1,7]
[2,6]
```

---

# ⚔️ Combination Sum vs Combination Sum II

This comparison is extremely important.

|                  | Combination Sum 39 | Combination Sum II 40             |
| ---------------- | ------------------ | --------------------------------- |
| Input duplicates | ❌ No               | ✅ Yes                             |
| Reuse element    | ✅ Unlimited        | ❌ Once                            |
| Start index      | ✅                  | ✅                                 |
| Recursive index  | `i`                | `i + 1`                           |
| Sorting          | Not required       | ✅ Required for duplicate skipping |
| Skip duplicates  | ❌                  | ✅                                 |
| Pruning          | `sum > target`     | `sum > target`                    |

### Mental shortcut

```text
39:
Reuse?
YES
→ i

40:
Reuse?
NO
→ i + 1

Duplicates?
YES
→ sort + skip same-level duplicates
```

---

# 🚨 Common Mistakes

### 1. Forgetting to sort

Without:

```python
candidates.sort()
```

the duplicate-skipping condition cannot reliably work.

---

### 2. Using `i` instead of `i + 1`

Incorrect:

```python
bt(..., i)
```

That would allow the same element to be reused.

For Combination Sum II:

```python
bt(..., i + 1)
```

---

### 3. Removing the `i > start` condition

Incorrect:

```python
if candidates[i] == candidates[i - 1]:
```

This can incorrectly prevent valid combinations such as:

```text
[1,1]
```

The duplicate should only be skipped when it is another identical **choice at the same level**.

---

### 4. Using `st[]`

You don't need a `used` array here.

The increasing index:

```text
i + 1
```

already guarantees that the same index cannot be reused.

---

### 5. Confusing duplicate values with duplicate elements

For:

```text
[1,1]
```

the two `1`s are different elements at different indices.

Therefore:

```text
[1,1]
```

can be valid.

The goal is to prevent duplicate **combinations**, not prevent duplicate values from appearing inside a combination.

---

### 6. Forgetting pruning

Because candidates are positive:

```python
if s > target:
    return
```

can stop impossible branches early.

---

# 🧠 Pattern Connection

Your backtracking progression is now:

```text
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
```

This is exactly how you should build your backtracking mental map.

You are not memorizing four unrelated solutions.

You are learning how to modify the same backtracking idea based on the problem's constraints.

---

# ⚔️ Similar Problems / Variations

| Problem                    | Difference                              |
| -------------------------- | --------------------------------------- |
| **78. Subsets**            | Every element → take or skip            |
| **90. Subsets II**         | Subsets with duplicate values           |
| **39. Combination Sum**    | Target + unlimited reuse                |
| **40. Combination Sum II** | Target + each element once + duplicates |
| **46. Permutations**       | Every ordering; use `used[]`            |
| **47. Permutations II**    | Permutations with duplicate values      |

The next useful problem is:

> **90. Subsets II**

It uses the same **sort + same-level duplicate skipping** idea, but combines it with the simpler Take/Skip subset pattern.

---

# 🎯 Placement-Level Takeaway

## ⭐ If you see this in a placement round...

Use this decision process:

```text
Need ALL combinations?
        ↓
      YES
        ↓
   Backtracking
        ↓
Does order matter?
        ↓
       NO
        ↓
   Use start index
        ↓
Can an element be reused?
      ↙       ↘
    YES        NO
     ↓          ↓
     i         i+1
                ↓
        Are duplicates possible?
                ↓
               YES
                ↓
          Sort + skip
       same-level duplicates
```

### The key question

When you see duplicate values, ask:

> **"Am I skipping a duplicate choice at the same recursion level, or am I preventing a duplicate value from being used deeper in the tree?"**

Those are **not the same thing**.

---

# ⚡ Final 30-Second Cheat Sheet

```text
╔══════════════════════════════════════════════╗
║              QUICK REVISION                  ║
╠══════════════════════════════════════════════╣
║ Pattern: Backtracking + Start Index          ║
║ State: current, sum, start                   ║
║ Order: Does NOT matter                       ║
║ Reuse: NO                                    ║
║ Recursive index: i + 1                       ║
║ Duplicates: Sort first                       ║
║ Skip: i > start AND same as previous         ║
║ Base: sum == target                          ║
║ Prune: sum > target                          ║
║ Key: Skip duplicates at SAME LEVEL           ║
║ Time: Exponential                            ║
║ Space: Exponential including output          ║
╚══════════════════════════════════════════════╝
```

> **⭐ One sentence to remember:**
> **Combination Sum II = sorted backtracking from `start`, move to `i + 1` because elements cannot be reused, and skip equal values only when they appear at the same recursion level.**

---

## 👨‍💻 Author

**Nijoy P Jose**

This solution is part of my **Data Structures & Algorithms** placement preparation repository, where I document problem-solving patterns, interview techniques, and Python implementations to strengthen my coding skills.
