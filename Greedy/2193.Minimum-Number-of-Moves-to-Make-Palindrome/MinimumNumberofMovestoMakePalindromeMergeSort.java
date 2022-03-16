import java.util.Deque;
import java.util.Map;
import java.util.HashMap;
import java.util.LinkedList;

class MinimumNumberofMovestoMakePalindromeMergeSort {
    public int minMovesToMakePalindrome(String s) {
        final int N = s.length();
        int cnt = 0;
        Map<Character, Deque<Integer>> map = new HashMap<>();
        for (int i = 0; i < N; ++i) {
            map.computeIfAbsent(s.charAt(i), k -> new LinkedList<>()).addLast(i);
        }
        int[] secondHalf = new int[N/2];
        int id = N/2 - 1;
        int res = 0;
        for (int i = 0; i < N; ++i) {
            Deque<Integer> cur = map.get(s.charAt(i));
            if (cur.isEmpty()) {
                continue;
            }
            if (cur.size() == 1) {
                cur.removeFirst();
                res += i - cnt;
            } else {
                cur.removeFirst();
                int back = cur.removeLast();
                res += i - cnt;
                secondHalf[id--] = back;
                cnt++;
            }
        }

        return res + reversePair(secondHalf, 0 , N/2 - 1, new int[N/2]);
    }

    private int reversePair(int[] arr, int start, int end, int[] temp) {
        if (start >= end) {
            return 0;
        }
        int mid = start + (end - start)/2;
        int left = reversePair(arr, start, mid, temp);
        int right = reversePair(arr, mid + 1, end, temp);
        return left + right + merge(arr, start, mid, end, temp);
    }

    private int merge (int[] arr, int start, int mid, int end, int[] temp) {
        int i = start;
        int j = mid + 1;
        int k = start;
        int res = 0;
        while (i <= mid && j <= end) {
            if (arr[i] <= arr[j]) {
                temp[k++] = arr[i++];
            } else {
                temp[k++] = arr[j++];
                res += mid + 1 - i;
                
            }
        }

        while (i <= mid) {
            temp[k++] = arr[i++];
        }
        while (j <= end) {
            temp[k++] = arr[j++];
        }
        k = start;
        while (k <= end) {
            arr[k] = temp[k];
            k++;
        }
        return res;
    }
}