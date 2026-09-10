# 1929. Concatenation of Array

> **Pattern:** Array Traversal · Array Construction · Index Mapping
> **Difficulty:** Easy
> **Platform:** LeetCode
> **LeetCode:** https://leetcode.com/problems/concatenation-of-array/

---

## ⚡ Quick Revision

| Concept     | Remember                                         |
| ----------- | ------------------------------------------------ |
| Goal        | Create an array containing `nums` twice          |
| Pattern     | Array Traversal + Index Mapping                  |
| Main Idea   | For every `nums[i]`, place it at `i` and `i + n` |
| Input Size  | `n`                                              |
| Output Size | `2 * n`                                          |
| First Copy  | `ans[i] = nums[i]`                               |
| Second Copy | `ans[i + n] = nums[i]`                           |
| Time        | `O(n)`                                           |
| Space       | `O(n)`                                           |

---

## 🧠 Problem in Simple Words

We are given an array `nums`.

We need to create a new array containing the same array **twice in sequence**.

For example:

```text
nums = [1, 2, 1]

First copy:  [1, 2, 1]
Second copy: [1, 2, 1]

Answer:      [1, 2, 1, 1, 2, 1]
```

The final array has twice the length of the original array.

---

# 🔥 How to Recognize This Problem in a Placement

Look for clues like:

* Create a new array of a specific size
* Copy elements into multiple positions
* Repeat an array
* Concatenate arrays
* Use index relationships such as `i + n`

### Recognition Trigger

> **"The output array is a fixed transformation or repetition of the input array" → Think Array Construction + Index Mapping.**

The main question becomes:

```text
Where should nums[i] go in the new array?
```

Once we know the answer is:

```text
i
and
i + n
```

the solution becomes straightforward.

---

# 🧩 How to Think / Derive the Solution

## Step 1: What should the answer look like?

Suppose:

```text
nums = [1, 3, 2, 1]
```

We need:

```text
ans = [1, 3, 2, 1, 1, 3, 2, 1]
```

So we can visualize the answer as:

```text
ans = [ First nums ][ Second nums ]
```

Each section has length `n`.

```text
Index:

0         n-1 | n        2n-1
──────────────┼──────────────
 First Copy   | Second Copy
```

---

## Step 2: How large should the new array be?

The original array has:

```text
n elements
```

We need two copies:

```text
n + n = 2n
```

Therefore:

```java
int[] ans = new int[2 * n];
```

---

## Step 3: Where should each element go?

For every:

```text
nums[i]
```

we need two copies.

### First copy

The first array starts at index `0`.

Therefore:

```text
ans[i] = nums[i]
```

### Second copy

The second array starts after the first `n` elements.

Therefore:

```text
ans[i + n] = nums[i]
```

This gives us the complete solution.

---

# 📊 Visual Explanation

Consider:

```text
nums = [1, 2, 1]
n = 3
```

Create:

```text
ans = [_, _, _, _, _, _]
```

### i = 0

```text
nums[0] = 1

ans[0] = 1
ans[0 + 3] = 1

ans = [1, _, _, 1, _, _]
```

---

### i = 1

```text
nums[1] = 2

ans[1] = 2
ans[1 + 3] = 2

ans = [1, 2, _, 1, 2, _]
```

---

### i = 2

```text
nums[2] = 1

ans[2] = 1
ans[2 + 3] = 1

ans = [1, 2, 1, 1, 2, 1]
```

Final answer:

```text
[1, 2, 1, 1, 2, 1]
```

---

# 🔁 Algorithm

1. Store the length of `nums` in `n`.
2. Create an array `ans` of size `2 * n`.
3. Traverse `nums` from index `0` to `n - 1`.
4. For every `nums[i]`:

   * Place it at `ans[i]`.
   * Place it again at `ans[i + n]`.
5. Return `ans`.

---

# 💻 My Solution

```java
class Solution {

    public int[] getConcatenation(int[] nums) {

        int n = nums.length;

        int[] ans = new int[2 * n];

        for (int i = 0; i < n; i++) {

            ans[i] = nums[i];
            ans[i + n] = nums[i];
        }

        return ans;
    }
}
```

## Code → What It Does → Why It Works

### 1. Store the Array Length

```java
int n = nums.length;
```

We use `n` to:

```text
Calculate the answer size → 2 * n
Find where the second copy starts → index n
```

---

### 2. Create the Answer Array

```java
int[] ans = new int[2 * n];
```

We need enough space for two complete copies of `nums`.

---

### 3. Traverse the Original Array

```java
for (int i = 0; i < n; i++)
```

We visit every element exactly once.

---

### 4. Fill the First Half

```java
ans[i] = nums[i];
```

This copies:

```text
nums → ans[0 ... n-1]
```

---

### 5. Fill the Second Half

```java
ans[i + n] = nums[i];
```

Since the second copy starts at index `n`:

```text
nums[0] → ans[n]
nums[1] → ans[n + 1]
nums[2] → ans[n + 2]
```

This creates the second copy.

---

# 🧠 Why Does This Work?

For every index `i`:

```text
0 ≤ i < n
```

we perform:

```text
nums[i]
   ↓
ans[i]       → First copy
ans[i + n]   → Second copy
```

The first `n` positions contain:

```text
nums[0], nums[1], ..., nums[n-1]
```

The next `n` positions contain exactly the same elements.

Therefore:

```text
ans = nums + nums
```

---

# 🧠 Pattern Connection

```text
Array Traversal
       ↓
Create New Array
       ↓
Find Output Index
       ↓
Index Mapping
       ↓
Place Each Input Element
```

This is a useful beginner pattern because many array problems can be solved by asking:

> **For the current input index `i`, where does this element belong in the output?**

---

# ⚔️ Similar Problems / Variations

| Problem                                | Difference                                              |
| -------------------------------------- | ------------------------------------------------------- |
| **1920. Build Array from Permutation** | Each output position gets its value using another index |
| **1470. Shuffle the Array**            | Elements are mapped into alternating positions          |
| **1480. Running Sum of 1d Array**      | Each position depends on previous values                |
| **88. Merge Sorted Array**             | Elements from two arrays are placed into one array      |
| **66. Plus One**                       | Creates or modifies an array based on digit positions   |

### Important Pattern Connection

```text
Input Index
     ↓
Find Formula for Output Index
     ↓
Place Element
     ↓
Array Transformation
```

---

# 🚨 Common Mistakes

### 1. Creating an Array of Size `n`

Wrong:

```java
int[] ans = new int[n];
```

We need two copies.

Correct:

```java
int[] ans = new int[2 * n];
```

---

### 2. Forgetting the Offset for the Second Copy

Wrong:

```java
ans[i] = nums[i];
ans[i] = nums[i];
```

This writes to the same position twice.

The second copy must start at:

```text
n
```

So:

```java
ans[i + n] = nums[i];
```

---

### 3. Using `i <= n`

Wrong:

```java
for (int i = 0; i <= n; i++)
```

This tries to access:

```text
nums[n]
```

which is outside the array.

Correct:

```java
i < n
```

---

### 4. Trying to Modify `nums` Directly

The output has size `2n`, but Java arrays have fixed sizes.

So we must create a new array.

---

# ⏱️ Complexity

### Time Complexity

```text
O(n)
```

We visit every element exactly once.

### Space Complexity

```text
O(n)
```

We create an output array of size `2n`.

Technically:

```text
O(2n)
```

but constants are ignored in Big-O notation:

```text
O(2n) = O(n)
```

---

# 🎯 Placement-Level Takeaway

### ⭐ If you see this in a placement round...

```text
Need to create a transformed array
           ↓
Find the output size
           ↓
Ask: Where does each nums[i] go?
           ↓
Derive the index mapping
           ↓
Traverse the input once
           ↓
Place every element in its required position
```

The important skill here is recognizing the relationship:

```text
Input index i
      ↓
Output indices i and i + n
```

---

# ⚡ Final 30-Second Cheat Sheet

```text
╔══════════════════════════════════════════════════╗
║                 QUICK REVISION                   ║
╠══════════════════════════════════════════════════╣
║ Pattern: Array Construction + Index Mapping      ║
║ Input Size: n                                   ║
║ Output Size: 2n                                 ║
║ First Copy: ans[i] = nums[i]                    ║
║ Second Copy: ans[i + n] = nums[i]               ║
║ Recognition: Repeat/map input into output array ║
║ Time: O(n)                                      ║
║ Space: O(n)                                     ║
╚══════════════════════════════════════════════════╝
```

> **⭐ One sentence to remember:** When an output array repeats or rearranges an input array, derive where each `nums[i]` belongs using an index mapping.
