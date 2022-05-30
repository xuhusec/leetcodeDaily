import java.util.TreeMap;

public class AmountofNewAreaPaintedEachDay {
    public int[] amountPainted(int[][] paint) {
        Map<Integer, List<Info>> diffMap = new HashMap<>();
        for (int i = 0; i < paint.length; ++i) {
            int[] p = paint[i];
            diffMap.computeIfAbsent(p[0], k -> new LinkedList<>()).add(new Info(i, true));
            diffMap.computeIfAbsent(p[1], k -> new LinkedList<>()).add(new Info(i, false));
        }
        
        diffMap = new TreeMap<>(diffMap);
        
        TreeSet<Integer> cntSet = new TreeSet<>();
        int[] ans = new int[paint.length];
        int preId = -1;
        int paintId = -1;
        
        for (Map.Entry<Integer, List<Info>> entry : diffMap.entrySet()) {
            int pos = entry.getKey();
            for (Info info : entry.getValue()) {
                if (info.isStart) {
                    cntSet.add(info.id);
                } else {
                    cntSet.remove(info.id);
                }
            }

            int id = cntSet.isEmpty() ? -1 : cntSet.first();
            if (preId == -1) {
                prev = pos;
                preId = id;
            } else if (preId != id) {
                ans[preId] += pos - prev;
                preId = id;
                prev = pos; 
            }
        }
        return ans;
    }
    
    private static class Info {
        private final int id;
        private final boolean isStart;
        public Info(int id, boolean isStart) {
            this.id = id;
            this.isStart = isStart;
        }
    }
}
