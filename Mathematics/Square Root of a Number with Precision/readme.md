# Square Root of a Number with Precision

> **Pattern:** Binary Search on Answer (Monotonic Search Space)

## Problem Statement

Given a non-negative integer `n`, find its square root up to **2 decimal places**.

- If `n` is a perfect square, return the exact square root.
- Otherwise, return the square root rounded down to two decimal places.

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
6² = 36
7² = 49

36 < 40 < 49
```

So the integer part is `6`.

Refining the answer:

```
6.1² = 37.21
6.2² = 38.44
6.3² = 39.69
6.4² = 40.96 ❌
```

So one decimal place is **6.3**

Next,

```
6.31² = 39.81
6.32² = 39.94
6.33² = 40.07 ❌
```

Final Answer:

```
6.32
```

---

# 💡 Intuition

The brute-force approach is simple.

Start from `1` and keep checking:

```
1²
2²
3²
4²
...
```

until the square becomes greater than `n`.

Although easy to understand, this takes **O(√n)** time.

Can we do better?

Yes.

Notice something interesting.

For any non-negative number,

```
0² < 1² < 2² < 3² < 4² < ...
```

The square of a number always increases as the number increases.

This means the answer lies in a **sorted search space**, allowing us to apply **Binary Search**.

Instead of checking every number one by one, Binary Search repeatedly eliminates half of the search space, reducing the complexity to **O(log n)**.

Once the integer part is found, we improve the answer by gradually increasing the value with smaller decimal increments.

---

# 🧠 Why Binary Search Works?

Consider the function

```
f(x) = x²
```

For non-negative numbers,

```
0² = 0
1² = 1
2² = 4
3² = 9
4² = 16
5² = 25
6² = 36
7² = 49
```

Notice that

```
As x increases,
x² also increases.
```

This property is called **Monotonicity**.

Because the values are always increasing,

If

```
mid² > n
```

then every number after `mid` is also too large.

Move Left.

If

```
mid² < n
```

then every number before `mid` is also too small.

Move Right.

This is exactly the condition required for Binary Search.

---

# 🔄 Dry Run

Suppose

```
n = 40
```

Initial search space

```
0 --------------------------- 40
```

### Iteration 1

```
mid = 20

20² = 400
```

Too large.

Move Left.

```
0 -----------19
```

---

### Iteration 2

```
mid = 9

9² = 81
```

Still too large.

Move Left.

---

### Iteration 3

```
mid = 4

4² = 16
```

Too small.

Move Right.

---

Continue...

Eventually,

```
start = 7
end = 6
```

No exact square root exists.

Return

```
6
```

Now improve the precision.

```
6.1
6.2
6.3
6.4 ❌
```

Keep

```
6.3
```

Now use

```
0.01
```

```
6.31
6.32
6.33 ❌
```

Final Answer

```
6.32
```

---

# 📝 Algorithm

### Step 1

Initialize

```
start = 0
end = n
```

---

### Step 2

Perform Binary Search.

If

```
mid² == n
```

return `mid`.

If

```
mid² > n
```

move left.

Otherwise,

move right.

---

### Step 3

If no exact square root exists,

return the floor value (`end`).

---

### Step 4

Convert the integer answer into a floating-point value.

---

### Step 5

Increase the answer by

```
0.1
```

until its square becomes greater than `n`.

Go back one step.

---

### Step 6

Repeat the same process using

```
0.01
```

to obtain two decimal places.

---

### Step 7

Print the final answer.

---

# 💻 Java Solution

```java
import java.util.*;

public class Main
{
    public static void main(String[] args) {
        System.out.println("Hello World");

        Scanner s = new Scanner(System.in);
        int n = s.nextInt();

        int ans = srt(n);
        float dans = ans;

        if (!(ans * ans == n))
        {
            for (int i = 1; i < 10; i++)
            {
                dans += 0.1;

                if (dans * dans > n)
                {
                    dans -= 0.1;
                    break;
                }
            }

            for (int i = 1; i < 10; i++)
            {
                dans += 0.01;

                if (dans * dans > n)
                {
                    dans -= 0.01;
                    break;
                }
            }
        }

        System.out.printf("%.2f", dans);
    }

    static int srt(int n)
    {
        int s = 0;
        int e = n;

        while (s <= e)
        {
            int mid = s + (e - s) / 2;

            if (mid * mid == n)
                return mid;

            else if (mid * mid > n)
                e = mid - 1;

            else
                s = mid + 1;
        }

        return e;
    }
}
```

---

# ⚠️ Common Mistakes

### 1. Integer Overflow

The expression

```java
mid * mid
```

can overflow for very large numbers.

A safer approach is

```java
long square = (long) mid * mid;
```

or

```java
mid <= n / mid
```

---

### 2. Returning `start`

If the number is not a perfect square,

the answer is

```java
end
```

because it represents the floor of the square root.

---

### 3. Forgetting Precision

Binary Search only gives the integer part.

A second step is required to calculate decimal places.

---

### 4. Hardcoding Precision

This implementation calculates **2 decimal places**.

For higher precision, use progressively smaller increments:

```
0.1
0.01
0.001
0.0001
...
```

---

# 💼 Interview Notes

Interviewers ask this problem to test your understanding of:

- Binary Search beyond arrays.
- Monotonic search spaces.
- Binary Search on Answer.
- Mathematical reasoning.
- Handling decimal precision.
- Integer overflow.

A common follow-up question is:

> "Can you calculate the answer up to 5 decimal places?"

The idea remains the same—keep reducing the increment (`0.1`, `0.01`, `0.001`, ...).

---

# ⏱️ Complexity Analysis

### Binary Search

The search space is halved in every iteration.

```
Time Complexity: O(log n)
```

### Precision Search

Each refinement loop performs at most 9 iterations.

```
Time Complexity: O(1)
```

### Overall

```
Time Complexity: O(log n)

Space Complexity: O(1)
```

---

# 🧩 Pattern Used

**Binary Search on Answer (Monotonic Search Space)**

Instead of searching in a sorted array, we search over the range of possible answers.

Search Space

```
0 → n
```

Decision Function

```
Is mid² ≤ n ?
```

If Yes

```
Move Right
```

If No

```
Move Left
```

Whenever the answer space is monotonic (true...true...false...false), Binary Search can be applied.

---

# 🔗 Related Problems

- 69. Sqrt(x)
- 367. Valid Perfect Square
- Koko Eating Bananas
- Capacity To Ship Packages Within D Days
- Split Array Largest Sum
- Aggressive Cows
- Allocate Minimum Number of Pages

---

# 📚 Concepts Practiced

- Binary Search
- Binary Search on Answer
- Monotonic Functions
- Mathematical Algorithms
- Precision Handling
- Time Complexity Analysis

---

# 🎯 Key Takeaways

- Binary Search is not limited to searching arrays.
- It can be applied whenever the search space is **monotonic**.
- Find the integer answer first, then refine it for decimal precision.
- Always be careful of integer overflow when using `mid * mid`.
- This problem is an excellent introduction to **Binary Search on Answer**, a pattern frequently asked in coding interviews.

---

# 👨‍💻 Author

**Nijoy P Jose**

This solution is part of my **Data Structures & Algorithms** placement preparation repository, where I document problem-solving patterns, interview techniques, and Java implementations to strengthen my coding skills.
