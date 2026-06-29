# Cyclic Sort

## 📖 Overview

Cyclic Sort is an in-place sorting algorithm designed for arrays containing numbers in the range **1 to N**. Instead of comparing adjacent elements, it places each element directly into its correct position.

The correct index of a value `x` is:

```
x - 1
```

This algorithm is extremely efficient because each element is moved to its correct position at most once.

---

## 🎯 When to Use

Use Cyclic Sort when:

* The array contains numbers from **1 to N**.
* Every number should appear once (or interview variations with duplicates/missing numbers).
* You need **O(n)** time complexity.
* You need **O(1)** extra space.

---

## 💡 Core Idea

1. Start from the first index.
2. Find the correct index of the current element.
3. If the element is not in its correct position, swap it with the element at its correct index.
4. Otherwise, move to the next index.
5. Repeat until the entire array is sorted.

---

## 🧠 Algorithm

1. Initialize `i = 0`.
2. While `i < n`:

   * Find the correct index: `correct = arr[i] - 1`.
   * If the current element is not at its correct position, swap it.
   * Otherwise, increment `i`.
3. The array is now sorted.

---

## ⏱️ Complexity

| Operation | Complexity |
| --------- | ---------- |
| Time      | **O(n)**   |
| Space     | **O(1)**   |

---

## 📚 Interview Applications

The Cyclic Sort pattern is commonly used in interview problems such as:

* Find Missing Number
* Find All Missing Numbers
* Find the Duplicate Number
* Find All Duplicate Numbers
* First Missing Positive
* Set Mismatch
* Find Corrupt Pair

---

## ⚠️ Important Notes

* Works best when numbers are in the range **1 to N**.
* Increment the index **only when the current element is already in its correct position**.
* Otherwise, keep swapping until the correct element is placed at the current index.

---

## 📝 Quick Revision

* ✅ Correct Index = `value - 1`
* ✅ Swap until the current element reaches its correct position.
* ✅ Increment the index only when no swap is needed.
* ✅ Time Complexity: **O(n)**
* ✅ Space Complexity: **O(1)**

---

## 🚀 Interview Tip

Whenever you see a problem involving:

* Numbers from **1 to N**
* Missing numbers
* Duplicate numbers
* Corrupt or misplaced numbers
* Requirement of **O(n)** time and **O(1)** space

👉 **Think of the Cyclic Sort pattern first.**
