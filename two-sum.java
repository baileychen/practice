class Solution {
    public int[] twoSum(int[] nums, int target) {
        /**
        Current: 2-pass Hash Table
        Goal: 1-pass Hash Table

        nums = [3,2,4], target = 6
        [2,3,4]
        2, 4
        3, 3
        4, 2
        
        naive: n^2
        
        put numbers into HashMap to get O(1) retrieval: O(n)
        key = number, value = index
        
        What if you have duplicate numbers?
        Map<Integer, List<Integer>>
        
        for each (n, i) in nums:
            if comp = (target - n) is in map:
                if can get comp.index that != i:
                    return (i, comp.index)
            
        return [] // no solution        
                
        Edge case: remember to not use same element 2x
        nums = [3, 2, 4], target = 6
        Answer != (0, 0) // uses 3 twice
        
        Runtime: O(n)
        **/
        // put into map
        Map<Integer, List<Integer>> numToIndex = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if (numToIndex.containsKey(num)) {
                numToIndex.get(num).add(i);
            } else {
                List<Integer> indices = new ArrayList<>();
                indices.add(i);
                numToIndex.put(num, indices);
            }
        }
        
        // pairing logic
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            int complement = target - num;
            if (numToIndex.containsKey(complement)) {
                int complementIndex = getDifferentIndex(numToIndex.get(complement), i);
                if (complementIndex != -1) {
                    return new int[]{i, complementIndex};
                }
            }
        }
        return new int[2];
    }
    
    private int getDifferentIndex(List<Integer> indices, int numIndex) {
        for (int i = 0; i < indices.size(); i++) {
            int compIndex = indices.get(i);
            if (compIndex != numIndex) {
                return compIndex;
            }
        }
        
        return -1;
    }
}