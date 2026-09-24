# [125. Valid Palindrome](https://leetcode.com/problems/valid-palindrome/)

**Difficulty:** Easy
**Primary Language:** Python
**Pattern:** Two Pointers
**Related Patterns:** String Traversal · Palindrome · Character Filtering

---

## ⚡ Quick Revision

|                |                                                                                            |
| -------------- | ------------------------------------------------------------------------------------------ |
| **Goal**       | Check whether a string is a palindrome after ignoring non-alphanumeric characters and case |
| **Pattern**    | Two Pointers                                                                               |
| **Pointers**   | `left` → beginning, `right` → end                                                          |
| **Ignore**     | Non-alphanumeric characters                                                                |
| **Comparison** | Case-insensitive                                                                           |
| **Time**       | `O(n)`                                                                                     |
| **Space**      | `O(1)`                                                                                     |

### Core Logic

```text
left →                         ← right

skip invalid characters
        ↓
compare characters
        ↓
equal?
 /    \
yes    no
 ↓      ↓
move   false
both
```

---

## 🧠 Problem in Simple Words

We need to determine whether a string reads the same from both directions.

Before checking:

1. Convert uppercase letters to lowercase.
2. Ignore spaces and other non-alphanumeric characters.
3. Compare the remaining characters from both ends.

For example:

```text
"A man, a plan, a canal: Panama"
```

After ignoring non-alphanumeric characters and case:

```text
"amanaplanacanalpanama"
```

It reads the same from both directions, so the answer is:

```text
true
```

---

## 🔥 How to Recognize This Problem in a Placement

Look for these clues:

* The problem asks whether something is a **palindrome**.
* You need to compare characters from the **beginning and end**.
* Characters need to be compared symmetrically.
* The problem may ask you to ignore certain characters.
* You don't necessarily need to create a reversed string.

### Recognition Trigger

> **Palindrome + compare from both ends → Think Two Pointers.**

Typical structure:

```text
left →           ← right
```

Compare:

```text
s[left] == s[right]
```

Then move:

```text
left++
right--
```

---

## 🧩 How to Think / Derive the Solution

### Step 1: What actually needs to be compared?

A palindrome has matching characters at opposite positions.

For:

```text
"racecar"
```

we compare:

```text
r       r
 e     e
  a   a
   c
```

So we naturally need two positions.

```text
left = 0
right = n - 1
```

---

### Step 2: What about spaces and punctuation?

The problem says non-alphanumeric characters should be ignored.

For example:

```text
"A man, a plan..."
```

The comma and spaces should not participate in the comparison.

Python provides:

```python
s[left].isalnum()
```

which allows us to check whether a character is alphanumeric.

If it isn't:

```python
left += 1
```

Similarly for the right pointer.

---

### Step 3: What about uppercase and lowercase?

The comparison should be case-insensitive.

So:

```text
'A' == 'a'
```

should be treated as true.

Python provides:

```python
.lower()
```

Therefore:

```python
s[left].lower() != s[right].lower()
```

checks the characters without considering their case.

---

### Step 4: What happens when characters don't match?

If:

```text
left character != right character
```

then the string cannot be a palindrome.

Immediately:

```python
return False
```

There is no reason to continue checking.

---

### Step 5: What happens when they match?

Move both pointers toward the center:

```python
left += 1
right -= 1
```

If every valid pair matches, the string is a palindrome.

Therefore:

```python
return True
```

---

## 📊 Visual Explanation

Consider:

```text
s = "A man, a plan, a canal: Panama"
```

Initially:

```text
A man, a plan, a canal: Panama
↑                              ↑
L                              R
```

Compare:

```text
A == a
```

They match ignoring case.

Move both:

```text
 A man, a plan, a canal: Panam a
   ↑                          ↑
   L                          R
```

If a pointer reaches a space or punctuation:

```text
A man, a plan...
  ↑
```

skip it:

```python
left += 1
```

Continue until the pointers meet.

If every valid character matches:

```text
Palindrome ✓
```

---

## 🔁 Algorithm

1. Set `left = 0`.
2. Set `right = len(s) - 1`.
3. While `left < right`:

   * Move `left` forward until it points to an alphanumeric character.
   * Move `right` backward until it points to an alphanumeric character.
   * Compare the two characters ignoring case.
   * If they are different, return `False`.
   * Otherwise move both pointers inward.
4. If all comparisons succeed, return `True`.

---

## 💻 My Solution — Python

```python
class Solution:
    def isPalindrome(self, s: str) -> bool:
        left = 0
        right = len(s) - 1

        while left < right:

            while left < right and not s[left].isalnum():
                left += 1

            while left < right and not s[right].isalnum():
                right -= 1

            if s[left].lower() != s[right].lower():
                return False

            left += 1
            right -= 1

        return True
```

---

## 🧠 Code Explanation

### Initialize pointers

```python
left = 0
right = len(s) - 1
```

One pointer starts from the beginning and the other from the end.

---

### Skip invalid characters

```python
while left < right and not s[left].isalnum():
    left += 1
```

If the left character isn't a letter or number, ignore it.

Similarly:

```python
while left < right and not s[right].isalnum():
    right -= 1
```

ignores invalid characters from the right.

---

### Compare valid characters

```python
if s[left].lower() != s[right].lower():
    return False
```

`.lower()` makes the comparison case-insensitive.

If the characters don't match, we immediately know the string isn't a palindrome.

---

### Move inward

```python
left += 1
right -= 1
```

After successfully comparing a pair, move both pointers toward the center.

---

## 🧠 Pattern Connection

This is another important variation of the **Two Pointers** pattern.

### 167. Two Sum II

You used:

```text
left →           ← right
```

and moved a pointer based on the sum:

```text
sum > target → right--
sum < target → left++
```

### 125. Valid Palindrome

Again:

```text
left →           ← right
```

But now the movement is based on character comparison:

```text
invalid character → skip it

characters match → move both

characters differ → false
```

### Common Pattern

Both problems avoid unnecessary searching by using information from **both ends**.

---

## ⚔️ Similar Problems / Variations

Problems to connect with this pattern:

* **167. Two Sum II - Input Array Is Sorted** → Two pointers + target
* **977. Squares of a Sorted Array** → Two pointers + sorted array
* **11. Container With Most Water** → Two pointers + greedy movement
* **15. 3Sum** → Sorting + two pointers

The main difference is **why the pointers move**.

---

## 🚨 Common Mistakes

### 1. Comparing every pair

Don't use nested loops.

```text
O(n²)
```

The palindrome structure allows a linear scan.

---

### 2. Forgetting non-alphanumeric characters

For:

```text
"A man, a plan..."
```

spaces and punctuation should be ignored.

---

### 3. Forgetting case-insensitive comparison

```text
'A' and 'a'
```

should be considered equal.

---

### 4. Moving only one pointer after a successful comparison

Once both characters match:

```python
left += 1
right -= 1
```

Both should move.

---

### 5. Creating unnecessary extra strings

You could create a cleaned string and then reverse it, but that requires additional memory.

The two-pointer solution checks the original string directly.

---

## ⏱️ Complexity

### Time Complexity

```text
O(n)
```

Each pointer moves toward the center and each character is processed only a small number of times.

### Space Complexity

```text
O(1)
```

No additional string or array is created.

Only the two pointers are used.

---

## 🎯 Placement-Level Takeaway

When you see:

```text
Palindrome
+
Compare from both ends
```

think:

```text
Two Pointers
```

For this particular problem, remember:

```text
1. left = 0
2. right = n - 1

3. Skip non-alphanumeric characters

4. Compare:
   lowercase(left) == lowercase(right)

5. If different:
   return false

6. If same:
   left++
   right--

7. Finish:
   return true
```

The important part isn't memorizing `isalnum()` or `.lower()`.

The important DSA pattern is:

> **Use two pointers moving inward to compare symmetric elements.**

---

## ⚡ Final 30-Second Cheat Sheet

```text
Problem:
Valid Palindrome

Pattern:
Two Pointers

Start:
left = 0
right = n - 1

Ignore:
Non-alphanumeric characters

Compare:
Case-insensitive

Mismatch:
return False

Match:
left++
right--

Finished:
return True

Time:
O(n)

Space:
O(1)
```

---

## ☕ Java Reference

The same two-pointer idea can be implemented in Java:

```java
class Solution {
    public boolean isPalindrome(String s) {

        int left = 0;
        int right = s.length() - 1;

        while (left < right) {

            while (left < right && !Character.isLetterOrDigit(s.charAt(left))) {
                left++;
            }

            while (left < right && !Character.isLetterOrDigit(s.charAt(right))) {
                right--;
            }

            if (Character.toLowerCase(s.charAt(left)) !=
                Character.toLowerCase(s.charAt(right))) {
                return false;
            }

            left++;
            right--;
        }

        return true;
    }
}
```

This Java version is included **only as a reference**; your primary repository solution for this problem can remain Python.

---

**Nijoy P Jose**

This solution is part of my **Data Structures & Algorithms** placement preparation repository, where I document problem-solving patterns, interview techniques, and implementations to strengthen my coding skills.
