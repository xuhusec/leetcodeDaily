import java.util.PriorityQueue;

public class TheMazeII {
    private static final int[][] DIRS = {{0,1},{0,-1},{1,0},{-1,0}};
    public int shortestDistance(int[][] maze, int[] start, int[] destination) {
        final int M = maze.length;
        final int N = maze[0].length;
        boolean[][] visited = new boolean[M][N];
        PriorityQueue<Info> pq = new PriorityQueue<>((a,b) -> a.distance - b.distance);
        pq.add(new Info(start[0], start[1], 0, -1));
        Carry carry = new Carry(maze, visited, M, N, pq);
        while (!pq.isEmpty()) {
            Info cur = pq.poll();
            if (visited[cur.x][cur.y]) {
                continue;
            }
            visited[cur.x][cur.y] = true;
            if (cur.x == destination[0] && cur.y == destination[1]) {
                return cur.distance;
            }
            // the way DIR specifies, 0 and 1 are reverse directions to each other. 2 and 3 are reverse directions.
            int skipID = cur.dirID < 0 ? cur.dirID : (cur.dirID < 2 ? 1 - cur.dirID : 5 - cur.dirID);
            for (int i = 0; i < DIRS.length; ++i) {
                if (skipID == i) {
                    continue;
                }
                travelTillStop(cur.x, cur.y, DIRS, i, cur.distance, carry);
            }
        }
        return -1;
    }
    
    private void travelTillStop(int x, int y, int[][] DIRS, int i, int dist, Carry carry) {
        final int dx = DIRS[i][0];
        final int dy = DIRS[i][1];
        int nx = x + dx;
        int ny = y + dy;
        int step = 0;
        while (nx >= 0 && nx < carry.M && ny >= 0 && ny < carry.N && carry.maze[nx][ny] == 0) {
            step++;
            x = nx;
            y = ny;
            nx += dx;
            ny += dy;
        }
        if (!carry.visited[x][y]) {
            carry.pq.add(new Info(x, y, dist + step, i));
        }
    }
    private static class Carry {
        private final int[][] maze;
        private final boolean[][] visited;
        private final int M;
        private final int N;
        private final PriorityQueue<Info> pq;
        
        public Carry(int[][] maze, boolean[][] visited, int M, int N, PriorityQueue<Info> pq) {
            this.maze = maze;
            this.visited = visited;
            this.M = M;
            this.N = N;
            this.pq = pq;
        }
    }
    
    private static class Info {
        private final int x;
        private final int y;
        private final int distance;
        private final int dirID;
        
        public Info(int x, int y, int distance, int dirID) {
            this.x = x;
            this.y = y;
            this.distance = distance;
            this.dirID = dirID;
        }
    }
}