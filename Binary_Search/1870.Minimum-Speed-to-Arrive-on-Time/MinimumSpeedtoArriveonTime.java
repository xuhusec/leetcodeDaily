public class MinimumSpeedtoArriveonTime {
    public int minSpeedOnTime(int[] dist, double hour) {
        int lo = 1;
        int hi = (int)1e7 + 1;

        while (lo < hi) {
            int mid = lo + (hi - lo)/2;
            if (travelTime(dist, mid) <= hour) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }

        return lo > (int) 1e7 ? -1 : lo;
    }

    private double travelTime(int[] dist, int speed) {
        int time = 0;
        for (int i = 0; i < dist.length - 1; ++i) {
            time += (dist[i] - 1) / speed + 1;
        }
        return time + ((double)dist[dist.length - 1] / speed);
    }
}
