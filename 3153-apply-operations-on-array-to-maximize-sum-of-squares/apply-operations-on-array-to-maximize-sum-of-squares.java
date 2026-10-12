class Solution {
    public int maxSum(List<Integer> nums, int k) {
        int[] bitCounts = new int[30];
        for (int num : nums) {
            for (int i = 0; i < 30; i++) {
                if ((num & (1 << i)) != 0) {
                    bitCounts[i]++;
                }
            }
        }

        long[] reconstructed = new long[nums.size()];
        for (int i = 0; i < nums.size(); i++) {
            long currentVal = 0;
            for (int bit = 0; bit < 30; bit++) {
                if (bitCounts[bit] > 0) {
                    currentVal |= (1L << bit);
                    bitCounts[bit]--;
                }
            }
            reconstructed[i] = currentVal;
        }

        Arrays.sort(reconstructed);
        
        long mod = 1_000_000_007;
        long totalSum = 0;
        
        for (int i = reconstructed.length - 1; i >= reconstructed.length - k; i--) {
            long val = reconstructed[i] % mod;
            totalSum = (totalSum + (val * val) % mod) % mod;
        }
        
        return (int) totalSum;
    }
}