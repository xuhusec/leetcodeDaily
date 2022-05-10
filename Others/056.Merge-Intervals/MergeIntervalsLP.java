public class MergeIntervalsLP {
    public int[][] merge(int[][] intervals) {
        int[][] events = new int[2 * intervals.length][];
        for (int i = 0; i < intervals.length; ++i) {
            int[] interval = intervals[i];
            events[2 * i] = new int[]{interval[0], 1};
            events[2 * i + 1] = new int[]{interval[1], -1};
        }

        Arrays.sort(events, (a, b) -> a[0] == b[0] ? b[1] - a[1] : a[0] - b[0]);

        int cnt = 0;
        int[][] ans = new int[intervals.length][];
        int id = 0;
        int start = 0;
        for (int[] event : events) {
            if (event[1] == 1) {
                if (++cnt == 1) {
                    start = event[0];
                }
            } else if (--cnt == 0) {
                ans[id++] = new int[]{start, event[0]};
            }
        }
        return Arrays.copyOf(ans, id);
    }
}
