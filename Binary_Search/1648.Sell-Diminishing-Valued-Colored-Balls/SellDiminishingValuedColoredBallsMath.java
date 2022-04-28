public class SellDiminishingValuedColoredBallsMath {
    private static final int MOD = (int)1e9 + 7;
    public int maxProfit(int[] inventory, int orders) {
        Arrays.sort(inventory);
        final int n = inventory.length;
        int i = n - 1;
        long ans = 0;
        while (orders > 0) {
            if (i > 0 && inventory[i - 1] == inventory[i]) {
                i--;
                continue;
            }
            // keep long because diff * cnt might be overflow
            // the number of balls should be taken in this round such that they ball sold are still most valued.
            long diff = inventory[i] - (i > 0 ?  inventory[i - 1] : 0);
            int cnt = n - i;
            if (orders >= diff * cnt) {
                ans = (ans + getProfit(inventory[i], diff, cnt)) % MOD;
                orders -= diff * cnt;
                // move to the next
                i--;
            } else {
                // we cannot sell all the balls
                // number that all max valued balls can be sold
                int take = orders / cnt;
                // the rest balls after taken from all the piles
                int left = orders % cnt;
                // (inventory[i] - take) * left. we do not need to pick cnt balls 
                // we only need left balls and the price for that is (inventory[i] - take)
                ans = (ans + getProfit(inventory[i], take, cnt) + (inventory[i] - take) * (long)left) % MOD;
                break;
            }
        }

        return (int)ans;
    }

    private long getProfit(int start, long diff, int cnt) {
        return ((start + start - diff + 1) * diff/2) % MOD * cnt; 
    }
}
