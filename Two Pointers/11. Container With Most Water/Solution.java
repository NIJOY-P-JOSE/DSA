class Solution {
    public int maxArea(int[] height) {

        int maxWater = 0, n =height.length;
        int left = 0, right = n-1;
        
        while(left<right){

            int area = (right-left) * Math.min(height[left],height[right]);
            //         ^--Width--^    ^---------------Height--------------^
            maxWater = Math.max(maxWater,area);

            if(height[left]>height[right])
                right--;
            else
                left++;
        }
        return maxWater;
    }
}
