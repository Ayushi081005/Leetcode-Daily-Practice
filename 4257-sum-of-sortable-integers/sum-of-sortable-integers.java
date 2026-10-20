class Solution {
    public int sortableIntegers(int[] nums) {
        int n = nums.length;

        int[] prefMax = new int[n + 1];
        int[] suffMin = new int[n + 1];
        int[] prefDrop = new int[n];

        for (int i = 0; i < n; i++) {
            prefMax[i + 1] = Math.max(prefMax[i], nums[i]);
        }
        suffMin[n] = Integer.MAX_VALUE;
        for (int i = n - 1; i >= 0; i--) {
            suffMin[i] = Math.min(nums[i], suffMin[i + 1]);
        }

        for (int i = 1; i < n; i++) {
            prefDrop[i] = prefDrop[i - 1];

            if (nums[i - 1] > nums[i]) {
                prefDrop[i]++;
            }
        }

        int totalSum = 0;

        // Check only divisors of n
        for (int k = 1; k <= n / k; k++) {
            if (n % k != 0) {
                continue;
            }

            if (isSortable(nums, prefMax, suffMin, prefDrop, k)) {
                totalSum += k;
            }

            int other = n / k;

            if (other != k &&
                isSortable(nums, prefMax, suffMin, prefDrop, other)) {
                totalSum += other;
            }
        }

        return totalSum;
    }

    private boolean isSortable(
        int[] nums,
        int[] prefMax,
        int[] suffMin,
        int[] prefDrop,
        int k
    ) {
        int n = nums.length;

        for (int start = 0; start < n; start += k) {
            int end = start + k - 1;

            int drops = prefDrop[end] - prefDrop[start];

            if (nums[end] > nums[start]) {
                drops++;
            }

            if (drops > 1) {
                return false;
            }
            if (prefMax[start] > suffMin[start]) {
                return false;
            }
        }

        return true;
    }
}
