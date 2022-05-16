import java.util.Iterator;
import java.util.List;
import java.util.RandomAccess;

public class EmployeeFreeTimeMergeSort {
    public List<Interval> employeeFreeTime(List<List<Interval>> schedule) {
        List<Interval> ans = new LinkedList<>();
        if (schedule.size() < 2) {
            return ans;
        }
        
        schedule = toRandomAccessList(schedule);

        int start = 0;
        int end = schedule.size() - 1;
        while (end > 0) {
            start = 0;
            while (start < end) {
                schedule.set(start, mergeInterval(schedule.get(start), schedule.get(end)));
                start++;
                end--;
            }
        }

        List<Interval> merged = schedule.get(0);
        for (int i = 0; i < merged.size() - 1; ++i) {
            ans.add(new Interval(merged.get(i).end, merged.get(i+1).start));
        }
        return ans;
    }

    private List<Interval> mergeInterval(List<Interval> l0, List<Interval> l1) {
        List<Interval> ans = new ArrayList<>();
        Iterator<Interval> it0 = l0.iterator();
        Iterator<Interval> it1 = l1.iterator();
        Interval i0 = null;
        Interval i1 = null;
        Interval cur = null;

        while ((i0 != null || it0.hasNext()) && (i1 != null || it1.hasNext())) {
            if (i0 == null) {
                i0 = it0.next();
            }
            if (i1 == null) {
                i1 = it1.next();
            }

            if (cur != null && cur.end < i0.start && cur.end < i1.start) {
                ans.add(cur);
                cur = null;
            } 
            
            if (cur == null) {
                if (i0.start <= i1.start) {
                    cur = i0;
                    i0 = null;
                } else {
                    cur = i1;
                    i1 = null;
                }
            } else  {
                if (cur.end >= i0.start){
                    cur.end = Math.max(cur.end, i0.end);
                    i0 = null;
                }
                if (cur.end >= i1.start){
                    cur.end = Math.max(cur.end, i1.end);
                    i1 = null;
                }
            } 
        }

        while (i0 != null || it0.hasNext()) {
            if (i0 == null) {
                i0 = it0.next();
            }
            if (cur == null) {
                ans.add(i0);
            } else if (cur.end < i0.start) {
                ans.add(cur);
                cur = i0;
            } else {
                cur.end = Math.max(cur.end, i0.end);
            }
            i0 = null;
        }

        while (i1 != null || it1.hasNext()) {
            if (i1 == null) {
                i1 = it1.next();
            }
            if (cur == null) {
                ans.add(i1);
            } else if (cur.end < i1.start) {
                ans.add(cur);
                cur = i1;
            } else {
                cur.end = Math.max(cur.end, i1.end);
            }
            i1 = null;
        }

        ans.add(cur);
        return ans;
    }

    private <T> List<T> toRandomAccessList(List<T> list) {
        if (! (list instanceof RandomAccess)) {
            list = new ArrayList<>(list);
        }
        return list;
    }

    private List<Interval> mergeIntervalIdx(List<Interval> l0, List<Interval> l1) {
        List<Interval> ans = new ArrayList<>();
        l0 = toRandomAccessList(l0);
        l1 = toRandomAccessList(l1);
        int idx0 = 0;
        int idx1 = 0;
        
        Interval cur = null;

        while (idx0 < l0.size() && idx1 < l1.size()) {
            Interval i0 = l0.get(idx0);
            Interval i1 = l1.get(idx1);

            if (cur != null && cur.end < i0.start && cur.end < i1.start) {
                ans.add(cur);
                cur = null;
            } 
            
            if (cur == null) {
                if (i0.start <= i1.start) {
                    cur = i0;
                    idx0++;
                } else {
                    cur = i1;
                    idx1++;
                }
            } else  {
                if (cur.end >= i0.start){
                    cur.end = Math.max(cur.end, i0.end);
                    idx0++;
                }
                if (cur.end >= i1.start){
                    cur.end = Math.max(cur.end, i1.end);
                    idx1++;
                }
            } 
        }

        while (idx0 < l0.size()) {
            Interval i0 = l0.get(idx0);
            if (cur == null) {
                ans.add(i0);
            } else if (cur.end < i0.start) {
                ans.add(cur);
                cur = i0;
            } else {
                cur.end = Math.max(cur.end, i0.end);
            }
            idx0++;
        }

        while (idx1 < l1.size()) {
            Interval i1 = l1.get(idx1);
            if (cur == null) {
                ans.add(i1);
            } else if (cur.end < i1.start) {
                ans.add(cur);
                cur = i1;
            } else {
                cur.end = Math.max(cur.end, i1.end);
            }
            idx1++;
        }

        ans.add(cur);
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

