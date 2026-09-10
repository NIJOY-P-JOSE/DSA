# 27. Remove Element

> **Pattern:** Array Traversal · Two Pointers / Write Pointer · In-Place Modification
> **Difficulty:** Easy
> **Platform:** LeetCode
> **LeetCode:** https://leetcode.com/problems/remove-element/

---

## ⚡ Quick Revision

| Concept       | Remember                                                |
| ------------- | ------------------------------------------------------- |
| Goal          | Remove all occurrences of `val` in-place                |
| Pattern       | Read Pointer + Write Pointer                            |
| Main Idea     | Keep non-`val` elements at the beginning                |
| Read Pointer  | Checks every element                                    |
| Write Pointer | Points to where the next valid element should be placed |
| Key Condition | `nums[i] != val`                                        |
| Time          | `O(n)`                                                  |
| Space         | `O(1)`                                                  |

---

## 🧠 Problem in Simple Words

We are given an array and a value `val`.

We need to remove every occurrence of `val` from the array **without creating another array**.

The important requirement is:

```text
After removing val:

First k positions → elements we want to keep
Remaining positions → don't care
Return → k
```

### Example

```text
nums = [3, 2, 2, 3]
val = 3
```

We want to keep:

```text
[2, 2]
```

So:

```text
k = 2
```

The array can finally look like:

```text
[2, 2, _, _]
```

The values after index `k - 1` do not matter.

---

# 🔥 How to Recognize This Problem in a Placement

Look for clues like:

* Remove elements from an array
* Modify the array in-place
* Move valid elements to the beginning
* Return the number of remaining elements
* Extra array is not allowed or should be avoided

### Recognition Trigger

> **"Scan the array, keep only valid elements, and modify the same array" → Think Read Pointer + Write Pointer.**

---

## ⚠️ What Can Confuse You?

You might initially think:

```text
Remove element
→ shift all elements left
```

But repeatedly shifting elements is unnecessary.

For example:

```text
[1, 2, 3, 2, 4]
```

If we remove every `2`, we do not need to shift the array every time.

Instead:

```text
Read every element
        ↓
Is it valid?
        ↓
Yes → write it at the next available position
No  → ignore it
```

This is much simpler.

---

# 🧩 How to Think / Derive the Solution

## Step 1: What is the actual goal?

We only care that all elements which are **not equal to `val`** appear in the first `k` positions.

For example:

```text
nums = [0, 1, 2, 2, 3, 0, 4, 2]
val = 2
```

Valid elements are:

```text
0, 1, 3, 0, 4
```

We need to place them at:

```text
nums[0 ... k-1]
```

---

## Step 2: Do we need another array?

No.

The problem explicitly asks us to modify the array **in-place**.

So instead of:

```text
newArray = []
```

we can reuse the original array.

---

## Step 3: What information do we need?

We need two positions.

### Read Pointer

```text
i
```

This moves through every element.

### Write Pointer

```text
index
```

This tells us where the next valid element should be placed.

---

## Step 4: What decision do we make for each element?

For every element:

```text
nums[i]
```

Ask:

```text
Is nums[i] equal to val?
```

### If Yes

Ignore it.

```text
nums[i] == val
        ↓
Do nothing
```

### If No

Keep it.

```text
nums[i] != val
        ↓
nums[index] = nums[i]
index++
```

---

# 📊 Visual Explanation

Consider:

```text
nums = [3, 2, 2, 3]
val = 3
```

Initially:

```text
index = 0

[3, 2, 2, 3]
 ↑
 i
```

### i = 0

```text
nums[i] = 3
```

It equals `val`.

```text
Ignore it.
index stays 0.
```

---

### i = 1

```text
nums[i] = 2
```

It is valid.

```text
nums[index] = nums[i]

[2, 2, 2, 3]
 ↑
index

index++
```

Now:

```text
index = 1
```

---

### i = 2

```text
nums[i] = 2
```

Valid again.

```text
nums[index] = nums[i]

[2, 2, 2, 3]

index++
```

Now:

```text
index = 2
```

---

### i = 3

```text
nums[i] = 3
```

It equals `val`.

Ignore it.

Final important portion:

```text
[2, 2, _, _]

k = index = 2
```

---

# 🔁 Algorithm

1. Initialize `index = 0`.
2. Traverse every element in `nums`.
3. If the current element is not equal to `val`:

   * Copy it to `nums[index]`.
   * Increment `index`.
4. Return `index`.

---

# 💻 My Solution

```java
class Solution { 
    public int removeElement(int[] nums, int val) { 
        
        int index = 0; 
        
        for (int i = 0; i < nums.length; i++) {
            
            if (nums[i] != val) {
                nums[index] = nums[i];
                index++;
            }
        }
        
        return index;
    } 
}
```

## Code → What It Does → Why It Works

### 1. Write Pointer

```java
int index = 0;
```

`index` represents:

```text
The position where the next valid element should go.
```

It also eventually becomes:

```text
Number of elements not equal to val
```

---

### 2. Traverse the Entire Array

```java
for (int i = 0; i < nums.length; i++)
```

`i` reads every element.

```text
i → Read Pointer
```

---

### 3. Check Whether the Element Should Be Kept

```java
if (nums[i] != val)
```

If it is not equal to `val`, it belongs in the final answer.

---

### 4. Place It at the Correct Position

```java
nums[index] = nums[i];
```

We overwrite positions from left to right with valid elements.

---

### 5. Move the Write Pointer

```java
index++;
```

The next valid element will go into the next position.

---

### 6. Return the Number of Valid Elements

```java
return index;
```

Since `index` increases exactly once for every valid element:

```text
index = number of elements != val
```

---

# 🧠 Why Does This Work?

The key invariant is:

> **At any moment, all elements before `index` are valid elements that should remain in the array.**

```text
0 ........ index-1
↓
All valid elements
```

Every time we find another valid element:

```text
Read it
   ↓
Copy it to index
   ↓
Move index
```

Therefore, when traversal finishes:

```text
nums[0 ... index-1]
```

contains exactly all elements that are not equal to `val`.

---

# 🧠 Pattern Connection

```text
Array Traversal
       ↓
Filter Elements
       ↓
Read + Write Pointer
       ↓
In-Place Modification
       ↓
Remove Element
```

This pattern is useful whenever we need to:

```text
Read every element
        ↓
Decide keep / discard
        ↓
Move kept elements to the front
```

---

# ⚔️ Similar Problems / Variations

| Problem                                        | Difference                                                |
| ---------------------------------------------- | --------------------------------------------------------- |
| **26. Remove Duplicates from Sorted Array**    | Keep unique elements instead of removing a specific value |
| **283. Move Zeroes**                           | Keep non-zero elements first, then place zeroes afterward |
| **88. Merge Sorted Array**                     | Uses pointers to modify an array in-place                 |
| **977. Squares of a Sorted Array**             | Uses two pointers from both ends                          |
| **80. Remove Duplicates from Sorted Array II** | Allows each value to appear at most twice                 |

### Important Connection

```text
Remove Element
      ↓
Keep elements satisfying a condition
      ↓
Write them using index
      ↓
Two-Pointer / Write-Pointer Pattern
```

---

# 🚨 Common Mistakes

### 1. Removing Elements Directly

Trying to physically remove elements from a Java array.

```text
Arrays have fixed size.
```

Instead, overwrite unwanted positions and return `k`.

---

### 2. Forgetting In-Place Modification

Creating another array works logically but does not follow the intended pattern.

---

### 3. Incrementing `index` for Every Element

Wrong:

```java
nums[index] = nums[i];
index++;
```

This should happen **only when**:

```java
nums[i] != val
```

---

### 4. Returning `nums.length`

The answer is not the original array length.

It is:

```text
Number of elements not equal to val
```

---

### 5. Trying to Care About Elements After `k`

You do not need to clean them.

For example:

```text
[2, 2, 3, 3]
```

with:

```text
k = 2
```

is accepted because only:

```text
[2, 2]
```

is checked.

---

# ⏱️ Complexity

### Time Complexity

```text
O(n)
```

We visit every array element exactly once.

### Space Complexity

```text
O(1)
```

We only use a few variables:

```text
i
index
```

No extra array is created.

---

# 🎯 Placement-Level Takeaway

### ⭐ If you see this in a placement round...

```text
Array problem
      ↓
Need to remove/filter elements?
      ↓
Need to modify in-place?
      ↓
Use one pointer to read every element
      ↓
Use another pointer to track where valid elements go
      ↓
Copy valid elements forward
      ↓
Return the write pointer
```

### Interview Thinking

The important idea is not:

> "I memorized Remove Element."

Instead remember:

> **"When I need to filter an array in-place, I can scan with one pointer and compact valid elements using another pointer."**

---

# ⚡ Final 30-Second Cheat Sheet

```text
╔══════════════════════════════════════════════════╗
║                 QUICK REVISION                   ║
╠══════════════════════════════════════════════════╣
║ Pattern: Read Pointer + Write Pointer            ║
║ Goal: Remove val in-place                       ║
║ Read: Check every nums[i]                       ║
║ Write: index = next valid position              ║
║ Keep if: nums[i] != val                         ║
║ Action: nums[index] = nums[i]                   ║
║ Then: index++                                   ║
║ Answer: return index                            ║
║ Time: O(n)                                      ║
║ Space: O(1)                                     ║
╚══════════════════════════════════════════════════╝
```

> **⭐ One sentence to remember:** Scan every element and compact only the elements you want to keep at the beginning of the same array.
