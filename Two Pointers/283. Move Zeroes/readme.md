# 283. Move Zeroes

**Difficulty:** Easy
**Pattern:** Two Pointers · Write Pointer · In-Place Array Modification
**Related Patterns:** Array Traversal · Stable Rearrangement · Partitioning

🔗 [LeetCode – 283. Move Zeroes](https://leetcode.com/problems/move-zeroes/)

---

## ⚡ Quick Revision

| Concept       | Idea                                 |
| ------------- | ------------------------------------ |
| Goal          | Move all `0`s to the end             |
| Main Pattern  | Two Pointers / Write Pointer         |
| Read Pointer  | `i` scans every element              |
| Write Pointer | `j` stores the next non-zero element |
| Key Operation | Copy non-zero elements forward       |
| Final Step    | Fill remaining positions with `0`    |
| In-place      | Yes                                  |
| Time          | `O(n)`                               |
| Space         | `O(1)`                               |

### 30-Second Idea

> **First move all non-zero elements to the front using a write pointer, then fill the remaining positions with zeroes.**

---

# 🧠 Problem in Simple Words

You are given an array containing numbers and zeroes.

Move **all zeroes to the end** of the array.

The important requirements are:

* Modify the array **in-place**.
* Keep the **relative order of non-zero elements** unchanged.

### Example

```text
Input:
[0, 1, 0, 3, 12]

Output:
[1, 3, 12, 0, 0]
```

The non-zero values remain in their original order:

```text
1 → 3 → 12
```

---

# 🔥 How to Recognize This Problem in a Placement

Look for:

* Move a particular value to one side.
* Keep the remaining elements in their original order.
* In-place modification.
* No extra array should be required.
* One category of elements should be grouped together.

### Recognition Trigger

> **Move unwanted/special elements aside while preserving the order of useful elements → Think Write Pointer.**

For this problem:

```text
Useful elements = non-zero values
Special elements = zeroes
```

So instead of directly moving every zero, we can:

```text
1. Collect non-zero elements at the front.
2. Fill the remaining positions with zeroes.
```

---

# 🧩 How to Think / Derive the Solution

## Step 1: What should the final array look like?

For:

```text
[0, 1, 0, 3, 12]
```

we want:

```text
[1, 3, 12, 0, 0]
```

Notice that the non-zero values appear first.

Therefore, we can focus on moving **non-zero values forward**.

---

## Step 2: Use a write pointer

Let:

```text
i → scans the original array
j → position where the next non-zero value should go
```

Initially:

```text
j = 0
```

because the first non-zero value should be placed at index `0`.

---

## Step 3: Scan with `i`

For every element:

```c
if (nums[i] != 0)
```

we have found a useful value.

Write it at:

```c
nums[j] = nums[i];
```

Then:

```c
j++;
```

---

# 📊 Visual Explanation

Consider:

```text
nums = [0, 1, 0, 3, 12]
```

Initially:

```text
j = 0
```

### `i = 0`

```text
0
```

It is zero.

Skip it.

```text
j = 0
```

---

### `i = 1`

```text
1
```

Non-zero.

Write it at `j`:

```text
[1, 1, 0, 3, 12]
 ↑
 j
```

Then:

```text
j = 1
```

---

### `i = 2`

```text
0
```

Skip it.

```text
j = 1
```

---

### `i = 3`

```text
3
```

Write it at `j`:

```text
[1, 3, 0, 3, 12]
    ↑
    j
```

Then:

```text
j = 2
```

---

### `i = 4`

```text
12
```

Write it at `j`:

```text
[1, 3, 12, 3, 12]
       ↑
       j
```

Then:

```text
j = 3
```

Now all non-zero elements occupy:

```text
[1, 3, 12, ...]
```

The remaining positions are:

```text
[3, 12]
```

These positions need to become zero.

---

## Final Step: Fill with Zeroes

```c
while (j < numsSize)
{
    nums[j++] = 0;
}
```

Result:

```text
[1, 3, 12, 0, 0]
```

---

# 🔁 Algorithm

1. Set `j = 0`.
2. Traverse the array using `i`.
3. If `nums[i]` is non-zero:

   * Copy it to `nums[j]`.
   * Increment `j`.
4. After the traversal, all non-zero elements are at the beginning.
5. Fill every remaining position from `j` to the end with `0`.

### Pseudocode

```text
j = 0

for i from 0 to n - 1:

    if nums[i] != 0:

        nums[j] = nums[i]
        j++

while j < n:

    nums[j] = 0
    j++
```

---

# 💻 My Solution

```c
void moveZeroes(int* nums, int numsSize) {

    int i, j = 0;

    for (i = 0; i < numsSize; i++) {

        if (nums[i] != 0) {

            nums[j] = nums[i];
            j++;
        }
    }

    while (j < numsSize) {

        nums[j++] = 0;
    }
}
```

Your solution is correct and runs in optimal `O(n)` time with `O(1)` extra space.

---

# 🧠 Code Explanation

### Write pointer

```c
int i, j = 0;
```

`i` is the read pointer.

`j` is the write pointer.

```text
i → scans
j → writes non-zero values
```

---

### Find non-zero values

```c
if (nums[i] != 0)
```

Only non-zero elements need to be kept at the front.

---

### Move the value forward

```c
nums[j] = nums[i];
```

Place the non-zero value at the next available position.

Then:

```c
j++;
```

---

### Fill the remaining positions

After all non-zero elements have been placed:

```c
while (j < numsSize)
{
    nums[j++] = 0;
}
```

Everything after `j` becomes zero.

---

# 🔗 Pattern Connection

This problem is directly connected to your previous **26. Remove Duplicates from Sorted Array**.

### 26. Remove Duplicates

```c
if (nums[i] != nums[i - 1])
{
    nums[j] = nums[i];
    j++;
}
```

Keep the element if it is **unique**.

### 283. Move Zeroes

```c
if (nums[i] != 0)
{
    nums[j] = nums[i];
    j++;
}
```

Keep the element if it is **non-zero**.

The underlying pattern is the same:

```text
       Read
        ↓
       i
        ↓
   Check condition
        ↓
   Should we keep it?
        ↓
       Yes
        ↓
   nums[j] = nums[i]
        ↓
       j++
```

### Your progression

```text
27 Remove Element
        ↓
26 Remove Duplicates
        ↓
283 Move Zeroes
```

All three strengthen the **write-pointer / in-place modification** technique.

---

# ⚔️ Similar Problems / Variations

| Problem                      | Write-Pointer Idea               |
| ---------------------------- | -------------------------------- |
| **27. Remove Element**       | Keep elements that are not `val` |
| **26. Remove Duplicates**    | Keep unique elements             |
| **283. Move Zeroes**         | Keep non-zero elements first     |
| **80. Remove Duplicates II** | Keep each value at most twice    |
| **75. Sort Colors**          | Partition elements into groups   |

A useful mental template is:

```text
Scan → Filter/Select → Write → Finish remaining positions
```

---

# 🚨 Common Mistakes

### 1. Using an extra array

You don't need:

```c
int result[numsSize];
```

The problem can be solved in-place.

---

### 2. Losing the order of non-zero elements

For:

```text
[0, 1, 0, 3, 12]
```

the result should be:

```text
[1, 3, 12, 0, 0]
```

not:

```text
[12, 3, 1, 0, 0]
```

Your left-to-right write process naturally preserves the order.

---

### 3. Forgetting to fill the remaining positions

After the first loop, you have moved all non-zero elements forward, but old values may still exist later in the array.

For example:

```text
[1, 3, 12, 3, 12]
```

So you need:

```c
while (j < numsSize)
    nums[j++] = 0;
```

---

### 4. Moving `j` when the value is zero

Zeroes should not advance the write pointer.

Only non-zero values should move `j`.

---

# ⏱️ Complexity

### Time Complexity

```text
O(n)
```

The first loop scans the array once and the second loop fills the remaining positions.

Together, they are still linear:

```text
O(n) + O(n) = O(n)
```

### Space Complexity

```text
O(1)
```

Only two integer variables are used.

---

# 🎯 Placement-Level Takeaway

When you see:

```text
In-place
+
Move certain elements
+
Preserve relative order
```

think:

> **Write Pointer.**

Instead of worrying about where every unwanted element should move, focus on:

> **Which elements should remain, and where should I write them?**

For Move Zeroes:

```text
Keep → non-zero
Write → from left to right
Finish → fill remaining positions with zero
```

This is a very useful placement pattern because the same idea appears in many **array filtering and in-place modification** questions.

---

# ⚡ Final 30-Second Cheat Sheet

```text
Problem:
Move all zeroes to the end

Pattern:
Two Pointers / Write Pointer

i:
Read pointer

j:
Write pointer for non-zero values

Condition:
nums[i] != 0

If non-zero:
nums[j] = nums[i]
j++

After scan:
Fill nums[j ... n-1] with 0

Time:
O(n)

Space:
O(1)
```

### Mental Shortcut

> **Don't move the zeroes — move the useful elements forward, then fill the leftover space with zeroes.**

---

> **Nijoy P Jose**
>
> This solution is part of my **Data Structures & Algorithms** placement preparation repository, where I document problem-solving patterns, interview techniques, and Java implementations to strengthen my coding skills.
