# Maximum Sum of K Consecutive Elements

## Problem

Given an array `error_count` of `N` integers and an integer `K`, find the maximum sum of any continuous subarray containing exactly `K` elements.

## Example

### Input

    error_count = [10, 20, 30, 40, 50]
    K = 3

### Possible Windows

    [10, 20, 30] = 60
    [20, 30, 40] = 90
    [30, 40, 50] = 120

### Output

    120

## Approach

This problem can be solved using the **Fixed-Size Sliding Window** technique.

Instead of calculating the sum of every group of `K` elements from scratch, calculate the sum of the first `K` elements and then slide the window across the array.

For every new window:

1. Remove the element leaving the window.
2. Add the new element entering the window.
3. Update the maximum sum.

### Sliding Window Formula

    new_window_sum = current_window_sum - element_leaving + element_entering

For example:

    [10, 20, 30] → [20, 30, 40]

    90 - 10 + 40 = 120

## Algorithm

1. Calculate the sum of the first `K` elements.
2. Store it as `window_sum`.
3. Store `window_sum` as the initial `maximum`.
4. Start iterating from index `K`.
5. Remove the element at index `i - K`.
6. Add the element at index `i`.
7. Update the maximum sum.
8. Return the maximum sum.

## Python Solution

    def maximum_error(error_count, K):
        n = len(error_count)

        if n <= K:
            return sum(error_count)

        window_sum = sum(error_count[:K])
        maximum = window_sum

        for i in range(K, n):
            window_sum = window_sum - error_count[i - K] + error_count[i]
            maximum = max(maximum, window_sum)

        return maximum

## Example

    error_count = [10, 20, 30, 40, 50]
    K = 3

    print(maximum_error(error_count, K))

### Output

    120

## Complexity Analysis

### Time Complexity

    O(N)

Each element is processed once while the window moves through the array.

### Space Complexity

    O(1)

Only a few variables are used, so no additional array is required.

## Why Sliding Window?

A brute-force solution would calculate the sum of every group of `K` elements separately.

This can take:

    O(N × K)

The sliding window approach reduces the time complexity to:

    O(N)

because each element is added and removed from the window only once.

## Pattern

**Fixed-Size Sliding Window**

Remember:

    REMOVE LEFT → ADD RIGHT → UPDATE ANSWER

General formula:

    window_sum = window_sum - arr[i-K] + arr[i]

## Common Mistakes

1. Using `k` instead of `K`.

   Python is case-sensitive.

2. Using an undefined variable such as `nums[i]`.

   Use `error_count[i]`.

3. Using an incorrect loop range.

   Correct:

    for i in range(K, n):

4. Naming a variable `sum`.

   Avoid this because `sum()` is a Python built-in function.

   Use:

    window_sum

5. Forgetting to update the maximum after each window.

## Key Takeaway

Whenever a problem asks for:

- Maximum sum of exactly `K` consecutive elements
- Minimum sum of exactly `K` consecutive elements
- Any fixed-size continuous window

Think:

**Fixed-Size Sliding Window**
