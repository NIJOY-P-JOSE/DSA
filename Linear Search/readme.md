# 🔍 Linear Search

> A collection of problems focused on **Linear Search, array traversal, and straightforward element-by-element processing**, practiced as part of my DSA and placement preparation.

[![Topic](https://img.shields.io/badge/DSA-Linear%20Search-blue)](https://github.com/NIJOY-P-JOSE/DSA/tree/main/Linear%20Search)
[![Language](https://img.shields.io/badge/Java-Solutions-red?logo=openjdk)](https://www.java.com/)

---

## 📌 About

**Linear Search** is one of the simplest and most fundamental searching techniques.

The basic idea is:

```text
Start from the beginning
        ↓
Check the current element
        ↓
Is it useful / does it satisfy the condition?
        ↓
No → Move to the next element
        ↓
Continue until the answer is found
```

Unlike Binary Search, Linear Search does not require the data to be sorted.

This folder uses simple traversal problems to build the habit of:

* Understanding the input
* Traversing arrays correctly
* Maintaining a result while iterating
* Handling nested arrays
* Identifying when a simple `O(n)` traversal is enough

---

# 🎯 Core Pattern

The general Linear Search pattern is:

```text id="7ymx3v"
for each element
      ↓
check / process element
      ↓
update answer
```

The solution usually involves a single pass through the data.

---

# 📚 Problems Covered

## 1. 1295. Find Numbers with Even Number of Digits

[Open Problem](./1295.%20Find%20Numbers%20with%20Even%20Number%20of%20Digits)

### Main Concept

**Array traversal + digit counting**

For every number:

```text id="19r1ki"
Take one number
     ↓
Count its digits
     ↓
Check whether digit count is even
     ↓
Update the answer
```

This problem is useful for practicing the basic pattern:

> **Traverse every element and apply a condition.**

---

## 2. 1672. Richest Customer Wealth

[Open Problem](./1672.%20Richest%20Customer%20Wealth)

### Main Concept

**2D array traversal + running maximum**

The input is a matrix where each row represents one customer's accounts.

The reasoning is:

```text id="uh2m6z"
Customer 1 → Sum all accounts
Customer 2 → Sum all accounts
Customer 3 → Sum all accounts
             ↓
       Keep maximum
```

This introduces an important extension of Linear Search:

> **Traverse each row, calculate a value, and maintain the best result seen so far.**

---

# 🧠 How to Recognize Linear Search

Linear Search should be your first thought when:

### 1. The array is not sorted

```text
[8, 2, 15, 4, 9]
```

There is no ordering that lets you safely eliminate half of the elements.

---

### 2. You must inspect every element

Examples:

```text
Count elements satisfying a condition
Find maximum
Find minimum
Calculate sum
Count digits
Check properties
```

In these cases, a complete traversal is often necessary.

---

### 3. The problem is simple enough for one pass

Ask:

> **Can I solve this by visiting each element once and maintaining the required information?**

If yes, start with a linear traversal.

---

# 🔥 Recognition Trigger

```text id="x19hpt"
Array / Matrix
      +
Need to inspect elements
      +
No useful ordering
      ↓
Think Linear Traversal
```

Do not automatically search for a more complicated DSA pattern.

Sometimes the correct solution really is:

```text
O(n)
```

---

# 🧩 How to Think About a Linear Search Problem

### Step 1 — Understand what must be computed

Ask:

> What information do I need from each element?

For example:

```text
Number of digits
Sum of values
Maximum value
Count of valid elements
```

---

### Step 2 — Decide what to maintain

Typical variables include:

```java
int count = 0;
int sum = 0;
int max = 0;
```

The variable depends on the required answer.

---

### Step 3 — Traverse the data

For a 1D array:

```java
for (int num : nums) {
    // process num
}
```

For a 2D array:

```java
for (int[] row : accounts) {
    int sum = 0;

    for (int value : row) {
        sum += value;
    }
}
```

---

### Step 4 — Update the result

Examples:

```java
count++;
```

or:

```java
max = Math.max(max, sum);
```

---

# 💻 General Java Template

### 1D Traversal

```java
for (int num : nums) {

    // Check or process num

}
```

### 2D Traversal

```java
for (int[] row : matrix) {

    for (int value : row) {

        // Check or process value

    }
}
```

The important idea is not memorizing the exact template.

It is understanding:

```text
Visit → Check → Process → Update
```

---

# 🔄 Linear Search vs Binary Search

| Feature             | Linear Search               | Binary Search              |
| ------------------- | --------------------------- | -------------------------- |
| Data must be sorted | ❌ No                        | ✅ Usually                  |
| Main idea           | Check elements sequentially | Eliminate half             |
| Typical time        | O(n)                        | O(log n)                   |
| Implementation      | Very simple                 | More careful               |
| Useful for          | General traversal           | Ordered / monotonic search |

### Important placement lesson

Do not use Binary Search just because you are "searching."

First ask:

> **Do I have ordering that lets me eliminate half the search space?**

If not, Linear Search or another traversal-based approach may be appropriate.

---

# 🧠 Pattern Connection

Linear Search is a foundation for many other DSA patterns.

```text id="tju6zc"
Linear Traversal
      ↓
Maintaining State
      ↓
     ┌──────────────┬──────────────┐
     ↓              ↓              ↓
   Counting       Maximum        Prefix Sum
     ↓              ↓              ↓
 Hashing         Greedy        Subarray Problems
```

The ability to make a clean single-pass solution is an important skill even when later problems become much more advanced.

---

# ⚠️ Common Mistakes

### 1. Wrong loop boundaries

Be careful with:

```java
i < nums.length
```

rather than:

```java
i <= nums.length
```

The latter can cause an index-out-of-bounds error.

---

### 2. Forgetting to update the result

Traversing the array is not enough.

You must update the required state while processing each element.

---

### 3. Incorrect initialization

For maximum/minimum problems, initialization matters.

For example:

```java
int max = 0;
```

may not always be correct if negative values are possible.

Choose an initialization consistent with the constraints.

---

### 4. Confusing nested-array dimensions

For a matrix:

```text
rows
 ↓
columns
```

make sure you understand which loop is processing which level.

---

### 5. Using a complex technique unnecessarily

A common beginner mistake is trying to force:

* Binary Search
* HashMap
* Sorting
* Recursion

onto a problem that can be solved with one straightforward traversal.

> **Use the simplest correct pattern that matches the problem.**

---

# ⏱️ Complexity

For a 1D array containing `n` elements:

```text
Time:  O(n)
Space: O(1)
```

because each element is processed at most once.

For a matrix containing `r × c` elements:

```text
Time:  O(r × c)
Space: O(1)
```

when only a few variables are used apart from the input.

---

# 🎯 Placement-Level Takeaway

When a problem looks simple, start with the simplest possible approach.

```text id="p3zz5k"
Can I solve it by scanning the data?
            ↓
           Yes
            ↓
What must I calculate?
            ↓
Choose a variable to maintain
            ↓
Traverse
            ↓
Check / Process
            ↓
Update Answer
```

Before searching for optimization, ask:

> **Is `O(n)` already sufficient for the given constraints?**

If yes, a simple linear traversal may be the intended solution.

---

# 🧠 Quick Revision Cheat Sheet

```text
╔══════════════════════════════════════════╗
║        🔍 LINEAR SEARCH CHEAT SHEET      ║
╠══════════════════════════════════════════╣
║ Pattern: Sequential Traversal            ║
║                                          ║
║ Trigger:                                 ║
║ No useful ordering + inspect elements    ║
║                                          ║
║ Core Idea:                               ║
║ Visit → Check → Process → Update         ║
║                                          ║
║ 1D Time: O(n)                            ║
║ 2D Time: O(rows × columns)               ║
║ Extra Space: O(1)                        ║
╚══════════════════════════════════════════╝
```

> **⭐ One sentence to remember:**
> **When there is no useful ordering to exploit, scan the data once and maintain exactly the information needed for the answer.**

---

# 📂 Folder Structure

```text
Linear Search/
│
├── 1295. Find Numbers with Even Number of Digits/
│   ├── Solution.java
│   └── readme.md
│
├── 1672. Richest Customer Wealth/
│   ├── Solution.java
│   └── readme.md
│
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
