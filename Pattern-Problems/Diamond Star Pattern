# Diamond Star Pattern

## Problem
Print a diamond star (`*`) pattern for a given integer `n`.

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
 * * * *
  * * *
   * *
    *
```

---

## Approach

The pattern consists of two parts:

1. **Upper Half**
   - The number of stars increases from `1` to `n`.
   - The number of leading spaces decreases.

2. **Lower Half**
   - The number of stars decreases from `n-1` to `1`.
   - The number of leading spaces increases.

Instead of writing two separate loops, this solution uses a single loop and a variable `c` to keep track of the number of stars.

- If the current row is in the upper half, increment `c`.
- Otherwise, decrement `c`.
- Print `(n - c)` leading spaces.
- Print `c` stars.

---

## Algorithm

1. Read the integer `n`.
2. Initialize `c = 0`.
3. Loop from `0` to `2 × n - 2`.
   - If the row is in the upper half, increment `c`.
   - Otherwise, decrement `c`.
   - Print `(n - c)` spaces.
   - Print `c` stars.
4. Move to the next line.

---

## Time Complexity

- **Time:** `O(n²)`
- **Space:** `O(1)`

---

## Java Solution

```java
import java.util.*;

class Main {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        int c = 0;

        for (int i = 0; i < n + n - 1; i++) {

            if (i < n)
                c++;
            else
                c--;

            for (int j = 0; j < n - c; j++)
                System.out.print(" ");

            for (int j = 0; j < c; j++)
                System.out.print("* ");

            System.out.println();
        }
    }
}
```

---

## Key Concept

The variable `c` represents the number of stars in the current row.

| Row | Stars (`c`) |
|-----|------------:|
| 1 | 1 |
| 2 | 2 |
| 3 | 3 |
| ... | ... |
| n | n |
| n+1 | n-1 |
| ... | ... |
| Last | 1 |

This eliminates the need for separate loops for the upper and lower halves, making the solution concise and easy to understand.

---

## Tags

`Pattern` `Diamond Pattern` `Star Pattern` `Loops` `Beginner`
