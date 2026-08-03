# Square Root of a Number using Newton-Raphson Method

> **Pattern:** Mathematical Optimization | Newton-Raphson (Babylonian) Method

## Problem Statement

Given a non-negative integer `n`, find its square root with high precision using the **Newton-Raphson Method**.

If `n` is a perfect square, return the exact square root. Otherwise, return the approximate square root up to **2 decimal places**.

Unlike Binary Search, this method repeatedly improves the current approximation until it converges to the actual square root.

---

## Examples

### Example 1

**Input**

```text
25
```

**Output**

```text
5.00
```

---

### Example 2

**Input**

```text
40
```

**Output**

```text
6.32
```

**Explanation**

```
√40 = 6.324555...
```

Rounded to two decimal places,

```
6.32
```

---

## 💡 Intuition

Suppose you are asked to find:

```
√40
```

Instead of searching from `1` to `40`, start with an initial guess.

For example,

```
x = 40
```

This guess is obviously far from the actual answer.

Can we improve it?

Imagine two numbers whose product is `40`.

```
40 × 1
20 × 2
10 × 4
8 × 5
```

As these two numbers get closer to each other, they approach

```
√40
```

The Newton-Raphson Method repeatedly averages the current guess with

```
n / currentGuess
```

to obtain a much better approximation.

Every iteration moves closer to the actual square root.

---

## 🧠 Why Does This Formula Work?

The Newton-Raphson formula is

\[
x_{new} = \frac{x + \frac{n}{x}}{2}
\]

where

- `x` is the current approximation.
- `n / x` is another approximation.

Taking their average gives a value much closer to the actual square root.

Unlike Binary Search, this method doesn't search a range.

Instead, it **iteratively improves** the current answer until two consecutive approximations become almost identical.

This is why Newton-Raphson converges extremely fast.

---

## 🔄 Dry Run

Suppose

```
n = 40
```

Initial Guess

```
x = 40
```

### Iteration 1

```
root = (40 + 40/40) / 2

= (40 + 1) / 2

= 20.5
```

---

### Iteration 2

```
root

= (20.5 + 40/20.5) / 2

≈ 11.2256
```

---

### Iteration 3

```
≈ 7.3945
```

---

### Iteration 4

```
≈ 6.4019
```

---

### Iteration 5

```
≈ 6.3249
```

---

### Iteration 6

```
≈ 6.324555
```

Difference between two consecutive answers becomes extremely small.

Stop.

Final Answer

```
6.324555...
```

Printed as

```
6.32
```

---

## 📝 Algorithm

1. Read the input number `n`.
2. If `n` is `0`, return `0`.
3. Assume the initial guess as `x = n`.
4. Compute

```
root = (x + n/x) / 2
```

5. Repeat until the difference between the current and previous approximation becomes very small.
6. Print the result.

---

## 💻 Java Solution

```java
import java.util.*;

public class Main {

    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);

        int n = s.nextInt();

        double ans = sqrt(n);

        System.out.printf("%.2f", ans);
    }

    static double sqrt(int n) {

        if (n == 0)
            return 0;

        double x = n;
        double root;

        while (true) {

            root = 0.5 * (x + (n / x));

            if (Math.abs(root - x) < 0.00001)
                break;

            x = root;
        }

        return root;
    }
}
```

---

## ⚠️ Common Mistakes

### 1. Forgetting the Base Case

If

```java
n == 0
```

then

```java
n / x
```

becomes

```
0 / 0
```

which results in `NaN`.

Always handle

```java
if (n == 0)
    return 0;
```

---

### 2. Using a Large Error Value

Using

```java
0.1
```

as the stopping condition gives poor accuracy.

A much better choice is

```java
0.00001
```

or

```java
1e-6
```

---

### 3. Infinite Loop

Always update

```java
x = root;
```

Otherwise, the approximation never changes.

---

### 4. Choosing a Bad Initial Guess

Starting with

```
x = n
```

works well for positive integers.

Other initial guesses are also possible, but this one is simple and converges quickly.

---

## 💼 Interview Notes

This problem tests your understanding of numerical algorithms.

Interviewers may ask:

- Why does Newton-Raphson converge?
- Why is it faster than Binary Search?
- What is the stopping condition?
- Can you explain the formula?
- Can this method be used for cube roots?

A common follow-up question is:

> "Which method would you choose: Binary Search or Newton-Raphson?"

A good answer is:

- Binary Search is simpler and easier to derive during interviews.
- Newton-Raphson is mathematically faster and converges in very few iterations.

Knowing both approaches demonstrates strong problem-solving skills.

---

## ⏱️ Complexity Analysis

The approximation improves rapidly after each iteration.

```
Time Complexity: O(log log n)
```

In practice, it converges within only a few iterations.

```
Space Complexity: O(1)
```

Only a few variables are used.

---

## 🧩 Pattern Used

**Newton-Raphson Method (Babylonian Method)**

Instead of searching for the answer, this method continuously improves an approximation using the formula

\[
x_{new} = \frac{x + \frac{n}{x}}{2}
\]

It is one of the fastest iterative methods for computing square roots.

---

## 🔗 Related Problems

- 69. Sqrt(x)
- 367. Valid Perfect Square
- Nth Root of a Number
- Cube Root using Newton-Raphson
- Square Root using Binary Search
- Newton-Raphson Method for Finding Roots of Equations

---

## 📚 Concepts Practiced

- Mathematics
- Newton-Raphson Method
- Babylonian Method
- Numerical Methods
- Iterative Algorithms
- Precision Handling
- Floating-Point Computation

---

## 🎯 Key Takeaways

- Newton-Raphson is a mathematical optimization technique for finding roots of equations.
- It converges much faster than Binary Search.
- Each iteration significantly improves the current approximation.
- A small stopping threshold (such as `1e-5`) provides high accuracy.
- This method is widely used in scientific computing, calculators, graphics, and engineering applications.

---

## 👨‍💻 Author

**Nijoy P Jose**

This solution is part of my **Data Structures & Algorithms** placement preparation repository, where I document problem-solving patterns, interview techniques, and Java implementations to strengthen my coding skills.
