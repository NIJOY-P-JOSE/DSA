class Solution {
    public int[] intersect(int[] nums1, int[] nums2) {
        
        Map<Integer,Integer> sh1 = new HashMap<>();
        // Map<Integer,Integer> sh2 = new HashMap<>();
        List<Integer> ans = new ArrayList<>(); 

        for(int n:nums1)
        {
            if(sh1.containsKey(n))
                sh1.put(n,sh1.get(n)+1);
            else
                sh1.put(n,1);
        }

        for(int n:nums2)
        {
            if(sh1.containsKey(n) && sh1.get(n)>0)
            {
                sh1.put(n,sh1.get(n)-1);
                ans.add(n);
            }

        }
        // for(int n:nums1)
        // {
        //     int l = Math.min(sh1.get(n),sh2.get(n)==null?0:sh2.get(n));
        //     for(int i = 0;i<l;i++)
        //     {
        //         ans.add(n);
        //         sh1.put(n,-1);
        //     }
        // }

        int[] res = new int[ans.size()];
        int i = 0;
        for(int n:ans)
            res[i++] = n;
        return res;
    }
}
