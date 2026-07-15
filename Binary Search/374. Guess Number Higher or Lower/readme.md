# 🎯 Guess Number Higher or Lower using Binary Search

This repository contains a Java solution for **LeetCode 374 - Guess Number Higher or Lower** using the **Binary Search** algorithm.

---

# 📌 Problem Statement

A number is secretly picked between **1** and **n**.

You can make guesses using the provided API:

```java id="v5h3ji"
guess(int num)
```

The API returns:

* `-1` → Your guess is **higher** than the picked number.
* `1` → Your guess is **lower** than the picked number.
* `0` → Your guess is **correct**.

Your task is to find the picked number while minimizing the number of guesses.

---

## Example 1

```text id="kg5jpr"
Input:
n = 10
pick = 6

Output:
6
```

---

## Example 2

```text id="s4mbup"
Input:
n = 1
pick = 1

Output:
1
```

---

## Example 3

```text id="h4s2z0"
Input:
n = 2
pick = 1

Output:
1
```

---

# 💡 Intuition

The secret number always lies within the range:

```text id="sm7v3w"
1 ... n
```

After every guess, the API tells us whether the answer is:

* Higher
* Lower
* Equal

This allows us to eliminate **half of the remaining search space** after each guess, making Binary Search the perfect solution.

---

# 🧠 Binary Search Thinking

Suppose:

```text id="6hkyx8"
n = 10
```

Search Space

```text id="q8isxb"
1 2 3 4 5 6 7 8 9 10
```

Choose the middle element.

### Case 1

```text id="lzjlwm"
guess(mid) == 0
```

The answer is found.

Return `mid`.

---

### Case 2

```text id="vdm0up"
guess(mid) == 1
```

The picked number is **greater** than `mid`.

Search the right half.

```text id="9k6lvj"
start = mid + 1
```

---

### Case 3

```text id="jlwmew"
guess(mid) == -1
```

The picked number is **smaller** than `mid`.

Search the left half.

```text id="cyv2zr"
end = mid - 1
```

---

# 🔄 Algorithm

1. Initialize:

   * `start = 1`
   * `end = n`
2. Compute the middle element.
3. Call the `guess()` API.
4. If:

   * `0` → Return `mid`.
   * `1` → Search the right half.
   * `-1` → Search the left half.
5. Repeat until the number is found.

---

# 📖 Dry Run

Example

```text id="0ggpcw"
n = 10
pick = 6
```

### Iteration 1

```text id="jlwm2d"
start = 1
end = 10

mid = 5
```

API

```text id="zomxps"
guess(5) = 1
```

Move right.

```text id="0db04m"
start = 6
```

---

### Iteration 2

```text id="h67u4k"
start = 6
end = 10

mid = 8
```

API

```text id="2ik2n4"
guess(8) = -1
```

Move left.

```text id="jlwmrk"
end = 7
```

---

### Iteration 3

```text id="e5p2jq"
start = 6
end = 7

mid = 6
```

API

```text id="jlwmdo"
guess(6) = 0
```

Return

```text id="x3g7rk"
6
```

---

# ⏱️ Complexity Analysis

### Time Complexity

```text id="g6btdo"
O(log n)
```

Each guess eliminates half of the remaining search space.

---

### Space Complexity

```text id="zbr9so"
O(1)
```

Only constant extra memory is used.

---

# 💻 Java Solution

```java id="jlwm9r"
/**
 * Forward declaration of guess API.
 * int guess(int num);
 */

public class Solution extends GuessGame {

    public int guessNumber(int n) {

        int s = 1;
        int e = n;

        while (s <= e) {

            int mid = s + (e - s) / 2;

            int result = guess(mid);

            if (result == 0)
                return mid;
            else if (result > 0)
                s = mid + 1;
            else
                e = mid - 1;
        }

        return -1;
    }
}
```

---

# 🎯 Binary Search Pattern

This problem demonstrates the **Classic Binary Search** pattern.

Instead of comparing values in an array, Binary Search uses the response from an API to decide which half of the search space can be discarded.

---

# 🔗 Related Problems

* Binary Search
* First Bad Version
* Search Insert Position
* Sqrt(x)
* Find Peak Element

---

# 📚 Concepts Practiced

* Binary Search
* Search Space Reduction
* Interactive API Problems
* Overflow-safe Mid Calculation
* Interview Pattern Recognition

---

# 🚀 Author

**Nijoy P Jose**

This solution is part of my **Data Structures & Algorithms** placement preparation repository, where I document Binary Search patterns, interview techniques, and Java implementations to strengthen problem-solving skills.
