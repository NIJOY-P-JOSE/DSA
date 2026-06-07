class Solution {
    public char nextGreatestLetter(char[] letters, char target) {
        int s = 0;
        int e = letters.length-1;
        int mid = -1;
        while(s<=e)
        {
            mid = s+(e-s)/2;
            if(target<letters[mid])
                e = mid-1;
            else
                s = mid+1;
        }
        if(s == letters.length)
            return letters[0];
        return letters[s];
    }
}
