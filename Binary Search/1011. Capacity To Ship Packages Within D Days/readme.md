# 1011. Capacity To Ship Packages Within D Days

**Difficulty:** Medium  
**Pattern:** Binary Search on Answer  
**Time:** `O(n log(sum(weights)))`  
**Space:** `O(1)`

---

## 1. Quick Revision

### Core Idea

We need the **minimum ship capacity** that can transport all packages within `days` days.

The possible capacities form a monotonic search space:

```text
Capacity:   low  low+1  ...  answer  ...  high
Possible:     ❌    ❌    ...    ✅     ...   ✅
```

If a capacity works, every larger capacity will also work.

So we use **Binary Search on Answer**.

### 30-Second Recall

> **Minimum capacity + can check whether a capacity works → Binary Search on Answer.**

The key difference from Koko is the feasibility check:

> For a given capacity, greedily load packages in their original order and count how many days are required.

---

## 2. Problem in Simple Words

Packages are given in a fixed order:

```text
weights = [1,2,3,4,5,6,7,8,9,10]
```

The ship has a fixed capacity.

Every day, we load consecutive packages until adding the next package would exceed the capacity. Then we start a new day.

We need the **smallest capacity** that ships everything within the given number of days.

For example:

```text
weights = [1,2,3,4,5,6,7,8,9,10]
days = 5
```

Answer:

```text
15
```

One valid arrangement is:

```text
Day 1 → 1 + 2 + 3 + 4 + 5 = 15
Day 2 → 6 + 7 = 13
Day 3 → 8
Day 4 → 9
Day 5 → 10
```

---

## 3. How to Recognize This Problem in a Placement

Look for these clues:

- We need the **minimum capacity**.
- We can check whether a particular capacity is sufficient.
- Increasing the capacity can never increase the number of days required.
- Packages must remain in their given order.
- The expected complexity suggests Binary Search.

The important question is:

> **Can I ship all packages within `days` using capacity `X`?**

If yes, try a smaller capacity.

If no, increase the capacity.

This gives:

```text
❌ ❌ ❌ ❌ ✅ ✅ ✅
             ↑
        minimum valid
```

That is a **Binary Search on Answer** pattern.

---

## 4. How to Think / Derive the Solution

### Step 1: What are we searching for?

We are searching for:

```text
minimum ship capacity
```

The capacity is not an index in the array.

Therefore, normal Binary Search on `weights` does not apply.

We binary-search the possible capacity values.

---

### Step 2: Find the minimum possible capacity

The ship must be able to carry the **heaviest individual package**.

For:

```text
weights = [1,2,3,4,5,6,7,8,9,10]
```

the heaviest package weighs:

```text
10
```

Therefore:

```text
minimum capacity = max(weights)
```

A capacity smaller than this is impossible because that package could never be shipped.

---

### Step 3: Find the maximum possible capacity

The maximum capacity we ever need is the total weight of all packages.

For:

```text
[1,2,3,4,5]
```

we could ship everything in one day if:

```text
capacity = 15
```

So:

```text
maximum capacity = sum(weights)
```

Therefore our search range is:

```text
l = max(weights)
r = sum(weights)
```

---

## 5. The Feasibility Check

For every candidate capacity, we need to calculate:

> How many days are required?

Suppose:

```text
weights = [1,2,3,4,5]
capacity = 5
```

We process packages from left to right.

```text
Day 1:
1 + 2 = 3
3 + 3 = 6 ❌

Day 2:
3 + 4 = 7 ❌
...
```

More clearly, the actual grouping is:

```text
Day 1 → 1 + 2 = 3
Day 2 → 3
Day 3 → 4 + 1 ...
```

The important rule is:

> Keep adding the next package while it fits. If it doesn't fit, start a new day with that package.

Because package order cannot change, this greedy simulation gives the required number of days for that capacity.

---

## 6. Visual Explanation

For:

```text
weights = [3,2,2,4,1,4]
capacity = 6
```

Process from left to right:

```text
3 + 2 = 5
+ 2 = 7 ❌
```

Start a new day:

```text
Day 1 → [3,2]
Day 2 → [2,4]
Day 3 → [1,4]
```

So:

```text
required days = 3
```

If:

```text
days = 3
```

then capacity `6` is possible.

---

## 7. Algorithm

1. Find the maximum package weight.
2. Find the total weight.
3. Set:
   ```text
   l = max(weights)
   r = sum(weights)
   ```
4. Calculate:
   ```text
   mid = l + (r-l)/2
   ```
5. Check how many days are required using capacity `mid`.
6. If required days `<= days`:
   - Capacity works.
   - Search for a smaller capacity.
   - `r = mid - 1`
7. Otherwise:
   - Capacity is too small.
   - Search for a larger capacity.
   - `l = mid + 1`
8. Return `l`.

---

## 8. My Java Solution

```java
class Solution {
    public int shipWithinDays(int[] weights, int days) {
        int l = 0, r = 0;

        // The ship must at least carry the heaviest package,
        // while the total weight is enough to ship everything in one day.
        for (int n : weights) {
            l = Math.max(l, n);
            r += n;
        }

        while (l <= r) {
            int mid = l + (r - l) / 2;

            // Check whether this capacity can ship all packages
            // within the required number of days.
            if (isPossible(mid, weights, days))
                r = mid - 1;   // Possible, so try a smaller capacity.
            else
                l = mid + 1;   // Not possible, so increase capacity.
        }

        // l becomes the smallest capacity that satisfies the condition.
        return l;
    }

    boolean isPossible(int cap, int[] weights, int days) {
        int d = 1, curw = 0;

        // Process packages in their original order. Keep adding packages
        // to the current day until the next package would exceed capacity.
        for (int w : weights) {
            if (curw + w <= cap)
                curw += w;
            else {
                // Start a new day with the current package.
                curw = w;
                d++;
            }
        }

        return d <= days;
    }
}
```

---

## 9. Code Explanation

### Finding the Search Range

```java
int l = 0, r = 0;

for (int n : weights) {
    l = Math.max(l, n);
    r += n;
}
```

At the end:

```text
l = maximum individual package
r = total weight
```

Why?

```text
capacity < max(weights) → impossible
capacity = sum(weights) → everything can fit in one day
```

So the answer must lie between these two values.

---

### Why `curw`?

`curw` stores the total weight loaded on the **current day**.

For example:

```text
capacity = 10

weights:
2 → curw = 2
3 → curw = 5
4 → curw = 9
5 → 14 ❌
```

Since adding `5` exceeds the capacity, we start another day:

```text
curw = 5
```

---

### Why Does `d` Start at 1?

Before processing any package, we already have the first day available.

```java
int d = 1;
```

Whenever a package cannot fit in the current day, we start a new day:

```java
d++;
```

At the end:

```text
d = number of days actually required
```

Then:

```java
return d <= days;
```

determines whether the candidate capacity works.

---

## 10. Example Walkthrough

### Input

```text
weights = [1,2,3,4,5,6,7,8,9,10]
days = 5
```

Search range:

```text
l = 10
r = 55
```

Suppose Binary Search tests:

```text
capacity = 32
```

This easily fits the packages within 5 days.

Therefore:

```text
32 is possible
```

Search smaller:

```text
r = 31
```

Binary Search continues testing smaller capacities.

Eventually it reaches:

```text
capacity = 15
```

Simulation:

```text
Day 1 → 1 + 2 + 3 + 4 + 5 = 15
Day 2 → 6 + 7 = 13
Day 3 → 8
Day 4 → 9
Day 5 → 10
```

Required days:

```text
5
```

Since:

```text
5 <= 5
```

capacity `15` works.

Trying `14` requires more than 5 days, so `15` is the minimum valid capacity.

---

## 11. Why the Greedy Check Works

For a fixed capacity, packages **must be shipped in their original order**.

Therefore, whenever the next package cannot fit:

```text
current weight + next package > capacity
```

there is no choice to rearrange packages or skip the package for a later day.

We must start the next day with that package.

So the simulation naturally forms the maximum possible consecutive group for each day.

For a fixed capacity, this gives the minimum number of days required.

That makes the feasibility check reliable for Binary Search.

---

## 12. Why Binary Search Works

Suppose a capacity `C` can ship all packages within the required days.

If we increase it:

```text
C + 1
C + 2
C + 3
...
```

we can never require **more** days.

Therefore the feasibility condition is monotonic:

```text
Capacity:
10  11  12  13  14  15  16  17 ...
❌  ❌  ❌  ❌  ❌  ✅  ✅  ✅ ...
                         ↑
                  first valid capacity
```

Binary Search efficiently finds this boundary.

---

## 13. Pattern Connection — Koko vs Shipping

This problem is very similar to **875. Koko Eating Bananas**.

### Koko

```text
Search:
eating speed

Check:
Can Koko finish within h hours?
```

### Shipping

```text
Search:
ship capacity

Check:
Can packages be shipped within days?
```

The overall pattern is the same:

```text
Possible Answers
       ↓
Binary Search
       ↓
Feasibility Function
       ↓
True / False
       ↓
Find Minimum Valid Answer
```

The important difference is the feasibility function.

```text
Koko:
hours = Σ ceil(pile / speed)

Shipping:
days = simulate consecutive package groups
```

---

## 14. Similar Problems / Variations

### LeetCode 875 — Koko Eating Bananas

```text
Minimum eating speed
→ Can finish within h hours?
```

### LeetCode 1482 — Minimum Number of Days to Make m Bouquets

```text
Minimum days
→ Can make enough bouquets?
```

### LeetCode 774 — Minimize Max Distance to Gas Station

```text
Minimize maximum distance
→ Can achieve a given maximum distance?
```

### LeetCode 410 — Split Array Largest Sum

```text
Minimize maximum subarray sum
→ Can split into required number of groups?
```

The common pattern is:

> **Search over the answer, not directly over the input array.**

---

## 15. Common Mistakes

### Mistake 1: Starting `l` at 1

For Koko, `1` is a valid lower bound.

For shipping, it is not.

If the heaviest package weighs `10`, capacity `5` is impossible.

Therefore:

```java
l = max(weights);
```

---

### Mistake 2: Using the number of days as the search range

The answer is a **weight capacity**, not a number of days.

The correct range is:

```text
max(weights) → sum(weights)
```

---

### Mistake 3: Reordering packages

This is not allowed.

For:

```text
[1,2,3,4,5]
```

you cannot create arbitrary groups such as:

```text
[1,4]
[2,5]
[3]
```

The order must remain:

```text
1 → 2 → 3 → 4 → 5
```

---

### Mistake 4: Forgetting to start a new day

When:

```java
curw + w > cap
```

the current package cannot fit.

You must:

```java
curw = w;
d++;
```

The current package belongs to the new day.

---

### Mistake 5: Returning `mid`

The last tested `mid` is not necessarily the answer.

We are looking for the **first valid capacity**.

So return:

```java
return l;
```

---

## 16. Complexity

Let:

```text
n = weights.length
S = sum(weights)
```

Each feasibility check processes all packages:

```text
O(n)
```

The Binary Search range is from:

```text
max(weights) → S
```

So the number of Binary Search iterations is:

```text
O(log S)
```

Therefore:

```text
Time = O(n log S)
Space = O(1)
```

---

## 17. Placement-Level Takeaway

This problem reinforces an important Binary Search pattern:

> **If you need to find the minimum possible value and can efficiently check whether a candidate value is feasible, Binary Search on Answer should come to mind.**

The thinking process is:

```text
What am I minimizing?
        ↓
Ship capacity
        ↓
What is the smallest possible value?
        ↓
max(weights)
        ↓
What is the largest possible value?
        ↓
sum(weights)
        ↓
Can I check a candidate?
        ↓
Simulate shipping and count days
        ↓
Is days <= required days?
        ↓
Binary Search
```

This is exactly the kind of reasoning interviewers look for rather than simply memorizing the code.

---

## 18. Final 30-Second Cheat Sheet

```text
Problem:
Find minimum ship capacity.

Pattern:
Binary Search on Answer.

Search Space:
max(weights) → sum(weights)

Feasibility:
Simulate packages in order.

For each package:
    if currentWeight + package <= capacity
        add to current day
    else
        start new day

Condition:
daysRequired <= days → possible

If possible:
r = mid - 1

If impossible:
l = mid + 1

Answer:
return l

Complexity:
Time  → O(n log(sum(weights)))
Space → O(1)
```

### One Line to Remember

> **Minimum capacity + packages must stay in order + can count required days for a candidate → Binary Search on Answer.**
