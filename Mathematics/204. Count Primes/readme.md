# 204. Count Primes

## Problem

Given an integer `n`, return the number of **prime numbers strictly less than `n`**.

This is a classic **Sieve of Eratosthenes** problem. The goal is not to check whether a single number is prime, but to efficiently find **all prime numbers in a range**.

---

## Intuition

The brute-force approach is to check every number individually.

For each number:
- Check divisibility from `2` to `√n`.
- If it has no divisors, it is prime.

Although this works, it becomes slow for large values of `n`.

Instead, think differently.

> Rather than finding prime numbers, eliminate numbers that **cannot be prime**.

Every composite number is a multiple of some smaller prime.

For example:

```
2 → 4, 6, 8, 10, 12 ...

3 → 6, 9, 12, 15 ...

5 → 10, 15, 20, 25 ...
```

After removing all these multiples, the remaining numbers are prime.

This is the idea behind the **Sieve of Eratosthenes**.

---

## Dry Run

Suppose

```
n = 20
```

Initially assume every number is prime.

```
2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19
```

### Step 1

Start with **2**.

Mark all multiples of 2.

```
4 6 8 10 12 14 16 18
```

---

### Step 2

Move to **3**.

Since it is still unmarked, it is prime.

Mark

```
6 9 12 15 18
```

---

### Step 3

Move to **4**.

Already marked.

Skip it.

---

### Step 4

Move to **5**.

Still unmarked.

Mark

```
10 15
```

Continue this process.

Finally, the unmarked numbers are

```
2 3 5 7 11 13 17 19
```

Count them.

Answer = **8**

---

## Why do we iterate only till `√n`?

Notice the loop:

```java
for(int i = 2; i * i < n; i++)
```

Why not go till `n`?

Because every composite number has a factor less than or equal to `√n`.

Example:

```
49 = 7 × 7
```

```
91 = 7 × 13
```

```
85 = 5 × 17
```

These numbers are already marked when processing their smaller factor.

So after `√n`, nothing new gets marked.

---

## Why check `if (!a[i])`?

If a number is already marked,

```
a[i] == true
```

it means it is composite.

Only prime numbers should mark their multiples.

Otherwise, we'd repeat unnecessary work.

---

## Why does `j += i` work?

For a prime `i = 5`

The multiples are

```
10
15
20
25
30
...
```

Adding `i` each time moves directly to the next multiple.

---

## Small Optimization

Instead of

```java
j = i * 2;
```

many optimized solutions use

```java
j = i * i;
```

Why?

Because

For `i = 5`

```
10 = 2 × 5
15 = 3 × 5
20 = 4 × 5
```

These were already marked when processing `2`, `3`, and `4`.

The first new multiple is

```
25
```

So starting from `i × i` avoids extra work.

---

## Complexity

### Time

```
O(n log log n)
```

One of the fastest algorithms for generating prime numbers.

---

### Space

```
O(n)
```

for the boolean array.

---

## Interview Takeaways

✅ This problem is based on the **Sieve of Eratosthenes**.

✅ Think about **eliminating composite numbers** instead of checking each number individually.

✅ Remember why the loop runs only till `√n`.

✅ Know why starting from `i × i` is an optimization.

✅ The interviewer may ask you to modify this solution to:

- Print all prime numbers.
- Check if a number is prime after preprocessing.
- Find primes in a range.
- Find the closest prime pair.
- Generate prime factors using the SPF (Smallest Prime Factor) array.

---

## Similar Problems

- Sieve of Eratosthenes
- Print Prime Numbers up to N
- Prime Numbers in a Range
- Closest Prime Numbers in Range
- Prime Factorization using SPF
- Segmented Sieve

---

## Key Learning

The biggest lesson from this problem is changing the way you think.

Instead of asking:

> "Is this number prime?"

Ask:

> "Can I eliminate numbers that are definitely not prime?"

That single change reduces the complexity from roughly **O(n√n)** to **O(n log log n)**, making the algorithm efficient enough for very large inputs.
