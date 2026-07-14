# 📄 Fibonacci Using Tabulation (Bottom-Up DP)

## 📌 Concept

Tabulation is a **Dynamic Programming** technique that solves smaller subproblems first and stores their results in a DP array. The final answer is built iteratively from these stored values.

## 💡 Approach

* Create a DP array of size `n + 1`.
* Initialize the base cases (`dp[0]` and `dp[1]`).
* Compute each Fibonacci number from `2` to `n`.
* Return the last value in the DP array.

## 🔑 Key Points

* Bottom-Up Dynamic Programming
* Uses **DP Array**
* No recursion (iterative approach)
* Every state is computed exactly once
* Can be optimized to **O(1)** space since only the previous two values are required

## ⏱ Complexity

* **Time:** `O(n)`
* **Space:** `O(n)`

## 📝 Interview Tip

When a DP state depends only on a few previous states (like `dp[i-1]` and `dp[i-2]`), think about **Space Optimization** by replacing the DP array with variables.

---

### ⭐ Quick Revision

| Technique       | Approach     | Time    | Space  |
| --------------- | ------------ | ------- | ------ |
| Recursion       | Recursive    | `O(2ⁿ)` | `O(n)` |
| Memoization     | Top-Down DP  | `O(n)`  | `O(n)` |
| Tabulation      | Bottom-Up DP | `O(n)`  | `O(n)` |
| Space Optimized | Bottom-Up DP | `O(n)`  | `O(1)` |





