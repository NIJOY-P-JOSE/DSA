# Greatest Common Divisor (GCD / HCF) using Euclidean Algorithm

> **Pattern:** Mathematics | Euclidean Algorithm | Recursion

## Problem Statement

Given two positive integers `a` and `b`, find their **Greatest Common Divisor (GCD)**.

The GCD (also called HCF - Highest Common Factor) is the largest positive integer that divides both numbers without leaving a remainder.

---

## Examples

### Example 1

**Input**

```text
12 18
```

**Output**

```text
6
```

**Explanation**

Divisors of 12:

```
1 2 3 4 6 12
```

Divisors of 18:

```
1 2 3 6 9 18
```

The greatest common divisor is

```
6
```

---

### Example 2

**Input**

```text
24 36
```

**Output**

```text
12
```

---

### Example 3

**Input**

```text
17 13
```

**Output**

```text
1
```

Since the only common divisor is `1`, the numbers are **co-prime**.

---

# 💡 Intuition

The brute-force approach is to check every number from `1` to the smaller of the two numbers.

For example,

```
a = 12
b = 18
```

Check

```
1
2
3
...
12
```

and keep track of the largest common divisor.

Although simple, this takes **O(min(a, b))** time.

Can we do better?

Yes.

The Euclidean Algorithm is based on one important observation:

> **The GCD of two numbers does not change if the larger number is replaced by its remainder when divided by the smaller number.**

In other words,

```
GCD(a, b) = GCD(b % a, a)
```

This reduces the size of the problem in every recursive call.

---

# 🧠 Why Does the Euclidean Algorithm Work?

Suppose

```
a = 12
b = 18
```

Both numbers are divisible by `6`.

Now,

```
18 % 12 = 6
```

Instead of finding

```
GCD(12, 18)
```

we find

```
GCD(6, 12)
```

Again,

```
12 % 6 = 0
```

Now,

```
GCD(0, 6) = 6
```

We reached the answer.

Notice how the numbers become smaller in every step.

This is why the Euclidean Algorithm is extremely efficient.

---

# 🔄 Dry Run

### Input

```
a = 48
b = 18
```

Call

```
GCD(48, 18)
```

↓

```
GCD(18, 48 % 18)

↓

GCD(18, 12)
```

↓

```
GCD(12, 18 % 12)

↓

GCD(12, 6)
```

↓

```
GCD(6, 12 % 6)

↓

GCD(6, 0)
```

↓

Since

```
a == 0
```

Return

```
6
```

---

# 📝 Algorithm

1. Read two integers `a` and `b`.
2. If `a` becomes `0`, return `b`.
3. Otherwise,
   - Compute `b % a`.
   - Recursively call `GCD(b % a, a)`.
4. Continue until the remainder becomes `0`.
5. Return the last non-zero value.

---

# 💻 Java Solution

```java
import java.util.*;

public class Main
{
    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);

        while (true)
        {
            int a = s.nextInt();
            int b = s.nextInt();

            System.out.println(GCD(a, b));
        }
    }

    static int GCD(int a, int b)
    {
        if (a == 0)
            return b;

        return GCD(b % a, a);
    }
}
```

---

# ⚠️ Common Mistakes

### 1. Wrong Base Case

The recursion should stop when

```java
a == 0
```

At that point, `b` is the GCD.

---

### 2. Incorrect Recursive Call

Many beginners write

```java
GCD(a % b, b)
```

The correct recursive relation is

```java
GCD(b % a, a)
```

---

### 3. Confusing GCD and LCM

Remember,

```
GCD → Greatest Common Divisor

LCM → Least Common Multiple
```

They are different concepts.

---

### 4. Forgetting Negative Numbers

If negative values are possible, convert them using

```java
Math.abs()
```

before computing the GCD.

---

# 💼 Interview Notes

The Euclidean Algorithm is one of the most frequently asked mathematical algorithms in coding interviews.

Interviewers may ask:

- Why does the algorithm work?
- Can you write an iterative version?
- How is LCM related to GCD?
- Can you compute the GCD of an array?
- Can you solve the problem without recursion?

A common follow-up question is:

> Find the LCM of two numbers.

Use the formula

```
LCM(a, b) = (a × b) / GCD(a, b)
```

---

# ⏱️ Complexity Analysis

### Time Complexity

```
O(log(min(a, b)))
```

The numbers decrease rapidly in each recursive call, making the algorithm extremely efficient.

### Space Complexity

```
O(log(min(a, b)))
```

due to the recursive call stack.

An iterative implementation reduces the space complexity to **O(1)**.

---

# 🧩 Pattern Used

**Euclidean Algorithm**

The key idea is:

```
GCD(a, b) = GCD(b % a, a)
```

Instead of checking all divisors, repeatedly replace the larger number with the remainder until it becomes zero.

---

# 🔗 Related Problems

- Find LCM of Two Numbers
- GCD of an Array
- Extended Euclidean Algorithm
- Fraction Simplification
- Euclidean Distance Problems
- Modular Arithmetic
- Bézout's Identity

---

# 📚 Concepts Practiced

- Mathematics
- Euclidean Algorithm
- Recursion
- Modulo Operator
- Number Theory
- GCD & LCM

---

# 🎯 Key Takeaways

- The Euclidean Algorithm is the fastest standard method for finding the GCD.
- Instead of checking every divisor, repeatedly replace the larger number with its remainder.
- The algorithm works because the GCD remains unchanged during this transformation.
- It is significantly faster than the brute-force approach and is expected knowledge for coding interviews.
- Knowing the relationship between GCD and LCM is a common interview requirement.

---

# 👨‍💻 Author

**Nijoy P Jose**

This solution is part of my **Data Structures & Algorithms** placement preparation repository, where I document problem-solving patterns, interview techniques, and Java implementations to strengthen my coding skills.
