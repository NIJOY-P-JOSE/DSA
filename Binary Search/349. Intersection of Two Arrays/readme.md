# 349. Intersection of Two Arrays using Binary Search

This repository contains a Java solution for **LeetCode 349 - Intersection of Two Arrays** using the **Binary Search** algorithm.

> **Note:** Although this problem is commonly solved using a **HashSet**, this solution demonstrates how it can be solved using **Sorting + Binary Search**, making it a good practice problem for Binary Search.

---

# 📌 Problem Statement

Given two integer arrays `nums1` and `nums2`, return an array containing their **intersection**.

* Every element in the result must be **unique**.
* The order of the returned elements does **not** matter.

---

## Example 1

```text
Input:
nums1 = [1,2,2,1]
nums2 = [2,2]

Output:
[2]
```

---

## Example 2

```text
Input:
nums1 = [4,9,5]
nums2 = [9,4,9,8,4]

Output:
[4,9]
```

`[9,4]` is also accepted.

---

# 💡 Intuition

Instead of comparing every element of both arrays, we can take advantage of **Binary Search**.

### Idea

1. Sort one array.
2. For every element in the other array:

   * Use Binary Search to check whether it exists.
3. Store the common elements in a `HashSet` to automatically remove duplicates.

This combines the efficiency of Binary Search with the uniqueness guarantee of a HashSet.

---

# 🧠 Binary Search Thinking

Suppose

```text
nums1 = [4,9,5]

nums2 = [9,4,9,8,4]
```

First, sort `nums2`.

```text
4 4 8 9 9
```

Now process every element of `nums1`.

Search for

```text
4
```

Binary Search

↓

Found

↓

Add to HashSet

---

Search for

```text
9
```

Binary Search

↓

Found

↓

Add to HashSet

---

Search for

```text
5
```

Binary Search

↓

Not Found

↓

Ignore

Result

```text
[4,9]
```

---

# 🔄 Algorithm

1. Sort `nums2`.
2. Traverse every element of `nums1`.
3. Binary Search the element in `nums2`.
4. If found:

   * Insert into a `HashSet`.
5. Convert the HashSet into an array.
6. Return the result.

---

# 📖 Dry Run

Example

```text
nums1 = [1,2,2,1]
nums2 = [2,2]
```

After sorting

```text
nums2

2 2
```

Process

```text
1
```

Binary Search

↓

Not Found

---

Process

```text
2
```

Binary Search

↓

Found

↓

HashSet

```text
{2}
```

---

Process

```text
2
```

Binary Search

↓

Found

Already exists in HashSet

↓

Ignored

---

Process

```text
1
```

Binary Search

↓

Not Found

Final Answer

```text
[2]
```

---

# ⚠️ Common Mistakes

## ❌ Mistake 1

Using Binary Search without sorting.

Binary Search only works on **sorted arrays**.

Always sort first.

```java
Arrays.sort(nums2);
```

---

## ❌ Mistake 2

Using an ArrayList instead of a HashSet.

Example

```text
nums1 = [2,2,2]
nums2 = [2]
```

Without a HashSet,

```text
[2,2,2]
```

would be returned.

The problem requires

```text
[2]
```

A `HashSet` automatically removes duplicates.

---

## ❌ Mistake 3

Sorting both arrays.

Only one array needs to be sorted because Binary Search is performed only on that array.

Sorting both arrays increases unnecessary work.

---

## ❌ Mistake 4

Forgetting to convert the HashSet into an array before returning.

The required return type is

```java
int[]
```

not

```java
HashSet<Integer>
```

---

# 📝 Interview Notes

There are multiple valid approaches.

### 1. HashSet

* Time: **O(n + m)**
* Most common interview solution.

---

### 2. Sorting + Binary Search ✅ (This Solution)

* Sort one array.
* Binary Search every element of the other array.

Complexity:

```text
O(m log m + n log m)
```

This approach is useful for practicing Binary Search on sorted data.

---

### 3. Sorting + Two Pointers

Sort both arrays and use two pointers to find common elements.

Complexity:

```text
O(n log n + m log m)
```

---

# ⏱️ Complexity Analysis

Let

* `n = nums1.length`
* `m = nums2.length`

### Time Complexity

Sorting

```text
O(m log m)
```

Binary Search for every element

```text
O(n log m)
```

Overall

```text
O(m log m + n log m)
```

---

### Space Complexity

```text
O(k)
```

where `k` is the number of unique common elements stored in the HashSet.

---

# 💻 Java Solution

```java
class Solution {

    public int[] intersection(int[] nums1, int[] nums2) {

        HashSet<Integer> ans = new HashSet<>();

        Arrays.sort(nums2);

        for (int n : nums1) {
            if (binarySearch(nums2, n))
                ans.add(n);
        }

        int[] res = new int[ans.size()];
        int i = 0;

        for (int n : ans)
            res[i++] = n;

        return res;
    }

    boolean binarySearch(int[] arr, int target) {

        int s = 0;
        int e = arr.length - 1;

        while (s <= e) {

            int mid = s + (e - s) / 2;

            if (arr[mid] == target)
                return true;
            else if (arr[mid] > target)
                e = mid - 1;
            else
                s = mid + 1;
        }

        return false;
    }
}
```

---

# 🎯 Binary Search Pattern

**Pattern Used:** Classic Binary Search on a Sorted Array

The Binary Search is used to determine whether a target element exists in a sorted array.

Unlike many Binary Search problems, we are **not searching for an index or boundary**. We only need to determine whether the target is **present or absent**.

---

# 🔗 Related Problems

* 704. Binary Search
* 35. Search Insert Position
* 744. Find Smallest Letter Greater Than Target
* 34. Find First and Last Position of Element in Sorted Array

---

# 📚 Concepts Practiced

* Binary Search
* Sorting
* HashSet
* Searching in Sorted Arrays
* Duplicate Removal
* Time Complexity Analysis

---

# 🚀 Author

**Nijoy P Jose**

This solution is part of my **Data Structures & Algorithms** placement preparation repository, where I document Binary Search patterns, interview techniques, and Java implementations to strengthen problem-solving skills.
