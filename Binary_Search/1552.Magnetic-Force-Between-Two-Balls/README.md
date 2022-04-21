### [1552.Magnetic-Force-Between-Two-Balls](https://leetcode.com/problems/magnetic-force-between-two-balls/)

#### Solution 1: binary search

The question asks for the max of minimum distance between two balls so that all balls can be placed. So, no geedy or dp solution is found. And we know if maxD is the answer. we can also place all balls with (maxD - 1) as the minimum distance between two balls. For a given, it would be O(n), where n is length of position array (after sorting), to check if it is possible to place all balls. So we can try binary search. The range of possible answer should be from 1 to R = max(position) - min(position). So it would be O(Rlogn).

The question asks for the max from feasible solutions. we need to track lo = mid to keep push the answer to its upper limit. the lo = 0 hi = 1 test tells hi - (hi - lo)/2 works but lo + (hi - lo) /2 would cause infinity loop. Then we have.

```java
    while (lo < hi) {
        int mid = hi - (hi - lo)/2;
        if (isPossible(position, mid, m)) {
            lo = mid;
        } else {
            hi = mid - 1;
        }
    }
```

To check if a given minLen work. We can just iterate over the position array geedily place the ball. Please note the position is not sorted in this problem, you must sort it first before applything the greedy method to verify if mid works.
```java
    private boolean isPossible(int[] position, int minLen, final int total) {
        // use greedy to find how many balls can be placed with a given minLen
        int cnt = 1;
        int prev = position[0];
        for (int i = 1; i < position.length; ++i) {
            // update when we find a position
            if (position[i] - prev >= minLen) {
                if (++cnt >= total) {
                    return true;
                } 
                prev = position[i];
            }
        }
        return false;
    }
```

You can also try to use binary search instead of looping but athe looping method is faster.

```java
    private boolean isPossible(int[] position, int minLen, int total) {
        int cnt = 1;
        int prev = position[0];
        int i = 0;
        while (i < position.length) {
            // find the next by binary search
            int next = Arrays.binarySearch(position, i + 1, position.length, prev + minLen);
            i = next < 0 ? -next - 1 : next;
            if (i < position.length) {
                if (++cnt >= total) {
                    return true;
                }
                prev = position[i];
            }
        }
        return false;
    }
```