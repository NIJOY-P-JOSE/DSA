# Least Common Multiple (LCM) using Euclidean Algorithm

> **Pattern:** Mathematics | Euclidean Algorithm | GCD & LCM

## Problem Statement

Given two positive integers `a` and `b`, find their **Least Common Multiple (LCM)**.

The LCM is the **smallest positive integer that is divisible by both numbers**.

---

## Examples

### Example 1

**Input**

```text
12 18
```

**Output**

```text
36
```

---

### Example 2

**Input**

```text
5 7
```

**Output**

```text
35
```

---

### Example 3

**Input**

```text
10 15
```

**Output**

```text
30
```

---

# 💡 Intuition

A simple way to find the LCM is to start from the larger number and keep checking its multiples until one is divisible by both numbers.

For example,

```
a = 12
b = 18
```

Multiples of 18

```
18
36
54
72
...
```

The first number divisible by both is

```
36
```

Although simple, this approach becomes inefficient for large numbers.

Instead, we use the relationship between **GCD** and **LCM**.

---

# 🧠 Key Observation

For any two positive integers,

```
GCD(a, b) × LCM(a, b) = a × b
```

Rearranging the formula,

```
LCM(a, b) = (a × b) / GCD(a, b)
```

Therefore, if we can compute the GCD efficiently, finding the LCM becomes straightforward.

---

# 🔄 Dry Run

Suppose

```
a = 12
b = 18
```

### Step 1

Find the GCD.

```
GCD(12,18)

↓

GCD(6,12)

↓

GCD(0,6)

↓

6
```

---

### Step 2

Apply the formula.

```
LCM

= (12 × 18) / 6

= 216 / 6

= 36
```

Final Answer

```
36
```

---

# 📝 Algorithm

1. Read two integers `a` and `b`.
2. Find their GCD using the Euclidean Algorithm.
3. Compute

```
LCM = (a × b) / GCD
```

4. Print the LCM.

---

# 💻 Java Solution

```java
import java.util.*;

public class Main {

    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);

        while (true) {

            // Read two numbers
            int a = s.nextInt();
            int b = s.nextInt();

            // Calculate and print the LCM
            System.out.println((a * b) / GCD(a, b));
        }
    }

    // Euclidean Algorithm to find GCD
    static int GCD(int a, int b) {

        // Base case:
        // If the first number becomes 0,
        // the second number is the GCD.
        if (a == 0)
            return b;

        // Recursive call
        return GCD(b % a, a);
    }
}
```

---

# ⚠️ Common Mistakes

### 1. Integer Overflow

This line

```java
a * b
```

can overflow when `a` and `b` are very large.

A safer implementation is

```java
(long)a * b / GCD(a, b)
```

or

```java
(a / GCD(a, b)) * b
```

This reduces the chance of overflow.

---

### 2. Forgetting the Formula

Many beginners confuse

```
LCM = GCD × a × b ❌
```

The correct formula is

```
LCM = (a × b) / GCD
```

---

### 3. Division Before Multiplication

Always ensure the division happens after multiplication (or divide one operand first to avoid overflow).

Incorrect:

```java
a * (b / GCD)
```

if `b` is not divisible by the GCD.

Preferred:

```java
(a / GCD) * b
```

---

### 4. Assuming GCD is Always 1

Only **co-prime numbers** have

```
GCD = 1
```

For all other numbers, compute the GCD first.

---

# 💼 Interview Notes

This is a very common interview problem.

Interviewers usually expect you to know:

- Euclidean Algorithm
- Relationship between GCD and LCM
- Why the formula works
- How to avoid integer overflow

Common follow-up questions:

- Find the GCD of an array.
- Find the LCM of an array.
- Simplify fractions using GCD.
- Check if two numbers are co-prime.

---

# ⏱️ Complexity Analysis

### Time Complexity

Finding the GCD takes

```
O(log(min(a, b)))
```

Computing the LCM after that is

```
O(1)
```

Overall

```
Time Complexity: O(log(min(a, b)))
```

---

### Space Complexity

Recursive GCD uses

```
O(log(min(a, b)))
```

due to the recursion stack.

An iterative GCD implementation reduces this to

```
O(1)
```

---

# 🧩 Pattern Used

**Euclidean Algorithm**

The LCM is calculated using the mathematical relationship

```
LCM(a, b) = (a × b) / GCD(a, b)
```

The Euclidean Algorithm efficiently computes the GCD, making the overall solution optimal.

---

# 🔗 Related Problems

- Greatest Common Divisor (GCD)
- GCD of an Array
- LCM of an Array
- Fraction Simplification
- Extended Euclidean Algorithm
- Bézout's Identity
- Co-Prime Numbers

---

# 📚 Concepts Practiced

- Mathematics
- Number Theory
- Euclidean Algorithm
- GCD
- LCM
- Recursion

---

# 🎯 Key Takeaways

- Always compute the GCD first.
- Use the mathematical relationship between GCD and LCM.
- The Euclidean Algorithm provides an efficient GCD computation.
- Be careful of integer overflow when multiplying large numbers.
- This is a fundamental algorithm frequently used in coding interviews, competitive programming, and mathematical computations.

---

# 👨‍💻 Author

**Nijoy P Jose**

This solution is part of my **Data Structures & Algorithms** placement preparation repository, where I document problem-solving patterns, interview techniques, and Java implementations to strengthen my coding skills.
