# 26. Remove Duplicates from Sorted Array

**Difficulty:** Easy
**Pattern:** Two Pointers · Read Pointer + Write Pointer · In-Place Array Modification
**Related Patterns:** Array Traversal · Sorted Array · Fast & Slow Pointer Concept

🔗 [LeetCode – 26. Remove Duplicates from Sorted Array](https://leetcode.com/problems/remove-duplicates-from-sorted-array/)

---

## ⚡ Quick Revision

| Concept         | Idea                                              |
| --------------- | ------------------------------------------------- |
| Goal            | Remove duplicates from a sorted array in-place    |
| Main Pattern    | Two Pointers                                      |
| Read Pointer    | `i` scans every element                           |
| Write Pointer   | `j` stores the next unique element                |
| Key Observation | Sorted array places duplicates next to each other |
| Condition       | `nums[i] != nums[i - 1]`                          |
| Return          | Number of unique elements `j`                     |
| Time            | `O(n)`                                            |
| Space           | `O(1)`                                            |

### 30-Second Idea

> **Read every element with `i`. Whenever a new value is found, write it at position `j` and move `j`.**

---

# 🧠 Problem in Simple Words

You are given a **sorted array**.

The array can contain duplicate values.

You need to:

1. Keep only **one copy** of every value.
2. Modify the array **in-place**.
3. Return the number of unique elements.

### Example

```text
Input:
[1, 1, 2]

After removing duplicates:

[1, 2, ...]

Return:
2
```

Only the first `2` positions matter because we return `2`.

---

# 🔥 How to Recognize This Problem in a Placement

Look for these clues:

* The array is **sorted**.
* You need to remove duplicates.
* You must modify the array **in-place**.
* Extra array/space is not allowed.
* The question asks for the **number of unique elements**.

### Recognition Trigger

> **Sorted array + remove duplicates + in-place → Think Two Pointers.**

The important clue is that the array is sorted.

Because it is sorted:

```text
[1, 1, 1, 2, 2, 3, 4, 4]
```

Equal values are always together.

So instead of using a `HashSet`, we can simply compare the current element with the previous element.

---

# 🧩 How to Think / Derive the Solution

## Step 1: What does the sorted property give us?

Consider:

```text
[1, 1, 2, 2, 3]
```

All duplicates are adjacent.

Therefore:

```text
nums[i] == nums[i - 1]
```

means the current value is a duplicate.

And:

```text
nums[i] != nums[i - 1]
```

means we found a new unique value.

---

## Step 2: We need two pointers

We need one pointer to **read** the array and another to **write** unique elements.

```text
i → Read pointer
j → Write pointer
```

### `i`

`i` scans through every element.

### `j`

`j` tells us where the next unique element should be placed.

---

## Step 3: What happens when we find a duplicate?

Example:

```text
[1, 1, 2, 2, 3]
    ↑
    i
```

Since:

```text
nums[i] == nums[i-1]
```

the value is already present.

So we do nothing.

Only `i` moves forward.

---

## Step 4: What happens when we find a new value?

Suppose:

```text
[1, 1, 2, 2, 3]
       ↑
       i
```

Here:

```text
nums[i] != nums[i-1]
```

So `2` is a new unique value.

Write it at `nums[j]`.

```text
nums[j] = nums[i]
```

Then:

```text
j++
```

---

# 📊 Visual Explanation

Consider:

```text
nums = [1, 1, 2, 2, 3]
```

Initially:

```text
i = 1
j = 1
```

### `i = 1`

```text
[1, 1, 2, 2, 3]
    ↑
    i
    ↑
    j
```

```text
nums[1] == nums[0]
```

Duplicate → skip.

---

### `i = 2`

```text
[1, 1, 2, 2, 3]
       ↑
       i
    ↑
    j
```

```text
nums[2] != nums[1]
```

New value.

Write:

```text
nums[j] = nums[i]
```

So:

```text
[1, 2, 2, 2, 3]
       ↑
       j
```

Then:

```text
j++
```

---

### `i = 3`

```text
nums[3] == nums[2]
```

Duplicate → skip.

---

### `i = 4`

```text
nums[4] != nums[3]
```

New value.

Write it:

```text
[1, 2, 3, 2, 3]
```

Now:

```text
j = 3
```

Return:

```text
3
```

Therefore, the valid portion is:

```text
[1, 2, 3]
```

---

# 🔁 Algorithm

1. Start `j = 1`.
2. Traverse the array using `i` from `1` to `n - 1`.
3. Compare `nums[i]` with `nums[i - 1]`.
4. If they are different:

   * `nums[j] = nums[i]`
   * Increment `j`.
5. If they are equal:

   * Skip the element.
6. Return `j`.

### Pseudocode

```text
j = 1

for i from 1 to n - 1:

    if nums[i] != nums[i - 1]:

        nums[j] = nums[i]
        j++

return j
```

---

# 💻 My Solution

```c
int removeDuplicates(int* nums, int numsSize) {

    int i = 1, j = 1;

    for (i = 1; i < numsSize; i++) {

        if (nums[i] != nums[i - 1]) {

            nums[j] = nums[i];
            j++;
        }
    }

    return j;
}
```

This solution uses the standard **read pointer + write pointer** technique.

---

# 🧠 Code Explanation

### Initialize the write pointer

```c
int i = 1, j = 1;
```

The first element is automatically unique because there is nothing before it.

So:

```text
j = 1
```

means the next unique element should be written at index `1`.

---

### Read every element

```c
for (i = 1; i < numsSize; i++)
```

`i` checks every element starting from the second element.

---

### Detect a new value

```c
if (nums[i] != nums[i - 1])
```

Because the array is sorted, comparing with the previous element is enough.

If they are different, we found a new unique value.

---

### Write the unique value

```c
nums[j] = nums[i];
```

Store the unique value at the next available position.

---

### Move the write pointer

```c
j++;
```

The next unique element will be written at the next position.

---

### Return the number of unique elements

```c
return j;
```

`j` represents how many unique elements have been stored.

---

# 🔗 Pattern Connection

This problem is strongly connected to your earlier:

### 27. Remove Element

Both use the same fundamental idea:

```text
Read → Decide → Write → Move write pointer
```

### 27. Remove Element

Condition:

```text
if(nums[i] != val)
```

If the element should be kept:

```text
nums[j] = nums[i]
j++
```

### 26. Remove Duplicates

Condition:

```text
if(nums[i] != nums[i-1])
```

If the element is unique:

```text
nums[j] = nums[i]
j++
```

So you can remember:

> **26 and 27 are both Write-Pointer problems. The main difference is the condition for keeping an element.**

---

# ⚔️ Similar Problems / Variations

| Problem                      | Main Idea                                  |
| ---------------------------- | ------------------------------------------ |
| **27. Remove Element**       | Write elements that are not equal to `val` |
| **26. Remove Duplicates**    | Write only new unique values               |
| **283. Move Zeroes**         | Move non-zero elements forward             |
| **80. Remove Duplicates II** | Allow each value at most twice             |
| **88. Merge Sorted Array**   | Two pointers + in-place writing            |

### Pattern progression

```text
27 Remove Element
       ↓
26 Remove Duplicates
       ↓
283 Move Zeroes
       ↓
80 Remove Duplicates II
```

These problems strengthen the same **in-place two-pointer/write-pointer** pattern.

---

# 🚨 Common Mistakes

### 1. Using an extra array

```text
Set / HashSet / new array
```

This can use additional space unnecessarily.

The problem specifically expects an **in-place** solution.

---

### 2. Forgetting that the array is sorted

If you don't use the sorted property, you may try unnecessary approaches such as hashing.

Remember:

```text
Sorted → duplicates are adjacent
```

---

### 3. Moving `j` for duplicates

Wrong idea:

```text
if duplicate:
    j++
```

`j` should move **only when a unique element is found**.

---

### 4. Starting `j` at `0`

The first element is already unique.

Therefore:

```text
j = 1
```

is the natural starting point.

---

### 5. Returning `numsSize`

The question asks for the number of **unique** elements.

Therefore return:

```text
j
```

not the original array length.

---

# ⏱️ Complexity

### Time Complexity

```text
O(n)
```

Every element is visited once.

### Space Complexity

```text
O(1)
```

No additional array or data structure is used.

---

# 🎯 Placement-Level Takeaway

When you see:

```text
Sorted Array
+
Remove Duplicates
+
In-Place
```

immediately think:

```text
Two Pointers
        ↓
Read Pointer + Write Pointer
```

The core template is:

```c
int j = 1;

for (int i = 1; i < n; i++) {

    if (nums[i] != nums[i - 1]) {

        nums[j] = nums[i];
        j++;
    }
}

return j;
```

The most important concept is **not memorizing this exact code**.

Understand why `j` exists:

> `i` explores the array, while `j` builds the valid portion of the array.

---

# ⚡ Final 30-Second Cheat Sheet

```text
Problem:
Remove duplicates from sorted array

Key clue:
Sorted + In-place

Pattern:
Two Pointers

i:
Read / scan pointer

j:
Write pointer

Condition:
nums[i] != nums[i-1]

If unique:
nums[j] = nums[i]
j++

Return:
j

Time:
O(n)

Space:
O(1)
```

### Mental Shortcut

> **Sorted array + remove duplicates → compare with previous → write unique values forward.**

---

> **Nijoy P Jose**
>
> This solution is part of my **Data Structures & Algorithms** placement preparation repository, where I document problem-solving patterns, interview techniques, and Java implementations to strengthen my coding skills.
