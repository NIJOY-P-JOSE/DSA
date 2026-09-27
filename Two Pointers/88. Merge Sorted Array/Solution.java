class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {

        int n2 = n - 1;
        int n1 = m - 1;
        int k = n + m - 1;

        if (n == 0)
            return;

        if (m == 0) {
            for (int i = 0; i < n; i++)
                nums1[i] = nums2[i];

            return;
        }

        while (n2 >= 0) {

            if (n1 < 0 || nums1[n1] < nums2[n2]) {

                nums1[k] = nums2[n2];
                k--;
                n2--;

            } else {

                nums1[k] = nums1[n1];
                k--;
                n1--;
            }
        }
    }
}
