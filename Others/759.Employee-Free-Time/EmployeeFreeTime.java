public class EmployeeFreeTime {
    public List<Interval> employeeFreeTime(List<List<Interval>> schedule) {
        List<Interval> ans = new LinkedList<>();
        if (schedule.size() < 2) {
            return ans;
        }

        List<int[]> events = new ArrayList<>();
        for (List<Interval> intervals : schedule) {
            for (Interval i : intervals) {
                events.add(new int[]{i.start, 1});
                events.add(new int[]{i.end, -1});
            }
        }

        Collections.sort(events, (a, b) -> a[0] == b[0] ? b[1] - a[1] : a[0] - b[0]);

        int start = -1;
        int count = 0;
        for (int[] event : events) {
            if (start > -1 && count == 0) {
                ans.add(new Interval(start, event[0]));
            }
            count += event[1];
            if (count == 0) {
                start = event[0];
            }
        }
        return ans;
    }
}

class Interval {
    public int start;
    public int end;

    public Interval() {}

    public Interval(int start, int end) {
        this.start = start;
        this.end = end;
    }
};

