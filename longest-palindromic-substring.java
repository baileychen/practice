class Solution {
    /*
    Start: 7:38pm
    wrong answer: 8:07 (30 min D':)
    LESSONS: make sure algorithm actually works with test cases BEFORE coding solution
    
    brute-force: O(n^2)
    
    dynamic programming -> problem builds on subproblems
    
    babad
    01234
    lp("babad", 0, 4) = lp("baba", 0,3) + "d"(4)
    lp("babab", 0, 4) = lp("baba", 0,3) + "b"(4) OR "b"(0) + lp("abab", 1,4)
    
    lp(babad) = b(0) + lp(aba) + d(4) --> aba
    babad
    lp(babab) = b(0) + lp(aba) + b(4) --> babab
    
    extend substring if start==end and lp(substring)
    space: O(N^2) 
    base cases:
    012345678
    aaaabcdefghijklmnop = a(aaabcdefghijklmno)p = a( aaabcdefghijklmno: aaa, 1,3)p
    if s.length = 0 or s.length = 1: return s (IS palindrome)
                    01234
    recursive case: babab
        lp(aba) --> 1,3 
        if start==end && lp(aba).start = start+1 && lp(aba).end = end - 1:
            return [s, start, end]
        else: // babad
            return lp(aba) -> 1,3
    
    helper(String s, int start, int end, boolean[][] isPalindrome);
    
    */
    public String longestPalindrome(String s) {
        
        /*
        0123456789
        aaaabcdefg
        len = 10
        isPalindrome:
          0 1 2 3 4 5 6 7 8 9
        0 1 1 1 0 0 0 0 0 0 0
        1 0 1 1 0 0 0 0 0 0 0
        2 0 0 1 1 0 0 0 0 0 0
        3 0 0 0 1 X 0 0 0 0 0
        4 0 0 0 0 1 X 0 0 0 0
        5 0 0 0 0 0 1 X 0 0 0
        6 0 0 0 0 0 0 1 X 0 0
        7 0 0 0 0 0 0 0 1 X 0
        8 0 0 0 0 0 0 0 0 1 X
        9 0 0 0 0 0 0 0 0 0 1
        Approach: need to iterate through all combos of (start,end) O(n^2), but with dynamic programing
        */
        int stringLen = s.length();
        boolean[][] isPalindrome = new boolean[stringLen][stringLen];
        
        // while loop
        int maxDiff = 0;
        int maxStart=0;
        int maxEnd = 0;
        // i, j
        // 0, 0 diff=0
        // 1, 1
        // 2, 2
        // 0, 1 diff=1
        // 1, 2
        for (int d=0; d < stringLen; d++) {
            for (int i=0; i+d < stringLen; i++) {
                int j = i + d;
                if (d == 0) {
                    isPalindrome[i][j] = true;
                } else if (s.charAt(i)==s.charAt(j) && ((i+1 >= j-1) || isPalindrome[i+1][j-1])) {
                    isPalindrome[i][j] = true;
                    if (d > maxDiff) {
                        maxDiff = d; 
                        maxStart = i; 
                        maxEnd = j; //update max
                    }
                } else {
                    isPalindrome[i][j] = false;
                }
            }
        }
        return s.substring(maxStart, maxEnd + 1);
        
    }
    
}