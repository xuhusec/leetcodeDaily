### [1802.Maximum-Value-at-a-Given-Index-in-a-Bounded-Array](https://leetcode.com/problems/maximum-value-at-a-given-index-in-a-bounded-array)

#### Solution 1: binary search

It is clear that there is monotonic character as the `nums[index]` raises. It would be monon-increased. So we can try to resolve this via binary search. The greedy and DP seems not working directly. Clearly, we still need some greedy strategy to find the minisum for a given `nums[index]`. To minimize the sum, `nums[index]` is the max. And we can decrease toward two ends. As indicated in the problem, only -1 difference is allowed when the number > 1. So for a given `nums[index]`, we got a mountain-like array. There is no other solutions. It would either voilate the requirement or it would higher than the sum.

We need to find the maximum so we need to keep track the lo = mid. Therefore, we set lo when the sum of `nums[index]` = mid <= the maxSum. 

```java
    while (lo < hi) {
        int mid = hi - (hi - lo)/2;
        if (miniSum(n, index, mid) <= maxSum) {
            lo = mid;
        } else {
            hi = mid - 1;
        }
    }
```

To compute the minimum sum, as stated above, we need to compute the mountain-like array. I would take the `nums[index]` alone and compute the left segement and the right segement separately. As the logic can be reused. For nums[index] - 1 to lower bound, it would be an arithmetic progression （first + last） * cnt / 2. If there is any element left, they should all be 1. The next would be figuring out the first and the last. The first element in the slop would be `nums[index] - 1`. The last depends on the number element left. It can be 1 or it would be   `nums[index] - 1 - cnt + 1`.

```java
    private long miniSum(int n, int index, int val) {
        long ans = val;
        //left
        ans += slopSum(index, val - 1);
        //right
        ans += slopSum(n - index - 1, val - 1);
        return ans;
    }

    private long slopSum(long cnt, long max) {
        return cnt < max ? ((max + max - cnt + 1) * cnt / 2) :((max + 1) * max /2 + cnt - max);
    }

```