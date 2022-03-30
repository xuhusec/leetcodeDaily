import java.util.Iterator;

public class MaximumValueofKCoinsFromPilesDFS {
    public int maxValueOfCoins(List<List<Integer>> piles, int k) {
        int[][] cache = new int[piles.size()][k+1];
        return dfs(piles, 0, k, cache);
    }

    private int dfs(List<List<Integer>> piles, int id, int left, int[][] cache) {
        if (left == 0) {
            return 0;
        }

        if (id == piles.size()) {
            // left > 0 but no more to take
            return Integer.MIN_VALUE;
        }

        if (cache[id][left] != 0) {
            return cache[id][left];
        }

        int ans = dfs(piles, id + 1, left, cache);
        int sum = 0;
        Iterator<Integer> it = piles.get(id).iterator();
        for (int i = 1; i <= left && it.hasNext(); ++i) {
            sum += it.next();
            ans = Math.max(ans, sum + dfs(piles, id + 1, left - i, cache));
        }
        cache[id][left] = ans;
        return ans; 
    }
}
