# 🔍 2D Matrix Binary Search That Like A 1D Sorted Array 

This repository contains my custom implementation of searching an element in a **fully sorted 2D matrix** using a modified binary search approach.

---

## 📌 Problem Statement

Given a 2D matrix where:

* Each row is sorted in ascending order
* The first element of each row is greater than the last element of the previous row

👉 The matrix behaves like a **flattened sorted 1D array**

Example:

```
1   2   3   4
5   6   7   8
9  10  11  12
```

---

## 💡 Approach Used

Instead of directly treating the matrix as a 1D array, this implementation uses a **two-phase binary search approach**:

### 🔹 Step 1: Reduce Rows

* Select the middle column
* Apply binary search on rows
* Narrow down to 2 possible rows

### 🔹 Step 2: Search in Row

* Based on target value, determine correct quadrant
* Apply binary search on that row

---

## ⚙️ Time Complexity

* Row reduction: `O(log n)`
* Column search: `O(log m)`

👉 Total Complexity:

```
O(log n + log m)
```

---

## 🧠 Key Learning

* Understanding different types of sorted matrices is important
* Not all 2D matrix problems can be solved using the same approach
* This method works best when the matrix is **strictly sorted like a flattened array**

---

## ⚠️ Limitations

This approach **only works when**:

* The matrix is globally sorted (like LeetCode 74)
* ❌ It does NOT work for matrices sorted only row-wise and column-wise

---

## 🚀 Alternative Approach

A simpler and more standard method is:

* Treat matrix as 1D array
* Apply binary search using index mapping

---

## 💻 Code

```java
// Your code here
```

---

## 🙌 Author

Nijoy P Jose
BTech CSE Student | DSA & Placement Preparation

---

## ⭐ Notes

This implementation is part of my DSA practice and helps strengthen understanding of **binary search patterns in 2D structures**.
