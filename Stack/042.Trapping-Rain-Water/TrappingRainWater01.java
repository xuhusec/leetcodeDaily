class TrappingRainWater01 {
    // Three pass
    public int trap(int[] height) {
        final int N = height.length;
        int[] left = new int[N];
        left[0] = height[0];
        for (int i = 1; i < N; ++i) {
            left[i] = Math.max(left[i-1], height[i]);
        }
        int[] right = new int[N];
        right[N-1] = height[N-1];
        for (int i = N - 2; i >= 0; --i) {
            right[i] = Math.max(right[i+1], height[i]);
        }
        int res = 0;
        for (int i = 0; i < N; ++i) {
            res += Math.min(left[i], right[i]) - height[i];
        }
        return res;
    }
}