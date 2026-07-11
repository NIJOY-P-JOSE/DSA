class Solution {
    public int[][] flipAndInvertImage(int[][] image) {
        
        for(int[] h :image)
        {
            int s = 0;
            int e = h.length-1;
            while(s<=e)
            {
                int t = h[s];
                h[s] = h[e]^1;
                h[e] = t^1;
                s++;
                e--;     
            }
        }
        return image;
    }
}
