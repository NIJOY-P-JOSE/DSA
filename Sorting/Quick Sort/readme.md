## 💻 My Solution

I use the **first element as the pivot** and partition the array so that:

- Elements `<= pivot` move to the left.
- Elements `> pivot` remain on the right.
- The pivot is finally placed in its correct position.
- Quick Sort is then recursively applied to the left and right portions.

### Code

```python
class Solution:
    def quickSort(self, arr, low, high):
        if low < high:
            pivot = self.partition(arr, low, high)

            self.quickSort(arr, low, pivot - 1)
            self.quickSort(arr, pivot + 1, high)

    def partition(self, arr, low, high):
        pivot = arr[low]
        i = low

        for j in range(low + 1, high + 1):
            if arr[j] <= pivot:
                i += 1
                arr[i], arr[j] = arr[j], arr[i]

        arr[i], arr[low] = arr[low], arr[i]

        return i
```

### Code Breakdown

#### 1. Base condition

```python
if low < high:
```

If the current portion has zero or one element, it is already sorted.

---

#### 2. Partition

```python
pivot = self.partition(arr, low, high)
```

`partition()` rearranges the current portion and returns the **final index of the pivot**.

For example:

```text
Before:
[4, 1, 3, 9, 7]

Pivot = 4

After partition:
[3, 1, 4, 9, 7]
       ↑
     pivot
```

The pivot is now fixed.

---

#### 3. Recursive left side

```python
self.quickSort(arr, low, pivot - 1)
```

Sort everything before the pivot.

```text
[ LEFT | PIVOT | RIGHT ]
   ↑
 sort this
```

---

#### 4. Recursive right side

```python
self.quickSort(arr, pivot + 1, high)
```

Sort everything after the pivot.

```text
[ LEFT | PIVOT | RIGHT ]
                 ↑
              sort this
```

---

#### 5. Choosing the pivot

```python
pivot = arr[low]
```

Your implementation always chooses the **first element** of the current range as the pivot.

This is important for understanding the worst-case complexity.

---

#### 6. `i` — partition boundary

```python
i = low
```

`i` tracks the boundary of the elements that are `<= pivot`.

Whenever:

```python
arr[j] <= pivot
```

we expand that region:

```python
i += 1
```

and move the element there.

---

#### 7. `j` — scanning pointer

```python
for j in range(low + 1, high + 1):
```

`j` scans every element after the pivot.

Its job is simply to determine:

> Does this element belong on the left side of the pivot?

---

#### 8. Final pivot placement

```python
arr[i], arr[low] = arr[low], arr[i]
```

After all elements have been processed, the pivot is moved between the two partitions.

The result is:

```text
[ elements <= pivot | pivot | elements > pivot ]
```

Then:

```python
return i
```

returns the pivot's final position.

---

## 🧠 Code Flow

Remember the code as four steps:

```text
quickSort()
    │
    ├── Is there more than one element?
    │
    ├── partition()
    │      │
    │      ├── choose pivot
    │      ├── scan elements
    │      ├── build <= pivot region
    │      └── place pivot
    │
    ├── quickSort(left)
    │
    └── quickSort(right)
```

### 30-second code recall

```text
if low < high
    ↓
partition
    ↓
pivot index
    ↓
sort left
    ↓
sort right
```

The **partition function is the heart of Quick Sort**. Once you understand how `i`, `j`, and the pivot maintain the partition, the recursive part becomes straightforward.
