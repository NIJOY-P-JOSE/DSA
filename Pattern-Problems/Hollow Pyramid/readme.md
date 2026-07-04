# Hollow Pyramid

## Problem

Print a hollow pyramid of stars.

### Example

**Input**
```
5
```

**Output**
```
    *
   * *
  *   *
 *     *
*********
```

---

## Approach

- Print stars only at the left and right boundaries.
- Print spaces inside the pyramid.
- Print a solid base in the last row.

---

## Algorithm

1. Read `n`.
2. For every row except the last:
   - Print stars at the boundary positions.
   - Print spaces elsewhere.
3. Print the base using `(2 × n - 1)` stars.

---

## Time Complexity

- **Time:** `O(n²)`
- **Space:** `O(1)`

---

## Tags

`Pattern` `Star Pattern` `Hollow Pyramid` `Java`
