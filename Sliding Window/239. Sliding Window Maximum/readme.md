# 239. Sliding Window Maximum

> **Difficulty:** Hard  
> **Pattern:** Sliding Window + Monotonic Deque  
> **Related Concepts:** Array, Deque, Monotonic Queue  
> **Platform:** [LeetCode](https://leetcode.com/problems/sliding-window-maximum/)

---

## ⚡ 1. Quick Revision

| Concept | Remember |
|---|---|
| Goal | Find the maximum element in every window of size `k` |
| Pattern | Fixed-Size Sliding Window + Monotonic Deque |
| Data Structure | Deque (Double-Ended Queue) |
| Stores | Indices of useful elements |
| Ordering | Values are maintained in decreasing order |
| Front | Index of the maximum element |
| Back | Position where new candidates are added |
| Time | `O(n)` |
| Space | `O(k)` |

**Core idea:** Maintain a deque of indices in decreasing order of their corresponding values. The front always points to the maximum of the current window.

---

## 🧠 2. Problem in Simple Words

You are given an array and a window of size `k`.

The window starts from the beginning and moves one position to the right each time.

For every window, find its maximum element.

**Example:**

```text
nums = [1, 3, -1, -3, 5, 3, 6, 7]
k = 3

Windows:                  Maximum:

[1, 3, -1]                   3
[3, -1, -3]                  3
[-1, -3, 5]                  5
[-3, 5, 3]                   5
[5, 3, 6]                    6
[3, 6, 7]                    7

Output = [3, 3, 5, 5, 6, 7]
```

The challenge is to find every maximum efficiently without scanning all `k` elements again for every window.

---

## 🔥 3. How to Recognize This Problem in a Placement

Look for these clues:

- A window of a fixed size `k`.
- The window moves one position at a time.
- You need the maximum or minimum in every window.
- Repeatedly finding the maximum would be inefficient.

### Recognition Trigger

> **Fixed-size sliding window + maximum/minimum in every window → Think Monotonic Deque.**

### Why not use a normal Sliding Window?

A normal sliding window efficiently maintains values such as sum or count.

But maintaining the maximum is different.

When the maximum element leaves the window, we need to know which remaining element becomes the new maximum.

A simple variable cannot maintain this information efficiently.

That is where a **Monotonic Deque** becomes useful.

---

## 🧩 4. How to Think / Derive the Solution

### Step 1: Start with Brute Force

For every possible window:

1. Traverse all `k` elements.
2. Find the maximum.
3. Store it in the result.
4. Move the window one position right.

For `n` elements, there are `n - k + 1` windows.

Each window requires `O(k)` work.

```text
Time = O((n-k+1) * k)
     = O(n*k)
```

This is too slow for `n = 100000`.

### Step 2: Identify Repeated Work

Consider:

```text
[1, 3, -1] -3 5 3 6 7
  ↓

  1 [3, -1, -3] 5 3 6 7
```

The windows overlap.

Most elements are common between consecutive windows.

Instead of finding the maximum from scratch, we should maintain useful information from the previous window.

### Step 3: What Information Do We Need?

We need to know:

- Which elements are still inside the current window?
- Which elements can potentially become the maximum?
- Which element is currently the maximum?

A deque can help us maintain these candidates.

### Step 4: Discover the Important Observation

Consider:

```text
Current candidates:

[3, 1]
```

Now a new element `5` enters.

```text
[3, 1, 5]
```

Can `3` or `1` ever become the maximum in a future window while `5` is still present?

No.

Why?

- `5` is greater than both.
- `5` is newer, so it will remain in the window longer than both.
- Therefore, `3` and `1` can never be useful maximum candidates again.

We can safely remove them.

```text
Before: [3, 1]
New:    5

Remove 1
Remove 3

After:  [5]
```

This is the most important observation.

**Whenever a new element is greater than or equal to the elements at the back of the deque, those smaller elements can be removed.**

### Step 5: Why Store Indices Instead of Values?

Suppose:

```text
nums = [1, 3, -1, -3, 5]
k = 3
```

When the window moves, we need to identify whether an element has gone outside the window.

If we store only values, identifying the exact position becomes difficult, especially when duplicates exist.

Therefore, store indices.

```text
Index:  0  1   2   3  4
Value:  1  3  -1  -3  5
```

We can access an element using:

```java
nums[index]
```

We can also check whether an index is outside the current window.

### Step 6: Maintain a Decreasing Deque

The deque stores indices, but the corresponding values must be in decreasing order.

For example:

```text
Deque indices: [1, 2, 3]

Deque values:  [3, -1, -3]
```

The front always contains the index of the maximum element.

When a new element enters:

1. Remove expired indices from the front.
2. Remove smaller or equal elements from the back.
3. Add the current index to the back.
4. Read the maximum from the front once the first complete window is formed.

---

## 📊 5. Visual Explanation

Let's understand how the deque changes for the example.

```text
nums = [1, 3, -1, -3, 5, 3, 6, 7]
k = 3
```

### Iteration 1: Index 0, Value 1

Deque is empty.

Add index `0`.

```text
Deque indices: [0]
Deque values:  [1]
```

### Iteration 2: Index 1, Value 3

Compare `3` with the back value `1`.

Since `1 <= 3`, remove index `0`.

Add index `1`.

```text
Deque indices: [1]
Deque values:  [3]
```

### Iteration 3: Index 2, Value -1

`-1` is smaller than `3`, so keep both.

```text
Deque indices: [1, 2]
Deque values:  [3, -1]
```

The first window is now complete.

Maximum = `nums[1] = 3`.

### Iteration 4: Index 3, Value -3

Add `-3` because it is smaller than `-1`.

```text
Deque indices: [1, 2, 3]
Deque values:  [3, -1, -3]
```

The current window is `[3, -1, -3]`.

Maximum = `3`.

### Iteration 5: Index 4, Value 5

First, check whether the front index has expired.

The current window is:

```text
Index range: [2, 4]

[1, 3, -1, -3, 5, 3, 6, 7]
    ↑       ↑
  left    right
```

Index `1` is outside the window, so remove it.

Now compare `5` with the back values:

```text
5 > -3  → Remove -3
5 > -1  → Remove -1
```

Add index `4`.

```text
Deque values: [5]
```

Maximum = `5`.

### Iteration 6: Index 5, Value 3

`3` is smaller than `5`.

Keep both.

```text
Deque values: [5, 3]
```

Maximum = `5`.

### Iteration 7: Index 6, Value 6

`6` is greater than both `3` and `5`.

Remove both and add `6`.

```text
Deque values: [6]
```

Maximum = `6`.

### Iteration 8: Index 7, Value 7

`7` is greater than `6`.

Remove `6` and add `7`.

```text
Deque values: [7]
```

Maximum = `7`.

### Final Result

```text
[3, 3, 5, 5, 6, 7]
```

---

## 💻 6. Algorithm

1. Create a result array of size `n - k + 1`.
2. Create a deque to store indices.
3. Traverse the array using `i`.
4. Remove expired indices from the front:
   - If `deque.peekFirst() <= i - k`, remove it.
5. Maintain decreasing order:
   - While the deque is not empty and the value at its back index is less than or equal to the current value, remove the back index.
6. Add the current index to the back.
7. Once `i >= k - 1`, the first complete window is formed:
   - The front index represents the maximum.
   - Store `nums[deque.peekFirst()]` in the result.
8. Return the result.

---

## 💻 7. My Java Solution

```java
import java.util.*;

class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int[] res = new int[nums.length-k+1];
        Deque<Integer> deque = new ArrayDeque<>();
        int id = 0;

        for(int i = 0; i < nums.length; i++){

            // Remove indices outside the current window
            while(!deque.isEmpty() && deque.peekFirst() <= i-k){
                deque.pollFirst();
            }

            // Remove smaller or equal elements from the back
            while(!deque.isEmpty() && nums[deque.peekLast()] <= nums[i]){
                deque.pollLast();
            }

            // Add the current index
            deque.offerLast(i);

            // Store maximum after the first complete window
            if(i >= k-1)
                res[id++] = nums[deque.peekFirst()];
        }

        return res;
    }
}
```

---

## 🔍 8. Code Explanation

### 1. Create the result array

```java
int[] res = new int[nums.length-k+1];
```

For an array of length `n` and window size `k`, the number of possible windows is:

```text
n - k + 1
```

Therefore, this is the required result size.

### 2. Create the Deque

```java
Deque<Integer> deque = new ArrayDeque<>();
```

The deque stores **indices**, not actual array values.

We use it because we need to add and remove elements from both ends efficiently.

### 3. Remove Expired Indices

```java
while(!deque.isEmpty() && deque.peekFirst() <= i-k){
    deque.pollFirst();
}
```

The current window covers:

```text
[i-k+1, i]
```

Any index smaller than `i-k+1` is outside the window.

Therefore:

```java
deque.peekFirst() <= i-k
```

means the front index has expired.

`pollFirst()` removes it.

### 4. Maintain Decreasing Order

```java
while(!deque.isEmpty() && nums[deque.peekLast()] <= nums[i]){
    deque.pollLast();
}
```

This is the most important part.

Compare the current element with the element at the back of the deque.

If the current element is greater than or equal to the back element, remove the back index.

**Why?**

The current element is:

- Greater than or equal to the previous element.
- Located at a newer index.
- Going to remain in the window at least as long as the previous element.

Therefore, the previous element is no longer useful as a maximum candidate.

Repeat until the deque is empty or its last value is greater than the current value.

### 5. Add the Current Index

```java
deque.offerLast(i);
```

After removing unnecessary candidates, add the current index at the back.

The deque now maintains decreasing values from front to back.

### 6. Store the Maximum

```java
if(i >= k-1)
    res[id++] = nums[deque.peekFirst()];
```

A complete window is formed when the current index reaches `k-1`.

Because the deque maintains decreasing values, its front contains the index of the maximum element.

Therefore:

```java
nums[deque.peekFirst()]
```

gives the maximum of the current window.

---

## 🧠 9. Understanding the Deque Operations

| Java Method | Meaning |
|---|---|
| `peekFirst()` | Read the front element without removing it |
| `pollFirst()` | Remove the front element |
| `peekLast()` | Read the last element without removing it |
| `pollLast()` | Remove the last element |
| `offerLast(x)` | Add an element at the back |
| `isEmpty()` | Check whether the deque is empty |

### Remember the two ends

```text
FRONT                         BACK
  ↓                             ↓
[Maximum, Next Candidate, Smaller Candidate]

Remove expired              Remove dominated
indices                     candidates

                              Add new index
```

The deque is not storing every element in the window. It stores only elements that could still become the maximum.

---

## 🔗 10. Pattern Connection

This problem extends the fixed-size Sliding Window pattern.

```text
Array
  ↓
Fixed-Size Sliding Window
  ↓
Need Maximum in Every Window
  ↓
Repeated Maximum Search is Expensive
  ↓
Monotonic Deque
  ↓
O(n) Solution
```

### Connection with Previous Problems

| Problem | Pattern | Main Difference |
|---|---|---|
| 643. Maximum Average Subarray I | Fixed Sliding Window | Maintains the window sum |
| 1456. Maximum Number of Vowels | Fixed Sliding Window | Maintains the vowel count |
| 1004. Max Consecutive Ones III | Variable Sliding Window | Shrinks when the number of zeros exceeds `k` |
| 239. Sliding Window Maximum | Sliding Window + Monotonic Deque | Maintains maximum candidates |

**Important distinction:**

- Sum/count in a window → maintain a running variable.
- Maximum/minimum in every window → consider a Monotonic Deque.

---

## ⚔️ 11. Similar Problems / Variations

| Problem | Difference |
|---|---|
| [1438. Longest Continuous Subarray With Absolute Diff Less Than or Equal to Limit](https://leetcode.com/problems/longest-continuous-subarray-with-absolute-diff-less-than-or-equal-to-limit/) | Uses monotonic deques to maintain both maximum and minimum in a variable-size window |
| [239. Sliding Window Maximum](https://leetcode.com/problems/sliding-window-maximum/) | Finds the maximum in every fixed-size window |
| [862. Shortest Subarray with Sum at Least K](https://leetcode.com/problems/shortest-subarray-with-sum-at-least-k/) | Uses prefix sums and a monotonic deque |
| [84. Largest Rectangle in Histogram](https://leetcode.com/problems/largest-rectangle-in-histogram/) | Uses a monotonic stack instead of a deque |
| [907. Sum of Subarray Minimums](https://leetcode.com/problems/sum-of-subarray-minimums/) | Uses monotonic stack reasoning to find subarray minimum contributions |

---

## 🚨 12. Common Mistakes

### 1. Storing Values Instead of Indices

Incorrect approach:

```java
Deque<Integer> deque = new ArrayDeque<>();
deque.offerLast(nums[i]);
```

Why it causes problems:

- We cannot directly identify when an element leaves the window.
- Duplicate values make position tracking difficult.

**Remember:** Store indices and access values using `nums[index]`.

### 2. Removing Elements from the Wrong End

- Expired indices must be removed from the **front**.
- Smaller or equal candidates must be removed from the **back**.

Mixing these operations breaks the deque logic.

### 3. Incorrect Expiration Condition

Correct:

```java
deque.peekFirst() <= i-k
```

The current window starts at `i-k+1`.

An index equal to `i-k` is already outside the window.

### 4. Forgetting to Remove Smaller Elements

If we only remove expired indices, the deque may contain unnecessary elements.

The algorithm would no longer maintain the required decreasing order.

### 5. Using the Wrong Comparison Operator

```java
nums[deque.peekLast()] <= nums[i]
```

Using `<=` removes equal values too.

This is valid because the newer equal value remains in the window longer and is at least as useful as the older one.

### 6. Storing Results Too Early

```java
if(i >= k-1)
```

This condition ensures that a complete window exists before recording its maximum.

Without it, the result could contain answers for incomplete windows.

### 7. Forgetting the Result Array Size

```java
nums.length-k+1
```

There are exactly `n-k+1` windows, not `n` windows.

---

## ⏱️ 13. Complexity Analysis

### Time Complexity: `O(n)`

Although the code contains nested `while` loops, the total work is linear.

Why?

- Every index is added to the deque exactly once.
- Every index is removed from the deque at most once.
- An index can be removed either from the front or the back, but cannot be removed twice.

Therefore, total deque operations are proportional to `n`.

```text
Time Complexity = O(n)
```

### Space Complexity: `O(k)`

The deque stores indices of elements that belong to the current window.

At most `k` indices can be present.

The result array requires `O(n-k+1)` space, but this is the required output.

```text
Auxiliary Space = O(k)
Output Space    = O(n-k+1)
```

---

## 🎯 14. Placement-Level Takeaway

### ⭐ If you see this in a placement round...

```text
Problem asks for maximum/minimum
             ↓
For every sliding window
             ↓
Brute force repeatedly scans k elements
             ↓
Need to maintain useful candidates
             ↓
Use a Monotonic Deque
             ↓
Store indices, not values
             ↓
Remove expired indices from front
             ↓
Remove dominated candidates from back
             ↓
Front gives the current maximum
```

### What should you explain in an interview?

1. Brute force takes `O(n*k)` because every window is scanned separately.
2. Consecutive windows overlap, so repeated work can be avoided.
3. A larger, newer element makes smaller elements before it useless as future maximum candidates.
4. A deque maintains these candidates in decreasing order.
5. Each index enters and leaves the deque at most once, giving `O(n)` time.

---

## ⚡ 15. Final 30-Second Cheat Sheet

```text
╔══════════════════════════════════════════╗
║           QUICK REVISION                 ║
╠══════════════════════════════════════════╣
║ Pattern: Sliding Window + Monotonic Deque║
║ Goal: Maximum in every window of size k  ║
║ Stores: Indices, not values              ║
║ Order: Decreasing values                  ║
║ Front: Index of maximum                   ║
║ Expired: Remove from front                ║
║ Smaller/equal: Remove from back           ║
║ Add: Current index at back                ║
║ Result: nums[deque.peekFirst()]           ║
║ Time: O(n)                                ║
║ Space: O(k) auxiliary                     ║
╚══════════════════════════════════════════╝
```

> **⭐ One sentence to remember:** A Monotonic Deque keeps only useful maximum candidates in decreasing order, so every sliding window's maximum is available at the front in `O(1)` time.

---

## 👨‍💻 Author

**Nijoy P Jose**

This solution is part of my **Data Structures & Algorithms** placement preparation repository, where I document problem-solving patterns, interview techniques, and Java implementations to strengthen my coding skills.
