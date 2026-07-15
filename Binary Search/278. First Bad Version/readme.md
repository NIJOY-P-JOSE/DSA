# 🚨 First Bad Version using Binary Search

This repository contains a Java solution for **LeetCode 278 - First Bad Version** using the **Binary Search** algorithm.

---

# 📌 Problem Statement

You are given `n` versions of a product, numbered from **1 to n**.

An API is provided:

```java
boolean isBadVersion(int version)
```

* Returns `true` if the version is bad.
* Returns `false` if the version is good.

Once a version becomes bad, **all subsequent versions are also bad**.

Your task is to find the **first bad version** while minimizing the number of API calls.

---

## Example 1

```text
Input:
n = 5
bad = 4

Output:
4
```

Explanation:

```text
Version:   1   2   3   4   5
Status :   G   G   G   B   B
```

The first bad version is **4**.

---

## Example 2

```text
Input:
n = 1
bad = 1

Output:
1
```

---

# 💡 Intuition

The versions follow a **monotonic pattern**.

```text
false false false false true true true true
```

or

```text
Good Good Good Good Bad Bad Bad Bad
```

Notice that once the first bad version appears:

* Every version before it is **good**.
* Every version after it is **bad**.

This transition from **false → true** makes Binary Search the ideal solution.

---

# 🧠 Binary Search Thinking

At every iteration:

### Case 1

```text
isBadVersion(mid) == true
```

The current version is bad.

It **could be the first bad version**, so we cannot discard it.

Search the left half including `mid`.

```text
end = mid
```

---

### Case 2

```text
isBadVersion(mid) == false
```

The current version is good.

The first bad version must be after `mid`.

```text
start = mid + 1
```

---

Eventually,

```text
start == end
```

Both pointers point to the first bad version.

---

# 🔄 Algorithm

1. Initialize:

   * `start = 1`
   * `end = n`
2. While `start < end`

   * Find `mid`.
   * If `mid` is bad

     * Move left (`end = mid`)
   * Otherwise

     * Move right (`start = mid + 1`)
3. Return `start`.

---

# 📖 Dry Run

### Example

```text
n = 5
bad = 4
```

Versions

```text
1 2 3 4 5
G G G B B
```

---

### Iteration 1

```text
start = 1
end = 5

mid = 3
```

```text
isBadVersion(3) = false
```

Move right

```text
start = 4
```

---

### Iteration 2

```text
start = 4
end = 5

mid = 4
```

```text
isBadVersion(4) = true
```

Possible answer.

Move left.

```text
end = 4
```

---

Now

```text
start = end = 4
```

Answer:

```text
4
```

---

# ⚠️ Why `end = mid` instead of `mid - 1`?

Suppose

```text
mid = 4
```

and

```text
isBadVersion(4) == true
```

Version **4** might actually be the **first bad version**.

If we do

```java
end = mid - 1;
```

we completely discard version **4**, which could be the correct answer.

Therefore,

```java
end = mid;
```

keeps the possible answer inside the search space.

---

# ⚠️ Why `while(start < end)`?

Using

```java
while(start < end)
```

guarantees that the search space always shrinks.

When

```text
start == end
```

the answer has been found.

Using `<=` together with `end = mid` may lead to an infinite loop.

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
/* The isBadVersion API is defined in the parent class VersionControl.
      boolean isBadVersion(int version); */

public class Solution extends VersionControl {

    public int firstBadVersion(int n) {

        int s = 1;
        int e = n;

        while (s < e) {

            int mid = s + (e - s) / 2;

            if (isBadVersion(mid)) {
                e = mid;
            } else {
                s = mid + 1;
            }
        }

        return s;
    }
}
```

---

# 🎯 Binary Search Pattern

This problem follows the **First True / First Occurrence Binary Search Pattern**.

Instead of searching for a value, Binary Search is used to locate the **first position where a condition becomes true**.

Pattern:

```text
false false false false true true true true
                     ↑
               Find First True
```

---

# 📚 Concepts Practiced

* Binary Search
* Monotonic Functions
* First True Pattern
* Search Space Reduction
* Overflow-safe Mid Calculation
* Interview Pattern Recognition

---

# 🚀 Author

**Nijoy P Jose**

This solution is part of my **Data Structures & Algorithms** placement preparation repository, where I document Binary Search patterns, interview techniques, and Java implementations to strengthen problem-solving skills.
