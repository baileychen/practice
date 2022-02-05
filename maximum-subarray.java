class Solution {
    /*
    1/6/2021
    Start: 11:47pm
    pause - I am stuck D': boo: 12:14am (27 min)
    */
    public int maxSubArray(int[] nums) {
        /*
        Naive O(n^2): iterate through all possible start, end indices and get biggest sum
        build sums on top of each other 
        nums = [-2,9,-99,1,|1,9,9,-5,4]
                 0 1  2  3 |4 5 6  7 8
        first = maxSubArray(nums[0, 3]); -> [a,b]
        second = maxSubArray(nums[4, 8]) -> [c, d]
        maxSubArray(nums) = if b+1=c ? first + second : Max(first, second) --> divide & conquer! O(n)
        
        int[] helper(start, end, nums);
        base case: 
        if arr.length = 0: return [-1, -1, 0]
        if arr.length = 1: return [start, end, arr[0]]
        recursive case: need to return
         - start
         - end 
         - sum
        medium;
        get first: helper(start, med, arr);
        get second: helper(med + 1, end, arr);
        get combination: of first.start to second.end.
        return max of (first, second, combination)
        */
        // return helper(0, nums.length - 1, nums);
        
        int best_sum = nums[0];
        int current_sum = nums[0];
        for (int i = 1; i < nums.length; i++) {
            current_sum = Math.max(nums[i], current_sum + nums[i]); // favor negative over positive subarray
            best_sum = Math.max(best_sum, current_sum);
        }
        return best_sum;
        
    }
    
}