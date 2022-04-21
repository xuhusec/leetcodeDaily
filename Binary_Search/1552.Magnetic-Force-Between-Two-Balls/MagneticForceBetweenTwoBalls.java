public class MagneticForceBetweenTwoBalls {
    public int maxDistance(int[] position, int m) {
        Arrays.sort(position);
        int lo = 1;
        int hi = position[position.length - 1] - position[0];

        while (lo < hi) {
            int mid = hi - (hi - lo)/2;
            if (isPossible(position, mid, m)) {
                lo = mid;
            } else {
                hi = mid - 1;
            }
        }

        return lo;
    }

    private boolean isPossible(int[] position, int minLen, final int total) {
        int cnt = 1;
        int prev = position[0];
        for (int i = 1; i < position.length; ++i) {
            if (position[i] - prev >= minLen) {
                if (++cnt >= total) {
                    return true;
                } 
                prev = position[i];
            }
        }
        return false;
    }
}