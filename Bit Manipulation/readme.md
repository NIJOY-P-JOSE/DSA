# Bit Manipulation - Interview Revision Notes

> A quick revision guide for coding interviews (Google, Amazon, Microsoft, Adobe, Atlassian, TCS Digital, etc.)

---

# Why Bit Manipulation?

Bit Manipulation allows us to solve many problems in **O(1)** or **O(log n)** instead of **O(n)** by directly operating on binary representations.

Applications:
- Check Odd/Even
- Power of Two
- Count Set Bits
- Single Number
- Binary Exponentiation
- Bit Masking
- Subsets
- DP + Bitmask
- Trie + XOR

---

# Binary Basics

```
Decimal    Binary

0          0000
1          0001
2          0010
3          0011
4          0100
5          0101
6          0110
7          0111
8          1000
```

Each bit represents a power of 2.

```
13 = 1101

1×2³ + 1×2² + 0×2¹ + 1×2⁰

= 8 + 4 + 1
= 13
```

---

# Bitwise Operators

| Operator | Name |
|----------|------|
| & | AND |
| \| | OR |
| ^ | XOR |
| ~ | NOT |
| << | Left Shift |
| >> | Right Shift |
| >>> | Unsigned Right Shift (Java) |

---

# AND (&)

```
1 & 1 = 1
1 & 0 = 0
0 & 1 = 0
0 & 0 = 0
```

Example

```
5 = 0101
3 = 0011

0101
0011
----
0001 = 1
```

Used for:
- Checking bits
- Odd/Even
- Clearing bits

---

# OR (|)

```
1 | 1 = 1
1 | 0 = 1
0 | 1 = 1
0 | 0 = 0
```

Used for:
- Setting bits

---

# XOR (^)

```
1 ^ 1 = 0
0 ^ 0 = 0
1 ^ 0 = 1
0 ^ 1 = 1
```

Properties

```
a ^ a = 0

a ^ 0 = a

a ^ b ^ a = b

Order doesn't matter.
```

Most Important Operator!

Applications:
- Single Number
- Swap
- Toggle Bit

---

# NOT (~)

Flips every bit.

```
~5

5 = 00000101

~

11111010
```

Java uses **32-bit two's complement**, so:

```java
~5 = -6
```

---

# Left Shift

```
x << 1 = x × 2

x << 2 = x × 4

x << 3 = x × 8
```

Example

```
5 << 1

0101

↓

1010

=10
```

---

# Right Shift

```
x >> 1 = x / 2

x >> 2 = x / 4
```

Example

```
20 >> 2

10100

↓

00101

=5
```

---

# Important Formulas

## Check Odd/Even

```java
(n & 1) == 1
```

---

## Get ith Bit

```java
(n >> i) & 1
```

---

## Set ith Bit

```java
n | (1 << i)
```

---

## Clear ith Bit

```java
n & ~(1 << i)
```

---

## Toggle ith Bit

```java
n ^ (1 << i)
```

---

## Remove Last Set Bit

```java
n & (n - 1)
```

---

## Get Lowest Set Bit

```java
n & (-n)
```

---

# Count Set Bits

## Method 1

```java
int count = 0;

while(n > 0){
    count += n & 1;
    n >>= 1;
}
```

Time

```
O(log n)
```

---

## Brian Kernighan Algorithm

```java
while(n > 0){
    n = n & (n - 1);
    count++;
}
```

Time

```
O(number of set bits)
```

---

# Check Power of Two

Observation:

Power of 2 has only one set bit.

```java
n > 0 && (n & (n - 1)) == 0
```

---

# Single Number

Every element appears twice except one.

```java
int ans = 0;

for(int x : nums)
    ans ^= x;
```

Time

```
O(n)
```

Space

```
O(1)
```

---

# Binary Exponentiation

Find

```
base^power
```

Instead of

```
O(power)
```

Use

```
O(log power)
```

Algorithm

```java
long ans = 1;

while(power > 0){

    if((power & 1) == 1)
        ans *= base;

    base *= base;

    power >>= 1;
}
```

Example

```
3^13

13 = 1101

= 3^8 × 3^4 × 3^1
```

---

# Bit Masking

Mask

```
1 << i
```

Examples

```
00010000

Used for

✔ Check Bit

✔ Set Bit

✔ Clear Bit

✔ Toggle Bit
```

---

# Time Complexities

| Operation | Complexity |
|------------|------------|
| Check Bit | O(1) |
| Set Bit | O(1) |
| Clear Bit | O(1) |
| Toggle Bit | O(1) |
| Odd/Even | O(1) |
| XOR Single Number | O(n) |
| Count Bits | O(log n) |
| Brian Kernighan | O(Set Bits) |
| Binary Exponentiation | O(log power) |

---

# Frequently Asked Interview Problems

## Easy

- **136.** Single Number
- **191.** Number of 1 Bits
- **338.** Counting Bits
- **231.** Power of Two
- **190.** Reverse Bits
- **476.** Number Complement
- **832.** Flipping an Image

---

## Medium

- **137.** Single Number II
- **260.** Single Number III
- **78.** Subsets
- **89.** Gray Code
- **201.** Bitwise AND of Numbers Range

---

## Hard

- **421.** Maximum XOR of Two Numbers in an Array
- **Trie + XOR**
- **DP + Bitmask**
- **Traveling Salesman (Bitmask DP)**

---

# Common Interview Tricks

```
Odd/Even

n & 1
```

```
Power of Two

n & (n - 1)
```

```
Remove Last Set Bit

n & (n - 1)
```

```
Lowest Set Bit

n & (-n)
```

```
Toggle Bit

n ^ (1 << i)
```

```
Invert Bit

bit ^ 1
```

```
Swap without temp

a ^= b
b ^= a
a ^= b
```

---

# Things Interviewers Expect You to Know

✔ Why XOR works

✔ Difference between AND, OR and XOR

✔ Binary Exponentiation

✔ Count Set Bits

✔ Power of Two

✔ Bit Masking

✔ Two Pointer + XOR tricks

✔ Binary Representation

✔ Time Complexity of Bit Operations

---

# Final Revision Checklist

- [ ] Binary Representation
- [ ] AND, OR, XOR, NOT
- [ ] Left & Right Shift
- [ ] Odd/Even
- [ ] Get / Set / Clear / Toggle Bit
- [ ] Count Set Bits
- [ ] Brian Kernighan Algorithm
- [ ] Power of Two
- [ ] Single Number
- [ ] Binary Exponentiation
- [ ] Bit Masking
- [ ] XOR Properties
- [ ] Practice LeetCode Bit Manipulation Problems
