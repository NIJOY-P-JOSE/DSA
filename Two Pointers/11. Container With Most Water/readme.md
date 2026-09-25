# [11. Container With Most Water](https://leetcode.com/problems/container-with-most-water/)

**Difficulty:** Medium
**Primary Language:** Java
**Pattern:** Two Pointers
**Related Patterns:** Greedy · Array Traversal

---

## ⚡ Quick Revision

|                      |                                                                      |
| -------------------- | -------------------------------------------------------------------- |
| **Goal**             | Find the maximum water that can be stored between two vertical lines |
| **Pattern**          | Two Pointers                                                         |
| **Pointers**         | `left` at beginning, `right` at end                                  |
| **Width**            | `right - left`                                                       |
| **Height**           | `min(height[left], height[right])`                                   |
| **Area**             | `width × height`                                                     |
| **Pointer Movement** | Move the shorter line                                                |
| **Time**             | `O(n)`                                                               |
| **Space**            | `O(1)`                                                               |

### Core Formula

```text id="l1t3nq"
Area = Width × Height

Width  = right - left
Height = min(height[left], height[right])
```

---

## 🧠 Problem in Simple Words

We are given an array where each value represents the height of a vertical line.

For example:

```text id="k3q5b7"
height = [1, 8, 6, 2, 5, 4, 8, 3, 7]
```

Choose **two lines** that can hold the maximum amount of water.

The amount of water depends on:

1. The distance between the two lines.
2. The height of the **shorter** line.

Why the shorter line?

Because water would overflow over the shorter side.

So:

```text id="9w9f5x"
Water = width × shorter height
```

---

## 🔥 How to Recognize This Problem in a Placement

Look for:

* An array representing heights.
* Choose two positions.
* Need to maximize an area.
* Area depends on the distance between positions.
* The smaller of two heights limits the result.

### Recognition Trigger

> **Two boundaries + maximize area + shorter boundary limits the result → Think Two Pointers.**

A brute-force approach would try every pair:

```text id="o7d1qz"
for every left
    for every right
```

That gives:

```text id="9d4p5s"
O(n²)
```

The two-pointer approach reduces this to:

```text id="r0q4gj"
O(n)
```

---

## 🧩 How to Think / Derive the Solution

### Step 1: Start with the widest possible container

Put:

```text id="6x6p5r"
left = 0
right = n - 1
```

This gives us the maximum possible width.

```text id="6v2vqb"
left →                         ← right
```

Calculate:

```text id="5lyr4g"
width = right - left
height = min(height[left], height[right])
area = width × height
```

---

### Step 2: Why can't we keep the wider container?

Suppose:

```text id="y6e5rx"
left height  = 8
right height = 3
```

The container can only hold water up to height `3`.

```text
       8
       |
       |       3
       |       |
       |~~~~~~~|
       |~~~~~~~|
_______|_______|
   left       right
```

The limiting height is:

```text id="9gk9go"
3
```

Now, if we move the **left** pointer:

* Width becomes smaller.
* Right height is still `3`.
* Therefore the new container still cannot have a height greater than `3`.

So moving the left pointer cannot produce a better container from this pair.

Therefore we move the shorter side:

```text id="5c0e2k"
right--
```

---

## ⭐ The Most Important Idea

> **Always move the pointer at the shorter height.**

Why?

Because the shorter height is the bottleneck.

### If left is shorter:

```text id="j5i1cj"
height[left] < height[right]

→ left++
```

We need a chance to find a taller left boundary.

### If right is shorter:

```text id="f6p7eo"
height[right] < height[left]

→ right--
```

We need a chance to find a taller right boundary.

### If both are equal:

Either pointer can move.

---

## 📊 Visual Explanation

Consider:

```text id="zj0a3j"
height = [1,8,6,2,5,4,8,3,7]
```

Initially:

```text
1  8  6  2  5  4  8  3  7
↑                             ↑
L                             R
```

Width:

```text id="0n5qjh"
8
```

Height:

```text id="u1f2td"
min(1, 7) = 1
```

Area:

```text id="2w6jyu"
8 × 1 = 8
```

Left is shorter:

```text id="w8j9xn"
1 < 7
```

So:

```text id="i0e9w6"
left++
```

---

Now:

```text
1  8  6  2  5  4  8  3  7
   ↑                          ↑
   L                          R
```

Width:

```text id="v5m7ey"
7
```

Height:

```text id="v5c2v8"
min(8, 7) = 7
```

Area:

```text id="8e9n9n"
7 × 7 = 49
```

This gives:

```text id="j7h5l2"
maximum = 49
```

---

## 🔁 Algorithm

1. Set `left = 0`.
2. Set `right = n - 1`.
3. Set `maxWater = 0`.
4. While `left < right`:

   * Calculate the width.
   * Find the shorter height.
   * Calculate the current area.
   * Update `maxWater`.
   * Move the pointer with the shorter height.
5. Return `maxWater`.

---

## 💻 My Solution

A cleaner version of your solution with more descriptive variable names:

```java id="q4g1x8"
class Solution {
    public int maxArea(int[] height) {

        int left = 0;
        int right = height.length - 1;
        int maxWater = 0;

        while (left < right) {

            int width = right - left;
            int containerHeight = Math.min(height[left], height[right]);
            int currentArea = width * containerHeight;

            maxWater = Math.max(maxWater, currentArea);

            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }

        return maxWater;
    }
}
```

### Why I prefer this version

Your original code:

```java
int area = (right-left) * Math.min(height[left],height[right]);
```

is completely correct.

But separating the calculations makes the reasoning much easier to read:

```java
int width = right - left;
int containerHeight = Math.min(height[left], height[right]);
int currentArea = width * containerHeight;
```

Now the code directly matches the formula:

```text id="x5h9d1"
Area = Width × Container Height
```

---

## 🧠 Code Explanation

### Two pointers

```java id="g8k4l0"
int left = 0;
int right = height.length - 1;
```

Start with the two farthest lines.

This gives the maximum possible width.

---

### Calculate width

```java id="p5k2yx"
int width = right - left;
```

The distance between the two lines is the width of the container.

---

### Find the limiting height

```java id="w5q1vk"
int containerHeight = Math.min(height[left], height[right]);
```

The shorter line determines how much water can be stored.

---

### Calculate area

```java id="j2m8zq"
int currentArea = width * containerHeight;
```

The formula is:

```text id="7z7q3v"
Area = width × shorter height
```

---

### Update maximum

```java id="0m5g8x"
maxWater = Math.max(maxWater, currentArea);
```

Keep the largest area found so far.

---

### Move the shorter pointer

```java id="g7s8x4"
if (height[left] < height[right]) {
    left++;
} else {
    right--;
}
```

This is the heart of the problem.

The shorter side limits the current container.

So we discard that side and search for a potentially taller boundary.

---

## 🧠 Pattern Connection

This problem connects directly with the previous two-pointer problems.

### 977. Squares of a Sorted Array

```text
Two pointers
     ↓
Compare both ends
     ↓
Choose appropriate side
```

### 167. Two Sum II

```text
Two pointers
     ↓
Calculate sum
     ↓
Move pointer based on target
```

```text
sum > target → right--
sum < target → left++
```

### 125. Valid Palindrome

```text
Two pointers
     ↓
Compare both ends
     ↓
Move both inward
```

### 11. Container With Most Water

```text
Two pointers
     ↓
Calculate area
     ↓
Move the shorter side
```

The important progression is:

> **Two pointers are not just "left and right variables." The important part is knowing what information determines which pointer should move.**

---

## ⚔️ Similar Problems / Variations

Continue connecting this pattern with:

* **167. Two Sum II** → Move pointer based on sum
* **125. Valid Palindrome** → Compare from both ends
* **977. Squares of a Sorted Array** → Compare absolute values
* **15. 3Sum** → Sorting + two pointers
* **26. Remove Duplicates from Sorted Array** → Read/write pointers

---

## 🚨 Common Mistakes

### 1. Moving the taller pointer

Wrong idea:

```text id="j8n2c7"
Move the taller side
```

Correct:

```text id="v7w3b9"
Move the shorter side
```

---

### 2. Using the taller height for the area

Wrong:

```text id="e1q6h2"
area = width × max(height[left], height[right])
```

Correct:

```text id="k8g5w3"
area = width × min(height[left], height[right])
```

---

### 3. Forgetting that width decreases

Every time we move a pointer:

```text id="q4s9m1"
width decreases
```

Therefore, to find a better area after moving, we need the possibility of finding a **taller limiting height**.

This is why we move the shorter side.

---

### 4. Using brute force

Checking every pair:

```java id="z2p4t8"
for (int i = 0; i < n; i++) {
    for (int j = i + 1; j < n; j++) {
        ...
    }
}
```

takes:

```text id="q9k1b4"
O(n²)
```

With `n = 100,000`, this is too expensive.

---

## ⏱️ Complexity

### Time Complexity

```text id="4m7p8x"
O(n)
```

Each pointer only moves toward the other pointer.

Together, they make at most `n - 1` pointer movements.

### Space Complexity

```text id="7x2q5m"
O(1)
```

Only a few variables are used.

No extra array or data structure is required.

---

## 🎯 Placement-Level Takeaway

When you see:

```text id="q4c8m1"
Array of heights
+
Choose two positions
+
Maximize area
```

Think:

```text id="w7p2n5"
Two Pointers
```

Immediately remember:

```text id="f5x9k2"
width = right - left

height = min(height[left], height[right])

area = width × height

if left is shorter:
    left++

else:
    right--
```

### The key reasoning

> **The shorter line is the bottleneck. Move it because keeping it while reducing the width cannot improve the current height limit.**

This is the most important lesson from this problem.

---

## ⚡ Final 30-Second Cheat Sheet

```text id="s8j2m4"
Pattern:
Two Pointers

Start:
left = 0
right = n - 1

Formula:
width = right - left

height:
min(height[left], height[right])

area:
width × height

Update:
maxWater = max(maxWater, area)

Pointer:
shorter side → move it

Time:
O(n)

Space:
O(1)
```

---

## 📌 Repository Placement

```text
DSA/
└── Two Pointers/
    └── 11. Container With Most Water/
        ├── README.md
        └── Solution.java
```

---

**Nijoy P Jose**

This solution is part of my **Data Structures & Algorithms** placement preparation repository, where I document problem-solving patterns, interview techniques, and Java implementations to strengthen my coding skills.
