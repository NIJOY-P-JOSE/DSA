# Sieve of Eratosthenes

## Problem
Given a number `n`, print all prime numbers from `2` to `n`.

## Approach
This solution uses the **Sieve of Eratosthenes**, an efficient algorithm for generating all prime numbers up to a given limit.

The algorithm works by:
1. Assuming all numbers are prime initially.
2. Starting from `2`, marking all of its multiples as non-prime.
3. Repeating the process for the next unmarked number.
4. Continuing until `i * i <= n`.
5. Printing all unmarked numbers as prime numbers.

## Algorithm
1. Create a boolean array of size `n + 1`.
2. Iterate from `2` to `√n`.
3. If the current number is not marked:
   - Mark all of its multiples as non-prime.
4. Print every unmarked number from `2` to `n`.

## Time Complexity
- **O(n log log n)**

## Space Complexity
- **O(n)**

## Example

### Input
```
20
```

### Output
```
2, 3, 5, 7, 11, 13, 17, 19
```

## Java Code

```java
import java.util.*;

public class Main
{
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();

        boolean[] a = new boolean[n + 1];

        for(int i = 2; i * i <= n; i++) {
            if(!a[i]) {
                for(int j = i * 2; j <= n; j += i)
                    a[j] = true;
            }
        }

        for(int i = 2; i <= n; i++) {
            if(!a[i])
                System.out.print(i + ", ");
        }
    }
}
```

## Notes
- This implementation starts marking multiples from `2 × i`.
- A common optimization is to start from `i × i`, since smaller multiples have already been marked by previous prime numbers.
- The Sieve of Eratosthenes is widely used in coding interviews, competitive programming, and mathematical algorithms involving prime numbers.

## Topics
- Mathematics
- Prime Numbers
- Sieve of Eratosthenes
- Arrays
- Number Theory
- DSA
