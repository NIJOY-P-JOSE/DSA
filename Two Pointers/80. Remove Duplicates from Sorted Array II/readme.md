# 80. Remove Duplicates from Sorted Array II

**Difficulty:** Medium
**Pattern:** Two Pointers · Write Pointer · In-Place Array Modification
**Technique:** Frequency Control using `j - 2`

🔗 [LeetCode – 80. Remove Duplicates from Sorted Array II](https://leetcode.com/problems/remove-duplicates-from-sorted-array-ii/)

---

## ⚡ Quick Revision

| Concept        | Details                                                 |
| -------------- | ------------------------------------------------------- |
| Goal           | Remove duplicates so each element appears at most twice |
| Array Property | Sorted in non-decreasing order                          |
| Main Pattern   | Two Pointers                                            |
| Technique      | Read Pointer + Write Pointer                            |
| Key Idea       | Compare `nums[i]` with `nums[j - 2]`                    |
| Modification   | In-place                                                |
| Extra Space    | `O(1)`                                                  |
| Time           | `O(n)`                                                  |
| Space          | `O(1)`                                                  |

### Core Condition

```java
if (nums[i] != nums[j - 2])
```

Meaning:

> If the current element is different from the element two positions behind the write pointer, it is safe to keep it.

---

## 🧠 Problem in Simple Words

The array is already sorted.

We need to remove duplicates **in-place**, but unlike Problem 26, we are allowed to keep **two copies** of each number.

### Example

```text
Input:
[1,1,1,2,2,3]

Output:
[1,1,2,2,3]
```

The third `1` must be removed because:

```text
1 1 1
```

contains more than two copies.

The result must occupy the beginning of the original array.

---

## 🔥 How to Recognize This Problem in a Placement

Look for these clues:

* The array is **sorted**.
* Duplicates are next to each other.
* The result must be stored **in-place**.
* Extra array space is not allowed.
* Each value has a frequency limit.
* The problem asks for the new valid length.

When you see:

> **Sorted array + remove duplicates + keep at most K copies + in-place**

think:

> **Two Pointers + Write Pointer + Frequency Limit**

For this problem, the frequency limit is **2**.

---

## 🧩 How to Think / Derive the Solution

The important part is understanding why we compare with:

```java
nums[j - 2]
```

rather than memorizing it.

### Step 1 — First two elements are always allowed

We are allowed to keep every number **twice**.

Therefore, the first two elements can always remain.

For:

```text
[1,1,1,2,2,3]
```

we initially consider:

```text
[1,1 | 1,2,2,3]
```

Here:

```text
j = 2
```

`j` represents the position where the next valid element should be written.

---

### Step 2 — What does `j` represent?

Think of the array as:

```text
[ valid elements | elements still being processed ]
                 ↑
                 j
```

Everything before `j` is already part of the final answer.

For example:

```text
[1,1 | 1,2,2,3]
     ↑
     j
```

The valid portion is:

```text
[1,1]
```

---

### Step 3 — Decide whether the current element can be kept

Suppose:

```text
nums[i] = 1
```

The valid portion already contains:

```text
[1,1]
```

We already have two copies.

If we add another `1`, we would get:

```text
[1,1,1]
```

which is not allowed.

So we need a way to detect whether adding the current element would create a **third copy**.

---

## 💡 Why `j - 2`?

This is the key idea.

Since we are allowed **at most two copies**, look at the element **two positions behind the write pointer**:

```java
nums[j - 2]
```

If:

```java
nums[i] == nums[j - 2]
```

then keeping `nums[i]` would make that value occur at least three times in the valid portion.

Therefore, we skip it.

If:

```java
nums[i] != nums[j - 2]
```

then it is safe to write the current element.

---

## 📊 Visual Explanation

Consider:

```text
[1,1,1,2,2,3]
```

Initially:

```text
[1,1 | 1,2,2,3]
     ↑
     j = 2
```

Current element:

```text
nums[i] = 1
```

Compare:

```text
nums[i]     = 1
nums[j-2]   = nums[0] = 1
```

They are equal:

```text
1 == 1
```

So adding this element would create:

```text
[1,1,1]
```

❌ Skip it.

---

### Next element

```text
[1,1 | 1,2,2,3]
```

Current:

```text
nums[i] = 2
```

Compare:

```text
nums[j-2] = nums[0] = 1
```

```text
2 != 1
```

✅ Safe to keep.

Write:

```text
nums[j] = nums[i]
```

Result:

```text
[1,1,2,2,2,3]
```

Now:

```text
j = 3
```

---

### Next `2`

Current:

```text
nums[i] = 2
```

Compare:

```text
nums[j-2] = nums[1] = 1
```

```text
2 != 1
```

✅ Keep it.

Valid portion becomes:

```text
[1,1,2,2]
```

---

### Finally `3`

```text
nums[i] = 3
nums[j-2] = 2
```

```text
3 != 2
```

✅ Keep it.

Final valid portion:

```text
[1,1,2,2,3]
```

Return:

```text
k = 5
```

---

## 🔁 Algorithm

1. Set the write pointer `j = 2`.
2. Start reading from index `2`.
3. For every `nums[i]`:

   * Compare it with `nums[j - 2]`.
   * If they are different:

     * Write `nums[i]` at `nums[j]`.
     * Increment `j`.
   * Otherwise skip the current element.
4. Return `j`.

### Pseudocode

```text
j = 2

for i from 2 to n-1:

    if nums[i] != nums[j-2]:

        nums[j] = nums[i]

        j++

return j
```

---

## 💻 My Solution

```java
class Solution {
    public int removeDuplicates(int[] nums) {

        int i = 0, j = 2;
        int n = nums.length;

        for(i = 2; i < n; i++) {

            if(nums[i] != nums[j - 2]) {

                nums[j] = nums[i];
                j++;
            }
        }

        return j;
    }
}
```

---

## 🧠 Code Explanation

### Read Pointer

```java
i
```

`i` moves through the original array and examines every element.

```text
i → reads
```

---

### Write Pointer

```java
j
```

`j` represents the position where the next valid element should be placed.

```text
j → writes
```

So we have:

```text
i = Read Pointer
j = Write Pointer
```

---

### Start from 2

```java
int j = 2;
```

Why?

Because the problem allows **two copies**.

Therefore, the first two elements are automatically valid.

---

### Main condition

```java
if(nums[i] != nums[j - 2])
```

This is the heart of the solution.

Think:

> **"Would adding this element create a third copy?"**

If `nums[i]` equals `nums[j - 2]`, then yes.

So we skip it.

Otherwise, we keep it.

---

### Write the valid element

```java
nums[j] = nums[i];
j++;
```

The current valid element is placed at the next available position.

---

## 🧠 Pattern Connection

This problem is an extension of:

### 26. Remove Duplicates from Sorted Array

In Problem 26:

```text
Allowed frequency = 1
```

The write-pointer logic keeps only one copy.

Here:

```text
Allowed frequency = 2
```

So we compare two positions behind.

### General Pattern

If the allowed frequency is `k`, the general idea becomes:

```text
Compare current element
with
element k positions behind the write pointer
```

For this problem:

```text
k = 2

nums[i] != nums[j - 2]
```

The important idea is not the number `2`.

The important idea is:

> **Use the write pointer to determine whether the current element would exceed the allowed frequency.**

---

## ⚔️ Similar Problems / Variations

### 26. Remove Duplicates from Sorted Array

Keep each element **once**.

```text
[1,1,2,2,3]

→ [1,2,3]
```

### 80. Remove Duplicates from Sorted Array II

Keep each element **at most twice**.

```text
[1,1,1,2,2,3]

→ [1,1,2,2,3]
```

### General Variation

A similar technique can be used when a sorted array allows each element to appear at most `k` times.

The frequency limit determines how far behind the write pointer we compare.

---

## 🚨 Common Mistakes

### 1. Comparing with `nums[j - 1]`

Using:

```java
nums[i] != nums[j - 1]
```

would not correctly allow two copies.

The important comparison is:

```java
nums[i] != nums[j - 2]
```

because we are checking whether the new element would become the **third occurrence**.

---

### 2. Starting `j` at 0

The first two elements are automatically allowed.

Therefore:

```java
j = 2;
```

---

### 3. Forgetting that the array is sorted

This solution relies heavily on the fact that duplicates are grouped together.

For example:

```text
[1,1,1,2,2,3]
```

The three `1`s are adjacent.

Without the sorted property, this simple `j - 2` comparison would not work.

---

### 4. Creating another array

The problem specifically requires:

```text
O(1) extra space
```

The write-pointer technique modifies the original array.

---

### 5. Thinking `j - 2` is a magic formula

Don't memorize:

```java
nums[i] != nums[j - 2]
```

Remember the reason:

> **Two copies are allowed, so check two positions behind the write pointer. If the values are equal, the current value would become the third copy.**

---

## ⏱️ Complexity

### Time Complexity

```text
O(n)
```

The array is traversed once.

### Space Complexity

```text
O(1)
```

No additional array or data structure is used.

---

## 🎯 Placement-Level Takeaway

This problem is important because it is a small variation of a very common placement pattern.

When you see:

```text
Sorted Array
+
Remove Duplicates
+
Maximum Frequency
+
In-Place
```

think:

```text
Two Pointers
      ↓
Read Pointer + Write Pointer
      ↓
Frequency Control
```

For **at most two copies**:

```java
if (nums[i] != nums[j - 2])
```

The reasoning is:

```text
Two copies are allowed
        ↓
First two elements are safe
        ↓
New element must not equal
the element two positions behind
        ↓
Otherwise it would create a third copy
```

---

## ⚡ Final 30-Second Cheat Sheet

```text
Problem:
Remove duplicates, but keep each value at most twice.

Because array is sorted:
duplicates are adjacent.

First two elements:
always keep.

Pointers:
i → read
j → write

Condition:
nums[i] != nums[j - 2]

If true:
    nums[j] = nums[i]
    j++

Return:
j
```

### Core Pattern

```text
Sorted Array
     ↓
Two Pointers
     ↓
Write Pointer
     ↓
Frequency Control
```

### Complexity

```text
Time  → O(n)
Space → O(1)
```

### Most Important Idea

> **`j - 2` is not a magic number. It comes directly from the rule that each element is allowed to appear at most twice.**

---

> **Nijoy P Jose**
>
> This solution is part of my **Data Structures & Algorithms** placement preparation repository, where I document problem-solving patterns, interview techniques, and Java implementations to strengthen my coding skills.
