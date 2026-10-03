class Solution {
    public int minDays(int[] bloomDay, int m, int k) {
        if (bloomDay.length < m * k)
            return -1;

        int l = Integer.MAX_VALUE, r = 0;

        // The answer must be between the earliest and latest
        // flower blooming days.
        for (int n : bloomDay) {
            l = Math.min(l, n);
            r = Math.max(r, n);
        }

        while (l <= r) {
            int mid = l + (r - l) / 2;

            // Check whether enough bouquets can be formed
            // by the given candidate day.
            if (isPossible(mid, bloomDay, m, k))
                r = mid - 1;   // Possible, so try an earlier day.
            else
                l = mid + 1;   // Not possible, so wait longer.
        }

        // l becomes the first day on which all required bouquets are possible.
        return l;
    }

    boolean isPossible(int days, int[] arr, int b, int f) {
        int bqts = 0, flow = 0;

        // Count consecutive flowers that have bloomed by this day.
        // Every f consecutive bloomed flowers form one bouquet.
        for (int d : arr) {
            if (d <= days) {
                flow++;

                if (flow == f) {
                    bqts++;
                    flow = 0;
                }
            } else {
                // An unbloomed flower breaks the adjacent sequence.
                flow = 0;
            }
        }

        return bqts >= b;
    }
}
