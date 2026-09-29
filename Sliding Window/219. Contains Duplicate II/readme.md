# 219. Contains Duplicate II

**Difficulty:** Easy
**Main Pattern:** HashSet + Sliding Window
**Related Patterns:** Hashing, Two Pointers / Window Management

🔗 [LeetCode – 219. Contains Duplicate II](https://leetcode.com/problems/contains-duplicate-ii/)

---

## ⚡ Quick Revision

| Concept            | Idea                                                 |
| ------------------ | ---------------------------------------------------- |
| Goal               | Find a duplicate whose indices are at most `k` apart |
| Main Pattern       | HashSet + Sliding Window                             |
| Data Structure     | `HashSet<Integer>`                                   |
| Window             | Last `k` relevant elements                           |
| Duplicate Check    | `hash.contains(nums[i])`                             |
| Remove Old Element | `hash.remove(nums[i-k])`                             |
| Time               | `O(n)`                                               |
| Space              | `O(min(n, k))`                                       |

### 30-Second Idea

> **Keep a HashSet containing the recent elements. If the current element already exists, a nearby duplicate is found. Remove elements that move outside the allowed distance `k`.**

---

## 🧠 Problem in Simple Words

You are given:

```text
nums = [1,2,3,1]
k = 3
```

We need to determine whether the same value occurs at two **different indices** whose distance is at most `k`.

For the two `1`s:

```text
Index:  0  1  2  3
Value:  1  2  3  1
        ↑        ↑
```

Their distance is:

```text
|0 - 3| = 3
```

Since:

```text
3 <= k
```

the answer is:

```text
true
```

---

# 🔥 How to Recognize This Problem in a Placement

Look for these keywords:

* **duplicate**
* **same value**
* **nearby**
* **within `k`**
* **index difference**
* `|i - j| <= k`

These indicate that we should not simply remember every element.

Instead, we only care about elements that are **close enough to the current index**.

### Recognition

```text
Duplicate?
    ↓
HashSet

Duplicate + nearby?
    ↓
HashSet + Sliding Window
```

---

# 🧩 How to Think / Derive the Solution

## Step 1 — Start from Problem 217

In **217. Contains Duplicate**, the question was:

> Have I seen this number before?

A `HashSet` solves that efficiently.

```java
if(hash.contains(nums[i]))
    return true;
```

But 219 adds another condition:

> Have I seen this number **within the last `k` positions**?

So we cannot keep every previous number forever.

---

## Step 2 — Think About a Window

Suppose:

```text
k = 3
```

For the current index `i`, we only care about previous indices that are at most `3` positions away.

Conceptually:

```text
[current element]
       ↓
... [previous relevant elements] [current]
```

The HashSet represents this **sliding window**.

---

## Step 3 — Check Before Adding

For every element:

```java
if(hash.contains(nums[i]))
    return true;
```

Why check first?

Because if the current value is already inside the window, then we have found:

```text
nums[i] == nums[j]
```

where:

```text
i - j <= k
```

Therefore, we can immediately return `true`.

---

## Step 4 — Add the Current Element

If the value was not found:

```java
hash.add(nums[i]);
```

Now it becomes part of the window for future elements.

---

## Step 5 — Remove the Element That Is Too Far Away

This is the most important part:

```java
if(hash.size() > k)
    hash.remove(nums[i-k]);
```

Consider:

```text
k = 3
```

When we move forward, an old element eventually becomes more than `3` positions away.

For example:

```text
Indices:

0  1  2  3  4
↑           ↑
old         current
```

Distance:

```text
4 - 0 = 4
```

Since:

```text
4 > k
```

index `0` can no longer form a valid pair with the current element.

So we remove:

```java
nums[i-k]
```

---

# 📊 Visual Explanation

For:

```text
nums = [1, 2, 3, 1]
k = 3
```

### Initially

```text
HashSet = {}
```

### i = 0

```text
Current = 1

HashSet:
{}

1 not found
↓
add 1

{1}
```

### i = 1

```text
Current = 2

{1}

2 not found
↓
add 2

{1, 2}
```

### i = 2

```text
Current = 3

{1, 2}

3 not found
↓
add 3

{1, 2, 3}
```

### i = 3

```text
Current = 1

{1, 2, 3}
 ↑
 1 already exists
```

Therefore:

```text
return true
```

---

# 🔁 Algorithm

For every index `i`:

1. Check whether `nums[i]` exists in the HashSet.
2. If it exists, return `true`.
3. Add `nums[i]` to the HashSet.
4. If the window has moved beyond `k`, remove the old element.
5. Continue until the array ends.
6. If no valid duplicate is found, return `false`.

### Pseudocode

```text
create empty HashSet

for every index i:

    if nums[i] is already in HashSet:
        return true

    add nums[i] to HashSet

    if window is larger than k:
        remove nums[i-k]

return false
```

---

# 💻 My Solution

```java
class Solution {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        HashSet<Integer> hash = new HashSet<>();

        for(int i=0;i<nums.length;i++){
            if(hash.contains(nums[i]))
                return true;

            hash.add(nums[i]);

            if(hash.size()>k)
                hash.remove(nums[i-k]);
        }

        return false;
    }
}
```

### Why this works

The solution combines two operations:

```java
hash.contains(nums[i])
```

checks whether the current value already exists in the recent window.

And:

```java
hash.remove(nums[i-k]);
```

removes an element that is no longer close enough to the current index.

Therefore, the HashSet represents the relevant **nearby elements**.

---

# 🧠 Pattern Connection

This problem is an important extension of **217. Contains Duplicate**.

### 217 — Contains Duplicate

```text
Have I seen this value anywhere before?

        ↓

HashSet
```

### 219 — Contains Duplicate II

```text
Have I seen this value recently?

        ↓

HashSet
     +
Sliding Window
```

The important pattern is:

> **Hashing + a limited range of previous elements = HashSet Sliding Window**

---

# ⚔️ Similar Problems / Variations

### 217. Contains Duplicate

Basic HashSet duplicate detection.

### 219. Contains Duplicate II

HashSet + index-distance restriction.

### Sliding Window Problems

Many problems require maintaining information about only the **current range** rather than the entire array.

The key question becomes:

> **What should enter the window, and what should leave the window?**

---

# 🚨 Common Mistakes

### 1. Storing every previous element

Simply doing:

```java
HashSet<Integer> hash = new HashSet<>();

for(int num : nums)
    hash.add(num);
```

does not solve 219.

Why?

Because an old duplicate may be farther than `k`.

---

### 2. Checking after removing

The current element must be checked against the valid previous window **before** removing the old element.

Correct order:

```text
Check
 ↓
Add
 ↓
Remove old
```

---

### 3. Forgetting the index condition

Finding the same value is not enough.

We need:

```text
nums[i] == nums[j]

AND

|i-j| <= k
```

Both conditions matter.

---

### 4. Forgetting that indices must be distinct

We cannot compare an element with itself.

That is why the current value is checked **before** it is added.

---

# ⏱️ Complexity

Let `n` be the number of elements.

### Time

Each element is:

* checked once
* inserted at most once
* removed at most once

Therefore:

```text
O(n)
```

### Space

The HashSet stores only the elements in the current window.

```text
O(min(n, k))
```

---

# 🎯 Placement-Level Takeaway

When you see:

```text
duplicate
+
nearby indices
+
distance <= k
```

immediately think:

```text
HashSet
   +
Sliding Window
```

The core mental model is:

```text
217
 ↓
HashSet

219
 ↓
HashSet
   +
Sliding Window
```

Don't think of the HashSet as storing the entire array.

Think:

> **"I only need to remember the recent elements that are still close enough to the current index."**

That is the key idea behind this problem.

---

# ⚡ Final 30-Second Cheat Sheet

```text
Problem:
Find duplicate values whose indices are <= k apart.

Pattern:
HashSet + Sliding Window

For every nums[i]:

1. If nums[i] is already in HashSet:
       return true

2. Add nums[i]

3. If window becomes too large:
       remove nums[i-k]

4. After loop:
       return false

Complexity:
Time  → O(n)
Space → O(min(n,k))
```

### Recognition Rule

> **"Duplicate within distance k" → HashSet + Sliding Window**

---

> **Nijoy P Jose**
>
> This solution is part of my **Data Structures & Algorithms** placement preparation repository, where I document problem-solving patterns, interview techniques, and Java implementations to strengthen my coding skills.
