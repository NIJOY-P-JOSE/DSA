# Selection Sort

**Difficulty:** Easy  
**Pattern:** Comparison-Based Sorting · Selection  
**Platform:** GeeksforGeeks

---

## 🔗 Problem

**Selection Sort** — sort the given array in increasing order by repeatedly selecting the minimum element from the unsorted portion and placing it at the beginning.

---

## ⚡ Quick Revision

| Property | Selection Sort |
|---|---|
| **Goal** | Sort array in increasing order |
| **Main Pattern** | Selection + comparison |
| **Core Idea** | Find minimum in the unsorted portion |
| **Placement** | Put minimum at the beginning of unsorted portion |
| **Sorted Region** | Grows from left → right |
| **Time — Best** | `O(n²)` |
| **Time — Average** | `O(n²)` |
| **Time — Worst** | `O(n²)` |
| **Extra Space** | `O(1)` |
| **In-place** | Yes |
| **Stable** | No, in the general implementation |

### 🧠 One-line memory

> **Find the minimum → put it in its correct position → repeat.**

---

## 📝 Problem in Simple Words

We have an array that may be in any order.

At every position:

1. Look through the remaining unsorted elements.
2. Find the smallest element.
3. Swap it with the first element of the unsorted region.
4. Move the boundary of the sorted region one position forward.

The array gradually becomes:

```text
[SORTED | UNSORTED]
```

and the sorted part grows from left to right.

---

## 🔍 How to Recognize Selection Sort

Look for these clues:

- You need to sort an array.
- You repeatedly **search for the minimum/maximum**.
- After finding it, you **place it at a fixed position**.
- The sorted portion grows one element at a time.
- You don't need to shift a whole section like insertion sort.

### Typical trigger

```text
Find minimum
      ↓
Place minimum at current position
      ↓
Increase sorted region
      ↓
Repeat
```

### Don't confuse it with:

**Bubble Sort**
- Compares neighboring elements.
- Swaps adjacent elements.
- Large elements gradually move toward the end.

**Insertion Sort**
- Takes the next element.
- Inserts it into an already sorted portion.
- Usually involves shifting elements.

**Selection Sort**
- Searches the entire remaining unsorted region.
- Selects one minimum.
- Performs one final swap per pass.

---

## 🧠 How to Think / Derive the Solution

Suppose:

```text
[4, 1, 3, 9, 7]
```

### Step 1 — What should happen at index `0`?

For increasing order, the smallest element should eventually be at index `0`.

So search:

```text
4  1  3  9  7
   ↑
minimum = 1
```

Swap `1` with `4`:

```text
[1, 4, 3, 9, 7]
```

Now index `0` is permanently sorted.

---

### Step 2 — What should happen at index `1`?

The remaining unsorted portion is:

```text
[4, 3, 9, 7]
```

Find its minimum:

```text
minimum = 3
```

Place it at index `1`:

```text
[1, 3, 4, 9, 7]
```

---

### Step 3 — Continue

```text
[1, 3 | 4, 9, 7]
```

Minimum of the remaining portion:

```text
4
```

Already in the correct position.

```text
[1, 3, 4 | 9, 7]
```

Then:

```text
minimum = 7
```

```text
[1, 3, 4, 7 | 9]
```

Finally:

```text
[1, 3, 4, 7, 9]
```

The key observation is:

> After every pass, the current position contains the smallest element from the remaining unsorted portion.



---

## 🎯 The Important State

At any point:

```text
[ sorted | unsorted ]
          ↑
       current i
```

For iteration `i`:

- `0 ... i-1` → already sorted
- `i ... n-1` → still unsorted
- Find the minimum in `i ... n-1`
- Put that minimum at `i`

This is the central idea behind the algorithm.

---

## 🔄 Algorithm

For every position `i` from `0` to `n-2`:

1. Assume `i` contains the minimum.
2. Search from `i + 1` to the end.
3. Keep track of the index of the smallest element.
4. Swap the smallest element with `arr[i]`.
5. Continue with the next position.

### Pseudocode

```text
for i = 0 to n-2

    minIndex = i

    for j = i+1 to n-1
        if arr[j] < arr[minIndex]
            minIndex = j

    swap(arr[i], arr[minIndex])
```

---

## 💻 My Solution

Your implementation follows the standard **minimum-selection approach**:

- `i` represents the first position of the unsorted region.
- `minIndex` stores the position of the smallest element found so far.
- `j` scans the remaining unsorted elements.
- Whenever a smaller element is found, `minIndex` is updated.
- After the scan, the minimum is swapped into position `i`.

The important part is that you store the **index** of the minimum rather than repeatedly swapping while searching.

### Why this works

Consider:

```text
[4, 1, 3, 9, 7]
 ↑
 i
```

During the inner loop:

```text
minIndex → 0
```

Then:

```text
1 < 4
```

so:

```text
minIndex → 1
```

Later:

```text
3 < 1   ❌
9 < 1   ❌
7 < 1   ❌
```

Therefore:

```text
minIndex = 1
```

Swap index `0` and `1`:

```text
[1, 4, 3, 9, 7]
```

The first position is now finalized.

---

## 🧩 Why the Algorithm Works

After each iteration:

```text
arr[0 ... i]
```

contains the smallest `i + 1` elements in sorted order.

Why?

Because before placing `arr[i]`, we search **every element from `i` to `n-1`** and select the smallest one.

Therefore no remaining element can be smaller than the element placed at `i`.

So the sorted region is safe to leave untouched.

---

## 🆚 Selection Sort vs Other Basic Sorting

| Algorithm | Main Action | Sorted Region | Best | Average | Worst |
|---|---|---|---:|---:|---:|
| **Selection Sort** | Find minimum and place it | Left → Right | `O(n²)` | `O(n²)` | `O(n²)` |
| **Bubble Sort** | Swap adjacent elements | Right side grows | `O(n)`* | `O(n²)` | `O(n²)` |
| **Insertion Sort** | Insert next element | Left side grows | `O(n)` | `O(n²)` | `O(n²)` |

\* With the early-termination optimization.

### The easiest way to remember

```text
Selection → SELECT minimum
Bubble    → SWAP neighbors
Insertion → INSERT into sorted portion
```

---

## ⚠️ Common Mistakes

### 1. Starting `j` from `0`

Wrong idea:

```text
for j = 0
```

The already-sorted portion should not be searched again.

Correct:

```text
j = i + 1
```

---

### 2. Swapping every time a smaller element is found

You don't need to immediately swap.

Instead:

```text
Find minimum index
        ↓
Finish scanning
        ↓
Perform one swap
```

This is the standard selection-sort approach.

---

### 3. Using the value instead of the index

You need to know **where** the minimum is so that you can swap it.

Therefore store:

```text
minIndex
```

not simply:

```text
minimum
```

---

### 4. Forgetting that the last element needs no separate pass

Once positions `0 ... n-2` are correctly placed, the final element is automatically in its correct position.

Therefore:

```text
i < n - 1
```

is sufficient.

---

## ⏱️ Complexity

### Time

For the first pass, search approximately `n` elements.

Second pass:

```text
n - 1
```

Third:

```text
n - 2
```

and so on.

Total comparisons:

```text
(n-1) + (n-2) + ... + 1
```

which gives:

```text
O(n²)
```

Importantly, **Selection Sort still performs these comparisons even if the array is already sorted**.

Therefore:

- Best: `O(n²)`
- Average: `O(n²)`
- Worst: `O(n²)`

### Space

Only a few variables are used:

```text
i
j
minIndex
temporary swap variable
```

So extra space is:

```text
O(1)
```

It is an **in-place sorting algorithm**.

---

## 🔗 Pattern Connection

Selection Sort belongs to:

```text
Sorting
   │
   └── Comparison-based sorting
          │
          ├── Selection Sort
          ├── Bubble Sort
          └── Insertion Sort
```

The important pattern is:

> **Search the unsorted region → select the element that belongs at the current position → place it.**

This idea of maintaining a **processed region + unprocessed region** appears in many other algorithms.

---

## 🔁 Similar Problems / Variations

### Selection Sort — Maximum Version

Instead of:

```text
Find minimum → place at beginning
```

you can:

```text
Find maximum → place at end
```

This is the approach used in your earlier Selection Sort implementation.

### Descending Order

For descending order:

```text
Find maximum
→ place it at the beginning
→ repeat
```

The main idea stays the same; only the comparison direction changes.

---

## 🎯 Placement-Level Takeaway

When you see a sorting problem, first ask:

```text
What am I doing during each pass?
```

If your answer is:

> "I'm searching the remaining unsorted elements for the minimum and putting it at the current position."

Think:

**Selection Sort.**

### Decision process

```text
Need to sort?
     ↓
Can I select the correct element for
the current position?
     ↓
YES
     ↓
Search remaining unsorted region
     ↓
Find minimum
     ↓
Swap into current position
     ↓
Move boundary forward
```

Don't memorize the nested loops first.

Memorize the **decision**:

> **"What element belongs at this position?" → Find it → Place it.**

---

# ⚡ 30-Second Cheat Sheet

```text
SELECTION SORT

Pattern:
→ Select minimum from unsorted region

Structure:
[SORTED | UNSORTED]

For each i:
→ minIndex = i
→ scan j = i+1 ... n-1
→ find smallest
→ swap arr[i] and arr[minIndex]

Sorted region:
→ grows from left to right

Time:
→ Best    O(n²)
→ Average O(n²)
→ Worst   O(n²)

Space:
→ O(1)

In-place:
→ Yes

Stable:
→ No, in general

Memory:
→ "Find the minimum and put it where it belongs."
```

## 🧠 One-Sentence Memory

> **Selection Sort repeatedly selects the smallest element from the unsorted portion and places it at the beginning of that portion.**
