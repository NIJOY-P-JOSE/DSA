# 74. Search a 2D Matrix

**Difficulty:** Medium  
**Pattern:** Binary Search  
**Time:** `O(log(m × n))`  
**Space:** `O(1)`

---

## 1. Quick Revision

### Core Idea

The matrix can be treated as a **single sorted 1D array** because:

- Every row is sorted.
- The first element of every row is greater than the last element of the previous row.

So instead of performing Binary Search twice, we can perform **one Binary Search** over virtual indices:

```text
0 ────────────────────────────────> m × n - 1
```

For a virtual index `mid`, convert it to:

```text
row = mid / columns
col = mid % columns
```

Then access:

```text
matrix[row][col]
```

### 30-Second Recall

> **Sorted rows + next row starts after previous row → flatten conceptually → Binary Search → convert 1D index to 2D using `/` and `%`.**

---

## 2. Problem in Simple Words

You are given a matrix where all elements are globally sorted from left to right and top to bottom.

For example:

```text
1   3   5   7
10  11  16  20
23  30  34  60
```

You need to determine whether `target` exists.

The required complexity is:

```text
O(log(m × n))
```

So scanning every element is not allowed.

---

## 3. How to Recognize This Problem in a Placement

Look for these clues:

- The matrix is sorted.
- Every row is sorted.
- The first element of a row is greater than the last element of the previous row.
- The question asks for `O(log(m × n))`.

These conditions tell you that the **entire matrix behaves like one sorted array**.

### Important Recognition

If you see:

```text
Sorted matrix
+
Complete ordering between rows
+
O(log(m × n))
```

Think:

> **Binary Search on a virtual 1D array.**

---

## 4. How to Think / Derive the Solution

### Step 1: Imagine the matrix as a 1D array

Original matrix:

```text
1   3   5   7
10  11  16  20
23  30  34  60
```

Conceptually:

```text
1  3  5  7  10  11  16  20  23  30  34  60
```

We don't actually create this array.

Creating it would require extra `O(m × n)` space.

Instead, we use a **virtual index**.

---

### Step 2: Define the Binary Search range

If there are:

```text
m = number of rows
c = number of columns
```

Then the virtual array contains:

```text
m × c
```

elements.

Therefore:

```text
l = 0
r = m × c - 1
```

---

### Step 3: Convert the virtual index into matrix coordinates

Suppose there are `4` columns.

For:

```text
mid = 6
```

The corresponding matrix position is:

```text
row = mid / columns
    = 6 / 4
    = 1

col = mid % columns
    = 6 % 4
    = 2
```

Therefore:

```text
matrix[1][2]
```

is the element represented by virtual index `6`.

### The Important Formula

```text
row = mid / columns
col = mid % columns
```

This is the main trick of the problem.

---

## 5. Visual Explanation

For a matrix with `4` columns:

```text
Virtual Index:

 0   1   2   3
 4   5   6   7
 8   9  10  11

Matrix:

[0][0] [0][1] [0][2] [0][3]
[1][0] [1][1] [1][2] [1][3]
[2][0] [2][1] [2][2] [2][3]
```

The mapping is:

```text
virtual index
      │
      ├── / columns → row
      │
      └── % columns → column
```

For example:

```text
6 / 4 = 1
6 % 4 = 2

6 → matrix[1][2]
```

---

## 6. Algorithm

1. Find the number of rows `m`.
2. Find the number of columns `c`.
3. Treat the matrix as a virtual sorted array.
4. Set:
   ```text
   l = 0
   r = m × c - 1
   ```
5. Calculate:
   ```text
   mid = l + (r - l) / 2
   ```
6. Convert `mid` into matrix coordinates:
   ```text
   row = mid / c
   col = mid % c
   ```
7. Compare `matrix[row][col]` with `target`.
8. If equal, return `true`.
9. If the target is greater, search the right half.
10. Otherwise, search the left half.
11. If the search space becomes empty, return `false`.

---

## 7. My Java Solution

```java
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        
        int m = matrix.length;
        int c = matrix[0].length;

        // Treat the entire matrix as one sorted 1D array.
        // For m rows and c columns, the virtual indices are 0 to m*c-1.
        int l = 0, r = m * c - 1;

        while (l <= r) {
            int mid = l + (r - l) / 2;

            // Convert the virtual 1D index back to a matrix position.
            // Example: if c = 4 and mid = 6:
            // row = 6/4 = 1, col = 6%4 = 2 → matrix[1][2]
            int row = mid / c;
            int col = mid % c;

            if (target == matrix[row][col])
                return true;

            // Since the virtual array is sorted, eliminate half
            // of the remaining search space just like normal Binary Search.
            else if (target > matrix[row][col])
                l = mid + 1;
            else
                r = mid - 1;
        }

        // Search space is empty, so the target does not exist.
        return false;
    }
}
```

---

## 8. Code Explanation

### Why `m * c - 1`?

There are `m × c` total elements.

Since array indices start from `0`:

```text
first index = 0
last index  = m × c - 1
```

---

### Why `mid / c` gives the row?

Every row contains exactly `c` elements.

For example, with `4` columns:

```text
Indices 0–3   → row 0
Indices 4–7   → row 1
Indices 8–11  → row 2
```

Integer division tells us which row the index belongs to.

---

### Why `mid % c` gives the column?

The remainder tells us the position inside the current row.

For:

```text
mid = 6
c = 4
```

```text
6 % 4 = 2
```

So the element is in column `2`.

---

## 9. Example Walkthrough

### Input

```text
matrix =
[
    [1, 3, 5, 7],
    [10, 11, 16, 20],
    [23, 30, 34, 60]
]

target = 3
```

There are:

```text
m = 3
c = 4
```

So:

```text
l = 0
r = 3 × 4 - 1
r = 11
```

### First iteration

```text
mid = 5
```

Convert:

```text
row = 5 / 4 = 1
col = 5 % 4 = 1
```

So:

```text
matrix[1][1] = 11
```

Since:

```text
3 < 11
```

search left:

```text
r = 4
```

### Next iteration

```text
mid = 2
```

Convert:

```text
row = 2 / 4 = 0
col = 2 % 4 = 2
```

```text
matrix[0][2] = 5
```

Since:

```text
3 < 5
```

search left:

```text
r = 1
```

### Next iteration

```text
mid = 0
```

```text
matrix[0][0] = 1
```

Since:

```text
3 > 1
```

move right:

```text
l = 1
```

### Next iteration

```text
mid = 1
```

```text
matrix[0][1] = 3
```

Target found:

```text
return true
```

---

## 10. Pattern Connection

This is a variation of the standard **Binary Search** pattern.

### Normal Binary Search

```text
Sorted 1D array
       ↓
Binary Search
       ↓
Compare with target
       ↓
Discard half
```

### This Problem

```text
Sorted 2D matrix
       ↓
Treat as virtual 1D array
       ↓
Binary Search
       ↓
Convert index → row/column
       ↓
Discard half
```

The important insight is:

> **The data structure looks 2D, but the ordering allows us to treat it as 1D.**

---

## 11. Similar Problems / Variations

### LeetCode 704 — Binary Search

Basic Binary Search.

```text
Sorted array → Binary Search
```

### LeetCode 33 — Search in Rotated Sorted Array

Still Binary Search, but the array is partially disrupted.

```text
Sorted array + rotation
→ Identify sorted half
→ Binary Search
```

### LeetCode 34 — Find First and Last Position

Uses Binary Search to find boundaries instead of simply finding one occurrence.

### LeetCode 240 — Search a 2D Matrix II

Also involves a sorted matrix, but its ordering conditions are different.

**Important:** Do not automatically use this exact virtual-array technique for Matrix II.

---

## 12. Common Mistakes

### Mistake 1: Using `matrix.length` for columns

Wrong:

```java
int c = matrix.length;
```

`matrix.length` gives the number of rows.

Correct:

```java
int c = matrix[0].length;
```

---

### Mistake 2: Using `m * c` as the last index

Wrong:

```java
r = m * c;
```

The last valid index is:

```text
m × c - 1
```

---

### Mistake 3: Forgetting the index conversion

You cannot directly access:

```java
matrix[mid]
```

because `mid` represents a virtual 1D index.

You must convert it:

```java
int row = mid / c;
int col = mid % c;
```

---

### Mistake 4: Creating an actual flattened array

You could copy all elements into a 1D array, but that requires:

```text
O(m × n)
```

extra space.

There is no need to do that.

Use virtual indexing instead.

---

### Mistake 5: Using the wrong matrix condition

This approach depends on the matrix being globally ordered:

```text
last element of previous row
<
first element of current row
```

If this condition doesn't exist, you cannot automatically treat the matrix as one sorted array.

---

## 13. Complexity

Let:

```text
m = number of rows
n = number of columns
```

### Time

Binary Search operates over `m × n` virtual elements:

```text
O(log(m × n))
```

### Space

Only a few variables are used:

```text
O(1)
```

No additional array is created.

---

## 14. Placement-Level Takeaway

The most important part of this problem is **not the Binary Search code**.

It is recognizing that the matrix's ordering allows you to **flatten it conceptually without actually flattening it**.

When you see:

```text
Rows sorted
+
Each row continues after the previous row
+
O(log(m × n))
```

immediately think:

```text
Virtual 1D Array
        ↓
Binary Search
        ↓
row = mid / columns
col = mid % columns
```

This is a useful Binary Search transformation that often appears in placement interviews.

---

## 15. Final 30-Second Cheat Sheet

```text
Problem:
Search target in globally sorted matrix.

Pattern:
Binary Search

Core Trick:
Treat matrix as a virtual 1D sorted array.

Search Range:
l = 0
r = rows × columns - 1

Convert index:
row = mid / columns
col = mid % columns

Comparison:
target == value → true
target > value  → l = mid + 1
target < value  → r = mid - 1

Complexity:
Time  → O(log(m × n))
Space → O(1)
```

### One Line to Remember

> **Don't flatten the matrix — just pretend it is flat and convert the virtual index using division and modulo.**
