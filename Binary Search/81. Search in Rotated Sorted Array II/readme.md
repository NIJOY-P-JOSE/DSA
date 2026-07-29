# 81. 🔄 Search in Rotated Sorted Array II (Binary Search)

This repository contains a Java solution for **LeetCode 81 - Search in Rotated Sorted Array II** using **Binary Search**.

This problem is an extension of **LeetCode 33 - Search in Rotated Sorted Array**.

The only difference is that **duplicate elements are allowed**, making pivot detection more challenging.

---

# 📌 Problem Statement

You are given a rotated sorted array that **may contain duplicate elements**.

Given a target value, return:

* `true` if the target exists.
* `false` otherwise.

Try to minimize the number of operations.

---

## Example 1

```text
Input:
nums = [2,5,6,0,0,1,2]
target = 0

Output:
true
```

---

## Example 2

```text
Input:
nums = [2,5,6,0,0,1,2]
target = 3

Output:
false
```

---

# 💡 Intuition

This problem is almost identical to **LeetCode 33**.

The idea is:

1. Find the pivot (largest element).
2. Decide which sorted half contains the target.
3. Perform a normal Binary Search on that half.

The challenge comes from **duplicate values**.

Example:

```text
[2,2,2,3,4,2]
```

or

```text
[1,1,1,1,1]
```

When

```text
nums[start] == nums[mid] == nums[end]
```

we cannot determine which half is sorted.

Therefore, we remove duplicates from both ends until Binary Search can continue.

---

# 🧠 Binary Search Thinking

Consider

```text
nums = [2,5,6,0,0,1,2]
```

Pivot

```text
6
```

Array becomes

```text
2 5 6 | 0 0 1 2
      ↑
```

If

```text
target = 0
```

Since

```text
0 < nums[0]
```

the target must be on the **right side**.

Perform Binary Search only on

```text
0 0 1 2
```

---

# 🔄 Algorithm

### Step 1

Find the pivot.

Unlike LeetCode 33, duplicates are allowed.

If

```text
nums[start] == nums[mid] == nums[end]
```

remove duplicates from both ends after checking whether `start` or `end` is the pivot.

---

### Step 2

If no pivot exists,

perform a normal Binary Search on the whole array.

---

### Step 3

If

```text
nums[pivot] == target
```

return

```text
true
```

---

### Step 4

Decide which sorted half contains the target.

If

```text
target >= nums[0]
```

search the left half.

Otherwise,

search the right half.

---

# 📖 Dry Run

Example

```text
nums = [2,5,6,0,0,1,2]

target = 0
```

Find Pivot

```text
2 5 6 | 0 0 1 2
      ↑
```

Pivot index

```text
2
```

Since

```text
target < nums[0]
```

Binary Search

```text
0 0 1 2
```

Found

```text
0
```

Return

```text
true
```

---

# 📖 Duplicate Case

```text
nums = [1,1,1,3,1]
```

Initially

```text
start = 0
mid = 2
end = 4
```

Values

```text
1 1 1
```

All are equal.

We cannot determine the sorted half.

So:

* Check whether `start` is the pivot.
* Increment `start`.
* Check whether `end` is the pivot.
* Decrement `end`.

This gradually removes duplicate ambiguity.

---

# ⚠️ Common Mistakes

## ❌ Mistake 1

Using the pivot algorithm from LeetCode 33 directly.

It fails for

```text
[1,1,1,3,1]
```

because duplicates hide the pivot.

---

## ❌ Mistake 2

Ignoring

```text
nums[start] == nums[mid] == nums[end]
```

Binary Search cannot determine the sorted side in this case.

Duplicates must be removed.

---

## ❌ Mistake 3

Removing duplicates before checking whether `start` or `end` is the pivot.

Always verify:

```java
nums[start] > nums[start + 1]
```

and

```java
nums[end] < nums[end - 1]
```

before moving the pointers.

Otherwise, the pivot may be skipped.

---

## ❌ Mistake 4

Searching both halves.

After finding the pivot,

only **one half** can contain the target.

Searching both halves unnecessarily increases work.

---

# 📝 Interview Notes

This problem is an extension of **LeetCode 33**.

| Problem | Duplicates | Pivot Detection |
| ------- | ---------- | --------------- |
| 33      | ❌ No       | Easy            |
| 81      | ✅ Yes      | Harder          |

The only additional logic is handling

```text
nums[start] == nums[mid] == nums[end]
```

because duplicates make it impossible to determine which half is sorted.

---

## Worst Case

Normally Binary Search runs in

```text
O(log n)
```

However,

consider

```text
[1,1,1,1,1,1,1]
```

We remove only one duplicate from each side every iteration.

Therefore,

the worst-case complexity becomes

```text
O(n)
```

This is unavoidable due to duplicates.

---

# ⏱️ Complexity Analysis

Let

* `n = nums.length`

### Average Time Complexity

```text
O(log n)
```

---

### Worst Case

```text
O(n)
```

because duplicates may force linear shrinking.

---

### Space Complexity

```text
O(1)
```

---

# 💻 Java Solution

```java
// (Your implementation)
class Solution {

    public boolean search(int[] nums, int target) {

        int pivot = findPivot(nums);

        if (pivot == -1)
            return bSearch(nums, target, 0, nums.length - 1);

        if (nums[pivot] == target)
            return true;

        if (nums[0] <= target)
            return bSearch(nums, target, 0, pivot - 1);
        else
            return bSearch(nums, target, pivot + 1, nums.length - 1);
    }

    // Pivot finding with duplicate handling
    // Binary Search helper
}
```

---

# 🎯 Binary Search Pattern

**Pattern Used:** Pivot Detection + Binary Search

The solution consists of three Binary Search ideas:

1. Find the pivot.
2. Handle duplicates while finding the pivot.
3. Perform Binary Search in the correct sorted half.

---

# 🔗 Related Problems

* 33. Search in Rotated Sorted Array
* 153. Find Minimum in Rotated Sorted Array
* 154. Find Minimum in Rotated Sorted Array II
* 852. Peak Index in Mountain Array
* 162. Find Peak Element

---

# 📚 Concepts Practiced

* Binary Search
* Rotated Sorted Array
* Pivot Detection
* Handling Duplicates
* Modified Binary Search
* Worst-case Complexity Analysis

---

# 🚀 Author

**Nijoy P Jose**

This solution is part of my **Data Structures & Algorithms** placement preparation repository, where I document Binary Search patterns, interview techniques, and Java implementations to strengthen problem-solving skills.
