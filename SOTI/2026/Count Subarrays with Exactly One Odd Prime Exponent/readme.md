# 🔢 Count Subarrays with Exactly One Odd Prime Exponent

> **Note:** This problem statement is reconstructed from a problem encountered during a campus recruitment coding assessment. The exact wording and conditions may differ from the original question.

---

## 📌 Problem Statement

You are given an array of positive integers.

Consider every possible **contiguous subarray**.

For each subarray:

1. Find the **product of all elements** in the subarray.
2. Find the **prime factorization** of that product.
3. Count how many times each prime factor occurs.

A subarray is considered **valid** if:

- Exactly **one prime number** has an **odd exponent**.
- Every other prime number has an **even exponent**.

Your task is to find the **total number of valid subarrays**.

---

## 🔍 Example

### Input

```text
A = [2, 3, 3, 4]
```

Consider some subarrays.

### Subarray `[2]`

Product:

```text
2
```

Prime factorization:

```text
2¹
```

Prime `2` has an odd exponent.

There is exactly one prime with an odd exponent.

✅ Valid

---

### Subarray `[3]`

Product:

```text
3
```

Prime factorization:

```text
3¹
```

Prime `3` has an odd exponent.

There is exactly one prime with an odd exponent.

✅ Valid

---

### Subarray `[2, 3]`

Product:

```text
2 × 3 = 6
```

Prime factorization:

```text
2¹ × 3¹
```

Both `2` and `3` have odd exponents.

There are two primes with odd exponents.

❌ Invalid

---

### Subarray `[3, 4]`

Product:

```text
3 × 4 = 12
```

Prime factorization:

```text
2² × 3¹
```

Exponent of `2` is even.

Exponent of `3` is odd.

There is exactly one prime with an odd exponent.

✅ Valid

---

### Subarray `[2, 3, 3]`

Product:

```text
2 × 3 × 3 = 18
```

Prime factorization:

```text
2¹ × 3²
```

Exponent of `2` is odd.

Exponent of `3` is even.

There is exactly one prime with an odd exponent.

✅ Valid

---

## 🎯 Objective

Count the number of contiguous subarrays whose product has:

```text
Exactly one prime factor with an odd exponent
```

and:

```text
All other prime factors with even exponents
```

---

# 💡 Mathematical Observation

Suppose a number has the prime factorization:

```text
N = p₁^a₁ × p₂^a₂ × p₃^a₃ × ...
```

The number is valid if exactly one exponent among:

```text
a₁, a₂, a₃, ...
```

is odd.

For example:

```text
18 = 2¹ × 3²
```

Only one exponent is odd.

Therefore:

```text
18 is valid
```

Another example:

```text
6 = 2¹ × 3¹
```

Two exponents are odd.

Therefore:

```text
6 is invalid
```

---

## 🧠 Important Idea

Only the **parity** of every prime exponent matters.

For each prime factor, we only need to know:

```text
Even → 0
Odd  → 1
```

For example:

```text
12 = 2² × 3¹
```

Parity representation:

```text
2 → 0
3 → 1
```

Since exactly one prime has parity `1`, the number is valid.

---

## 🔗 Connection to Square-Free Numbers

This problem can be related to the **square-free part** of a number.

If all prime factors with even exponents are removed, the remaining product contains only the primes with odd exponents.

For example:

```text
18 = 2¹ × 3²
```

Removing the square factor:

```text
3²
```

leaves:

```text
2
```

Since only one prime remains, the number is valid.

For:

```text
6 = 2¹ × 3¹
```

both primes remain:

```text
2 × 3
```

Therefore, the number is invalid.

---

# 🛠️ Concepts Used

- Prime Factorization
- Subarrays
- Prime Exponent Parity
- Number Theory
- Square-Free Numbers
- Prefix-Based Optimization
- HashMap / Frequency Counting

---

⭐ **The key observation is that the exact exponent does not matter. Only whether each prime exponent is odd or even is important.**
