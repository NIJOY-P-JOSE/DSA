# Bubble Sort

> **Pattern:** Comparison-Based Sorting · Adjacent Swapping  
> **Difficulty:** Easy  
> **Platform:** GeeksforGeeks  
> **Problem:** [Bubble Sort](https://www.geeksforgeeks.org/problems/bubble-sort/1)

---

## ⚡ Quick Revision

| Concept | Remember |
|---|---|
| Goal | Sort the array in ascending order |
| Pattern | Comparison-Based Sorting |
| Main Idea | Repeatedly compare adjacent elements and swap if they are in the wrong order |
| Key Observation | After every pass, the largest unsorted element reaches the end |
| Optimization | Stop early if a complete pass makes no swaps |
| Best Time | **O(n)** with early termination |
| Average Time | **O(n²)** |
| Worst Time | **O(n²)** |
| Space | **O(1)** |
| Stable? | **Yes** |

---

## 🧠 Problem in Simple Words

Given an array, sort its elements in **ascending order using Bubble Sort**.

For example:

```text
[4, 1, 3, 9, 7]

        ↓

[1, 3, 4, 7, 9]
```

The important idea is that we repeatedly compare **neighbouring elements**.

If the left element is greater than the right element, swap them.

---

## 🔥 How to Recognize Bubble Sort

For a sorting problem, if the expected approach involves:

- Comparing **adjacent elements**
- Swapping neighbouring elements when they are in the wrong order
- Repeatedly traversing the array
- One extreme element becoming fixed after every pass

think:

> **Adjacent comparison + repeated swapping → Bubble Sort**

### Recognition Trigger

```text
Adjacent elements
       ↓
Compare
       ↓
Wrong order?
       ↓
Swap
       ↓
Repeat passes
```

The important distinction is that Bubble Sort works through **local adjacent comparisons**, unlike Selection Sort, which searches for a minimum/maximum element.

---

## 🧩 How to Think / Derive the Solution

Instead of memorizing the code, derive it from the behavior of the algorithm.

### Step 1 — How can we sort?

We need smaller elements to move toward the beginning and larger elements toward the end.

Consider:

```text
[4, 1, 3, 9, 7]
```

Look at adjacent elements:

```text
4  1
```

Since:

```text
4 > 1
```

swap them:

```text
1  4
```

Now compare:

```text
4  3
```

Again:

```text
4 > 3
```

Swap:

```text
3  4
```

Continue this process.

---

### Step 2 — What happens after one complete pass?

Consider:

```text
[4, 1, 3, 9, 7]
```

After comparing adjacent elements throughout the array:

```text
[1, 3, 4, 7, 9]
```

The largest element, `9`, has **bubbled to the last position**.

Therefore:

> After every pass, the largest element in the remaining unsorted portion is placed at the end.

---

### Step 3 — Do we need to check the sorted element again?

No.

After the first pass:

```text
[1, 3, 4, 7, 9]
                 ↑
              sorted
```

So the next pass only needs to consider:

```text
[1, 3, 4, 7]
```

After every pass, the unsorted portion becomes smaller.

```text
Pass 1 → [1, 3, 4, 7] | 9
Pass 2 → [1, 3, 4]     | 7 9
Pass 3 → [1, 3]        | 4 7 9
...
```

---

## 📊 Visual Explanation

The key idea of Bubble Sort is:

```text
[4, 1, 3, 9, 7]

 4 > 1 → swap
[1, 4, 3, 9, 7]

 4 > 3 → swap
[1, 3, 4, 9, 7]

 4 < 9 → no swap
[1, 3, 4, 9, 7]

 9 > 7 → swap
[1, 3, 4, 7, 9]
             ↑
        largest fixed
```

Then repeat for the remaining unsorted portion.

---

## 🔄 Algorithm

1. Start from the first element.
2. Compare the current element with the next element.
3. If the current element is greater, swap them.
4. Continue until the end of the unsorted portion.
5. The largest element is now at the end.
6. Reduce the unsorted portion.
7. Repeat until the array is sorted.
8. If a complete pass produces **no swaps**, stop early.

---

## 💡 Early Termination Optimization

A basic Bubble Sort can continue making passes even when the array has already become sorted.

We can avoid this using:

```python
swap = False
```

At the beginning of every pass.

Whenever a swap occurs:

```python
swap = True
```

After the pass:

```python
if not swap:
    break
```

### Why does this work?

If we go through the entire unsorted portion and **never swap anything**, then every adjacent pair is already in the correct order.

Therefore, the entire array is sorted.

```text
No swaps in a complete pass
            ↓
No adjacent elements are out of order
            ↓
Array is sorted
            ↓
Stop
```

This changes the best-case complexity from **O(n²)** to **O(n)**.

---

## 💻 My Solution

```python
class Solution:
    def bubbleSort(self, arr):
        n = len(arr)

        for i in range(n - 1):
            for j in range(n - i - 1):
                if arr[j] > arr[j + 1]:
                    arr[j], arr[j + 1] = arr[j + 1], arr[j]
```

### Code Explanation

#### 1. Get the array size

```python
n = len(arr)
```

Stores the number of elements in the array.

---

#### 2. Outer loop

```python
for i in range(n - 1):
```

Each iteration represents one Bubble Sort pass.

After every pass, one more largest element reaches its final position.

---

#### 3. Inner loop

```python
for j in range(n - i - 1):
```

The `- i` is important because the last `i` elements are already sorted.

For example:

```text
Pass 1 → check n-1 elements
Pass 2 → check n-2 elements
Pass 3 → check n-3 elements
```

---

#### 4. Compare adjacent elements

```python
if arr[j] > arr[j + 1]:
```

If the left element is greater than the right element, they are in the wrong order.

---

#### 5. Swap

```python
arr[j], arr[j + 1] = arr[j + 1], arr[j]
```

Swap the two adjacent elements.

Python allows the swap to be written without a temporary variable.

---

## ⚠️ Important Difference: Your Earlier Implementation

You previously wrote an optimized version using:

```java
boolean swap;
```

and stopped when no swap occurred.

The GeeksforGeeks implementation provided here does **not** use that early-termination optimization.

So:

### GFG implementation

```text
Best:    O(n²)
Average: O(n²)
Worst:   O(n²)
```

### Your optimized implementation

```text
Best:    O(n)
Average: O(n²)
Worst:   O(n²)
```

Both are valid Bubble Sort implementations.

---

## 🔗 Pattern Connection

Bubble Sort belongs to:

```text
Sorting
   ↓
Comparison-Based Sorting
   ↓
Adjacent Comparison
   ↓
Repeated Swapping
   ↓
Bubble Sort
```

Compare this with Selection Sort:

```text
Selection Sort
      ↓
Find minimum/maximum
      ↓
Place it in correct position
```

Whereas Bubble Sort:

```text
Bubble Sort
      ↓
Compare neighbours
      ↓
Swap if necessary
      ↓
Largest element bubbles to the end
```

### Key Difference

| Algorithm | Main Operation |
|---|---|
| Bubble Sort | Compare adjacent elements and swap |
| Selection Sort | Find minimum/maximum and swap |
| Insertion Sort | Insert an element into the sorted portion |

---

## ⚔️ Similar Problems / Variations

| Algorithm | Main Difference |
|---|---|
| Selection Sort | Finds the minimum/maximum directly |
| Insertion Sort | Inserts each element into its correct position |
| Merge Sort | Divides the array and merges sorted halves |
| Quick Sort | Uses partitioning around a pivot |

For placement preparation, understanding the **difference between Bubble, Selection, and Insertion Sort** is more useful than memorizing their code independently.

---

## 🚨 Common Mistakes

### 1. Wrong inner-loop boundary

Incorrect:

```python
for j in range(n):
```

The already sorted suffix does not need to be checked again.

Use:

```python
for j in range(n - i - 1):
```

---

### 2. Comparing the wrong elements

Bubble Sort compares:

```text
arr[j] and arr[j + 1]
```

not an arbitrary element with the current minimum/maximum.

---

### 3. Wrong swap condition

For ascending order:

```python
if arr[j] > arr[j + 1]:
```

For descending order:

```python
if arr[j] < arr[j + 1]:
```

---

### 4. Forgetting that the sorted portion grows

After every pass:

```text
Unsorted portion ↓
Sorted portion   ↑
```

So the inner loop becomes smaller.

---

### 5. Confusing Bubble Sort with Selection Sort

Bubble Sort does **not** search the entire unsorted array for the maximum.

It repeatedly performs:

```text
Adjacent comparison → swap → adjacent comparison → swap
```

The maximum reaches the end as a **result of these swaps**.

---

## ⏱️ Complexity

### Time

For the provided GFG implementation:

```text
Best Case:    O(n²)
Average Case: O(n²)
Worst Case:   O(n²)
```

There are nested loops, and the implementation does not stop early when the array is already sorted.

With the `swap` optimization you previously implemented:

```text
Best Case:    O(n)
Average Case: O(n²)
Worst Case:   O(n²)
```

### Space

```text
O(1)
```

Only a constant amount of extra space is required because the array is sorted in-place.

---

## 🎯 Placement-Level Takeaway

### ⭐ If you see this in a placement round...

```text
Need to sort an array
        ↓
Adjacent elements are repeatedly compared
        ↓
Swap when they are in the wrong order
        ↓
Largest unsorted element moves to the end
        ↓
Reduce the unsorted range
        ↓
Bubble Sort
```

If the array can already be sorted and you want to avoid unnecessary passes:

```text
Add swap flag
     ↓
No swap during a complete pass?
     ↓
Array already sorted
     ↓
Break
```

---

## ⚡ Final 30-Second Cheat Sheet

```text
╔══════════════════════════════════════════════╗
║              QUICK REVISION                  ║
╠══════════════════════════════════════════════╣
║ Pattern: Adjacent comparison + swapping      ║
║ Key idea: Largest unsorted element           ║
║           bubbles to the end                 ║
║ Inner loop: n - i - 1                        ║
║ Ascending: swap when arr[j] > arr[j+1]       ║
║ Optimization: Stop if no swaps occur        ║
║ Best: O(n) with optimization                 ║
║ Average: O(n²)                               ║
║ Worst: O(n²)                                 ║
║ Space: O(1)                                  ║
║ Stable: Yes                                  ║
╚══════════════════════════════════════════════╝
```

> **⭐ One sentence to remember:**  
> **Bubble Sort repeatedly swaps adjacent elements so that the largest remaining element bubbles to the end of the unsorted portion.**

---

## 👤 Author

**Nijoy P Jose**

This solution is part of my **Data Structures & Algorithms** placement preparation repository, where I document problem-solving patterns, interview techniques, and Java/Python implementations to strengthen my coding skills.
