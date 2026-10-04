# 1004. Max Consecutive Ones III

[LeetCode Problem](https://leetcode.com/problems/max-consecutive-ones-iii/)

**Difficulty:** Medium  
**Pattern:** Variable-Size Sliding Window  
**Language:** Java

---

## Quick Revision

### Pattern: Variable-Size Sliding Window

This problem asks for the **longest contiguous subarray** where we can have at most `k` zeroes.

We don't actually need to flip the zeroes.

Instead, we maintain a window and count how many zeroes are inside it.

### Core Rule

```text
zeroCount <= k → valid window
zeroCount > k  → invalid window
```

When the window becomes invalid, move `left` forward until the number of zeroes becomes valid again.

### Template

```java
int left = 0;

for (int right = 0; right < nums.length; right++) {

    // Add nums[right] to the window

    while (window is invalid) {

        // Remove nums[left] from the window

        left++;
    }

    // Update maximum answer
}
```

---

# Problem Understanding

Given a binary array `nums` and an integer `k`, we can flip at most `k` zeroes to ones.

We need to find the **maximum length of consecutive ones** that can be obtained.

For example:

```text
nums = [1,1,1,0,0,0,1,1,1,1,0]
k = 2
```

We can choose:

```text
[1,1,1,0,0,1,1,1,1,1]
       ↑   ↑
     flip flip
```

The answer is:

```text
6
```

---

# Placement Recognition

When you see:

- **Longest**
- **Contiguous**
- **Subarray**
- A condition that can be maintained while moving through the array

Think:

> **Sliding Window**

Here the condition is:

> The window can contain at most `k` zeroes.

Therefore:

```text
Longest valid window
        ↓
Variable-size Sliding Window
```

---

# Why Sliding Window?

A brute-force approach could check every possible subarray.

There are `O(n²)` possible subarrays, so that would be too slow for:

```text
n <= 100000
```

Instead, we maintain one window:

```text
[left ........ right]
```

As `right` moves forward, we add elements.

If the window contains too many zeroes, we move `left` forward until the window becomes valid again.

This allows both pointers to move only forward.

Therefore, the overall complexity becomes:

```text
O(n)
```

---

# Key Observation

We don't actually need to perform the flips.

Suppose:

```text
window = [1, 1, 0, 1, 0, 1]
```

There are:

```text
2 zeroes
```

If:

```text
k = 2
```

then we can flip those two zeroes:

```text
[1, 1, 1, 1, 1, 1]
```

Therefore, instead of modifying the array, we only need to count zeroes.

The problem becomes:

> Find the longest subarray containing at most `k` zeroes.

This is the key transformation.

---

# Deriving the Algorithm

Suppose:

```text
left = 0
right = 0
```

We expand the window using `right`.

Whenever we see a zero:

```java
if(nums[right] == 0)
    zeroCount++;
```

Now check whether the window is valid.

```text
zeroCount <= k
```

If:

```text
zeroCount > k
```

the window is invalid.

So we move `left` forward.

When an element leaves the window:

```java
if(nums[left] == 0)
    zeroCount--;
```

Continue shrinking until:

```text
zeroCount <= k
```

Then the window is valid again.

Finally:

```java
maxLength = Math.max(maxLength, right - left + 1);
```

---

# Visual Example

Consider:

```text
nums = [1,1,0,0,1]
k = 1
```

Start:

```text
[1]
zeroCount = 0
valid
```

Expand:

```text
[1,1]
zeroCount = 0
valid
```

Add `0`:

```text
[1,1,0]
zeroCount = 1
valid
```

Add another `0`:

```text
[1,1,0,0]
zeroCount = 2
invalid
```

Since:

```text
2 > k
```

move `left`.

```text
[1,0,0]
```

Still:

```text
zeroCount = 2
```

Move `left` again:

```text
[0,0]
```

Still:

```text
zeroCount = 2
```

Move `left` again and remove the zero:

```text
[0]
zeroCount = 1
```

Now the window is valid.

The important point is:

> We keep shrinking until the window satisfies the condition.

---

# Standard Java Solution

```java
class Solution {
    public int longestOnes(int[] nums, int k) {

        int left = 0;
        int zeroCount = 0;
        int maxLength = 0;

        for (int right = 0; right < nums.length; right++) {

            if (nums[right] == 0)
                zeroCount++;

            while (zeroCount > k) {

                if (nums[left] == 0)
                    zeroCount--;

                left++;
            }

            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }
}
```

---

# Code Explanation

### 1. `left`

```java
int left = 0;
```

Represents the beginning of the current window.

---

### 2. `zeroCount`

```java
int zeroCount = 0;
```

Stores the number of zeroes currently inside:

```text
[left ... right]
```

---

### 3. Expand the window

```java
for (int right = 0; right < nums.length; right++)
```

`right` moves from left to right and expands the window.

---

### 4. Count zeroes

```java
if (nums[right] == 0)
    zeroCount++;
```

Whenever a zero enters the window, increase `zeroCount`.

---

### 5. Shrink when invalid

```java
while (zeroCount > k)
```

The window is invalid if it contains more zeroes than we are allowed to flip.

So move `left`.

```java
if (nums[left] == 0)
    zeroCount--;

left++;
```

If the element leaving the window is a zero, decrease `zeroCount`.

---

### 6. Update the answer

```java
maxLength = Math.max(maxLength, right - left + 1);
```

Once the window becomes valid, calculate its length.

```text
length = right - left + 1
```

Keep the maximum.

---

# My Solution

```java
class Solution {
    public int longestOnes(int[] nums, int k) {
        int c = 0, left = 0;
        int max1 = 0;

        for(int right = 0; right < nums.length; right++){

            if(nums[right] == 0)
                c++;

            while(c > k){
                if(nums[left] == 0)
                    c--;

                left++;
            }

            max1 = Math.max(max1, right - left + 1);
        }

        return max1;
    }
}
```

---

# My Solution Explained

My variable:

```java
c
```

represents:

> Number of zeroes currently inside the sliding window.

So:

```java
if(nums[right] == 0)
    c++;
```

adds a zero when `right` enters the window.

Then:

```java
while(c > k)
```

checks whether the window has become invalid.

If an element leaving from the left is zero:

```java
if(nums[left] == 0)
    c--;
```

Then:

```java
left++;
```

moves the window forward.

Finally:

```java
max1 = Math.max(max1, right - left + 1);
```

stores the longest valid window.

---

# Connection With Previous Sliding Window Problems

This problem is useful because it shows another form of the same pattern.

| Problem | Goal | Invalid Condition | Answer |
|---|---|---|---|
| 904 Fruit Into Baskets | Longest | More than 2 types | Maximum |
| 209 Minimum Size Subarray Sum | Shortest | Sum not enough / validity changes | Minimum |
| 1004 Max Consecutive Ones III | Longest | `zeroCount > k` | Maximum |

### 904

```text
Longest valid window
Shrink when invalid
```

### 1004

```text
Longest valid window
Shrink when invalid
```

The difference is only the condition used to determine whether the window is valid.

---

# Important Pattern

For **longest valid sliding window**:

```java
for (right...) {

    // Add right element

    while (window is invalid) {

        // Remove left element
        left++;
    }

    // Maximize answer
}
```

For this problem:

```java
while (zeroCount > k)
```

is the critical condition.

---

# Common Mistakes

### 1. Reversing the condition

Wrong:

```java
while (zeroCount <= k)
```

Correct:

```java
while (zeroCount > k)
```

We only shrink when the window is **invalid**.

---

### 2. Actually flipping the zeroes

We don't need to modify the array.

Just count:

```java
zeroCount
```

---

### 3. Removing only one element

The window may remain invalid after moving `left` once.

That's why we use:

```java
while (zeroCount > k)
```

rather than:

```java
if (zeroCount > k)
```

---

### 4. Forgetting to decrease the zero count

When removing an element:

```java
if(nums[left] == 0)
    zeroCount--;
```

Otherwise `zeroCount` would no longer represent the actual window.

---

### 5. Updating the answer before restoring validity

Always make the window valid first:

```java
while(zeroCount > k) {
    ...
}
```

Then:

```java
maxLength = Math.max(maxLength, right - left + 1);
```

---

# Complexity

### Time Complexity

```text
O(n)
```

Although there is a `while` loop inside the `for` loop, this is still `O(n)`.

Why?

`right` moves from:

```text
0 → n-1
```

and `left` also only moves forward:

```text
0 → n-1
```

Neither pointer moves backward.

Therefore, each element is processed a limited number of times.

### Space Complexity

```text
O(1)
```

Only a few variables are used.

---

# Placement Takeaway

When you see:

```text
Longest
+
Subarray / Substring
+
A condition that can be maintained
```

ask yourself:

> **Can I maintain a window and adjust its left boundary when the condition becomes invalid?**

If yes, try **Sliding Window**.

For this problem:

```text
Binary Array
      ↓
Count zeroes
      ↓
At most k zeroes
      ↓
Longest valid window
      ↓
Variable-Size Sliding Window
```

---

# Final Cheat Sheet

```text
Pattern:
Variable-Size Sliding Window

Goal:
Longest valid subarray

State:
zeroCount

Valid:
zeroCount <= k

Invalid:
zeroCount > k

When adding:
if nums[right] == 0
    zeroCount++

When shrinking:
if nums[left] == 0
    zeroCount--

Then:
left++

Answer:
max(right - left + 1)

Time:
O(n)

Space:
O(1)
```

### One-line memory trick

> **For 1004: expand → count zeroes → if zeroes exceed k, shrink → maximize window.**
