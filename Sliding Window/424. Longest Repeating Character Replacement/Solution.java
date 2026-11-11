class Solution {
    public int characterReplacement(String s, int k) {
        int[] freq = new int[26]; // Stores frequency of each character

        int maxWindow = 0; // Longest valid window found
        int maxFreq = 0;   // Highest frequency observed so far
        int left = 0;      // Left pointer of the window

        for(int right = 0; right < s.length(); right++)
        {
            // Add the current character to the window
            freq[s.charAt(right) - 'A']++;

            // Update the highest character frequency
            maxFreq = Math.max(maxFreq, freq[s.charAt(right) - 'A']);

            // Calculate the current window length
            int windowLength = right - left + 1;

            // Check if replacements required exceed k
            if(windowLength - maxFreq > k){

                // Remove the leftmost character from the window
                freq[s.charAt(left) - 'A']--;

                // Shrink the window by moving left forward
                left++;

                // Update the window length after shrinking
                windowLength--;
            }

            // Update the maximum window length
            maxWindow = Math.max(maxWindow, windowLength);
        }

        return maxWindow;
    }
}
