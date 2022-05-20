public class DescribethePainting {
    public List<List<Long>> splitPainting(int[][] segments) {
        Map<Integer, Long> diffMap = new HashMap<>();
        for (int[] seg : segments) {
            long color = seg[2];
            diffMap.merge(seg[0], color, (v1, v2) -> v1 + v2);
            diffMap.merge(seg[1], -color, (v1, v2) -> v1 + v2);
        }

        diffMap = new TreeMap<>(diffMap);

        List<List<Long>> ans = new LinkedList<>();
        long sum = 0;
        int start = -1;
        for (Map.Entry<Integer, Long> entry : diffMap.entrySet()) {
            int pos = entry.getKey();
            if (sum > 0) {
                ans.add(List.of(Long.valueOf(start), Long.valueOf(pos), Long.valueOf(sum)));
            }
            start = pos;
            sum += entry.getValue();
        } 
        return ans;
    }
}