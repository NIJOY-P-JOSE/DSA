# 1431. Kids With the Greatest Number of Candies

> **Pattern:** Array Traversal · Maximum Element · Comparison
> **Difficulty:** Easy
> **Platform:** LeetCode
> **LeetCode:** https://leetcode.com/problems/kids-with-the-greatest-number-of-candies/

---

## ⚡ Quick Revision

| Concept         | Remember                                                     |
| --------------- | ------------------------------------------------------------ |
| Goal            | Determine which kids can have the greatest number of candies |
| Pattern         | Find Maximum + Array Traversal                               |
| Key Observation | We only need the current maximum                             |
| Step 1          | Find the maximum candies                                     |
| Step 2          | Check `candies[i] + extraCandies >= max`                     |
| Result          | `true` if the kid can reach/exceed the maximum               |
| Time            | `O(n)`                                                       |
| Space           | `O(n)` for the result                                        |

---

## 🧠 Problem in Simple Words

Each kid has some candies, and we have `extraCandies`.

For every kid, give **all** the extra candies to that kid and ask:

> "Will this kid have at least as many candies as the kid who currently has the most?"

If yes → `true`.

Otherwise → `false`.

### Example

```text
candies = [2, 3, 5, 1, 3]
extraCandies = 3
```

First find the maximum:

```text
max = 5
```

Now check each kid:

```text
2 + 3 = 5  → true
3 + 3 = 6  → true
5 + 3 = 8  → true
1 + 3 = 4  → false
3 + 3 = 6  → true
```

Result:

```text
[true, true, true, false, true]
```

---

# 🔥 How to Recognize This Problem in a Placement

The important clues are:

* We need to compare every element against the **greatest/largest** value.
* The same `extraCandies` is added to each candidate.
* We need an answer for **every element**.
* We are not actually modifying the original array.

### Recognition Trigger

> **"For each element, determine whether it can reach or exceed the maximum" → Find the maximum first, then make one comparison pass.**

The key question to ask is:

```text
What value does every kid need to reach?
             ↓
The current maximum number of candies.
```

Once we know that maximum, the problem becomes simple.

---

# 🧩 How to Think / Derive the Solution

## Step 1: What is the actual condition?

For kid `i`, after receiving all extra candies:

```text
candies[i] + extraCandies
```

They are considered a greatest-candy kid if this value is at least the current maximum.

So:

```text
candies[i] + extraCandies >= maximum
```

---

## Step 2: Do we need to compare with every other kid?

At first, it might seem like we need to do:

```text
Kid i
 ↓
Compare with kid 1
Compare with kid 2
Compare with kid 3
...
```

That would be unnecessary.

We only need one piece of information:

```text
The maximum number of candies any kid currently has.
```

If a kid can reach that value, they can be one of the kids with the greatest number of candies.

---

## Step 3: Find the maximum

Traverse the array once:

```text
candies = [2, 3, 5, 1, 3]

max = 2
      ↓
max = 3
      ↓
max = 5
```

Now:

```text
max = 5
```

---

## Step 4: Check Every Kid

For each kid:

```text
candies[i] + extraCandies >= max
```

If true:

```text
result[i] = true
```

Otherwise:

```text
result[i] = false
```

---

## Step 5: Why `>=` and not `>`?

This is an important detail.

The problem says **multiple kids can have the greatest number of candies**.

Therefore, reaching exactly the current maximum is enough.

For example:

```text
candies[i] = 2
extraCandies = 3
max = 5
```

Then:

```text
2 + 3 = 5
```

This kid is also a greatest-candy kid.

Therefore:

```text
>=
```

is required.

---

# 📊 Visual Explanation

```text
candies = [2, 3, 5, 1, 3]
extra   = 3

             Find maximum
                  ↓
                max = 5
                  ↓
        ┌─────────┴─────────┐
        ↓                   ↓
   Add extras            Compare
        ↓                   ↓
candies[i] + 3       >= max?
        ↓                   ↓
                    true / false
```

### Complete check

```text
Kid       Candies    + Extra    Result
────────────────────────────────────────
1            2          5        true
2            3          6        true
3            5          8        true
4            1          4        false
5            3          6        true
```

---

# 🔁 Algorithm

1. Initialize `maxCandie` with the first element.
2. Traverse the array and find the maximum number of candies.
3. Traverse the array again.
4. For each kid:

   * Calculate `candies[i] + extraCandies`.
   * If it is greater than or equal to `maxCandie`, add `true`.
   * Otherwise, add `false`.
5. Return the result.

---

# 💻 My Solution

```java
class Solution {
    public List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {

        ArrayList<Boolean> result = new ArrayList<>();

        int maxCandie = candies[0];

        for (int i = 1; i < candies.length; i++) {
            if (maxCandie < candies[i])
                maxCandie = candies[i];
        }

        for (int i = 0; i < candies.length; i++) {
            if (maxCandie <= candies[i] + extraCandies)
                result.add(true);
            else
                result.add(false);
        }

        return result;
    }
}
```

## Code → What It Does → Why It Works

### 1. Create the Result

```java
ArrayList<Boolean> result = new ArrayList<>();
```

We need one Boolean answer for every kid.

---

### 2. Find the Maximum

```java
int maxCandie = candies[0];
```

Initially, assume the first kid has the maximum.

Then:

```java
for (int i = 1; i < candies.length; i++) {
    if (maxCandie < candies[i])
        maxCandie = candies[i];
}
```

updates the maximum whenever a larger value is found.

After this loop:

```text
maxCandie = maximum value in candies
```

---

### 3. Check Every Kid

```java
if (maxCandie <= candies[i] + extraCandies)
```

This is equivalent to:

```text
candies[i] + extraCandies >= maxCandie
```

If the condition is true, the kid can have the greatest number of candies.

---

### 4. Store the Result

```java
result.add(true);
```

or:

```java
result.add(false);
```

One result is added for every kid.

---

# 🧠 Why Does This Work?

Suppose:

```text
max = maximum candies currently held by any kid
```

For a particular kid `i`:

```text
newCandies = candies[i] + extraCandies
```

There are only two possibilities:

### Case 1

```text
newCandies >= max
```

The kid can reach or exceed the current maximum.

Therefore:

```text
true
```

### Case 2

```text
newCandies < max
```

Even after receiving all extra candies, the kid cannot reach the current maximum.

Therefore:

```text
false
```

So one maximum value is enough to make the decision for every kid.

---

# 🧠 Pattern Connection

This problem demonstrates a very common array technique:

```text
Array
 ↓
Need to compare every element with the greatest value?
 ↓
Find maximum
 ↓
Use maximum as a reference
 ↓
Make a second traversal
```

This pattern appears frequently in placement questions.

### Mental Template

```text
Find global property
        ↓
Use that property
        ↓
Evaluate each element
```

Here:

```text
Global property = maximum value
```

---

# ⚔️ Similar Problems / Variations

| Problem                                      | Difference                                              |
| -------------------------------------------- | ------------------------------------------------------- |
| **1471. The k Strongest Values in an Array** | Uses the array's median and maximum-related comparisons |
| **414. Third Maximum Number**                | Requires tracking multiple maximum values               |
| **169. Majority Element**                    | Finds the element occurring most frequently             |
| **724. Find Pivot Index**                    | Finds a special position using aggregate information    |
| **121. Best Time to Buy and Sell Stock**     | Tracks a useful previous/global value while traversing  |

The most relevant connection for now is:

```text
Find Maximum
      ↓
Use Maximum as Reference
      ↓
Evaluate Every Element
```

---

# 🚨 Common Mistakes

### 1. Using `>` Instead of `>=`

Wrong:

```java
candies[i] + extraCandies > maxCandie
```

This fails when a kid reaches exactly the maximum.

Correct:

```java
candies[i] + extraCandies >= maxCandie
```

---

### 2. Finding the Maximum After Adding Extras

The comparison target should be the **original maximum**.

We are asking:

```text
Can this kid reach the greatest current number of candies?
```

We don't need to actually give the candies to everyone.

---

### 3. Comparing Every Kid Against Every Other Kid

A brute-force approach might compare each kid with every other kid.

That is unnecessary.

Instead:

```text
Find max → O(n)
Check everyone → O(n)
```

Total:

```text
O(n)
```

---

### 4. Forgetting That Multiple Kids Can Be Greatest

If:

```text
candies[i] + extraCandies == max
```

the answer is still:

```text
true
```

---

# ⏱️ Complexity

### Time Complexity

```text
O(n)
```

We traverse the array twice:

```text
First pass → find maximum → O(n)
Second pass → build result → O(n)

O(n) + O(n) = O(n)
```

---

### Space Complexity

```text
O(n)
```

The returned Boolean list contains `n` elements.

The algorithm itself uses only `O(1)` extra working space apart from the required result.

---

# 🎯 Placement-Level Takeaway

### ⭐ If you see this in a placement round...

Ask:

```text
"For each element, what am I comparing against?"
                    ↓
              A global value?
                    ↓
             Find that value first
                    ↓
       Use it during the second traversal
                    ↓
             Build the answer
```

For this problem:

```text
Need to know if each kid can become greatest
                 ↓
What does "greatest" depend on?
                 ↓
Current maximum
                 ↓
Find maximum
                 ↓
candies[i] + extra >= maximum
                 ↓
true / false
```

---

# ⚡ Final 30-Second Cheat Sheet

```text
╔══════════════════════════════════════════════════╗
║                 QUICK REVISION                   ║
╠══════════════════════════════════════════════════╣
║ Pattern: Find Maximum + Comparison              ║
║ Goal: Can each kid reach the greatest count?    ║
║ Step 1: Find maximum candies                    ║
║ Step 2: Check each candies[i] + extra           ║
║ Condition: candies[i] + extra >= max            ║
║ Important: Use >=, not >                        ║
║ Why? Multiple kids can be greatest              ║
║ Time: O(n)                                      ║
║ Extra Working Space: O(1)                       ║
║ Result Space: O(n)                              ║
╚══════════════════════════════════════════════════╝
```

> **⭐ One sentence to remember:** When every element must be checked against a global maximum, find the maximum once and use it as the reference for the remaining checks.
