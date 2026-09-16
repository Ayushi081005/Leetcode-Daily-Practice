class Solution {
    public int numberOfSets(int n, int k) {
        int MOD = 1_000_000_007;
        int total = n + k - 1;
        int r = 2 * k;
        
        if (total < r || r < 0) {
            return 0;
        }
        long[] dp = new long[r + 1];
        dp[0] = 1;
        
        for (int i = 1; i <= total; i++) {
            for (int j = Math.min(i, r); j > 0; j--) {
                dp[j] = (dp[j] + dp[j - 1]) % MOD;
            }
        }
        
        return (int) dp[r];
    }
}