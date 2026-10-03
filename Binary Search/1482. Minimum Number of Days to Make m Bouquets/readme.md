# 1482. Minimum Number of Days to Make m Bouquets

**Difficulty:** Medium  
**Pattern:** Binary Search on Answer  
**Time:** `O(n log(max(bloomDay)))`  
**Space:** `O(1)`

---

## 1. Quick Revision

### Core Idea

We need the **minimum number of days** after which we can make `m` bouquets.

Each bouquet requires:

```text
k adjacent flowers
```

For a candidate day `D`, a flower is available if:

```text
bloomDay[i] <= D
```

We can then check whether enough **consecutive bloomed flowers** exist to make `m` bouquets.

The feasibility is monotonic:

```text
Day:       1   2   3   4   5   ...   10
Possible:  ❌  ❌  ✅  ✅  ✅  ...    ✅
```

So we Binary Search for the **first possible day**.

### 30-Second Recall

> **Minimum day + can check whether `m` bouquets are possible by that day → Binary Search on Answer.**

---

## 2. Problem in Simple Words

We have flowers arranged in a fixed order.

Each flower blooms on a particular day.

To make one bouquet, we need:

```text
k adjacent bloomed flowers
```

We need:

```text
m bouquets
```

Find the earliest day when this is possible.

For example:

```text
bloomDay = [1,10,3,10,2]
m = 3
k = 1
```

By day `3`:

```text
[x, _, x, _, x]
```

There are 3 bloomed flowers, so we can make 3 bouquets.

Answer:

```text
3
```

---

## 3. How to Recognize This Problem in a Placement

Look for:

- The question asks for the **minimum number of days**.
- We can test whether a particular day is sufficient.
- Once it becomes possible, every later day is also possible.
- We need to find the earliest valid day.

This creates:

```text
False False False True True True
                  ↑
             first valid day
```

That is a strong signal for **Binary Search on Answer**.

---

## 4. How to Think / Derive the Solution

### Step 1: What are we searching for?

We are searching for:

```text
minimum number of days
```

The days are not array indices, so we don't Binary Search directly on `bloomDay`.

Instead, we Binary Search over possible day values.

---

### Step 2: Find the search range

The earliest possible answer is the minimum value in `bloomDay`.

The latest possible answer is the maximum value.

Therefore:

```text
l = minimum bloom day
r = maximum bloom day
```

For:

```text
bloomDay = [1,10,3,10,2]
```

we get:

```text
l = 1
r = 10
```

---

### Step 3: Check whether a day works

Suppose:

```text
days = 3
```

A flower is available when:

```text
bloomDay[i] <= 3
```

For:

```text
[1,10,3,10,2]
```

we get:

```text
[x, _, x, _, x]
```

Now scan from left to right and count consecutive available flowers.

Whenever we collect `k` consecutive flowers:

```text
one bouquet is formed
```

Then reset the consecutive count and continue searching.

---

## 5. Visual Explanation

Suppose:

```text
bloomDay = [7,7,7,7,12,7,7]
m = 2
k = 3
```

### After Day 7

```text
[x, x, x, x, _, x, x]
```

We can make:

```text
[x, x, x] → Bouquet 1
```

But the last two bloomed flowers are not enough for another bouquet.

So:

```text
bouquets = 1
```

---

### After Day 12

```text
[x, x, x, x, x, x, x]
```

Now we can form:

```text
[x, x, x] → Bouquet 1
[x, x, x] → Bouquet 2
```

Therefore:

```text
answer = 12
```

---

## 6. Algorithm

1. If there aren't enough flowers to make `m × k` flowers, return `-1`.
2. Find the minimum and maximum bloom days.
3. Binary Search between those two days.
4. For `mid`, check whether `m` bouquets can be formed.
5. While checking:
   - If a flower has bloomed, increase the consecutive count.
   - If `k` consecutive flowers are found, form one bouquet and reset the count.
   - If a flower hasn't bloomed, reset the consecutive count.
6. If enough bouquets can be formed:
   - Search for an earlier day.
7. Otherwise:
   - Search for a later day.
8. Return the first valid day.

---

## 7. My Java Solution

```java id="q7f4cx"
class Solution {
    public int minDays(int[] bloomDay, int m, int k) {
        if (bloomDay.length < m * k)
            return -1;

        int l = Integer.MAX_VALUE, r = 0;

        // The answer must be between the earliest and latest
        // flower blooming days.
        for (int n : bloomDay) {
            l = Math.min(l, n);
            r = Math.max(r, n);
        }

        while (l <= r) {
            int mid = l + (r - l) / 2;

            // Check whether enough bouquets can be formed
            // by the given candidate day.
            if (isPossible(mid, bloomDay, m, k))
                r = mid - 1;   // Possible, so try an earlier day.
            else
                l = mid + 1;   // Not possible, so wait longer.
        }

        // l becomes the first day on which all required bouquets are possible.
        return l;
    }

    boolean isPossible(int days, int[] arr, int b, int f) {
        int bqts = 0, flow = 0;

        // Count consecutive flowers that have bloomed by this day.
        // Every f consecutive bloomed flowers form one bouquet.
        for (int d : arr) {
            if (d <= days) {
                flow++;

                if (flow == f) {
                    bqts++;
                    flow = 0;
                }
            } else {
                // An unbloomed flower breaks the adjacent sequence.
                flow = 0;
            }
        }

        return bqts >= b;
    }
}
```

---

## 8. Code Explanation

### Early Impossibility Check

```java
if (bloomDay.length < m * k)
    return -1;
```

Each bouquet needs `k` flowers.

For `m` bouquets, we need:

```text
m × k flowers
```

If the garden contains fewer flowers than this, making the required bouquets is impossible regardless of how long we wait.

---

### Finding the Search Range

```java
int l = Integer.MAX_VALUE, r = 0;

for (int n : bloomDay) {
    l = Math.min(l, n);
    r = Math.max(r, n);
}
```

We don't need to search every possible integer from `1` to `1e9`.

The answer must be between:

```text
minimum bloom day
        ↓
maximum bloom day
```

---

### Checking Whether a Flower Is Available

```java
if (d <= days)
```

If a flower blooms on or before the candidate day, it is available.

For example:

```text
candidate day = 5

bloomDay = 3 → available
bloomDay = 5 → available
bloomDay = 7 → unavailable
```

---

### Counting Adjacent Flowers

```java
flow++;
```

`flow` represents the number of consecutive flowers that have bloomed.

Suppose:

```text
k = 3
```

and we encounter:

```text
x x x
```

Then:

```java
if (flow == f)
```

means we have enough adjacent flowers to create one bouquet.

---

### Why Reset `flow` After Making a Bouquet?

```java
bqts++;
flow = 0;
```

Once `k` flowers have been used for a bouquet, they cannot be reused.

So we reset the consecutive count and look for the next bouquet.

For:

```text
x x x x x x
```

with:

```text
k = 3
```

we form:

```text
[x x x] → Bouquet 1
[x x x] → Bouquet 2
```

---

### Why Reset When a Flower Hasn't Bloomed?

```java
else
    flow = 0;
```

The bouquets require **adjacent** flowers.

For:

```text
x x _ x x x
```

the first two `x` values cannot be combined with the flowers after `_`.

The unbloomed flower breaks the consecutive sequence.

---

## 9. Example Walkthrough

### Input

```text
bloomDay = [1,10,3,10,2]
m = 3
k = 1
```

Search range:

```text
l = 1
r = 10
```

Suppose:

```text
mid = 5
```

Available flowers:

```text
[1,10,3,10,2]
 ↓     ↓     ↓

[x, _, x, _, x]
```

Since:

```text
k = 1
```

each bloomed flower forms a bouquet.

Therefore:

```text
bouquets = 3
```

Since:

```text
3 >= m
```

day `5` works.

But we need the **minimum** day, so search earlier.

Eventually:

```text
day = 3
```

Available:

```text
[x, _, x, _, x]
```

Again:

```text
bouquets = 3
```

So day `3` works.

Day `2` gives:

```text
[x, _, _, _, x]
```

Only 2 bouquets.

Therefore:

```text
answer = 3
```

---

## 10. Important Detail: Adjacent Means Adjacent

This is the most important part of the feasibility check.

Consider:

```text
bloomDay = [7,7,7,7,12,7,7]
m = 2
k = 3
```

At day `7`:

```text
[x,x,x,x,_,x,x]
```

There are 6 bloomed flowers, but we **cannot** make 2 bouquets.

Why?

Because we need:

```text
3 adjacent + 3 adjacent
```

The flowers are split by the unbloomed flower:

```text
[x,x,x,x] [_,] [x,x]
```

We can only make one group of 3.

This is why simply counting the total number of bloomed flowers is incorrect.

---

## 11. Why Binary Search Works

For every candidate day, `isPossible()` returns either:

```text
true
```

or:

```text
false
```

The result is monotonic.

If we can make the required bouquets on day `D`, then we can also make them on every day after `D`.

Therefore:

```text
Day:
1  2  3  4  5  6  7  8  ...
❌ ❌ ❌ ❌ ❌ ❌ ✅ ✅ ...
                  ↑
             first valid day
```

Binary Search finds this boundary efficiently.

---

## 12. Pattern Connection

This problem is another **Binary Search on Answer** problem.

Compare the three problems you've now solved:

### 875. Koko Eating Bananas

```text
Answer → Eating Speed

Check → Can Koko finish within h hours?
```

### 1011. Capacity To Ship Packages

```text
Answer → Ship Capacity

Check → Can packages be shipped within days?
```

### 1482. Minimum Days to Make Bouquets

```text
Answer → Number of Days

Check → Can m bouquets be formed by this day?
```

The common structure is:

```text
             Binary Search on Answer
                       │
             ┌─────────┴─────────┐
             ↓                   ↓
       Candidate Answer      Feasibility Check
                                   │
                              True / False
                                   │
                       Find first valid answer
```

The important skill is recognizing this structure even though the story of each problem is completely different.

---

## 13. Similar Problems / Variations

### LeetCode 875 — Koko Eating Bananas

```text
Minimum speed
→ Can finish within h hours?
```

### LeetCode 1011 — Capacity To Ship Packages Within D Days

```text
Minimum capacity
→ Can ship within required days?
```

### LeetCode 410 — Split Array Largest Sum

```text
Minimum possible maximum sum
→ Can split into required groups?
```

### LeetCode 1552 — Magnetic Force Between Two Balls

```text
Maximum possible minimum distance
→ Can place balls with this distance?
```

These problems look different, but the Binary Search reasoning is similar.

---

## 14. Common Mistakes

### Mistake 1: Checking Only Total Bloomed Flowers

Wrong idea:

```text
bloomed flowers >= m * k
```

This ignores adjacency.

You need `k` **consecutive** bloomed flowers for each bouquet.

---

### Mistake 2: Not Resetting the Consecutive Count

When:

```java
d > days
```

the flower hasn't bloomed yet.

Therefore:

```java
flow = 0;
```

must happen because the adjacency sequence is broken.

---

### Mistake 3: Reusing Flowers

After:

```java
if (flow == f)
```

you form a bouquet and reset:

```java
flow = 0;
```

Otherwise the same flowers could incorrectly contribute to multiple bouquets.

---

### Mistake 4: Wrong Search Range

The search should be:

```text
minimum bloom day → maximum bloom day
```

not:

```text
0 → bloomDay.length
```

The answer represents a **day**, not an array index.

---

### Mistake 5: Forgetting the Impossible Case

If:

```text
n < m × k
```

there are not enough flowers to make the required bouquets.

Return:

```text
-1
```

immediately.

---

### Mistake 6: Returning `mid`

As with the previous Binary Search on Answer problems, we are searching for the **first valid answer**.

Therefore:

```java
return l;
```

is the correct final return.

---

## 15. Complexity

Let:

```text
n = bloomDay.length
M = maximum bloom day
```

Each `isPossible()` check scans the entire array:

```text
O(n)
```

Binary Search checks the range of possible days:

```text
O(log M)
```

Therefore:

```text
Time = O(n log M)
```

Space:

```text
O(1)
```

So:

```text
Time  → O(n log(max(bloomDay)))
Space → O(1)
```

---

## 16. Placement-Level Takeaway

The important thing to learn from this problem is not the flower story.

Translate the problem into:

```text
What is the answer?
        ↓
Minimum number of days

Can I check a candidate answer?
        ↓
Can I make m bouquets by this day?

Is the result monotonic?
        ↓
Earlier days may fail
Later days continue to work

Therefore:
        ↓
Binary Search on Answer
```

The general template is:

```text
low = minimum possible answer
high = maximum possible answer

while low <= high:

    mid = middle candidate

    if candidate is possible:
        high = mid - 1
    else:
        low = mid + 1

return low
```

For this problem, the only part that changes is the feasibility function.

---

## 17. Final 30-Second Cheat Sheet

```text
Problem:
Minimum days to make m bouquets.

Pattern:
Binary Search on Answer.

Impossible:
n < m × k → return -1

Search Space:
min(bloomDay) → max(bloomDay)

For candidate day:
    bloomDay[i] <= day
        → flower available

Count consecutive available flowers.

When:
    consecutive == k
        → make one bouquet
        → reset consecutive count

When flower hasn't bloomed:
    reset consecutive count

Condition:
    bouquets >= m → possible

If possible:
    r = mid - 1

If impossible:
    l = mid + 1

Answer:
    return l

Complexity:
Time  → O(n log(max(bloomDay)))
Space → O(1)
```

### One Line to Remember

> **Minimum day + consecutive elements + feasibility check → Binary Search on Answer.**
