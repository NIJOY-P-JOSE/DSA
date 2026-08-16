# 740. Delete and Earn

**Pattern:** Dynamic Programming — 1D DP / House Robber Pattern
**Difficulty:** Medium
**Platform:** LeetCode
**Problem:** [740. Delete and Earn](https://leetcode.com/problems/delete-and-earn/)

---

## 📌 Problem Statement

You are given an integer array `nums`.

You can repeatedly choose an element `nums[i]` and delete it to earn `nums[i]` points.

After choosing a value `x`:

* Every element equal to `x - 1` is deleted.
* Every element equal to `x + 1` is deleted.
* Other elements remain available.

Return the **maximum number of points** that can be earned.

### Example 1

```text
Input:
[3,4,2]

Output:
6
```

Explanation:

```text
Take 4 → earn 4
3 is deleted

Take 2 → earn 2

Total = 6
```

### Example 2

```text
Input:
[2,2,3,3,3,4]

Output:
9
```

Taking all three `3`s gives:

```text
3 + 3 + 3 = 9
```

---

# 💡 Key Observation

At first, this doesn't look like House Robber.

But consider choosing a number `x`.

If we choose `x`, we cannot choose:

```text
x - 1
x + 1
```

This is exactly the same restriction as House Robber:

> If we choose house `i`, we cannot choose adjacent houses.

So we can **transform Delete and Earn into House Robber**.

---

# 1. Group Equal Numbers

Instead of processing every element separately, count how many times each number appears.

For:

```text
nums = [2,2,3,3,3,4]
```

The frequencies are:

```text
2 → 2
3 → 3
4 → 1
```

If we choose the number `3`, we can earn from **all occurrences of 3**.

Therefore:

```text
points[x] = x × frequency[x]
```

For this example:

```text
2 → 2 × 2 = 4
3 → 3 × 3 = 9
4 → 4 × 1 = 4
```

So the problem becomes:

```text
Number:   2    3    4
Points:   4    9    4
```

Now it looks exactly like:

```text
House Robber
```

---

# 🧠 DP State

Define:

```text
dp[i]
```

as:

> The maximum number of points we can earn using numbers from `1` through `i`.

---

# 🔄 DP Transition

For number `i`, we have two choices.

### Choice 1 — Don't take `i`

Then the answer remains:

```text
dp[i-1]
```

### Choice 2 — Take `i`

If we take `i`, we cannot take `i-1`.

Therefore:

```text
dp[i-2] + points[i]
```

where:

```text
points[i] = i × frequency[i]
```

So:

```text
dp[i] = max(
    dp[i-1],
    dp[i-2] + i × frequency[i]
)
```

This is exactly the **House Robber recurrence**.

---

# 🔍 Example

Consider:

```text
nums = [2,2,3,3,3,4]
```

Frequency:

```text
frequency[2] = 2
frequency[3] = 3
frequency[4] = 1
```

Points:

```text
points[2] = 2 × 2 = 4
points[3] = 3 × 3 = 9
points[4] = 4 × 1 = 4
```

Now calculate DP:

```text
dp[2] = 4
```

For `3`:

```text
dp[3] = max(dp[2], dp[1] + 9)
      = max(4, 9)
      = 9
```

For `4`:

```text
dp[4] = max(dp[3], dp[2] + 4)
      = max(9, 8)
      = 9
```

Therefore:

```text
Answer = 9
```

---

# 💻 Bottom-Up DP Solution

```python
class Solution:
    def deleteAndEarn(self, nums: List[int]) -> int:
        if len(nums) <= 1:
            return nums[0]

        frequency = [0] * (max(nums) + 1)

        for x in nums:
            frequency[x] += 1

        dp = [0] * len(frequency)

        dp[1] = frequency[1]

        for i in range(2, len(frequency)):
            dp[i] = max(
                dp[i - 1],
                dp[i - 2] + frequency[i] * i
            )

        return dp[-1]
```

---

# 🚀 Space-Optimized Version

Just like House Robber, we only need the previous two DP values:

```text
dp[i-1]
dp[i-2]
```

Therefore, we can reduce the DP space to `O(1)`.

```python
class Solution:
    def deleteAndEarn(self, nums: List[int]) -> int:
        if len(nums) <= 1:
            return nums[0]

        frequency = [0] * (max(nums) + 1)

        for x in nums:
            frequency[x] += 1

        prev2 = 0
        prev1 = 0

        for i in range(1, len(frequency)):
            current = max(
                prev1,
                prev2 + frequency[i] * i
            )

            prev2 = prev1
            prev1 = current

        return prev1
```

### Complexity

Let:

```text
M = max(nums)
```

Then:

```text
Time:  O(n + M)
Space: O(M)
```

The `O(M)` space is required for the frequency array.

---

# 🔄 Connection to House Robber

This is the most important concept from this problem.

### House Robber

```text
nums = [2,7,9,3,1]
```

Transition:

```text
dp[i] = max(
    dp[i-1],
    dp[i-2] + nums[i]
)
```

### Delete and Earn

First transform:

```text
frequency[i] = number of occurrences of i
```

Then:

```text
points[i] = i × frequency[i]
```

Now use the same recurrence:

```text
dp[i] = max(
    dp[i-1],
    dp[i-2] + points[i]
)
```

So:

```text
Delete and Earn
       ↓
Group equal values
       ↓
Calculate total points for each value
       ↓
Adjacent values conflict
       ↓
House Robber
       ↓
Take / Skip DP
```

---

# 🎯 Important Placement Insight

The biggest lesson is **not the code**.

The important skill is recognizing:

> A problem may look completely different from a known DP problem, but after transforming the input, it can become a familiar pattern.

For Delete and Earn:

```text
Original problem
      ↓
Choose x
      ↓
Lose x-1 and x+1
      ↓
Adjacent values cannot both be chosen
      ↓
Looks like House Robber
```

This is a very useful **problem transformation + DP recognition** pattern.

---

# ⚠️ Common Mistakes

### 1. Treating each occurrence independently

For:

```text
[3,3,3]
```

choosing `3` can earn:

```text
3 + 3 + 3 = 9
```

So we must group equal values.

---

### 2. Forgetting the frequency

The value associated with `i` is:

```text
i × frequency[i]
```

not simply:

```text
i
```

---

### 3. Thinking only about the original array order

The restriction is based on **value**:

```text
x-1 and x+1
```

not on the position of elements in the original array.

That's why we create the frequency array.

---

# 🧠 Pattern Recognition

When you see a problem where:

* You choose a value.
* Choosing it prevents choosing neighboring values.
* Multiple occurrences of the same value can contribute together.

Think:

```text
Frequency Counting
        +
House Robber / Take-Skip DP
```

---

# 📊 Complexity

For the provided solution:

```text
Frequency construction: O(n)

DP: O(max(nums))
```

Therefore:

```text
Time:  O(n + max(nums))
Space: O(max(nums))
```

---

# 📚 Related Problems

This problem is especially useful after:

1. **70. Climbing Stairs** — Basic 1D DP
2. **746. Min Cost Climbing Stairs** — 1D Min DP
3. **198. House Robber** — Take/Skip DP
4. **213. House Robber II** — Case splitting + Take/Skip DP
5. **740. Delete and Earn** — Transform into House Robber

The progression is:

```text
Climbing Stairs
      ↓
1D DP
      ↓
Min Cost Climbing Stairs
      ↓
Take / Min DP
      ↓
House Robber
      ↓
Take / Skip DP
      ↓
House Robber II
      ↓
Case Splitting
      ↓
Delete and Earn
      ↓
Problem Transformation
```

---

# 📚 Concepts Practiced

* [x] Dynamic Programming
* [x] 1D DP
* [x] Bottom-Up DP
* [x] Space Optimization
* [x] Frequency Counting
* [x] Problem Transformation
* [x] Take / Skip Pattern
* [x] State Definition
* [x] State Transition
* [x] Complexity Analysis

---

# 👨‍💻 Author

**Nijoy P Jose**

Part of my **Data Structures & Algorithms placement preparation repository**.
