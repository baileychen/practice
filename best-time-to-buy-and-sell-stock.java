class Solution {
    /*
    Start: 11:13pm
    optimal solution end: 11:32pm (19min)
    NOTES: need to run WHOLE method when running test cases
    */
    public int maxProfit(int[] prices) {
        /*
        Example:
        prices = [2, 1, 5, 3, 2, 10, 8, 9]
        output = 9 = 10 - 1
        profit = buy - sell;
        buy < sell
        
        O(n)
        initially set buy = 0
        set sell to buy, sell++ increment
        if increasing order (more recent > buy), profit = max(profit, sell - buy)
        if decrease in order, set buyPrice = most recent
        
        
        int profit = 0
        */
        int max_profit = 0;
        int buyPrice = Integer.MAX_VALUE;
        for (int sellPrice : prices) {
            if (sellPrice > buyPrice) {
                max_profit = Math.max(sellPrice - buyPrice, max_profit);
            } else if (sellPrice < buyPrice) {
                buyPrice = sellPrice;
            }
        }
        return max_profit;
    }
}