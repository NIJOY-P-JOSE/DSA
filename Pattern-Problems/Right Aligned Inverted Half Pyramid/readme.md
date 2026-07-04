# Right Aligned Inverted Half Pyramid

## Problem

Print an inverted right-aligned half pyramid of stars.

### Example

**Input**
```
5
```

**Output**
```
* * * * *
  * * * *
    * * *
      * *
        *
```

---

## Approach

- Print increasing spaces.
- Print decreasing stars.

---

## Algorithm

1. Read `n`.
2. For each row:
   - Print leading spaces.
   - Print remaining stars.
3. Move to the next line.

---

## Time Complexity

- **Time:** `O(n²)`
- **Space:** `O(1)`

---

## Tags

`Pattern` `Star Pattern` `Inverted Pyramid` `Java`
