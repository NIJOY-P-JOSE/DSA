# 66. Plus One

* **Difficulty:** Easy
* **Main DSA Pattern:** Array Traversal · Carry Propagation
* **Important Related Patterns:** In-Place Modification · Reverse Traversal
* **LeetCode:** https://leetcode.com/problems/plus-one/

---

## ⚡ Quick Revision

|                      |                                                               |
| -------------------- | ------------------------------------------------------------- |
| **Goal**             | Add `1` to a number represented as an array of digits         |
| **Pattern**          | Reverse Array Traversal + Carry                               |
| **Main Idea**        | Start from the last digit and propagate carry toward the left |
| **Decision**         | If digit ≠ `9`, increment and return                          |
| **If digit = `9`**   | Change it to `0` and continue left                            |
| **All digits = `9`** | Create an array of size `n + 1`                               |
| **Time**             | `O(n)`                                                        |
| **Space**            | `O(1)` normally, `O(n)` for all `9`s                          |

### ⚡ One-Line Idea

> **Start from the right: non-9 → increment and stop; 9 → make it 0 and carry left.**

---

## 🧠 Problem in Simple Words

The array represents a large number.

For example:

```text
[1, 2, 3]
```

represents:

```text
123
```

We need to add `1`:

```text
123 + 1 = 124
```

So:

```text
[1, 2, 3] → [1, 2, 4]
```

The difficulty comes when the last digit is `9`.

For example:

```text
[1, 2, 9]
```

Adding `1` gives:

```text
[1, 3, 0]
```

And:

```text
[9, 9, 9]
```

becomes:

```text
[1, 0, 0, 0]
```

---

## 🔥 How to Recognize This Problem in a Placement

### 🚨 Recognition Clues

Look for:

* Number represented as an array of digits
* Add `1`
* Increment the number
* Handle carry
* Digits are stored from most significant → least significant

### Recognition Trigger

> **"An operation starts from the last digit and carry may move toward the left."**

Immediately think:

```text
RIGHT → LEFT
```

This is important because the **least significant digit is at the end of the array**.

---

## 🧩 How to Think / Derive the Solution

### Step 1 — Where does addition start?

Normal addition starts from the rightmost digit.

Example:

```text
  123
+   1
-----
  124
```

Therefore, in the array:

```text
[1, 2, 3]
       ↑
      start
```

We should traverse from:

```text
n - 1 → 0
```

---

### Step 2 — What happens if the digit is not `9`?

Example:

```text
[1, 2, 3]
```

Last digit:

```text
3 + 1 = 4
```

No carry is generated.

So we can immediately:

```text
increment digit
return
```

There is no reason to continue scanning.

---

### Step 3 — What happens if the digit is `9`?

Example:

```text
[1, 2, 9]
```

We cannot store `10` in a single digit.

Instead:

```text
9 + 1 = 10
```

So:

```text
9 → 0
```

and carry `1` moves to the previous digit.

Therefore:

```text
[1, 2, 9]
       ↓
[1, 2, 0]
    ↓
2 + 1 = 3

[1, 3, 0]
```

---

### Step 4 — What if we encounter another `9`?

Example:

```text
[1, 9, 9]
```

Process from right:

```text
9 → 0
9 → 0
1 → 2
```

Result:

```text
[2, 0, 0]
```

So the loop naturally handles **multiple consecutive 9s**.

---

### Step 5 — What if every digit is `9`?

Example:

```text
[9, 9, 9]
```

Every digit becomes `0`:

```text
[0, 0, 0]
```

But there is still a carry left.

That means:

```text
999 + 1 = 1000
```

The result needs one extra digit.

Therefore:

```text
[9,9,9] → [1,0,0,0]
```

---

## 📊 Visual Explanation

### Normal case

```text
[1, 2, 3]
       ↑
      +1
       ↓
[1, 2, 4]
```

### Carry case

```text
[1, 2, 9]
       ↑
       9 → 0
       ↓
[1, 2, 0]
    ↑
    2 → 3

[1, 3, 0]
```

### Multiple carries

```text
[1, 9, 9]
       ↓
[1, 9, 0]
    ↓
[1, 0, 0]
 ↓
[2, 0, 0]
```

### All 9s

```text
[9, 9, 9]
     ↓
[0, 0, 0]

carry still remains
     ↓

[1, 0, 0, 0]
```

---

## 🔁 Algorithm

```text
1. Start from the last index.
2. Move from right to left.
3. If digits[i] != 9:
       digits[i]++
       return digits
4. Otherwise:
       digits[i] = 0
       continue to the left
5. If the loop finishes:
       all digits were 9
6. Create an array of size n + 1.
7. Set result[0] = 1.
8. Return result.
```

---

## 💻 My Solution — Java

Your final solution:

```java
class Solution {
    public int[] plusOne(int[] digits) {

        for (int i = digits.length - 1; i >= 0; i--) {

            if (digits[i] != 9) {
                digits[i] += 1;
                return digits;
            }

            digits[i] = 0;
        }

        int[] result = new int[digits.length + 1];
        result[0] = 1;

        return result;
    }
}
```

### Code Explanation

#### 1. Traverse from right to left

```java
for (int i = digits.length - 1; i >= 0; i--)
```

The last element represents the least significant digit.

So addition must start there.

---

#### 2. Check whether the digit is `9`

```java
if (digits[i] != 9)
```

If it isn't `9`, we can simply increase it.

For example:

```text
5 → 6
```

---

#### 3. Increment and return

```java
digits[i] += 1;
return digits;
```

Once a non-9 digit is incremented, there is no carry left.

So the answer is complete.

---

#### 4. Handle `9`

```java
digits[i] = 0;
```

A `9 + 1` becomes `0` with a carry of `1` to the next position.

The loop automatically moves to the previous digit.

---

#### 5. Handle all `9`s

If the loop finishes, every digit was `9`.

For example:

```text
999 → 000
```

We still need the remaining carry.

So create:

```java
int[] result = new int[digits.length + 1];
```

Then:

```java
result[0] = 1;
```

Result:

```text
1000
```

---

## 🧠 Why Does the Algorithm Work?

There are only two possibilities for each digit:

### Case 1 — Digit is not `9`

```text
digit + 1 ≤ 9
```

No carry is generated.

Therefore, we can stop immediately.

### Case 2 — Digit is `9`

```text
9 + 1 = 10
```

The current digit becomes:

```text
0
```

and the carry moves left.

Eventually either:

```text
a non-9 digit
```

is found, or:

```text
all digits were 9
```

This covers every possible case.

---

## 🧠 Pattern Connection

This problem teaches an important **carry propagation** pattern.

Think:

```text
Array
 ↓
Start from right
 ↓
Process current digit
 ↓
9?
├── No → increment → DONE
└── Yes → 0 → move left
```

This pattern appears in problems involving:

* Digit manipulation
* Addition
* Carry propagation
* Number representation

### Important Mental Model

Don't think:

> "How do I add 1 to an array?"

Think:

> **"Where does the carry go?"**

That immediately gives you the right traversal direction.

---

## ⚔️ Similar Problems / Variations

| Problem                               | Difference                                           |
| ------------------------------------- | ---------------------------------------------------- |
| **67. Add Binary**                    | Add two binary strings and handle carry              |
| **2. Add Two Numbers**                | Add two numbers represented using linked lists       |
| **415. Add Strings**                  | Add two large numbers represented as strings         |
| **43. Multiply Strings**              | Perform multiplication without converting to integer |
| **989. Add to Array-Form of Integer** | Add an arbitrary integer to an array-form number     |

The common idea is:

```text
Process digits → handle carry → move left
```

---

## 🚨 Common Mistakes

### 1. Traversing from left to right

Wrong direction:

```text
0 → n-1
```

Addition starts from the least significant digit.

Correct:

```text
n-1 → 0
```

---

### 2. Trying to store `10` in one digit

A digit can only be:

```text
0 → 9
```

So:

```text
9 + 1
```

must become:

```text
0 + carry
```

---

### 3. Forgetting the all-9 case

For:

```text
[9,9,9]
```

you cannot simply return:

```text
[0,0,0]
```

The remaining carry creates:

```text
[1,0,0,0]
```

---

### 4. Continuing after finding a non-9 digit

Once you do:

```java
digits[i]++;
```

there is no carry remaining.

So:

```java
return digits;
```

immediately.

---

### 5. Converting the entire array into an integer

Avoid doing something like:

```text
[9,9,9,...] → integer → +1
```

The problem specifically allows up to **100 digits**, so the number may not fit into normal integer types.

The array itself is the number representation.

---

## ⏱️ Complexity

### Time

```text
O(n)
```

In the worst case, every digit is `9`, so we traverse the entire array.

### Space

Normally:

```text
O(1)
```

because the input array is modified directly.

For the all-9 case:

```text
O(n)
```

because a new array of size `n + 1` is created.

### Overall

```text
Time:       O(n)
Extra Space: O(1) normally
             O(n) worst case
```

---

## 🎯 Placement-Level Takeaway

When you see:

> **"Increment a number represented as an array of digits."**

Don't think about converting it into an integer.

Instead:

```text
Number representation
       ↓
Least significant digit is at the END
       ↓
Start from RIGHT
       ↓
Handle carry
       ↓
9 → 0 and continue
non-9 → +1 and stop
       ↓
All 9s → create n+1 array
```

### Placement Decision Process

Ask these three questions:

**1. Where does addition start?**

```text
Rightmost digit
```

**2. When do I need to continue?**

```text
Only when the current digit is 9
```

**3. When do I stop?**

```text
As soon as I increment a non-9 digit
```

If all digits are `9`, create the extra leading `1`.

---

## ⚡ Final 30-Second Cheat Sheet

```text
┌───────────────────────────────────────────┐
│              66. PLUS ONE                 │
├───────────────────────────────────────────┤
│ Pattern: Carry Propagation                │
│                                           │
│ Start from RIGHT                          │
│                                           │
│ if digit != 9:                            │
│     digit += 1                            │
│     return                                │
│                                           │
│ if digit == 9:                            │
│     digit = 0                             │
│     continue LEFT                         │
│                                           │
│ If loop finishes:                         │
│     all digits were 9                     │
│     create n+1 array                      │
│     result[0] = 1                         │
├───────────────────────────────────────────┤
│ Time:  O(n)                               │
│ Space: O(1) normally, O(n) for all 9s     │
└───────────────────────────────────────────┘
```

### 🧠 Remember

> **"Start from the right; 9 becomes 0 and carries left, otherwise increment and stop."**
