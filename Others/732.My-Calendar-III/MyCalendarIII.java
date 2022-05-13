public class MyCalendarIII {
    private final TreeMap<Integer, Integer> diffMap;
    public MyCalendarThree() {
        diffMap = new TreeMap<>();
    }
    
    public int book(int start, int end) {
        diffMap.merge(start, 1, (v1, v2) -> v1 + v2);
        diffMap.merge(end, -1, (v1, v2) -> v1 + v2);

        int ans = 0;
        int max = 0;
        for (Map.Entry<Integer, Integer> entry : diffMap.entrySet()) {
            int cnt = entry.getValue();
            max += cnt;
            ans = Math.max(ans, max);
        }
        return ans;
    }
}