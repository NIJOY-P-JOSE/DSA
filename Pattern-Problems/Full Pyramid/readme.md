# Full Pyramid

## Problem

Print a full pyramid of stars.

### Example

**Input**
```
5
```

**Output**
```
    *
   ***
  *****
 *******
*********
```

---

## Approach

- Print leading spaces.
- Print `(2 × row - 1)` stars.

---

## Algorithm

1. Read `n`.
2. For every row:
   - Print spaces.
   - Print left stars.
   - Print right stars.
3. Move to the next line.

---

## Time Complexity

- **Time:** `O(n²)`
- **Space:** `O(1)`

---

## Tags

`Pattern` `Star Pattern` `Full Pyramid` `Java`
