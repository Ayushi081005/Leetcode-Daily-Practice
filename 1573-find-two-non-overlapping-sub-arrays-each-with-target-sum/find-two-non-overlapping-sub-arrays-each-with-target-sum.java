class Solution {
    public int minSumOfLengths(int[] arr, int target) {
        int n = arr.length;
        int[] minLen = new int[n];
        Arrays.fill(minLen, Integer.MAX_VALUE);
        
        int left = 0;
        int currentSum = 0;
        int minTotalLength = Integer.MAX_VALUE;
        int currentMinLen = Integer.MAX_VALUE;
        
        for (int right = 0; right < n; right++) {
            currentSum += arr[right];
            
            while (currentSum > target) {
                currentSum -= arr[left];
                left++;
            }
            
            if (currentSum == target) {
                int currSubarrayLen = right - left + 1;
                
                if (left > 0 && minLen[left - 1] != Integer.MAX_VALUE) {
                    minTotalLength = Math.min(minTotalLength, currSubarrayLen + minLen[left - 1]);
                }
                
                currentMinLen = Math.min(currentMinLen, currSubarrayLen);
            }
            
            minLen[right] = currentMinLen;
        }
        
            if (minTotalLength > n) {
            return -1;
}
        return minTotalLength;
    }
}