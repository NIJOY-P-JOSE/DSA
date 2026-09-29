class Solution { 
    public int totalFruit(int[] fruits) { 
        HashMap<Integer,Integer> count = new HashMap<>(); 
        int left = 0, maxBox = 0; 
 
        for(int right = 0;right<fruits.length;right++){ 
            count.put(fruits[right],count.getOrDefault(fruits[right],0)+1); 
 
            while(count.size()>2){ 
                count.put(fruits[left],count.get(fruits[left])-1); 

                if(count.get(fruits[left])==0) 
                    count.remove(fruits[left]); 

                left++; 
            } 

            maxBox = Math.max(maxBox,right-left+1); 
        } 

        return maxBox; 
    }
}
