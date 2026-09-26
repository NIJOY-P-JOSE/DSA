# 15. 3Sum

**Difficulty:** Medium
**Main Pattern:** Two Pointers
**Related Patterns:** Sorting, Array Traversal, Duplicate Handling
**LeetCode:** https://leetcode.com/problems/3sum/

---

## ⚡ Quick Revision

| Concept            | Details                                                                         |
| ------------------ | ------------------------------------------------------------------------------- |
| Goal               | Find all unique triplets whose sum is `0`                                       |
| Main Pattern       | Sort + Two Pointers                                                             |
| State              | Fixed index `i`, with `left` and `right` searching for the remaining two values |
| Key Decision       | `sum < 0 → left++`, `sum > 0 → right--`                                         |
| Duplicate Handling | Skip duplicate `i`, `left`, and `right` values                                  |
| Time               | `O(n²)`                                                                         |
| Extra Space        | `O(1)` excluding the output                                                     |

### 30-Second Idea

```text
Sort the array
      ↓
Fix nums[i]
      ↓
left = i + 1
right = n - 1
      ↓
Check nums[i] + nums[left] + nums[right]
      ↓
sum < 0  → left++
sum > 0  → right--
sum == 0 → save triplet + move both
      ↓
Skip duplicates
```

---

## 🧠 Problem in Simple Words

You are given an integer array.

Find **all unique combinations of three different elements** whose sum is `0`.

For example:

```text
nums = [-1, 0, 1, 2, -1, -4]
```

After sorting:

```text
[-4, -1, -1, 0, 1, 2]
```

The valid triplets are:

```text
[-1, -1, 2]
[-1, 0, 1]
```

The same triplet must not appear more than once.

---

## 🔥 How to Recognize This Problem in a Placement

Look for these clues:

* Need to find **three elements**
* Need a particular sum, here `0`
* Array can be sorted
* Duplicate combinations must be avoided
* `n` can be around thousands
* A brute-force `O(n³)` solution would be too slow

### Recognition Trigger

When you see:

> **"Find three numbers whose sum is..."**

immediately think:

```text
3Sum
  ↓
Sort
  ↓
Fix one element
  ↓
Two Pointer for the remaining two
```

This is an extension of the **Two Sum II** pattern.

---

## 🧩 How to Think / Derive the Solution

### Step 1 — Start with the brute force idea

We need three indices:

```text
i, j, k
```

We could try every combination:

```text
for i
    for j
        for k
```

This gives:

```text
O(n³)
```

For `n = 3000`, this is too expensive.

So we need to reduce one dimension.

---

### Step 2 — Fix one element

Choose `nums[i]`.

Now the problem becomes:

```text
Find two numbers whose sum is:

-nums[i]
```

So:

```text
nums[i] + nums[left] + nums[right] = 0
```

This is basically a **Two Sum** problem.

---

### Step 3 — Sort the array

After sorting:

```text
[-4, -1, -1, 0, 1, 2]
```

Now we can use two pointers.

For a fixed `i`:

```text
        i    left              right
        ↓     ↓                  ↓
[-4, -1, -1, 0, 1, 2]
```

---

### Step 4 — Use two pointers

Calculate:

```text
sum = nums[i] + nums[left] + nums[right]
```

There are three possibilities.

#### If `sum < 0`

The sum is too small.

Because the array is sorted, increase the smaller value:

```text
left++
```

#### If `sum > 0`

The sum is too large.

Decrease the larger value:

```text
right--
```

#### If `sum == 0`

We found a valid triplet.

Save it:

```text
[nums[i], nums[left], nums[right]]
```

Then move both pointers:

```text
left++
right--
```

---

## 🔁 Algorithm

```text
1. Sort nums.

2. Initialize result.

3. For every possible i:
      a. Skip nums[i] if it is a duplicate.
      b. Set left = i + 1.
      c. Set right = n - 1.

4. While left < right:
      a. Calculate sum of nums[i], nums[left], nums[right].

      b. If sum == 0:
            Add the triplet to result.
            Move left forward.
            Move right backward.
            Skip duplicate left values.
            Skip duplicate right values.

      c. If sum > 0:
            Move right backward.

      d. If sum < 0:
            Move left forward.

5. Return result.
```

---

## 💻 My Solution

```java
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);

        int n = nums.length;
        List<List<Integer>> result = new ArrayList<>();

        for (int i = 0; i < n - 2; i++) {

            if (i > 0 && nums[i] == nums[i - 1])
                continue;

            int left = i + 1;
            int right = n - 1;

            while (left < right) {

                int sum = nums[left] + nums[right] + nums[i];

                if (sum == 0) {

                    List<Integer> arr = new ArrayList<>();
                    arr.add(nums[i]);
                    arr.add(nums[left]);
                    arr.add(nums[right]);

                    result.add(arr);

                    right--;
                    left++;

                    while (left < right && nums[left] == nums[left - 1])
                        left++;

                    while (left < right && nums[right] == nums[right + 1])
                        right--;

                } else if (sum > 0) {
                    right--;
                } else {
                    left++;
                }
            }
        }

        return result;
    }
}
```

---

## 🧠 Code Explanation

### 1. Sort the array

```java
Arrays.sort(nums);
```

Sorting is important because it allows us to determine which pointer to move.

---

### 2. Fix the first element

```java
for (int i = 0; i < n - 2; i++)
```

We leave at least two elements after `i` because a triplet needs three elements.

---

### 3. Skip duplicate first elements

```java
if (i > 0 && nums[i] == nums[i - 1])
    continue;
```

Suppose:

```text
[-1, -1, 0, 1, 2]
 ↑   ↑
 i   duplicate
```

Both `-1`s would produce the same set of possible triplets.

So we process the first `-1` and skip the second.

---

### 4. Initialize the two pointers

```java
int left = i + 1;
int right = n - 1;
```

The two pointers search the portion of the array after `i`.

---

### 5. Calculate the sum

```java
int sum = nums[left] + nums[right] + nums[i];
```

Now determine how to adjust the pointers.

---

### 6. Sum is zero

```java
if (sum == 0)
```

Create and store the triplet:

```java
List<Integer> arr = new ArrayList<>();
arr.add(nums[i]);
arr.add(nums[left]);
arr.add(nums[right]);

result.add(arr);
```

Then:

```java
right--;
left++;
```

We move both pointers because the current pair has already been used.

---

### 7. Skip duplicate values

After moving:

```java
while (left < right && nums[left] == nums[left - 1])
    left++;
```

and:

```java
while (left < right && nums[right] == nums[right + 1])
    right--;
```

This prevents duplicate triplets.

---

### 8. Sum is too large

```java
else if (sum > 0)
    right--;
```

Since the array is sorted, decreasing `right` gives us a smaller value.

---

### 9. Sum is too small

```java
else
    left++;
```

Increasing `left` gives us a larger value.

---

## 📊 Visual Example

For:

```text
[-1, 0, 1, 2, -1, -4]
```

After sorting:

```text
[-4, -1, -1, 0, 1, 2]
```

Fix:

```text
i
↓
[-4, -1, -1, 0, 1, 2]
```

Try:

```text
-4 + (-1) + 2 = -3
```

Too small:

```text
left++
```

Eventually:

```text
-1 + 0 + 1 = 0
```

Valid:

```text
[-1, 0, 1]
```

Another valid combination:

```text
-1 + (-1) + 2 = 0
```

So:

```text
Result:
[
    [-1, -1, 2],
    [-1, 0, 1]
]
```

---

## 🧠 Why Sorting Is Important

Without sorting, we cannot confidently decide which pointer to move.

After sorting:

```text
smaller ← ---------------- → larger
```

Therefore:

```text
sum < 0
    ↓
Need a larger value
    ↓
left++


sum > 0
    ↓
Need a smaller value
    ↓
right--
```

This is the key reason the two-pointer technique works.

---

## 🧠 Pattern Connection

### 3Sum is an extension of Two Sum II

You previously solved:

```text
167. Two Sum II
```

There:

```text
Find two numbers with target sum
        ↓
left + right
```

Here:

```text
15. 3Sum
        ↓
Fix one number
        ↓
Find two numbers with target = -nums[i]
        ↓
Two Pointers
```

So the mental connection is:

```text
Two Sum
   ↓
Two Sum II
   ↓
3Sum
   ↓
More advanced k-Sum problems
```

This makes 3Sum an important problem for recognizing how a known pattern can be extended.

---

## ⚔️ Similar Problems / Variations

| Problem          | Main Pattern           |
| ---------------- | ---------------------- |
| 1. Two Sum       | Hashing                |
| 167. Two Sum II  | Two Pointers           |
| 15. 3Sum         | Sort + Two Pointers    |
| 16. 3Sum Closest | Sort + Two Pointers    |
| 18. 4Sum         | Sorting + Two Pointers |

The most important connection is:

```text
167. Two Sum II → 15. 3Sum
```

---

## 🚨 Common Mistakes

### 1. Using three nested loops

```java
for (i)
    for (j)
        for (k)
```

This gives `O(n³)`.

Use:

```text
Sort + Fix one + Two Pointers
```

---

### 2. Forgetting to sort

Without sorting, the pointer movement logic does not work.

---

### 3. Forgetting duplicate `i`

```java
if (i > 0 && nums[i] == nums[i - 1])
    continue;
```

Without this, duplicate triplets can appear.

---

### 4. Forgetting duplicate `left` and `right`

After finding a valid triplet:

```java
left++;
right--;
```

Then skip repeated values.

---

### 5. Not moving both pointers after finding a triplet

After:

```text
sum == 0
```

you must continue searching for other combinations.

Therefore:

```java
left++;
right--;
```

---

### 6. Creating the triplet list outside the loop

Each valid triplet needs its own `List<Integer>`.

Correct:

```java
List<Integer> arr = new ArrayList<>();
```

inside the `sum == 0` block.

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

Ignoring the returned output:

```text
O(1)
```

The result itself requires space proportional to the number of triplets returned.

---

## 🎯 Placement-Level Takeaway

When you see:

> Find all unique triplets satisfying a sum condition.

Think:

```text
Can I sort?
      ↓
Can I fix one element?
      ↓
Does the remaining problem become Two Sum?
      ↓
Use Two Pointers
      ↓
Handle duplicates
```

The important thing is **not memorizing the 3Sum code**.

Remember the derivation:

```text
3 elements
    ↓
Fix 1
    ↓
Find remaining 2
    ↓
Two Sum
    ↓
Sorted array
    ↓
Two Pointers
```

### Core pattern

```text
3Sum
=
Sorting
+
Fixed Element
+
Two Pointers
+
Duplicate Handling
```

---

## ⚡ Final 30-Second Cheat Sheet

```text
Problem:
Find all unique triplets with sum = 0.

Approach:
Sort → Fix i → Two Pointers

Pointers:
left = i + 1
right = n - 1

Decision:
sum < 0  → left++
sum > 0  → right--
sum == 0 → save + left++ + right--

Duplicates:
Skip duplicate i
Skip duplicate left
Skip duplicate right

Time:
O(n²)

Extra Space:
O(1), excluding output

Mental Model:
3Sum → Fix one → Solve Two Sum
```

> **Nijoy P Jose**
>
> This solution is part of my **Data Structures & Algorithms** placement preparation repository, where I document problem-solving patterns, interview techniques, and Java implementations to strengthen my coding skills.
