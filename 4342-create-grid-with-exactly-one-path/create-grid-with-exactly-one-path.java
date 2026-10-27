class Solution {
    public String[] createGrid(int m, int n) {
        char[][] grid = new char[m][n];
        
        for (int i = 0; i < m; i++) {
            Arrays.fill(grid[i], '#');
        }
        
        for (int j = 0; j < n; j++) {
            grid[0][j] = '.';
        }
        
        for (int i = 0; i < m; i++) {
            grid[i][n - 1] = '.';
        }
        String[] result = new String[m];
        for (int i = 0; i < m; i++) {
            result[i] = new String(grid[i]);
        }
        
        return result;
    }
}