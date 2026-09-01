# Equal Product Subarrays

> **Pattern:** Array · Prefix Product · Greedy
> **Difficulty:** Medium
> **Platform:** SOTI Coding Assessment

---

## ⚡ Quick Revision

| Concept         | Remember                                                           |
| --------------- | ------------------------------------------------------------------ |
| Goal            | Split the array into the maximum number of contiguous parts        |
| Condition       | Every part must have the same product                              |
| Key idea        | Try each prefix product as the target product                      |
| Partition       | Greedily create a new part whenever its product reaches the target |
| Valid partition | The entire array must be consumed                                  |
| Reuse           | Not applicable; every array element belongs to exactly one part    |
| Impossible      | Return `-1` if fewer than 2 equal-product parts can be formed      |
| Time            | `O(N²)`                                                            |
| Space           | `O(1)`                                                             |

---

# 🧠 Problem in Simple Words

Given an array `A`, split it into **contiguous, mutually exclusive subarrays** such that every subarray has the same total product.

Return the **maximum number of parts** possible.

If the array cannot be divided into at least two such parts, return `-1`.

---

## Example

```text
A = [2, 2, 4, 4, 2, 2]
```

One valid partition is:

```text
[2, 2] [4] [4] [2, 2]
```

Products:

```text
2 × 2 = 4
4     = 4
4     = 4
2 × 2 = 4
```

Therefore:

```text
Answer = 4
```

---

# 💡 Key Observation

We need all parts to have the **same product**.

A useful candidate for this common product is a **prefix product**.

For example:

```text
A = [2, 2, 4, 4, 2, 2]
```

Prefix products are:

```text
2
4
16
64
128
256
```

Try:

```text
target = 4
```

Now scan the array from the beginning and accumulate the product.

```text
2 × 2 = 4   → part 1
4 = 4       → part 2
4 = 4       → part 3
2 × 2 = 4   → part 4
```

So we obtain:

```text
[2,2] [4] [4] [2,2]
```

with `4` parts.

We try every prefix product and keep the maximum valid number of parts.

---

# 🔍 Algorithm

### Step 1 — Choose a target product

Build the prefix product:

```python
target *= A[i]
```

Each prefix product is considered as a possible product of every partition.

---

### Step 2 — Try to partition the complete array

For the selected `target`, start with:

```python
prod = 1
count = 0
```

Traverse the array.

```python
prod *= A[j]
```

Whenever:

```python
prod == target
```

we have completed one valid part.

```python
count += 1
prod = 1
```

---

### Step 3 — Stop if the product becomes too large

For positive integers:

```python
elif prod > target:
```

means this partition cannot reach the target anymore.

Therefore, this target is invalid.

---

### Step 4 — Make sure the entire array was partitioned

This is important.

It is not enough to find two or more valid parts.

The **whole array** must be consumed.

Therefore:

```python
prod == 1
```

must be true after the scan.

---

### Step 5 — Update the answer

If the complete array was partitioned into at least two equal-product parts:

```python
ans = max(ans, count)
```

If no valid partition exists:

```python
return -1
```

---

# 💻 Solution

```python
def EQUAL_PROD_SUBARRAY(A, N):
    target = 1
    ans = -1
    
    for i in range(N):
        target *= A[i]
        
        prod = 1
        c = 0
        flag = True
        
        for j in range(N):
            prod *= A[j]
            
            if prod == target:
                c += 1
                prod = 1
            
            elif prod > target:
                flag = False
                break
        
        if flag and prod == 1 and c >= 2:
            ans = max(ans, c)
    
    return ans
```

---

# 🧪 Dry Run

### Input

```text
A = [2, 2, 4, 4, 2, 2]
N = 6
```

### Prefix target = `2`

Try partitioning:

```text
2 → part
2 → part
4 → exceeds target
```

Invalid.

---

### Prefix target = `4`

```text
2 × 2 = 4 → part 1
4       = 4 → part 2
4       = 4 → part 3
2 × 2   = 4 → part 4
```

Therefore:

```text
count = 4
```

Update:

```text
ans = 4
```

---

### Larger targets

They cannot produce more than 4 valid parts for this array.

Final answer:

```text
4
```

---

# 🚨 Important Edge Case

Consider:

```text
A = [2, 3, 5]
```

There is no way to divide the entire array into at least two contiguous parts having equal product.

Therefore:

```text
Answer = -1
```

---

# ❌ Common Mistakes

### 1. Not checking whether the entire array was consumed

Wrong:

```python
if flag and c >= 2:
```

Correct:

```python
if flag and prod == 1 and c >= 2:
```

Finding some valid parts isn't enough. They must cover the **whole array**.

---

### 2. Forgetting to reset `prod`

When a part reaches the target:

```python
if prod == target:
    c += 1
    prod = 1
```

The next part must start with a fresh product.

---

### 3. Continuing after `prod > target`

For positive integers, once:

```python
prod > target
```

the current part cannot become equal to the target by multiplying more positive integers.

So we can stop checking this target.

---

# 🎯 Placement Pattern

When you see:

```text
Array
+
Partition into contiguous parts
+
Every part has equal product
+
Maximum number of parts
```

Think:

```text
Possible common product
        ↓
Prefix product
        ↓
Greedily build parts
        ↓
Product reaches target
        ↓
Start next part
        ↓
Check entire array
        ↓
Maximum number of parts
```

---

# ⚡ 30-Second Revision

```text
Pattern:
Prefix Product + Greedy Partition

1. Try every prefix product as target.
2. Scan the entire array.
3. Multiply elements into current part.
4. If product == target:
       count += 1
       reset product.
5. If product > target:
       target is invalid.
6. Accept only if prod == 1 after scanning.
7. Need at least 2 parts.
8. Take maximum count.
9. Otherwise return -1.
```

> **⭐ Key takeaway:**
> **For each possible target product, greedily form contiguous parts whenever the running product reaches the target, and only accept the target if the entire array is partitioned.**
