class Solution {
    public int minEatingSpeed(int[] piles, int h) {
        int l = 1, r = 0;

        // The minimum speed is 1, while the maximum useful speed
        // is the size of the largest pile.
        for (int n : piles)
            r = Math.max(r, n);

        while (l <= r) {
            int mid = l + (r - l) / 2;

            // Check whether this speed allows Koko to finish
            // all piles within the given number of hours.
            if (isPossible(mid, piles, h))
                r = mid - 1;   // Possible, so try a smaller speed.
            else
                l = mid + 1;   // Too slow, so increase the speed.
        }

        // l is the first speed that satisfies the required condition.
        return l;
    }

    boolean isPossible(int speed, int[] piles, int h) {
        int hrs = 0;

        // Each pile requires ceil(pile / speed) hours.
        // Add the required hours for all piles and check the limit.
        for (int pile : piles)
            hrs += Math.ceil(pile / (double) speed);

        return hrs <= h;
    }
}
