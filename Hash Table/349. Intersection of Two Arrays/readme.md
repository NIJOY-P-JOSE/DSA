# 349. Intersection of Two Arrays using HashSet

This repository contains a Java solution for **LeetCode 349 - Intersection of Two Arrays** using the **HashSet** data structure.

This is the most efficient and commonly expected interview solution for this problem.

---

# 📌 Problem Statement

Given two integer arrays `nums1` and `nums2`, return an array containing their **intersection**.

Requirements:

* Every element in the result must be **unique**.
* The order of the returned elements does **not** matter.

---

## Example 1

```text id="u8s08w"
Input:
nums1 = [1,2,2,1]
nums2 = [2,2]

Output:
[2]
```

---

## Example 2

```text id="n4g0fi"
Input:
nums1 = [4,9,5]
nums2 = [9,4,9,8,4]

Output:
[4,9]
```

`[9,4]` is also accepted.

---

# 💡 Intuition

We need to find the elements that are present in **both arrays**, but each element should appear **only once** in the result.

A `HashSet` is ideal because:

* It stores **only unique elements**.
* Searching for an element takes **O(1)** on average.

### Idea

1. Store every element of `nums1` in a HashSet.
2. Traverse `nums2`.
3. If an element exists in the first set, add it to another HashSet.
4. Convert the result set into an array.

---

# 🧠 HashSet Thinking

Suppose

```text id="c7bm8r"
nums1 = [4,9,5]

nums2 = [9,4,9,8,4]
```

### Step 1

Store all elements of `nums1`.

```text id="a2x6st"
Set

{4, 5, 9}
```

---

### Step 2

Traverse `nums2`.

Current element

```text id="cv2rl5"
9
```

Exists in the set

↓

Add to answer.

```text id="2kc0pq"
Answer

{9}
```

---

Current element

```text id="l7ej3q"
4
```

Exists

↓

```text id="m73x4q"
{9,4}
```

---

Current element

```text id="9cmwt4"
9
```

Already exists in the answer set.

Ignored automatically.

---

Current element

```text id="0q8y5o"
8
```

Not present.

Ignore.

---

Current element

```text id="tknuq2"
4
```

Already exists.

Ignored.

Final Answer

```text id="8dqp3l"
[4,9]
```

---

# 🔄 Algorithm

1. Create a HashSet.
2. Insert every element of `nums1`.
3. Traverse `nums2`.
4. If the current element exists in the first HashSet:

   * Insert it into another HashSet.
5. Convert the answer HashSet into an array.
6. Return the array.

---

# 📖 Dry Run

Example

```text id="6ztjlwm"
nums1 = [1,2,2,1]

nums2 = [2,2]
```

### Build the first HashSet

```text id="odpxf5"
{1,2}
```

Duplicate `2` is ignored automatically.

---

Traverse `nums2`

Current

```text id="h7n06g"
2
```

Exists

↓

```text id="udxdw9"
Answer

{2}
```

---

Current

```text id="qj7d9c"
2
```

Already exists in the answer set.

Ignored.

Final Answer

```text id="x0jzks"
[2]
```

---

# ⚠️ Common Mistakes

## ❌ Mistake 1

Using an `ArrayList` instead of a `HashSet`.

Example

```text id="4y58y8"
nums1 = [2,2,2]
nums2 = [2]
```

Without a HashSet,

```text id="3h5pms"
[2,2,2]
```

may be produced.

The expected answer is

```text id="lmu0gf"
[2]
```

A HashSet automatically removes duplicates.

---

## ❌ Mistake 2

Checking every pair of elements.

```text id="hr95s7"
for each element
    compare with every element
```

This results in

```text id="e27n5c"
O(n × m)
```

which is much slower.

---

## ❌ Mistake 3

Returning the HashSet directly.

The required return type is

```java id="r9m1up"
int[]
```

Convert the HashSet into an array before returning.

---

## ❌ Mistake 4

Using only one HashSet.

One HashSet stores all elements of the first array.

The second HashSet stores only the unique common elements.

This keeps the implementation simple and avoids duplicate checks.

---

# 📝 Interview Notes

This problem has several approaches.

### Brute Force

Compare every element with every other element.

```text id="rjmxqc"
O(n × m)
```

---

### HashSet ✅ (Recommended)

* Store the first array in a HashSet.
* Check every element of the second array.

Time Complexity

```text id="vjlwm4"
O(n + m)
```

Average case.

This is the most commonly expected interview solution.

---

### Sorting + Binary Search

* Sort one array.
* Binary Search every element of the other array.

Time Complexity

```text id="mjlwmn"
O(m log m + n log m)
```

Useful for practicing Binary Search.

---

### Sorting + Two Pointers

Sort both arrays and use two pointers.

Time Complexity

```text id="jlwmn2"
O(n log n + m log m)
```

---

# ⏱️ Complexity Analysis

Let

* `n = nums1.length`
* `m = nums2.length`

### Time Complexity

Building first HashSet

```text id="jlwmn3"
O(n)
```

Traversing second array

```text id="jlwmn4"
O(m)
```

Overall

```text id="jlwmn5"
O(n + m)
```

Average case.

---

### Space Complexity

```text id="jlwmn6"
O(n + k)
```

where

* `n` = unique elements stored from `nums1`
* `k` = unique common elements stored in the answer set

In the worst case,

```text id="jlwmn7"
O(n)
```

---

# 💻 Java Solution

```java id="jlwmn8"
class Solution {

    public int[] intersection(int[] nums1, int[] nums2) {

        HashSet<Integer> set = new HashSet<>();
        HashSet<Integer> ans = new HashSet<>();

        for (int n : nums1)
            set.add(n);

        for (int n : nums2) {
            if (set.contains(n))
                ans.add(n);
        }

        int[] res = new int[ans.size()];
        int i = 0;

        for (int n : ans)
            res[i++] = n;

        return res;
    }
}
```

---

# 🎯 Hashing Pattern

**Pattern Used:** Membership Testing using HashSet

A HashSet provides **average O(1)** lookup time, making it an excellent choice for problems that require checking whether an element exists while maintaining uniqueness.

---

# 🔗 Related Problems

* 1. Two Sum
* 217. Contains Duplicate
* 219. Contains Duplicate II
* 202. Happy Number
* 128. Longest Consecutive Sequence

---

# 📚 Concepts Practiced

* HashSet
* Hashing
* Membership Testing
* Duplicate Removal
* Set Operations
* Time Complexity Analysis

---

# 🚀 Author

**Nijoy P Jose**

This solution is part of my **Data Structures & Algorithms** placement preparation repository, where I document Hash Table patterns, interview techniques, and Java implementations to strengthen problem-solving skills.
