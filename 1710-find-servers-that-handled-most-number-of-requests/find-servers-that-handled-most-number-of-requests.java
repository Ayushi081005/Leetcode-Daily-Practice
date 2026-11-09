class Solution {
    public List<Integer> busiestServers(int k, int[] arrival, int[] load) {
        TreeSet<Integer> available = new TreeSet<>();
        for (int i = 0; i < k; i++) {
            available.add(i);
        }
        PriorityQueue<int[]> busy = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));

        int[] requestCount = new int[k];
        int maxRequests = 0;

        for (int i = 0; i < arrival.length; i++) {
            int currTime = arrival[i];
            int duration = load[i];

            while (!busy.isEmpty() && busy.peek()[0] <= currTime) {
                available.add(busy.poll()[1]);
            }

            if (available.isEmpty()) {
                continue;
            }
            Integer serverId = available.ceiling(i % k);
            if (serverId == null) {
                serverId = available.first();
            }

            available.remove(serverId);
            busy.offer(new int[] { currTime + duration, serverId });
            
            requestCount[serverId]++;
            maxRequests = Math.max(maxRequests, requestCount[serverId]);
        }
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            if (requestCount[i] == maxRequests) {
                result.add(i);
            }
        }

        return result;
    }
}