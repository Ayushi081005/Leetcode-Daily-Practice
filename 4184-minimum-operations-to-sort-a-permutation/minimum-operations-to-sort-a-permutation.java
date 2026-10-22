class Solution {
    public int minOperations(int[] nums) {
        int n = nums.length;

        int index0 = -1;
        int indexLast = -1;

        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) index0 = i;
            if (nums[i] == n - 1) indexLast = i;
        }

        boolean ascending = true;
        for (int i = 0; i < n; i++) {
            if (nums[(index0 + i) % n] != i) {
                ascending = false;
                break;
            }
        }

        boolean descending = true;
        for (int i = 0; i < n; i++) {
            if (nums[(indexLast + i) % n] != n - 1 - i) {
                descending = false;
                break;
            }
        }

        if (!ascending && !descending) return -1;

        int answer = Integer.MAX_VALUE;

        if (ascending) {
            int cost = index0;
            if (index0 > 0) {
                cost = Math.min(index0, n - index0 + 2);
            }
            answer = Math.min(answer, cost);
        }

        if (descending) {
            answer = Math.min(answer, 1 + Math.min(indexLast, n - 1 - index0));
        }

        return answer;
    }
}