# Print All Divisors of a Number

> **Pattern:** Mathematics | Square Root Optimization

## Problem Statement

Given a positive integer `n`, print all of its divisors in **ascending order**.

A divisor (or factor) of a number is an integer that divides the number exactly without leaving a remainder.

---

## Examples

### Example 1

**Input**

```text
36
```

**Output**

```text
1 2 3 4 6 9 12 18 36
```

---

### Example 2

**Input**

```text
20
```

**Output**

```text
1 2 4 5 10 20
```

---

## 💡 Intuition

The simplest approach is to check every number from `1` to `n`.

```
for(i = 1; i <= n; i++)
```

If

```
n % i == 0
```

then `i` is a divisor.

Although simple, this takes **O(n)** time.

Can we do better?

Yes.

Notice something interesting.

Whenever a number divides `n`, another divisor also exists.

Example:

```
36

1 × 36

2 × 18

3 × 12

4 × 9

6 × 6
```

Divisors always appear in **pairs**.

If we know one divisor,

```
i
```

the other divisor is

```
n / i
```

This means we only need to search until

```
√n
```

---

## 🧠 Key Observation

Suppose

```
n = 100
```

The square root is

```
√100 = 10
```

Checking after `10` is unnecessary.

Why?

Because every divisor larger than `10` has already been found as the pair of a smaller divisor.

Example

```
20 divides 100

Pair divisor

100 / 20 = 5
```

Since `5` is already checked,

`20` is automatically discovered.

Therefore,

Searching only up to

```
√n
```

is enough.

---

## 🔄 Dry Run

### Input

```
36
```

Loop

```
i = 1
```

```
36 % 1 == 0

Print 1

Store 36
```

---

```
i = 2
```

```
Print 2

Store 18
```

---

```
i = 3
```

```
Print 3

Store 12
```

---

```
i = 4
```

```
Print 4

Store 9
```

---

```
i = 5
```

Not a divisor.

---

```
i = 6
```

```
Print 6

6 == 36 / 6
```

Same divisor.

Do not store again.

---

Current Output

```
1 2 3 4 6
```

Stored List

```
36 18 12 9
```

Print the stored list in reverse order

```
9 12 18 36
```

Final Output

```
1 2 3 4 6 9 12 18 36
```

---

## 📝 Algorithm

1. Read the input number.
2. Create an empty list to store larger divisors.
3. Traverse from `1` to `√n`.
4. If `i` divides `n`
   - Print `i`
   - If `i` and `n / i` are different, store `n / i`.
5. After the loop, print the stored divisors in reverse order.

---

## 💻 Java Solution

```java
import java.util.*;

public class Main
{
    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);

        while (true)
        {
            ArrayList<Integer> list = new ArrayList<>();

            int n = s.nextInt();

            for (int i = 1; i <= Math.sqrt(n); i++)
            {
                if (n % i == 0)
                {
                    if (n / i != i)
                        list.add(n / i);

                    System.out.print(i + " ");
                }
            }

            for (int i = list.size() - 1; i >= 0; i--)
                System.out.print(list.get(i) + " ");

            System.out.println();
        }
    }
}
```

---

## ⚠️ Common Mistakes

### 1. Printing Duplicate Divisors

For perfect squares,

```
36

6 × 6
```

Without checking

```java
if (n / i != i)
```

the divisor `6` would be printed twice.

---

### 2. Printing in the Wrong Order

If both divisors are printed immediately,

```
1 36 2 18 3 12 ...
```

the output is not sorted.

Store the larger divisors first and print them in reverse order.

---

### 3. Iterating Until `n`

Many beginners write

```java
for(int i = 1; i <= n; i++)
```

This increases the complexity to **O(n)**.

Always iterate only until

```
√n
```

---

### 4. Using `Math.sqrt(n)` in Every Iteration

Calling `Math.sqrt(n)` repeatedly is unnecessary.

A better approach is

```java
int limit = (int)Math.sqrt(n);

for(int i = 1; i <= limit; i++)
```

or

```java
for(int i = 1; i * i <= n; i++)
```

---

## 💼 Interview Notes

Interviewers ask this problem to test whether you recognize divisor pairs.

A brute-force solution is easy.

The optimized solution is based on one observation:

> Every divisor greater than `√n` has a corresponding divisor smaller than `√n`.

Follow-up questions may include:

- Count the number of divisors.
- Find the sum of all divisors.
- Check if a number is prime.
- Find the prime factors of a number.

---

## ⏱️ Complexity Analysis

### Time Complexity

```
O(√n)
```

Only numbers up to the square root are checked.

### Space Complexity

```
O(√n)
```

In the worst case, the list stores approximately half of the divisor pairs.

---

## 🧩 Pattern Used

**Square Root Optimization**

Instead of checking every number from `1` to `n`, exploit the fact that divisors always occur in pairs.

```
i × (n / i) = n
```

Searching only until `√n` finds every divisor efficiently.

---

## 🔗 Related Problems

- Count Number of Divisors
- Prime Factorization
- Prime Numbers
- Sieve of Eratosthenes
- Highly Composite Numbers
- GCD and LCM
- Perfect Number

---

## 📚 Concepts Practiced

- Mathematics
- Divisors
- Factors
- Square Root Optimization
- Number Theory
- Time Complexity Optimization

---

## 🎯 Key Takeaways

- Divisors always occur in pairs.
- Only iterate until `√n`.
- Store the larger divisor and print it later to maintain sorted order.
- Handle perfect squares carefully to avoid duplicate output.
- This optimization reduces the complexity from **O(n)** to **O(√n)**.

---

## 👨‍💻 Author

**Nijoy P Jose**

This solution is part of my **Data Structures & Algorithms** placement preparation repository, where I document problem-solving patterns, interview techniques, and Java implementations to strengthen my coding skills.
