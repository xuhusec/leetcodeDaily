public class NumberofFlowersinFullBloom {
    public int[] fullBloomFlowers(int[][] flowers, int[] persons) {
        Map<Integer, Integer> diffMap = new HashMap<>();

        for (int[] flower : flowers) {
            diffMap.merge(flower[0], 1, (v1, v2) -> v1 + v2);
            diffMap.merge(flower[1] + 1, -1, (v1, v2) -> v1 + v2);
        }

        diffMap = new TreeMap<>(diffMap);

        
        final int n = persons.length;
        Info[] infos = new Info[n];
        for (int i = 0; i < n; ++i) {
            infos[i] = new Info(i);
        }
        Arrays.sort(infos, (a, b) -> persons[a.id] - persons[b.id]);

        int[] ans = new int[n];
        Iterator<Map.Entry<Integer, Integer>> it = diffMap.entrySet().iterator();
        if (!it.hasNext()) {
            return ans;
        }
        Map.Entry<Integer, Integer> entry = it.next();
        int sum = 0;
        for (int i = 0; i < n; ++i) {
            int cur = persons[infos[i].id];
            while (entry != null && entry.getKey() <= cur) {
                sum += entry.getValue();
                entry = it.hasNext() ? it.next() : null;
            }
            ans[infos[i].id] = sum;
        }
        return ans;
    }

    private static class Info {
        private final int id;
        public Info(int id) {
            this.id = id;
        }
    }
}
