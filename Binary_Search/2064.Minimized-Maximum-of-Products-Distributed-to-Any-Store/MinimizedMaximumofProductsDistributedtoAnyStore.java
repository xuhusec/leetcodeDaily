public class MinimizedMaximumofProductsDistributedtoAnyStore {
    public int minimizedMaximum(int n, int[] quantities) {
        int lo = 1;
        int hi = 0;
        for (int q : quantities) {
            hi = Math.max(hi, q);
        }

        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (isPossible(quantities, n, mid)) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }
        return lo;
    }

    private boolean isPossible (int[] quantities, int total, int max) {
        int cnt = 0;
        for (int q : quantities) {
            cnt += q / max + (q % max == 0 ? 0 : 1);
            if (cnt > total) {
                return false;
            }
        }
        return true;
    }
}
