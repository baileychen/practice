class Solution:
    def twoSum(self, nums: List[int], target: int) -> List[int]:
        # 1-pass hash table
        hashMap = {}
        for i in range(len(nums)):
            num = nums[i]
            complement = target - num
            if complement in hashMap and hashMap[complement] != i:
                return [i, hashMap[complement]]
            hashMap[num] = i
                
            