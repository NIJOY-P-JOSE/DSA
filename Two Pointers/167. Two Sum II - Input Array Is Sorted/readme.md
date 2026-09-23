# [167. Two Sum II - Input Array Is Sorted](https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/)

**Difficulty:** Medium
**Pattern:** Two Pointers
**Related Patterns:** Sorted Array · Greedy Pointer Movement

---

## ⚡ Quick Revision

|                   |                                                      |
| ----------------- | ---------------------------------------------------- |
| **Goal**          | Find two numbers whose sum equals `target`           |
| **Pattern**       | Two Pointers                                         |
| **Pointers**      | `left` at beginning, `right` at end                  |
| **Key Idea**      | Use the sorted order to decide which pointer to move |
| **Sum > target**  | Move `right--`                                       |
| **Sum < target**  | Move `left++`                                        |
| **Sum == target** | Return the two indices                               |
| **Time**          | `O(n)`                                               |
| **Space**         | `O(1)`                                               |

---

## 🧠 Problem in Simple Words

You are given a **sorted array** and a `target`.

Find two different numbers whose sum is equal to the target.

The answer must contain their **1-based indices**.

Example:

```text
numbers = [2, 7, 11, 15]
target = 9
```

We need:

```text
2 + 7 = 9
```

So the answer is:

```text
[1, 2]
```

---

## 🔥 How to Recognize This Problem in a Placement

Look for these clues:

* The array is **already sorted**
* Need to find **two elements**
* Their sum must satisfy a target
* Brute force would check every pair → `O(n²)`
* The sorted order can be used to eliminate possibilities

### Recognition Trigger

> **Sorted array + find two values satisfying a condition → Think Two Pointers.**

For a sum problem:

```text
left = 0
right = n - 1
```

---

## 🧩 How to Think / Derive the Solution

Start with the smallest and largest values.

```text
[2, 7, 11, 15]
 ↑           ↑
left        right
```

Calculate:

```text
2 + 15 = 17
```

Suppose:

```text
target = 9
```

The sum is too large.

Since the array is sorted, moving `left` right would make the sum even larger.

So we must decrease the sum:

```text
right--
```

Now:

```text
2 + 11 = 13
```

Still too large:

```text
right--
```

Now:

```text
2 + 7 = 9
```

Found the answer.

---

## 💡 Why Do the Pointer Movements Work?

This is the most important part of the problem.

### Case 1: `sum > target`

```text
numbers[left] + numbers[right] > target
```

We need a **smaller sum**.

Because the array is sorted:

```text
numbers[right - 1] < numbers[right]
```

So:

```text
right--
```

can decrease the sum.

---

### Case 2: `sum < target`

```text
numbers[left] + numbers[right] < target
```

We need a **larger sum**.

Because the array is sorted:

```text
numbers[left + 1] > numbers[left]
```

So:

```text
left++
```

can increase the sum.

---

### Case 3: `sum == target`

We found the required pair.

```text
return new int[]{left + 1, right + 1};
```

The `+1` is required because LeetCode asks for **1-based indices**.

---

## 📊 Visual Explanation

Example:

```text
numbers = [2, 7, 11, 15]
target = 9

Step 1:

  2   7   11   15
  ↑             ↑
 left          right

  2 + 15 = 17
  17 > 9

Move right


Step 2:

  2   7   11   15
  ↑         ↑
 left      right

  2 + 11 = 13
  13 > 9

Move right


Step 3:

  2   7   11   15
  ↑     ↑
 left  right

  2 + 7 = 9

Found!
```

Answer:

```text
[1, 2]
```

---

## 🔁 Algorithm

1. Set `left = 0`.
2. Set `right = n - 1`.
3. While `left < right`:

   * Calculate `sum = numbers[left] + numbers[right]`.
   * If `sum == target`, return the 1-based indices.
   * If `sum > target`, decrement `right`.
   * If `sum < target`, increment `left`.
4. If no pair is found, return `[-1, -1]`.

---

## 💻 My Solution

```java
class Solution {
    public int[] twoSum(int[] numbers, int target) {

        int n = numbers.length;
        int left = 0, right = n - 1;

        while (left < right) {

            if (numbers[left] + numbers[right] == target)
                return new int[]{left + 1, right + 1};

            else if (numbers[left] + numbers[right] > target)
                right--;

            else
                left++;
        }

        return new int[]{-1, -1};
    }
}
```

### Code Explanation

```java
int left = 0, right = n - 1;
```

Start from both ends.

```java
while (left < right)
```

Continue until the pointers meet.

```java
numbers[left] + numbers[right]
```

Check the current pair.

```java
right--;
```

Used when the sum is too large.

```java
left++;
```

Used when the sum is too small.

```java
return new int[]{left + 1, right + 1};
```

Return 1-based indices when the target is found.

---

## 🧠 Pattern Connection

This is an important extension of **977. Squares of a Sorted Array**.

### 977 — Squares of a Sorted Array

We used:

```text
left ←        → right
```

to compare values from both ends.

The pointers were used to determine which value should be placed next.

### 167 — Two Sum II

We again use:

```text
left ←        → right
```

But now the pointers are used to **search for a pair**.

The key difference:

```text
977 → Two pointers + reverse filling

167 → Two pointers + target-based movement
```

The common idea is:

> **Use information from both ends instead of checking every pair.**

---

## ⚔️ Similar Problems / Variations

Once comfortable with this pattern, practice:

* **125. Valid Palindrome** → Two pointers from both ends
* **11. Container With Most Water** → Two pointers + greedy movement
* **15. 3Sum** → Sorting + Two Pointers
* **977. Squares of a Sorted Array** → Two pointers + reverse filling
* **26. Remove Duplicates from Sorted Array** → Two pointers + write pointer

---

## 🚨 Common Mistakes

### 1. Using `O(n²)` brute force

```java
for(...)
    for(...)
```

This checks every possible pair.

The sorted property allows us to solve it in `O(n)`.

---

### 2. Moving the wrong pointer

Remember:

```text
sum > target → right--

sum < target → left++
```

---

### 3. Forgetting 1-based indexing

The array uses:

```text
0, 1, 2, 3
```

But the problem asks for:

```text
1, 2, 3, 4
```

Therefore:

```java
left + 1
right + 1
```

---

### 4. Using `left <= right`

The problem requires **two different elements**.

Therefore:

```java
while (left < right)
```

is the correct condition.

---

## ⏱️ Complexity

### Time Complexity

```text
O(n)
```

Each pointer moves only toward the other pointer.

Neither pointer moves backward.

### Space Complexity

```text
O(1)
```

Only two pointers are used.

---

## 🎯 Placement-Level Takeaway

When you see:

```text
Sorted Array
      +
Find Two Elements
      +
Target / Condition
```

Immediately consider:

```text
Two Pointers
```

For a target sum:

```text
sum > target → right--

sum < target → left++

sum == target → answer
```

The important skill is **not memorizing the code**.

Understand why the sorted order makes each pointer movement safe.

---

## ⚡ Final 30-Second Cheat Sheet

```text
Pattern:
Two Pointers

Start:
left = 0
right = n - 1

While:
left < right

Check:
sum = numbers[left] + numbers[right]

If:
sum == target → answer

sum > target → right--

sum < target → left++

Complexity:
Time  → O(n)
Space → O(1)

Important:
Answer uses 1-based indexing.
```

---

**Nijoy P Jose**

This solution is part of my **Data Structures & Algorithms** placement preparation repository, where I document problem-solving patterns, interview techniques, and Java implementations to strengthen my coding skills.
