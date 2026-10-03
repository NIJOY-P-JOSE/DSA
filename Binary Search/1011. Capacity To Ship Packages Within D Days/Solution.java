class Solution {
    public int shipWithinDays(int[] weights, int days) {
        int l = 0, r = 0;

        // The ship must at least carry the heaviest package,
        // while the total weight is enough to ship everything in one day.
        for (int n : weights) {
            l = Math.max(l, n);
            r += n;
        }

        while (l <= r) {
            int mid = l + (r - l) / 2;

            // Check whether this capacity can ship all packages
            // within the required number of days.
            if (isPossible(mid, weights, days))
                r = mid - 1;   // Possible, so try a smaller capacity.
            else
                l = mid + 1;   // Not possible, so increase capacity.
        }

        // l becomes the smallest capacity that satisfies the condition.
        return l;
    }

    boolean isPossible(int cap, int[] weights, int days) {
        int d = 1, curw = 0;

        // Process packages in their original order. Keep adding packages
        // to the current day until the next package would exceed capacity.
        for (int w : weights) {
            if (curw + w <= cap)
                curw += w;
            else {
                // Start a new day with the current package.
                curw = w;
                d++;
            }
        }

        return d <= days;
    }
}
