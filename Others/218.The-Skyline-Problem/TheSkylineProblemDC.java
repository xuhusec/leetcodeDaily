public class TheSkylineProblemDC {
    public List<List<Integer>> getSkyline(int[][] buildings) {
        return mergeSkyLine(buildings, 0, buildings.length - 1);
    }

    private List<List<Integer>> mergeSkyLine(int[][] buildings, int start, int end) {
        // if there is only one building, we break it into two events {start, height}, {end, 0}
        if (start == end) {
            int[] building = buildings[start];
            return List.of(List.of(building[0], building[2]), List.of(building[1], 0));
        }

        // divide evenly into two parts
        int mid = start + (end - start)/2;
        List<List<Integer>> left = mergeSkyLine(buildings, start, mid);
        List<List<Integer>> right = mergeSkyLine(buildings, mid + 1, end);
        // merge the result;
        return merge(left, right);
    }

    private List<List<Integer>> merge(List<List<Integer>> left, List<List<Integer>> right) {
        // left id and right id to track which  element we are investigate in left list and right list
        int lId = 0;
        int rId = 0;
        // record the height of left and that of the right. Please note for left and right because they are already merged from other list
        // there is only one height at each point.
        int leftH = 0;
        int rightH = 0;
        // as we are using id, so we need randomAccess list. Here we use ArrayList
        List<List<Integer>> ans = new ArrayList<>();
        while (lId < left.size() && rId < right.size()) {
            // holder for position or the first element int the array
            Integer pos = null;
            
            // compare left element and right element to find out which shows earlier
            int cmp = left.get(lId).get(0).compareTo(right.get(rId).get(0));

            if (cmp < 0) {
                // if left shows early, we can update the leftH and position
                var l = left.get(lId++);
                leftH = l.get(1);
                pos = l.get(0);
            } else if (cmp > 0) {
                // if right shows early, we can update the rightH and position
                var r = right.get(rId++);
                rightH = r.get(1);
                pos = r.get(0);
            } else {
                // they both show up at next point, update the both height and position
                var l = left.get(lId++);
                var r = right.get(rId++);
                pos = l.get(0);
                leftH = l.get(1);
                rightH = r.get(1);
            }
            
            // only the max of leftH and rightH should be recorded.
            Integer h = Math.max(leftH, rightH);
            // if the last height in the answer is not equal to cur max we can add it to the result.
            // for example {2, 5, 7}, {3, 6, 7}, we should only have {2, 7}, {6, 0}. We cannot have {2, 7}, {3, 7}, {5, 7}, {6, 0}
            if (ans.isEmpty() || !ans.get(ans.size() - 1).get(1).equals(h)) {
               ans.add(List.of(pos, h));
            }
        }

        //add the rest in the left array
        while (lId < left.size()) {
            addIfNotEqual(ans, left.get(lId++));
        }
        //add the rest in the right array
        while (rId < right.size()) {
            addIfNotEqual(ans, right.get(rId++));
        }
        return ans;
    }


    private void addIfNotEqual(List<List<Integer>> ans, List<Integer> cand) {
        if (ans.isEmpty() || !ans.getLast().get(1).equals(cand.get(1))) {
            ans.add(cand);
        }
    }
}