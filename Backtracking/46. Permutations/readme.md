# 46. Permutations

> **Pattern:** Backtracking · Used Array
> **Difficulty:** Medium
> **Platform:** LeetCode
> **Link:** https://leetcode.com/problems/permutations/

---

## ⚡ Quick Revision

| Concept        | Remember                                        |
| -------------- | ----------------------------------------------- |
| Goal           | Generate every possible ordering of the numbers |
| Pattern        | Backtracking                                    |
| Main Technique | Choose an unused element                        |
| State          | `current` + `st[i]`                             |
| Choice         | Any element that has not been used              |
| Base Case      | `len(current) == n`                             |
| Undo           | `st[i] = False`                                 |
| Time           | `O(n × n!)`                                     |
| Space          | `O(n × n!)` including output                    |

### 🔑 Core Pattern

```text
Choose
  ↓
Mark Used
  ↓
Recurse
  ↓
Undo
  ↓
Try Next Choice
```

---

# 🧠 Problem in Simple Words

Given an array of **unique numbers**, generate every possible ordering of those numbers.

For:

```text
nums = [1,2,3]
```

we need all possible arrangements:

```text
[1,2,3]
[1,3,2]
[2,1,3]
[2,3,1]
[3,1,2]
[3,2,1]
```

The order of the returned permutations does not matter.

---

# 🔥 How to Recognize This Problem in a Placement

### Recognition Trigger

> **"Arrange/order all elements in every possible way" → Think Permutations + Backtracking.**

The important clue is that:

* Every element must eventually be used.
* The **order matters**.
* At every position, we can choose from the elements that haven't been used yet.

### Example

For:

```text
[1,2,3]
```

the first position can contain:

```text
1
2
3
```

If we choose `1`:

```text
[1]
```

then `1` cannot be selected again.

The next position can choose:

```text
2 or 3
```

This naturally creates a backtracking tree.

---

# 🧩 How to Think / Derive the Solution

Don't start by thinking about the code.

Start with:

> **"How can I construct one permutation?"**

Suppose:

```text
nums = [1,2,3]
current = []
```

### Step 1 — Choose the first element

We can choose:

```text
1
2
3
```

Suppose we choose `1`:

```text
current = [1]
```

Now `1` is no longer available.

---

### Step 2 — Choose the second element

Available elements:

```text
2
3
```

Choose `2`:

```text
current = [1,2]
```

---

### Step 3 — Choose the third element

Only `3` remains:

```text
current = [1,2,3]
```

Now the permutation is complete.

---

### Step 4 — Undo the choice

We need to go back:

```text
[1,2,3]
      ↑
    remove 3
```

Then try another available choice:

```text
[1,3]
```

and continue.

This gives:

```text
[1,2,3]
[1,3,2]
```

Eventually, we return to the beginning and try starting with `2` and `3`.

---

# 🌳 Backtracking Tree

For `[1,2,3]`:

```text
                         []
                  /       |       \
                 1        2        3
               /   \    /   \    /   \
              2     3  1     3  1     2
              |     |  |     |  |     |
              3     2  3     1  2     1

             [1,2,3] [1,3,2]
             [2,1,3] [2,3,1]
             [3,1,2] [3,2,1]
```

At every level:

> **Choose one unused number.**

---

# 🔑 What State Do We Need?

We need to know two things:

### 1. `current`

The permutation we are currently constructing.

Example:

```text
current = [1,3]
```

### 2. `st`

Which numbers have already been used.

For:

```text
nums = [1,2,3]
```

after choosing `1` and `3`:

```text
st = [True, False, True]
```

Meaning:

```text
1 → used
2 → unused
3 → used
```

This is why we create:

```python
st = [False] * n
```

---

# 🔄 Choose → Explore → Undo

This is the most important part of the problem.

```python
st[i] = True
bt(current + [nums[i]])
st[i] = False
```

### 1. Choose

```python
st[i] = True
```

Mark the number as used.

### 2. Explore

```python
bt(current + [nums[i]])
```

Continue constructing the permutation.

### 3. Undo

```python
st[i] = False
```

Make the number available again.

This allows us to use that number in another permutation.

---

# 🛑 Base Case

We stop when:

```python
len(current) == n
```

Why?

Because every number has been selected.

For:

```text
nums = [1,2,3]
```

when:

```text
current = [1,2,3]
```

we have a complete permutation.

So:

```python
if n == len(current):
    ans.append(current)
    return
```

---

# 🔁 Algorithm

1. Create an empty result list `ans`.
2. Create a boolean array `st` to track used elements.
3. Start backtracking with an empty `current`.
4. If `current` contains `n` elements, save it.
5. Loop through every element.
6. If the element has already been used, skip it.
7. Mark the element as used.
8. Add it to the current permutation.
9. Recursively continue.
10. After recursion returns, mark the element as unused.
11. Try the next available element.

---

# 💻 My Solution

```python
class Solution:
    def permute(self, nums: List[int]) -> List[List[int]]:
        n = len(nums)
        ans = []
        st = [False] * n

        def bt(current):
            if n == len(current):
                ans.append(current)
                return

            for i in range(n):
                if not st[i]:
                    st[i] = True
                    bt(current + [nums[i]])
                    st[i] = False

        bt([])
        return ans
```

This solution correctly implements the **used-array backtracking pattern**.

---

# 🔍 Code → What It Does → Why

### `n = len(nums)`

```python
n = len(nums)
```

Stores the number of elements.

We need this to know when a complete permutation has been constructed.

---

### `ans`

```python
ans = []
```

Stores every complete permutation.

---

### `st`

```python
st = [False] * n
```

Tracks whether each element has already been used.

Initially:

```text
[False, False, False]
```

Nothing has been selected.

---

### Base case

```python
if n == len(current):
    ans.append(current)
    return
```

When `current` contains all `n` elements, one complete permutation has been created.

---

### Try every element

```python
for i in range(n):
```

At every position, we consider every number.

---

### Check whether it is available

```python
if not st[i]:
```

Only unused elements can be selected.

---

### Choose

```python
st[i] = True
```

Mark it as used.

---

### Recurse

```python
bt(current + [nums[i]])
```

Add the chosen number and continue building the permutation.

---

### Undo

```python
st[i] = False
```

This is the backtracking step.

After finishing all permutations beginning with that choice, we make the number available again.

---

# 🧪 Dry Run

Consider:

```text
nums = [1,2,3]
```

Initially:

```text
current = []
st = [False, False, False]
```

### Choose `1`

```text
st = [True, False, False]
current = [1]
```

Now choose `2`:

```text
st = [True, True, False]
current = [1,2]
```

Choose `3`:

```text
st = [True, True, True]
current = [1,2,3]
```

Complete:

```text
ans = [[1,2,3]]
```

Now return and undo `3`:

```text
st = [True, True, False]
```

There are no more choices at this level, so return again.

Undo `2`:

```text
st = [True, False, False]
```

Now try `3`:

```text
current = [1,3]
st = [True, False, True]
```

Then choose `2`:

```text
current = [1,3,2]
```

So we get:

```text
[1,2,3]
[1,3,2]
```

The algorithm then goes back and tries permutations beginning with `2` and `3`.

---

# ⚔️ Subsets vs Permutations

This difference is **very important for placement questions**.

| Subsets                    | Permutations                               |
| -------------------------- | ------------------------------------------ |
| Order does not matter      | Order matters                              |
| Take / Skip                | Choose an unused element                   |
| Usually uses `index`       | Uses `used[]`                              |
| Each element has 2 choices | Each position can choose multiple elements |
| Number of results: `2ⁿ`    | Number of results: `n!`                    |

### Subsets

```text
Take
  OR
Skip
```

### Permutations

```text
Choose any UNUSED element
```

This distinction should become automatic.

---

# 🚨 Common Mistakes

### 1. Not tracking used elements

Incorrect thinking:

```text
"I'll just keep increasing the index."
```

That works for subsets but not permutations.

For permutations, after choosing `1`, we must still be able to choose `2` or `3`.

---

### 2. Forgetting to undo

This is critical:

```python
st[i] = False
```

Without undoing, once an element is selected it would remain unavailable for other branches.

---

### 3. Allowing an already-used element

Always check:

```python
if not st[i]:
```

Otherwise you could produce:

```text
[1,1,2]
```

which is not a valid permutation of `[1,2,3]`.

---

### 4. Wrong base case

We need:

```python
if len(current) == n:
```

because a permutation is complete only after every element has been used.

---

### 5. Confusing `i` with recursion depth

In this solution, `i` belongs to the `for` loop.

It tells us:

> Which candidate element am I currently trying?

The recursion depth is represented by:

```text
len(current)
```

This is different from the Subsets problem.

---

# ⏱️ Complexity

For `n` unique elements, there are:

```text
n!
```

possible permutations.

For every permutation, we create/copy a list of size `n`.

### Time

```text
O(n × n!)
```

### Space

```text
O(n × n!)
```

This includes the output.

The recursion/temporary state itself is only `O(n)`.

---

# 🧠 Pattern Connection

You previously learned:

```text
Subsets
   ↓
Take / Skip
   ↓
Use index
```

Now:

```text
Permutations
   ↓
Choose
   ↓
Track used elements
   ↓
Recurse
   ↓
Undo
```

So the progression is:

```text
Recursion
    ↓
Backtracking
    ↓
Subsets
    ↓
Permutations
    ↓
Combination Sum
    ↓
Constraint Backtracking
```

The important progression is that **the choices become more complex**.

---

# ⚔️ Similar Problems / Variations

| Problem                    | Main Difference                                  |
| -------------------------- | ------------------------------------------------ |
| **78. Subsets**            | Take/skip each element                           |
| **90. Subsets II**         | Subsets with duplicate values                    |
| **46. Permutations**       | Every ordering of unique elements                |
| **47. Permutations II**    | Permutations with duplicate values               |
| **39. Combination Sum**    | Choose elements to reach a target                |
| **40. Combination Sum II** | Target sum + duplicates + each element used once |

The next major pattern to learn is **Combination Sum**.

---

# 🎯 Placement-Level Takeaway

## ⭐ If you see this in a placement round...

Ask:

```text
Does the problem ask for every possible ORDERING?
             ↓
          YES
             ↓
       Backtracking
             ↓
Can I choose any element at each position?
             ↓
          YES
             ↓
       Track USED elements
             ↓
Choose → Recurse → Undo
```

### The key question

> **"Which elements are still available to choose?"**

That question should immediately make you think about:

```python
st = [False] * n
```

---

# ⚡ Final 30-Second Cheat Sheet

```text
╔══════════════════════════════════════════╗
║             QUICK REVISION               ║
╠══════════════════════════════════════════╣
║ Pattern: Backtracking + Used Array       ║
║ Goal: Generate every ordering            ║
║ State: current + st[]                    ║
║ Choice: Any unused element                ║
║ Base: len(current) == n                   ║
║ Key: Choose → Recurse → Undo             ║
║ Used: st[i] = True                       ║
║ Undo: st[i] = False                      ║
║ Results: n!                              ║
║ Time: O(n × n!)                          ║
║ Space: O(n × n!) including output        ║
╚══════════════════════════════════════════╝
```

> **⭐ One sentence to remember:**
> **Permutations = choose any unused element → recurse → undo the choice.**

---

## 👨‍💻 Author

**Nijoy P Jose**

This solution is part of my **Data Structures & Algorithms** placement preparation repository, where I document problem-solving patterns, interview techniques, and Python implementations to strengthen my coding skills.
