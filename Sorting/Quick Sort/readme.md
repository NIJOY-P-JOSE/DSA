# Quick Sort

**Difficulty:** Medium  
**Pattern:** Divide and Conquer · Partitioning · Recursion  
**Platform:** GeeksforGeeks

---

## ⚡ Quick Revision

| Property | Quick Sort |
|---|---|
| **Goal** | Sort an array in increasing order |
| **Main Pattern** | Divide and Conquer |
| **Core Operation** | Partition around a pivot |
| **Pivot in My Solution** | First element |
| **Partition Rule** | Elements `<= pivot` go to the left |
| **After Partition** | Pivot reaches its final sorted position |
| **Average Time** | `O(n log n)` |
| **Best Time** | `O(n log n)` |
| **Worst Time** | `O(n²)` |
| **Average Space** | `O(log n)` recursion stack |
| **Worst Space** | `O(n)` recursion stack |
| **In-place** | Yes, apart from recursion stack |
| **Stable** | No |

### 🧠 One-line memory

> **Choose pivot → partition → pivot is fixed → recursively sort left and right.**

---

## 📝 Problem in Simple Words

Given an array and its starting and ending indices, sort the array using **Quick Sort**.

Quick Sort does not directly sort the whole array.

Instead, it repeatedly breaks the problem into smaller problems:

```text
Whole Array
     ↓
Choose Pivot
     ↓
Partition
     ↓
[ Elements ≤ Pivot | Pivot | Elements > Pivot ]
             ↓
       Pivot is finalized
             ↓
     Recursively sort both sides
```

---

## 🔍 How to Recognize Quick Sort

Look for these clues:

- The problem involves sorting.
- You can divide the array around a chosen element.
- A **pivot** is involved.
- Elements are rearranged based on their relationship with the pivot.
- The left and right portions can be solved independently.
- Recursion naturally appears after partitioning.

### Strong placement trigger

> **"Can I put one element into its final position and then independently solve the two sides?"**

If yes, think:

**Quick Sort / partition-based divide and conquer.**

---

# 🧠 How to Think / Derive the Solution

Consider:

```text
[4, 1, 3, 9, 7]
```

Your implementation chooses:

```text
pivot = 4
```

Now the goal is **not** to completely sort the array.

The immediate goal is:

> Put `4` in the position where it belongs in the final sorted array.

Everything smaller than or equal to `4` should go to its left, while larger elements should go to its right.

So we want:

```text
[ smaller/equal | 4 | larger ]
```

Once `4` reaches that position, we don't need to move it again.

This is the most important Quick Sort observation.

---

## 🎯 Partitioning

Your `partition()` starts with:

```text
pivot = arr[low]
```

For:

```text
[4, 1, 3, 9, 7]
 ↑
pivot
```

we initially keep:

```text
i = low
```

Then `j` scans from:

```text
low + 1 → high
```

Whenever:

```text
arr[j] <= pivot
```

we increase `i` and swap `arr[i]` with `arr[j]`.

Conceptually:

```text
[ pivot | elements processed | elements not processed ]
          ↑                    ↑
          i                    j
```

The important invariant is:

```text
indices low+1 ... i
        ↓
elements <= pivot
```

while `j` continues exploring the unprocessed portion.

---

## 🔄 Example Partition

Start:

```text
[4, 1, 3, 9, 7]
 ↑
pivot = 4
```

### `j` points to `1`

```text
1 <= 4
```

So increase `i` and place `1` into the left partition.

The array remains effectively:

```text
[4, 1, 3, 9, 7]
    ↑
  left region
```

### `j` points to `3`

```text
3 <= 4
```

Again, expand the left region.

```text
[4, 1, 3, 9, 7]
       ↑
       i
```

### `j` points to `9`

```text
9 <= 4   ❌
```

Do nothing.

### `j` points to `7`

```text
7 <= 4   ❌
```

Do nothing.

At the end:

```text
i
↓
[4, 1, 3, 9, 7]
```

Now swap the pivot with `arr[i]`:

```text
[3, 1, 4, 9, 7]
       ↑
     pivot
```

The exact arrangement of the left side can vary, but the critical property is:

```text
[ elements <= 4 | 4 | elements > 4 ]
```

Therefore `4` is now in its final position.

---

## 🧩 The Most Important Quick Sort Idea

After partitioning:

```text
           pivot
             ↓
[ LEFT SIDE | P | RIGHT SIDE ]
```

The pivot never needs to participate in future recursive calls.

So instead of:

```text
quickSort(low, high)
```

we solve:

```text
quickSort(low, pivot - 1)
quickSort(pivot + 1, high)
```

This is why the partition index is so important.

---

# 🔄 Algorithm

### `quickSort()`

For a range `[low, high]`:

1. Check whether more than one element exists.
2. Partition the range.
3. Get the pivot's final index.
4. Recursively sort the left portion.
5. Recursively sort the right portion.

### Pseudocode

```text
quickSort(arr, low, high)

    if low < high

        pivotIndex = partition(arr, low, high)

        quickSort(arr, low, pivotIndex - 1)

        quickSort(arr, pivotIndex + 1, high)
```

---

## `partition()`

Your partition method:

1. Selects `arr[low]` as the pivot.
2. Uses `i` to track the boundary of elements `<= pivot`.
3. Uses `j` to scan the remaining elements.
4. Moves qualifying elements into the left region.
5. Places the pivot between the two regions.
6. Returns the pivot's final index.

Conceptually:

```text
[ <= pivot | unknown ]
            ↑
            j
```

As `j` moves:

```text
[ <= pivot | > pivot / not processed ]
            ↑
            i
```

At the end:

```text
[ <= pivot | pivot | > pivot ]
```

---

# 💻 My Solution

Your solution uses:

### 1. First element as pivot

```text
pivot = arr[low]
```

This means the pivot-selection strategy is:

> **Always choose the first element of the current subarray.**

---

### 2. Boundary pointer `i`

Initially:

```text
i = low
```

`i` represents the boundary of the region containing elements that belong on the left side of the pivot.

Whenever:

```text
arr[j] <= pivot
```

you do:

```text
i += 1
```

and swap the element into that region.

---

### 3. Scanning pointer `j`

`j` moves through every element after the pivot:

```text
low + 1 → high
```

Its job is simply:

> Check whether the current element belongs on the left side of the pivot.

---

### 4. Final pivot placement

After scanning everything:

```text
arr[i], arr[low] = arr[low], arr[i]
```

The pivot moves from the beginning to its final partition position.

Then:

```text
return i
```

gives the index used by the recursive calls.

---

# 🧠 Why Does Partition Work?

Suppose the pivot is `P`.

During the scan, maintain:

```text
[ P | <= P | unknown ]
     ↑       ↑
     i       j
```

Whenever `arr[j] <= P`:

```text
[ P | <= P | element | unknown ]
             ↑
```

we move that element into the `<= P` region.

So by the time `j` reaches the end:

```text
[ P | <= P | > P ]
```

Finally, swapping the pivot with the first element after the `<= P` region gives:

```text
[ <= P | P | > P ]
```

Therefore the pivot is correctly positioned.

---

# 🔁 Recursive Structure

Suppose:

```text
[4, 1, 3, 9, 7]
```

After partition:

```text
[3, 1 | 4 | 9, 7]
        ↑
      pivot
```

Now Quick Sort doesn't care about `4` anymore.

It creates two smaller problems:

```text
[3, 1]       [9, 7]
   ↓             ↓
quickSort      quickSort
```

Then:

```text
[1, 3]   4   [7, 9]
```

Final:

```text
[1, 3, 4, 7, 9]
```

---

## 🌳 Recursion Mental Model

Think of Quick Sort as a tree:

```text
              [4,1,3,9,7]
                    |
                  pivot
                    4
                 /   \
              [3,1]  [9,7]
                |       |
              pivot    pivot
               3         9
              / \       / \
            [1] []    [7] []
```

Each recursive call works on a smaller range.

The recursion stops when:

```text
low >= high
```

because a range with zero or one element is already sorted.

---

# ⚠️ Important Edge Cases

### Empty / single-element range

```text
low >= high
```

No sorting is required.

Handled by:

```text
if low < high:
```

---

### Already sorted array

Example:

```text
[1, 2, 3, 4, 5]
```

Because your pivot is always the first element, the partition becomes highly unbalanced.

This leads to the worst-case behavior.

---

### Reverse-sorted array

```text
[5, 4, 3, 2, 1]
```

Again, choosing the first element produces a very unbalanced partition.

---

### Duplicate values

Your partition condition is:

```text
arr[j] <= pivot
```

So elements equal to the pivot are included in the left partition.

The array still becomes correctly sorted, including cases such as:

```text
[5, 5, 5, 5]
```

However, many equal elements can also produce poorly balanced partitions with this particular partition strategy.

---

# ⏱️ Complexity

Quick Sort's complexity depends heavily on how balanced the partitions are.

## Best Case — `O(n log n)`

If every partition approximately divides the array in half:

```text
              n
            /   \
          n/2   n/2
         / \     / \
       n/4 ...       n/4
```

There are approximately:

```text
log n
```

levels.

Each level processes approximately `n` elements.

Therefore:

```text
O(n log n)
```

---

## Average Case — `O(n log n)`

For reasonably balanced partitions on average:

```text
O(n log n)
```

---

## Worst Case — `O(n²)`

If the pivot repeatedly becomes the smallest or largest element:

```text
n
|
n-1
|
n-2
|
n-3
|
...
```

The recursion becomes highly unbalanced.

The partition work becomes approximately:

```text
n + (n-1) + (n-2) + ... + 1
```

Therefore:

```text
O(n²)
```

Your choice of **first element as pivot** can produce this worst case on already sorted or reverse-sorted arrays.

---

## 💾 Space Complexity

Quick Sort performs the partition in-place, so it does not require another array for sorting.

However, recursion uses the call stack.

### Average / balanced case

```text
O(log n)
```

recursive stack depth.

### Worst case

```text
O(n)
```

recursive stack depth.

So:

```text
Average auxiliary space → O(log n)
Worst auxiliary space   → O(n)
```

---

# 🆚 Quick Sort vs Basic Sorting Algorithms

| | Selection | Insertion | Quick Sort |
|---|---|---|---|
| Main idea | Select minimum | Insert key | Partition around pivot |
| Pattern | Selection | Incremental | Divide & Conquer |
| Average | `O(n²)` | `O(n²)` | `O(n log n)` |
| Best | `O(n²)` | `O(n)` | `O(n log n)` |
| Worst | `O(n²)` | `O(n²)` | `O(n²)` |
| Extra array | No | No | No |
| Recursion | No | No | Yes |
| Stable | No | Yes | No |

### Memory trick

```text
Selection → SELECT minimum
Insertion → INSERT element
Bubble    → SWAP neighbors
Quick     → PARTITION around pivot
```

---

# 🔗 Pattern Connection

Quick Sort is a classic:

```text
Divide and Conquer
```

The pattern is:

```text
            Problem
               ↓
          Choose Pivot
               ↓
           Partition
          /         \
      Smaller      Larger
        ↓             ↓
     Solve          Solve
     recursively    recursively
          \         /
             Done
```

The key difference from simply "splitting an array" is that **partitioning makes the pivot permanently correct**.

---

# ⚠️ Common Mistakes

### 1. Including the pivot in the recursive calls

Wrong:

```text
quickSort(low, pivot)
quickSort(pivot, high)
```

The pivot is already finalized.

Correct:

```text
quickSort(low, pivot - 1)
quickSort(pivot + 1, high)
```

---

### 2. Forgetting the base condition

Without:

```text
if low < high
```

the recursion will not stop correctly.

---

### 3. Returning the wrong partition index

The returned index represents the pivot's final position.

That index determines the two recursive ranges.

---

### 4. Confusing `i` and `j`

Think:

```text
i → boundary of <= pivot region
j → scanning pointer
```

`j` explores.

`i` expands the left partition.

---

### 5. Assuming Quick Sort is always `O(n log n)`

This is a common interview mistake.

Correct:

```text
Best/Average → O(n log n)
Worst         → O(n²)
```

The partition quality determines the complexity.

---

### 6. Ignoring pivot selection

The pivot strategy matters.

Your implementation uses:

```text
pivot = first element
```

That can perform poorly on already sorted or reverse-sorted input.

Other implementations may use:

- Last element
- Middle element
- Random element
- Median-of-three style selection

These are **pivot-selection variations**, not changes to the core Quick Sort idea.

---

# 🎯 Placement-Level Takeaway

When you see a sorting problem, ask:

```text
Can I choose an element
and put it in its final position?
```

If yes, ask:

```text
Can everything smaller/equal go to one side
and everything larger go to the other?
```

If yes:

**Think Quick Sort.**

The complete decision process:

```text
Sorting problem
      ↓
Choose pivot
      ↓
Partition around pivot
      ↓
Pivot reaches final position
      ↓
Can solve left and right independently?
      ↓
Yes
      ↓
Recursion
```

The most important concept is **not recursion itself**.

The key is:

> **Partitioning turns one sorting problem into two smaller independent sorting problems.**

---

# ⚡ 30-Second Cheat Sheet

```text
QUICK SORT

Pattern:
→ Divide and Conquer

Core:
→ Choose pivot
→ Partition
→ Pivot reaches final position
→ Recursively sort left + right

My pivot:
→ First element

Partition:
→ i = low
→ j = low + 1
→ if arr[j] <= pivot:
      i++
      swap(arr[i], arr[j])
→ swap(arr[i], arr[low])
→ return i

Recursive calls:
→ quickSort(low, pivot - 1)
→ quickSort(pivot + 1, high)

Best:
→ O(n log n)

Average:
→ O(n log n)

Worst:
→ O(n²)

Average recursion space:
→ O(log n)

Worst recursion space:
→ O(n)

In-place:
→ Yes

Stable:
→ No

Memory:
→ "Partition around a pivot, then solve both sides."
```

## 🧠 One-Sentence Memory

> **Quick Sort places a pivot in its final position through partitioning, then recursively sorts the elements on either side.**
