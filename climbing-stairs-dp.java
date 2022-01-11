class Solution {
    /*
    Start: 10:25pm
    No DP: 10:35pm end
    DP: 10:45pm (20 min)
    NOTES: EXACTLY THE SAME AS FIBONACCI NUMBERS!!! My mind is blown
    Feedback: need to test code manually before clicking "Run Code"
    */
    public int climbStairs(int n) {
        /*
        DP? recursion? How to avoid recursion to prevent memory/stack call usage?
        climbStairs(3) = 
        climbStairs(3 - 1) 1 step +
        climbStairs(3 - 2) 2 steps
        Use DP to cut down on runtime. First do brute force/no DP
        
            3
          2    1
         1 0  0 -1X
        0
        a lot of repeated calls like climbStairs(1) O(2^n)?
        reminds me of fibonacci numbers
        fib(n) = fib(n-1) + fib(n-2)
        maybe do iteratively?
        
        
        
        */
        // edge case when n = 1, n = 0, n < 0
        
        int[] numStepWays = new int[n + 1]; //[0,1,2,3]
        numStepWays[0] = 1;
        numStepWays[1] = 1;
        for (int i = 2; i <= n; i++) {
            numStepWays[i] = numStepWays[i - 1] + numStepWays[i - 2];
        }
        return numStepWays[n];
    }
    
//     private int climbStairsRecursiveNoDp(int n) {
//         if (n < 0) {
//             return 0;
//         }
//         if (n == 0) {
//             return 1;
//         }
//         return climbStairs(n - 1) + climbStairs(n - 2);
//     }
    
}