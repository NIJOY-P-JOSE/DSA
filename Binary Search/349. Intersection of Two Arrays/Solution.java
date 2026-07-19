class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {

        HashSet<Integer> ans = new HashSet<>();
        Arrays.sort(nums2);
        for(int n:nums1)
        {
            if(bs(nums2,n))
                ans.add(n);
        }

        int[] res = new int[ans.size()];
        int i = 0;
        for(int n:ans) 
            res[i++] = n;
        
        return res;
    }

    boolean bs(int[] arr, int n)
    {
        int s = 0;
        int e = arr.length-1;
        while(s<=e)
        {
            int mid = s+(e-s)/2;
            if(arr[mid]==n)
                return true;
            else if(arr[mid]>n)
                e = mid-1;
            else
                s = mid + 1;
        }
        return false;
    }
}
