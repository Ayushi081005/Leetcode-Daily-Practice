class Solution {
    public List<Integer> targetIndices(int[] nums, int target) {
        int lessThanTarget = 0;
        int targetCount = 0;
        for (int num : nums) {
            if (num < target) {
                lessThanTarget++;
            } else if (num == target) {
                targetCount++;
            }
        }

        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < targetCount; i++) {
            result.add(lessThanTarget + i);
        }

        return result;
    }
}