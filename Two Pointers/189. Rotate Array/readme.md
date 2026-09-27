# 189. Rotate Array

**Difficulty:** Medium
**Pattern:** Array Manipulation · Two Pointers · Reversal
**Technique:** Three Reversals · In-Place Modification

🔗 [LeetCode – 189. Rotate Array](https://leetcode.com/problems/rotate-array/)

---

## ⚡ Quick Revision

| Concept      | Details                                         |
| ------------ | ----------------------------------------------- |
| Goal         | Rotate the array to the right by `k` positions  |
| Main Pattern | Array Manipulation + Reversal                   |
| Technique    | Three-Reversal Algorithm                        |
| Key Idea     | Reverse the whole array, then reverse two parts |
| In-Place     | Yes                                             |
| Time         | `O(n)`                                          |
| Space        | `O(1)`                                          |

---

## 🧠 Problem in Simple Words

You are given an integer array `nums` and an integer `k`.

You need to **rotate the array to the right by `k` positions**.

### Example

```text
Input:
nums = [1,2,3,4,5,6,7]
k = 3

Output:
[5,6,7,1,2,3,4]
```

The last `k` elements:

```text
5 6 7
```

need to move to the beginning.

---

## 🔥 How to Recognize This Problem in a Placement

Look for these clues:

* The array needs to be **rotated**.
* Rotation is toward the left or right.
* The problem asks for an **in-place** solution.
* Extra array space should be avoided.
* You need to achieve `O(n)` time and `O(1)` space.

When you see:

> **Rotate an array + in-place + constant extra space**

Think:

> **Three Reversals**

---

## 🧩 How to Think / Derive the Solution

Suppose:

```text
nums = [1,2,3,4,5,6,7]
k = 3
```

We want:

```text
[5,6,7,1,2,3,4]
```

The important observation is:

```text
Original:
[1,2,3,4] [5,6,7]

Required:
[5,6,7] [1,2,3,4]
```

So the last `k` elements need to come to the front.

Instead of physically shifting every element, we can use **reversal**.

### Step 1 — Reverse the entire array

```text
[1,2,3,4,5,6,7]

        ↓

[7,6,5,4,3,2,1]
```

### Step 2 — Reverse the first `k` elements

```text
[7,6,5] [4,3,2,1]

        ↓

[5,6,7] [4,3,2,1]
```

### Step 3 — Reverse the remaining elements

```text
[5,6,7] [4,3,2,1]

        ↓

[5,6,7] [1,2,3,4]
```

Final answer:

```text
[5,6,7,1,2,3,4]
```

---

## 📊 Visual Explanation

The complete transformation is:

```text
Original
┌───────────────┬─────────┐
│   1 2 3 4     │ 5 6 7  │
└───────────────┴─────────┘
        k = 3

Reverse everything

┌─────────┬───────────────┐
│ 7 6 5   │ 4 3 2 1       │
└─────────┴───────────────┘

Reverse first k

┌─────────┬───────────────┐
│ 5 6 7   │ 4 3 2 1       │
└─────────┴───────────────┘

Reverse remaining part

┌─────────┬───────────────┐
│ 5 6 7   │ 1 2 3 4       │
└─────────┴───────────────┘
```

---

## 🔁 Algorithm

### Three-Reversal Algorithm

1. Let `n` be the array length.
2. Reduce `k` using:

   ```java
   k = k % n;
   ```

   because rotating by `n` positions gives the original array.
3. Reverse the entire array.
4. Reverse the first `k` elements.
5. Reverse the remaining `n-k` elements.

### Formula

```text
reverse(0, n-1)
reverse(0, k-1)
reverse(k, n-1)
```

---

## 💻 My Solution

```java
class Solution {
    public void rotate(int[] nums, int k) {
        int n = nums.length-1;
        k = k%(n+1);
        reverse(nums,0,n);
        reverse(nums,0,k-1);
        reverse(nums,k,n);
    }

    void reverse(int[] arr,int left, int right){
        while(left<right){
            int t = arr[left];
            arr[left] = arr[right];
            arr[right] = t;
            right--;
            left++;
        }
    }
}
```

### Code Explanation

#### 1. Find the last index

```java
int n = nums.length-1;
```

Here, `n` represents the last valid index of the array.

For an array of length `7`:

```text
indices: 0 1 2 3 4 5 6
```

So:

```text
n = 6
```

#### 2. Handle large `k`

```java
k = k%(n+1);
```

Since `n + 1` is the array length, this is equivalent to:

```java
k = k % nums.length;
```

For example:

```text
k = 10
length = 7

10 % 7 = 3
```

Rotating by `10` positions is therefore the same as rotating by `3`.

#### 3. Reverse the entire array

```java
reverse(nums,0,n);
```

#### 4. Reverse the first `k` elements

```java
reverse(nums,0,k-1);
```

#### 5. Reverse the remaining elements

```java
reverse(nums,k,n);
```

#### 6. Reverse helper

```java
void reverse(int[] arr,int left, int right)
```

The two pointers move toward each other:

```java
right--;
left++;
```

After every swap.

---

## 🧠 Pattern Connection

This problem combines:

### Two Pointers

The `reverse()` function uses two pointers:

```text
left →          ← right
```

They move toward the center while swapping elements.

### In-Place Modification

No additional array is created.

The original array is modified directly.

### Reversal Technique

Three reversals are used to perform the rotation efficiently.

This is an important array manipulation technique to remember for placement coding rounds.

---

## ⚔️ Similar Problems / Variations

### Left Rotation

Instead of rotating right:

```text
[1,2,3,4,5]
```

by `2`:

```text
[3,4,5,1,2]
```

The same reversal idea can be adapted.

### Related Problems

* **26. Remove Duplicates from Sorted Array** → Two Pointers + In-Place
* **27. Remove Element** → Write Pointer + In-Place
* **283. Move Zeroes** → Two Pointers + In-Place
* **88. Merge Sorted Array** → Reverse Traversal + In-Place
* **977. Squares of a Sorted Array** → Two Pointers + Reverse Filling

The common theme is:

> **Modify the array efficiently without unnecessary extra space.**

---

## 🚨 Common Mistakes

### 1. Forgetting `k % n`

If:

```text
length = 5
k = 12
```

you should use:

```text
12 % 5 = 2
```

Otherwise the reversal boundaries can become incorrect.

---

### 2. Forgetting to move the pointers

Inside `reverse()`:

```java
left++;
right--;
```

must be performed after every swap.

Otherwise:

```java
while(left < right)
```

never becomes false.

---

### 3. Incorrect reversal boundaries

For right rotation:

```java
reverse(nums, 0, n - 1);
reverse(nums, 0, k - 1);
reverse(nums, k, n - 1);
```

Be careful about the difference between **array length** and **last index**.

---

### 4. Using an extra array unnecessarily

A simple solution may create another array and place elements at their rotated positions.

That works, but it uses:

```text
O(n) extra space
```

The three-reversal approach achieves:

```text
O(1) extra space
```

---

## ⏱️ Complexity

### Time Complexity

```text
O(n)
```

The array is traversed a constant number of times.

### Space Complexity

```text
O(1)
```

Only a temporary variable is used for swapping.

---

## 🎯 Placement-Level Takeaway

When you see:

```text
Rotate Array
+
In-place
+
O(1) extra space
```

immediately consider:

```text
Three Reversals
```

Remember the sequence:

```text
1. Reverse entire array
2. Reverse first k elements
3. Reverse remaining elements
```

And always normalize:

```java
k = k % n;
```

This problem is a good example of how a simple **reversal operation + two pointers** can solve an array transformation problem without using extra memory.

---

## ⚡ Final 30-Second Cheat Sheet

```text
Right rotate by k:

k = k % n

reverse(0, n-1)
reverse(0, k-1)
reverse(k, n-1)
```

### Core Pattern

```text
Array
 ↓
In-Place
 ↓
Reversal
 ↓
Two Pointers
```

### Complexity

```text
Time  → O(n)
Space → O(1)
```

### Recognition

> **Array rotation + in-place + constant space → Think Three Reversals.**

---

> **Nijoy P Jose**
>
> This solution is part of my **Data Structures & Algorithms** placement preparation repository, where I document problem-solving patterns, interview techniques, and Java implementations to strengthen my coding skills.
