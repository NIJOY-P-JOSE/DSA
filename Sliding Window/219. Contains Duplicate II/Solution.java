class Solution {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        HashSet<Integer> hash = new HashSet<>();

        for(int i=0;i<nums.length;i++){
            if(hash.contains(nums[i]))
                return true;

            hash.add(nums[i]);

            if(hash.size()>k)
                hash.remove(nums[i-k]);
        }

        return false;
    }
}
