# 16. 3Sum Closest

**Difficulty:** Medium
**Main Pattern:** Two Pointers
**Related Patterns:** Sorting, Array Traversal, Closest Value Tracking
**LeetCode:** https://leetcode.com/problems/3sum-closest/

---

## ⚡ Quick Revision

| Concept      | Details                                                          |              |   |
| ------------ | ---------------------------------------------------------------- | ------------ | - |
| Goal         | Find three numbers whose sum is closest to `target`              |              |   |
| Main Pattern | Sort + Two Pointers                                              |              |   |
| State        | Fixed `i`, with `left` and `right` searching for the closest sum |              |   |
| Key Decision | `sum > target → right--`, `sum < target → left++`                |              |   |
| Tracking     | Keep the sum with minimum `                                      | target - sum | ` |
| Exact Match  | If `sum == target`, return immediately                           |              |   |
| Time         | `O(n²)`                                                          |              |   |
| Extra Space  | `O(1)`                                                           |              |   |

### 30-Second Idea

```text
Sort the array
      ↓
Fix nums[i]
      ↓
left = i + 1
right = n - 1
      ↓
Calculate sum
      ↓
Compare |target - sum|
      ↓
Keep the closest sum
      ↓
sum < target → left++
sum > target → right--
sum == target → return target
```

---

## 🧠 Problem in Simple Words

You are given an integer array and a target.

You need to select **three different elements** whose sum is as close as possible to the target.

For example:

```text
nums = [-1, 2, 1, -4]
target = 1
```

Possible useful combination:

```text
-1 + 2 + 1 = 2
```

The answer is:

```text
2
```

because `2` is closest to `1`.

Unlike **3Sum**, we are not looking only for a sum equal to the target.

We are looking for the **closest sum**.

---

## 🔥 How to Recognize This Problem in a Placement

Look for these clues:

* Need to choose **three numbers**
* Need their sum to be close to a target
* The exact target may not be possible
* `n` can be large enough that `O(n³)` is undesirable
* The array can be sorted
* The problem asks for the **closest value**

### Recognition Trigger

When you see:

> **"Find three numbers whose sum is closest to target."**

Think:

```text
3Sum pattern
      +
Closest-value tracking
```

So:

```text
Sort
  ↓
Fix one element
  ↓
Two Pointers
  ↓
Track minimum difference
```

---

## 🧩 How to Think / Derive the Solution

### Step 1 — Start with the 3Sum idea

In 3Sum, we have:

```text
nums[i] + nums[left] + nums[right] = 0
```

For 3Sum Closest, the target changes:

```text
nums[i] + nums[left] + nums[right] ≈ target
```

So the structure of the solution can remain almost the same.

---

### Step 2 — Sort the array

Sort the numbers first:

```text
[-1, 2, 1, -4]
```

becomes:

```text
[-4, -1, 1, 2]
```

Now we know:

```text
left → smaller values
right → larger values
```

This allows us to decide which pointer to move.

---

### Step 3 — Fix one number

Choose:

```text
nums[i]
```

Then use two pointers for the remaining two numbers.

```text
             i    left       right
             ↓     ↓           ↓
[-4, -1, 1, 2]
```

Calculate:

```text
sum = nums[i] + nums[left] + nums[right]
```

---

### Step 4 — Measure how close the sum is

We need to know how far the current sum is from the target.

Use:

```text
difference = |target - sum|
```

For example:

```text
target = 1
sum = 2

difference = |1 - 2|
           = 1
```

If this difference is smaller than the best difference found so far, update the answer.

---

### Step 5 — Decide which pointer to move

Because the array is sorted:

### If:

```text
sum > target
```

The sum is too large.

We need a smaller value:

```text
right--
```

### If:

```text
sum < target
```

The sum is too small.

We need a larger value:

```text
left++
```

### If:

```text
sum == target
```

We found the exact answer.

Nothing can be closer than an exact match, so:

```text
return target
```

---

## 📊 Visual Explanation

Example:

```text
nums = [-1, 2, 1, -4]
target = 1
```

After sorting:

```text
[-4, -1, 1, 2]
```

Suppose:

```text
i = -4
left = -1
right = 2
```

Then:

```text
-4 + (-1) + 2 = -3
```

This is below the target:

```text
-3 < 1
```

So:

```text
left++
```

Now try another combination.

Eventually:

```text
-1 + 1 + 2 = 2
```

Difference:

```text
|1 - 2| = 1
```

So the closest sum is:

```text
2
```

---

## 🔁 Algorithm

```text
1. Sort nums.

2. Initialize:
      closestSum = 0
      diff = Integer.MAX_VALUE

3. For every possible i:
      a. left = i + 1
      b. right = n - 1

4. While left < right:

      a. Calculate:
         sum = nums[i] + nums[left] + nums[right]

      b. Calculate:
         currentDifference = |target - sum|

      c. If currentDifference == 0:
            return target

      d. If currentDifference < diff:
            update diff
            update closestSum

      e. If sum > target:
            right--

         Otherwise:
            left++

5. Return closestSum.
```

---

## 💻 My Solution

```java
class Solution {
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);

        int maxSum = 0;
        int diff = Integer.MAX_VALUE;
        int n = nums.length;

        for (int i = 0; i < n - 2; i++) {

            int left = i + 1;
            int right = n - 1;

            while (left < right) {

                int sum = nums[i] + nums[left] + nums[right];
                int td = Math.abs(target - sum);

                if (td == 0)
                    return target;

                if (td < diff) {
                    diff = td;
                    maxSum = sum;
                }

                if (sum > target)
                    right--;
                else
                    left++;
            }
        }

        return maxSum;
    }
}
```

### Note

`maxSum` works correctly in your implementation, but the name can be slightly misleading because we are not looking for the maximum sum.

A clearer name would be:

```java
int closestSum = 0;
```

The algorithm itself remains exactly the same.

---

## 🧠 Code Explanation

### 1. Sort the array

```java
Arrays.sort(nums);
```

Sorting allows us to use the two-pointer technique.

---

### 2. Track the closest sum

```java
int maxSum = 0;
int diff = Integer.MAX_VALUE;
```

`diff` stores the smallest difference found so far.

For example:

```text
target = 10

sum = 7  → difference = 3
sum = 12 → difference = 2
sum = 9  → difference = 1
```

The current best answer would be `9`.

---

### 3. Fix the first element

```java
for (int i = 0; i < n - 2; i++)
```

We fix one element and search for the other two using two pointers.

---

### 4. Initialize pointers

```java
int left = i + 1;
int right = n - 1;
```

The remaining search space is between `left` and `right`.

---

### 5. Calculate the sum

```java
int sum = nums[i] + nums[left] + nums[right];
```

---

### 6. Calculate the difference

```java
int td = Math.abs(target - sum);
```

This tells us how close the current sum is to the target.

---

### 7. Exact match

```java
if (td == 0)
    return target;
```

An exact match is automatically the closest possible answer.

---

### 8. Update the closest answer

```java
if (td < diff) {
    diff = td;
    maxSum = sum;
}
```

Only replace the previous answer when the current sum is closer.

---

### 9. Move the correct pointer

```java
if (sum > target)
    right--;
else
    left++;
```

Because the array is sorted:

```text
sum > target
→ need smaller value
→ right--

sum < target
→ need larger value
→ left++
```

---

## 🧠 Pattern Connection

This problem is directly connected to **15. 3Sum**.

### 15. 3Sum

Goal:

```text
sum == 0
```

Pattern:

```text
Sort
 ↓
Fix i
 ↓
Two Pointers
 ↓
Find exact sum
```

### 16. 3Sum Closest

Goal:

```text
sum ≈ target
```

Pattern:

```text
Sort
 ↓
Fix i
 ↓
Two Pointers
 ↓
Track closest sum
```

So the important progression is:

```text
167. Two Sum II
       ↓
15. 3Sum
       ↓
16. 3Sum Closest
```

You are reusing the same two-pointer reasoning instead of learning a completely new technique.

---

## ⚔️ Similar Problems / Variations

| Problem           | Main Idea                              |
| ----------------- | -------------------------------------- |
| 167. Two Sum II   | Two Pointers                           |
| 15. 3Sum          | Sort + Two Pointers                    |
| 16. 3Sum Closest  | Sort + Two Pointers + Closest Tracking |
| 18. 4Sum          | Sort + Two Pointers                    |
| 259. 3Sum Smaller | Sort + Two Pointers                    |

The most important connection is:

```text
15. 3Sum
    ↓
Change "exact sum" into "closest sum"
    ↓
16. 3Sum Closest
```

---

## 🚨 Common Mistakes

### 1. Forgetting `Math.abs()`

Wrong:

```java
int diff = target - sum;
```

Correct:

```java
int diff = Math.abs(target - sum);
```

We need the **distance**, not the signed difference.

---

### 2. Moving the wrong pointer

Remember:

```text
sum > target → right--
sum < target → left++
```

Because the array is sorted.

---

### 3. Using `O(n³)`

Three nested loops work conceptually, but are unnecessarily slow.

Use:

```text
Sort + Fix one + Two Pointers
```

to achieve `O(n²)`.

---

### 4. Forgetting the exact-match case

If:

```java
sum == target
```

the answer is already perfect.

Return immediately:

```java
return target;
```

---

### 5. Thinking this is the same as 3Sum

The structure is similar, but the goal is different.

```text
3Sum:
Find sum == target

3Sum Closest:
Find minimum |target - sum|
```

---

## ⏱️ Complexity

### Time Complexity

Sorting:

```text
O(n log n)
```

Outer loop + two-pointer search:

```text
O(n²)
```

Overall:

```text
O(n²)
```

because `O(n²)` dominates `O(n log n)`.

### Extra Space

The two-pointer algorithm itself uses:

```text
O(1)
```

extra space, excluding the sorting implementation's internal behavior and the returned result.

---

## 🎯 Placement-Level Takeaway

When you see:

> **Find three numbers whose sum is closest to a target.**

Think:

```text
Can I sort?
     ↓
Fix one element
     ↓
Use two pointers
     ↓
Calculate current sum
     ↓
Track minimum |target - sum|
     ↓
Move pointer based on sum vs target
```

The key difference from 3Sum is:

```text
3Sum
→ Looking for an exact match

3Sum Closest
→ Looking for the minimum difference
```

### Core Pattern

```text
3Sum Closest
=
Sorting
+
Fixed Element
+
Two Pointers
+
Closest Difference Tracking
```

---

## ⚡ Final 30-Second Cheat Sheet

```text
Problem:
Find 3 numbers whose sum is closest to target.

Approach:
Sort → Fix i → Two Pointers

Calculate:
sum = nums[i] + nums[left] + nums[right]

Distance:
|target - sum|

Update:
If distance < best distance
→ save this sum

Pointer:
sum < target → left++
sum > target → right--
sum == target → return target

Time:
O(n²)

Extra Space:
O(1)

Mental Model:
3Sum + Closest Difference
```

> **Nijoy P Jose**
>
> This solution is part of my **Data Structures & Algorithms** placement preparation repository, where I document problem-solving patterns, interview techniques, and Java implementations to strengthen my coding skills.
