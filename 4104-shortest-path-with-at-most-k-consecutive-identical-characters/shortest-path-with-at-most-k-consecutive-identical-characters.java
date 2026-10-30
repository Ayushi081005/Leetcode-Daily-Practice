class Solution {
    public int shortestPath(int n, int[][] edges, String labels, int k) {
        List<int[]>[] adj = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            adj[edge[0]].add(new int[] { edge[1], edge[2] });
        }

        PriorityQueue<long[]> pq = new PriorityQueue<>(Comparator.comparingLong(a -> a[0]));

        long[][] minWeight = new long[n][k + 1];
        for (int i = 0; i < n; i++) {
            Arrays.fill(minWeight[i], Long.MAX_VALUE);
        }

        pq.offer(new long[] { 0, 0, 1 });
        minWeight[0][1] = 0;

        while (!pq.isEmpty()) {
            long[] curr = pq.poll();
            long w = curr[0];
            int u = (int) curr[1];
            int count = (int) curr[2];

            if (u == n - 1) {
                return (int) w;
            }

            if (w > minWeight[u][count]) {
                continue;
            }

            for (int[] neighbor : adj[u]) {
                int v = neighbor[0];
                int weight = neighbor[1];

                int nextCount = (labels.charAt(u) == labels.charAt(v)) ? count + 1 : 1;

                if (nextCount <= k) {
                    if (w + weight < minWeight[v][nextCount]) {
                        minWeight[v][nextCount] = w + weight;
                        pq.offer(new long[] { w + weight, v, nextCount });
                    }
                }
            }
        }

        long ans = Long.MAX_VALUE;
        for (int c = 1; c <= k; c++) {
            ans = Math.min(ans, minWeight[n - 1][c]);
        }

        return ans == Long.MAX_VALUE ? -1 : (int) ans;
    }
}