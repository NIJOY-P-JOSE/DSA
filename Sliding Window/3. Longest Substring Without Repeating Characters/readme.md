
# 3. Longest Substring Without Repeating Characters

> **Difficulty:** Medium  
> **Pattern:** Variable-Size Sliding Window + HashSet  
> **Related Concepts:** Two Pointers, Hashing, String  
> **Platform:** [LeetCode](https://leetcode.com/problems/longest-substring-without-repeating-characters/)

---

## ⚡ 1. Quick Revision

| Concept | Remember |
|---|---|
| Goal | Find the longest substring without duplicate characters |
| Pattern | Variable-Size Sliding Window |
| Data Structure | HashSet |
| Window | `left` to `right` |
| Expand | Move `right` forward |
| Shrink | Move `left` forward |
| Condition | Current character already exists in the HashSet |
| Window Length | `right - left + 1` |
| Time | `O(n)` average |
| Space | `O(min(n, charset size))` |

**Core idea:** Expand the window while characters are unique. When a duplicate appears, remove characters from the left until the duplicate disappears.

---

## 🧠 2. Problem in Simple Words

You are given a string `s`.

Find the length of the longest continuous substring that contains no repeated characters.

A substring must contain consecutive characters.

### Example 1

```text
Input: s = "abcabcbb"

Possible longest substrings:
"abc", "bca", "cab"

Output: 3
```

### Example 2

```text
Input: s = "bbbbb"

Longest substring = "b"

Output: 1
```

### Example 3

```text
Input: s = "pwwkew"

Longest substring = "wke"

Output: 3
```

---

## 🔥 3. How to Recognize This Problem in a Placement

Look for these clues:

- Find the longest substring.
- No character should repeat.
- The substring must be continuous.
- You need to maintain a window satisfying a condition.

### Recognition Trigger

> **Longest substring + no repeating characters → Variable-Size Sliding Window + HashSet.**

Why not check every substring?

Checking all possible substrings and verifying uniqueness would require unnecessary repeated work.

Instead, maintain a window containing only unique characters.

---

## 🧩 4. How to Think / Derive the Solution

### Step 1: Understand the Requirement

We need the longest substring in which every character appears only once.

For example:

```text
s = "abcabcbb"
```

Initially:

```text
Window = [a, b, c]
```

All characters are unique, so the window is valid.

When another `a` arrives:

```text
[a, b, c, a]
```

The window becomes invalid because `a` appears twice.

### Step 2: Identify the Sliding Window

Use two pointers:

- `left` represents the starting index.
- `right` represents the ending index.

```text
left                    right
 ↓                         ↓
[a, b, c]
```

The window contains all characters between `left` and `right`.

### Step 3: How Do We Detect Duplicates?

Use a HashSet.

```java
HashSet<Character> set = new HashSet<>();
```

A HashSet stores unique elements and provides average O(1) lookup.

Before adding a character, check:

```java
set.contains(s.charAt(right))
```

If it already exists, we have found a duplicate.

### Step 4: How Do We Remove the Duplicate?

Suppose:

```text
s = "abcabcbb"
```

Current window:

```text
[a, b, c]
```

The next character is `a`.

Since `a` already exists, remove characters from the left.

```text
Before:

[a, b, c]
 ↑
left

Remove a:

[b, c]
    ↑
```

Now the duplicate `a` is gone from the window.

We can add the new `a`.

```text
[b, c, a]
```

The window is valid again.

### Step 5: When Do We Update the Answer?

After ensuring that the window contains unique characters:

```java
maxl = Math.max(maxl, right - left + 1);
```

This calculates the current window length and updates the maximum.

---

## 📊 5. Visual Explanation

Consider:

```text
s = "abcabcbb"
```

| Right | Character | Current Window | Maximum Length |
|---:|:---:|---|---:|
| 0 | a | a | 1 |
| 1 | b | ab | 2 |
| 2 | c | abc | 3 |
| 3 | a | bca | 3 |
| 4 | b | cab | 3 |
| 5 | c | abc | 3 |
| 6 | b | cb | 3 |
| 7 | b | b | 3 |

### Important Iteration

At `right = 3`, the current character is `a`.

Before processing:

```text
Window = [a, b, c]
Set = {a, b, c}
```

Since `a` already exists:

1. Remove `a` from the left.
2. Move `left` forward.
3. Add the new `a`.

After processing:

```text
Window = [b, c, a]
Set = {b, c, a}
```

The window is valid again.

Final output:

```text
3
```

---

## 💻 6. Algorithm

1. Create a HashSet to store unique characters.
2. Initialize `left = 0` and `maxl = 0`.
3. Traverse the string using `right`.
4. Check whether the current character already exists in the HashSet.
5. If it exists:
   - Remove the character at `left` from the HashSet.
   - Move `left` forward.
   - Repeat until the duplicate disappears.
6. Add the current character to the HashSet.
7. Calculate the window length using `right - left + 1`.
8. Update `maxl`.
9. Return `maxl`.

---

## 💻 7. My Java Solution

```java
import java.util.*;

class Solution {
    public int lengthOfLongestSubstring(String s) {

        HashSet<Character> set = new HashSet<>();
        int left = 0, maxl = 0;

        for(int right = 0; right < s.length(); right++)
        {
            // Remove characters until the duplicate disappears
            while(set.contains(s.charAt(right)))
            {
                set.remove(s.charAt(left));
                left++;
            }

            // Add the current character
            set.add(s.charAt(right));

            // Update the maximum substring length
            maxl = Math.max(maxl, right - left + 1);
        }

        return maxl;
    }
}
```

---

## 🔍 8. Code Explanation

### 1. Initialize the HashSet

```java
HashSet<Character> set = new HashSet<>();
```

Stores the characters currently present in the window.

A HashSet is useful because it provides average O(1) lookup, insertion, and removal.

### 2. Initialize the Pointers

```java
int left = 0, maxl = 0;
```

- `left`: Starting index of the current window.
- `maxl`: Longest valid substring length found.

### 3. Expand the Window

```java
for(int right = 0; right < s.length(); right++)
```

Move `right` forward to include new characters.

### 4. Check for Duplicates

```java
while(set.contains(s.charAt(right)))
```

If the current character already exists, the window contains a duplicate.

We must shrink the window before adding the current character.

### 5. Shrink the Window

```java
set.remove(s.charAt(left));
left++;
```

Remove the leftmost character and move `left` forward.

The `while` loop continues until the duplicate disappears.

### 6. Add the Current Character

```java
set.add(s.charAt(right));
```

Now the current character can safely be added.

### 7. Update the Maximum Length

```java
maxl = Math.max(maxl, right - left + 1);
```

Calculate the length of the current valid window and update the answer.

---

## 🔗 9. Pattern Connection

This problem is a fundamental example of Variable-Size Sliding Window.

```text
Sliding Window
      ↓
Variable-Size Window
      ↓
Maintain Unique Elements
      ↓
HashSet for Duplicate Detection
      ↓
Shrink Until Window Is Valid
      ↓
Update Maximum Length
```

### Connection with Previously Solved Problems

| Problem | Main Concept |
|---|---|
| 209. Minimum Size Subarray Sum | Variable window + Sum |
| 904. Fruit Into Baskets | Variable window + At Most 2 Distinct |
| 1004. Max Consecutive Ones III | Variable window + Constraint |
| 424. Longest Repeating Character Replacement | Variable window + Frequency Array |
| 3. Longest Substring Without Repeating Characters | Variable window + Unique Characters |

**Important distinction:**

- `HashSet`: Useful when you only need to know whether an element exists.
- `HashMap`: Useful when you need additional information such as the last index of a character.

---

## ⚔️ 10. Similar Problems / Variations

| Problem | Difference |
|---|---|
| [424. Longest Repeating Character Replacement](https://leetcode.com/problems/longest-repeating-character-replacement/) | Allows at most `k` replacements to make all characters identical |
| [567. Permutation in String](https://leetcode.com/problems/permutation-in-string/) | Checks whether a permutation exists inside a substring |
| [438. Find All Anagrams in a String](https://leetcode.com/problems/find-all-anagrams-in-a-string/) | Finds every substring matching an anagram |
| [904. Fruit Into Baskets](https://leetcode.com/problems/fruit-into-baskets/) | Finds the longest window with at most two distinct elements |

---

## 🚨 11. Common Mistakes

### 1. Using `if` Instead of `while`

Incorrect:

```java
if(set.contains(s.charAt(right)))
```

Correct:

```java
while(set.contains(s.charAt(right)))
```

Why?

A duplicate may require removing multiple characters from the left before it disappears.

### 2. Forgetting to Remove Characters

When moving `left`, always remove the outgoing character from the HashSet.

Otherwise, the set will contain characters that are no longer inside the window.

### 3. Adding the Current Character Too Early

First remove the duplicate, then add the current character.

### 4. Incorrect Window Length

Correct:

```java
right - left + 1
```

Remember that both pointers are inclusive.

### 5. Confusing Substring and Subsequence

A substring must be continuous.

For example:

```text
s = "pwwkew"

"wke" → Substring
"pwke" → Subsequence, not substring
```

---

## ⏱️ 12. Complexity Analysis

### Time Complexity: O(n) Average

Although there is a nested `while` loop:

- Each character is added to the HashSet at most once.
- Each character is removed at most once.
- Both pointers only move forward.
- HashSet operations take O(1) average time.

Therefore:

```text
Time = O(n) average
```

### Space Complexity: O(min(n, charset size))

The HashSet stores only the unique characters in the current window.

```text
Space = O(min(n, charset size))
```

The input can contain English letters, digits, symbols, and spaces.

---

## 🎯 13. Placement-Level Takeaway

### ⭐ If you see this in a placement round...

```text
Longest Substring
        +
No Duplicate Characters
        ↓
Variable-Size Sliding Window
        ↓
Need Fast Duplicate Detection
        ↓
Use HashSet
        ↓
Expand Using right
        ↓
Duplicate Found?
   /          \
 Yes           No
  ↓             ↓
Remove from     Add Character
left            to HashSet
  ↓             ↓
Move left       Update Maximum
Forward         Window Length
```

### What should you explain in an interview?

1. Brute force checks multiple substrings and repeatedly verifies uniqueness.
2. We can avoid repeated work using a sliding window.
3. A HashSet tracks characters currently in the window.
4. When a duplicate appears, shrink the window from the left until it disappears.
5. Each character is added and removed at most once, giving O(n) average time.

---

## ⚡ 14. Final 30-Second Cheat Sheet

```text
╔══════════════════════════════════════════╗
║              QUICK REVISION              ║
╠══════════════════════════════════════════╣
║ Pattern: Variable Sliding Window         ║
║ Data Structure: HashSet                  ║
║ State: left, right, maxl                  ║
║ Expand: Move right                       ║
║ Duplicate: set.contains(current char)    ║
║ Shrink: Remove from left until unique    ║
║ Add: set.add(current char)               ║
║ Window: right - left + 1                  ║
║ Time: O(n) average                       ║
║ Space: O(min(n, charset size))            ║
╚══════════════════════════════════════════╝
```

> **⭐ One sentence to remember:** Expand the window while characters are unique; when a duplicate appears, remove characters from the left until the window becomes unique again.

---

## 👨‍💻 Author

**Nijoy P Jose**

This solution is part of my **Data Structures & Algorithms** placement preparation repository, where I document problem-solving patterns, interview techniques, and Java implementations to strengthen my coding skills.
