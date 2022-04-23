### [1482.Minimum-Number-of-Days-to-Make-m-Bouquets](https://leetcode.com/problems/minimum-number-of-days-to-make-m-bouquets/)

#### Solution 1: binary search

As the days increase, more flowers would be ready for being picked. So, as days increased, the number of bouquets can be served is a non-decreasing sequence. That means we can perform bindary search. The greedy or dp seems not suitable for this question.

The question asks for the minimum number of days. We need to track the upper limit so that we can push to lower values. So hi = mid when a valid solution is found and lo = mid + 1 when an invalid solution is found. For the boundary, it would be at min and max in the array. If all flowers bloomed and it was still short for the bouquets, number of flowers / k > m, it would return -1 because it is impossible.

For a given mid, to check if we can get enough flowers can be resolved by greedy. We need max the consectutive flowers and if the cnt == k, we can get them  for a bouqets.

Note:
The above make a short cut for impossible case.
```java
    if (bloomDay.length/k < m) {
        return -1;
    }
    int lo = Integer.MAX_VALUE;
    int hi = 0;
    for (int bd : bloomDay) {
        hi = Math.max(hi, bd);
        lo = Math.min(lo, bd);
    }
```

We can also record the max day and make the hi = max + 1. and return -1 if lo > max.
```java
    if (bloomDay.length/k < m) {
        return -1;
    }
    int lo = Integer.MAX_VALUE;
    int max = 0;
    for (int bd : bloomDay) {
        max = Math.max(max, bd);
        lo = Math.min(lo, bd);
    }
    int hi = max + 1;

    ...
    return lo > max ? -1 : lo;

```