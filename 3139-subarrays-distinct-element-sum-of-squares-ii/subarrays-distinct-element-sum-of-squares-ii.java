class Solution {
    private static final int MOD = 1000000007;

    static class SegmentTree {
        int n;
        long[] sum;
        long[] lazy;

        public SegmentTree(int n) {
            this.n = n;
            sum = new long[4 * n];
            lazy = new long[4 * n];
        }

        private void push(int node, int l, int r) {
            if (lazy[node] != 0) {
                int mid = (l + r) / 2;
                long val = lazy[node];

                lazy[2 * node] = (lazy[2 * node] + val) % MOD;
                sum[2 * node] = (sum[2 * node] + val * (mid - l + 1)) % MOD;

                lazy[2 * node + 1] = (lazy[2 * node + 1] + val) % MOD;
                sum[2 * node + 1] = (sum[2 * node + 1] + val * (r - mid)) % MOD;

                lazy[node] = 0;
            }
        }

        public void update(int node, int l, int r, int ql, int qr, long val) {
            if (ql > r || qr < l) return;
            if (ql <= l && r <= qr) {
                lazy[node] = (lazy[node] + val) % MOD;
                sum[node] = (sum[node] + val * (r - l + 1)) % MOD;
                return;
            }
            push(node, l, r);
            int mid = (l + r) / 2;
            update(2 * node, l, mid, ql, qr, val);
            update(2 * node + 1, mid + 1, r, ql, qr, val);
            sum[node] = (sum[2 * node] + sum[2 * node + 1]) % MOD;
        }

        public long query(int node, int l, int r, int ql, int qr) {
            if (ql > r || qr < l) return 0;
            if (ql <= l && r <= qr) return sum[node];
            push(node, l, r);
            int mid = (l + r) / 2;
            return (query(2 * node, l, mid, ql, qr) + query(2 * node + 1, mid + 1, r, ql, qr)) % MOD;
        }
    }

    public int sumCounts(int[] nums) {
        int n = nums.length;
        SegmentTree tree = new SegmentTree(n);
        
        java.util.HashMap<Integer, Integer> lastSeen = new java.util.HashMap<>();
        long currentSumSquares = 0;
        long totalSum = 0;

        for (int j = 0; j < n; j++) {
            int lastIdx = lastSeen.getOrDefault(nums[j], -1);
            
            long querySum = tree.query(1, 0, n - 1, lastIdx + 1, j);
            currentSumSquares = (currentSumSquares + 2 * querySum + (j - lastIdx)) % MOD;
            
            totalSum = (totalSum + currentSumSquares) % MOD;
            
            tree.update(1, 0, n - 1, lastIdx + 1, j, 1);
            
            lastSeen.put(nums[j], j);
        }

        return (int) totalSum;
    }
}