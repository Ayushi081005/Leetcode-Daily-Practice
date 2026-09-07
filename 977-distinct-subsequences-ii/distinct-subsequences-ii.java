class Solution {
    public int distinctSubseqII(String s) {
        long MOD = 1_000_000_007;
        long tot = 0;
        long[] dp = new long[26];
        
        for (char c : s.toCharArray()) {
            int idx = c - 'a';
            long newSubseq = (tot + 1 - dp[idx] + MOD) % MOD;
            tot = (tot + newSubseq) % MOD;
            dp[idx] = (dp[idx] + newSubseq) % MOD;
        }
        
        return (int) tot;
    }
}