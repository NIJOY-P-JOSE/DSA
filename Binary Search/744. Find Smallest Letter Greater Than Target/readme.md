# 744. 🔍 Find Smallest Letter Greater Than Target (Binary Search)

This repository contains a Java solution for **LeetCode 744 - Find Smallest Letter Greater Than Target** using **Binary Search**.

This problem is a classic **Upper Bound** Binary Search problem where we need to find the **first element strictly greater than the target**.

---

# 📌 Problem Statement

You are given a sorted array of lowercase characters `letters` and a target character `target`.

Return the **smallest character that is lexicographically greater than** `target`.

If no such character exists, return the **first character** in the array (the array is considered circular).

---

## Example 1

```text
Input:
letters = ['c','f','j']
target = 'a'

Output:
'c'
```

---

## Example 2

```text
Input:
letters = ['c','f','j']
target = 'c'

Output:
'f'
```

---

## Example 3

```text
Input:
letters = ['x','x','y','y']
target = 'z'

Output:
'x'
```

Since there is no character greater than `'z'`, we wrap around and return the first character.

---

# 💡 Intuition

Since the array is **sorted**, we can use Binary Search.

We are **not** searching for the target itself.

Instead, we are searching for the **first character greater than the target**.

This is known as the **Upper Bound** pattern.

---

# 🧠 Binary Search Thinking

Suppose

```text
letters = ['c','f','j']

target = 'c'
```

We need the first character **greater than** `'c'`.

The answer is

```text
'f'
```

Notice that even though `'c'` exists in the array, we ignore it because the problem asks for a **strictly greater** character.

---

# 🔄 Algorithm

1. Initialize:

   * `start = 0`
   * `end = letters.length - 1`
2. Perform Binary Search.
3. If `letters[mid]` is greater than the target:

   * It can be the answer.
   * Continue searching on the left to find a smaller valid character.
4. Otherwise:

   * Search on the right.
5. After Binary Search:

   * If `start == letters.length`, no greater character exists.
   * Return `letters[0]`.
6. Otherwise return `letters[start]`.

---

# 📖 Dry Run

### Example

```text
letters = ['c','f','j']

target = 'c'
```

Initially

```text
start = 0
end = 2
```

---

### First Iteration

```text
mid = 1

letters[mid] = 'f'
```

Since

```text
'f' > 'c'
```

Possible answer found.

Search left.

```text
end = 0
```

---

### Second Iteration

```text
start = 0
end = 0

mid = 0

letters[mid] = 'c'
```

Since

```text
'c' <= 'c'
```

Search right.

```text
start = 1
```

Loop ends.

Answer

```text
letters[1] = 'f'
```

---

# 📖 Dry Run (Wrap Around)

```text
letters = ['x','x','y','y']

target = 'z'
```

Every character is less than or equal to `'z'`.

Binary Search ends with

```text
start = letters.length
```

Since no greater character exists,

return

```text
letters[0]
```

Output

```text
'x'
```

---

# ⚠️ Common Mistakes

## ❌ Mistake 1

Searching for the target instead of the next greater character.

Wrong idea

```text
Find target.
```

Correct idea

```text
Find the first character greater than target.
```

---

## ❌ Mistake 2

Using

```java
if (letters[mid] >= target)
```

The problem asks for a **strictly greater** character.

Correct condition

```java
if (letters[mid] > target)
```

or equivalently

```java
if (target < letters[mid])
```

---

## ❌ Mistake 3

Forgetting the wrap-around case.

Example

```text
letters = ['x','y']

target = 'z'
```

Binary Search ends with

```text
start = letters.length
```

The answer is

```java
letters[0]
```

not an out-of-bounds index.

---

## ❌ Mistake 4

Returning `letters[mid]`.

Binary Search does **not** guarantee that `mid` is the correct answer when the loop ends.

Always return

```java
letters[start]
```

(or `letters[0]` in the wrap-around case).

---

# 📝 Interview Notes

This is a classic **Upper Bound** problem.

The goal is to find the **first element greater than the target**.

The Binary Search template is:

```java
while (start <= end) {

    int mid = start + (end - start) / 2;

    if (letters[mid] > target)
        end = mid - 1;
    else
        start = mid + 1;
}
```

After the loop:

* `start` points to the first element greater than the target.
* If `start == letters.length`, no greater element exists.

This same Binary Search pattern appears in many interview problems.

---

# ⏱️ Complexity Analysis

Let

* `n = letters.length`

### Time Complexity

Binary Search

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

    public char nextGreatestLetter(char[] letters, char target) {

        int s = 0;
        int e = letters.length - 1;

        while (s <= e) {

            int mid = s + (e - s) / 2;

            if (target < letters[mid])
                e = mid - 1;
            else
                s = mid + 1;
        }

        if (s == letters.length)
            return letters[0];

        return letters[s];
    }
}
```

---

# 🎯 Binary Search Pattern

**Pattern Used:** Upper Bound (First Greater Element)

We are searching for the **first element that is strictly greater than the target**.

Template:

```text
Condition True:
letters[mid] > target

Move Left:
end = mid - 1

Otherwise:
start = mid + 1
```

After Binary Search,

```text
start
```

points to the required answer.

---

# 🔗 Related Problems

* 35. Search Insert Position
* 34. Find First and Last Position of Element in Sorted Array
* 69. Sqrt(x)
* 278. First Bad Version
* 1539. Kth Missing Positive Number
* 852. Peak Index in a Mountain Array

---

# 📚 Concepts Practiced

* Binary Search
* Upper Bound
* First Greater Element
* Wrap-around Handling
* Boundary Conditions
* Time Complexity Analysis

---

# 🚀 Author

**Nijoy P Jose**

This solution is part of my **Data Structures & Algorithms** placement preparation repository, where I document Binary Search patterns, interview techniques, and Java implementations to strengthen problem-solving skills.
