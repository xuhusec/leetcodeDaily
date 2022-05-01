public class TheNumberofWeakCharactersintheGame {
    public int numberOfWeakCharacters(int[][] properties) {
        Arrays.sort(properties, (a, b) -> a[0] == b[0] ? Integer.compare(b[1], a[1]) : Integer.compare(a[0], b[0]));
        Deque<Integer> stack = new ArrayDeque<>();
        for (int[] prop : properties) {
            while (!stack.isEmpty() && stack.peek() < prop[1]) {
                stack.pop();
            }
            stack.push(prop[1]);
        }
        return properties.length - stack.size();
    }
}
