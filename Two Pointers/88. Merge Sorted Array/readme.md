# 88. Merge Sorted Array

**Difficulty:** Easy
**Pattern:** Two Pointers · Reverse Traversal · In-Place Merging
**Related Patterns:** Sorted Arrays · Write Pointer · In-Place Modification

🔗 [LeetCode – 88. Merge Sorted Array](https://leetcode.com/problems/merge-sorted-array/)

---

## ⚡ Quick Revision

| Concept      | Idea                                 |
| ------------ | ------------------------------------ |
| Goal         | Merge two sorted arrays into `nums1` |
| Main Pattern | Two Pointers                         |
| Direction    | From right to left                   |
| Pointer `n1` | Last valid element of `nums1`        |
| Pointer `n2` | Last element of `nums2`              |
| Pointer `k`  | Last position of `nums1`             |
| Decision     | Put the larger element at `nums1[k]` |
| Time         | `O(m + n)`                           |
| Space        | `O(1)`                               |

### 30-Second Idea

> **Compare the largest remaining elements and place the larger one at the end of `nums1`. Move backward until all elements of `nums2` are processed.**

---

# 🧠 Problem in Simple Words

You are given two sorted arrays.

`nums1` has enough empty space at the end to store all elements of `nums2`.

Example:

```text id="6v6yp1"
nums1 = [1, 2, 3, 0, 0, 0]
nums2 = [2, 5, 6]
```

The actual elements of `nums1` are:

```text id="h5q6ry"
[1, 2, 3]
```

The zeroes at the end are empty space.

We need to modify `nums1` into:

```text id="3tq6iq"
[1, 2, 2, 3, 5, 6]
```

---

# 🔥 How to Recognize This Problem in a Placement

Look for:

* Two arrays are already **sorted**.
* Need to **merge** them.
* The destination array has extra space.
* The result must be stored **in-place**.
* No extra array should be used.

### Recognition Trigger

> **Two sorted arrays + merge in-place + empty space at the end → Use three pointers from the back.**

The important clue is that `nums1` already contains the elements that we need to preserve.

If we merge from the beginning, we may overwrite elements that haven't been processed yet.

So instead:

```text id="6q5l7x"
Start from the END
        ↓
Compare largest elements
        ↓
Write largest element at the END
```

---

# 🧩 How to Think / Derive the Solution

## Step 1: Normal merge

When merging two sorted arrays, we normally compare:

```text id="i1xj6y"
current element of nums1
        vs
current element of nums2
```

and take the smaller one.

But here there is a problem.

`nums1` itself is the destination.

---

## Step 2: Why not merge from the beginning?

Consider:

```text id="ymfl5x"
nums1 = [1, 2, 3, 0, 0, 0]
nums2 = [2, 5, 6]
```

If we start writing at index `0`, we may overwrite:

```text id="ak3m6c"
2
3
```

before we have processed them.

That creates unnecessary shifting.

---

## Step 3: Notice where the empty space is

The empty positions are already at the **end**:

```text id="y4km2t"
[1, 2, 3, 0, 0, 0]
         ↑
      empty space
```

Therefore, use that space directly.

Start from the back.

---

## Step 4: Use three pointers

```text id="7e9kpr"
n1 = m - 1
```

Points to the last actual element in `nums1`.

```text id="9d1j47"
n2 = n - 1
```

Points to the last element in `nums2`.

```text id="g5p0gd"
k = m + n - 1
```

Points to the last position in `nums1`.

Example:

```text id="p6yx1q"
nums1 = [1, 2, 3, 0, 0, 0]
             ↑           ↑
            n1           k

nums2 = [2, 5, 6]
             ↑
            n2
```

---

# 📊 Visual Explanation

### Initial state

```text id="c1h4wq"
nums1 = [1, 2, 3, 0, 0, 0]
             ↑        ↑
            n1        k

nums2 = [2, 5, 6]
             ↑
            n2
```

Compare:

```text id="8ihjhr"
3 vs 6
```

`6` is larger.

Put `6` at `k`:

```text id="7z1v6d"
[1, 2, 3, 0, 0, 6]
             ↑
             k
```

Move:

```text id="5kr6ez"
n2--
k--
```

---

### Next

Compare:

```text id="v8h2l0"
3 vs 5
```

Put `5`:

```text id="ym0o3j"
[1, 2, 3, 0, 5, 6]
```

---

### Next

Compare:

```text id="7e2q2x"
3 vs 2
```

Put `3`:

```text id="g8y3c2"
[1, 2, 3, 3, 5, 6]
```

---

### Next

Compare:

```text id="k7c4y5"
2 vs 2
```

Either one can be selected.

Your code chooses the element from `nums1` when they are equal:

```text id="w8q4d5"
[1, 2, 2, 3, 5, 6]
```

Final result:

```text id="2y9x7a"
[1, 2, 2, 3, 5, 6]
```

---

# 🔁 Algorithm

1. Set `n1 = m - 1`.
2. Set `n2 = n - 1`.
3. Set `k = m + n - 1`.
4. While `n2 >= 0`:

   * If `n1 < 0`, copy `nums2[n2]`.
   * Otherwise compare `nums1[n1]` and `nums2[n2]`.
   * Put the larger element at `nums1[k]`.
   * Move the corresponding pointer backward.
   * Move `k` backward.
5. Stop when all elements of `nums2` are processed.

### Pseudocode

```text id="ps9vqd"
n1 = m - 1
n2 = n - 1
k = m + n - 1

while n2 >= 0:

    if n1 < 0 OR nums1[n1] < nums2[n2]:

        nums1[k] = nums2[n2]
        n2--

    else:

        nums1[k] = nums1[n1]
        n1--

    k--
```

---

# 💻 My Solution

```java id="t8v4bw"
class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {

        int n2 = n - 1;
        int n1 = m - 1;
        int k = n + m - 1;

        if (n == 0)
            return;

        if (m == 0) {
            for (int i = 0; i < n; i++)
                nums1[i] = nums2[i];

            return;
        }

        while (n2 >= 0) {

            if (n1 < 0 || nums1[n1] < nums2[n2]) {

                nums1[k] = nums2[n2];
                k--;
                n2--;

            } else {

                nums1[k] = nums1[n1];
                k--;
                n1--;
            }
        }
    }
}
```

Your solution correctly uses:

**Three pointers + reverse traversal + in-place merging.**

---

# 🧠 Code Explanation

### Pointer to `nums2`

```java id="c2f0g6"
int n2 = n - 1;
```

Starts at the largest element of `nums2`.

---

### Pointer to valid part of `nums1`

```java id="2ij3nt"
int n1 = m - 1;
```

Important:

We use `m`, not `nums1.length`, because the last `n` positions are empty space.

---

### Write pointer

```java id="8y7z0h"
int k = n + m - 1;
```

This is the final position of the merged array.

---

### Process `nums2`

```java id="f7p3jh"
while (n2 >= 0)
```

We only need to guarantee that every element of `nums2` gets placed.

If `nums2` is exhausted, the remaining elements of `nums1` are already in the correct positions.

---

### Handle exhausted `nums1`

```java id="l9b6vc"
if (n1 < 0 || nums1[n1] < nums2[n2])
```

The `n1 < 0` check is important.

For example:

```text id="q6m3kf"
nums1 = [2, 0]
nums2 = [1]
```

After placing `2`, we get:

```text id="q2f0g4"
n1 = -1
```

But `1` still needs to be copied.

So:

```java id="m3r0yd"
n1 < 0
```

means:

> There are no more original elements in `nums1`; copy from `nums2`.

---

### Choose the larger element

```java id="4u8h5g"
nums1[n1] < nums2[n2]
```

If `nums2[n2]` is larger:

```java id="t4z3ck"
nums1[k] = nums2[n2];
n2--;
```

Otherwise:

```java id="h8p2c0"
nums1[k] = nums1[n1];
n1--;
```

In both cases:

```java id="a8u0jd"
k--;
```

because the next largest element belongs one position earlier.

---

# 🔗 Pattern Connection

This problem connects directly with your previous two-pointer problems.

### 26. Remove Duplicates

```text id="e3v7bq"
Read → Check → Write forward
```

### 283. Move Zeroes

```text id="s5j2q9"
Read → Select → Write forward
```

### 88. Merge Sorted Array

```text id="l8m3sd"
Compare → Select larger → Write backward
```

So the progression is:

```text id="v9f0g3"
27. Remove Element
        ↓
26. Remove Duplicates
        ↓
283. Move Zeroes
        ↓
88. Merge Sorted Array
```

You are now adding an important variation:

> **Reverse Write Pointer**

---

# ⚔️ Similar Problems / Variations

| Problem                            | Main Technique                 |
| ---------------------------------- | ------------------------------ |
| **27. Remove Element**             | Forward write pointer          |
| **26. Remove Duplicates**          | Forward write pointer          |
| **283. Move Zeroes**               | Forward write pointer          |
| **88. Merge Sorted Array**         | Reverse write pointer          |
| **977. Squares of a Sorted Array** | Two pointers + reverse filling |
| **167. Two Sum II**                | Two pointers on sorted array   |

There is a particularly useful connection with your earlier **977. Squares of a Sorted Array**:

Both use:

```text
Compare from the right-side candidates
        ↓
Place the largest result at the end
        ↓
Move pointer(s) backward
```

---

# 🚨 Common Mistakes

### 1. Starting from the front

This can overwrite unprocessed elements of `nums1`.

Instead:

```text
Start from the back.
```

---

### 2. Using `nums1.length` for the first pointer

Wrong:

```java
int n1 = nums1.length - 1;
```

The last `n` elements are empty space.

Correct:

```java
int n1 = m - 1;
```

---

### 3. Forgetting `n1 < 0`

Eventually all original elements of `nums1` may be processed.

Therefore:

```java
if (n1 < 0 || ...)
```

is necessary for your implementation.

---

### 4. Forgetting to move `k`

Every placement consumes one position:

```java
k--;
```

---

### 5. Using an extra array

An extra array is unnecessary.

The available space in `nums1` is specifically provided so that the merge can happen **in-place**.

---

# ⏱️ Complexity

### Time Complexity

```text id="e9a6zq"
O(m + n)
```

Each element is processed at most once.

### Space Complexity

```text id="c2n4sk"
O(1)
```

No additional array is used.

---

# 🎯 Placement-Level Takeaway

The biggest lesson from this problem is:

> **When the destination array has empty space at the end, consider merging from the back.**

Think:

```text
Two sorted arrays
        +
In-place
        +
Empty space at end
        ↓
Three pointers
        ↓
Compare from the back
        ↓
Write the larger element at the back
```

### Important boundary question

Whenever using multiple pointers, ask:

> **What happens when one pointer becomes `-1`?**

That exact question helped fix the runtime error in your first version.

---

# ⚡ Final 30-Second Cheat Sheet

```text
Problem:
Merge nums2 into nums1

Key clue:
Two sorted arrays + extra space at end

Pattern:
Three Pointers + Reverse Merge

n1:
Last valid element of nums1

n2:
Last element of nums2

k:
Last position of nums1

Compare:
nums1[n1] vs nums2[n2]

Larger:
Write at nums1[k]

Then:
Move selected pointer
Move k backward

Condition:
n1 < 0 → nums2 must be copied

Stop:
n2 < 0

Time:
O(m + n)

Space:
O(1)
```

### Mental Shortcut

> **Merge sorted arrays in-place → start from the end and put the largest remaining element at the end.**

---

> **Nijoy P Jose**
>
> This solution is part of my **Data Structures & Algorithms** placement preparation repository, where I document problem-solving patterns, interview techniques, and Java implementations to strengthen my coding skills.
