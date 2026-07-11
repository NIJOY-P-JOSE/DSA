 832. Flipping an Image

## Problem
Given an `n x n` binary matrix `image`, perform the following two operations:

1. **Flip Horizontally**
   - Reverse every row.

2. **Invert the Image**
   - Replace every `0` with `1`.
   - Replace every `1` with `0`.

Return the modified image.

**LeetCode:** 832 - Flipping an Image  
**Difficulty:** Easy  
**Asked In:** Google

---

## Approach

Instead of performing the two operations separately, both operations can be combined into a **single traversal**.

For every row:

- Use two pointers:
  - `start` at the beginning.
  - `end` at the end.
- Swap the elements while simultaneously inverting them using the XOR operation (`^ 1`).
- Continue until the two pointers meet.

For odd-length rows, when `start == end`, the middle element is simply inverted once.

---

## Why XOR?

The matrix contains only `0` and `1`.

Using

```java
x ^ 1
```

toggles the bit.

| Original | After `^ 1` |
|----------|-------------|
| 0 | 1 |
| 1 | 0 |

This avoids writing extra `if-else` conditions.

---

## Algorithm

For each row:

1. Initialize two pointers:
   - `start = 0`
   - `end = row.length - 1`
2. While `start <= end`
   - Store the left element.
   - Replace the left element with the inverted right element.
   - Replace the right element with the inverted stored left element.
   - Move both pointers inward.
3. Return the modified matrix.

---

## Dry Run

### Input

```
1 1 0
```

### Step 1

Swap and invert

```
1 1 0

↓

1 0 0
```

Explanation:

- Left gets `0 ^ 1 = 1`
- Right gets `1 ^ 1 = 0`

Middle element:

```
1 ^ 1 = 0
```

Final row:

```
1 0 0
```

---

## Complexity Analysis

**Time Complexity:** `O(n²)`

- Every element is processed exactly once.

**Space Complexity:** `O(1)`

- No extra matrix is used.

---

## Java Solution

```java
class Solution {
    public int[][] flipAndInvertImage(int[][] image) {

        for (int[] row : image) {
            int start = 0;
            int end = row.length - 1;

            while (start <= end) {
                int temp = row[start];
                row[start] = row[end] ^ 1;
                row[end] = temp ^ 1;

                start++;
                end--;
            }
        }

        return image;
    }
}
```

---

## Key Concepts

- Two Pointers
- Bit Manipulation
- XOR Operation
- Matrix Traversal
- In-place Algorithm

---

## Similar Problems

- 344. Reverse String
- 905. Sort Array By Parity
- 75. Sort Colors
- 48. Rotate Image
