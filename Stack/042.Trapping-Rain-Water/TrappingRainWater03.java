import java.util.ArrayDeque;
import java.util.Deque;

class TrappingRainWater03 {
    //Stack
    public int trap(int[] height) {
        int res = 0;
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < height.length; ++i) {
            int h = height[i];
            while (!stack.isEmpty() && height[stack.peek()] < h) {
                int base = height[stack.pop()];
                if (!stack.isEmpty()) {
                    int prev = stack.peek();
                    res += (Math.min(height[prev], h) - base)* (i - prev - 1);
                }
            }
            stack.push(i);
        }
        return res;
    }

    public int trap2(int[] height) {
        int res = 0;
        int[] st = new int[height.length];
        int id = 0;
        for (int i = 0; i < height.length; ++i) {
            int h = height[i];
            while (id > 0 && height[st[id-1]] < h) {
                int base = height[st[--id]];                
                if (id > 0) {
                    int prev = st[id - 1];
                    res += (Math.min(height[prev], h) - base)* (i - prev - 1);
                }
            }
            st[id++] = i;
        }
        return res;
    }
}