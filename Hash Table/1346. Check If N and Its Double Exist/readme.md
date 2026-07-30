# 1346. 🔍 Check If N and Its Double Exist (Hash Table)

This repository contains a Java solution for **LeetCode 1346 - Check If N and Its Double Exist** using a **HashSet**.

This is a simple but clever Hash Table problem where the key idea is to perform **constant-time lookups** while traversing the array only once.

---

# 📌 Problem Statement

Given an integer array `arr`, determine whether there exist two different indices `i` and `j` such that:

* `i != j`
* `arr[i] == 2 × arr[j]`

Return:

* `true` if such a pair exists.
* `false` otherwise.

---

## Example 1

```text
Input:
arr = [10,2,5,3]

Output:
true

Explanation:
10 = 2 × 5
```

---

## Example 2

```text
Input:
arr = [3,1,7,11]

Output:
false
```

---

# 💡 Intuition

The brute force solution checks every pair.

```text
for every number
    compare with every other number
```

This takes **O(n²)** time.

Instead, we can remember all previously seen numbers using a **HashSet**.

For every current number `num`, we ask:

* Have we already seen `2 × num`?
* If `num` is even, have we already seen `num / 2`?

If either answer is **Yes**, we've found a valid pair.

Otherwise, store the current number and continue.

---

# 🧠 Why Check Both?

Consider:

```text
arr = [10,5]
```

### Current = 10

Seen:

```text
{}
```

No match.

Store 10.

```text
{10}
```

---

### Current = 5

Need:

```text
2 × 5 = 10
```

Found!

Answer:

```text
true
```

---

Now reverse the array.

```text
arr = [5,10]
```

### Current = 5

Seen:

```text
{}
```

Store 5.

```text
{5}
```

---

### Current = 10

Need:

```text
10 / 2 = 5
```

Found!

Answer:

```text
true
```

Checking both possibilities allows the algorithm to work regardless of which number appears first.

---

# 🤔 Why Check `num % 2 == 0`?

Suppose

```text
num = 5
```

Integer division gives

```text
5 / 2 = 2
```

But

```text
5 ≠ 2 × 2
```

So checking `num / 2` for odd numbers would produce incorrect results.

Therefore, we first ensure

```java
num % 2 == 0
```

before checking

```java
set.contains(num / 2)
```

---

# 📖 Dry Run

Input

```text
arr = [7,1,14,11]
```

Initially

```text
Seen = {}
```

---

Current = 7

Need:

```text
14
```

or

```text
3.5 (ignored)
```

Store 7

```text
Seen = {7}
```

---

Current = 1

Need:

```text
2
```

Not found.

Store 1

```text
Seen = {7,1}
```

---

Current = 14

Need:

```text
28
```

or

```text
7
```

Found!

Return

```text
true
```

---

# 🔄 Algorithm

1. Create an empty `HashSet`.
2. Traverse the array.
3. For each number:

   * Check if `2 × num` already exists.
   * If `num` is even, check if `num / 2` exists.
4. If either exists, return `true`.
5. Otherwise, add the current number to the set.
6. If the loop finishes, return `false`.

---

# ⚠️ Common Mistakes

## ❌ Mistake 1

Checking only

```java
set.contains(2 * num)
```

This fails when the double appears **after** the current number.

Example:

```text
[5,10]
```

---

## ❌ Mistake 2

Checking

```java
set.contains(num / 2)
```

without verifying that the number is even.

Example:

```text
num = 5

5 / 2 = 2
```

This is integer division and can produce incorrect matches.

Always write

```java
if (num % 2 == 0 && set.contains(num / 2))
```

---

## ❌ Mistake 3

Adding the current number before checking.

Example:

```text
arr = [0]
```

If you add first, the element may incorrectly match itself.

Always check first, then insert into the set.

---

## ❌ Mistake 4

Using a nested loop.

```java
for(...)
    for(...)
```

This gives

```text
O(n²)
```

Using a `HashSet` reduces it to

```text
O(n)
```

---

# 📝 Interview Notes

The trick is recognizing that we don't need to search the entire array every time.

A **HashSet** provides average **O(1)** lookup.

For each element, only two values matter:

* its double
* its half (only when even)

This transforms an **O(n²)** search into a single linear traversal.

This is a common interview pattern:

> **"Store previously seen elements and perform constant-time lookups."**

---

# ⏱️ Complexity Analysis

Let

* `n = arr.length`

### Time Complexity

Each element is processed once.

Each `HashSet` lookup is **O(1)** on average.

```text
O(n)
```

---

### Space Complexity

The `HashSet` stores at most `n` elements.

```text
O(n)
```

---

# 💻 Java Solution

```java
class Solution {
    public boolean checkIfExist(int[] arr) {

        HashSet<Integer> set = new HashSet<>();

        for (int num : arr) {

            if (set.contains(2 * num))
                return true;

            if (num % 2 == 0 && set.contains(num / 2))
                return true;

            set.add(num);
        }

        return false;
    }
}
```

---

# 🎯 Hash Table Pattern

**Pattern Used:** HashSet for Fast Lookup

The key idea is to remember previously seen numbers so that every lookup takes constant time.

For every number:

* Search for its double.
* Search for its half (only if even).

If either exists, we've found a valid pair.

---

# 🔗 Related Problems

* 1. Two Sum
* 217. Contains Duplicate
* 349. Intersection of Two Arrays
* 350. Intersection of Two Arrays II
* 888. Fair Candy Swap

---

# 📚 Concepts Practiced

* HashSet
* One-Pass Traversal
* Constant-Time Lookup
* Integer Division
* Time Complexity Optimization

---

# 🚀 Author

**Nijoy P Jose**

This solution is part of my **Data Structures & Algorithms** placement preparation repository, where I document Hash Table patterns, interview techniques, and Java implementations to strengthen problem-solving skills.
