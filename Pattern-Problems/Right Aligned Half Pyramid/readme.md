# Right Aligned Half Pyramid

## Problem

Print a right-aligned half pyramid of stars for a given integer `n`.

### Example

**Input**
```
5
```

**Output**
```
        *
      * *
    * * *
  * * * *
* * * * *
```

---

## Approach

- Print leading spaces before each row.
- The number of spaces decreases while the number of stars increases.

---

## Algorithm

1. Read `n`.
2. For each row:
   - Print `(n - currentRow)` spaces.
   - Print `currentRow` stars.
3. Move to the next line.

---

## Time Complexity

- **Time:** `O(n²)`
- **Space:** `O(1)`

---

## Tags

`Pattern` `Star Pattern` `Half Pyramid` `Java`
