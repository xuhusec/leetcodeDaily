public class MeetingRoomsII {
    public int minMeetingRooms(int[][] intervals) {
        final int n = intervals.length;
        int[][] events = new int[2*n][];
        for (int i = 0; i < n; ++i) {
            int[] interval = intervals[i];
            events[2*i] = new int[]{interval[0], 1};
            events[2*i + 1] = new int[]{interval[1], -1};
        }

        Arrays.sort(events, (a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
        int ans = 0;
        int cur = 0;
        for (int[] event : events) {
            cur += event[1];
            if (cur > ans) {
                ans = cur;
            }
        }
        return ans;
    }
}
