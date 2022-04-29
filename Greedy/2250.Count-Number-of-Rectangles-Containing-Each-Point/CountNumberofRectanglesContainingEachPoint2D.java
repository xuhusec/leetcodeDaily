public class CountNumberofRectanglesContainingEachPoint2D {
    public int[] countRectangles(int[][] rectangles, int[][] points) {
        Info info = collectAndSortUniqueCoordinates(rectangles);

        Diff2D df = new Diff2D(info.xs.length, info.ys.length);

        for (int[] rectangle : rectangles) {
            // map rectangle to the index in the sorted array 
            df.add(0 , 0, info.xIdMap.get(rectangle[0]), info.yIdMap.get(rectangle[1]), 1);
        }

        int[][] sum = df.sum();

        int[] ans = new int[points.length];
        
        for (int i = 0; i < points.length; ++i) {
            int[] point = points[i];
            // info.xy and info.ys are sorted so we can perform binary search
            int x = Arrays.binarySearch(info.xs, point[0]);
            int y = Arrays.binarySearch(info.ys, point[1]);
            if (x < 0) {
                x = -x - 1;
            }
            if (y < 0) {
                y = -y - 1;
            }
            
            ans[i] = x >= info.xs.length || y >= info.ys.length ? 0 : sum[x][y];
        }
        return ans;

    }

    private Info collectAndSortUniqueCoordinates(int[][] rectangles) {
        Set<Integer> xs = new HashSet<>();
        Set<Integer> ys = new HashSet<>();

        for (int[] rectangle : rectangles) {
            xs.add(rectangle[0]);
            ys.add(rectangle[1]);
        }

        int[] x = new int[xs.size()];
        int[] y = new int[ys.size()];
        int id = 0;
        for (int i : xs) {
            x[id++] = i;
        }

        id = 0;
        for (int i : ys) {
            y[id++] = i;
        }
        // for binary search and id Mapping we need sort
        Arrays.sort(x);
        Arrays.sort(y);
        
        Map<Integer, Integer> xIdMap = new HashMap<>();
        Map<Integer, Integer> yIdMap = new HashMap<>();
        for (int i = 0; i < x.length; ++i) {
            xIdMap.put(x[i], i);
        }
        
        for (int i = 0; i < y.length; ++i) {
            yIdMap.put(y[i], i);
        }
        

        return new Info(x, y, xIdMap, yIdMap);
    }

    private static class Diff2D  {
        private int[][] diff;
        private final int m;
        private final int n;

        public Diff2D(int m, int n) {
            this.m = m;
            this.n = n;

            diff = new int[m + 1][n + 1];
        }

        public void add(int x0, int y0, int x1, int y1, int val) {
            diff[x0][y0] += val;
            diff[x0][y1 + 1] -= val;
            diff[x1 + 1][y0] -= val;
            diff[x1 + 1][y1 + 1] += val;
        }

        public int[][] sum() {
           int[][] ans = new int[m + 1][n + 1];
           ans[0][0] = diff[0][0];
           for (int i = 0; i < m; ++i) {
               for (int j = 0; j < n; ++j) {
                   int a = i == 0 ? 0 : ans[i - 1][j];
                   int b = j == 0 ? 0 : ans[i][j - 1];
                   int c = (i == 0 || j == 0) ? 0 : ans[i - 1][j - 1];
                   ans[i][j] = a + b - c + diff[i][j];
               }
           }
           return ans;
        }
        
    }

    private static class Info {
        private final int[] xs;
        private final int[] ys;
        private final Map<Integer, Integer> xIdMap;
        private final Map<Integer, Integer> yIdMap; 

        public Info(int[] xs, int[] ys, Map<Integer, Integer> xIdMap, Map<Integer, Integer> yIdMap) {
            this.xs = xs;
            this.ys = ys;
            this.xIdMap = xIdMap;
            this.yIdMap = yIdMap;
        }
    }
}