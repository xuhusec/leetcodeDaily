public class CarPooling {
    public boolean carPooling(int[][] trips, int capacity) {
        final int n = trips.length;
        int[][] events = new int[2 * n][];
        for (int i = 0; i < n; ++i) {
            int[] trip = trips[i];
            events[2 * i] = new int[]{trip[1], trip[0]};
            events[2 * i + 1] = new int[]{trip[2], -trip[0]};
        }

        Arrays.sort(events, (a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);

        int cur = 0;

        for (int[] event : events) {
            cur += event[1];
            if (cur > capacity) {
                return false;
            }
        }
        return true;
    }
}