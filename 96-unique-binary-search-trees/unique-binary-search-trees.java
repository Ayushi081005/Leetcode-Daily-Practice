class Solution {
    public int numTrees(int n) {
        return countBST(1, n);
    }
    
    private int countBST(int start, int end) {
        if (start >= end) {
            return 1;
        }
        
        int total = 0;
        for (int i = start; i <= end; i++) {
            int left = countBST(start, i - 1);
            int right = countBST(i + 1, end);
            total += left * right;
        }
    return total;
    }
}