### [1943.Describe-the-Painting](https://leetcode.com/problems/describe-the-painting/)

#### Solution 1: Diff Array

Please note the problem said the each segement was painted with a unique color. So there is no two segements with the same color. We do not need to think about merge two adjacent segments with the same color. We only need to check how the kinds and counts of paints changed and merge the range. Although the final answer asks for the sum but we cannot merge two same with the same value. Because no two segemtns with the same color. Even though the sum is the same it represents different combination of colors. So we only need to ingore those unpainted and keep the segements even though they have the same color. 

Even painting segment is a range. we need to check when the combination changes. Then change only happens in the start of a range and the end of the range. So we can use diff array to manage the change points instead of marking colors directly to reduce the time and space complexity.

We can maintain a map to record the change points they are at least one of start's and/or end's. Aggregate the changes points because two or more change events might happen at a change point. Sort the map via change points order. And go over the empty. The prefix sum of the diff is current sum of color. We should skip zero because there is no paint. And we do not merge the same sum as we discussed previously.

The following is a longer implementation of tracking new ranges than the recorded but some people might find it a little easy to understand.


```java
long sum = -1;
int start = -1;
for (Map.Entry<Integer, Long> entry : diffMap.entrySet()) {
    int key = entry.getKey();
    long diff = entry.getValue();
    if (start < 0) {
        start = key;
        sum = diff;
    } else {
        if (sum != 0) {
            ans.add(List.of(Long.valueOf(start), Long.valueOf(key), Long.valueOf(sum)));
        }
        start = key;
        sum += diff;
    }
}
```

* the sort happens when conver hashMap to treeMap.