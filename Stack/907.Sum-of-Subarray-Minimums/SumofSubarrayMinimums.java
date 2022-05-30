public class SumofSubarrayMinimums {
    private static final  int MOD = (int) 1e9 + 7;
    public int sumSubarrayMins(int[] arr) {
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(-1);
        final int n = arr.length;
        long ans = 0;

        for (int i = 0; i < n; ++i) {
            int curVal = arr[i];
            while (stack.size() > 1 && arr[stack.peek()] >= curVal) {
                int prev = stack.pop();
                long preVal = arr[prev];
                ans = (ans + preVal * (i - prev) * (prev - stack.peek())) % MOD;
            }
            stack.push(i);
        }

        while (stack.size() > 1) {
            int prev = stack.pop();
            long preVal = arr[prev];
            ans = (ans + preVal * (n - prev) * (prev - stack.peek())) % MOD;
        }

        return (int) ans;
    }
}
