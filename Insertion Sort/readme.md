# Insertion Sort

## Overview

This project demonstrates the **Insertion Sort** algorithm in Java.

Insertion Sort builds the sorted portion of the array one element at a time. During each iteration, the next element is inserted into its correct position within the already sorted part of the array.

---

## Algorithm

1. Assume the first element is already sorted.
2. Select the next element from the unsorted portion.
3. Compare it with elements on its left.
4. Shift (or swap) elements until the correct position is found.
5. Repeat until the entire array is sorted.

---

## Complexity Analysis

| Complexity       | Value     |
| ---------------- | --------- |
| Best Case        | **O(n)**  |
| Average Case     | **O(n²)** |
| Worst Case       | **O(n²)** |
| Space Complexity | **O(1)**  |

---

## Features

* In-place sorting algorithm
* Stable sorting algorithm
* Efficient for small datasets
* Performs well on nearly sorted arrays
* Simple and easy to understand

---

## Example

**Input**

```text
[10, 3, 6, 3, 312, 7, 4, 9, 1, 9987]
```

**Output**

```text
[1, 3, 3, 4, 6, 7, 9, 10, 312, 9987]
```

---

## Note

This implementation uses the **swap-based approach** to Insertion Sort. Instead of shifting elements and inserting the key separately, adjacent elements are swapped until the current element reaches its correct position. This approach is simple to understand and correctly implements the Insertion Sort algorithm.
