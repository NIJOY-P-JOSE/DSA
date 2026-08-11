# 5. Longest Palindromic Substring

**Pattern:** Dynamic Programming — 2D DP
**Difficulty:** Medium
**LeetCode:** [5. Longest Palindromic Substring](https://leetcode.com/problems/longest-palindromic-substring/)

---

## 📌 Problem Statement

Given a string `s`, find and return the **longest palindromic substring**.

A palindrome reads the same from left to right and right to left.

### Example 1

```text
Input:
babad

Output:
bab
```

`aba` is also a valid answer.

### Example 2

```text
Input:
cbbd

Output:
bb
```

---

# 💡 Intuition

The main question is:

> **How can we determine whether a substring `s[i...j]` is a palindrome?**

Consider:

```text
b a b
↑   ↑
```

The first and last characters are equal.

If the inside:

```text
a
```

is also a palindrome, then:

```text
bab
```

must be a palindrome.

Therefore, a substring is a palindrome when:

1. Its first and last characters are equal.
2. The substring inside them is also a palindrome.

This gives us a natural DP relationship.

---

# 🧠 DP State

Define:

```text
dp[i][j]
```

as:

> `true` if the substring `s[i...j]` is a palindrome, otherwise `false`.

For example, for:

```text
s = "babad"
```

```text
dp[0][2]
```

represents:

```text
s[0...2] = "bab"
```

---

# 🔄 DP Transition

For a substring from `i` to `j`:

First check:

```java
s.charAt(i) == s.charAt(j)
```

If they are different:

```text
dp[i][j] = false
```

If they are equal, we need to check the substring inside.

```text
dp[i][j] = dp[i+1][j-1]
```

Therefore:

```text
             s[i] == s[j]
                  │
                 YES
                  │
                  ▼
          Check inside substring
             dp[i+1][j-1]
```

### Example

For:

```text
"bab"
```

we check:

```text
b == b
```

Then check:

```text
"a"
```

Since `"a"` is a palindrome:

```text
dp[1][1] = true
```

Therefore:

```text
dp[0][2] = true
```

So `"bab"` is a palindrome.

---

# 🧱 Base Cases

## Length 1

Every single character is a palindrome.

```text
dp[i][i] = true
```

For:

```text
babad
```

we have:

```text
b    a    b    a    d
✓    ✓    ✓    ✓    ✓
```

---

## Length 2

For two characters, there is no substring between them.

Therefore:

```text
"aa" → palindrome
"ab" → not palindrome
```

So:

```java
if (len == 2)
    dp[i][j] = true;
```

when:

```text
s[i] == s[j]
```

---

# 🔢 Why Process by Length?

Our transition depends on:

```text
dp[i+1][j-1]
```

which is the substring **inside** the current substring.

For example:

```text
"bab"
 ↓
check "a"
```

Therefore, smaller substrings must be calculated before larger substrings.

We process:

```text
Length 1
   ↓
Length 2
   ↓
Length 3
   ↓
Length 4
   ↓
...
Length n
```

This is why the outer loop is:

```java
for (int len = 2; len <= n; len++)
```

---

# 🔍 Dry Run

Consider:

```text
s = "babad"
```

For length `2`:

```text
ba → not palindrome
ab → not palindrome
ba → not palindrome
ad → not palindrome
```

For length `3`:

```text
bab → palindrome ✓
aba → palindrome ✓
bad → not palindrome
```

We find:

```text
bab
```

and:

```text
aba
```

Both have length `3`.

Since `"bab"` is found first, it remains the stored answer.

```text
start = 0
maxlen = 3
```

Final result:

```text
"bab"
```

---

# 🧩 Algorithm

1. Create a 2D boolean DP table.
2. Mark every single character as a palindrome.
3. Start checking substrings of length `2`.
4. For each substring `s[i...j]`:

   * Check whether `s[i] == s[j]`.
   * If length is `2`, it is a palindrome.
   * Otherwise, use `dp[i+1][j-1]`.
5. Whenever a longer palindrome is found:

   * Update `start`.
   * Update `maxlen`.
6. Return the substring using `start` and `maxlen`.

---

# 💻 Java Solution

```java
class Solution {
    public String longestPalindrome(String s) {

        int n = s.length();
        boolean[][] dp = new boolean[n][n];

        // Every single character is a palindrome
        for (int i = 0; i < n; i++)
            dp[i][i] = true;

        int maxlen = 1;
        int start = 0;

        // Check substrings by increasing length
        for (int len = 2; len <= n; len++) {

            for (int i = 0; i + len <= n; i++) {

                int j = i + len - 1;

                if (s.charAt(i) == s.charAt(j)) {

                    if (len == 2)
                        dp[i][j] = true;
                    else
                        dp[i][j] = dp[i + 1][j - 1];
                }

                if (dp[i][j] && len > maxlen) {
                    start = i;
                    maxlen = len;
                }
            }
        }

        return s.substring(start, start + maxlen);
    }
}
```

---

# ⚠️ Common Mistakes

### 1. Using `< n` instead of `<= n`

Incorrect:

```java
for (int len = 2; len < n; len++)
```

This never checks a substring of length `n`.

Correct:

```java
for (int len = 2; len <= n; len++)
```

---

### 2. Forgetting the length-2 case

For:

```text
"bb"
```

the inside substring does not exist.

Therefore, we handle it separately:

```java
if (len == 2)
    dp[i][j] = true;
```

when the two characters are equal.

---

### 3. Checking the inside before it is calculated

The transition uses:

```java
dp[i + 1][j - 1]
```

so shorter substrings must be processed first.

That's why we iterate by increasing `len`.

---

### 4. Confusing substring and subsequence

A **substring** must contain consecutive characters.

For:

```text
abcdef
```

`bcd` is a substring.

But `ace` is a subsequence, not a substring.

This problem asks specifically for a **substring**.

---

# 📊 Complexity Analysis

There are approximately `n²` possible `(i, j)` pairs.

For every pair, we perform constant-time operations.

### Time Complexity

```text
O(n²)
```

### Space Complexity

```text
O(n²)
```

because we store a `boolean[n][n]` DP table.

---

# 🧠 Why This Is Dynamic Programming

This problem has:

### 1. State

```text
dp[i][j]
```

### 2. Overlapping subproblems

A smaller substring's palindrome result is reused when determining larger substrings.

### 3. Optimal substructure / recurrence

A larger substring can be determined from its inner substring:

```text
s[i] == s[j]
        +
dp[i+1][j-1]
```

Therefore, the problem can naturally be solved using Dynamic Programming.

---

# 🔄 Top-Down vs Bottom-Up

This problem can be solved using both.

### Top-Down

```text
isPalindrome(i, j)
        ↓
check inside
        ↓
isPalindrome(i+1, j-1)
        ↓
memoize
```

### Bottom-Up — This Solution

```text
Length 1
   ↓
Length 2
   ↓
Length 3
   ↓
Length 4
   ↓
...
```

For this particular problem, **Bottom-Up is easier to reason about** because the dependency `dp[i+1][j-1]` naturally corresponds to a shorter substring.

---

# 🎯 Interview Notes

### Pattern

```text
2D Dynamic Programming
```

### Key Recognition

When you see:

> "Is this substring a palindrome?"

Think:

```text
First character == Last character
             ↓
       Check inside
```

which gives:

```text
dp[i][j] = dp[i+1][j-1]
```

with the appropriate base cases.

### Interview Follow-Up

An interviewer may ask:

> Can you solve this without `O(n²)` space?

Yes. **Expand Around Center** uses:

```text
Time:  O(n²)
Space: O(1)
```

It is generally a better practical solution for this particular problem.

A more advanced solution, **Manacher's Algorithm**, can solve it in `O(n)` time.

---

# 🔗 Related Problems

Practice these to strengthen the same pattern:

1. **647. Palindromic Substrings** — 2D DP / palindrome checking
2. **516. Longest Palindromic Subsequence** — 2D DP
3. **131. Palindrome Partitioning** — DP + Backtracking
4. **132. Palindrome Partitioning II** — DP
5. **680. Valid Palindrome II** — Two Pointers

---

# 📚 Concepts Practiced

* [x] Dynamic Programming
* [x] 2D DP
* [x] Bottom-Up DP
* [x] String DP
* [x] Palindrome Detection
* [x] State Definition
* [x] State Transition
* [x] Base Cases
* [x] Substring Processing
* [x] Complexity Analysis

---

# 👨‍💻 Author

**Nijoy P Jose**

This solution is part of my **Data Structures & Algorithms** placement preparation repository, where I document problem-solving patterns, interview techniques, and Java implementations to strengthen my coding skills.
