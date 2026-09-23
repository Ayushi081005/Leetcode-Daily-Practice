class Solution {
    public boolean isBipartite(int[][] graph) {
        int[] color = new int[graph.length];
        Arrays.fill(color, -1);

        for(int i=0; i<graph.length; i++){
            if(color[i] == -1){
                if(!bfs(i, graph, color)){
                    return false;
                }
            }
        }

        return true;
    }

    public boolean bfs(int i, int[][] graph, int[] color){
        color[i] = 0;

        Queue<Integer> q = new LinkedList<>();
        q.add(i);

        while(!q.isEmpty()){
            int curr = q.poll();

            for(int neighbour : graph[curr]){
                if(color[neighbour] == -1){
                    color[neighbour] = 1 - color[curr];
                    q.add(neighbour);
                }else if(color[curr] == color[neighbour]){
                    return false;
                }
            }
        }

        return true;
    }
}