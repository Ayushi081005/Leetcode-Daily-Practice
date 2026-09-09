class Solution {
    public long countCommas(long n) {
        long totalcommas = 0;
for (long threshold = 1000, commas = 1; threshold <= n; threshold *= 1000, commas++) {
            long nextThreshold = threshold * 1000;
long count = Math.min(n + 1, nextThreshold) - threshold;
            totalcommas += count * commas;
        }
        
        return totalcommas;
    }
}