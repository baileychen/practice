class Solution {
    /*
    Start: 11:39
    blocked: 11:53pm (14min)
     0. 1. 2  3
    [-2, 2,-3,-4]
    [2, 2, -2, 2, 2]
    output = 2*-3*-4
    [2, 3,-2, 4]
    output = 6
    negatives (-) can switch big negative number to big positive number
    keep track of: currProduct
    
    
    - maxNeg * currNum --> see if overtakes maxPos
    
    return biggest positive number
    
    
    caveats: 0 
    anytime a number is NOT worth multiplying?
    - maybe 0? maybe (-)?
    
    runtime O(n)?
    
    New Strategy: 12am
    - get indexes of 0s
    - between each 0, get the sign +/-
        - if +: max is product of all elements
        - if -: max is product of all elements removing first (-)
    
    move currStart,currEnd=i when get 0
    
    compare to get current max
    - current num
    - maxPos AFTER updating with num
    Update
    - maxPos
    - maxNeg
    
    LESSONS LEARNED:
    - can simplify starting values by starting at i=1 instead of i=0 (help remove `null` case)
    - think of simpler solutions rather than covering all your edge cases
    */
    public int maxProduct(int[] nums) {
        if (nums.length == 0) {
            return nums[0];
        }
        Integer maxProduct = nums[0];
        Integer minSoFar = nums[0];
        Integer maxSoFar = nums[0];
        for (int i=1; i< nums.length; i++) {
            int num = nums[i];
            int newMaxSoFar = Math.max(num, Math.max(minSoFar * num, maxSoFar * num));
            int newMinSoFar = Math.min(num, Math.min(minSoFar * num, maxSoFar * num));
            maxSoFar = newMaxSoFar;
            // maxSoFar: Max of
            // - minSoFar * num
            // - maxSoFar * num
            // - num
            minSoFar = newMinSoFar;
            maxProduct = Math.max(maxProduct, Math.max(maxSoFar, minSoFar));
        }
        
        return maxProduct;
        
    }
}