public class DivideChocolate {
    public int maximizeSweetness(int[] sweetness, int k) {
        int lo = Integer.MAX_VALUE;
        int hi = 0;
        for (int sweet : sweetness) {
            lo = Math.min(lo, sweet);
            hi += sweet;
        }

        while (lo < hi) {
            int mid = hi - (hi - lo)/2;
            if (isEnoughPieces(sweetness, mid) >= k + 1) {
                lo = mid;
            } else {
                hi = mid - 1;
            }
        }
        return lo;
    }

    private int isEnoughPieces(int[] sweetness, final int min) {
        int cnt = 0;
        int cur = 0;
        for (int sweet : sweetness) {
            cur += sweet;
            if (cur >= min) {
                ++cnt;
                cur = 0;
            }
        }
        return cnt;
    }
}