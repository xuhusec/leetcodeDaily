public class SumofSubarrayRanges {
    public long subArrayRanges(int[] nums) {
        long sumMax = 0;
        Deque<Integer> stack = new ArrayDeque<>();
        final int n = nums.length;
        stack.push(-1);
        for (int i = 0; i < n; ++i) {
            int cur = nums[i];

            while (stack.size() > 1 && nums[stack.peek()] <= cur) {
                int pos = stack.pop();
                sumMax += (i - pos) * (pos - stack.peek()) * ((long)nums[pos]);
            }
            stack.push(i);
        }

        while (stack.size() > 1) {
            int pos = stack.pop();
            sumMax += (n - pos) * (pos - stack.peek()) * ((long)nums[pos]);
        }
        long sumMin = 0;
        for (int i = 0; i < n; ++i) {
            int cur = nums[i];

            while (stack.size() > 1 && nums[stack.peek()] >= cur) {
                int pos = stack.pop();
                sumMin += (i - pos) * (pos - stack.peek()) * ((long)nums[pos]);
            }
            stack.push(i);
        }

        while (stack.size() > 1) {
            int pos = stack.pop();
            sumMin += (n - pos) * (pos - stack.peek()) * ((long)nums[pos]);
        }

        return sumMax - sumMin;
    }
}