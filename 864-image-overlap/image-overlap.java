class Solution {
    public int largestOverlap(int[][] img1, int[][] img2) {
        int n = img1.length;
        int[] bits1 = new int[n];
        int[] bits2 = new int[n];

        // Convert each row to a bitmask integer
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                bits1[i] = (bits1[i] << 1) | img1[i][j];
                bits2[i] = (bits2[i] << 1) | img2[i][j];
            }
        }

        int maxOverlap = 0;

        // Try all row shifts (dr) and column shifts (dc)
        for (int dr = -n + 1; dr < n; dr++) {
            for (int dc = -n + 1; dc < n; dc++) {
                int overlap = 0;
                for (int r = 0; r < n; r++) {
                    int r2 = r + dr;
                    if (r2 >= 0 && r2 < n) {
                        // Shift columns of img1 to align with img2
                        int shiftedRow = (dc >= 0) ? (bits1[r] >> dc) : (bits1[r] << -dc);
                        overlap += Integer.bitCount(shiftedRow & bits2[r2]);
                    }
                }
                maxOverlap = Math.max(maxOverlap, overlap);
            }
        }

        return maxOverlap;
    }
}