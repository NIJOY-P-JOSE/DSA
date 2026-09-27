class Solution {
    public void rotate(int[] nums, int k) {
        int n = nums.length-1;
        k = k%(n+1);
        reverse(nums,0,n);
        reverse(nums,0,k-1);
        reverse(nums,k,n);
    }

    void reverse(int[] arr,int left, int right){
        while(left<right){
            int t = arr[left];
            arr[left] = arr[right];
            arr[right] = t;
            right--;
            left++;
        }
    }
}
