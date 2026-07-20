# 350. Intersection of Two Arrays II using HashMap

This repository contains a Java solution for **LeetCode 350 - Intersection of Two Arrays II** using the **HashMap** data structure.

Unlike **Intersection of Two Arrays (LeetCode 349)**, this problem requires each element to appear in the result **as many times as it appears in both arrays**.

---

# 📌 Problem Statement

Given two integer arrays `nums1` and `nums2`, return an array containing their **intersection**.

Each element in the result should appear **the minimum number of times it occurs in both arrays**.

The order of the returned elements does **not** matter.

---

## Example 1

```text id="mjlwm1"
Input:
nums1 = [1,2,2,1]
nums2 = [2,2]

Output:
[2,2]
```

Explanation:

```text id="mjlwm2"
2 appears twice in both arrays.

So the answer contains two 2's.
```

---

## Example 2

```text id="mjlwm3"
Input:
nums1 = [4,9,5]
nums2 = [9,4,9,8,4]

Output:
[4,9]
```

Explanation:

```text id="mjlwm4"
4 appears once in nums1.

9 appears once in nums1.

So each appears only once in the answer.
```

---

# 💡 Intuition

Unlike the previous problem, **duplicates matter**.

Therefore, simply checking whether an element exists is not enough.

We need to know **how many times** each number appears.

A `HashMap` is perfect for this because it stores:

```text id="mjlwm5"
Number → Frequency
```

### Idea

1. Count the frequency of every element in `nums1`.
2. Traverse `nums2`.
3. If the current element exists in the map and its frequency is greater than zero:

   * Add it to the answer.
   * Decrease its frequency.
4. Return the collected elements.

---

# 🧠 HashMap Thinking

Suppose

```text id="mjlwm6"
nums1 = [1,2,2,1]
```

Frequency Map

```text id="mjlwm7"
1 → 2
2 → 2
```

Now traverse

```text id="mjlwm8"
nums2 = [2,2]
```

Current element

```text id="mjlwm9"
2
```

Frequency

```text id="mjlwm10"
2 → 2
```

↓

Add to answer

↓

Decrease count

```text id="mjlwm11"
2 → 1
```

---

Next element

```text id="mjlwm12"
2
```

↓

Frequency

```text id="mjlwm13"
2 → 1
```

↓

Add again

↓

Decrease

```text id="mjlwm14"
2 → 0
```

Final Answer

```text id="mjlwm15"
[2,2]
```

---

# 🔄 Algorithm

1. Create a HashMap.
2. Store the frequency of every element in `nums1`.
3. Traverse `nums2`.
4. For every element:

   * Check whether it exists in the map.
   * If its frequency is greater than zero:

     * Add it to the answer.
     * Decrease its frequency by one.
5. Convert the answer list into an array.
6. Return the array.

---

# 📖 Dry Run

Example

```text id="mjlwm16"
nums1 = [4,9,5]

nums2 = [9,4,9,8,4]
```

### Build Frequency Map

```text id="mjlwm17"
4 → 1

9 → 1

5 → 1
```

---

Traverse `nums2`

Current

```text id="mjlwm18"
9
```

Exists

↓

Answer

```text id="mjlwm19"
[9]
```

Map

```text id="mjlwm20"
9 → 0
```

---

Current

```text id="mjlwm21"
4
```

Exists

↓

Answer

```text id="mjlwm22"
[9,4]
```

Map

```text id="mjlwm23"
4 → 0
```

---

Current

```text id="mjlwm24"
9
```

Frequency

```text id="mjlwm25"
9 → 0
```

Cannot be used again.

Ignore.

---

Current

```text id="mjlwm26"
8
```

Not present.

Ignore.

---

Current

```text id="mjlwm27"
4
```

Frequency

```text id="mjlwm28"
4 → 0
```

Ignore.

Final Answer

```text id="mjlwm29"
[9,4]
```

---

# ⚠️ Common Mistakes

## ❌ Mistake 1

Using a `HashSet`.

A HashSet removes duplicates automatically.

Example

```text id="mjlwm30"
nums1 = [2,2]

nums2 = [2,2]
```

A HashSet returns

```text id="mjlwm31"
[2]
```

Correct answer

```text id="mjlwm32"
[2,2]
```

A HashMap is required because frequencies matter.

---

## ❌ Mistake 2

Adding the element without decreasing its frequency.

Wrong

```java id="mjlwm33"
ans.add(num);
```

Correct

```java id="mjlwm34"
ans.add(num);
map.put(num, map.get(num) - 1);
```

Otherwise the same occurrence can be counted multiple times.

---

## ❌ Mistake 3

Checking only

```java id="mjlwm35"
map.containsKey(num)
```

This is not enough.

The frequency might already be

```text id="mjlwm36"
0
```

Always check

```java id="mjlwm37"
map.get(num) > 0
```

---

## ❌ Mistake 4

Using nested loops.

Brute Force

```text id="mjlwm38"
O(n × m)
```

HashMap reduces it to

```text id="mjlwm39"
O(n + m)
```

---

# 📝 Interview Notes

### Why HashMap?

HashMap allows us to store the **frequency** of every element.

Whenever a common element is found,

```text id="mjlwm40"
Frequency--
```

This guarantees that every element is added exactly

```text id="mjlwm41"
min(freq(nums1), freq(nums2))
```

times.

---

### Follow-up 1

If both arrays are already sorted,

use the **Two Pointers** technique.

Time Complexity

```text id="mjlwm42"
O(n + m)
```

Space

```text id="mjlwm43"
O(1)
```

---

### Follow-up 2

If one array is much smaller,

build the HashMap from the **smaller array**.

This minimizes memory usage.

Space

```text id="mjlwm44"
O(min(n,m))
```

---

### Follow-up 3

If the second array is stored on disk,

keep only the frequency map of the smaller array in memory and process the larger array in **chunks (streaming)**.

---

# ⏱️ Complexity Analysis

Let

* `n = nums1.length`
* `m = nums2.length`

### Time Complexity

Building the frequency map

```text id="mjlwm45"
O(n)
```

Traversing the second array

```text id="mjlwm46"
O(m)
```

Overall

```text id="mjlwm47"
O(n + m)
```

---

### Space Complexity

```text id="mjlwm48"
O(n)
```

where `n` is the number of unique elements stored in the HashMap.

This can be optimized to

```text id="mjlwm49"
O(min(n,m))
```

by building the map from the smaller array.

---

# 💻 Java Solution

```java id="mjlwm50"
class Solution {

    public int[] intersect(int[] nums1, int[] nums2) {

        Map<Integer, Integer> freq = new HashMap<>();
        List<Integer> ans = new ArrayList<>();

        for (int num : nums1) {
            if (freq.containsKey(num))
                freq.put(num, freq.get(num) + 1);
            else
                freq.put(num, 1);
        }

        for (int num : nums2) {

            if (freq.containsKey(num) && freq.get(num) > 0) {

                freq.put(num, freq.get(num) - 1);
                ans.add(num);
            }
        }

        int[] res = new int[ans.size()];

        int i = 0;
        for (int num : ans)
            res[i++] = num;

        return res;
    }
}
```

---

# 🎯 Hashing Pattern

**Pattern Used:** Frequency Counting using HashMap

Instead of storing only whether an element exists, a HashMap stores **how many times** it appears.

This pattern is commonly used in problems involving:

* Duplicates
* Counting occurrences
* Character frequencies
* Inventory tracking
* Matching elements between collections

---

# 🔗 Related Problems

* 349. Intersection of Two Arrays
* 1. Two Sum
* 217. Contains Duplicate
* 219. Contains Duplicate II
* 242. Valid Anagram
* 128. Longest Consecutive Sequence

---

# 📚 Concepts Practiced

* HashMap
* Frequency Counting
* Duplicate Handling
* Array Traversal
* Dynamic Arrays (`ArrayList`)
* Time & Space Complexity Analysis

---

# 🚀 Author

**Nijoy P Jose**

This solution is part of my **Data Structures & Algorithms** placement preparation repository, where I document Hash Table patterns, interview techniques, and Java implementations to strengthen problem-solving skills.
