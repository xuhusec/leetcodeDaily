### [218.The-Skyline-Problem](https://leetcode.com/problems/the-skyline-problem)

#### Solution 1: Line Sweep

The final answer would be a list of max heights at all the critical points and merge the points with the same height. So, if we can have a virtual line to move along the critical point and check the max height at that point and record if necessary. We would get the answer. The Brute Force solution would take every building and update corresponding range [left, right) with its height. Only the cell with height lower to its height would be update.  This does not work in this problem because it is time consuming and the left and right are from 1 to 10<sup>9</sup>. So the space complexity exceeds the limit. It is range. So we can check if line sweep works. 

This problem is different from other line sweep problem. For other line sweep solution, we are record the occurance and update accordingly. But for this problem, we need to record the heights and we need to figure out the largest height among all building in consideration. We cannot just have a number. We need a list to keep all the information.

Like the other line sweep problem, we can break a building into two events. Here we need to keep height. So we have have {left<sub>i</sub>: {{height<sub>i</sub>, isRaise}}, right<sub>i</sub>: {{height<sub>i</sub>, !isRaise}}} We break it into two events. The first is assoicated with they key left<sub>i</sub> of the building and an object with its height and indicator of it is start or raise. The second is with the key right<sub>i</sub> of the building and the height and an indicator tells it is the end. If there is another event use either key of the above. we can append that event into the value (a list); This can be done 

We can sort those events by the key. Now we can scan the events. We need a data structure for look up and and get the max key quickly for the heights. We can use TreeMap. It provide O(logn) for both operations. The treeMap, let's name it cntMap, records the heights and its count. When we met a start event, it is height is added into the map. Otherwise, the count of the height is reduced by 1. If it is zero after reducation, it should be removed from the map. After checking all events at a given point. We can know the max height at that point. Note, if the cntMap is empty, like at the end, we should set height to zero.

But not all the ponts should be added. If the current largest height is equal to previous in the result, we should skip it.

#### Solution 2: Divide and Conquer

The idea is like merge sort. It is difficult to merge all the buildings directly. But merge two skyline is not as hard as the original problem. So we can divide the buildings and merge the result. 

First, it is very easy to get the answer when there is only one building. We only have {left<sub>i</sub>, height} and {right<sub>i</sub>, 0}.

Second, once we have two skyline. we can merge them. The idea is to track the max height at all the critical point in both skyline. Only the max is added into the result. Because the two are both skylines, we can trust their height in the critical points within their own group. So we can keep track the heights of the two list separately. The max of them would be added to the answer. When we need to move to the next elment in the list. The corresponding height required to be updatd. If one of the current "head" element in the list shows early, we can update its corresponding height and move to the next. If they are equal, we need update both heights. After this process, we need to add the rest of left and right list into the answer. We still need to take care of the previous height added into the answer is the same of the current one. That should be skipped.

In the implementation, I used the ArrayList. The following is an implementation of LinkedList and iterator.

```java
private List<List<Integer>> merge(List<List<Integer>> left, List<List<Integer>> right) {
    List<Integer> l = null;
    List<Integer> r = null;
    Iterator<List<Integer>> lit = left.iterator();
    Iterator<List<Integer>> rit = right.iterator();
    
    int leftH = 0;
    int rightH = 0;
    LinkedList<List<Integer>> ans = new LinkedList<>();
    while ((l != null || lit.hasNext()) && (r != null || rit.hasNext())) {
        if (l == null) {
            l = lit.next();
        }
        if (r == null) {
            r = rit.next();
        }
        
        Integer pos = null;
        int cmp = l.get(0).compareTo(r.get(0));
        if (cmp < 0) {
            leftH = l.get(1);
            pos = l.get(0);
            l = null;
        } else if (cmp > 0) {
            rightH = r.get(1);
            pos = r.get(0);
            r = null;
        } else {
            pos = l.get(0);
            leftH = l.get(1);
            rightH = r.get(1);
            l = null;
            r = null;
        }
        
        Integer h = Math.max(leftH, rightH);
        if (ans.isEmpty() || !ans.getLast().get(1).equals(h)) {
            ans.add(List.of(pos, h));
        }
    }

    while ((l != null || lit.hasNext())) {
        if (l == null) {
            l = lit.next();
        }
        addIfNotEqual(ans, l);
        l = null;
    }
    
    while ((r != null || rit.hasNext())) {
        if (r == null) {
            r = rit.next();
        }
        addIfNotEqual(ans, r);
        r = null;
    }
    return ans;
}
 ```   