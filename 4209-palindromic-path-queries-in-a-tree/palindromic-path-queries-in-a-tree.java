class Solution {
    int[] head, to, next, edgeHead;
    int edgeCount = 0;
    
    int[] depth, parent, heavy, headNode, pos;
    int curPos = 0;
    
    int[] tree;
    char[] charArr;

    void addEdge(int u, int v) {
        to[edgeCount] = v;
        next[edgeCount] = edgeHead[u];
        edgeHead[u] = edgeCount++;
    }

    int dfs(int u, int p, int d) {
        depth[u] = d;
        parent[u] = p;
        int size = 1;
        int maxSubtree = 0;
        
        for (int i = edgeHead[u]; i != -1; i = next[i]) {
            int v = to[i];
            if (v != p) {
                int subSize = dfs(v, u, d + 1);
                size += subSize;
                if (subSize > maxSubtree) {
                    maxSubtree = subSize;
                    heavy[u] = v;
                }
            }
        }
        return size;
    }

    void decompose(int u, int h) {
        headNode[u] = h;
        pos[u] = curPos++;
        
        if (heavy[u] != -1) {
            decompose(heavy[u], h);
        }
        
        for (int i = edgeHead[u]; i != -1; i = next[i]) {
            int v = to[i];
            if (v != parent[u] && v != heavy[u]) {
                decompose(v, v);
            }
        }
    }

    void buildTree(int node, int start, int end) {
        if (start == end) {
            return;
        }
        int mid = (start + end) / 2;
        buildTree(2 * node, start, mid);
        buildTree(2 * node + 1, mid + 1, end);
    }

    void updateTree(int node, int start, int end, int idx, int val) {
        if (start == end) {
            tree[node] = val;
            return;
        }
        int mid = (start + end) / 2;
        if (idx <= mid) {
            updateTree(2 * node, start, mid, idx, val);
        } else {
            updateTree(2 * node + 1, mid + 1, end, idx, val);
        }
        tree[node] = tree[2 * node] ^ tree[2 * node + 1];
    }

    int queryTree(int node, int start, int end, int l, int r) {
        if (r < start || end < l) {
            return 0;
        }
        if (l <= start && end <= r) {
            return tree[node];
        }
        int mid = (start + end) / 2;
        return queryTree(2 * node, start, mid, l, r) ^ queryTree(2 * node + 1, mid + 1, end, l, r);
    }

    int queryPath(int u, int v, int n) {
        int res = 0;
        while (headNode[u] != headNode[v]) {
            if (depth[headNode[u]] < depth[headNode[v]]) {
                int temp = u; u = v; v = temp;
            }
            int h = headNode[u];
            res ^= queryTree(1, 0, n - 1, pos[h], pos[u]);
            u = parent[h];
        }
        if (depth[u] > depth[v]) {
            int temp = u; u = v; v = temp;
        }
        res ^= queryTree(1, 0, n - 1, pos[u], pos[v]);
        return res;
    }

    public List<Boolean> palindromePath(int n, int[][] edges, String s, String[] queries) {
        edgeHead = new int[n];
        Arrays.fill(edgeHead, -1);
        to = new int[2 * n];
        next = new int[2 * n];
        
        for (int[] edge : edges) {
            addEdge(edge[0], edge[1]);
            addEdge(edge[1], edge[0]);
        }

        depth = new int[n];
        parent = new int[n];
        heavy = new int[n];
        Arrays.fill(heavy, -1);
        headNode = new int[n];
        pos = new int[n];
        
        dfs(0, -1, 0);
        decompose(0, 0);

        tree = new int[4 * n];
        charArr = s.toCharArray();

        for (int i = 0; i < n; i++) {
            updateTree(1, 0, n - 1, pos[i], 1 << (charArr[i] - 'a'));
        }

        List<Boolean> answer = new ArrayList<>();
        for (String qStr : queries) {
            String[] parts = qStr.split(" ");
            if (parts[0].equals("update")) {
                int u = Integer.parseInt(parts[1]);
                char c = parts[2].charAt(0);
                charArr[u] = c;
                updateTree(1, 0, n - 1, pos[u], 1 << (c - 'a'));
            } else {
                int u = Integer.parseInt(parts[1]);
                int v = Integer.parseInt(parts[2]);
                int pathMask = queryPath(u, v, n);
                boolean isPalindrome = (pathMask & (pathMask - 1)) == 0;
                answer.add(isPalindrome);
            }
        }

        return answer;
    }
}