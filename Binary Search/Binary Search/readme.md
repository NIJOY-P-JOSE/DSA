# 🔍 704. Binary Search

This repository contains a Java solution for **LeetCode 704 - Binary Search**.

This is the fundamental **Binary Search** problem and an important template for understanding how to search efficiently in a sorted array.

---

# 📌 Problem Statement

Given a sorted array `nums` and an integer `target`, return the index of `target` if it exists.

If the target does not exist, return `-1`.

The solution must run in **O(log n)** time.

### Example 1

```text
Input:
nums = [-1,0,3,5,9,12]
target = 9

Output:
4
```

### Example 2

```text
Input:
nums = [-1,0,3,5,9,12]
target = 2

Output:
-1
```

---

# 💡 Intuition

The array is already **sorted**.

That means we don't need to check every element one by one.

Suppose we have:

```text
[-1, 0, 3, 5, 9, 12]
```

Instead of starting from the beginning, look at the **middle element**.

```text
[-1, 0, 3, 5, 9, 12]
          ↑
         mid
```

Compare the middle value with the target.

There are only three possibilities:

### Target == Middle

We found the target.

```java
return mid;
```

### Target > Middle

Because the array is sorted, everything to the **left of `mid`** is smaller.

Therefore, search the right half.

```java
start = mid + 1;
```

### Target < Middle

Everything to the **right of `mid`** is greater.

Therefore, search the left half.

```java
end = mid - 1;
```

Every iteration eliminates approximately half of the remaining elements.

That's why Binary Search is much faster than linear search.

---

# 🧠 Step-by-Step Thinking

The key idea is maintaining a **search space**.

Initially:

```text
start = 0
end = nums.length - 1
```

The target must be somewhere between `start` and `end`.

Calculate:

```text
mid = start + (end - start) / 2
```

Then decide which half can be discarded.

```text
             mid
              ↓
[ left half | middle | right half ]
```

If the target is larger:

```text
[ ❌ discarded | mid | search → ]
```

If the target is smaller:

```text
[ ← search | mid | ❌ discarded ]
```

Continue until:

* The target is found, or
* The search space becomes empty.

---

# 📖 Dry Run

Consider:

```text
nums = [-1,0,3,5,9,12]
target = 9
```

Initially:

```text
start = 0
end = 5
```

---

### Iteration 1

```text
mid = 0 + (5 - 0) / 2
    = 2
```

Array:

```text
[-1, 0, 3, 5, 9, 12]
         ↑
        mid
```

```text
nums[mid] = 3
target = 9
```

Since:

```text
9 > 3
```

The target must be on the right.

```text
start = mid + 1
      = 3
```

---

### Iteration 2

```text
start = 3
end = 5

mid = 4
```

```text
[-1, 0, 3, 5, 9, 12]
             ↑
            mid
```

```text
nums[mid] = 9
target = 9
```

Found!

Return:

```text
4
```

---

# 📖 Dry Run - Target Not Found

```text
nums = [-1,0,3,5,9,12]
target = 2
```

Initially:

```text
start = 0
end = 5
mid = 2
```

```text
nums[mid] = 3
```

Since:

```text
2 < 3
```

Search left:

```text
end = 1
```

---

Now:

```text
start = 0
end = 1
mid = 0
```

```text
nums[mid] = -1
```

Since:

```text
2 > -1
```

Search right:

```text
start = 1
```

---

Now:

```text
start = 1
end = 1
mid = 1
```

```text
nums[mid] = 0
```

Since:

```text
2 > 0
```

Move right:

```text
start = 2
```

Now:

```text
start = 2
end = 1
```

The search space is empty.

Therefore:

```text
return -1
```

---

# 🔄 Algorithm

1. Set `start = 0`.
2. Set `end = nums.length - 1`.
3. While `start <= end`:

   * Calculate `mid`.
   * If `nums[mid] == target`, return `mid`.
   * If `target > nums[mid]`, search the right half.
   * Otherwise, search the left half.
4. If the loop finishes, the target does not exist.
5. Return `-1`.

---

# ⚠️ Common Mistakes

## ❌ Mistake 1: Using Binary Search on an unsorted array

Binary Search depends on the array being sorted.

For example:

```text
[5,1,8,2,9]
```

cannot be searched using normal Binary Search.

The sorted property allows us to safely discard half of the array.

---

## ❌ Mistake 2: Wrong search boundaries

Correct initialization:

```java
int s = 0;
int e = nums.length - 1;
```

The last valid index is:

```text
nums.length - 1
```

not:

```text
nums.length
```

---

## ❌ Mistake 3: Wrong loop condition

For this implementation:

```java
while (s <= e)
```

is important.

When:

```text
s == e
```

there is still **one element left to check**.

Using:

```java
while (s < e)
```

would skip that final candidate.

---

## ❌ Mistake 4: Incorrect pointer movement

If:

```text
target > nums[mid]
```

use:

```java
s = mid + 1;
```

If:

```text
target < nums[mid]
```

use:

```java
e = mid - 1;
```

Don't use:

```java
s = mid;
```

or

```java
e = mid;
```

in this particular template, because `mid` has already been checked and can be discarded.

---

## ❌ Mistake 5: Calculating `mid` as `(s + e) / 2`

You may commonly see:

```java
int mid = (s + e) / 2;
```

A safer standard form is:

```java
int mid = s + (e - s) / 2;
```

This avoids potential integer overflow when `s` and `e` are very large.

---

# 📝 Interview Notes

This is the **basic Binary Search template**.

You should become comfortable writing this without looking at notes because many harder problems modify this basic structure.

The most important thing is not memorizing the code.

Understand these three questions:

### 1. What is my search space?

```text
start ... end
```

### 2. Which half can I eliminate?

Based on the relationship between:

```text
target
```

and

```text
nums[mid]
```

### 3. What happens to the boundaries?

```text
target > nums[mid]
→ start = mid + 1
```

```text
target < nums[mid]
→ end = mid - 1
```

---

# 🎯 Binary Search Pattern

**Pattern:** Standard Binary Search

The fundamental structure is:

```text
while (start <= end)

        ↓

      mid

   ↙         ↘

target <    target >

   ↓           ↓

 search      search
 left        right
```

This pattern is the foundation for more advanced variations such as:

* First occurrence
* Last occurrence
* Lower Bound
* Upper Bound
* Rotated sorted arrays
* Peak finding
* Binary Search on Answer

---

# ⏱️ Complexity Analysis

Let `n` be the number of elements.

### Time Complexity

Each iteration eliminates approximately half of the remaining search space.

Therefore:

```text
O(log n)
```

For example, with 1,000,000 elements, Binary Search needs only around 20 comparisons in the worst case.

---

### Space Complexity

Only a few variables are used:

```text
start
end
mid
```

Therefore:

```text
O(1)
```

---

# 💻 Java Solution

```java
class Solution {
    public int search(int[] nums, int target) {

        int s = 0;
        int e = nums.length - 1;

        while (s <= e) {

            int mid = s + (e - s) / 2;

            if (target > nums[mid])
                s = mid + 1;

            else if (target < nums[mid])
                e = mid - 1;

            else
                return mid;
        }

        return -1;
    }
}
```

---

# 🔍 Why This Code Works

The important part is:

```java
if (target > nums[mid])
    s = mid + 1;
```

If the target is greater than the middle element, everything from `start` through `mid` can be discarded.

Similarly:

```java
else if (target < nums[mid])
    e = mid - 1;
```

Everything from `mid` through `end` can be discarded.

Finally:

```java
else
    return mid;
```

means:

```text
nums[mid] == target
```

so the target has been found.

---

# 🔗 Related Problems

Once this basic template is comfortable, practice these variations:

### Easy

* **35. Search Insert Position**
* **69. Sqrt(x)**
* **744. Find Smallest Letter Greater Than Target**
* **852. Peak Index in a Mountain Array**

### Medium

* **33. Search in Rotated Sorted Array**
* **81. Search in Rotated Sorted Array II**
* **153. Find Minimum in Rotated Sorted Array**
* **875. Koko Eating Bananas**
* **1011. Capacity To Ship Packages Within D Days**
* **1283. Find the Smallest Divisor Given a Threshold**

---

# 📚 Concepts Practiced

* Binary Search
* Sorted Arrays
* Search Space
* Two-Pointer Boundaries
* `start`, `end`, and `mid`
* Search Space Reduction
* Integer Overflow Prevention
* `O(log n)` Time Complexity
* `O(1)` Space Complexity

---

# 🚀 Author

**Nijoy P Jose**

This solution is part of my **Data Structures & Algorithms** placement preparation repository, where I document problem-solving patterns, interview techniques, and Java implementations to strengthen my coding skills.
