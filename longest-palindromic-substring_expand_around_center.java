class Solution {
    /*
    expand from center
    
    */
    public String longestPalindrome(String s) {
        
        /*
        0123456789
        aaaabcdefg
        len = 10
        Approach: need to iterate through all combos of (start,end) O(n^2), but with dynamic programing
        */
        // i, j
        // 0, 0 diff=0
        // 1, 1
        // 2, 2
        // 0, 1 diff=1
        // 1, 2
        /*
        maxLen
        maxStart;maxEnd;
        for c in centers: // O(n)
            // expand from center
            while start >= 0 and end < len and s[start] == s[end]
                start--;end++;
                update maxLen,maxStart,maxEnd
            
        return s[maxStart, maxLen+1]
        
        0 -> start,end=0
        1 -> start=0,end=1
        2 -> start,end=1
        3 -> start=1,end=2
        
        if c is even:
            start,end=c
        else:
            start=c/2,end=c/2 + 1
        
        012
        ccc
        c=2
        start=
        end=2
        maxLen=2
        maxStart,maxEnd=0,1
        */
        int maxLen = 0;
        int maxStart = 0;
        int maxEnd = 0;
        
        int start = 0;
        int end = 0;
        for (int c = 0; c < 2 * s.length() - 1; c++) {
            if (c % 2 == 0) {
                start = c / 2;
                end = c / 2;
            } else {
                start = c / 2;
                end = c / 2 + 1;
            }
            while (start >= 0 && end < s.length() && s.charAt(start) == s.charAt(end)) {
                if (end - start + 1 > maxLen) {
                    maxLen = end - start + 1;
                    maxStart = start;
                    maxEnd = end;
                }
                start--;
                end++;
            }
        }
        return s.substring(maxStart, maxEnd + 1);
        
    }
    
}