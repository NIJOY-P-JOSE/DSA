# Selection Sort (Maximum Element Approach)

## Overview

This project demonstrates the **Selection Sort** algorithm in Java.

Unlike the traditional implementation that repeatedly selects the **minimum element** and places it at the beginning, this implementation repeatedly selects the **maximum element** from the unsorted portion of the array and places it at the end. Both approaches produce the same sorted array.

---

## Algorithm

1. Consider the entire array as unsorted.
2. Find the maximum element in the unsorted portion.
3. Swap it with the last element of the unsorted portion.
4. Reduce the unsorted portion by one element.
5. Repeat until the array is completely sorted.

---

## Complexity Analysis

| Complexity                | Value     |
| ------------------------- | --------- |
| Time Complexity (Best)    | **O(n²)** |
| Time Complexity (Average) | **O(n²)** |
| Time Complexity (Worst)   | **O(n²)** |
| Space Complexity          | **O(1)**  |

---

## Features

* In-place sorting algorithm
* No extra memory required
* Simple and easy to understand
* Demonstrates Selection Sort using the **maximum element approach**

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

This implementation was written independently to understand the logic behind Selection Sort. It is a valid variation of the algorithm that selects the maximum element in each pass instead of the minimum element.
