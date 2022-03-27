public class MaximizetheTopmostElementAfterKMoves {
    public int maximumTop(int[] nums, int k) {
        if (nums.length == 1) {
            return k % 2 == 0 ? nums[0] : -1;
        }
        int max = Integer.MIN_VALUE;
        if (nums.length < k) {
            for (int num : nums) {
                max = Math.max(max, num);
            }
            return max;
        }

        for (int i = 0; i < k - 1; ++i) {
            max = Math.max(max, nums[i]);
        }

        if (k < nums.length) {
            max = Math.max(max, nums[k]);
        }
        return max;
    }
}