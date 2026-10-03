# 875. Koko Eating Bananas

**Difficulty:** Medium  
**Pattern:** Binary Search on Answer  
**Time:** `O(n log(max(piles)))`  
**Space:** `O(1)`

---

## 1. Quick Revision

### Core Idea

We are not searching for an element in `piles`.

Instead, we are searching for the **minimum eating speed `k`** that allows Koko to finish all bananas within `h` hours.

The possible speeds form a monotonic search space:

```text
Speed:      1   2   3   4   5   6   ...   maxPile
Possible:   ❌  ❌  ❌  ✅  ✅  ✅  ...    ✅
```

Once a speed is possible, every larger speed is also possible.

Therefore, we can use **Binary Search on the answer**.

### 30-Second Recall

> **Find minimum value satisfying a condition → define the answer range → check feasibility → if possible, search smaller; otherwise, search larger.**

---

## 2. Problem in Simple Words

Koko has several piles of bananas.

For every hour, she eats at most `k` bananas from one pile.

Given `h` hours, find the **smallest eating speed `k`** that allows her to finish all piles within `h` hours.

For example:

```text
piles = [3, 6, 7, 11]
h = 8
```

The answer is:

```text
k = 4
```

because Koko can finish all bananas in 8 hours at speed 4.

---

## 3. How to Recognize This Problem in a Placement

This problem is a classic **Binary Search on Answer** question.

Look for these clues:

- The answer is an integer.
- We need the **minimum** or **maximum** possible value.
- We can check whether a candidate answer is valid.
- The validity of the answer is monotonic.

Here:

```text
Candidate = eating speed
Condition = Can Koko finish within h hours?
```

The condition looks like:

```text
small speed → may not finish
large speed → can finish
```

Therefore:

```text
False False False True True True
```

This monotonic behavior is the signal to use Binary Search.

---

## 4. How to Think / Derive the Solution

### Step 1: What are we searching for?

We need the minimum:

```text
eating speed k
```

So instead of searching an array, search possible values of `k`.

---

### Step 2: Find the possible range

The minimum possible speed is:

```text
1
```

The maximum useful speed is the largest pile.

Why?

If:

```text
maxPile = 11
```

then eating at speed `11` finishes any individual pile in at most one hour.

Going beyond `11` cannot improve the number of hours further.

Therefore:

```java
int l = 1;
int r = maxPile;
```

---

### Step 3: Check whether a speed works

Suppose:

```text
pile = 11
speed = 4
```

Koko needs:

```text
ceil(11 / 4) = 3 hours
```

For every pile:

```text
hours += ceil(pile / speed)
```

If the total number of hours is:

```text
hours <= h
```

then this speed is possible.

---

### Step 4: Use Binary Search

Suppose the current speed is `mid`.

If:

```text
isPossible(mid) == true
```

then Koko can finish at this speed.

But we want the **minimum** speed.

So try smaller speeds:

```text
r = mid - 1
```

If:

```text
isPossible(mid) == false
```

the speed is too slow.

So we need a larger speed:

```text
l = mid + 1
```

At the end, `l` points to the smallest possible speed.

---

## 5. Visual Explanation

For:

```text
piles = [3, 6, 7, 11]
h = 8
```

Possible speeds behave like:

```text
k = 1   → ❌
k = 2   → ❌
k = 3   → ❌
k = 4   → ✅
k = 5   → ✅
k = 6   → ✅
...
k = 11  → ✅
```

So the search space looks like:

```text
1   2   3   4   5   6   7   8   9   10  11
❌  ❌  ❌  ✅  ✅  ✅  ✅  ✅  ✅  ✅  ✅
            ↑
       minimum valid
```

Binary Search finds the **first `true`**.

---

## 6. Algorithm

1. Find the maximum pile size.
2. Set the speed search range:
   ```text
   l = 1
   r = maxPile
   ```
3. Calculate `mid`.
4. Check whether Koko can finish all piles at speed `mid`.
5. If possible:
   - `mid` is a valid answer.
   - Search for a smaller speed.
   - `r = mid - 1`
6. If impossible:
   - Speed is too slow.
   - Search for a larger speed.
   - `l = mid + 1`
7. Return `l`.

---

## 7. My Java Solution

```java
class Solution {
    public int minEatingSpeed(int[] piles, int h) {
        int l = 1, r = 0;

        // The minimum speed is 1, while the maximum useful speed
        // is the size of the largest pile.
        for (int n : piles)
            r = Math.max(r, n);

        while (l <= r) {
            int mid = l + (r - l) / 2;

            // Check whether this speed allows Koko to finish
            // all piles within the given number of hours.
            if (isPossible(mid, piles, h))
                r = mid - 1;   // Possible, so try a smaller speed.
            else
                l = mid + 1;   // Too slow, so increase the speed.
        }

        // l is the first speed that satisfies the required condition.
        return l;
    }

    boolean isPossible(int speed, int[] piles, int h) {
        int hrs = 0;

        // Each pile requires ceil(pile / speed) hours.
        // Add the required hours for all piles and check the limit.
        for (int pile : piles)
            hrs += Math.ceil(pile / (double) speed);

        return hrs <= h;
    }
}
```

---

## 8. Code Explanation

### Finding the Search Range

```java
int l = 1, r = 0;

for (int n : piles)
    r = Math.max(r, n);
```

We don't know the answer initially, but we know its boundaries.

```text
minimum speed = 1
maximum useful speed = largest pile
```

So the answer must be somewhere between these two values.

---

### Why `isPossible()`?

Binary Search needs a way to determine whether the current candidate is valid.

For a given speed:

```text
speed = k
```

we calculate how many hours are required.

This turns the problem into:

```text
Can speed k finish within h hours?
```

That gives us a simple `true / false` condition for Binary Search.

---

### Calculating Hours

```java
hrs += Math.ceil(pile / (double) speed);
```

Suppose:

```text
pile = 7
speed = 3
```

Koko needs:

```text
ceil(7 / 3) = 3 hours
```

because:

```text
Hour 1 → 3
Hour 2 → 3
Hour 3 → 1
```

So the required hours for a pile are:

```text
ceil(pile / speed)
```

---

### Why `double`?

In Java:

```java
7 / 3
```

with integers gives:

```text
2
```

But we need:

```text
ceil(7 / 3) = 3
```

Therefore:

```java
pile / (double) speed
```

produces the required decimal division before applying `Math.ceil()`.

---

## 9. Example Walkthrough

### Example

```text
piles = [3, 6, 7, 11]
h = 8
```

Search range:

```text
l = 1
r = 11
```

Suppose:

```text
mid = 6
```

Required hours:

```text
ceil(3/6)  = 1
ceil(6/6)  = 1
ceil(7/6)  = 2
ceil(11/6) = 2

total = 6
```

Since:

```text
6 <= 8
```

speed `6` is possible.

But we need the **minimum** speed.

So:

```text
r = mid - 1
```

and search smaller speeds.

Eventually:

```text
speed = 4
```

Required hours:

```text
ceil(3/4)  = 1
ceil(6/4)  = 2
ceil(7/4)  = 2
ceil(11/4) = 3

total = 8
```

Since:

```text
8 <= 8
```

speed `4` works.

Trying smaller speeds fails, so:

```text
answer = 4
```

---

## 10. The Most Important Binary Search Pattern

This problem is different from normal Binary Search.

### Normal Binary Search

We search for a value inside a sorted array:

```text
array → target
```

### Binary Search on Answer

We search through possible answers:

```text
possible answers
       ↓
check candidate
       ↓
valid / invalid
       ↓
discard half
```

For Koko:

```text
Eating Speed
      ↓
Can she finish within h hours?
      ↓
       Yes / No
      ↓
Binary Search
```

---

## 11. Why the Condition is Monotonic

Suppose speed `k` is enough to finish within `h` hours.

Then any speed greater than `k` will also be enough.

For example:

```text
speed 4 → 8 hours → ✅
speed 5 → 7 hours → ✅
speed 6 → 6 hours → ✅
speed 7 → 6 hours → ✅
```

Increasing the speed can never increase the required number of hours.

Therefore:

```text
❌ ❌ ❌ ❌ ✅ ✅ ✅ ✅
```

There is a clear boundary between impossible and possible speeds.

That boundary is exactly what Binary Search finds.

---

## 12. Similar Problems / Variations

This pattern is extremely important for placement preparation.

### LeetCode 1011 — Capacity To Ship Packages Within D Days

Search for:

```text
minimum ship capacity
```

Pattern:

```text
capacity → can ship within D days?
```

---

### LeetCode 1482 — Minimum Number of Days to Make m Bouquets

Search for:

```text
minimum number of days
```

Pattern:

```text
days → can make enough bouquets?
```

---

### LeetCode 410 — Split Array Largest Sum

Search for:

```text
minimum possible maximum sum
```

Pattern:

```text
maximum allowed sum → can split array?
```

---

### LeetCode 1552 — Magnetic Force Between Two Balls

Search for:

```text
maximum possible minimum distance
```

Pattern:

```text
distance → can place balls with this distance?
```

---

## 13. Common Mistakes

### Mistake 1: Binary Searching the `piles` array

This is not normal Binary Search.

The piles themselves are not the search space.

The search space is:

```text
1 → maximum pile
```

---

### Mistake 2: Starting `r` with `piles.length`

Wrong:

```java
r = piles.length;
```

The answer depends on the **number of bananas**, not the number of piles.

Correct:

```java
r = maximum pile size;
```

---

### Mistake 3: Using Integer Division

Wrong:

```java
pile / speed
```

This loses the fractional part.

For example:

```text
7 / 3 = 2
```

but we need:

```text
ceil(7 / 3) = 3
```

Use:

```java
Math.ceil(pile / (double) speed)
```

---

### Mistake 4: Moving the Wrong Boundary

If the current speed works:

```java
r = mid - 1;
```

because we want to find an even smaller valid speed.

If it doesn't work:

```java
l = mid + 1;
```

because we need a faster speed.

---

### Mistake 5: Returning `mid`

The last tested `mid` is not necessarily the minimum valid answer.

The Binary Search is looking for the **first valid speed**.

Therefore, return:

```java
return l;
```

---

## 14. Complexity

Let:

```text
n = number of piles
M = maximum pile size
```

### Feasibility Check

For every candidate speed, we inspect every pile:

```text
O(n)
```

### Binary Search

The speed range contains at most `M` values:

```text
O(log M)
```

Therefore:

```text
Time = O(n log M)
```

or:

```text
O(n log(max(piles)))
```

### Space

Only constant extra variables are used:

```text
O(1)
```

---

## 15. Placement-Level Takeaway

The biggest lesson from Koko is learning to recognize **Binary Search on Answer**.

Don't ask:

> "Where is the target in this array?"

Instead ask:

> "What possible answer am I searching for, and can I efficiently check whether a candidate answer works?"

For this problem:

```text
Answer = eating speed
          ↓
Candidate speed
          ↓
Calculate required hours
          ↓
hours <= h ?
       ↙     ↘
     YES      NO
      ↓        ↓
 search       search
 smaller      larger
```

The general template is:

```text
low = minimum possible answer
high = maximum possible answer

while low <= high:
    mid = middle candidate

    if candidate is possible:
        search for a smaller answer
    else:
        search for a larger answer

return low
```

This template is worth memorizing because it applies to many placement problems.

---

## 16. Final 30-Second Cheat Sheet

```text
Problem:
Find minimum eating speed.

Pattern:
Binary Search on Answer

Search Space:
1 → maximum pile

Check:
hours = Σ ceil(pile / speed)

Condition:
hours <= h → speed is possible

If possible:
r = mid - 1

If impossible:
l = mid + 1

Answer:
return l

Complexity:
Time  → O(n log(max(piles)))
Space → O(1)
```

### One Line to Remember

> **When the answer is a number and you can check whether a candidate number works, look for Binary Search on Answer.**
