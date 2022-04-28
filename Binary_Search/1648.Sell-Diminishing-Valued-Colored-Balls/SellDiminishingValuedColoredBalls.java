public class SellDiminishingValuedColoredBalls {
    private static final int MOD = (int)1e9 + 7;
    public int maxProfit(int[] inventory, int orders) {
        Arrays.sort(inventory);
        reverse(inventory);
        int lo = 0;
        int hi = inventory[0];

        long maxLeft = 0;
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            long offer = getOrder(inventory, mid);
            if ( offer > orders) {
                lo = mid + 1;
            } else {
                hi = mid;
                maxLeft = offer;
            }
        }

        long ans = 0;
        for (int i : inventory) {
            if (i < lo) {
                break;
            }
            ans = (ans + getProfit(i, lo)) % MOD;
        }
        
        ans = (ans + (orders - maxLeft) * lo) % MOD;
        return (int) ans;
    }

    private void reverse(int[] arr) {
        int lo = 0;
        int hi = arr.length - 1;
        while (lo < hi) {
            var temp = arr[lo];
            arr[lo++] = arr[hi];
            arr[hi--] = temp;
        }
    }

    private long getOrder(int[] inventory, int left) {
        long ans = 0;
        for (int i : inventory) {
            if (i < left) {
                break;
            }
            ans += i - left;
        }
        return ans;
    }

    private long getProfit(int total, int left) {
        return ((long)total + left + 1) * (total - left) / 2;
    }
}
