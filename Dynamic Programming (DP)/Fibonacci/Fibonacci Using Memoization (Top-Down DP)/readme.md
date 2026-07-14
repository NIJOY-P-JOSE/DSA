These are a bit more detailed while still being concise enough for a GitHub README and interview revision.

---

# 📄 Fibonacci Using Memoization (Top-Down DP)

## 📌 Concept

Memoization is a **Dynamic Programming** technique that stores the results of previously solved subproblems. When the same subproblem appears again, the stored result is returned instead of recomputing it.

## 💡 Approach

* Solve the problem recursively.
* Store each computed Fibonacci number in a dictionary.
* Before computing a value, check whether it already exists in the dictionary.
* This avoids repeated recursive calls and significantly improves performance.

## 🔑 Key Points

* Top-Down Dynamic Programming
* Uses **Recursion + Hash Map (Dictionary)**
* Eliminates overlapping subproblems
* Compute Once → Store → Reuse
* Easy way to optimize an existing recursive solution

## ⏱ Complexity

* **Time:** `O(n)`
* **Space:** `O(n)` (Dictionary + Recursion Stack)

## 📝 Interview Tip

If a recursive solution repeatedly solves the same subproblem, **Memoization** is usually the first optimization to consider.

---

### ⭐ Quick Revision

| Technique       | Approach     | Time    | Space  |
| --------------- | ------------ | ------- | ------ |
| Recursion       | Recursive    | `O(2ⁿ)` | `O(n)` |
| Memoization     | Top-Down DP  | `O(n)`  | `O(n)` |
| Tabulation      | Bottom-Up DP | `O(n)`  | `O(n)` |
| Space Optimized | Bottom-Up DP | `O(n)`  | `O(1)` |

