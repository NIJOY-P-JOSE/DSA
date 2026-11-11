import java.util.*;

class Solution {
    public int lengthOfLongestSubstring(String s) {

        HashSet<Character> set = new HashSet<>();
        int left = 0, maxl = 0;

        for(int right = 0; right < s.length(); right++)
        {
            // Remove characters until the duplicate disappears
            while(set.contains(s.charAt(right)))
            {
                set.remove(s.charAt(left));
                left++;
            }

            // Add the current character
            set.add(s.charAt(right));

            // Update the maximum substring length
            maxl = Math.max(maxl, right - left + 1);
        }

        return maxl;
    }
}
