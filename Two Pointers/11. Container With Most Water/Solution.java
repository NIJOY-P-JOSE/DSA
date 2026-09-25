class Solution {
    public int maxArea(int[] height) {

        int maxWater = 0;
        int left = 0, right = height.length;
        
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
