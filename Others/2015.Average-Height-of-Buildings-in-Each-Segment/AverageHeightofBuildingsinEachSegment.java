import java.util.Map;

public class AverageHeightofBuildingsinEachSegment {
    public int[][] averageHeightOfBuildings(int[][] buildings) {
        final int n = buildings.length;
        if (n < 2) {
            return buildings; 
        }
        Map<Integer, Info> diffMap = new HashMap<>();
        for (int[] building : buildings) {
            Info start = diffMap.computeIfAbsent(building[0], k -> new Info());
            start.cnt++;
            start.sum += building[2];

            Info end = diffMap.computeIfAbsent(building[1], k -> new Info());
            end.cnt--;
            end.sum -= building[2];
        }

        // sort map
        diffMap = new TreeMap<>(diffMap);

        int[][] ans = new int[diffMap.size()][];
        int id = 0;

        int start = 0;
        int avg = 0;
        int cnt = 0;
        int sum = 0;
        for (Map.Entry<Integer, Info> entry : diffMap.entrySet()) {
            Info cur = entry.getValue();
            if (cur.sum == 0 && cur.cnt == 0) {
                continue;
            }
            int pos = entry.getKey();
            sum += cur.sum;
            if (cnt == 0) {
                start = pos;
                cnt = cur.cnt;
                avg = sum / cnt;
            } else {
                cnt += cur.cnt;
                int nAvg = cnt > 0 ? sum / cnt : 0; 
                if (nAvg != avg) {
                    ans[id++] = new int[]{start, pos, avg};
                    avg = nAvg;
                    start = pos;
                } 
                
            }
        }

        return id < ans.length ? Arrays.copyOf(ans, id) : ans;
    }

    private static class Info {
        private int sum;
        private int cnt;
    }
}