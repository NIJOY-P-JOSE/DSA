# Merge Sort

**Difficulty:** Medium  
**Pattern:** Divide and Conquer + Recursion + Merging  
**Algorithm:** Merge Sort  
**Language:** Python

---

## 🔗 Problem

[Merge Sort — GeeksforGeeks](https://www.geeksforgeeks.org/problems/merge-sort/1)

---

## ⚡ Quick Revision

| Property | Details |
|---|---|
| Main Pattern | Divide and Conquer |
| Core Idea | Divide the array into halves, sort each half, then merge |
| Technique | Recursion + Two Pointers |
| Best Time | `O(n log n)` |
| Average Time | `O(n log n)` |
| Worst Time | `O(n log n)` |
| Auxiliary Space | `O(n)` |
| In-place | ❌ No |
| Stable | ✅ Yes |

### 30-second idea

> **Split → Sort left → Sort right → Merge**

The important part is that **merging two already sorted arrays is easy**.

---

# 🧠 Problem in Simple Words

Given an array and its starting index `l` and ending index `r`, sort the elements between those indexes using **Merge Sort**.

Example:

```text
[4, 1, 3, 9, 7]

→ [4, 1] + [3, 9, 7]
→ sort both parts
→ merge them
→ [1, 3, 4, 7, 9]
```

---

# 🔍 How to Recognize Merge Sort in a Placement

Look for these clues:

- The problem asks you to **sort an array**.
- The expected approach involves **divide and conquer**.
- The array can be repeatedly divided into smaller parts.
- Two already sorted portions need to be combined.
- You see a recursive structure involving:
  - left half
  - right half
  - merge

### Main trigger

> **"Divide the array into halves and combine sorted halves."**

Think:

```text
Merge Sort
    ↓
Divide
    ↓
Recursively sort
    ↓
Merge
```

---

# 💡 How to Derive the Solution

Instead of memorizing the Merge Sort code, derive it from the problem.

## Step 1 — Can we sort the entire array directly?

Suppose:

```text
[4, 1, 3, 9, 7]
```

Sorting everything at once is difficult.

What if we divide it?

```text
[4, 1]       [3, 9, 7]
```

Now each part is smaller.

---

## Step 2 — Keep dividing

```text
[4, 1]        [3, 9, 7]

[4] [1]       [3] [9, 7]

              [9] [7]
```

Eventually every part contains only one element.

A single element is already sorted.

This gives us the **base case**:

```python
if l < r:
```

If `l == r`, there is only one element, so nothing needs to be done.

---

# 🔄 Step 3 — How do we combine them?

Suppose we have:

```text
Left:  [1, 4]
Right: [3, 7, 9]
```

Both are already sorted.

Compare their first elements:

```text
1 vs 3 → take 1
4 vs 3 → take 3
4 vs 7 → take 4
7 vs 7 → take 7
```

Result:

```text
[1, 3, 4, 7, 9]
```

This is the **merge step**.

---

# 🎯 Key Observation

The important insight behind Merge Sort is:

> **If two arrays are already sorted, they can be merged into one sorted array in linear time.**

That is why we don't need to sort the merged result again.

---

# 🧩 Step 4 — Finding the Middle

For a range:

```text
l ........ r
```

we divide it using:

```python
m = (l + r) // 2
```

So:

```text
l ........ m | m+1 ........ r
       Left  |     Right
```

Then recursively sort both sides:

```python
self.mergeSort(arr, l, m)
self.mergeSort(arr, m + 1, r)
```

Finally:

```python
self.merge(arr, l, m, r)
```

---

# 🧑‍💻 My Solution

```python
class Solution:
    def mergeSort(self, arr, l, r):
        if l < r:
            m = (l + r) // 2

            self.mergeSort(arr, l, m)
            self.mergeSort(arr, m + 1, r)

            self.merge(arr, l, m, r)

    def merge(self, arr, l, m, r):
        left = arr[l:m + 1]
        right = arr[m + 1:r + 1]

        i = 0
        j = 0
        k = l

        while i < len(left) and j < len(right):
            if left[i] <= right[j]:
                arr[k] = left[i]
                i += 1
            else:
                arr[k] = right[j]
                j += 1

            k += 1

        while i < len(left):
            arr[k] = left[i]
            i += 1
            k += 1

        while j < len(right):
            arr[k] = right[j]
            j += 1
            k += 1
```

---

# 🔎 Code Explanation

## 1. Base Case

```python
if l < r:
```

If:

```text
l == r
```

there is only one element.

Therefore, it is already sorted.

---

## 2. Find Middle

```python
m = (l + r) // 2
```

This divides the current range into two parts:

```text
[l ... m]
[m+1 ... r]
```

---

## 3. Sort Left Half

```python
self.mergeSort(arr, l, m)
```

The left half is recursively divided until single-element arrays are reached.

---

## 4. Sort Right Half

```python
self.mergeSort(arr, m + 1, r)
```

The same process happens for the right half.

---

## 5. Merge

```python
self.merge(arr, l, m, r)
```

At this point:

```text
arr[l ... m]
```

and

```text
arr[m+1 ... r]
```

are already sorted.

Now they are merged.

---

# 🔀 Understanding the `merge()` Function

## Create temporary arrays

```python
left = arr[l:m + 1]
right = arr[m + 1:r + 1]
```

For example:

```text
arr = [1, 4, 3, 7, 9]

l = 0
m = 1
r = 4
```

We get:

```text
left  = [1, 4]
right = [3, 7, 9]
```

---

## Three pointers

```python
i = 0
j = 0
k = l
```

Their roles are different:

```text
i → current element in left
j → current element in right
k → position in original arr
```

Think:

```text
left[i] ─┐
         ├──→ arr[k]
right[j]─┘
```

### Important

`k` starts at `l`, **not 0**.

Why?

Because we are merging only the portion:

```text
arr[l ... r]
```

So the first value must be written at index `l`.

---

# ⚔️ Comparing Both Halves

```python
while i < len(left) and j < len(right):
```

Continue while both arrays still have elements.

Then:

```python
if left[i] <= right[j]:
    arr[k] = left[i]
    i += 1
else:
    arr[k] = right[j]
    j += 1
```

We always choose the smaller front element.

---

# 📌 Why `<=` Instead of `<`?

Your code uses:

```python
if left[i] <= right[j]:
```

When both values are equal, the element from the **left** array is selected first.

This preserves their original relative order.

Therefore, this implementation is **stable**.

---

# 🧹 Remaining Elements

Eventually one side becomes empty.

For example:

```text
left  = [1, 4]
right = [3]
```

After taking:

```text
1
3
```

we still have:

```text
4
```

So this loop copies the remaining left elements:

```python
while i < len(left):
    arr[k] = left[i]
    i += 1
    k += 1
```

Similarly, the remaining right elements are copied:

```python
while j < len(right):
    arr[k] = right[j]
    j += 1
    k += 1
```

---

# 🧪 Dry Run

Consider:

```text
[4, 1, 3, 9, 7]
```

## Divide

```text
              [4, 1, 3, 9, 7]
                     ↓
             [4, 1]    [3, 9, 7]
              ↓           ↓
           [4] [1]     [3] [9, 7]
                           ↓
                         [9] [7]
```

---

## Merge `[4]` and `[1]`

```text
4 vs 1 → take 1
4 remains → take 4

[1, 4]
```

---

## Merge `[9]` and `[7]`

```text
9 vs 7 → take 7
9 remains → take 9

[7, 9]
```

---

## Merge `[3]` and `[7, 9]`

```text
3 vs 7 → take 3
7 vs 9 → take 7
9 remains → take 9

[3, 7, 9]
```

---

## Final Merge

```text
Left:  [1, 4]
Right: [3, 7, 9]
```

Compare:

```text
1 vs 3 → 1
4 vs 3 → 3
4 vs 7 → 4
7 vs 7 → 7
9 remains → 9
```

Final:

```text
[1, 3, 4, 7, 9]
```

---

# 🆚 Merge Sort vs Quick Sort

| Feature | Merge Sort | Quick Sort |
|---|---|---|
| Main Pattern | Divide & Conquer | Divide & Conquer |
| Division | Split in half | Partition around pivot |
| Best Time | `O(n log n)` | `O(n log n)` |
| Average Time | `O(n log n)` | `O(n log n)` |
| Worst Time | `O(n log n)` | `O(n²)` |
| Extra Array Space | `O(n)` | Usually `O(log n)` stack |
| Stable | ✅ Yes | ❌ Usually no |
| In-place | ❌ No | ✅ Mostly |
| Key Operation | Merge | Partition |

### Mental distinction

```text
Merge Sort → "Split equally, then MERGE"

Quick Sort → "Choose pivot, then PARTITION"
```

---

# ⚠️ Common Mistakes

### 1. Wrong starting index for `k`

❌ Wrong:

```python
k = 0
```

Correct:

```python
k = l
```

Because the merge is performed only on:

```text
arr[l ... r]
```

---

### 2. Mixing temporary-array indexes with original-array indexes

Remember:

```text
i → left
j → right
k → arr
```

---

### 3. Wrong slicing

For inclusive boundaries:

```python
left = arr[l:m + 1]
right = arr[m + 1:r + 1]
```

The `+1` is important because Python's ending index is exclusive.

---

### 4. Forgetting remaining elements

After the main comparison loop, one side can still contain elements.

Therefore, both are required:

```python
while i < len(left):
```

and

```python
while j < len(right):
```

---

### 5. Wrong recursive ranges

Correct:

```python
self.mergeSort(arr, l, m)
self.mergeSort(arr, m + 1, r)
```

Do not overlap the ranges.

---

# ⏱️ Complexity

## Time Complexity

At every level, the array is divided into smaller halves.

There are approximately:

```text
log n
```

levels.

At each level, merging all elements takes:

```text
O(n)
```

Therefore:

```text
O(n) × O(log n)
= O(n log n)
```

So:

- Best: `O(n log n)`
- Average: `O(n log n)`
- Worst: `O(n log n)`

---

## Space Complexity

Temporary arrays are created during merging:

```python
left = ...
right = ...
```

Together they require:

```text
O(n)
```

The recursion stack adds:

```text
O(log n)
```

Overall auxiliary space is:

```text
O(n)
```

---

# 🎯 Placement-Level Takeaway

When you see a sorting problem, ask:

### Question 1

**Can I divide the problem into independent halves?**

If yes, think:

```text
Divide and Conquer
```

### Question 2

**If the halves are sorted, can I combine them efficiently?**

If yes:

```text
Merge Sort
```

### Question 3

**How do I merge two sorted arrays?**

Use two pointers:

```text
i → left
j → right
k → result
```

Take the smaller front element each time.

---

# 🔗 Pattern Connection

```text
                Divide & Conquer
                       │
             ┌─────────┴─────────┐
             ↓                   ↓
        Merge Sort           Quick Sort
             │                   │
          Divide              Pivot
             │                   │
       Sort both halves      Partition
             │                   │
           Merge              Recurse
```

The key difference:

```text
Merge Sort → work happens mainly during MERGE

Quick Sort → work happens mainly during PARTITION
```

---

# 🚨 What to Remember for Placements

If you forget the implementation, rebuild it from these four ideas:

```text
1. Find middle
2. Recursively sort left
3. Recursively sort right
4. Merge the two sorted halves
```

Then remember the merge pointers:

```text
i = left pointer
j = right pointer
k = original-array pointer
```

---

# 📝 30-Second Cheat Sheet

```text
MERGE SORT

Pattern:
Divide & Conquer

Steps:
1. Find middle
2. Divide into two halves
3. Recursively sort both halves
4. Merge sorted halves

Base Case:
l >= r

Middle:
m = (l + r) // 2

Recursive Calls:
mergeSort(l, m)
mergeSort(m+1, r)

Merge:
i → left
j → right
k → original array

Time:
O(n log n) in all cases

Space:
O(n)

Stable:
Yes

Memory:
"Split until single elements,
then merge sorted pieces."
```

## 🧠 One-Sentence Memory

> **Merge Sort divides the array until each part is trivially sorted, then repeatedly merges the sorted parts back together.**
