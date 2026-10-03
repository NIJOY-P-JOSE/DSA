# Quick Sort

**Difficulty:** Medium  
**Pattern:** Divide and Conquer · Partitioning · Recursion  
**Platform:** GeeksforGeeks

---

## 🔗 Problem

Given an array `arr[]` with starting index `low` and ending index `high`, sort the array in ascending order using Quick Sort by implementing `quickSort()` and `partition()`.

---

## ⚡ Quick Revision

| Property | Quick Sort |
|---|---|
| **Goal** | Sort the array in increasing order |
| **Main Pattern** | Divide and Conquer |
| **Core Technique** | Partitioning |
| **Pivot in My Solution** | First element |
| **Partition Rule** | Elements `<= pivot` go to the left |
| **After Partition** | Pivot reaches its final position |
| **Best Time** | `O(n log n)` |
| **Average Time** | `O(n log n)` |
| **Worst Time** | `O(n²)` |
| **Average Space** | `O(log n)` recursion stack |
| **Worst Space** | `O(n)` recursion stack |
| **In-place** | Yes |
| **Stable** | No |

### 🧠 One-line memory

> **Choose pivot → partition → pivot becomes fixed → recursively sort both sides.**

---

# 📝 Problem in Simple Words

We need to sort an array using **Quick Sort**.

Instead of trying to sort the entire array at once:

1. Choose a pivot.
2. Rearrange the elements around the pivot.
3. Put the pivot into its final sorted position.
4. Recursively sort the elements on the left.
5. Recursively sort the elements on the right.

The basic structure is:

```text
                 ARRAY
                   ↓
                PIVOT
                   ↓
             PARTITION
              /       \
             /         \
        LEFT SIDE    RIGHT SIDE
           ↓             ↓
       QUICK SORT    QUICK SORT
```

---

# 🔍 How to Recognize Quick Sort in a Placement

Look for these clues:

- The problem asks you to sort an array.
- A **pivot** is involved.
- Elements are divided based on their relationship with the pivot.
- One element can be placed into its final position.
- The remaining left and right portions can be solved independently.
- Recursion naturally fits the problem.

### Strong recognition trigger

Ask yourself:

> **"Can I choose one element, put it into its final position, and then independently solve the two sides?"**

If yes, Quick Sort is a possible approach.

---

# 🧠 How to Think / Derive the Solution

Suppose:

```text
[4, 1, 3, 9, 7]
```

Your solution chooses the first element:

```text
pivot = 4
```

The goal of the first step is **not to completely sort the array**.

Instead, we only want to place `4` where it belongs.

We want:

```text
[ elements <= 4 | 4 | elements > 4 ]
```

Once `4` reaches this position, we don't need to move it again.

Then we have two smaller problems:

```text
[ LEFT ]  4  [ RIGHT ]
    ↓             ↓
Quick Sort    Quick Sort
```

This is the main idea behind Quick Sort.

---

# 🎯 The Core Idea: Partition

Partitioning is the heart of Quick Sort.

Your implementation chooses:

```python
pivot = arr[low]
```

So the first element of the current range becomes the pivot.

For:

```text
[4, 1, 3, 9, 7]
 ↑
pivot
```

we scan the remaining elements.

The goal is to create:

```text
[ <= pivot | pivot | > pivot ]
```

---

# 🔄 Understanding `i` and `j`

This is one of the most important parts to understand for an interview.

Initially:

```text
i = low
```

and:

```text
j = low + 1
```

Think of them as:

```text
[ pivot | <= pivot | unknown ]
    ↑        ↑          ↑
  low        i          j
```

### `j`

`j` is the **scanning pointer**.

It checks every element after the pivot.

```text
j →  low + 1 ... high
```

### `i`

`i` is the **boundary of the `<= pivot` region**.

Whenever:

```text
arr[j] <= pivot
```

we expand the left region:

```text
i += 1
```

and move that element into the region.

---

# 🧪 Partition Example

Take:

```text
[4, 1, 3, 9, 7]
```

Pivot:

```text
4
```

Initially:

```text
i = 0
j = 1
```

### `j` points to `1`

```text
1 <= 4
```

So:

```text
i++
```

and swap:

```text
arr[i], arr[j]
```

The `<= pivot` region now contains `1`.

---

### `j` points to `3`

```text
3 <= 4
```

Again:

```text
i++
```

and place `3` in the left region.

---

### `j` points to `9`

```text
9 <= 4
```

False.

So nothing happens.

---

### `j` points to `7`

```text
7 <= 4
```

False.

Again, nothing happens.

---

At the end, the elements have been divided conceptually into:

```text
[4 | 1, 3 | 9, 7]
     <= 4    > 4
```

Now place the pivot in between these two regions.

Swap:

```text
arr[i], arr[low]
```

Result:

```text
[3, 1, 4, 9, 7]
       ↑
     pivot
```

The exact ordering inside the left/right regions does **not** need to be sorted yet.

The only important property is:

```text
[ elements <= 4 | 4 | elements > 4 ]
```

---

# ⭐ Why Is the Pivot Now Fixed?

After partitioning:

```text
[ LEFT | PIVOT | RIGHT ]
```

Every element on the left is:

```text
<= PIVOT
```

Every element on the right is:

```text
> PIVOT
```

Therefore, regardless of how the left and right portions are eventually sorted, the pivot itself is already in its final position.

This is the key observation that makes Quick Sort work.

---

# 🔄 Recursive Structure

Suppose partition gives:

```text
[3, 1 | 4 | 9, 7]
       ↑
     pivot
```

We don't process `4` anymore.

Instead:

```text
quickSort(left)
```

and:

```text
quickSort(right)
```

Conceptually:

```text
             [3,1,4,9,7]
                   |
                  4
                /   \
             [3,1] [9,7]
               |      |
              3       9
             / \     / \
           [1] []  [7] []
```

Eventually:

```text
[1, 3, 4, 7, 9]
```

---

# 🛑 Base Case

When:

```python
low >= high
```

there are zero or one elements in the current range.

Such a range is already sorted.

Therefore:

```python
if low < high:
```

is the stopping condition.

This prevents infinite recursion.

---

# 🔄 Algorithm

## Quick Sort

```text
quickSort(arr, low, high)

    if low < high

        pivotIndex = partition(arr, low, high)

        quickSort(arr, low, pivotIndex - 1)

        quickSort(arr, pivotIndex + 1, high)
```

---

## Partition

```text
partition(arr, low, high)

    pivot = arr[low]
    i = low

    for j from low + 1 to high

        if arr[j] <= pivot

            i++
            swap(arr[i], arr[j])

    swap(arr[i], arr[low])

    return i
```

---

# 💻 My Solution

Your implementation uses:

- **First element as pivot**
- `i` as the partition boundary
- `j` as the scanning pointer
- Elements `<= pivot` moved toward the left
- Final pivot placement using one swap
- Recursive Quick Sort on both sides

## Code

```python
class Solution:
    def quickSort(self, arr, low, high):
        if low < high:
            pivot = self.partition(arr, low, high)

            self.quickSort(arr, low, pivot - 1)
            self.quickSort(arr, pivot + 1, high)

    def partition(self, arr, low, high):
        pivot = arr[low]
        i = low

        for j in range(low + 1, high + 1):
            if arr[j] <= pivot:
                i += 1
                arr[i], arr[j] = arr[j], arr[i]

        arr[i], arr[low] = arr[low], arr[i]

        return i
```

---

# 🔎 Code Breakdown

## 1. Check whether sorting is necessary

```python
if low < high:
```

If the range contains at least two elements, partitioning is required.

Otherwise, recursion stops.

---

## 2. Partition the array

```python
pivot = self.partition(arr, low, high)
```

The function returns the pivot's final index.

For example:

```text
[3, 1, 4, 9, 7]
       ↑
    pivot index
```

---

## 3. Sort the left side

```python
self.quickSort(arr, low, pivot - 1)
```

Everything before the pivot is recursively sorted.

---

## 4. Sort the right side

```python
self.quickSort(arr, pivot + 1, high)
```

Everything after the pivot is recursively sorted.

---

# 🔬 `partition()` Breakdown

## Choose pivot

```python
pivot = arr[low]
```

The first element is selected.

---

## Initialize boundary

```python
i = low
```

Initially, there are no elements in the `<= pivot` region.

---

## Scan the remaining elements

```python
for j in range(low + 1, high + 1):
```

`j` visits every element after the pivot.

---

## Expand the left partition

```python
if arr[j] <= pivot:
    i += 1
    arr[i], arr[j] = arr[j], arr[i]
```

If the element belongs on the left:

1. Increase `i`.
2. Swap the element into the left region.

---

## Put pivot in its final position

```python
arr[i], arr[low] = arr[low], arr[i]
```

The pivot moves between the two partitions.

---

## Return pivot position

```python
return i
```

This index tells Quick Sort where the two recursive problems begin/end.

---

# 🧠 The Partition Invariant

During the loop, think:

```text
        pivot
          ↓
[ P | <=P | UNKNOWN ]
      ↑       ↑
      i       j
```

At every point:

```text
low+1 ... i
```

contains elements:

```text
<= pivot
```

while:

```text
i+1 ... j-1
```

contains elements that have already been examined but belong to the other side.

`j` continues scanning the unknown elements.

At the end:

```text
[ P | <=P | >P ]
```

Then the pivot is swapped into position:

```text
[ <=P | P | >P ]
```

That is the entire partition idea.

---

# ⚠️ Common Mistakes

## 1. Including the pivot in recursive calls

Wrong:

```python
quickSort(arr, low, pivot)
quickSort(arr, pivot, high)
```

The pivot is already finalized.

Correct:

```python
quickSort(arr, low, pivot - 1)
quickSort(arr, pivot + 1, high)
```

---

## 2. Forgetting the base condition

Without:

```python
if low < high:
```

the recursion won't terminate correctly.

---

## 3. Confusing `i` and `j`

Remember:

```text
i → partition boundary
j → scanning pointer
```

Easy memory:

> **`j` searches, `i` organizes.**

---

## 4. Returning the wrong index

The partition function must return the **final position of the pivot**.

That returned value controls the recursive ranges.

---

## 5. Thinking partition completely sorts the array

Partition does **not** sort the left and right regions.

It only guarantees:

```text
[ <= pivot | pivot | > pivot ]
```

The recursive calls perform the remaining sorting.

---

## 6. Assuming Quick Sort is always `O(n log n)`

Quick Sort can become:

```text
O(n²)
```

when partitions are highly unbalanced.

Your first-element pivot strategy can cause this for certain inputs.

---

# 🧪 Important Edge Cases

### Already sorted

```text
[1, 2, 3, 4, 5]
```

With the first-element pivot:

```text
pivot = 1
```

almost everything goes to the right.

The next recursive call has almost the entire remaining array.

This creates an unbalanced recursion tree.

---

### Reverse sorted

```text
[5, 4, 3, 2, 1]
```

The first element is again a poor pivot because most elements fall on one side.

---

### All elements equal

```text
[5, 5, 5, 5]
```

Because your condition is:

```python
arr[j] <= pivot
```

equal elements are included in the left partition.

The array is still sorted correctly, although the partition can be unbalanced.

---

### Single element

```text
[5]
```

No work is required.

Handled by:

```python
low < high
```

being false.

---

# ⏱️ Complexity Analysis

Quick Sort's complexity depends on the partition balance.

## Best Case

If the pivot divides the array approximately equally:

```text
              n
            /   \
          n/2   n/2
         / \     / \
       n/4 ...   ... n/4
```

There are approximately:

```text
log n
```

levels.

Each level processes approximately `n` elements.

Therefore:

**Time = `O(n log n)`**

---

## Average Case

When partitions are reasonably balanced on average:

**Time = `O(n log n)`**

---

## Worst Case

If the pivot is repeatedly the smallest or largest element:

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

The total partitioning work becomes approximately:

```text
n + (n-1) + (n-2) + ... + 1
```

Therefore:

**Time = `O(n²)`**

This can happen with your **first-element pivot** on already sorted or reverse-sorted arrays.

---

## Space Complexity

Quick Sort itself partitions in-place, but recursive calls require stack space.

### Balanced case

```text
O(log n)
```

### Worst case

```text
O(n)
```

So:

| Case | Time | Recursion Space |
|---|---:|---:|
| Best | `O(n log n)` | `O(log n)` |
| Average | `O(n log n)` | `O(log n)` |
| Worst | `O(n²)` | `O(n)` |

---

# 📌 Important Properties

### In-place

Yes.

The array is rearranged directly without creating another array for the partitions.

However, recursion still uses stack memory.

### Stable

No.

Equal elements can change their relative order during partitioning.

---

# 🆚 Sorting Algorithm Comparison

| Algorithm | Main Idea | Best | Average | Worst | Extra Space | Stable |
|---|---|---:|---:|---:|---:|---|
| **Selection Sort** | Select minimum | `O(n²)` | `O(n²)` | `O(n²)` | `O(1)` | No |
| **Insertion Sort** | Insert key | `O(n)` | `O(n²)` | `O(n²)` | `O(1)` | Yes |
| **Bubble Sort** | Swap neighbors | `O(n)`* | `O(n²)` | `O(n²)` | `O(1)` | Yes |
| **Quick Sort** | Partition around pivot | `O(n log n)` | `O(n log n)` | `O(n²)` | `O(log n)` avg | No |

\* With early termination.

### Quick memory map

```text
Selection → SELECT minimum
Bubble    → SWAP neighbors
Insertion → INSERT key
Quick     → PARTITION around pivot
```

---

# 🔗 Pattern Connection

Quick Sort is a classic **Divide and Conquer** algorithm.

```text
                SORT ARRAY
                    ↓
               Choose Pivot
                    ↓
                Partition
                /        \
               /          \
          Left Side     Right Side
             ↓              ↓
         Recursion       Recursion
             \              /
              \            /
                 Sorted
```

The important difference from simply splitting an array is:

> **Partitioning puts the pivot into its final position before the recursive calls.**

---

# 🔄 Variations

The core Quick Sort idea stays the same, but the pivot selection can change.

### Your implementation

```text
First element
```

Other possible choices:

```text
Last element
Middle element
Random element
Median-based strategies
```

Changing the pivot-selection strategy can affect how balanced the partitions are.

But the core pattern remains:

```text
Pivot → Partition → Recurse
```

---

# 🎯 Placement-Level Takeaway

When you encounter a sorting problem, ask:

### Question 1

> Can I choose an element as a pivot?

### Question 2

> Can I put that pivot into its final position?

### Question 3

> Can I independently solve the two resulting sides?

If the answer is yes:

```text
Think Quick Sort.
```

### Derivation

```text
Sorting problem
      ↓
Choose pivot
      ↓
Partition
      ↓
Pivot reaches final position
      ↓
Left and right become independent
      ↓
Recursively solve both
```

Don't start by memorizing:

```python
quickSort(...)
partition(...)
```

Start with the reasoning:

> **"I will permanently place one element, then solve everything on either side."**

---

# ⚡ 30-Second Cheat Sheet

```text
QUICK SORT
────────────────────────────

Pattern:
→ Divide and Conquer

Core:
→ Choose pivot
→ Partition
→ Pivot becomes fixed
→ Recursively sort both sides

My pivot:
→ First element

Partition:
→ i = low
→ j = low + 1
→ if arr[j] <= pivot:
      i++
      swap(arr[i], arr[j])

After scan:
→ swap(arr[i], arr[low])
→ return i

Recursion:
→ quickSort(low, pivot - 1)
→ quickSort(pivot + 1, high)

Best:
→ O(n log n)

Average:
→ O(n log n)

Worst:
→ O(n²)

Space:
→ O(log n) average
→ O(n) worst

In-place:
→ Yes

Stable:
→ No

Key idea:
→ Partition first, recursively sort later.
```

---

# 🧠 One-Sentence Memory

> **Quick Sort chooses a pivot, partitions the array so the pivot reaches its final position, and recursively sorts the two sides.**
