class TrappingRainWater02 {
    //Two pointer
    public int trap(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int leftMax = height[left];
        int rightMax = height[right];

        int res = 0;
        while (left < right) {
            if (leftMax < rightMax) {
                int cur = height[++left];
                if (cur > leftMax) {
                    leftMax = cur;
                } else {
                    res += leftMax - cur;
                }
            } else {
                int cur = height[--right];
                if (cur > rightMax) {
                    rightMax = cur;
                } else {
                    res += rightMax - cur;
                }
            }
        }
        return res;
    }
}