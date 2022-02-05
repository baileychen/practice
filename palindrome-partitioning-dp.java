class Solution {
    private boolean[][] isPalindrome;
    public List<List<String>> partition(String s) {
        List<List<String>> partitions = new ArrayList<List<String>>();
        isPalindrome = buildIsPalindrome(s);
        traverse(0, new ArrayList<String>(), partitions, s);
        return partitions;
    }

    private void traverse(int start, List<String> currList, List<List<String>> partitions, String s) {
        if (start >= s.length()) {
            partitions.add(currList);
            return;
        }

        for (int end = start; end < s.length(); end++) {
            if (isPalindrome[start][end]) {
                List<String> newCurrList = new ArrayList<String>(currList);
                newCurrList.add(s.substring(start, end + 1));
                traverse(end + 1, newCurrList, partitions, s);
            }
        }
    }
    
    boolean[][] buildIsPalindrome(String s) {
        // handle base case where len(s) == 0 

        int len = s.length();
        // initialize len x len matrix where start>=end = true
        boolean[][] isPalindrome = new boolean[len][len];
        for (int start = 0; start < len; start++) {
            boolean[] row = new boolean[len];
            for (int end = 0; end < len; end++) {
                if (start >= end) {
                    row[end] = true;
                }
            }
            isPalindrome[start] = row;
        }

        for (int diff=1; diff < len; diff++) {
            for (int start=0; start < len && ((start + diff) < len); start++) {
                int end = start + diff;
                isPalindrome[start][end] = 
                    s.charAt(start) == s.charAt(end) && 
                    isPalindrome[start + 1][end - 1];
            }


        }
        return isPalindrome;
    }
}