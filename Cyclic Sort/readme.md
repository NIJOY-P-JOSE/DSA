# 🔄 Cyclic Sort

> A structured collection of **Cyclic Sort and index-placement problems** practiced for DSA and software engineering placement preparation.

[![Topic](https://img.shields.io/badge/DSA-Cyclic%20Sort-blue)](https://github.com/NIJOY-P-JOSE/DSA/tree/main/Cyclic%20Sort)
[![Language](https://img.shields.io/badge/Java-Solutions-red?logo=openjdk)](https://www.java.com/)

---

## 📌 About

This folder focuses on the **Cyclic Sort pattern**, an in-place technique that is especially useful when an array contains numbers from a known range such as:

```text
1 → n
```

or

```text
0 → n
```

The main idea is simple:

> **Place every value at the index where it belongs.**

Instead of comparing elements repeatedly or using an extra HashSet/HashMap, we use the relationship between the **value and its correct index**.

---

# 🧠 Core Idea

For an array containing values from `1` to `n`:

```text
Value = x
Correct Index = x - 1
```

For example:

```text
Array:

[3, 1, 5, 4, 2]

Value → Correct Index

1 → 0
2 → 1
3 → 2
4 → 3
5 → 4
```

So we keep swapping elements until they reach their correct positions.

```text
Before

[3, 1, 5, 4, 2]

   ↓ place 3 at index 2

[5, 1, 3, 4, 2]

   ↓ place 5 at index 4

[2, 1, 3, 4, 5]

   ↓ place 2 at index 1

[1, 2, 3, 4, 5]
```

---

# 🎯 Recognition Trigger

Cyclic Sort should come to mind when a problem contains:

```text
✅ Array of integers
✅ Values belong to a known range
✅ Values are closely related to array indices
✅ Missing numbers / duplicate numbers
✅ Need O(1) extra space
✅ In-place modification is allowed
```

A strong trigger is:

> **"The values tell me where the elements should go."**

---

# 🔥 The Pattern

The basic Cyclic Sort workflow is:

```text
             Current Index
                   ↓
             Check Value
                   ↓
        Find Correct Index
                   ↓
     ┌─────────────┴─────────────┐
     │                           │
Correct Position            Wrong Position
     │                           │
   Move On                    Swap
                                 │
                                 ↓
                         Check Again
```

The important part is that after a swap, the new value at the current index may also be misplaced.

So we continue checking the **same index** until the correct element is placed there.

---

# 💻 Basic Template

For numbers from `1` to `n`:

```java
int i = 0;

while (i < nums.length) {

    int correctIndex = nums[i] - 1;

    if (nums[i] != nums[correctIndex]) {
        int temp = nums[i];
        nums[i] = nums[correctIndex];
        nums[correctIndex] = temp;
    } else {
        i++;
    }
}
```

The exact condition changes depending on the problem.

For example, duplicates require additional handling so that we do not swap identical values forever.

---

# 📚 Problems Covered

## 1. 268. Missing Number

[Open Problem](./268.%20Missing%20Number)

### Pattern

**Cyclic placement + missing value detection**

The array contains values in a range where one number is absent.

The key idea is:

```text
Value → Expected Index
```

After placing valid values correctly, the index containing the wrong value identifies the missing number.

---

## 2. 287. Find the Duplicate Number

[Open Problem](./287.%20Find%20the%20Duplicate%20Number)

### Pattern

**Duplicate detection**

The problem exploits the relationship between values and positions to identify a repeated number.

This problem is especially useful for understanding an important limitation:

> **Cyclic Sort works differently when duplicate values prevent a one-to-one mapping between value and position.**

---

## 3. 41. First Missing Positive

[Open Problem](./41.%20First%20Missing%20Positive)

### Pattern

**Cyclic placement + range filtering**

This is an important advanced variation.

Not every value is useful.

For a particular array length:

```text
Only values in the useful range
need to be placed.
```

Values such as:

```text
0
negative numbers
numbers larger than n
```

do not need to occupy a valid cyclic position.

This makes the problem a good test of whether the basic pattern has actually been understood.

---

## 4. 442. Find All Duplicates in an Array

[Open Problem](./442.%20Find%20All%20Duplicates%20in%20an%20Array)

### Pattern

**Cyclic placement + duplicate detection**

Unlike finding a single duplicate, this problem requires identifying **all repeated values**.

The key observation remains:

```text
Value x → index x - 1
```

After the placement process, incorrectly positioned values can reveal duplicates.

---

## 5. 448. Find All Numbers Disappeared in an Array

[Open Problem](./448.%20Find%20All%20Numbers%20Disappeared%20in%20an%20Array)

### Pattern

**Cyclic placement + missing values**

This is a natural extension of the missing-number pattern.

After attempting to place each number at its correct index:

```text
Index with incorrect value
        ↓
Missing number
```

The same principle can therefore solve multiple missing-value problems.

---

## 6. 645. Set Mismatch

[Open Problem](./645.%20Set%20Mismatch)

### Pattern

**Cyclic placement + duplicate + missing number**

This combines two ideas:

```text
One number appears twice
        +
One number is missing
```

The cyclic-placement process helps expose both values from the final arrangement.

---

# 🧩 Problem Progression

The problems in this folder form a useful progression:

```text
268. Missing Number
        ↓
Basic Cyclic Placement
        ↓
448. Find All Numbers Disappeared
        ↓
Missing Values
        ↓
442. Find All Duplicates
        ↓
Duplicate Detection
        ↓
645. Set Mismatch
        ↓
Duplicate + Missing
        ↓
287. Find the Duplicate Number
        ↓
Advanced Duplicate Handling
        ↓
41. First Missing Positive
        ↓
Range Filtering + Cyclic Placement
```

This progression is useful because each problem modifies the same central idea instead of introducing a completely unrelated technique.

---

# 🧠 How to Think About Cyclic Sort

When you see an array problem, ask:

### Step 1 — Do values correspond to positions?

For example:

```text
nums[i] ∈ [1, n]
```

Then:

```text
correctIndex = nums[i] - 1
```

This is a strong indication that Cyclic Sort may work.

---

### Step 2 — Can I rearrange the array in-place?

Cyclic Sort is particularly useful when:

```text
Extra Space = O(1)
```

is required or desirable.

---

### Step 3 — What does an incorrect position tell me?

After placement:

```text
index ≠ expected value
```

can reveal:

* Missing number
* Duplicate number
* Mismatched value

The final scan is often where the answer is extracted.

---

# 🔄 Cyclic Sort vs Other Approaches

| Approach          | Typical Space | Main Idea                        |
| ----------------- | ------------: | -------------------------------- |
| HashSet / HashMap |          O(n) | Track numbers separately         |
| Sorting           |    O(n log n) | Sort then inspect                |
| Cyclic Sort       |          O(1) | Place values directly            |
| XOR               |          O(1) | Compare bit patterns             |
| Mathematical Sum  |          O(1) | Compare expected and actual sums |

Cyclic Sort is particularly valuable when the problem's **value range matches the array positions**.

---

# ⚠️ Common Mistakes

### 1. Using the wrong target index

For values from `1` to `n`:

```java
correctIndex = nums[i] - 1;
```

For values from `0` to `n`:

```java
correctIndex = nums[i];
```

Do not assume the mapping is always the same.

---

### 2. Incrementing `i` too early

After a swap, the new value at index `i` still needs to be checked.

Wrong idea:

```text
Swap → i++
```

Correct idea:

```text
Swap → check current index again
```

unless the current element is already in its correct position.

---

### 3. Ignoring duplicates

With duplicate values:

```text
nums[i] == nums[correctIndex]
```

can indicate that a swap would accomplish nothing.

Blindly swapping can create an infinite loop.

---

### 4. Trying to place invalid values

Problems such as **First Missing Positive** contain values that cannot map to valid indices.

Before placing an element, check whether it belongs to the useful range.

---

### 5. Forgetting the final scan

Cyclic Sort often does not directly return the answer.

Instead:

```text
Place values
      ↓
Scan array
      ↓
Find the incorrect position
      ↓
Derive answer
```

---

# ⏱️ Complexity

The main advantage of Cyclic Sort is:

```text
Time:  O(n)
Space: O(1)
```

### Why is the time O(n)?

Although the algorithm contains swaps inside a loop, every successful swap places an element closer to its final position.

An element does not keep moving indefinitely.

The array is gradually transformed into its correct arrangement using in-place swaps.

---

# 🎯 Placement-Level Takeaway

When an unfamiliar placement problem gives you an array containing numbers in a predictable range, ask:

```text
Are values related to indices?
          ↓
      Yes
          ↓
Can I place each value
at its correct position?
          ↓
      Yes
          ↓
Think CYCLIC SORT
          ↓
Perform in-place placement
          ↓
Scan for incorrect positions
          ↓
Derive missing / duplicate values
```

---

# 🧠 Pattern Connection

Cyclic Sort connects naturally with several other DSA techniques:

```text
Array Problems
      │
      ├── Hashing
      │      ↓
      │   Track values
      │
      ├── Sorting
      │      ↓
      │   Arrange values
      │
      └── Cyclic Sort
             ↓
       Use value as index
             ↓
       In-place placement
```

The key distinction is:

> **Normal sorting asks "How should I order these values?"**

while:

> **Cyclic Sort asks "Where does each value belong?"**

---

# 🔗 Related Problems

After learning the problems in this folder, useful variations include problems involving:

* Missing numbers
* Duplicate numbers
* Set mismatch
* First missing positive
* In-place array marking
* Index/value relationships

The important thing is to recognize the **value → index mapping**, even when the problem is not explicitly named "Cyclic Sort."

---

# ⚡ Quick Revision Cheat Sheet

```text
╔══════════════════════════════════════════╗
║          🔄 CYCLIC SORT CHEAT SHEET      ║
╠══════════════════════════════════════════╣
║ Main Pattern: Value → Correct Index      ║
║                                          ║
║ Values 1...n:                            ║
║ correctIndex = value - 1                 ║
║                                          ║
║ Core Operation:                          ║
║ Swap misplaced value into its position   ║
║                                          ║
║ After Placement:                         ║
║ Wrong index → Missing / Duplicate        ║
║                                          ║
║ Main Advantage:                          ║
║ O(n) time + O(1) extra space             ║
╚══════════════════════════════════════════╝
```

> **⭐ One sentence to remember:**
> **When array values belong to a known index-based range, think "put every value where it belongs" before reaching for extra data structures.**

---

# 📂 Folder Structure

```text
Cyclic Sort/
│
├── 268. Missing Number/
│   ├── Solution.java
│   └── readme.md
│
├── 287. Find the Duplicate Number/
│   ├── Solution.java
│   └── readme.md
│
├── 41. First Missing Positive/
│   ├── Solution.java
│   └── readme.md
│
├── 442. Find All Duplicates in an Array/
│   ├── Solution.java
│   └── readme.md
│
├── 448. Find All Numbers Disappeared in an Array/
│   ├── Solution.java
│   └── readme.md
│
├── 645. Set Mismatch/
│   ├── Solution.java
│   └── readme.md
│
├── Solution.java
└── readme.md
```

---

# 👨‍💻 Author

**Nijoy P Jose**

B.Tech Computer Science Engineering Student

[GitHub](https://github.com/NIJOY-P-JOSE)

[DSA Repository](https://github.com/NIJOY-P-JOSE/DSA)

---

⭐ Part of my **Data Structures & Algorithms and placement preparation journey**.

