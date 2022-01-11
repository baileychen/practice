class Solution {
    /*Variation: return best start and end index*/
    public int maxSubArray(int[] nums) {
    
        
        int best_start = 0;
        int best_end = 0;
        int best_sum = nums[0];
        int current_sum = nums[0];
        for (int i = 1; i < nums.length; i++) {
            int n = nums[i];
            if (n > current_sum + n) {
                // start over
                best_start = i;
                best_end = i;
            }
            // set best_sum
            // current_sum = 1, best_sum = 6, best_start = 3, best_end = 6, n = -5
            current_sum = Math.max(n, current_sum + n); // favor positive over negative subarray
            // current_sum = 1
            if (current_sum > best_sum) {
                // extend subarray
                best_end = i;
            }
            best_sum = Math.max(best_sum, current_sum);
        }
        System.out.println(best_sum + " " + best_start + " " + best_end);
        return best_sum;
        
    }
    
}