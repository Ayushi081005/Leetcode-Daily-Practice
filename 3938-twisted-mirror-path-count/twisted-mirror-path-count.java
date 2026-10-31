class Solution {
    public int uniquePaths(int[][] grid) {
        final int MOD = 1_000_000_007;
        int m = grid.length, n = grid[0].length;
        int[][] landR = new int[m][n];
        int[][] landD = new int[m][n];

        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (grid[i][j] == 0) {
                    landR[i][j] = landD[i][j] = i * n + j;
                } else {
                    landR[i][j] = (i + 1 < m) ? landD[i + 1][j] : -1;
                    landD[i][j] = (j + 1 < n) ? landR[i][j + 1] : -1;
                }
            }
        }

        int[] dp = new int[m * n];
        dp[0] = 1;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) continue;
                int cur = dp[i * n + j];
                if (cur == 0) continue;

                if (j + 1 < n) {
                    int t = landR[i][j + 1];
                    if (t != -1) {
                        dp[t] = (int) (((long) dp[t] + cur) % MOD);
                    }
                }
                if (i + 1 < m) {
                    int t = landD[i + 1][j];
                    if (t != -1) {
                        dp[t] = (int) (((long) dp[t] + cur) % MOD);
                    }
                }
            }
        }

        return dp[m * n - 1];
    }
}