# 852. ⛰️ Peak Index in a Mountain Array (Binary Search)

This repository contains a Java solution for **LeetCode 852 - Peak Index in a Mountain Array** using **Binary Search**.

This is a classic Binary Search problem where we use the shape of a **mountain array** to locate the peak in **O(log n)** time.

---

# 📌 Problem Statement

You are given a **mountain array**.

A mountain array satisfies:

* Elements first **strictly increase**.
* Then they **strictly decrease**.
* There is exactly one peak.

Return the **index of the peak element**.

The solution must run in **O(log n)** time.

---

## Example 1

```text
Input:
arr = [0,1,0]

Output:
1
```

---

## Example 2

```text
Input:
arr = [0,2,1,0]

Output:
1
```

---

## Example 3

```text
Input:
arr = [0,10,5,2]

Output:
1
```

---

# 💡 Intuition

The important observation is that we **do not need to compare with both neighbors**.

Instead, compare only:

```text
arr[mid]
```

and

```text
arr[mid + 1]
```

There are only two possibilities.

### Case 1

```text
arr[mid] < arr[mid + 1]
```

We are on the **ascending slope**.

Example

```text
0 2 5 8 10 7 3
      ↑
```

The peak is definitely **to the right**.

So,

```java
start = mid + 1;
```

---

### Case 2

```text
arr[mid] > arr[mid + 1]
```

We are on the **descending slope** or exactly at the peak.

Example

```text
0 2 5 8 10 7 3
          ↑
```

The peak could be:

* `mid` itself
* somewhere on the left

So,

```java
end = mid;
```

Notice that we **do not** write:

```java
end = mid - 1;
```

because `mid` itself might be the answer.

---

# 🧠 Binary Search Thinking

Consider

```text
arr = [0,2,5,8,10,7,3]
```

Initially

```text
start = 0
end = 6
```

---

### First Iteration

```text
mid = 3

arr[mid] = 8
arr[mid+1] = 10
```

Since

```text
8 < 10
```

we are on the increasing slope.

Discard the left half.

```text
start = 4
```

---

### Second Iteration

```text
start = 4
end = 6

mid = 5

arr[mid] = 7
arr[mid+1] = 3
```

Since

```text
7 > 3
```

we are on the decreasing slope.

The peak is on the left (or at `mid`).

```text
end = 5
```

---

### Third Iteration

```text
start = 4
end = 5

mid = 4

arr[mid] = 10
arr[mid+1] = 7
```

Again

```text
10 > 7
```

Move left.

```text
end = 4
```

Now

```text
start == end
```

Peak found.

Answer

```text
Index = 4
```

---

# 🔄 Algorithm

1. Initialize:

   * `start = 0`
   * `end = n - 1`
2. While `start < end`
3. Compute `mid`.
4. Compare `arr[mid]` with `arr[mid + 1]`.
5. If increasing:

   * Move right.
6. Otherwise:

   * Keep `mid` and search left.
7. When `start == end`, return the index.

---

# ⚠️ Common Mistakes

## ❌ Mistake 1

Using

```java
while(start <= end)
```

Correct

```java
while(start < end)
```

Because we always keep one possible answer in the search space.

---

## ❌ Mistake 2

Writing

```java
end = mid - 1;
```

Wrong.

The peak might actually be

```text
mid
```

So always use

```java
end = mid;
```

---

## ❌ Mistake 3

Comparing both neighbors.

Example

```java
arr[mid] > arr[mid-1]
arr[mid] > arr[mid+1]
```

This introduces unnecessary boundary checks.

Comparing only

```java
arr[mid]
```

and

```java
arr[mid+1]
```

is sufficient.

---

## ❌ Mistake 4

Accessing

```java
arr[mid+1]
```

without ensuring safety.

This implementation uses

```java
while(start < end)
```

which guarantees

```text
mid < end
```

Therefore,

```java
arr[mid+1]
```

is always valid.

This is a subtle but important Binary Search observation.

---

# 📝 Interview Notes

This is one of the most common Binary Search patterns.

Instead of searching for a value,

we search for a **change in trend**.

The array has two regions:

```text
Increasing
```

and

```text
Decreasing
```

The peak lies exactly where the trend changes.

This idea appears in several interview problems.

---

## Relation to LeetCode 162

This problem is essentially a **special case** of **LeetCode 162 - Find Peak Element**.

Difference:

**852**

* Guaranteed mountain array.
* Exactly one peak.

**162**

* General array.
* One or multiple peaks may exist.

Interestingly, **both problems use the exact same Binary Search algorithm**.

---

# ⏱️ Complexity Analysis

Let

* `n = arr.length`

### Time Complexity

Binary Search halves the search space every iteration.

```text
O(log n)
```

---

### Space Complexity

```text
O(1)
```

---

# 💻 Java Solution

```java
class Solution {

    public int peakIndexInMountainArray(int[] arr) {

        int s = 0;
        int e = arr.length - 1;

        while (s < e) {

            int mid = s + (e - s) / 2;

            if (arr[mid] < arr[mid + 1])
                s = mid + 1;
            else
                e = mid;
        }

        return s;
    }
}
```

---

# 🎯 Binary Search Pattern

**Pattern Used:** Peak Finding (Binary Search on Monotonic Trend)

Instead of searching for a value, Binary Search is performed on the **slope** of the array.

Decision rule:

```text
arr[mid] < arr[mid + 1]
```

↓

Move Right

```java
start = mid + 1;
```

Otherwise

↓

Move Left

```java
end = mid;
```

When the loop ends,

```text
start == end
```

and that index is the peak.

---

# 🔗 Related Problems

* 162. Find Peak Element
* 1095. Find in Mountain Array
* 69. Sqrt(x)
* 278. First Bad Version
* 744. Find Smallest Letter Greater Than Target

---

# 📚 Concepts Practiced

* Binary Search
* Peak Finding
* Monotonic Property
* Search Space Reduction
* Boundary Conditions
* Time Complexity Analysis

---

# 🚀 Author

**Nijoy P Jose**

This solution is part of my **Data Structures & Algorithms** placement preparation repository, where I document Binary Search patterns, interview techniques, and Java implementations to strengthen problem-solving skills.
