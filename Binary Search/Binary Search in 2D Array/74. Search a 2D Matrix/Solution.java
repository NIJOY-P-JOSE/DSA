class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        
        int m = matrix.length;
        int c = matrix[0].length;

        // Treat the entire matrix as one sorted 1D array.
        // For m rows and c columns, the virtual indices are 0 to m*c-1.
        int l = 0, r = m * c - 1;

        while (l <= r) {
            int mid = l + (r - l) / 2;

            // Convert the virtual 1D index back to a matrix position.
            // Example: if c = 4 and mid = 6:
            // row = 6/4 = 1, col = 6%4 = 2 → matrix[1][2]
            int row = mid / c;
            int col = mid % c;

            if (target == matrix[row][col])
                return true;

            // Since the virtual array is sorted, eliminate half
            // of the remaining search space just like normal Binary Search.
            else if (target > matrix[row][col])
                l = mid + 1;
            else
                r = mid - 1;
        }

        // Search space is empty, so the target does not exist.
        return false;
    }
}
