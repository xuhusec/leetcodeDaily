import java.util.PriorityQueue;

public class TheMazeIII {
    private static final int[][] DIRS = {{-1,0}, {1,0}, {0, -1}, {0, 1}};
    private static final char[] INS = {'u', 'd', 'l', 'r'};
    
    public String findShortestWay(int[][] maze, int[] ball, int[] hole) {
        final int M = maze.length;
        final int N = maze[0].length;
        ResInfo resInfo = new ResInfo();
        PriorityQueue<Info> pq = new PriorityQueue<>(
            (a, b) -> a.dist == b.dist ? CharSequence.compare(a.pathBuilder, b.pathBuilder) 
            : Integer.compare(a.dist, b.dist));
        boolean[][] visited = new boolean[M][N];
        pq.add(new Info(ball[0], ball[1], 0, new StringBuilder()));
        while (!pq.isEmpty()) {
            Info cur = pq.poll();
            if (visited[cur.x][cur.y]) {
                continue;
            }
            visited[cur.x][cur.y] = true;
            //cur.dist would be the starting point
            // if it is greater than or equal to the current minimum dist, the path the hole from it would be larger than the known minimum
            if (cur.dist >= resInfo.dist) {
                break;
            }
            // skip where the node come frome
            char backward = cur.pathBuilder.isEmpty() ? '\0' : getReverseDirection(cur.pathBuilder.charAt(cur.pathBuilder.length()-1));
            for (int i = 0; i < DIRS.length; ++i) {
                if (backward == INS[i]) {
                    continue;
                }
                explore(cur.pathBuilder.append(INS[i]), cur, maze, DIRS[i], visited, hole, pq, resInfo);
                cur.pathBuilder.setLength(cur.pathBuilder.length() - 1);
            }
        }
        return resInfo.instructions;
    }

    private void explore(StringBuilder instructions, Info cur, int[][] maze, int[] dir, boolean[][] visited, int[] hole, PriorityQueue<Info> pq, ResInfo resInfo) {
        final int M = maze.length;
        final int N = maze[0].length;
        int x = cur.x;
        int y = cur.y;
        int nx = x + dir[0];
        int ny = y + dir[1];
        int dist = cur.dist;
        while (nx >= 0 && nx < M && ny >= 0 && ny < N && maze[nx][ny] != 1) {
            dist++;
            x = nx;
            y = ny;
            if (x == hole[0] && y == hole[1]) {
                if (dist < resInfo.dist || (resInfo.dist == dist && CharSequence.compare(resInfo.instructions, instructions) > 0)) {
                    resInfo.instructions = instructions.toString();
                    resInfo.dist = dist;
                }
            }
            nx += dir[0];
            ny += dir[1];
        }
        if (!visited[x][y]) {
            // need to save a copy of StringBuilder to not affect other candidates
            pq.add(new Info(x, y, dist, new StringBuilder(instructions)));
        }


    }

    private char getReverseDirection(char ch) {
        if (ch == 'u') {
            return 'd';
        } else if (ch == 'd') {
            return 'u';
        } else if (ch == 'l') {
            return 'r';
        } else if (ch == 'r') {
            return 'l';
        }
        return '\0';
    }

    private static class Info {
        private final StringBuilder pathBuilder;
        private final int x;
        private final int y;
        private final int dist;

        public Info(int x, int y, int dist, StringBuilder sb) {
            this.x = x;
            this.y = y;
            this.dist = dist;
            this.pathBuilder = sb;
        }
    }
    
    private static class ResInfo {
        private int dist = Integer.MAX_VALUE;
        private String instructions = "impossible";
    }
}
