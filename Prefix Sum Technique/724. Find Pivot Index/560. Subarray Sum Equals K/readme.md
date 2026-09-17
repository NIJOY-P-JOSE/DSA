# 560. Subarray Sum Equals K

> **Pattern:** Prefix Sum + HashMap · Subarray Counting
> **Difficulty:** Medium
> **Platform:** LeetCode
> **LeetCode:** https://leetcode.com/problems/subarray-sum-equals-k/

---

## ⚡ Quick Revision

| Concept        | Remember                             |
| -------------- | ------------------------------------ |
| Goal           | Count subarrays whose sum equals `k` |
| Pattern        | Prefix Sum + HashMap                 |
| Subarray       | Must be contiguous and non-empty     |
| Prefix Sum     | `sum = sum of elements seen so far`  |
| Key Equation   | `currentSum - previousSum = k`       |
| Rearranged     | `previousSum = currentSum - k`       |
| HashMap Stores | `prefixSum → frequency`              |
| Initialization | `map.put(0, 1)`                      |
| Time           | `O(n)`                               |
| Space          | `O(n)`                               |

---

# 🧠 Problem in Simple Words

Given an array and a target `k`, count how many **contiguous subarrays** have a sum exactly equal to `k`.

For example:

```text
nums = [1, 1, 1]
k = 2
```

The valid subarrays are:

```text
[1, 1]       → 2
[1, 1]       → 2
```

Therefore:

```text
answer = 2
```

---

# 🔥 How to Recognize This Problem in a Placement

This is an important recognition pattern.

Look for:

* **subarray**
* **contiguous**
* **sum equals `k`**
* **count the number of subarrays**
* Array may contain **negative numbers**

### Recognition Trigger

> **"Count contiguous subarrays with a target sum" → Think Prefix Sum + HashMap.**

Especially notice when **negative numbers are allowed**.

For example:

```text
nums = [1, -1, 1, 2]
```

A simple sliding-window approach is not generally reliable because adding an element can either increase **or decrease** the sum.

That is a strong clue to consider:

```text
Prefix Sum + HashMap
```

---

# 🧩 How to Think / Derive the Solution

This is the most important part of the problem.

Don't start by memorizing:

```java
map.get(sum - k)
```

Instead, derive it.

---

## Step 1: What is a subarray?

A subarray is a **contiguous** section.

For:

```text
[1, 2, 3]
```

some subarrays are:

```text
[1]
[2]
[3]
[1, 2]
[2, 3]
[1, 2, 3]
```

We need to count the ones whose sum is `k`.

---

## Step 2: Think About Prefix Sums

Suppose we have:

```text
nums = [1, 2, 3]
```

Prefix sums are:

```text
Index       Value       Prefix Sum
-----------------------------------
  0           1             1
  1           2             3
  2           3             6
```

Define:

```text
prefixSum[i] = sum of elements from the beginning through i
```

Now suppose we want a subarray whose sum is `k`.

---

## Step 3: The Important Equation

Suppose:

```text
current prefix sum = S
```

and some earlier prefix sum is:

```text
P
```

Then the elements between those two positions have sum:

```text
S - P
```

We want this to equal `k`.

Therefore:

```text
S - P = k
```

Rearrange:

```text
P = S - k
```

### ⭐ This is the core insight.

When we reach a current prefix sum `S`, we ask:

> **"Have I previously seen a prefix sum equal to `S - k`?"**

If yes, that previous prefix gives us a subarray with sum `k`.

---

# 📊 Visual Explanation

Suppose:

```text
nums = [1, 2, 3]
k = 3
```

After reading the first element:

```text
prefix = 1
```

After reading the second:

```text
prefix = 3
```

We want:

```text
previousPrefix = currentPrefix - k
```

So:

```text
previousPrefix = 3 - 3
               = 0
```

We have seen prefix sum `0` before the array started.

Therefore:

```text
[1, 2]
```

has sum `3`.

---

# 🧠 Why Do We Need a HashMap?

We need to quickly answer:

> "How many times have I previously seen prefix sum `S - k`?"

A `HashMap` lets us store:

```text
prefix sum → number of times it appeared
```

For example:

```text
{
    0 → 1,
    1 → 1,
    3 → 1
}
```

So we can find the required prefix sum in approximately `O(1)` average time.

---

# 🔥 Why Store Frequency Instead of Just `true/false`?

This is extremely important.

Consider:

```text
nums = [0, 0, 0]
k = 0
```

Prefix sums are:

```text
0
0
0
```

The same prefix sum occurs multiple times.

Each previous occurrence can create a different valid subarray.

Therefore we need:

```text
prefixSum → frequency
```

not:

```text
prefixSum → exists
```

---

# 🧩 Deriving `map.put(0, 1)`

This line is one of the most important details:

```java
map.put(0, 1);
```

Why?

Imagine:

```text
nums = [3]
k = 3
```

At the first element:

```text
currentSum = 3
```

We need:

```text
currentSum - k
= 3 - 3
= 0
```

The prefix sum `0` represents:

```text
"Nothing has been taken yet."
```

This allows the entire prefix:

```text
[3]
```

to be counted as a valid subarray.

So:

```text
map.put(0, 1)
```

means:

> There is one prefix sum of `0` before we start processing the array.

---

# 🔁 Algorithm

1. Create a `HashMap` to store prefix-sum frequencies.
2. Add:

   ```java
   map.put(0, 1);
   ```
3. Initialize:

   ```text
   sum = 0
   count = 0
   ```
4. Traverse every element.
5. Add the current element to `sum`.
6. Calculate:

   ```text
   sum - k
   ```
7. If that prefix sum exists in the map:

   * Add its frequency to `count`.
8. Store the current prefix sum in the map.
9. Return `count`.

---

# 💻 My Solution

```java
class Solution {

    public int subarraySum(int[] nums, int k) {

        HashMap<Integer, Integer> map = new HashMap<>();

        map.put(0, 1);

        int sum = 0;
        int count = 0;

        for (int num : nums) {
            sum += num;

            if (map.containsKey(sum - k)) {
                count += map.get(sum - k);
            }

            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }

        return count;
    }
}
```

Your solution is **correct** and uses the standard `O(n)` Prefix Sum + HashMap approach.

---

# 🔍 Code → What It Does → Why It Works

## 1. Create the HashMap

```java
HashMap<Integer, Integer> map = new HashMap<>();
```

The map stores:

```text
prefixSum → frequency
```

For example:

```text
5 → 2
```

means prefix sum `5` has appeared twice.

---

## 2. Initialize Prefix Sum `0`

```java
map.put(0, 1);
```

This represents the empty prefix before the array begins.

It allows subarrays starting at index `0` to be counted.

---

## 3. Initialize Variables

```java
int sum = 0;
int count = 0;
```

`sum`:

```text
Current prefix sum
```

`count`:

```text
Number of valid subarrays found so far
```

---

## 4. Traverse the Array

```java
for (int num : nums)
```

Each element is processed exactly once.

---

## 5. Update Prefix Sum

```java
sum += num;
```

Now `sum` represents the total from the beginning of the array up to the current element.

---

## 6. Find the Required Previous Prefix

```java
if (map.containsKey(sum - k))
```

This comes directly from:

```text
currentSum - previousSum = k
```

Therefore:

```text
previousSum = currentSum - k
```

So we search for:

```text
sum - k
```

---

## 7. Add Its Frequency

```java
count += map.get(sum - k);
```

If the required prefix sum appeared multiple times, each occurrence represents a different valid subarray.

Therefore we add the **frequency**, not just `1`.

---

## 8. Store Current Prefix Sum

```java
map.put(sum, map.getOrDefault(sum, 0) + 1);
```

We record that the current prefix sum has appeared once more.

---

# 📊 Complete Dry Run

Consider:

```text
nums = [1, 1, 1]
k = 2
```

Initial state:

```text
map = {0=1}
sum = 0
count = 0
```

### Iteration 1

```text
num = 1
sum = 1
```

Required prefix:

```text
sum - k
= 1 - 2
= -1
```

`-1` is not in the map.

So:

```text
count = 0
```

Store:

```text
map = {0=1, 1=1}
```

---

### Iteration 2

```text
num = 1
sum = 2
```

Required prefix:

```text
2 - 2 = 0
```

Map contains:

```text
0 → 1
```

Therefore:

```text
count += 1
count = 1
```

Store `2`:

```text
map = {0=1, 1=1, 2=1}
```

The valid subarray is:

```text
[1, 1]
```

---

### Iteration 3

```text
num = 1
sum = 3
```

Required prefix:

```text
3 - 2 = 1
```

Map contains:

```text
1 → 1
```

Therefore:

```text
count += 1
count = 2
```

The second valid subarray is:

```text
[1, 1]
```

Final:

```text
count = 2
```

---

# 🧠 The Core Mental Model

Don't memorize:

```java
map.containsKey(sum - k)
```

Memorize the equation:

```text
Current Prefix
      -
Previous Prefix
      =
Subarray Sum
```

We want:

```text
Current Prefix - Previous Prefix = k
```

Therefore:

```text
Previous Prefix = Current Prefix - k
```

So:

```text
Current sum
      ↓
Look for sum - k
      ↓
Found?
      ↓
Add its frequency
```

---

# 🧠 Pattern Connection

This problem extends the basic **Prefix Sum** pattern.

```text
Running Sum
     ↓
Prefix Sum
     ↓
Need subarray sum?
     ↓
Current Prefix - Previous Prefix
     ↓
Need target k
     ↓
Previous Prefix = Current Prefix - k
     ↓
HashMap for fast lookup
```

So the full pattern is:

> **Prefix Sum + HashMap for Counting Subarrays**

---

# ⚔️ Similar Problems / Variations

| Problem                               | Difference                                         |
| ------------------------------------- | -------------------------------------------------- |
| **1480. Running Sum of 1d Array**     | Basic prefix sum without counting subarrays        |
| **303. Range Sum Query - Immutable**  | Uses prefix sums for range queries                 |
| **974. Subarray Sums Divisible by K** | Stores prefix-sum remainders instead of exact sums |
| **525. Contiguous Array**             | Uses prefix-sum transformation + HashMap           |
| **523. Continuous Subarray Sum**      | Uses prefix sums and remainder tracking            |

### Pattern progression

```text
1480
Running Sum
   ↓
303
Prefix Sum
   ↓
560
Prefix Sum + HashMap
   ↓
974 / 523 / 525
Prefix Sum + HashMap variations
```

---

# 🚨 Common Mistakes

## 1. Forgetting `map.put(0, 1)`

This causes subarrays starting from index `0` to be missed.

Always initialize:

```java
map.put(0, 1);
```

---

## 2. Storing Only Whether a Sum Exists

Wrong idea:

```text
HashSet<prefixSum>
```

A prefix sum can occur multiple times.

We need:

```text
prefixSum → frequency
```

---

## 3. Using `sum + k` Instead of `sum - k`

The equation is:

```text
currentSum - previousSum = k
```

Therefore:

```text
previousSum = currentSum - k
```

Not:

```text
currentSum + k
```

---

## 4. Using Sliding Window Automatically

A common mistake is:

> "It's a subarray sum problem, so I'll use sliding window."

That doesn't work generally when negative numbers are allowed.

Example:

```text
[1, -1, 2]
```

The sum can increase or decrease when the window expands.

Prefix Sum + HashMap handles positive, zero, and negative values.

---

## 5. Counting Only One Match

Suppose:

```text
map.get(sum - k) = 3
```

That means there are **three previous prefix sums** that can form valid subarrays with the current position.

So:

```java
count += map.get(sum - k);
```

not simply:

```java
count++;
```

---

# ⏱️ Complexity

### Time

```text
O(n)
```

We traverse the array once.

HashMap lookup and insertion are `O(1)` on average.

Therefore:

```text
O(n)
```

---

### Space

```text
O(n)
```

In the worst case, we may store `O(n)` different prefix sums.

---

# 🎯 Placement-Level Takeaway

### ⭐ If you see this in a placement round...

Look for:

```text
"subarray"
+
"contiguous"
+
"sum equals k"
+
"count number of subarrays"
```

Then think:

```text
             Subarray Sum
                   ↓
             Prefix Sum
                   ↓
     Current - Previous = k
                   ↓
       Previous = Current - k
                   ↓
       Need fast lookup/count
                   ↓
              HashMap
```

### Your Decision Process

```text
1. Is it a contiguous subarray?
              ↓
             Yes
              ↓
2. Is there a target sum?
              ↓
             Yes
              ↓
3. Do negative numbers exist?
              ↓
             Yes
              ↓
4. Think Prefix Sum + HashMap
              ↓
5. Store prefixSum → frequency
              ↓
6. Look for currentSum - k
```

---

# ⚡ Final 30-Second Cheat Sheet

```text
╔══════════════════════════════════════════════════╗
║                 QUICK REVISION                   ║
╠══════════════════════════════════════════════════╣
║ Pattern: Prefix Sum + HashMap                   ║
║ Goal: Count subarrays with sum = k              ║
║ Key Equation: Current - Previous = k            ║
║ Required: Previous = Current - k                ║
║ Map: prefixSum → frequency                      ║
║ Initialize: map.put(0, 1)                       ║
║ For each num:                                    ║
║   sum += num                                     ║
║   count += map.get(sum - k)                     ║
║   map.put(sum, frequency + 1)                   ║
║ Time: O(n)                                       ║
║ Space: O(n)                                      ║
╚══════════════════════════════════════════════════╝
```

> **⭐ One sentence to remember:** For every current prefix sum, look for how many previous prefix sums equal `currentSum - k`, because their difference forms a subarray with sum `k`.
