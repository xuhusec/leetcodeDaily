public class CountNodesWiththeHighestScore {
    public int countHighestScoreNodes(int[] parents) {
        final int n = parents.length;
        List<Integer>[] children = new List[n];
        for (int i = 0; i < n; ++i) {
            children[i] = new ArrayList<>(2);
        }
        // skip the root which has no parent
        for (int i = 1; i < n; ++i) {
            children[parents[i]].add(i);
        }

        Info info = new Info(n);

        getNodeCnt(0, children, info);

        return info.cnt;
    }

    private long getNodeCnt(int cur, List<Integer>[] children, Info info) {
        List<Integer> childArr = children[cur];
        long totalCnt = 1; // 1 for itself
        long score = 1;
        for (int child : childArr) {
            long cnt = getNodeCnt(child, children, info);
            score *= cnt;
            totalCnt += cnt;
        }

        score *= Math.max(1, info.total - totalCnt);
        if (score > info.max) {
            info.max = score;
            info.cnt = 1;
        } else if (score == info.max) {
            info.cnt++;
        }

        return totalCnt;
    }

    private static class Info {
        private final int total;
        private int cnt;
        private long max;

        public Info (int total) {
            this.total = total;
            this.cnt = 0;
            this.max = 0;
        } 
    }
}