# Inverted Full Pyramid

## Problem

Print an inverted full pyramid.

### Example

**Input**
```
5
```

**Output**
```
*********
 *******
  *****
   ***
    *
```

---

## Approach

- Print increasing spaces.
- Print decreasing stars.

---

## Algorithm

1. Read `n`.
2. For every row:
   - Print leading spaces.
   - Print `(2 × remainingRows - 1)` stars.
3. Move to the next line.

---

## Time Complexity

- **Time:** `O(n²)`
- **Space:** `O(1)`

---

## Tags

`Pattern` `Star Pattern` `Inverted Full Pyramid` `Java`
