# Bubble Sort

## Overview

Bubble Sort is one of the simplest comparison-based sorting algorithms. It repeatedly compares adjacent elements and swaps them if they are in the wrong order. After every pass, the largest element moves (or **"bubbles up"**) to its correct position at the end of the array.

This implementation includes an **early exit optimization**. If no swaps occur during a complete pass, the algorithm immediately stops because the array is already sorted.

---

## How It Works

1. Start from the beginning of the array.
2. Compare each pair of adjacent elements.
3. Swap them if the left element is greater than the right element.
4. Continue until the largest element reaches the end.
5. Ignore the last sorted element and repeat the process for the remaining unsorted portion.
6. If an entire pass completes without any swaps, terminate the algorithm early.

---

## Complexity Analysis

| Case                        | Time Complexity |
| --------------------------- | --------------- |
| Best Case (Already Sorted)  | **O(n)**        |
| Average Case                | **O(n²)**       |
| Worst Case (Reverse Sorted) | **O(n²)**       |
| Space Complexity            | **O(1)**        |

---

## Characteristics

* In-place sorting algorithm
* Stable sorting algorithm
* Easy to understand and implement
* Works well for small datasets
* Early termination optimization improves performance on nearly sorted arrays

---

## Example

**Input**

```text
[10, 3, 6, 3, 394, 7, 9, 0, 1]
```

**Output**

```text
[0, 1, 3, 3, 6, 7, 9, 10, 394]
```

---

## Key Insight

After each iteration, the **largest element in the unsorted portion** is guaranteed to be placed at its correct position at the end of the array. The size of the unsorted portion decreases by one after every pass.

---

## Optimization Used

This implementation uses a **swap flag** (`boolean swap`) to detect whether any elements were exchanged during a pass.

* If at least one swap occurs, another pass is required.
* If no swaps occur, the array is already sorted, and the algorithm terminates early.

This optimization improves the **best-case time complexity** from **O(n²)** to **O(n)**.
