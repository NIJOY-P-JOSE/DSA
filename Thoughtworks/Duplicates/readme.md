# Duplicates

> **Pattern:** HashSet · Duplicate Detection · Frequency Tracking
> **Difficulty:** Easy
> **Placement Level:** Basic Coding Round
> **Platform:** Practice Problem / Thoughtworks Preparation

---

# ⚡ Quick Revision

| Concept         | Remember                                                     |
| --------------- | ------------------------------------------------------------ |
| Goal            | Count how many duplicate emails/roll numbers must be deleted |
| Pattern         | HashSet                                                      |
| Key Idea        | Store values that have already appeared                      |
| Duplicate Check | If value is already in HashSet → duplicate                   |
| Action          | Increment count for every repeated occurrence                |
| Time            | O(n)                                                         |
| Space           | O(n)                                                         |

---

# 🧠 Problem in Simple Words

We are given a list of student roll numbers.

Each roll number represents an email sender.

The principal wants to receive only **one email from each student**.

If the same roll number appears again, that email is a duplicate and must be deleted.

Our task is to find:

> **How many duplicate emails must be deleted?**

### Example

```text
6
1 3 3 4 3 3
```

The roll numbers are:

```text
1 → first occurrence → keep
3 → first occurrence → keep
3 → already appeared → delete
4 → first occurrence → keep
3 → already appeared → delete
3 → already appeared → delete
```

Therefore:

```text
Duplicate emails = 3
```

---

# 🔥 How to Recognize This Problem in a Placement

When you see words such as:

* Duplicate
* Repeated
* Unique
* Already exists
* Seen before
* Count repeated elements

you should immediately think:

```text
Can I use a HashSet?
```

A HashSet is useful when we need to quickly answer:

> **Have I seen this value before?**

### Recognition Trigger

```text
Duplicate / Unique / Seen Before
            ↓
      Need fast lookup
            ↓
         HashSet
```

---

# 🧩 How to Think / Derive the Solution

Let's derive the solution instead of memorizing it.

## Step 1: What do we need to know?

For every roll number, we need to know:

> Has this roll number already appeared?

For example:

```text
1  3  3  4  3
```

When we reach the second `3`, we need to remember that `3` already appeared earlier.

So we need some storage for previously seen numbers.

---

## Step 2: What data structure should we use?

We need two operations:

```text
1. Check whether a number already exists.
2. Add a new number.
```

A `HashSet` is perfect for this.

```text
HashSet<Integer> seen
```

---

## Step 3: Process every number

For each number:

```text
Is it already in the HashSet?
```

### If YES

It is a duplicate.

```text
count++
```

### If NO

It is the first occurrence.

```text
Add it to the HashSet.
```

---

# 📊 Visual Explanation

Consider:

```text
Input: 1 3 3 4 3 3
```

| Current Number | HashSet Before | Action                  | Duplicate Count |
| -------------- | -------------- | ----------------------- | --------------- |
| 1              | `{}`           | Add 1                   | 0               |
| 3              | `{1}`          | Add 3                   | 0               |
| 3              | `{1, 3}`       | Already exists → Delete | 1               |
| 4              | `{1, 3}`       | Add 4                   | 1               |
| 3              | `{1, 3, 4}`    | Already exists → Delete | 2               |
| 3              | `{1, 3, 4}`    | Already exists → Delete | 3               |

Final result:

```text
3 duplicate emails
```

---

# 🔁 Algorithm

1. Create an empty `HashSet`.
2. Initialize `count = 0`.
3. Traverse every roll number.
4. Check whether it already exists in the HashSet.
5. If it exists:

   * Increment `count`.
6. Otherwise:

   * Add it to the HashSet.
7. Return or print `count`.

---

# 💻 My Java Solution

```java
import java.util.*;

class Main {
    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);

        int n = s.nextInt();

        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = s.nextInt();
        }

        HashSet<Integer> hs = new HashSet<>();

        int c = 0;

        for (int x : arr) {

            if (hs.contains(x)) {
                c++;
            } else {
                hs.add(x);
            }
        }

        System.out.println(c);
    }
}
```

---

## Code → What It Does → Why It Works

### Reading the input

```java
int n = s.nextInt();
```

Reads the number of roll numbers.

---

### Creating the array

```java
int[] arr = new int[n];
```

Stores all roll numbers.

---

### Creating the HashSet

```java
HashSet<Integer> hs = new HashSet<>();
```

The HashSet stores every roll number that has appeared for the first time.

---

### Duplicate counter

```java
int c = 0;
```

Stores the number of emails that must be deleted.

---

### Checking every roll number

```java
for (int x : arr)
```

We process each roll number one by one.

---

### Duplicate check

```java
if (hs.contains(x))
```

If `x` is already inside the HashSet, we have already seen this student.

Therefore:

```java
c++;
```

This email must be deleted.

---

### First occurrence

```java
else {
    hs.add(x);
}
```

If the roll number has not appeared before, we store it.

---

# 🐍 Python Version

Your Python approach follows exactly the same logic.

```python
n = int(input())

seen = []
count = 0

for _ in range(n):
    x = int(input())

    if x in seen:
        count += 1
    else:
        seen.append(x)

print(count)
```

However, for placement coding, a `set` is better than a list:

```python
n = int(input())

seen = set()
count = 0

for _ in range(n):
    x = int(input())

    if x in seen:
        count += 1
    else:
        seen.add(x)

print(count)
```

### Why?

```text
List lookup:
x in list → O(n)

Set lookup:
x in set → O(1) average
```

So for large input, `set` is the better choice.

---

# 🧠 Pattern Connection

This problem belongs to the following mental pattern:

```text
Array / List
      ↓
Need to know whether an element appeared before
      ↓
Fast lookup required
      ↓
HashSet
      ↓
Duplicate Detection
```

This is a basic pattern that later appears in more difficult problems.

For example:

```text
Contains Duplicate
        ↓
Duplicate Detection
        ↓
HashSet
        ↓
Longest Consecutive Sequence
        ↓
HashSet + Sequence Expansion
```

So this simple problem helps build an important foundation.

---

# ⚔️ Similar Problems / Variations

| Problem Type                 | Difference                                          |
| ---------------------------- | --------------------------------------------------- |
| Contains Duplicate           | Only check whether at least one duplicate exists    |
| First Unique Character       | Find an element that appears exactly once           |
| Valid Anagram                | Compare character frequencies                       |
| Two Sum                      | Use HashMap to find a required complement           |
| Longest Consecutive Sequence | Use HashSet to efficiently find sequences           |
| Frequency Counting           | Use HashMap when you need the number of occurrences |

---

# 🚨 Common Mistakes

## 1. Using nested loops

A beginner might compare every element with every other element.

```text
for every i
    for every j
        compare
```

This takes:

```text
O(n²)
```

A HashSet reduces the average lookup time significantly.

---

## 2. Adding before checking

Wrong thinking:

```text
Add x
Then check if x exists
```

After adding, it will obviously exist.

Correct order:

```text
Check
↓
If new → Add
If already exists → Count duplicate
```

---

## 3. Counting the first occurrence as a duplicate

The first occurrence should always be kept.

Example:

```text
3 3 3 3
```

Emails to delete:

```text
First 3 → keep
Remaining 3 → delete
```

Answer:

```text
3
```

Not `4`.

---

## 4. Using a List instead of a Set in Python

This works:

```python
if x in seen_list:
```

But searching a list takes:

```text
O(n)
```

For placement coding, use:

```python
seen = set()
```

when the main operation is checking membership.

---

# ⏱️ Complexity

## Time Complexity

```text
O(n)
```

We traverse all `n` elements once.

HashSet operations such as:

```text
contains()
add()
```

take **O(1) average time**.

Therefore:

```text
O(n) × O(1) = O(n)
```

---

## Space Complexity

```text
O(n)
```

In the worst case, every roll number is unique, so all `n` numbers are stored in the HashSet.

---

# 🎯 Placement-Level Takeaway

### ⭐ If you see this in a placement round...

```text
Problem mentions duplicates
          ↓
Need to know if element appeared before
          ↓
Need fast lookup
          ↓
Use HashSet
          ↓
Already exists?
    ↙           ↘
  Yes           No
   ↓             ↓
Count          Add to Set
Duplicate
```

---

# ⚡ Final 30-Second Cheat Sheet

```text
╔══════════════════════════════════════════════╗
║               QUICK REVISION                 ║
╠══════════════════════════════════════════════╣
║ Pattern: HashSet                             ║
║ Goal: Count repeated elements                ║
║ Key idea: Store elements already seen        ║
║ Check: contains(x)                           ║
║ Seen before: count++                         ║
║ New element: add(x)                          ║
║ Recognition: Duplicate / Unique / Seen       ║
║ Time: O(n)                                   ║
║ Space: O(n)                                  ║
╚══════════════════════════════════════════════╝
```

> **⭐ One sentence to remember:** If a problem asks whether something has appeared before, think about using a HashSet for fast lookup.

---

**Nijoy P Jose**

This solution is part of my **Data Structures & Algorithms** placement preparation repository, where I document problem-solving patterns, interview techniques, and implementations to strengthen my coding skills for placement coding rounds and software engineering interviews.
