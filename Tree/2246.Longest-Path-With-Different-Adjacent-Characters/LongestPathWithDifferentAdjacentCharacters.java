public class LongestPathWithDifferentAdjacentCharacters {
    public int longestPath(int[] parent, String s) {
        if (s.length() < 2) {
            return s.length();
        }
        List<Integer>[] children = new List[parent.length];
        for (int i = 0; i < children.length; ++i) {
            children[i] = new ArrayList<>();
        }
        // skip the root node which does not have a parent node
        for (int i = 1; i < parent.length; ++i) {
            children[parent[i]].add(i);
        }
        Info info = new Info();
        dfs(0, children, s.toCharArray(), info);
        return info.ans;
    }

    private int dfs(int cur, List<Integer>[] children, char[] chars, Info info) {
        int max = 0;
        int secondMax = 0;

        char ch = chars[cur];
        for (int child : children[cur]) {
            // the max might under any node so we need visited regardless of its character
            int cnt = dfs(child, children, chars, info);
            if (chars[child] == ch) {
                continue;
            }
            if (cnt > max) {
                secondMax = max;
                max = cnt;
            } else if (cnt > secondMax) {
                secondMax = cnt;
            }
        }
        info.ans = Math.max(info.ans, 1 + max + secondMax);
        return 1 + max;
    }

    private static class Info {
        private int ans;
    }
}