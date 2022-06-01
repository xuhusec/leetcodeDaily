public class MaximumSubarrayMinProduct {
    private static final int MOD = (int) 1e9 + 7;
    public int maxSumMinProduct(int[] nums) {
        final int n = nums.length;
        long[] presum = new long[n + 1];
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(-1);
        long ans = 0;
        for (int i = 0 ; i < n; ++i) {
            presum[i + 1] = presum[i] + nums[i];

            int cur = nums[i];
            while (stack.size() > 1 && nums[stack.peek()] > cur) {
                int minPos = stack.pop();
                ans = Math.max(ans, nums[minPos] * (presum[i] - presum[stack.peek() + 1]));
            }
            stack.push(i);
        }
        long total = presum[n];
        while (stack.size() > 1) {
            int minPos = stack.pop();
            ans = Math.max(ans, nums[minPos] * (total - presum[stack.peek() + 1]));
        }
        return (int) (ans % MOD);
    }
}