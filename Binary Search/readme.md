# 🔎 Binary Search

> A structured collection of **Binary Search problems, patterns, variations, and solutions** practiced for DSA and software engineering placement preparation.

[![Topic](https://img.shields.io/badge/DSA-Binary%20Search-blue)](https://github.com/NIJOY-P-JOSE/DSA/tree/main/Binary%20Search)
[![Language](https://img.shields.io/badge/Java-Solutions-red?logo=openjdk)](https://www.java.com/)

---

## 📌 About

This folder contains my practice and study material for **Binary Search** and its important variations.

The purpose is not simply to memorize the standard binary search template.

The goal is to understand:

```text
Search Space
     ↓
Identify the Condition
     ↓
Eliminate Half of the Search Space
     ↓
Repeat
     ↓
Find the Answer
```

Binary Search is treated here as a **problem-solving pattern**, not just an algorithm for searching a sorted array.

---

# 🎯 What I Am Learning

This section focuses on recognizing different forms of Binary Search:

* Standard Binary Search
* Lower/upper-bound style searches
* Boundary finding
* Search in rotated sorted arrays
* Binary Search on the answer
* Peak finding
* Binary Search in matrices
* Monotonic search conditions

The main skill is learning to answer:

> **What is my search space, and why can I safely eliminate half of it?**

---

# 📚 Problems Covered

## 1. Basic Binary Search

| Problem                                 | Main Concept           |
| --------------------------------------- | ---------------------- |
| [704. Binary Search](./Binary%20Search) | Standard binary search |

**Core idea:**

```text
Sorted Search Space
        ↓
Check Middle
        ↓
Target < Mid → Search Left
Target > Mid → Search Right
```

---

# 2. Boundary & Position Searching

| Problem                                                                                                                                          | Main Concept                                      |
| ------------------------------------------------------------------------------------------------------------------------------------------------ | ------------------------------------------------- |
| [35. Search Insert Position](./35.%20Search%20Insert%20Position)                                                                                 | Find target or insertion position                 |
| [34. Find First and Last Position of Element in Sorted Array](./34.%20Find%20First%20and%20Last%20Position%20of%20Element%20in%20Sorted%20Array) | Find leftmost and rightmost occurrence            |
| [744. Find Smallest Letter Greater Than Target](./744.%20Find%20Smallest%20Letter%20Greater%20Than%20Target)                                     | Find the first valid element                      |
| [1539. Kth Missing Positive Number](./1539.%20Kth%20Missing%20Positive%20Number)                                                                 | Binary search using a derived monotonic condition |

These problems move beyond simply asking:

> "Does the target exist?"

and introduce:

> **"Where is the first/last/next valid position?"**

---

# 3. Binary Search on Special Structures

| Problem                                                                                      | Main Concept                        |
| -------------------------------------------------------------------------------------------- | ----------------------------------- |
| [33. Search in Rotated Sorted Array](./33.%20Search%20in%20Rotated%20Sorted%20Array)         | Search in a rotated sorted array    |
| [81. Search in Rotated Sorted Array II](./81.%20Search%20in%20Rotated%20Sorted%20Array%20II) | Rotated array with duplicates       |
| [162. Find Peak Element](./162.%20Find%20Peak%20Element)                                     | Binary search using slope/direction |
| [852. Peak Index in a Mountain Array](./852.%20Peak%20Index%20in%20a%20Mountain%20Array)     | Find peak in a mountain array       |

These problems are important because the array may not look completely sorted.

Instead, we look for a **property that tells us which half can be discarded**.

---

# 4. Binary Search on the Answer

This is one of the most important Binary Search patterns for placement problems.

| Problem                                                                            | Main Concept                                |
| ---------------------------------------------------------------------------------- | ------------------------------------------- |
| [69. Sqrt(x)](./69.%20Sqrt%28x%29)                                                 | Search for the largest valid value          |
| [367. Valid Perfect Square](./367.%20Valid%20Perfect%20Square)                     | Search over possible square roots           |
| [374. Guess Number Higher or Lower](./374.%20Guess%20Number%20Higher%20or%20Lower) | Search over a numeric range                 |
| [410. Split Array Largest Sum](./410.%20Split%20Array%20Largest%20Sum)             | Binary Search on answer + greedy validation |
| [441. Arranging Coins](./441.%20Arranging%20Coins)                                 | Search for maximum feasible value           |

### Important pattern

```text
Possible Answers
      ↓
Check whether an answer is feasible
      ↓
Feasible?
  ↙       ↘
Yes       No
 ↓         ↓
Keep      Reject
 ↓
Binary Search
```

The key idea is:

> **When the answer lies inside a numeric range and feasibility changes monotonically, Binary Search may be possible even when the input array is not the thing being searched.**

---

# 5. Binary Search in 2D Arrays

| Problem                                                                                                            | Main Concept                                                 |
| ------------------------------------------------------------------------------------------------------------------ | ------------------------------------------------------------ |
| [Binary Search in 2D Array](./Binary%20Search%20in%202D%20Array)                                                   | Applying binary-search ideas to matrices                     |
| [1351. Count Negative Numbers in a Sorted Matrix](./1351.%20Count%20Negative%20Numbers%20in%20a%20Sorted%20Matrix) | Search for the boundary between positive and negative values |

This section helps extend Binary Search thinking from:

```text
1D Array
```

to:

```text
2D Matrix
```

The important concept remains the same:

> **Use ordering or monotonicity to eliminate unnecessary search space.**

---

# 🧠 Binary Search Pattern Map

```text
                    Binary Search
                         │
          ┌──────────────┼──────────────┐
          │              │              │
      Sorted Array    Boundaries    Answer Search
          │              │              │
       Search         First/Last      Feasibility
       Insert         Position         Check
          │              │              │
          └──────┬───────┴───────┬──────┘
                 │               │
           Rotated Array       Peak Finding
                 │               │
                 └───────┬───────┘
                         │
                    2D Search
```

---

# 🔥 How to Recognize Binary Search

Do not wait for the problem to explicitly say:

> "Use Binary Search."

Look for these clues.

### 1. The data is sorted

```text
[1, 4, 7, 10, 15, 20]
```

This is the most obvious Binary Search trigger.

---

### 2. The problem asks for a boundary

Examples:

```text
First occurrence
Last occurrence
First greater element
First valid position
Insertion position
```

Think:

> **Binary Search for the boundary.**

---

### 3. The answer lies in a numeric range

For example:

```text
Possible answer = 1 ... X
```

Then ask:

> **Can I check whether a candidate answer is valid?**

If yes, investigate **Binary Search on Answer**.

---

### 4. The problem has a monotonic condition

A typical structure is:

```text
False False False False True True True
                     ↑
                 Find boundary
```

or:

```text
Possible
Possible
Possible
Impossible
Impossible
Impossible
```

The transition point can often be found using Binary Search.

---

# 🧩 How to Derive Binary Search

When facing an unfamiliar problem, ask these questions:

### Step 1 — What is the search space?

It could be:

```text
Array indices
```

or:

```text
Possible answer values
```

---

### Step 2 — What does `mid` represent?

For example:

```text
mid = middle index
```

or:

```text
mid = candidate answer
```

This is extremely important.

---

### Step 3 — Can I decide which half is useless?

Ask:

```text
Does the condition tell me
that everything on one side
cannot contain the answer?
```

If yes, Binary Search may apply.

---

### Step 4 — What property is monotonic?

Look for something like:

```text
False → False → False → True → True → True
```

or:

```text
True → True → True → False → False → False
```

That transition is what allows the search space to shrink.

---

# ⚔️ Binary Search Variations

| Variation             | Main Question                                     |
| --------------------- | ------------------------------------------------- |
| Standard Search       | Does the target exist?                            |
| Lower Bound           | What is the first valid position?                 |
| Upper Bound           | What is the first position after the valid range? |
| First/Last Occurrence | Where does the target begin/end?                  |
| Rotated Array         | Which half is sorted?                             |
| Peak Search           | Which direction leads toward a peak?              |
| Answer Search         | Is this candidate answer feasible?                |
| Matrix Search         | How can ordering reduce the 2D space?             |

---

# 💻 Standard Template

```java
int left = 0;
int right = nums.length - 1;

while (left <= right) {

    int mid = left + (right - left) / 2;

    if (nums[mid] == target) {
        return mid;
    }
    else if (nums[mid] < target) {
        left = mid + 1;
    }
    else {
        right = mid - 1;
    }
}

return -1;
```

### Why use:

```java
left + (right - left) / 2
```

instead of:

```java
(left + right) / 2
```

The first form avoids possible integer overflow when indices are very large.

---

# 🚨 Common Binary Search Mistakes

Binary Search is simple to understand but easy to implement incorrectly.

### 1. Wrong loop condition

```java
while (left <= right)
```

and

```java
while (left < right)
```

are not interchangeable.

The correct condition depends on what your search interval represents.

---

### 2. Forgetting to eliminate `mid`

Incorrect:

```java
left = mid;
```

or:

```java
right = mid;
```

can cause an infinite loop.

Usually the search must move beyond the current midpoint:

```java
left = mid + 1;
right = mid - 1;
```

---

### 3. Off-by-one errors

Especially common when finding:

* First occurrence
* Last occurrence
* First greater element
* Insertion position

---

### 4. Not defining the search interval

Before coding, decide:

```text
[left, right]
```

means what?

For example:

> "Every possible answer is currently inside this interval."

If that meaning is unclear, the implementation becomes difficult to reason about.

---

### 5. Using Binary Search without monotonicity

Not every problem involving numbers can use Binary Search.

You need some ordering or monotonic property that lets you safely discard part of the search space.

---

# ⏱️ Complexity

For standard Binary Search:

```text
Time:  O(log n)
Space: O(1)
```

Why?

Each iteration removes approximately half of the remaining search space.

```text
n
↓
n/2
↓
n/4
↓
n/8
↓
...
↓
1
```

Therefore the number of iterations grows logarithmically.

For Binary Search on Answer, the complexity is generally:

```text
O(log(answer_range) × cost_of_feasibility_check)
```

This distinction is especially important for problems such as **Split Array Largest Sum**.

---

# 🎯 Placement-Focused Learning Order

A useful progression through this folder is:

```text
704. Binary Search
        ↓
35. Search Insert Position
        ↓
34. First and Last Position
        ↓
744. Smallest Letter Greater Than Target
        ↓
69. Sqrt(x)
        ↓
367. Valid Perfect Square
        ↓
374. Guess Number Higher or Lower
        ↓
162. Find Peak Element
        ↓
852. Peak Index in a Mountain Array
        ↓
33. Search in Rotated Sorted Array
        ↓
81. Search in Rotated Sorted Array II
        ↓
1539. Kth Missing Positive Number
        ↓
410. Split Array Largest Sum
        ↓
Binary Search in 2D Array
        ↓
1351. Count Negative Numbers in a Sorted Matrix
```

The exact order is less important than understanding the progression:

```text
Basic Search
     ↓
Boundaries
     ↓
Special Structures
     ↓
Rotated Arrays
     ↓
Peak Problems
     ↓
Answer-Space Search
     ↓
2D Search
```

---

# ⭐ Most Important Concepts

Before moving away from Binary Search, I should be comfortable with:

* Standard Binary Search
* Search boundaries
* First/last occurrence
* Lower-bound thinking
* Rotated sorted arrays
* Peak finding
* Monotonic functions
* Binary Search on Answer
* Feasibility checks
* Binary Search in matrices
* Correct handling of `left`, `right`, and `mid`

---

# 🧠 Quick Recognition Cheat Sheet

```text
┌──────────────────────────────────────────┐
│        🔎 BINARY SEARCH CHEAT SHEET      │
├──────────────────────────────────────────┤
│ Sorted array?          → Binary Search   │
│ Find first/last?       → Boundary BS    │
│ Rotated sorted array?  → Modified BS    │
│ Find peak?             → Direction BS   │
│ Numeric answer range?  → Answer BS      │
│ Feasibility check?     → Check monotonic│
│ 2D ordered matrix?     → Matrix BS      │
├──────────────────────────────────────────┤
│ Time: O(log n)                           │
│ Space: O(1)                              │
└──────────────────────────────────────────┘
```

> **⭐ One sentence to remember:**
> **Binary Search is not just about sorted arrays; it is about finding a monotonic property that lets you eliminate half of the search space.**

---

# 📂 Folder Structure

```text
Binary Search/
│
├── 704. Binary Search/
├── 35. Search Insert Position/
├── 34. Find First and Last Position/
├── 744. Find Smallest Letter Greater Than Target/
├── 1539. Kth Missing Positive Number/
│
├── 33. Search in Rotated Sorted Array/
├── 81. Search in Rotated Sorted Array II/
│
├── 162. Find Peak Element/
├── 852. Peak Index in a Mountain Array/
│
├── 69. Sqrt(x)/
├── 367. Valid Perfect Square/
├── 374. Guess Number Higher or Lower/
├── 441. Arranging Coins/
├── 410. Split Array Largest Sum/
│
├── Binary Search in 2D Array/
│   ├── 2D Matrix Binary Search
│   └── Row/Column Sorted Matrix Search
│
├── 1351. Count Negative Numbers in a Sorted Matrix/
│
└── readme.md
```

---

# 🚀 Why This Folder Matters

Binary Search is one of the most reusable DSA patterns because the same core idea appears in many different forms:

```text
Sorted Data
     +
Monotonic Property
     +
Search Space Reduction
     ↓
      Binary Search
```

The real objective is therefore not memorizing a single template.

It is learning to recognize **when half of the possibilities can be eliminated safely**.

---

# 👨‍💻 Author

**Nijoy P Jose**

B.Tech Computer Science Engineering Student

[GitHub](https://github.com/NIJOY-P-JOSE)

[DSA Repository](https://github.com/NIJOY-P-JOSE/DSA)

---

⭐ Part of my **Data Structures & Algorithms placement preparation journey**.
