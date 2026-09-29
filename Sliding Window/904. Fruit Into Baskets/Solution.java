class Solution { 
    public int totalFruit(int[] fruits) { 
        HashMap<Integer,Integer> set = new HashMap<>(); 
        int left = 0, maxBox = 0; 
 
        for(int right = 0;right<fruits.length;right++){ 
            set.put(fruits[right],set.getOrDefault(fruits[right],0)+1); 
 
            while(set.size()>2){ 
                set.put(fruits[left],set.get(fruits[left])-1); 

                if(set.get(fruits[left])==0) 
                    set.remove(fruits[left]); 

                left++; 
            } 

            maxBox = Math.max(maxBox,right-left+1); 
        } 

        return maxBox; 
    }
}
