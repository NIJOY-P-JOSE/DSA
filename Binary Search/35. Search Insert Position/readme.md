# 🔍 Search Insert Position using Binary Search

This repository contains a Java solution for **LeetCode 35 - Search Insert Position** using the Binary Search algorithm.

---

# 📌 Problem Statement

Given a **sorted array of distinct integers** and a target value:

* If the target exists, return its index.
* Otherwise, return the index where it should be inserted to keep the array sorted.

The solution must have **O(log n)** time complexity.

### Example 1

```text
Input:
nums = [1,3,5,6]
target = 5

Output:
2
```

### Example 2

```text
Input:
nums = [1,3,5,6]
target = 2

Output:
1
```

### Example 3

```text
Input:
nums = [1,3,5,6]
target = 7

Output:
4
```

---

# 💡 Intuition

Since the array is already sorted, **Binary Search** is the best choice.

There are two possibilities:

* **Target exists** → Return its index.
* **Target does not exist** → Return the position where Binary Search would insert it.

The important observation is that when Binary Search finishes without finding the target:

* `start (s)` points to the first element **greater than the target**.
* That position is exactly where the target should be inserted.

---

# 🧠 Binary Search Thinking

At every iteration:

* If `target > nums[mid]`

  * The answer must be on the **right side**.
  * Move `start = mid + 1`.

* If `target < nums[mid]`

  * The answer must be on the **left side**.
  * Move `end = mid - 1`.

* If `target == nums[mid]`

  * Target found.
  * Return `mid`.

If the loop finishes without finding the target, return the insertion position.

---

# 🔄 Algorithm

1. Initialize two pointers:

   * `start = 0`
   * `end = n - 1`
2. Perform Binary Search.
3. If target is found, return its index.
4. Otherwise, return the correct insertion index.

---

# 📖 Dry Run

### Example

```text
nums = [1,3,5,6]
target = 2
```

### Iteration 1

```text
s = 0
e = 3
mid = 1

nums[mid] = 3
```

Since

```text
2 < 3
```

Move left:

```text
e = mid - 1 = 0
```

---

### Iteration 2

```text
s = 0
e = 0
mid = 0

nums[mid] = 1
```

Since

```text
2 > 1
```

Move right:

```text
s = mid + 1 = 1
```

Loop ends because

```text
s > e
```

Now,

```text
s = 1
```

This is exactly where `2` should be inserted.

Answer:

```text
1
```

---

# ⏱️ Complexity Analysis

### Time Complexity

```text
O(log n)
```

The search space is reduced by half in every iteration.

### Space Complexity

```text
O(1)
```

Only a few variables are used.

---

# 💻 Java Solution

```java
class Solution {
    public int searchInsert(int[] nums, int target) {

        int s = 0, e = nums.length - 1;
        int mid = -1;

        while (s <= e) {
            mid = s + (e - s) / 2;

            if (target > nums[mid]) {
                s = mid + 1;
            } else if (target < nums[mid]) {
                e = mid - 1;
            } else {
                return mid;
            }
        }

        if (target > nums[mid])
            return s;

        return mid;
    }
}
```

---

# 🎯 Binary Search Pattern

This problem demonstrates the **Lower Bound / Search Insert Position** pattern.

The goal is not only to find the target but also to determine the correct insertion index if the target is absent.

---

# 💡 Simpler Observation

After Binary Search ends:

* `start` always points to the correct insertion position.

Because of this, the solution can be simplified by directly returning `start` after the loop, removing the need for additional conditions.

---

# 📚 Concepts Practiced

* Binary Search
* Search Space Reduction
* Lower Bound Concept
* Search Insert Position
* Interview Pattern Recognition

---

# 🚀 Author

**Nijoy P Jose**

This solution is part of my **Data Structures & Algorithms** placement preparation repository, where I document Binary Search patterns, interview techniques, and Java implementations to strengthen problem-solving skills.
