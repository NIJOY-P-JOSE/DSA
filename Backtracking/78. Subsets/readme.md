# 78. Subsets

**Pattern:** Backtracking — Take / Skip

Given an array of unique integers, return all possible subsets (the power set).

The key idea is that **for every element, we have exactly two choices**:

1. Take the element.
2. Don't take the element.

This creates a binary decision tree that contains every possible subset.

---

## 📝 Problem Statement

Given an integer array `nums` containing unique elements, return all possible subsets.

The answer must not contain duplicate subsets.

### Example 1

```text
Input:
nums = [1,2,3]

Output:
[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
```

### Example 2

```text
Input:
nums = [0]

Output:
[[],[0]]
```

---

## 💡 Intuition

For every number, we have two choices:

```text
Take it
   OR
Skip it
```

For:

```text
nums = [1,2]
```

the decision tree looks like:

```text
                  []
                /    \
            Take 1   Skip 1
              /          \
            [1]            []
           /  \           /  \
       Take 2 Skip 2  Take 2 Skip 2
         /       \       /       \
      [1,2]     [1]     [2]       []
```

Every leaf of this tree represents one complete subset.

Therefore:

```text
[1,2]
[1]
[2]
[]
```

are all possible subsets.

---

## 🧠 Step-by-Step Thinking

We maintain two important pieces of information:

```text
current → the subset we are currently building

i       → the index of the element we are considering
```

At every index, we make two decisions.

### Choice 1 — Take the current element

```python
bt(current + [nums[i]], i + 1)
```

The current number is added to the subset.

### Choice 2 — Skip the current element

```python
bt(current, i + 1)
```

The current number is not added.

Both branches must be explored because each represents a different possibility.

---

## 🛑 Base Case

When:

```python
i == n
```

we have considered every element.

The current subset is now complete, so we add it to the answer:

```python
if i == n:
    ans.append(current)
    return
```

Important:

`i == n` does **not** mean the subset has reached its maximum size.

It means:

> We have finished making decisions for all elements.

For example, all of these eventually reach the base case:

```text
[]
[1]
[2]
[1,2]
```

---

## 🔄 Backtracking Pattern

The general structure is:

```text
             Current State
                  ↓
             Make a Choice
              /         \
            Take        Skip
             ↓            ↓
          Recurse       Recurse
             ↓            ↓
          Continue      Continue
```

For this problem, the pattern can be summarized as:

```text
Take → Recurse
Skip → Recurse
```

There is no explicit `pop()` in this implementation because:

```python
current + [nums[i]]
```

creates a new list instead of modifying `current`.

---

## 🔍 Algorithm

1. Create an empty result list `ans`.
2. Start the backtracking function at index `0` with an empty subset.
3. If `i == n`, add the current subset to `ans`.
4. Explore the branch where `nums[i]` is included.
5. Explore the branch where `nums[i]` is excluded.
6. Continue until every possible decision has been explored.
7. Return `ans`.

---

## 🧪 Dry Run

Consider:

```text
nums = [1,2]
```

Start:

```text
i = 0
current = []
```

### Take `1`

```text
current = [1]
i = 1
```

Now consider `2`.

Take `2`:

```text
current = [1,2]
i = 2
```

We reached the end:

```text
ans = [[1,2]]
```

Backtrack and skip `2`:

```text
current = [1]
i = 2
```

Now:

```text
ans = [[1,2], [1]]
```

---

### Go back to the root and skip `1`

```text
current = []
i = 1
```

Take `2`:

```text
current = [2]
```

Save:

```text
ans = [[1,2], [1], [2]]
```

Skip `2`:

```text
current = []
```

Save:

```text
ans = [[1,2], [1], [2], []]
```

Final result:

```text
[ [1,2], [1], [2], [] ]
```

The order is different from the example, but that is allowed.

---

## ⚠️ Common Mistakes

### 1. Forgetting the skip branch

Incorrect:

```python
bt(current + [nums[i]], i + 1)
```

This only explores subsets that keep taking elements.

You must also explore:

```python
bt(current, i + 1)
```

---

### 2. Using `current + nums[i]`

Incorrect:

```python
current + nums[i]
```

`current` is a list and `nums[i]` is an integer.

Use:

```python
current + [nums[i]]
```

---

### 3. Saving the answer before reaching the end

The subset should be saved when all elements have been considered:

```python
if i == n:
    ans.append(current)
```

---

### 4. Confusing `i == n` with maximum subset size

The base case means:

```text
All elements have been considered.
```

It does not mean:

```text
The subset is full.
```

---

### 5. Forgetting that there is always an empty subset

For any input:

```text
[]
```

is one of the subsets.

For example:

```text
[1,2,3]
```

always includes:

```text
[]
```

---

## 🎯 Interview Notes

This problem is one of the fundamental problems for learning **backtracking**.

The important thing is not just memorizing the code.

You should be able to recognize:

```text
What are my choices?
        ↓
Take / Skip
        ↓
What is my state?
        ↓
current + index
        ↓
When am I finished?
        ↓
index == n
```

The most important mental model is:

> **Every element creates two possibilities: take it or skip it.**

This creates a binary decision tree.

---

## ⏱️ Complexity Analysis

There are `n` elements.

Each element has two choices:

```text
2 × 2 × 2 × ... × 2
       n times

= 2ⁿ
```

Therefore, there are:

```text
2ⁿ subsets
```

Each subset may contain up to `n` elements, and creating/copying the subset can take `O(n)`.

### Time Complexity

```text
O(n × 2ⁿ)
```

### Space Complexity

```text
O(n × 2ⁿ)
```

The output itself contains `2ⁿ` subsets, with up to `n` elements in each.

---

## 💻 Python Solution

```python
class Solution:
    def subsets(self, nums: List[int]) -> List[List[int]]:
        n = len(nums)
        ans = []

        def bt(current, i):
            if i == n:
                ans.append(current)
                return

            # Take nums[i]
            bt(current + [nums[i]], i + 1)

            # Skip nums[i]
            bt(current, i + 1)

        bt([], 0)
        return ans
```

---

## 🔑 Pattern Used

```text
Backtracking
    ↓
Take / Skip
    ↓
Binary Decision Tree
```

This is the basic **Subsets Backtracking Pattern**.

The structure is:

```python
def bt(current, i):

    if base_case:
        save_answer
        return

    # Take
    bt(new_state, i + 1)

    # Skip
    bt(current, i + 1)
```

---

## 🔗 Related Problems

After understanding this problem, practice these in order:

1. **46. Permutations** — Learn a different choice structure using `used`.
2. **90. Subsets II** — Handle duplicate elements.
3. **39. Combination Sum** — Backtracking with a target and reusable choices.
4. **40. Combination Sum II** — Target + duplicates + pruning.
5. **78. Subsets** — Revisit from memory before moving to harder problems.

---

## 📚 Concepts Practiced

* Recursion
* Backtracking
* Decision Trees
* Take / Skip Pattern
* Base Cases
* State Management
* Recursive Branching
* Power Set
* Time Complexity: `O(n × 2ⁿ)`
* Space Complexity: `O(n × 2ⁿ)`

---

## 👨‍💻 Author

**Nijoy P Jose**

This solution is part of my **Data Structures & Algorithms** placement preparation repository, where I document problem-solving patterns, interview techniques, and Python implementations to strengthen my coding skills.
