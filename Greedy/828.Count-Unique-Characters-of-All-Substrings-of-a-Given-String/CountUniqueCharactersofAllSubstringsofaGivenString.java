import java.util.Iterator;

public class CountUniqueCharactersofAllSubstringsofaGivenString {
    public int uniqueLetterString(String s) {
        List<Integer>[] lists = new List[26];
        for (int i = 0; i < lists.length; ++i) {
            lists[i] = new ArrayList<>();
        }

        final int n = s.length();
        for (int i = 0; i < n; ++i) {
            int id = s.charAt(i) - 'A';
            lists[id].add(i);
        }

        int ans = 0;
        int prev = -1;
        int cur = -1;

        for (List<Integer> l : lists) {
            if (l.isEmpty()) {
                continue;
            }
            Iterator<Integer> it = l.iterator();
            // set the first appear of the current character as -1 to count all previous characters before the real first one
            prev = -1;
            cur = it.next();
            
            while (it.hasNext()) {
                int next = it.next();
                ans += (cur - prev) * (next - cur);
                prev = cur;
                cur = next;
            }
            // do not forget the last segment
            ans += (cur - prev) * (n - cur);
        }
        return ans;
    }
}
