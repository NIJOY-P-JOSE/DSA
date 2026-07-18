# 1351. Count Negative Numbers in a Sorted Matrix using Binary Search

This repository contains a Java solution for **LeetCode 1351 - Count Negative Numbers in a Sorted Matrix** using the **Binary Search** algorithm.

---

# 📌 Problem Statement

Given an `m × n` matrix where each row and each column is sorted in **non-increasing order** (largest to smallest), return the total number of **negative numbers** in the matrix.

The follow-up asks whether the problem can be solved in **O(m + n)** time.

---

## Example 1

```text
Input:
grid =
[
 [ 4, 3, 2,-1],
 [ 3, 2, 1,-1],
 [ 1, 1,-1,-2],
 [-1,-1,-2,-3]
]

Output:
8
```

Explanation:

```text
Negative numbers are:

-1
-1
-1 -2
-1 -1 -2 -3

Total = 8
```

---

## Example 2

```text
Input:
grid =
[
 [3,2],
 [1,0]
]

Output:
0
```

---

# 💡 Intuition

Each row is sorted in **descending order**.

For example,

```text
7 5 4 2 0 -1 -3 -8
```

Notice that once the **first negative number** appears,

```text
7 5 4 2 0 | -1 -3 -8
           ↑
```

every element to its right is also negative.

So instead of checking every element, we only need to find the **first negative number** in each row using Binary Search.

---

# 🧠 Binary Search Thinking

Suppose a row is

```text
8 6 4 2 0 -3 -5 -7
```

Binary Search looks for the **first negative element**.

Possible cases:

### Current value is non-negative

```text
arr[mid] >= 0
```

The first negative must be on the **right**.

```java
start = mid + 1;
```

---

### Current value is negative

```text
arr[mid] < 0
```

There might be an earlier negative.

Search the **left half**.

```java
end = mid - 1;
```

---

After Binary Search,

```text
start
```

points to the **first negative number**.

Number of negatives:

```text
length - start
```

---

# 🔄 Algorithm

For every row:

1. Perform Binary Search.
2. Find the first negative number.
3. Count:

```
rowLength - firstNegativeIndex
```

4. Add the result to the final answer.

---

# 📖 Dry Run

Example row

```text
4 3 2 -1
```

### Iteration 1

```text
start = 0
end = 3

mid = 1

arr[mid] = 3
```

Positive

Move right.

```text
start = 2
```

---

### Iteration 2

```text
start = 2
end = 3

mid = 2

arr[mid] = 2
```

Positive

Move right.

```text
start = 3
```

---

### Iteration 3

```text
start = 3
end = 3

mid = 3

arr[mid] = -1
```

Negative

Move left.

```text
end = 2
```

Loop ends.

```text
start = 3
```

Negative count

```text
4 - 3 = 1
```

---

Another row

```text
5 2 -1 -2 -3
```

Binary Search ends with

```text
start = 2
```

Negative count

```text
5 - 2 = 3
```

---

# ⚠️ Common Mistakes

## ❌ Mistake 1

Searching for **any** negative number.

We specifically need the **first negative** because every element after it is guaranteed to be negative.

---

## ❌ Mistake 2

Returning

```java
start
```

instead of

```java
arr.length - start
```

`start` is the index of the first negative—not the count.

---

## ❌ Mistake 3

Using Linear Search for every row.

That results in

```text
O(m × n)
```

Binary Search reduces it to

```text
O(m log n)
```

---

# 📝 Interview Notes

### Why Binary Search works

The rows are sorted in descending order.

The condition

```text
Positive Positive Positive Negative Negative
```

is monotonic.

This makes Binary Search possible.

---

### Can this be optimized?

Yes.

The follow-up asks for an

```text
O(m + n)
```

solution.

Instead of Binary Searching every row, start from the **top-right corner**.

* If the current value is **negative**, then every element below it is also negative.
* Count them and move left.
* Otherwise move down.

This reduces the complexity to

```text
O(m + n)
```

---

# ⏱️ Complexity Analysis

### Time Complexity

For each row,

```text
O(log n)
```

There are `m` rows.

Overall:

```text
O(m log n)
```

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

    public int countNegatives(int[][] grid) {

        int ans = 0;

        for (int i = 0; i < grid.length; i++)
            ans += binarySearch(grid[i]);

        return ans;
    }

    private int binarySearch(int[] arr) {

        int s = 0;
        int e = arr.length - 1;

        while (s <= e) {

            int mid = s + (e - s) / 2;

            if (arr[mid] >= 0)
                s = mid + 1;
            else
                e = mid - 1;
        }

        return arr.length - s;
    }
}
```

---

# 🎯 Binary Search Pattern

**Pattern Used:** First True / Lower Bound Binary Search

We search for the **first negative element** in each row.

The search condition is:

```text
arr[mid] < 0
```

Once the first negative is found, every element after it is also negative.

---

# 🔗 Related Problems

* 35. Search Insert Position
* 278. First Bad Version
* 1539. Kth Missing Positive Number
* 744. Find Smallest Letter Greater Than Target
* 367. Valid Perfect Square

---

# 📚 Concepts Practiced

* Binary Search
* Lower Bound
* First True Pattern
* Matrix Traversal
* Monotonic Property
* Time Complexity Optimization

---

# 🚀 Author

**Nijoy P Jose**

This solution is part of my **Data Structures & Algorithms** placement preparation repository, where I document Binary Search patterns, interview techniques, and Java implementations to strengthen problem-solving skills.
