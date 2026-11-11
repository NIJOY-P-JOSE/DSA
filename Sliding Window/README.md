# Sliding Window — Placement Preparation Guide

> **Topic:** Sliding Window  
> **Core Patterns:** Fixed-Size Window · Variable-Size Window · Frequency Window · Monotonic Deque  
> **Primary Language:** Java  
> **Purpose:** Learn to recognize sliding window problems, derive the approach independently, avoid common traps, and revise efficiently before placement coding rounds.

---

## 1. What Is Sliding Window?

Sliding Window is a technique for processing a **contiguous part** of an array or string.

Instead of recalculating information for every subarray or substring from scratch, maintain a window using two pointers:

- `left`: beginning of the current window.
- `right`: end of the current window.

Move the pointers to expand or shrink the window while maintaining the information needed by the problem.

```text
Array / String:
[a, b, c, d, e, f, g]

       left      right
         ↓         ↓
       [ b, c, d ]

Move right → expand the window
Move left  → shrink the window
```

**Main benefit:** Avoid repeated work between overlapping subarrays or substrings.

---

## 2. The First Question: Is This a Sliding Window Problem?

Before coding, identify these clues.

### Strong recognition signals

- The problem asks about a **subarray** or **substring**.
- The elements must be **contiguous**.
- It asks for the longest, shortest, maximum, minimum, count, or sum of a continuous range.
- A window moves through an array or string.
- Consecutive candidate ranges overlap heavily.
- The condition can be updated when an element enters or leaves the window.

### Recognition trigger

```text
Contiguous subarray / substring
             +
Need information about a moving range
             ↓
       Think Sliding Window
```

### Be careful: not every subarray problem is Sliding Window

Sliding Window is especially useful when adding/removing elements lets you maintain the needed information efficiently.

For example:

- Positive numbers + sum constraint → a variable-size sum window may work.
- Negative numbers can break the usual expand/shrink logic for sum constraints.
- Arbitrary subarray sum equal to a target → Prefix Sum + HashMap is often more appropriate.
- Need maximum of each fixed window → Monotonic Deque, not just a running variable.

**Do not choose Sliding Window only because the statement contains the word “subarray.”** Check whether the window condition behaves predictably.

---

## 3. The Two Main Window Types

### A. Fixed-Size Sliding Window

Use this when the window size is given and must remain exactly `k`.

Examples:
- Maximum sum of `k` consecutive elements.
- Maximum average of a subarray of size `k`.
- Maximum number of vowels in any substring of length `k`.

#### How to derive it

1. Calculate information for the first `k` elements.
2. Store the initial answer.
3. Move the window right by one.
4. Remove the outgoing element at `right - k`.
5. Add the incoming element at `right`.
6. Update the answer.

```text
Previous window: [a, b, c] d e
                         ↓
Next window:     a [b, c, d] e

Remove a
Add d
```

For a sum:

```java
windowSum = windowSum - nums[right - k] + nums[right];
```

**Key trick:** Do not calculate the sum of all `k` elements again. Remove the outgoing value and add the incoming value.

Typical complexity: `O(n)` time and `O(1)` auxiliary space.

### B. Variable-Size Sliding Window

Use this when the window grows and shrinks according to a condition.

Examples:
- Longest substring without repeating characters.
- Minimum-size subarray whose sum reaches a target.
- Longest window with at most two distinct elements.
- Longest window that can be made valid using at most `k` replacements.

#### Standard mental model

```text
Expand using right
        ↓
Update window information
        ↓
Is the window invalid?
     /       \
   Yes        No
    ↓          ↓
Shrink       Update answer
using left
    ↓
Recheck condition
```

Typical template:

```java
int left = 0;
int answer = 0;

for (int right = 0; right < n; right++) {
    // Add nums[right] to the window

    while (windowIsInvalid) {
        // Remove nums[left] from the window
        left++;
    }

    // Update answer using right - left + 1
}
```

Use `while` when the window may need multiple removals before it becomes valid.

---

## 4. The Most Important Sliding Window Templates

### Pattern 1: Fixed Window + Running Sum / Count

**Recognition:** Exact window size `k`, and you need sum, average, count, or another incrementally maintainable value.

```text
First window → calculate once
Next window  → remove outgoing + add incoming
Repeat       → update answer
```

Java update:

```java
windowValue -= outgoing;
windowValue += incoming;
```

Problems in this repository:
- [Maximum Sum of K Consecutive Elements](./Maximum%20Sum%20of%20K%20Consecutive%20Elements/)
- [643. Maximum Average Subarray I](./643.%20Maximum%20Average%20Subarray%20I/)
- [1456. Maximum Number of Vowels in a Substring of Given Length](./1456.%20Maximum%20Number%20of%20Vowels%20in%20a%20Substring%20of%20Given%20Length/)

### Pattern 2: Variable Window + Sum / Product Constraint

**Recognition:** Find the shortest or longest contiguous range satisfying a threshold.

Common flow:

```text
Add right element
       ↓
Update sum / product
       ↓
Condition satisfied or violated?
       ↓
Shrink from left when required
       ↓
Update answer at the correct point
```

Problems:
- [209. Minimum Size Subarray Sum](./209.%20Minimum%20Size%20Subarray%20Sum/)
- [713. Subarray Product Less Than K](./713.%20Subarray%20Product%20Less%20Than%20K/)

**Important limitation:** The classic sum-based expand/shrink method for finding a threshold relies on non-negative (typically positive) array values. Negative values can make the sum increase or decrease unpredictably when the window expands or shrinks. For arbitrary positive-and-negative arrays, consider Prefix Sum, HashMap, or other techniques.

### Pattern 3: Variable Window + HashSet (Unique Elements)

**Recognition:** Longest substring/subarray with no duplicates.

Maintain a set of values currently inside the window.

```java
while (set.contains(nums[right])) {
    set.remove(nums[left]);
    left++;
}

set.add(nums[right]);
answer = Math.max(answer, right - left + 1);
```

Problem:
- [3. Longest Substring Without Repeating Characters](./3.%20Longest%20Substring%20Without%20Repeating%20Characters/)

**Remember:** A duplicate may require removing several elements from the left. That is why the condition uses `while`, not just `if`.

### Pattern 4: Variable Window + Frequency Map

**Recognition:** The condition depends on how often values or characters occur.

Maintain:
- Frequency of each value.
- Number of distinct values, if needed.
- A condition such as at most `K` distinct values or at most `k` replacements.

Problems:
- [904. Fruit Into Baskets](./904.%20Fruit%20Into%20Baskets/)
- [1004. Max Consecutive Ones III](./1004.%20Max%20Consecutive%20Ones%20III/)
- [424. Longest Repeating Character Replacement](./424.%20Longest%20Repeating%20Character%20Replacement/)

Useful formulas:

**At most K distinct values**
```text
while (numberOfDistinctValues > K)
    shrink from left
```

**Character replacement**
```text
replacementsNeeded = windowLength - maxFrequency

Invalid when:
windowLength - maxFrequency > k
```

For LeetCode 424, the optimized solution keeps a historical `maxFreq` that does not decrease when the window shrinks. It may not equal the exact maximum frequency in the current window. This is intentional for the maximum-length algorithm; do not assume every temporarily represented window is exactly valid.

### Pattern 5: Fixed Window + Monotonic Deque

**Recognition:** Find the maximum or minimum in every fixed-size window.

A running sum is easy to update, but a maximum is different: when the current maximum leaves, we need the next maximum efficiently.

Use a deque of **indices**. Keep corresponding values in decreasing order for a maximum (increasing order for a minimum).

For a maximum:
1. Remove expired indices from the front.
2. Remove smaller or equal values from the back.
3. Add the current index at the back.
4. The front index points to the maximum.

Problem:
- [239. Sliding Window Maximum](./239.%20Sliding%20Window%20Maximum/)

Each index enters and leaves the deque at most once, so the time complexity is `O(n)`.

---

## 5. How to Derive a Sliding Window Solution in an Interview

Do not start by writing two pointers immediately. Work through these questions.

### Step 1 — What exactly is the range?

Is it a contiguous subarray or substring? Is the size fixed or flexible?

### Step 2 — What does a valid window mean?

Write the condition in plain English.

Examples:
- No duplicate characters.
- Sum is at least target.
- At most two distinct values.
- At most `k` replacements.
- Exactly `k` elements.

### Step 3 — What information must be maintained?

Choose the smallest useful state:
- Running sum or product.
- Count of special elements.
- HashSet for membership.
- HashMap / frequency array for counts.
- Deque for window maximum/minimum.

### Step 4 — When should the window expand?

Usually, move `right` one step at a time and add the new element to the state.

### Step 5 — When should the window shrink?

Define the invalid condition precisely.

Ask: after removing the leftmost element, how must the state change?

### Step 6 — When should the answer be updated?

This is a frequent source of bugs.

- **Longest valid window:** Usually update after shrinking until valid.
- **Shortest window satisfying a condition:** Often update while the condition is satisfied, before shrinking.
- **Count of valid subarrays ending at right:** When all starts from `left` through `right` are valid, add `right - left + 1`.

### Step 7 — Dry-run the smallest tricky case

Use a duplicate, a boundary, or a window that must shrink multiple times.

---

## 6. Choosing the Correct Data Structure

| Need | Useful structure | Typical operation |
|---|---|---|
| Maintain sum / product | Variable | Add and subtract / divide |
| Count fixed property | Integer | Increment / decrement |
| Check whether value exists | HashSet | `contains`, `add`, `remove` |
| Store frequencies | HashMap / array | Increment / decrement count |
| Track at most K distinct | HashMap | Track count and remove zero-count keys |
| Maximum in every window | Monotonic Deque | Remove expired / dominated indices |
| Minimum in every window | Increasing Monotonic Deque | Maintain smallest candidate at front |

### HashSet vs HashMap vs ArrayList

- **HashSet:** Use when you only need to know whether an element exists.
- **HashMap:** Use when you need frequency or other information about a value.
- **Frequency array:** Often simpler and faster when the value range is small and known (for example, 26 uppercase letters).
- **ArrayList:** `contains()` is linear, and removing index `0` shifts remaining elements. It is usually not the right choice for fast membership/removal in a sliding window.

Hash-based collection operations are generally **O(1) average**, not guaranteed O(1) worst-case.

---

## 7. Traps and Common Mistakes

### 1. Confusing Substring with Subsequence

A substring is continuous. A subsequence does not have to be continuous.

Sliding Window normally works with contiguous ranges.

### 2. Using `if` Instead of `while`

If multiple elements must be removed to restore validity, one removal is not enough.

Use:
```java
while (windowIsInvalid) {
    // remove nums[left]
    left++;
}
```

### 3. Forgetting to Update the State When Shrinking

Whenever `left` moves, remove the outgoing element from the sum, product, set, or frequency structure as appropriate.

### 4. Off-by-One Window Length

Both pointers are included:

```java
int windowLength = right - left + 1;
```

A fixed window of size `k` is complete when:

```java
right >= k - 1
```

### 5. Updating the Answer at the Wrong Time

For longest-valid-window problems, update after restoring validity. For shortest-valid-window problems, record the candidate while the requirement is satisfied and then shrink.

### 6. Assuming Every Window Condition Is Monotonic

The usual sum-based technique is not automatically correct with negative numbers. Check the input constraints.

### 7. Counting All Valid Windows Incorrectly

For problems such as Subarray Product Less Than K, once the window ending at `right` is valid, every suffix ending at `right` that starts from `left` through `right` is also valid. Add:

```java
count += right - left + 1;
```

This relies on the relevant positivity/monotonicity property.

### 8. Forgetting Duplicates in a Set-Based Window

When a duplicate is found, remove from the left until the old occurrence is removed. Only then add the new occurrence.

### 9. Monotonic Deque: Storing Values Instead of Indices

Store indices so you can detect when an element has expired from the window. Remove expired indices from the front and dominated candidates from the back.

### 10. Integer Overflow

If maintaining a sum or product, check constraints and choose an appropriate type. Java `int` may not be sufficient for large sums or products.

### 11. Empty Input and Boundary Cases

Always check:
- Empty string / array if allowed.
- `k = 1`.
- `k = n`.
- All values equal.
- All values different.
- Duplicate at the current left boundary.
- Window shrinks multiple times.
- No valid window exists.

---

## 8. Complexity: What to Expect

| Approach | Typical time | Auxiliary space |
|---|---:|---:|
| Fixed window with running sum/count | `O(n)` | `O(1)` |
| Variable window with simple state | `O(n)` | `O(1)` |
| Variable window with HashSet / HashMap | `O(n)` average | `O(k)` or bounded by distinct values |
| Frequency array with fixed alphabet | `O(n)` | `O(1)` |
| Monotonic deque | `O(n)` | `O(k)` |

Why can a loop containing a `while` still be O(n)?

Because both pointers only move forward. Each element enters the window once and leaves at most once. Across the whole algorithm, the total number of pointer movements is linear.

Do not simply count nested loops. Count how many times each element can be added and removed.

---

## 9. Repository Practice Map

These are the problem folders currently present in this directory.

| Problem | Main pattern / concept |
|---|---|
| [Maximum Sum of K Consecutive Elements](./Maximum%20Sum%20of%20K%20Consecutive%20Elements/) | Fixed window + running sum |
| [643. Maximum Average Subarray I](./643.%20Maximum%20Average%20Subarray%20I/) | Fixed window + running sum |
| [1456. Maximum Number of Vowels in a Substring of Given Length](./1456.%20Maximum%20Number%20of%20Vowels%20in%20a%20Substring%20of%20Given%20Length/) | Fixed window + count |
| [209. Minimum Size Subarray Sum](./209.%20Minimum%20Size%20Subarray%20Sum/) | Variable window + sum threshold |
| [219. Contains Duplicate II](./219.%20Contains%20Duplicate%20II/) | Bounded window + HashSet |
| [713. Subarray Product Less Than K](./713.%20Subarray%20Product%20Less%20Than%20K/) | Variable window + product + counting |
| [904. Fruit Into Baskets](./904.%20Fruit%20Into%20Baskets/) | At most K distinct values |
| [1004. Max Consecutive Ones III](./1004.%20Max%20Consecutive%20Ones%20III/) | Variable window + allowed violations |
| [3. Longest Substring Without Repeating Characters](./3.%20Longest%20Substring%20Without%20Repeating%20Characters/) | Variable window + HashSet |
| [424. Longest Repeating Character Replacement](./424.%20Longest%20Repeating%20Character%20Replacement/) | Variable window + frequency array |
| [239. Sliding Window Maximum](./239.%20Sliding%20Window%20Maximum/) | Fixed window + monotonic deque |

### Recommended next practice

- [567. Permutation in String](https://leetcode.com/problems/permutation-in-string/) — Fixed-size window + character frequency matching.
- [438. Find All Anagrams in a String](https://leetcode.com/problems/find-all-anagrams-in-a-string/) — Fixed-size window + frequency matching; closely related to 567.

These two reinforce frequency matching in fixed-size string windows.

---

## 10. Placement Interview Checklist

Before submitting a Sliding Window solution, ask:

- [ ] Is the required range contiguous?
- [ ] Is the window fixed-size or variable-size?
- [ ] What exactly makes a window valid or invalid?
- [ ] What information must I maintain?
- [ ] Have I updated that information when adding the right element?
- [ ] Have I removed the outgoing element correctly when moving left?
- [ ] Should I use `if` or `while` to shrink?
- [ ] Am I updating the answer at the correct point?
- [ ] Do constraints allow the usual expand/shrink logic?
- [ ] Have I checked duplicates, boundaries, and empty inputs?
- [ ] Can I explain why the total pointer movement is O(n)?

### How to explain the approach aloud

1. "The problem asks about a contiguous range, so Sliding Window may apply."
2. "I will maintain a window using left and right pointers."
3. "I need to track ______ to determine whether the window is valid."
4. "When the condition becomes invalid, I will shrink from the left."
5. "Once the window is valid, I will update the answer."
6. "Each pointer moves forward at most n times, so the time complexity is O(n)."

---

## ⚡ 11. Final 30-Second Cheat Sheet

```text
╔════════════════════════════════════════════╗
║          SLIDING WINDOW REVISION           ║
╠════════════════════════════════════════════╣
║ First check: Is the range contiguous?      ║
║ Fixed size: Remove outgoing + add incoming║
║ Variable: Expand right, shrink left       ║
║ State: Sum / Count / Set / Map / Deque    ║
║ Longest valid: Shrink until valid, update  ║
║ Shortest valid: Update while condition met║
║ Unique chars: HashSet                     ║
║ Frequencies: HashMap / frequency array    ║
║ Window maximum: Monotonic Deque           ║
║ Length: right - left + 1                  ║
║ Fixed window ready: right >= k - 1        ║
║ Complexity: Often O(n), prove it          ║
║ Trap: Negative numbers can break sum logic║
╚════════════════════════════════════════════╝
```

> **One sentence to remember:** Sliding Window works by maintaining information about a contiguous range, expanding it with `right`, shrinking it with `left`, and avoiding repeated calculations.

---

## 👨‍💻 Author

**Nijoy P Jose**

This guide is part of my **Data Structures & Algorithms** placement preparation repository, where I document problem-solving patterns, interview techniques, and Java implementations to strengthen my coding skills.
