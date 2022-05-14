### [1094.Car-Pooling](https://leetcode.com/problems/car-pooling/)

#### Solution 1: Line Sweep

This problem can be viewed as a follow up of Meeting Room II (LC253).  In the meeting room problem, only one meeting room would be occupied for a range. But in this problem,  it is weighted range. And we want to check if it would be exceed the capacity at any given time. But the idea is the same. We can break up a trip into two events. One event is for those passenger get on borad and the other events is for those passenger get off (negative of number of passengers in the trip). For a given time, the number of passengers would be the presum of those weight (i.e. number of passengers) after the event sorted by time. However, the number of passenger would not change if it is not a abroad time or a loading off time. So we only need to check the when those events happens. Also, for a given stop, we need to consider the number of people getting off before those getting on. So when sort we can sort by time and if they are equal, we can sort by the weight so that the negative first always shown first.

It can also be done via tree map as the time would be sorted directly if we use it as the key.

```java
    public boolean carPooling(int[][] trips, int capacity) {
        Map<Integer,Integer> map = new HashMap<>();
        for (int[] trip : trips) {
            map.merge(trip[1], trip[0], (v1,v2) -> v1 + v2);
            map.merge(trip[2], -trip[0], (v1,v2) -> v1 + v2);
        }
        // O(1) for adding events. Sort after populate all the events so that we can sort the unqie time stamp only
        map = new TreeMap<>(map);
        int cur = 0;
        for (int pass : map.values()) {
            cur += pass;
            if (capacity < cur) {
                return false;
            }
        }
        return true;
    }
```