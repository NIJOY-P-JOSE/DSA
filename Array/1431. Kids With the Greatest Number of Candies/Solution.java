class Solution {
    public List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
        ArrayList<Boolean> result = new ArrayList<>();
        int maxCandie = candies[0];
        for(int i = 1;i<candies.length;i++){
            if(maxCandie<candies[i])
                maxCandie = candies[i];
        } 

        for(int i = 0;i<candies.length;i++){
            if(maxCandie<=candies[i]+extraCandies)
                result.add(true);
            else
                result.add(false);
        }
        return result;
    }
}
