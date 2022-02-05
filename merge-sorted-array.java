class Solution {
    /*
    Start: 11:28pm
    Start2: 8:53pm
    End2: 9:17pm
    Example:
    nums1 = [1,2,2,4,6,9] nums2 = [0,0,1,3,5,6]
    output = [0,1,1,2,2,3,4,5,6,6,9]
    O(m+n)
    */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        /*
        can copy... O(2m + n) = O(m + n)
        int tempNums1StoreStart = 4;
        int tempNums1CompareStart = 1;
        int[m] nums1 temp save value = [1,2,2,4,6,-inf]
        int nums1pointer = 0
        int nums2pointer = 2
        nums1 = [0,0,1,1,6,9,0,0,0,0,0]
        nums2 = [0,0,1,3,5,6]
        */
        
        /*
        int nums1ptr = 0;
        int nums2ptr = 0;
        while nums1ptr
        */
        // copy nums1 into int[m]
        // use original nums1 to store m + n length array
        
        int[] nums1copy = Arrays.copyOf(nums1, m);
        
        int nums1ptr = 0;
        int nums2ptr = 0;
        int retPtr = 0;
        
        while (nums1ptr < m && nums2ptr < n) {
            if (nums1copy[nums1ptr] <= nums2[nums2ptr]) {
                // copy nums1 value, move nums1ptr
                nums1[retPtr] = nums1copy[nums1ptr];
                nums1ptr++;
            } else {
                // copy nums2 value, move nums2ptr
                nums1[retPtr] = nums2[nums2ptr];
                nums2ptr++;
            }
            retPtr++;
        }
        
        if (nums1ptr >= m) {
            // copy remaining nums2ptr array
            while (nums2ptr < n) {
                nums1[retPtr] = nums2[nums2ptr];
                nums2ptr++;
                retPtr++;      
            }
        } else {
            // copy remaining nums1ptr array
            while (nums1ptr < m) {
                nums1[retPtr] = nums1copy[nums1ptr];
                nums1ptr++;
                retPtr++;
            }
        }
        
    }
}