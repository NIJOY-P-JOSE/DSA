# 22. Generate Parentheses

> **LeetCode:** [22. Generate Parentheses](https://leetcode.com/problems/generate-parentheses/)
> **Pattern:** Backtracking · Recursion
> **Difficulty:** Medium
> **Platform:** SOTI Coding Assessment · LeetCode

---

## ⚡ Quick Revision

| Concept         | Remember                                                        |
| --------------- | --------------------------------------------------------------- |
| Goal            | Generate all valid combinations of `n` pairs of parentheses     |
| Key idea        | Build the string one character at a time                        |
| Add `(`         | Only when `open < n`                                            |
| Add `)`         | Only when `close < open`                                        |
| Base case       | `open == n` and `close == n`                                    |
| Main constraint | Never allow more `)` than `(`                                   |
| Pattern         | Backtracking + Recursion                                       |
| Time            | `O(Cₙ × n)`                                                     |
| Space           | `O(Cₙ × n)` including output                                    |

---

# 🧠 Problem in Simple Words

Given `n` pairs of parentheses, generate **all possible valid combinations**.

A combination is valid when:

- It contains exactly `n` opening parentheses `(`.
- It contains exactly `n` closing parentheses `)`.
- At every point, the number of `)` never exceeds the number of `(`.

For:

```text
n = 3
```

the output is:

```text
["((()))","(()())","(())()","()(())","()()()"]
```

---

## Example

```text
Input:
n = 3

Output:
["((()))","(()())","(())()","()(())","()()()"]
```

We build the answer one character at a time.

At every step, we have two possible choices:

```text
Add "("
Add ")"
```

But we only make a choice when it keeps the string valid.

---

# 💡 Key Observation

The important idea is to keep track of:

```text
open  = number of '(' used
close = number of ')' used
```

### When can we add `(`?

We can use at most `n` opening parentheses:

```python
if open < n:
```

---

### When can we add `)`?

We can add `)` only if there is an unmatched `(`.

Therefore:

```python
if close < open:
```

For example:

```text
open = 2
close = 1
```

We can add `)`.

But:

```text
open = 1
close = 1
```

we cannot add another `)` because that would create an invalid prefix.

So:

```python
close < open
```

is the **most important condition** in this problem.

---

# 🔍 Algorithm

### Step 1 — Start with an empty string

```python
bt("", 0, 0)
```

where:

```text
"" → current string
0  → opening parentheses used
0  → closing parentheses used
```

---

### Step 2 — Try adding `(`

If:

```python
open < n
```

add an opening parenthesis:

```python
bt(string + "(", open + 1, close)
```

---

### Step 3 — Try adding `)`

If:

```python
close < open
```

add a closing parenthesis:

```python
bt(string + ")", open, close + 1)
```

This prevents invalid strings from being generated.

---

### Step 4 — Add completed strings

When:

```python
open == n and close == n
```

we have used all parentheses.

Add the current string to the answer:

```python
ans.append(string)
```

---

# 💻 Solution

```python
class Solution:
    def generateParenthesis(self, n: int) -> List[str]:
        
        ans = []

        def bt(string, open, close):
            if open == n and close == n:
                ans.append(string)
                return

            if open < n:
                bt(string + "(", open + 1, close)

            if close < open:
                bt(string + ")", open, close + 1)

        bt("", 0, 0)

        return ans
```

---

# 🧪 Dry Run

For:

```text
n = 2
```

Start:

```text
string = ""
open = 0
close = 0
```

Only `(` can be added:

```text
"("
open = 1
close = 0
```

Now we have two possible choices.

### Choice 1 — Add `(`

```text
"(("
open = 2
close = 0
```

Now only `)` can be added:

```text
"(()"
open = 2
close = 1
```

Then:

```text
"(())"
open = 2
close = 2
```

Add it to the answer.

---

### Choice 2 — Add `)`

Backtrack to:

```text
"("
open = 1
close = 0
```

Add `)`:

```text
"()"
open = 1
close = 1
```

Then add `(`:

```text
"()("
open = 2
close = 1
```

Finally:

```text
"()()"
open = 2
close = 2
```

Final answer:

```text
["(())", "()()"]
```

---

# 🚨 Important Edge Case

### `n = 1`

```text
Input:
n = 1
```

Only one valid combination exists:

```text
()
```

Output:

```text
["()"]
```

---

### Never allow `close > open`

For example:

```text
open = 1
close = 2
```

must never happen.

The condition:

```python
if close < open:
```

prevents this.

---

# ❌ Common Mistakes

### 1. Adding `)` without checking `close < open`

Wrong:

```python
bt(string + ")", open, close + 1)
```

Correct:

```python
if close < open:
    bt(string + ")", open, close + 1)
```

---

### 2. Using more than `n` opening parentheses

Correct:

```python
if open < n:
```

---

### 3. Adding the answer too early

Only add the string when:

```python
open == n and close == n
```

---

### 4. Checking validity only after creating the string

The better approach is to prevent invalid choices during recursion.

Instead of:

```text
Generate everything
       ↓
Check validity
```

we do:

```text
Make only valid choices
       ↓
Continue recursion
```

This is the essence of backtracking.

---

# 🎯 Placement Pattern

When you see:

```text
Generate all possible combinations
+
There are constraints on valid choices
```

think:

```text
Backtracking
     ↓
Choose
     ↓
Check constraint
     ↓
Explore
     ↓
Return
```

For parentheses:

```text
Can I add "("?
       ↓
   open < n

Can I add ")"?
       ↓
   close < open
```

### Recognition Trick

If the problem asks you to:

```text
Generate ALL valid possibilities
```

and there are rules restricting which choices are allowed, think:

```text
Backtracking
```

---

# ⚡ 30-Second Revision

```text
Pattern:
Backtracking + Recursion

Maintain:
open  = number of '(' used
close = number of ')' used

1. If open < n:
       add "("

2. If close < open:
       add ")"

3. If open == n and close == n:
       add string to answer

Most important condition:
       close < open

Why?
       Never allow more ')' than '('.

Think:

"Can I add '('?"
→ open < n

"Can I add ')'?"
→ close < open
```

> **⭐ Key takeaway:**
> **In backtracking, don't generate every possibility blindly. Track the state and only make choices that keep the partial solution valid.**

> **🔥 For Generate Parentheses:**
> `open < n` → add `(`
>
> `close < open` → add `)`
