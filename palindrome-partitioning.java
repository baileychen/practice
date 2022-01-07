class Solution {
    public List<List<String>> partition(String s) {
        List<List<String>> partitions = new ArrayList<List<String>>();
        traverse(0, new ArrayList<String>(), partitions, s);
        return partitions;
    }

    private void traverse(int start, List<String> currList, List<List<String>> partitions, String s) {
        if (start >= s.length()) {
            partitions.add(currList);
            return;
        }

        for (int end = start; end < s.length(); end++) {
            if (isPalindrome(s, start, end)) {
                List<String> newCurrList = new ArrayList<String>(currList);
                newCurrList.add(s.substring(start, end + 1));
                traverse(end + 1, newCurrList, partitions, s);
            }
        }
    }

    private boolean isPalindrome(String s, int start, int end) {
        while (start < end) {
            if (s.charAt(start) == s.charAt(end)) {
                start+=1;
                end-=1;
            } else {
                return false;
            }
        }
        return true;
    }
}