import java.util.TreeMap;

public class TheSkylineProblem {
    public List<List<Integer>> getSkyline(int[][] buildings) {
        // record the height change
        // Info class provide the height and if it is raise (start of the building) and not raise (the end of the building)
        Map<Integer, List<Info>> eventMap = new HashMap<>();

        for (int[] building: buildings) {
            Integer h = building[2];
            eventMap.computeIfAbsent(building[0], k -> new LinkedList<>()).add(new Info(h, true));
            eventMap.computeIfAbsent(building[1], k -> new LinkedList<>()).add(new Info(h, false));
        }
        //sort events;
        eventMap = new TreeMap<>(eventMap);
        // we need treeMap because we want to get the largest height at the end of each round
        TreeMap<Integer, Integer> cntMap = new TreeMap<>();
        LinkedList<List<Integer>> ans = new LinkedList<>();
        for (Map.Entry<Integer, List<Info>> entry : eventMap.entrySet()) {
            Integer pos = entry.getKey();

            for (Info info : entry.getValue()) {
                if (info.isRaise) {
                    //increase cnt of info.height
                    cntMap.merge(info.height, 1 , (v1, v2) -> v1 + v2);
                } else {
                    int left = cntMap.merge(info.height, -1, (v1, v2) -> v1 + v2);
                    // remove if no cnt left
                    if (left == 0) {
                        cntMap.remove(info.height);
                    }
                }
            }
            // if no entry left in cntMap  we get zero (like the last change of height). Otherwise we get the tallest. 
            Integer h = cntMap.isEmpty() ? 0 : cntMap.lastKey();
            // if the height does not change we should skip. For example we have {2, 5, 3} {2, 7, 3}. There is no change from 5 to 7 so we should skip
            if (ans.isEmpty() || !h.equals(ans.getLast().get(1))) {
                ans.add(List.of(pos, h));
            }
            
        }
        return ans;
    }

    private static class Info {
        private final Integer height;
        private final boolean isRaise;

        public Info(Integer height, boolean isRaise) {
            this.height = height;
            this.isRaise = isRaise;
        }
        
    }
}