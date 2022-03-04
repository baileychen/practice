class Solution {
    /*
    Start: 10:52pm
    brute force:
    runtime O(n^2) loop through all n elements, calculate product
    
    space: O(n^2) int[][] of all elements: int[i] = array of nums except i???
    
    Repeated work: doing the same multiplication operations except, adding/removing elements
    
    NOTE: VISUALIZE the problem to see how problems build on smaller sub-problems to reduce repeated work! had to look at a hint/beginning of solution D':
    
    [ ,2,3,4]
    [1, ,3,4]
    [1,2, ,4]
    [1,2,3, ]
    
    left = [1, 1, 1*2, 1*2*3]
    right = [4*3*2, 4*3, 4, 1]
    multiple pairs together
    runtime: O(n)
    space: O(n)
    
    space: O(1) use output array as 2nd array
    
    
    END: 11:23pm (30 min)
    */
    public int[] productExceptSelf(int[] nums) {
        int[] right = new int[nums.length];
        int currRight = 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            System.out.println(currRight);
            right[i] = currRight;
            currRight = currRight * nums[i];
        }
        System.out.println("--");
        
        int currLeft = 1;
        for (int i = 0; i < nums.length; i++) {
            System.out.println(currLeft);
            int num = nums[i];
            nums[i] = currLeft;
            currLeft *= num;
        }
        
        for (int i = 0; i < nums.length; i++) {
            right[i] = right[i] * nums[i];
        }
        return right;
        
        
    }
}