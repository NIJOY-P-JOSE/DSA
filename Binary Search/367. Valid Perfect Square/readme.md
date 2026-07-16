# 307. Perfect Square using Binary Search

This repository contains a Java solution for **LeetCode 367 - Valid Perfect Square** using the **Binary Search** algorithm.

---

# 📌 Problem Statement

Given a positive integer `num`, determine whether it is a **perfect square**.

A perfect square is an integer that can be written as:

```text
x × x = num
```

where `x` is an integer.

You **must not** use any built-in square root functions such as `sqrt()`.

---

## Example 1

```text
Input:
num = 16

Output:
true

Explanation:
4 × 4 = 16
```

---

## Example 2

```text
Input:
num = 14

Output:
false

Explanation:
No integer squared equals 14.
```

---

# 💡 Intuition

Instead of checking every number from `1` to `num`, we can use **Binary Search**.

The possible square roots lie between:

```text
1 → num / 2
```

(Except for `num = 1`, which is handled separately.)

For every middle value:

* If `mid² == num`

  * Perfect square found.
* If `mid² < num`

  * Search the right half.
* If `mid² > num`

  * Search the left half.

---

# 🧠 Binary Search Thinking

Search Space

```text
1 ---------------- num/2
```

Choose

```text
mid
```

If

```text
mid² == num
```

Return `true`.

If

```text
mid² < num
```

Search right.

If

```text
mid² > num
```

Search left.

---

# 🔄 Algorithm

1. Handle `num == 1`.
2. Initialize:

   * `start = 1`
   * `end = num / 2`
3. Perform Binary Search.
4. Compare `mid²` with `num`.
5. Return `true` if found.
6. Otherwise return `false`.

---

# 📖 Dry Run

Example

```text
num = 16
```

### Iteration 1

```text
start = 1
end = 8

mid = 4
```

```text
4 × 4 = 16
```

Answer:

```text
true
```

---

Example

```text
num = 14
```

```text
start = 1
end = 7

mid = 4

4² = 16 > 14
```

Move left.

```text
end = 3
```

Continue Binary Search until the search space becomes empty.

Return

```text
false
```

---

# ⚠️ Common Mistake (Very Important)

## Integer Overflow

A common mistake is writing:

```java
if(mid * mid == num)
```

When `mid` becomes large,

```text
46341 × 46341
```

is greater than the maximum value of an `int`:

```text
2,147,483,647
```

This causes **integer overflow**, leading to incorrect comparisons and wrong answers.

### ✅ Correct Solution

Always perform the multiplication using `long`.

```java
long square = (long) mid * mid;
```

Then compare:

```java
if(square == num)
```

This safely handles all inputs within the problem constraints.

---

# 📝 Interview Notes

* This problem has the **same overflow issue** as **LeetCode 69 - Sqrt(x)**.
* Whenever you see:

```java
mid * mid
```

always ask:

> **Can this multiplication overflow?**

If yes,

* Cast to `long`, or
* Rewrite the comparison to avoid multiplication (when appropriate).

This is one of the most common Binary Search interview mistakes.

---

# ⏱️ Complexity Analysis

### Time Complexity

```text
O(log n)
```

Binary Search halves the search space every iteration.

### Space Complexity

```text
O(1)
```

Only constant extra memory is used.

---

# 💻 Java Solution

```java
class Solution {
    public boolean isPerfectSquare(int num) {

        if (num == 1)
            return true;

        int s = 1;
        int e = num / 2;

        while (s <= e) {

            int mid = s + (e - s) / 2;

            long square = (long) mid * mid;

            if (square == num)
                return true;
            else if (square < num)
                s = mid + 1;
            else
                e = mid - 1;
        }

        return false;
    }
}
```

---

# 🎯 Binary Search Pattern

This problem follows the **Binary Search on Numeric Values** pattern.

Instead of searching an array, Binary Search is performed over the range of possible square roots.

---

# 🔗 Related Problems

* 69. Sqrt(x)
* 374. Guess Number Higher or Lower
* 278. First Bad Version
* 441. Arranging Coins

---

# 📚 Concepts Practiced

* Binary Search
* Numeric Search Space
* Integer Overflow
* Overflow-safe Multiplication
* Interview Pattern Recognition

---

# 🚀 Author

**Nijoy P Jose**

This solution is part of my **Data Structures & Algorithms** placement preparation repository, where I document Binary Search patterns, interview techniques, and Java implementations to strengthen problem-solving skills.
