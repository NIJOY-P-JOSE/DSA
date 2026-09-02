# Beacon Net Quality

> **Pattern:** Binary Search on Answer · Greedy
> **Difficulty:** Medium
> **Platform:** SOTI Coding Assessment

---

## ⚡ Quick Revision

| Concept         | Remember                                                        |
| --------------- | --------------------------------------------------------------- |
| Goal            | Minimize the worst-case location error                          |
| Key idea        | Binary search on the minimum possible maximum gap               |
| Boundaries      | Always include `0` and `length`                                 |
| Feasibility     | Check if all gaps can become `≤ mid`                            |
| Greedy formula  | `ceil(gap / mid) - 1`                                           |
| Valid condition | `requiredBeacons <= extraBeacons`                              |
| Accuracy        | `maximumGap / 2`                                                |
| Conversion      | Meters → millimeters: multiply by `1000`                        |
| Time            | `O(N log N + 100N)`                                             |
| Space           | `O(N)`                                                          |

---

# 🧠 Problem in Simple Words

We have an office of length `length` meters with some existing beacons.

The user's location is estimated using the nearest surrounding beacons.

If two adjacent beacons are `gap` meters apart, the maximum possible location error is:

```text
gap / 2
```

We can add at most `extraBeacons` new beacons.

Our goal is to place the new beacons so that the **largest gap between adjacent beacons is minimized**.

Finally, return the minimum possible location error in **millimeters**, rounded to the nearest integer.

<img width="593" height="292" alt="1722673612-2ddc900e3b-beacon" src="https://github.com/user-attachments/assets/2d8ce910-8769-4b3b-8c84-dcd4b2a8df47" />

---

## Example

```text
length = 10
beacons = [1, 3, 8]
extraBeacons = 2
```

Include the office boundaries:

```text
0 ---- 1 ---- 3 ---------------- 8 ---- 10
```

The gaps are:

```text
1, 2, 5, 2
```

The largest gap is `5` meters.

Without adding beacons:

```text
accuracy = 5 / 2
         = 2.5 meters
         = 2500 mm
```

Add 2 beacons at `4.5` and `6`:

```text
0 ---- 1 ---- 3 ---- 4.5 ---- 6 ---- 8 ---- 10
```

New gaps:

```text
1, 2, 1.5, 1.5, 2, 2
```

Largest gap:

```text
2 meters
```

Therefore:

```text
accuracy = 2 / 2
         = 1 meter
         = 1000 mm
```

Answer:

```text
1000
```

---

# 💡 Key Observation

We don't need to directly find the positions of the new beacons.

Instead, binary search the answer.

Ask:

> **Can we make every gap at most `X` meters using at most `extraBeacons`?**

For a gap of length `g`, if the maximum allowed gap is `X`:

```text
number of sections = ceil(g / X)
```

If a gap is divided into `k` sections, we need `k - 1` new beacons.

Therefore:

```text
newBeacons = ceil(g / X) - 1
```

For every gap:

```python
need += ceil(gap / X) - 1
```

If:

```python
need <= extraBeacons
```

then `X` is possible.

Otherwise, `X` is too small.

---

# 🔍 Algorithm

### Step 1 — Sort the beacons

```python
beacons = sorted(beacons)
```

---

### Step 2 — Add the office boundaries

The office starts at `0` and ends at `length`.

```python
points = [0] + beacons + [length]
```

Example:

```text
beacons = [1, 3, 8]

points = [0, 1, 3, 8, 10]
```

---

### Step 3 — Calculate the gaps

```python
gap = []

for i in range(1, len(points)):
    gap.append(points[i] - points[i - 1])
```

For the example:

```text
gaps = [1, 2, 5, 2]
```

---

### Step 4 — Binary search the maximum allowed gap

The answer lies between `0` and `length`.

```python
low = 0
high = length
```

Take:

```python
mid = (low + high) / 2
```

and check whether this maximum gap is achievable.

---

### Step 5 — Count required beacons

For every existing gap:

```python
need += max(0, math.ceil(g / mid) - 1)
```

Example:

```text
gap = 10
mid = 3

ceil(10 / 3) - 1
= 4 - 1
= 3
```

So 3 new beacons are required.

---

### Step 6 — Move the binary search

If:

```python
need <= extraBeacons
```

then `mid` is achievable.

Try a smaller maximum gap:

```python
high = mid
```

Otherwise, more beacons are required than available.

Try a larger maximum gap:

```python
low = mid
```

---

### Step 7 — Calculate the answer

After binary search:

```text
minimum maximum gap = high
```

Maximum location error:

```text
high / 2
```

Convert meters to millimeters:

```text
(high / 2) × 1000
```

Return:

```python
round((high / 2) * 1000)
```

---

# 💻 Solution

```python
import math

def BEACONS_NET_QUALITY(length, beacons, beaconsLength, extraBeacons):
    beacons = sorted(beacons)

    points = [0] + beacons + [length]

    gap = []

    for i in range(1, len(points)):
        gap.append(points[i] - points[i - 1])

    low = 0
    high = length

    for _ in range(100):
        mid = (high + low) / 2

        if mid == 0:
            break

        need = 0

        for g in gap:
            need += max(0, math.ceil(g / mid) - 1)

        if need <= extraBeacons:
            high = mid
        else:
            low = mid

    return round((high / 2) * 1000)
```

> **Note:** `beaconsLength` is part of the given function signature but is not required by the algorithm.

---

# 🧪 Dry Run

### Input

```text
length = 10
beacons = [7]
extraBeacons = 3
```

Add boundaries:

```text
points = [0, 7, 10]
```

Gaps:

```text
7, 3
```

Suppose the allowed maximum gap is approximately:

```text
X = 2.333
```

For gap `7`:

```text
ceil(7 / 2.333) - 1
= 3 - 1
= 2 beacons
```

For gap `3`:

```text
ceil(3 / 2.333) - 1
= 2 - 1
= 1 beacon
```

Total required:

```text
2 + 1 = 3
```

Since:

```text
3 <= extraBeacons
```

this maximum gap is feasible.

Therefore:

```text
maximum gap ≈ 2.333 meters
```

Accuracy:

```text
2.333 / 2
≈ 1.1667 meters
```

Convert to millimeters:

```text
1.1667 × 1000
≈ 1166.7
```

Rounded answer:

```text
1167
```

---

# 🚨 Important Edge Case

### No beacons and no extra beacons

```text
length = 10
beacons = []
extraBeacons = 0
```

The only points are:

```text
0 -------------------- 10
```

Largest gap:

```text
10 meters
```

Accuracy:

```text
10 / 2
= 5 meters
= 5000 mm
```

Answer:

```text
5000
```

---

# ❌ Common Mistakes

### 1. Forgetting the boundaries

Wrong:

```python
points = beacons
```

Correct:

```python
points = [0] + beacons + [length]
```

The boundary-to-beacon distances are also part of the problem.

---

### 2. Using the wrong formula

Wrong:

```python
gap // mid
```

Correct:

```python
math.ceil(gap / mid) - 1
```

Remember:

```text
sections = ceil(gap / allowedGap)

new beacons = sections - 1
```

---

### 3. Forgetting that the answer is half the gap

Binary search finds the:

```text
minimum possible maximum gap
```

But the required location error is:

```text
maximum gap / 2
```

---

### 4. Forgetting unit conversion

The positions are in meters, but the answer is required in millimeters.

```text
1 meter = 1000 millimeters
```

Therefore:

```python
round((high / 2) * 1000)
```

---

### 5. Trying to find the beacon positions directly

Don't try every possible placement.

Instead:

```text
Assume maximum gap = X
          ↓
Count required beacons
          ↓
required <= extraBeacons ?
       ↙             ↘
     YES              NO
      ↓                ↓
smaller X          larger X
```

This is the key reason **Binary Search on Answer** works.

---

# 🎯 Placement Pattern

When you see:

```text
Minimize the maximum distance
+
Can add K points
+
Need optimal placement
```

Think:

```text
Binary Search on Answer
          ↓
Assume answer = X
          ↓
Check if X is feasible
          ↓
Count required new points
          ↓
required <= K ?
      ↙       ↘
    YES        NO
     ↓          ↓
  decrease X  increase X
```

### Recognition Trick

If the problem asks:

```text
"Minimize the maximum..."
```

look for **Binary Search on Answer**.

If you are inserting points inside existing gaps, remember:

```python
ceil(gap / X) - 1
```

---

# ⚡ 30-Second Revision

```text
Pattern:
Binary Search on Answer + Greedy

1. Sort beacons.
2. Add boundaries:
       [0] + beacons + [length]

3. Calculate adjacent gaps.

4. Binary search the minimum possible maximum gap.

5. For every gap:
       required = ceil(gap / mid) - 1

6. If:
       required <= extraBeacons
   then:
       high = mid
   else:
       low = mid

7. high = minimum possible maximum gap.

8. Accuracy = high / 2.

9. Convert to millimeters:
       (high / 2) × 1000

10. Return:
       round((high / 2) * 1000)
```

> **⭐ Key takeaway:**
> **Minimize the maximum gap → Binary Search on Answer.**
>
> **Adding points inside gaps → Greedy feasibility check.**
>
> **Required new beacons → `ceil(gap / allowedGap) - 1`.**
