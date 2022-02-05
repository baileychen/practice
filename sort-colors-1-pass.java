class Solution {
    /*
    Start: 10:53pm
    1-pass w/ O(1) space End: 11:20pm (27 MIN D':)
    
    RWB, in-place sort -> order RWB (012)
     0 1 2 3 4 5
    [2 0 2 1 1 0] -> curr = 3, zeroRight = 2, twoLeft = 3
    [0 0 1 1 2 2]
    keep curr if swap happened since currNum is now different
    currNum = 1 valid condition: zeroRight <= curr <= twoLeft
    
     0 1 2 3 4 5
    [1 0 2 2 1 0] -> curr = 3, zeroRight = 2, twoLeft = 3
    [0 0 1 1 2 2]
    [0 0 1 1 2 2]
    
    swaps only happen when currNum = 0 or 2.
    Stop when curr == twoLeft
    
    
     0 1 2 3 4 5
    [2 0 2 1 1 0] -> 1-pass
    [2 X X X X X] i = 1, prev = 2, curr = 0
    linked list to swap around order fast?
    {0:1, 1:0, 2:1}
    
    [0 0 1 1 2 2]
    
    
     0 1 2 3 4 5
    [0 0 2 2 1 0] curr = 0, zeroRight = 0, twoLeft = 5
    [0 0 0 1 2 2]
    
    [1 1 1 0] curr = 0, zeroRight = 0, twoLeft = 1
    [0 1 1 1]
    [2 1 2]
    [2 1 2]
    
     0 1 2 
    [1 0 2] curr = 1, zeroRight = 0, twoLeft = 1
    */
    public void sortColors(int[] nums) {
        // 1-pass using linked list
        int curr = 0; 
        int zeroRight = 0;
        int twoLeft = nums.length - 1;
        
        while (curr <= twoLeft) {
            int currNum = nums[curr];
            if (currNum == 0) {
                // see if need swap
                boolean needSwap = curr != zeroRight;
                if (needSwap) {
                    int temp = nums[zeroRight];
                    nums[zeroRight] = currNum;
                    nums[curr] = temp;
                } else {
                    curr++;
                }
                zeroRight++;
            } else if (currNum == 1) {
                curr++;
            } else if (currNum == 2) {
                boolean needSwap = curr != twoLeft;
                if (needSwap) {
                    int temp = nums[twoLeft];
                    nums[twoLeft] = currNum;
                    nums[curr] = temp;
                } else {
                    curr++;
                }
                twoLeft--;
            }
        }
       
    }
    

}