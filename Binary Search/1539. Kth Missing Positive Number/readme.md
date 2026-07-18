# 🔍 Kth Missing Positive Number using Binary Search

This repository contains a Java solution for **LeetCode 1539 - Kth Missing Positive Number** using the **Binary Search** algorithm.

---

# 📌 Problem Statement

Given a **strictly increasing sorted array** of positive integers `arr` and an integer `k`, return the **kth positive integer that is missing** from the array.

You must solve the follow-up in **less than O(n)** time.

---

## Example 1

```text
Input:
arr = [2,3,4,7,11]
k = 5

Output:
9

Explanation:
Missing numbers are:
1, 5, 6, 8, 9, 10, ...

The 5th missing number is 9.
```

---

## Example 2

```text
Input:
arr = [1,2,3,4]
k = 2

Output:
6

Explanation:
Missing numbers are:
5, 6, 7, ...

The 2nd missing number is 6.
```

---

# 💡 Intuition

Instead of checking every missing number one by one, we can determine **how many numbers are missing before each array element**.

Suppose:

```text
arr = [2,3,4,7,11]
```

| Index | Value | Missing Numbers Before It | Missing Count |
| ----: | ----: | ------------------------: | ------------: |
|     0 |     2 |                         1 |             1 |
|     1 |     3 |                         1 |             1 |
|     2 |     4 |                         1 |             1 |
|     3 |     7 |                   1, 5, 6 |             3 |
|     4 |    11 |         1, 5, 6, 8, 9, 10 |             6 |

Notice the formula:

```text
Missing Count = arr[i] - (i + 1)
```

Why?

* `arr[i]` tells us how many positive numbers should exist up to that value.
* `i + 1` tells us how many numbers actually exist in the array.
* The difference is exactly the number of missing integers.

---

# 🧠 Binary Search Thinking

The missing count is **monotonically increasing**.

For the example above:

```text
Missing Count

1   1   1   3   6
                ↑
```

We need to find the **first index** where

```text
Missing Count >= k
```

This is a classic **First True / Lower Bound Binary Search** problem.

---

# 🔄 Algorithm

1. Initialize:

   * `start = 0`
   * `end = arr.length - 1`
2. Compute:

   ```
   missing = arr[mid] - (mid + 1)
   ```
3. If:

   * `missing < k`

     * Search the right half.
   * Otherwise

     * Search the left half.
4. After Binary Search ends:

   * `start` represents how many array elements are before the answer.
5. Return:

   ```
   start + k
   ```

---

# 📖 Dry Run

Example

```text
arr = [2,3,4,7,11]
k = 5
```

### Initial Search Space

```text
Index : 0  1  2  3  4
Value : 2  3  4  7 11
Missing Count:
        1  1  1  3  6
```

### Iteration 1

```text
mid = 2

missing = 4 - (2 + 1)
         = 1
```

```text
1 < 5
```

Move right.

```text
start = 3
```

---

### Iteration 2

```text
mid = 3

missing = 7 - (3 + 1)
         = 3
```

```text
3 < 5
```

Move right.

```text
start = 4
```

---

### Iteration 3

```text
mid = 4

missing = 11 - (4 + 1)
         = 6
```

```text
6 >= 5
```

Move left.

```text
end = 3
```

Binary Search ends.

```text
start = 4
```

Answer:

```text
start + k
= 4 + 5
= 9
```

---

# ❓ Why is the Answer `start + k`?

After Binary Search,

```text
start
```

represents the **number of array elements that come before the kth missing number**.

If there are

```text
start
```

existing numbers before the answer, then

```text
Answer - Existing Numbers = Missing Numbers
```

So,

```text
Answer - start = k
```

Therefore,

```text
Answer = start + k
```

This elegant observation removes the need for any extra calculations.

---

# ⚠️ Common Mistakes

### ❌ Mistake 1

Using

```java
arr[mid] - (i + 1)
```

instead of

```java
arr[mid] - (mid + 1)
```

There is no variable `i` inside Binary Search.

---

### ❌ Mistake 2

Trying to Binary Search directly on array values.

We are **not** searching for a value.

We are Binary Searching on the derived property:

```text
Missing Count
```

---

### ❌ Mistake 3

Trying to store an answer index manually.

After Binary Search,

```java
start
```

already points to the correct insertion position.

No extra variable is needed.

---

# 📝 Interview Notes

* This is **not** a standard Binary Search on values.
* We Binary Search on a **derived monotonic property**.
* The key observation is:

  ```
  missing = arr[i] - (i + 1)
  ```
* This is another example of the **First True / Lower Bound** Binary Search pattern.
* Whenever you can derive a monotonic function from the data, consider Binary Search on that function instead of the original values.

---

# ⏱️ Complexity Analysis

### Time Complexity

```text
O(log n)
```

Binary Search over the array.

---

### Space Complexity

```text
O(1)
```

Only constant extra memory is used.

---

# 💻 Java Solution

```java
class Solution {
    public int findKthPositive(int[] arr, int k) {

        int s = 0;
        int e = arr.length - 1;

        while (s <= e) {

            int mid = s + (e - s) / 2;

            int missing = arr[mid] - (mid + 1);

            if (missing < k)
                s = mid + 1;
            else
                e = mid - 1;
        }

        return s + k;
    }
}
```

---

# 🎯 Binary Search Pattern

**Pattern Used:** First True / Lower Bound Binary Search

Instead of searching for an exact value, we search for the **first position where the missing count becomes greater than or equal to `k`**.

This pattern is widely used in advanced Binary Search problems.

---

# 🔗 Related Problems

* 35. Search Insert Position
* 278. First Bad Version
* 374. Guess Number Higher or Lower
* 367. Valid Perfect Square
* 441. Arranging Coins

---

# 📚 Concepts Practiced

* Binary Search
* Lower Bound
* First True Pattern
* Monotonic Functions
* Derived Properties
* Mathematical Observation

---

# 🚀 Author

**Nijoy P Jose**

This solution is part of my **Data Structures & Algorithms** placement preparation repository, where I document Binary Search patterns, interview techniques, and Java implementations to strengthen problem-solving skills.
