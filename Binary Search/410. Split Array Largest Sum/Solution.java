class Solution {
    public int splitArray(int[] nums, int k) {
        
        int s = 0, e = 0;

        for(int i=0; i<nums.length; i++)
        {
            s = Math.max(s, nums[i]); // To find max value from s and nums[i]. Start rage of possible answer
            e+=nums[i]; // Sum of all elements in array. Largest value of possible answer range
        }

        // Maximum split of array size of N is N splits
        // Here each element in array is an subarray. So larget sum will be the max value in the Array.
        // Eg: arr=[7, 2, 10, 4] split by 4(N): [7],[2],[10],[4]. Max sum of subarray is 10

        // An array can split by minimum is 1
        //So minimum sum in this will be sum of Array
        //Eg: arr=[7, 2, 10, 4] split by 1. So the sum of array 23 will be the max sum of subarry( only one sub arry as whole array)


         while(s<e) // If s == e then the answer is found. it point to the minimum sum 
        {
            int mid = s+(e-s)/2; // Mid will be the max sum of an sub array.
            int p = 1, sum = 0;// p is count splits, sum calculate each sum of sub arrays.
            for(int n: nums)
            {
                if(sum+n>mid) // new element add to the sum is greater than mid then the n in need to put in new sub array.
                {
                      sum = n; // new sub array for n. S = n bcaouse n is the first element here.
                      p++; // In subarray. Count the new sub arrays.
                }
                else
                {
                  
                    sum+=n; // Sum is less than mid, so n can add in this sub array.
                }
            }
            
            if(p>k) // If pieces  grater than needed splits, than we need to increase s that increase mid in mid equation
                s = mid+1;
            else
                e = mid;
        }
        return s;// At end answer will point to s = mid =e; 
    }       
}
