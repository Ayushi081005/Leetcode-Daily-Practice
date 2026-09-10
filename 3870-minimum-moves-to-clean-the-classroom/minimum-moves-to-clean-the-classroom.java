import java.util.*;

class Solution {
    public int minMoves(String[] classroom, int maxEnergy) {
        int m = classroom.length;
        int n = classroom[0].length();
        
        int startX = -1, startY = -1;
        List<int[]> litterList = new ArrayList<>();
        
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                char ch = classroom[r].charAt(c);
                if (ch == 'S') {
                    startX = r;
                    startY = c;
                } else if (ch == 'L') {
                    litterList.add(new int[]{r, c});
                }
            }
        }
        
        int numLitter = litterList.size();
        int fullMask = (1 << numLitter) - 1;
        
        int[][] litterIdx = new int[m][n];
        for (int i = 0; i < m; i++) Arrays.fill(litterIdx[i], -1);
        for (int i = 0; i < numLitter; i++) {
            int[] pos = litterList.get(i);
            litterIdx[pos[0]][pos[1]] = i;
        }
        
        int startMask = litterIdx[startX][startY] != -1 ? (1 << litterIdx[startX][startY]) : 0;
        if (startMask == fullMask) return 0;
        
        int[][][] bestEnergy = new int[m][n][1 << numLitter];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                Arrays.fill(bestEnergy[i][j], -1);
            }
        }
        
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{startX, startY, startMask, maxEnergy});
        bestEnergy[startX][startY][startMask] = maxEnergy;
        
        int moves = 0;
        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            moves++;
            
            for (int i = 0; i < size; i++) {
                int[] curr = queue.poll();
                int r = curr[0], c = curr[1], mask = curr[2], energy = curr[3];
                
                for (int[] d : dirs) {
                    int nr = r + d[0], nc = c + d[1];
                    
                    if (nr < 0 || nr >= m || nc < 0 || nc >= n || classroom[nr].charAt(nc) == 'X') {
                        continue;
                    }
                    
                    int nEnergy = energy - 1;
                    if (nEnergy < 0) continue;
                    
                    char cell = classroom[nr].charAt(nc);
                    if (cell == 'R') nEnergy = maxEnergy;
                    
                    int nMask = mask;
                    if (cell == 'L') {
                        int idx = litterIdx[nr][nc];
                        if (idx != -1) nMask |= (1 << idx);
                    }
                    
                    if (nMask == fullMask) return moves;
                    
                    if (nEnergy > bestEnergy[nr][nc][nMask]) {
                        bestEnergy[nr][nc][nMask] = nEnergy;
                        queue.offer(new int[]{nr, nc, nMask, nEnergy});
                    }
                }
            }
        }
        
        return -1;
    }
}