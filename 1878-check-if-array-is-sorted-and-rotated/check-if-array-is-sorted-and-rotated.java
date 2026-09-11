class Solution {
    public boolean check(int[] nums) {
                int rotate = 0;

        for(int i=1; i<nums.length; i++){
            if(nums[i] < nums[i-1]){
                rotate++;
            }
        }

        if (nums[nums.length - 1] > nums[0]) {
            rotate++;
        }

        return rotate <= 1;

    }
}