class Solution {
    public int numTrees(int n) {
        int[] uniqueBST = new int[n + 1];
        uniqueBST[0] = 1;
        uniqueBST[1] = 1;

        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                uniqueBST[i] += uniqueBST[j - 1] * uniqueBST[i - j];
            }
        }

        return uniqueBST[n];
    }
}