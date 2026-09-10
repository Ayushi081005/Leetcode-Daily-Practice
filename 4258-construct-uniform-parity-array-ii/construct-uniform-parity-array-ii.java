class Solution {
    public boolean uniformArray(int[] nums1) {
        int minVal = Integer.MAX_VALUE;
        int oddCount = 0;

        for (int x : nums1) {
            if (x < minVal) {
                minVal = x;
            }
            if (x % 2 != 0) {
                oddCount++;
            }
        }

        if (minVal % 2 == 0) {
            return oddCount == 0;
        }

        return true;
    }
}