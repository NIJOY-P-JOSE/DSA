# 39. Combination Sum

> **Pattern:** Backtracking · Start Index · Target Sum · Pruning
> **Difficulty:** Medium
> **Platform:** LeetCode
> **Link:** https://leetcode.com/problems/combination-sum/

---

## ⚡ Quick Revision

| Concept        | Remember                                               |
| -------------- | ------------------------------------------------------ |
| Goal           | Find all unique combinations whose sum equals `target` |
| Pattern        | Backtracking                                           |
| Main Technique | `start` index                                          |
| State          | `current`, `sum`, `start`                              |
| Choice         | Candidates from `start` onward                         |
| Reuse          | Same candidate can be chosen again                     |
| Base Case      | `sum == target`                                        |
| Pruning        | `sum > target`                                         |
| Important Rule | Recurse with `i`, not `i + 1`                          |
| Time           | Exponential                                            |
| Space          | Exponential including output                           |

### 🔑 Core Pattern

```text
Choose candidate
      ↓
Add to current
      ↓
Recurse
      ↓
Same candidate can be reused
      ↓
Continue from i
```

---

# 🧠 Problem in Simple Words

We are given distinct positive integers and a target.

We need to find **all combinations** whose elements add up to the target.

The same number can be used **unlimited times**.

For:

```text
candidates = [2,3,6,7]
target = 7
```

the answer is:

```text
[2,2,3]
[7]
```

The order inside a combination does **not** matter.

Therefore:

```text
[2,2,3]
[2,3,2]
[3,2,2]
```

should be considered the **same combination**.

---

# 🔥 How to Recognize This Problem in a Placement

### Recognition Trigger

> **"Find all combinations that satisfy a target" + "elements can be reused" → Think Backtracking + Start Index.**

Look for these clues:

```text
Find ALL possible combinations
        ↓
Need to explore possibilities
        ↓
Backtracking
```

and:

```text
Same candidate can be used unlimited times
        ↓
Current index can be reused
        ↓
Recurse with i
```

### Important Question

Ask yourself:

> **Does order matter?**

Here:

```text
[2,3,2]
```

and:

```text
[2,2,3]
```

represent the same combination.

Therefore, we need to prevent the recursion from moving backward through the candidates.

---

# 🧩 How to Think / Derive the Solution

## Step 1 — Start with the simplest idea

At every step, choose a candidate.

For:

```text
candidates = [2,3,6,7]
target = 7
```

we can initially choose:

```text
2
3
6
7
```

Suppose we choose `2`:

```text
current = [2]
sum = 2
```

We still need:

```text
7 - 2 = 5
```

So we continue making choices.

---

## Step 2 — The duplicate-order problem

If we simply allow every candidate at every recursion level, we can generate:

```text
[2,2,3]
[2,3,2]
[3,2,2]
```

These are duplicates from the problem's perspective.

The problem wants only:

```text
[2,2,3]
```

So we need a way to say:

> **"Once I move forward through the candidates, don't go backward."**

---

# 🔥 Step 3 — Use a `start` index

Instead of:

```python
for i in candidates:
```

we use:

```python
for i in range(start, n):
```

Now the recursion only considers candidates from `start` onward.

Example:

```text
candidates = [2,3,6,7]

indices:
          0 1 2 3
          ↓ ↓ ↓ ↓
         [2,3,6,7]
```

Initially:

```text
start = 0
```

We can choose anything.

If we choose `3` at index `1`, the next recursion starts from index `1`.

So:

```text
3 → 3,6,7
```

It cannot go back to:

```text
2
```

Therefore:

```text
[3,2,...]
```

is never generated.

This removes duplicate orderings.

---

# 🔥 Step 4 — Why recurse with `i` instead of `i + 1`?

This is one of the most important parts of this problem.

The problem says:

> The same number may be chosen an unlimited number of times.

Suppose:

```text
candidates = [2,3]
target = 8
```

We need:

```text
[2,2,2,2]
```

Suppose we choose `2` at index `0`.

If we call:

```python
bt(..., i + 1)
```

we move to index `1` and can no longer choose `2`.

That would be wrong.

Instead:

```python
bt(..., i)
```

keeps the current candidate available.

So:

```text
Choose 2
   ↓
i = 0
   ↓
Choose 2 again
   ↓
i = 0
   ↓
Choose 2 again
```

This allows unlimited reuse.

---

# 📊 Visual Explanation

For:

```text
candidates = [2,3]
target = 7
```

the important part of the recursion looks like:

```text
                    []
                 start=0
                 /      \
                2        3
               / \        \
              2   3        3
             /     \        \
            2       3        ...
           /
          3
```

Notice:

### After choosing `2`

We can choose `2` again:

```text
2 → 2 → 2 → ...
```

because we recurse with the **same index**.

### After choosing `3`

We cannot return to `2`.

That's what prevents:

```text
[3,2]
```

when:

```text
[2,3]
```

already represents the same combination.

---

# 🧠 What Does the State Mean?

Our backtracking function is:

```python
bt(current, s, start)
```

Each parameter has a purpose.

### `current`

The combination currently being constructed.

Example:

```text
[2,2,3]
```

### `s`

The current sum.

Example:

```text
2 + 2 + 3 = 7
```

### `start`

The first candidate index that we're allowed to consider.

This prevents going backward and generating duplicate orderings.

---

# 🛑 Base Case

When:

```python
s == target
```

we found a valid combination.

So:

```python
if s == target:
    ans.append(current)
    return
```

Example:

```text
current = [2,2,3]
s = 7
target = 7
```

Save it and stop this branch.

---

# ✂️ Pruning

Because every candidate is positive, once:

```text
s > target
```

we can never return to the target.

For example:

```text
target = 7

current sum = 8
```

Adding another positive number can only make the sum larger.

Therefore:

```python
if s > target:
    return
```

This prevents us from exploring useless branches.

### Important

This pruning is valid because the constraints guarantee:

```text
candidates[i] >= 2
```

If negative numbers were allowed, `s > target` would not necessarily mean the branch is impossible.

---

# 🔁 Algorithm

1. Start with an empty combination.
2. Start the candidate search at index `0`.
3. If the current sum equals the target, save the combination.
4. If the sum exceeds the target, stop this branch.
5. Loop from `start` to the end of the candidates.
6. Choose `candidates[i]`.
7. Add it to the current combination.
8. Recurse using **the same `i`** because the candidate can be reused.
9. After returning, try the next candidate.
10. Continue until all possibilities are explored.

---

# 💻 My Solution

Your solution:

```python
class Solution:
    def combinationSum(self, candidates: List[int], target: int) -> List[List[int]]:
        n = len(candidates)
        ans = []

        def bt(current, s, start):
            if s == target:
                ans.append(current)
                return

            if s > target:
                return

            for i in range(start, n):
                bt(current + [candidates[i]], s + candidates[i], i)

        bt([], 0, 0)
        return ans
```

This correctly solves the problem.

One unnecessary line from your earlier version was:

```python
st = [False] * n
```

It is not needed here because combinations use a `start` index instead of a `used` array.

---

# 🔍 Code → What It Does → Why

### `ans`

```python
ans = []
```

Stores all valid combinations.

---

### Backtracking function

```python
def bt(current, s, start):
```

Keeps track of:

```text
current → current combination
s       → current sum
start   → where we can start choosing
```

---

### Target reached

```python
if s == target:
    ans.append(current)
    return
```

We found a valid combination.

---

### Pruning

```python
if s > target:
    return
```

Because all candidates are positive, this branch cannot become valid again.

---

### Loop from `start`

```python
for i in range(start, n):
```

This is what prevents duplicate orderings.

We never move backward.

---

### Choose and recurse

```python
bt(current + [candidates[i]], s + candidates[i], i)
```

Two important things happen:

```text
current + [candidates[i]]
```

adds the selected candidate.

And:

```text
i
```

allows the same candidate to be selected again.

---

# 🧪 Dry Run

Consider:

```text
candidates = [2,3]
target = 7
```

Start:

```text
current = []
s = 0
start = 0
```

Choose `2`:

```text
current = [2]
s = 2
start = 0
```

Because we pass `i = 0`, we can choose `2` again:

```text
current = [2,2]
s = 4
```

Again:

```text
current = [2,2,2]
s = 6
```

Now:

```text
2 + 2 + 2 = 6
```

Try `2`:

```text
s = 8
```

Since:

```text
8 > 7
```

prune.

Backtrack and try `3`:

```text
current = [2,2,3]
s = 7
```

Target reached:

```text
ans = [[2,2,3]]
```

The recursion eventually tries starting with `3`:

```text
[3]
```

and can choose `3` again, but it can never go back to `2`.

This prevents:

```text
[3,2,2]
```

from being generated.

---

# ⚔️ Subsets vs Permutations vs Combination Sum

This is one of the most useful comparisons for placement preparation.

| Problem             | Main Question                         | Technique           |
| ------------------- | ------------------------------------- | ------------------- |
| **Subsets**         | Take or skip each element?            | `index` + Take/Skip |
| **Permutations**    | Which unused element comes next?      | `used[]`            |
| **Combination Sum** | Which candidate should I choose next? | `start`             |
| **Combination Sum** | Can I reuse it?                       | Recurse with `i`    |

### Remember:

```text
Subsets
→ Take / Skip

Permutations
→ Any UNUSED element

Combination Sum
→ Choose from START onward
→ Reuse allowed → use i
```

---

# 🚨 Common Mistakes

### 1. Looping from `0` every time

Incorrect:

```python
for i in range(n):
```

This allows the recursion to move backward and creates duplicate orderings.

Use:

```python
for i in range(start, n):
```

---

### 2. Using `i + 1` for the recursive call

Incorrect:

```python
bt(..., i + 1)
```

That prevents reusing the same candidate.

For Combination Sum:

```python
bt(..., i)
```

because reuse is allowed.

---

### 3. Using `used[]`

A `used` array is appropriate for **Permutations**.

Here, it is unnecessary.

The `start` index controls which candidates are available.

---

### 4. Forgetting pruning

Without:

```python
if s > target:
    return
```

the recursion continues generating combinations that can never reach the target.

---

### 5. Thinking `[2,3]` and `[3,2]` are different

They are not.

The problem asks for **combinations**, so order does not matter.

The `start` index prevents generating both.

---

# ⏱️ Complexity

Let `T` be the target and `m` be the smallest candidate value.

The recursion depth can be as large as:

```text
T / m
```

because the smallest candidate may be repeatedly chosen.

The exact number of generated combinations depends heavily on the input, so this problem does not have a simple polynomial complexity.

A useful placement-level description is:

```text
Time: Exponential
Space: Exponential including output
```

The recursion depth is bounded by approximately:

```text
O(T / min(candidates))
```

---

# 🧠 Pattern Connection

We have now progressed through three important backtracking patterns:

```text
78. Subsets
       ↓
Take / Skip
       ↓
46. Permutations
       ↓
Choose unused element
       ↓
39. Combination Sum
       ↓
Choose from start onward
       ↓
Target + Reuse + Pruning
```

This is important because Combination Sum is **not a completely new technique**.

It is backtracking with a different way of controlling the choices.

---

# ⚔️ Similar Problems / Variations

| Problem                    | Difference                                      |
| -------------------------- | ----------------------------------------------- |
| **78. Subsets**            | Generate all subsets                            |
| **46. Permutations**       | Order matters; use `used[]`                     |
| **39. Combination Sum**    | Target sum; reuse allowed                       |
| **40. Combination Sum II** | Target sum; each element used once + duplicates |
| **90. Subsets II**         | Subsets with duplicate values                   |
| **47. Permutations II**    | Permutations with duplicate values              |

The most important next variation is:

> **40. Combination Sum II**

It will force you to understand what changes when **reuse is NOT allowed** and the input contains **duplicates**.

---

# 🎯 Placement-Level Takeaway

## ⭐ If you see this in a placement round...

Ask these questions:

```text
Does the problem ask for ALL combinations?
             ↓
            YES
             ↓
        Backtracking
             ↓
Does order matter?
             ↓
            NO
             ↓
Use a START index
             ↓
Can an element be reused?
        ↙           ↘
      YES            NO
       ↓              ↓
   recurse i      recurse i+1
```

For this problem:

```text
Combination
     ↓
Order doesn't matter
     ↓
start index
     ↓
Reuse allowed
     ↓
recurse with i
     ↓
Positive numbers
     ↓
Prune when sum > target
```

---

# ⚡ Final 30-Second Cheat Sheet

```text
╔════════════════════════════════════════════╗
║              QUICK REVISION                ║
╠════════════════════════════════════════════╣
║ Pattern: Backtracking + Start Index        ║
║ State: current, sum, start                 ║
║ Choice: candidates[start:]                 ║
║ Order: Does NOT matter                     ║
║ Reuse: YES                                 ║
║ Base: sum == target                        ║
║ Prune: sum > target                        ║
║ Reuse allowed → recurse with i             ║
║ No backward movement → range(start, n)     ║
║ Time: Exponential                          ║
║ Space: Exponential including output       ║
╚════════════════════════════════════════════╝
```

> **⭐ One sentence to remember:**
> **Combination Sum = choose candidates from `start` onward, recurse with `i` when reuse is allowed, and prune when the sum exceeds the target.**

---

## 👨‍💻 Author

**Nijoy P Jose**

This solution is part of my **Data Structures & Algorithms** placement preparation repository, where I document problem-solving patterns, interview techniques, and Python implementations to strengthen my coding skills.
