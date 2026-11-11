# 424. Longest Repeating Character Replacement

> **Difficulty:** Medium  
> **Pattern:** Variable-Size Sliding Window + Frequency Array  
> **Related Concepts:** Two Pointers, Character Frequency, Greedy Optimization  
> **Platform:** [LeetCode](https://leetcode.com/problems/longest-repeating-character-replacement/)

---

## ⚡ 1. Quick Revision

| Concept | Remember |
|---|---|
| Goal | Find the longest substring containing the same character |
| Pattern | Variable-Size Sliding Window |
| Data Structure | Frequency Array |
| State | `freq[26]`, `maxFreq`, `left` |
| Window Length | `right - left + 1` |
| Replacements Required | `windowLength - maxFreq` |
| Invalid Condition | `windowLength - maxFreq > k` |
| Time | `O(n)` |
| Auxiliary Space | `O(1)` |

**Core idea:** Keep track of character frequencies in the current window. The minimum replacements required is the window length minus the highest character frequency.

---

## 🧠 2. Problem in Simple Words

You are given a string `s` and an integer `k`.

You can replace at most `k` characters with any uppercase English letter.

Find the length of the longest substring that can be converted into a substring containing only one character.

### Example 1

```text
Input:
s = "ABAB"
k = 2

Replace:
ABAB → BBBB

Output: 4
```

### Example 2

```text
Input:
s = "AABABBA"
k = 1

Replace one A with B:

AABABBA → AABBBBA

Output: 4
```

---

## 🔥 3. How to Recognize This Problem in a Placement

Look for these clues:

- Find the longest substring.
- You can modify at most `k` characters.
- The substring must satisfy a particular condition.
- You need to maintain character frequencies.

### Recognition Trigger

> **Longest substring + at most K replacements + character frequency → Variable-Size Sliding Window.**

Why Sliding Window?

Checking every possible substring would be expensive. Instead, expand the window using `right` and shrink it when the number of required replacements exceeds `k`.

---

## 🧩 4. How to Think / Derive the Solution

### Step 1: Understand the Actual Goal

We need to find the longest substring that can be converted into a string containing only one character using at most `k` replacements.

For example:

```text
A A B B A
```

Frequencies:

```text
A = 3
B = 2
```

We should keep the most frequent character and replace the remaining characters.

### Step 2: Find the Number of Replacements

Suppose:

```text
Window = A A B B A

Window length = 5
A frequency = 3
B frequency = 2
```

The best character to keep is `A`.

We need to replace the two `B` characters.

Therefore:

```text
Replacements = Window Length - Maximum Frequency

             = 5 - 3

             = 2
```

### Step 3: Identify the Window Condition

Let:

```text
windowLength = right - left + 1
```

Let:

```text
maxFreq = Highest character frequency
```

Then:

```text
Replacements Required = windowLength - maxFreq
```

The window is valid when:

```text
windowLength - maxFreq <= k
```

The window becomes invalid when:

```text
windowLength - maxFreq > k
```

### Step 4: Apply Sliding Window

We use two pointers:

- `right` expands the window.
- `left` shrinks the window when the replacement limit is exceeded.

The process:

```text
Expand Window
      ↓
Update Character Frequency
      ↓
Update maxFreq
      ↓
Calculate Required Replacements
      ↓
More than k?
   /       \
 Yes       No
  ↓         ↓
Shrink    Update Maximum
Window    Window Length
```

### Step 5: Why Use a Frequency Array?

The string contains only uppercase English letters.

There are exactly 26 possible characters.

We can maintain their frequencies using:

```java
int[] freq = new int[26];
```

Character index:

```java
s.charAt(i) - 'A'
```

For example:

```text
'A' → 0
'B' → 1
'C' → 2
...
'Z' → 25
```

This gives constant-time frequency updates.

---

## 📊 5. Visual Explanation

Consider:

```text
s = "AABABBA"
k = 1
```

The algorithm expands the window and tracks the highest frequency.

| Window | Length | maxFreq | Replacements | Action |
|---|---:|---:|---:|---|
| A | 1 | 1 | 0 | Valid |
| AA | 2 | 2 | 0 | Valid |
| AAB | 3 | 2 | 1 | Valid |
| AABA | 4 | 3 | 1 | Valid |
| AABAB | 5 | 3 | 2 | Shrink |
| ABAB | 4 | 3 (historical) | 1 | Continue |
| BABBA | 5 | 3 | 2 | Shrink |

The maximum valid window length found is:

```text
4
```

Output:

```text
4
```

**Important:** The `maxFreq` used here is the highest frequency observed during expansion. It is not always the exact maximum frequency of the current window.

---

## 💻 6. Algorithm

1. Create a frequency array of size 26.
2. Initialize `left = 0`, `maxFreq = 0`, and `maxWindow = 0`.
3. Traverse the string using `right`.
4. Increase the frequency of the current character.
5. Update `maxFreq` if the current character frequency is greater.
6. Calculate the current window length.
7. If `windowLength - maxFreq > k`:
   - Decrease the frequency of the leftmost character.
   - Move `left` one position forward.
8. Update the maximum window length.
9. Return `maxWindow`.

---

## 💻 7. My Java Solution

```java
class Solution {
    public int characterReplacement(String s, int k) {
        int[] freq = new int[26];
        int maxWindow = 0;
        int maxFreq = 0;
        int left = 0;

        for(int right = 0; right < s.length(); right++)
        {
            freq[s.charAt(right) - 'A']++;

            maxFreq = Math.max(
                maxFreq,
                freq[s.charAt(right) - 'A']
            );

            int windowLength = right - left + 1;

            if(windowLength - maxFreq > k){
                freq[s.charAt(left) - 'A']--;
                left++;
            }

            windowLength = right - left + 1;
            maxWindow = Math.max(maxWindow, windowLength);
        }

        return maxWindow;
    }
}
```

---

## 🔍 8. Code Explanation

### 1. Initialize the Frequency Array

```java
int[] freq = new int[26];
```

Stores the frequency of each uppercase English letter.

### 2. Initialize the Variables

```java
int maxWindow = 0;
int maxFreq = 0;
int left = 0;
```

| Variable | Purpose |
|---|---|
| `maxWindow` | Stores the longest window found |
| `maxFreq` | Highest frequency observed during expansion |
| `left` | Starting index of the window |
| `right` | Ending index of the window |

### 3. Expand the Window

```java
freq[s.charAt(right) - 'A']++;
```

Add the current character to the window.

### 4. Update Maximum Frequency

```java
maxFreq = Math.max(
    maxFreq,
    freq[s.charAt(right) - 'A']
);
```

Keep track of the highest character frequency observed so far.

### 5. Calculate Window Length

```java
int windowLength = right - left + 1;
```

The current window includes both `left` and `right`.

### 6. Check Whether the Window Is Invalid

```java
if(windowLength - maxFreq > k){
    freq[s.charAt(left) - 'A']--;
    left++;
}
```

If the required replacements exceed `k`, remove the leftmost character and shrink the window.

### 7. Update the Answer

```java
windowLength = right - left + 1;
maxWindow = Math.max(maxWindow, windowLength);
```

Update the maximum window length.

---

## 🧠 9. Why Don't We Decrease maxFreq?

This is the important optimization in this problem.

Normally, when we shrink a window, we might expect to recalculate its maximum frequency.

But we don't do that here.

### Example

Suppose:

```text
Window = A A A B B

maxFreq = 3
```

After removing one `A`:

```text
Window = A A B B

Actual maximum frequency = 2
Stored maxFreq = 3
```

Notice that `maxFreq` is now stale.

**Why is this okay?**

We only need to find the maximum achievable window length, not maintain an exactly valid window at every iteration.

The historical maximum frequency is sufficient to prevent the algorithm from reporting an incorrect maximum length.

Keeping it avoids repeatedly scanning the frequency array.

### Remember

```text
maxFreq never decreases.

It stores the highest frequency observed
during the entire traversal.
```

**Important:** A window can temporarily be technically invalid even when the optimized condition considers it valid. This does not affect the final maximum length.

---

## 🔗 10. Pattern Connection

This problem extends the Variable-Size Sliding Window pattern.

```text
Sliding Window
      ↓
Variable-Size Window
      ↓
Maintain Character Frequency
      ↓
Calculate Required Replacements
      ↓
Shrink When Replacements > k
      ↓
Find Maximum Window
```

### Connection with Previously Solved Problems

| Problem | Main Concept |
|---|---|
| 209. Minimum Size Subarray Sum | Variable window + Sum |
| 713. Subarray Product Less Than K | Variable window + Product |
| 904. Fruit Into Baskets | Variable window + Distinct elements |
| 1004. Max Consecutive Ones III | Variable window + Constraint |
| 424. Longest Repeating Character Replacement | Variable window + Character Frequency |

The key difference is that this problem uses the highest character frequency to calculate how many replacements are needed.

---

## ⚔️ 11. Similar Problems / Variations

| Problem | Difference |
|---|---|
| [3. Longest Substring Without Repeating Characters](https://leetcode.com/problems/longest-substring-without-repeating-characters/) | Finds the longest substring with no duplicate characters |
| [1004. Max Consecutive Ones III](https://leetcode.com/problems/max-consecutive-ones-iii/) | Allows flipping at most `k` zeros |
| [904. Fruit Into Baskets](https://leetcode.com/problems/fruit-into-baskets/) | Allows at most two distinct elements |
| [1438. Longest Continuous Subarray With Absolute Diff Less Than or Equal to Limit](https://leetcode.com/problems/longest-continuous-subarray-with-absolute-diff-less-than-or-equal-to-limit/) | Maintains maximum and minimum values using monotonic deques |

---

## 🚨 12. Common Mistakes

1. **Using the wrong replacement formula**

   Incorrect:

   ```java
   windowLength - k
   ```

   Correct:

   ```java
   windowLength - maxFreq
   ```

2. **Forgetting to update character frequency**

   Every new character must be added to the frequency array.

3. **Shrinking without decreasing the outgoing character frequency**

   ```java
   freq[s.charAt(left) - 'A']--;
   ```

4. **Decreasing `maxFreq` unnecessarily**

   In the optimized approach, `maxFreq` is intentionally not decreased.

5. **Using `>` instead of `>=` incorrectly**

   The invalid condition must be:

   ```java
   windowLength - maxFreq > k
   ```

   Exactly `k` replacements are allowed.

6. **Using an incorrect character index**

   For uppercase English letters:

   ```java
   s.charAt(i) - 'A'
   ```

---

## ⏱️ 13. Complexity Analysis

### Time Complexity: O(n)

- Each character is added to the window once.
- The left pointer only moves forward.
- Frequency updates and comparisons take constant time.

Therefore:

```text
Time = O(n)
```

### Auxiliary Space Complexity: O(1)

The frequency array always contains exactly 26 elements.

```text
Space = O(26) = O(1)
```

---

## 🎯 14. Placement-Level Takeaway

### ⭐ If you see this in a placement round...

```text
Longest Substring
       +
At Most K Replacements
       ↓
Variable-Size Sliding Window
       ↓
Maintain Character Frequencies
       ↓
Find Highest Frequency
       ↓
Replacements = Window Length - maxFreq
       ↓
If Replacements > k, Shrink
       ↓
Update Maximum Length
```

### What should you explain in an interview?

1. Brute force checks every substring and is inefficient.
2. Sliding Window avoids repeatedly processing overlapping substrings.
3. A frequency array tracks character counts.
4. The most frequent character should be kept unchanged.
5. All other characters can be replaced.
6. The replacement count determines whether the window is acceptable.

---

## ⚡ 15. Final 30-Second Cheat Sheet

```text
╔══════════════════════════════════════════╗
║              QUICK REVISION              ║
╠══════════════════════════════════════════╣
║ Pattern: Variable Sliding Window         ║
║ Data: Frequency Array [26]               ║
║ State: left, right, maxFreq              ║
║ Window: right - left + 1                  ║
║ Replacements: Window - maxFreq           ║
║ Invalid: Replacements > k                ║
║ Action: Decrease outgoing frequency      ║
║ maxFreq: Never decreases                 ║
║ Time: O(n)                               ║
║ Space: O(1)                              ║
╚══════════════════════════════════════════╝
```

> **⭐ One sentence to remember:** Keep the most frequent character, replace the remaining characters, and shrink the window whenever the required replacements exceed `k`.

---

## 👨‍💻 Author

**Nijoy P Jose**

This solution is part of my **Data Structures & Algorithms** placement preparation repository, where I document problem-solving patterns, interview techniques, and Java implementations to strengthen my coding skills.
