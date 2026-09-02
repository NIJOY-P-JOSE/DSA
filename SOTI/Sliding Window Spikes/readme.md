# Sliding Window Spikes

> **Pattern:** Sliding Window · Mathematics
> **Difficulty:** Medium
> **Platform:** SOTI Coding Assessment

---

## ⚡ Quick Revision

| Concept         | Remember                                                        |
| --------------- | --------------------------------------------------------------- |
| Goal            | Find windows whose population standard error exceeds tolerance  |
| Window          | Every `m` consecutive measurements                              |
| Key idea        | Maintain window sum and sum of squares                          |
| PSE Formula     | `√(Σx²/m - (Σx/m)²)`                                            |
| Sliding Update  | Remove the old element and add the new element                  |
| Indexing        | Window indexes are 1-based                                     |
| Answer          | Sum the indexes of all spike windows                           |
| Time            | `O(N)`                                                          |
| Space           | `O(1)`                                                          |

---

# 🧠 Problem in Simple Words

Given a list of signal measurements, examine every consecutive window of size `m`.

For each window:

1. Calculate its population standard error (PSE).
2. If `PSE > tolerance`, the window is considered a spike.
3. Add the 1-based index of that window to the answer.

Return the sum of the indexes of all spike windows.

---

## Example

```text
measurements = [10, 25, 27, 52, 23, 26]
m = 4
tolerance = 15.0
```

Windows:

[10, 25, 27, 52] → spike → index 1
[25, 27, 52, 23] → not a spike
[27, 52, 23, 26] → not a spike

Therefore:

```text
Answer = 1
```

---

# 💡 Key Observation

Calculating PSE from scratch for every window would repeatedly process the same elements.

Maintain two values:

```text
ws  = sum of elements in the current window
wsq = sum of squares of elements in the current window
```

The PSE is:

```text
PSE = √(wsq/m - (ws/m)²)
```

When the window moves, remove the old element and add the new element.

---

# 🔍 Algorithm

1. Calculate the sum of the first `m` elements.
2. Calculate the sum of squares of the first `m` elements.
3. Slide the window through the array.
4. When moving the window:
   - Remove the old element.
   - Add the new element.
   - Update both `ws` and `wsq`.
5. Calculate the PSE.
6. If `PSE > tolerance`, add `i + 1` to the answer.
7. Return the answer.

---

# 💻 Solution

```python
def SLIDING_WINDOW_SPIKES(measurements, measurementsLength, m, tolerance):
    ans = 0
    ws = sum(measurements[:m])
    wsq = sum(x*x for x in measurements[:m])

    for i in range(measurementsLength-m+1):
        if i > 0:
            new = measurements[i+m-1]
            old = measurements[i-1]

            ws = ws - old + new
            wsq = wsq - old**2 + new**2

        pse = ((wsq/m) - (ws/m)**2) ** 0.5

        if pse > tolerance:
            ans += i + 1

    return ans
```

---

# 🧪 Dry Run

For:

```text
measurements = [10, 25, 27, 52, 23, 26]
m = 4
```

Initial window:

```text
[10, 25, 27, 52]
```

Maintain:

```text
ws  = 10 + 25 + 27 + 52
wsq = 10² + 25² + 27² + 52²
```

Move the window:

```text
Remove = 10
Add    = 23
```

Update:

```text
ws  = ws - 10 + 23
wsq = wsq - 10² + 23²
```

The new window becomes:

```text
[25, 27, 52, 23]
```

Repeat the same process for every window.

---

# 🚨 Important Edge Cases

### `m > measurementsLength`

There is no complete window.

```text
Answer = 0
```

### Very large tolerance

No window exceeds the tolerance.

```text
Answer = 0
```

### `m = 1`

A window contains only one element, so:

```text
PSE = 0
```

---

# ❌ Common Mistakes

### 1. Missing the last window

Wrong:

```python
range(measurementsLength-m)
```

Correct:

```python
range(measurementsLength-m+1)
```

### 2. Wrong PSE formula

Correct:

```python
pse = ((wsq/m) - (ws/m)**2) ** 0.5
```

### 3. Forgetting 1-based indexing

Correct:

```python
ans += i + 1
```

### 4. Recalculating every window

Avoid calculating the sum from scratch for every window.

Use:

```python
ws = ws - old + new
wsq = wsq - old**2 + new**2
```

---

# 🎯 Placement Pattern

This problem combines **Sliding Window + Mathematics**.

Whenever you see:

```text
Every consecutive window of size K
+
Calculate some property for each window
```

think:

```text
Can I maintain the required information
when one element leaves and another enters?
```

Here, maintain:

```text
sum
sum of squares
```

---

# ⚡ 30-Second Revision

```text
Window size = m

ws  = Σx
wsq = Σx²

PSE = √(wsq/m - (ws/m)²)

Move window:
ws  = ws - old + new
wsq = wsq - old² + new²

If PSE > tolerance:
    ans += i + 1

Time: O(N)
Space: O(1)
```

> **Key Takeaway:** For sliding-window problems involving variance or standard deviation, maintain the **sum and sum of squares** to avoid recalculating every window.
