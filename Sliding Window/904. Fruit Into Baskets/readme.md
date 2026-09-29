# 904. Fruit Into Baskets

**Difficulty:** Medium
**Main Pattern:** Sliding Window
**Related Patterns:** HashMap, Frequency Counting, Two Pointers

🔗 [LeetCode – 904. Fruit Into Baskets](https://leetcode.com/problems/fruit-into-baskets/)

---

## ⚡ Quick Revision

| Concept        | Idea                                                                            |
| -------------- | ------------------------------------------------------------------------------- |
| Goal           | Find the longest contiguous subarray containing at most 2 different fruit types |
| Main Pattern   | Variable-Size Sliding Window                                                    |
| Data Structure | `HashMap<Integer, Integer>`                                                     |
| Map Value      | Frequency of each fruit inside the window                                       |
| Valid Window   | At most 2 distinct fruit types                                                  |
| Invalid Window | More than 2 distinct fruit types                                                |
| Expand         | Move `right`                                                                    |
| Shrink         | Move `left`                                                                     |
| Answer         | Maximum window length                                                           |
| Time           | `O(n)`                                                                          |
| Space          | `O(n)`                                                                          |

### 30-Second Idea

> **Maintain a window containing at most 2 different fruit types. Expand the window using `right`. When a third type appears, move `left` until the window becomes valid again. Keep track of the largest valid window.**

---

# 🧠 Problem in Simple Words

You have a row of trees:

```text
fruits = [1,2,3,2,2]
```

You have **2 baskets**.

Each basket can contain only **one type of fruit**.

So you can collect fruits from a continuous section of the array as long as that section contains **at most 2 different values**.

For example:

```text
[2,3,2,2]
```

contains:

```text
2 → type 1
3 → type 2
```

Only 2 types, so it is valid.

Its length is:

```text
4
```

Therefore the answer is:

```text
4
```

---

# 🔥 How to Recognize This Problem in a Placement

Look for phrases such as:

* longest contiguous section
* maximum number of elements
* at most `k` different values
* at most 2 distinct values
* consecutive elements
* maintain a range
* expand and shrink

These are strong signals for **Sliding Window**.

For this problem:

```text
Longest
+
Contiguous
+
At most 2 distinct values
```

suggests:

```text
Variable-Size Sliding Window
```

---

# 🧩 How to Think / Derive the Solution

## Step 1 — What does the problem really ask?

The original story talks about:

> Trees, baskets and fruits.

But the actual DSA problem is:

> **Find the longest contiguous subarray containing at most 2 distinct values.**

That is the important transformation.

---

## Step 2 — Why not brute force?

We could generate every possible subarray and count its fruit types.

For example:

```text
[1]
[1,2]
[1,2,3]
[1,2,3,2]
...
```

For every subarray, we would need to check how many different fruit types it contains.

This can become approximately:

```text
O(n²)
```

or worse depending on how the distinct types are counted.

We need something closer to:

```text
O(n)
```

---

# 🪟 Step 3 — Think About a Window

Instead of repeatedly creating subarrays, maintain one window:

```text
left ---------------- right
          window
```

`right` expands the window.

```text
left → → → → right
```

When the window becomes invalid, move `left` forward.

```text
left → → → right
```

This gives us the Sliding Window technique.

---

# 🧠 Step 4 — What Makes a Window Valid?

The window can contain at most **2 different fruit types**.

Therefore:

```text
map.size() <= 2
```

is valid.

And:

```text
map.size() > 2
```

is invalid.

---

# 🗂️ Step 5 — Why Do We Need a HashMap?

We need to know:

> How many times does each fruit type occur inside the current window?

For example:

```text
Window = [2,3,2,2]
```

The HashMap contains:

```text
2 → 3
3 → 1
```

So:

```text
map.size() = 2
```

The window is valid.

If we add `1`:

```text
Window = [2,3,2,2,1]
```

the map becomes:

```text
2 → 3
3 → 1
1 → 1
```

Now:

```text
map.size() = 3
```

The window is invalid.

---

# 🔄 Step 6 — How Do We Fix an Invalid Window?

When:

```java
map.size() > 2
```

we cannot simply throw away the entire window.

Instead, move `left` forward.

Remove the fruit at:

```java
fruits[left]
```

from the window.

But we must decrease its frequency first.

```java
map.put(fruits[left], map.get(fruits[left]) - 1);
```

If its frequency becomes zero:

```java
map.remove(fruits[left]);
```

Then:

```java
left++;
```

Continue until:

```text
map.size() <= 2
```

again.

---

# 📊 Visual Explanation

Consider:

```text
fruits = [1,2,3,2,2]
```

### Start

```text
left
 ↓
[1]
 ↑
right
```

Types:

```text
{1}
```

Valid.

---

### Expand

```text
left
 ↓
[1,2]
   ↑
 right
```

Types:

```text
{1,2}
```

Valid.

---

### Expand Again

```text
left
 ↓
[1,2,3]
     ↑
    right
```

Types:

```text
{1,2,3}
```

Invalid because there are 3 types.

---

### Shrink

Move `left`:

```text
   left
    ↓
[1,2,3]
```

Remove `1`.

Window becomes:

```text
[2,3]
```

Types:

```text
{2,3}
```

Valid again.

---

### Continue Expanding

Add `2`:

```text
[2,3,2]
```

Valid.

Add another `2`:

```text
[2,3,2,2]
```

Valid.

Length:

```text
4
```

So:

```text
answer = 4
```

---

# 🔁 Algorithm

```text
Create an empty HashMap
left = 0
max = 0

For every right index:

    Add fruits[right] to the HashMap

    While the window contains more than 2 types:

        Decrease frequency of fruits[left]

        If its frequency becomes 0:
            Remove it from the HashMap

        Move left forward

    Update maximum window length

Return maximum
```

---

# 💻 My Solution

Your solution is **correct** and uses the intended variable-size Sliding Window pattern.

```java
class Solution { 
    public int totalFruit(int[] fruits) { 
        HashMap<Integer,Integer> set = new HashMap<>(); 
        int left = 0, maxBox = 0; 
 
        for(int right = 0;right<fruits.length;right++){ 
            set.put(fruits[right],set.getOrDefault(fruits[right],0)+1); 
 
            while(set.size()>2){ 
                set.put(fruits[left],set.get(fruits[left])-1); 

                if(set.get(fruits[left])==0) 
                    set.remove(fruits[left]); 

                left++; 
            } 

            maxBox = Math.max(maxBox,right-left+1); 
        } 

        return maxBox; 
    }
}
```

---

# 🧠 Code Explanation

### 1. Create the frequency map

```java
HashMap<Integer,Integer> set = new HashMap<>();
```

The key is the fruit type.

The value is its frequency inside the current window.

For example:

```text
2 → 3
3 → 1
```

---

### 2. Expand the window

```java
for(int right = 0; right < fruits.length; right++)
```

`right` moves from left to right and adds new elements to the window.

---

### 3. Add the new fruit

```java
set.put(
    fruits[right],
    set.getOrDefault(fruits[right], 0) + 1
);
```

If the fruit already exists, increase its frequency.

Otherwise start its frequency at `1`.

---

### 4. Check whether the window is invalid

```java
while(set.size() > 2)
```

There are more than 2 different fruit types.

Therefore the current window violates the basket restriction.

---

### 5. Remove from the left

```java
set.put(
    fruits[left],
    set.get(fruits[left]) - 1
);
```

We are removing one occurrence of the leftmost fruit from the window.

---

### 6. Remove the type if its frequency becomes zero

```java
if(set.get(fruits[left]) == 0)
    set.remove(fruits[left]);
```

This is important.

Suppose:

```text
Window = [2,3,2,2]
```

and we remove one `2`.

We should **not** remove the key `2`, because two other `2`s still exist.

Only when:

```text
frequency = 0
```

does that fruit type disappear from the window.

---

### 7. Move `left`

```java
left++;
```

The window becomes smaller.

We continue shrinking until it contains at most 2 types.

---

### 8. Update the answer

```java
maxBox = Math.max(maxBox, right-left+1);
```

The current window is now valid.

Its length is:

```text
right - left + 1
```

Keep the largest length found.

---

# 🧠 Pattern Connection

This problem is an important step in learning **Sliding Window**.

### Fixed-Size Sliding Window

Example:

```text
643. Maximum Average Subarray I
```

The window size is fixed:

```text
k
```

So the window moves like:

```text
[1,2,3]
   [2,3,4]
      [3,4,5]
```

---

### Variable-Size Sliding Window

904 is different.

The window size is not fixed.

Instead, the size depends on a condition:

```text
At most 2 distinct fruit types
```

Therefore:

```text
right expands
      ↓
window becomes invalid
      ↓
left shrinks
      ↓
window becomes valid
      ↓
record answer
```

This is the general **Variable Sliding Window** pattern.

---

# 🔥 Important Sliding Window Template

Remember this structure:

```java
int left = 0;

for(int right = 0; right < n; right++) {

    // Add right element

    while(window is invalid) {

        // Remove left element
        left++;
    }

    // Window is valid
    // Update answer
}
```

For 904:

```text
Add:
fruits[right]

Invalid:
map.size() > 2

Remove:
fruits[left]

Answer:
right - left + 1
```

---

# ⚔️ Similar Problems / Variations

### 219. Contains Duplicate II

Introduced the idea of maintaining a limited recent range.

```text
HashSet + Sliding Window
```

### 643. Maximum Average Subarray I

Introduces a **fixed-size** window.

### 209. Minimum Size Subarray Sum

Introduces a **variable-size** window based on a sum condition.

### 904. Fruit Into Baskets

Variable window + frequency counting.

```text
At most 2 distinct values
```

### 1004. Max Consecutive Ones III

Variable window based on the number of zeroes.

### 713. Subarray Product Less Than K

Variable window based on product.

---

# 🚨 Common Mistakes

### 1. Using `if` instead of `while`

Incorrect:

```java
if(set.size() > 2)
```

The window may still be invalid after removing one element.

Use:

```java
while(set.size() > 2)
```

because we need to keep shrinking until the condition becomes valid.

---

### 2. Removing the key immediately

Don't do:

```java
set.remove(fruits[left]);
```

immediately.

There may be multiple occurrences of that fruit.

First decrease its frequency:

```java
set.put(fruits[left], set.get(fruits[left]) - 1);
```

Then remove the key only if its frequency becomes zero.

---

### 3. Forgetting `+1`

Window length is:

```text
right - left + 1
```

not:

```text
right - left
```

For example:

```text
left = 1
right = 3
```

Elements are:

```text
1, 2, 3
```

Length:

```text
3 - 1 + 1 = 3
```

---

### 4. Confusing number of elements with number of types

This:

```java
set.size()
```

does **not** represent the number of fruits in the window.

It represents:

> **Number of different fruit types.**

The frequency values represent how many fruits of each type exist.

---

# ⏱️ Complexity

Let `n` be the number of trees.

### Time

`right` moves from left to right once.

`left` also only moves forward.

Therefore:

```text
O(n)
```

Although there is a `while` loop, the total number of `left` movements is at most `n`.

So it is still:

```text
O(n)
```

### Space

The HashMap stores the different fruit types currently present in the window.

General worst-case:

```text
O(n)
```

For this specific problem, the valid window contains at most 2 types, so the map contains at most 3 temporarily while shrinking.

---

# 🎯 Placement-Level Takeaway

The most important lesson from 904 is not the fruit problem.

It is this pattern:

> **When asked for the longest contiguous subarray satisfying a condition, consider a variable-size Sliding Window.**

Ask yourself:

```text
1. What does my window represent?
2. What makes the window valid?
3. What makes it invalid?
4. What information must I maintain?
5. How do I shrink the window?
6. When do I update the answer?
```

For 904:

```text
Window
↓
Current contiguous section

Valid
↓
At most 2 distinct fruit types

Data Structure
↓
HashMap<fruit, frequency>

Expand
↓
right++

Shrink
↓
left++

Answer
↓
maximum window length
```

---

# ⚡ Final 30-Second Cheat Sheet

```text
904. Fruit Into Baskets

Pattern:
Variable Sliding Window

Problem:
Longest contiguous subarray
with at most 2 distinct values.

Data Structure:
HashMap<value, frequency>

right:
Expand window

while(map.size() > 2):
    Remove fruits[left]
    Decrease frequency
    Remove key if frequency == 0
    left++

Answer:
max(answer, right-left+1)

Time:
O(n)

Space:
O(n) worst case

Recognition:
"Longest contiguous subarray"
+
"At most K distinct values"
→ Sliding Window + Frequency Map
```

---

> **Nijoy P Jose**
>
> This solution is part of my **Data Structures & Algorithms** placement preparation repository, where I document problem-solving patterns, interview techniques, and Java implementations to strengthen my coding skills.
