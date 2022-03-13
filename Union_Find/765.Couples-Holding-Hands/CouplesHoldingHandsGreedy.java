public class CouplesHoldingHandsGreedy {
    public int minSwapsCouples(int[] row) {
        final int N = row.length;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < N; ++i) {
            map.put(row[i], i);
        }

        int ans = 0;
        for (int i = 0; i < N/2; ++i) {
            int first = row[2 * i];
            int second = getSecond(first);
            int nextId = 2 * i + 1;
            if (second != row[nextId]) {
                ans++;
                int swapTo = map.get(second);
                // update map then swap. Otherwise do not refer to row[nextId] save it before swap
                map.put(row[nextId], swapTo);
                map.put(second, nextId);

                swap(row, nextId, swapTo);
            }
        }
        return ans;
    }

    private int getSecond(int first) {
        // if first is 2 * i then second is 2 * i + 1 (first + 1). Otherwise it is (2 * i + 1) - 1 , aka first - 1
        return first / 2 * 2 == first ? first + 1 : first - 1; 
    }

    private void swap(int[] arr, int i, int j) {
        var temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}