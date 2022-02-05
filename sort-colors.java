class Solution {
    /*
    Start: 10:54pm
    1-pass w/ LinkedList End: 11:30pm (36 min D':)
    
    RWB, in-place sort -> order RWB (012)
     0 1 2 3 4 5
    [2 0 2 1 1 0] ->
    [0 0 1 1 2 2] naive: {0: 2, 1:2, 2:2} Runtime O(n), Space: O(n)
    
     0 1 2 3 4 5
    [2 0 2 1 1 0] -> 1-pass
    [2 X X X X X] i = 1, prev = 2, curr = 0
    linked list to swap around order fast?
    {0:1, 1:0, 2:1}
    
    [0 0 1 1 2 2]
    
    */
    public void sortColors(int[] nums) {
        // 1-pass using linked list
        LinkedList<Integer> sorted = new LinkedList<Integer>();
        int[] numColors = new int[3]; // [0 0 0]
        int maxSoFar = 0;
        for (int i = 0; i < nums.length; i++) {
            int currNum = nums[i];
            numColors[currNum] = numColors[currNum] + 1; //0 {0:1, 1:0, 2:1}
            if (currNum < maxSoFar) { // need to change order
                sorted.add(getInsertIndex(i, currNum, maxSoFar, numColors), currNum);
            } else {
                sorted.add(currNum);
            }
            maxSoFar = Math.max(maxSoFar, currNum);
        }
        
        // copy linked list into int[] nums
        for (int i = 0; i < nums.length; i++) {
            nums[i] = sorted.get(i);
        }    
        
    }
    
    private int getInsertIndex(int startIndex, int currNum, int maxSoFar, int[] numColors) {
        // currNum = 0; maxSoFar = 2, numColors = {0:1, 1:0, 2:1}
        int index = startIndex; // 1
        for (int i = maxSoFar; i > currNum; i--) {
            index -= numColors[i]; // i = 2
        }
        return index;
        
    }
}