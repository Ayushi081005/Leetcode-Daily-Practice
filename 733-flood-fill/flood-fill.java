class Solution {
    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
         int originalColor = image[sr][sc];
        if(originalColor == color){
            return image;
        }
        boolean[][] visited = new boolean[image.length][image[0].length];

        dfs(image, sr, sc, color, originalColor, visited);

        return image;
    }

    public void dfs(int[][] image, int sr, int sc, int color, int originalColor, boolean[][] visited){
        visited[sr][sc] = true;
        image[sr][sc] = color;

        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        for(int k=0; k<4; k++){
            int newRow = sr + dr[k];
            int newCol = sc + dc[k];

            if(newRow >= 0 && newRow < image.length &&
            newCol >= 0 && newCol < image[0].length &&
            !visited[newRow][newCol] &&
            image[newRow][newCol] == originalColor){

                dfs(image, newRow, newCol, color, originalColor, visited);
            }
        }
    }
}