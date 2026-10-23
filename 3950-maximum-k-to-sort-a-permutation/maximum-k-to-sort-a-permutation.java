class Solution {
    public int sortPermutation(int[] nums) {
        int n = nums.length;
        int k = 0;
        boolean isSorted = true;
        
        for (int i = 0; i < n; i++) {
            if (nums[i] != i) {
                if (isSorted) {
                    k = nums[i];
                    isSorted = false;
                } else {
                    k &= nums[i];
                }
            }
        }
        return isSorted ? 0 : k;
    }
}