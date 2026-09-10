# 🚁 Maximum Profit Package Delivery

> **Note:** This problem statement is reconstructed from a problem encountered during a campus recruitment coding assessment. The exact wording and conditions may differ from the original question.

---

## 📌 Problem Statement

A drone starts from location `0` and must deliver packages to different locations.

The drone travels through a network of routes. Each route takes a certain amount of time.

The drone carries packages in a **stack**.

Because the packages are arranged in a stack:

```text
First In → Last Out
```

only the package at the **top of the stack** can be delivered first.

The drone may:

- Carry multiple packages.
- Choose which packages to deliver.
- Skip packages if necessary.
- Choose the order in which packages are arranged in the stack.
- Travel through multiple routes.
- Stop after the final delivery.

The drone does **not** need to return to location `0`.

A package earns profit only if it is delivered on or before its deadline.

Your task is to find the **maximum total profit** that can be earned.

---

# 📥 Input

The problem contains the following inputs.

## 1️⃣ Number of Packages

```text
num_packages
```

Represents the total number of available packages.

---

## 2️⃣ Number of Routes

```text
num_routes
```

Represents the total number of routes connecting locations.

---

## 3️⃣ Number of Locations

```text
num_locations
```

Represents the total number of locations.

---

## 4️⃣ Routes

A 2D array where each row represents a route:

```text
[start_location, travel_time, end_location]
```

For example:

```text
[0, 5, 2]
```

means:

```text
Location 0 ──5── Location 2
```

Travelling between these two locations takes `5` units of time.

Routes are **bidirectional**.

Therefore:

```text
0 → 2
```

and:

```text
2 → 0
```

both take `5` units of time.

---

## 5️⃣ Locations Array

The `locations` array describes which package must be delivered at each location.

The:

```text
Index → Location
Value → Package to deliver
```

For example:

```text
locations = [1, 3, 3, 2, 0]
```

means:

| Location | Package |
|----------|---------|
| `0` | Package `1` |
| `1` | Package `3` |
| `2` | Package `3` |
| `3` | Package `2` |
| `4` | Package `0` |

---

## 6️⃣ Profit Array

The `profit` array contains the profit earned for delivering each package.

```text
profit[i]
```

represents the profit of:

```text
Package i
```

For example:

```text
profit = [10, 20, 30]
```

means:

| Package | Profit |
|---------|--------|
| Package `0` | `10` |
| Package `1` | `20` |
| Package `2` | `30` |

---

## 7️⃣ Deadline Array

The deadline array is associated with locations.

```text
deadline[i]
```

represents the latest time at which the package associated with location `i` must be delivered.

If the drone reaches the location after the deadline:

```text
delivery_time > deadline[i]
```

the delivery does not earn its profit.

A delivery is successful if:

```text
delivery_time <= deadline[i]
```

---

# 📦 Package Stack Rule

The drone carries the selected packages in a stack.

Only the package at the top of the stack can be delivered first.

For example:

```text
Top
 ↓

Package 3
Package 1
Package 0
```

The delivery order must be:

```text
Package 3 → Package 1 → Package 0
```

The drone cannot deliver a package below another package before delivering the package above it.

However, before starting the trip, the drone may choose:

- Which packages to include.
- Which packages to skip.
- The order of packages in the stack.

---

# ⏱️ Travel Time

The drone starts from:

```text
Location 0
```

Time starts at:

```text
0
```

Every route consumes the travel time specified in the `routes` array.

For example:

```text
Location 0 → Location 2 = 5
Location 2 → Location 4 = 3
```

The total time when reaching location `4` is:

```text
5 + 3 = 8
```

A package delivered at that location earns profit only if:

```text
8 <= deadline[4]
```

---

# 🎯 Objective

Find the maximum total profit that can be earned by choosing:

1. Which packages to deliver.
2. Which packages to skip.
3. The order of packages in the stack.
4. A valid route through the locations.

A package contributes to the total profit only if it is delivered on or before its deadline.

---

# 🧠 Important Observations

This problem combines multiple concepts.

## 🗺️ Graph

Locations can be represented as:

```text
Nodes
```

Routes can be represented as:

```text
Weighted Edges
```

The travel time is the:

```text
Edge Weight
```

---

## 📦 Ordering

Packages must be delivered according to the stack order.

Since the order can be chosen before starting, the problem involves deciding the best sequence of deliveries.

---

## ⏰ Scheduling

Every delivery has a deadline.

Therefore, delivering one package may consume time and cause another package to miss its deadline.

The drone may need to skip some packages to maximize total profit.

---

# 💡 Possible Approach

A solution can involve the following steps.

### Step 1: Build the Graph

Convert the `routes` array into an adjacency list.

```text
Location → [(Neighbour, Travel Time)]
```

---

### Step 2: Find Travel Costs

Use a shortest-path algorithm such as Dijkstra's algorithm to find the minimum travel time between relevant locations.

---

### Step 3: Choose Delivery Order

Consider possible orders of package deliveries.

For each possible delivery:

1. Calculate the travel time.
2. Update the total elapsed time.
3. Check whether the deadline is satisfied.
4. Add the package profit if the delivery is successful.
5. Continue with the remaining packages.

---

### Step 4: Maximize Profit

Use Dynamic Programming or Search to store the best result for a state.

A possible state may depend on:

```text
Current Location
Current Time
Packages Already Delivered
```

---

# 🧠 Example State

A state can conceptually be represented as:

```text
(location, time, delivered_packages)
```

Where:

| State | Meaning |
|-------|---------|
| `location` | Current drone location |
| `time` | Total travel time so far |
| `delivered_packages` | Packages already delivered |

The answer is the maximum profit achievable from valid delivery sequences.

---

# 🛠️ Concepts Used

- Graphs
- Weighted Graphs
- Dijkstra's Algorithm
- Shortest Paths
- Dynamic Programming
- State Space Search
- Scheduling
- Deadlines
- Optimization
- Stack Ordering

---

⭐ **The challenge is to balance travel time, package order, deadlines, and profit to find the delivery sequence that produces the maximum total profit.**
