public class CountNumberofRectanglesContainingEachPoint {
    public int[] countRectangles(int[][] rectangles, int[][] points) {
        // sort x to reduce the complexity
        Arrays.sort(rectangles, (a, b) -> Integer.compare(a[0], b[0]));
        // keep the index
        Info[] infos = new Info[points.length];
        for (int i = 0; i < points.length; ++i) {
            infos[i] = new Info(i, points[i]);
        }
        // sort the points
        Arrays.sort(infos, (a, b) -> Integer.compare(a.point[0], b.point[0]));
        // h <= 100, we have a bucket array
        int[] cntH = new int[101];
        // two pointers
        int rId = rectangles.length - 1;
        int pId = infos.length - 1;

        int[] ans = new int[points.length];
        while (pId >= 0) {
            if (rId >= 0 && rectangles[rId][0] >= infos[pId].point[0]) {
                // record count of height
                cntH[rectangles[rId][1]]++;
                rId--;
                continue;
            }
            // all the passed rectangle satisfied li >= xi
            // we only need to make sure hi >= yi
            int total = 0;
            for (int i = infos[pId].point[1]; i <= 100; ++i) {
                total += cntH[i];
            }
            ans[infos[pId].id] = total;
            // move to the next point
            pId--;
        }
        return ans;
    }

    private static final int maxH = 100;
    public int[] countRectanglesBIT(int[][] rectangles, int[][] points) {
        Arrays.sort(rectangles, (a, b) -> Integer.compare(a[0], b[0]));
        Info[] infos = new Info[points.length];
        for (int i = 0; i < points.length; ++i) {
            infos[i] = new Info(i, points[i]);
        }
        Arrays.sort(infos, (a, b) -> Integer.compare(a.point[0], b.point[0]));

        FenwickTree ft = new FenwickTree(maxH + 1);

        int rId = rectangles.length - 1;
        int pId = infos.length - 1;

        int[] ans = new int[points.length];
        while (pId >= 0) {
            if (rId >= 0 && rectangles[rId][0] >= infos[pId].point[0]) {
                ft.update(rectangles[rId][1], 1);
                rId--;
                continue;
            }
            ans[infos[pId].id] = ft.sum(maxH) - ft.sum(infos[pId].point[1] - 1);
            pId--;
        }
        return ans;       
    }

    private static class Info {
        private int id;
        private int[] point;

        public Info(int id, int[] point) {
            this.id = id;
            this.point = point;
        }
    }

    private static class FenwickTree {
        private final int[] nums;

        public FenwickTree(int n) {
            nums = new int[n + 1];
        }

        public int sum(int i) {
            int sum = 0;
            ++i;
            while (i > 0) {
                sum += nums[i];
                i = i - lowBit(i);
            }
            return sum;
        }

        public void update(int i, int v) {
            ++i;
            while (i < nums.length) {
                nums[i] += v;
                i = i + lowBit(i);
            }
        }

        private int lowBit(int i) {
            return i & (-i);
        }
    }
}