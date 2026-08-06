# 1342. Number of Steps to Reduce a Number to Zero

- **Difficulty:** Easy
- **Pattern:** Recursion (Tail Recursion) / Simulation

---

# Problem Statement

Given an integer `num`, return the number of steps required to reduce it to `0`.

In one step:

- If the number is **even**, divide it by `2`.
- If the number is **odd**, subtract `1`.

Return the total number of operations performed.

### Example

**Input**

```text
num = 14
```

**Output**

```text
6
```

**Explanation**

```text
14 → 7 → 6 → 3 → 2 → 1 → 0
```

Total steps = **6**

---

# 💡 Intuition

The problem asks us to repeatedly perform an operation until the number becomes `0`.

Notice that after every operation, the number becomes **smaller**.

This naturally suggests recursion:

- Perform one operation.
- Count it.
- Let recursion solve the remaining smaller problem.

---

# 🧠 Step-by-Step Thinking

Suppose

```text
num = 14
```

Current operation

```text
14 is even

↓

Divide by 2

↓

7
```

Now we don't need to think about the remaining operations.

Simply ask recursion:

```text
How many steps are needed for 7?
```

So,

```text
Steps(14)

=

1 + Steps(7)
```

Again,

```text
Steps(7)

=

1 + Steps(6)
```

Eventually,

```text
Steps(0)

=

0
```

This forms the recursive relation.

---

# 🌳 Dry Run

Input

```text
14
```

Recursive calls

```text
Steps(14)

↓

1 + Steps(7)

↓

1 + Steps(6)

↓

1 + Steps(3)

↓

1 + Steps(2)

↓

1 + Steps(1)

↓

1 + Steps(0)

↓

0
```

Returning

```text
0

↓

1

↓

2

↓

3

↓

4

↓

5

↓

6
```

Answer = **6**

---

# Algorithm

1. If the number becomes `0`, return `0`.
2. If the number is even, divide it by `2`.
3. Otherwise subtract `1`.
4. Count the current operation.
5. Recursively solve the remaining problem.

---

# Common Mistakes

### ❌ Forgetting the Base Case

```java
if(num == 0)
    return 0;
```

Without this, recursion never stops.

---

### ❌ Passing an Unnecessary Counter

Many beginners write:

```java
rec(num, count)
```

Although correct, it isn't required.

A cleaner recursive approach is

```java
return 1 + recursion(...);
```

---

### ❌ Assuming Recursion is Optimal

This recursive solution is useful for learning recursion.

However, it is **not the optimal solution** because recursion requires additional call stack memory.

An iterative solution achieves:

- Same Time Complexity
- Better Space Complexity

---

# Interview Notes

This problem is usually intended as a simple **simulation problem**.

Although it can be solved recursively, interviewers generally expect an **iterative solution** because:

- Both approaches take **O(log n)** time.
- Iteration only requires **O(1)** extra space.
- Recursion requires **O(log n)** call stack space.

This problem is excellent for practicing **tail recursion**, but iteration is the preferred production solution.

---

# Complexity Analysis

### Recursive Solution

**Time Complexity**

```text
O(log n)
```

The number decreases quickly because every even number is divided by `2`.

---

**Space Complexity**

```text
O(log n)
```

Due to the recursion call stack.

---

# Java Solution

```java
class Solution {

    public int numberOfSteps(int num) {

        if (num == 0)
            return 0;

        if (num % 2 == 0)
            return 1 + numberOfSteps(num / 2);

        return 1 + numberOfSteps(num - 1);
    }
}
```

---

# Pattern Used

**Pattern:** Tail Recursion / Simulation

Why?

- Each recursive call reduces the problem size.
- The same operation is repeated until reaching the base case.
- The problem demonstrates how recursion can simulate iterative processes.

---

# Related Problems

- 509. Fibonacci Number
- 50. Pow(x, n)
- 231. Power of Two
- 326. Power of Three
- 342. Power of Four
- 191. Number of 1 Bits

---

# Concepts Practiced

- Recursion
- Base Case
- Recursive Thinking
- Tail Recursion
- Simulation
- Time & Space Complexity Analysis

---

# 👨‍💻 Author

**Nijoy P Jose**

This solution is part of my **Data Structures & Algorithms** placement preparation repository, where I document problem-solving patterns, interview techniques, and Java implementations to strengthen my coding skills.
