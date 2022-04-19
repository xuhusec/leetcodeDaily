### [2064.Minimized-Maximum-of-Products-Distributed-to-Any-Store](https://leetcode.com/problems/minimized-maximum-of-products-distributed-to-any-store/)

#### Solution 1: binary search

Each store can only sell one kind of products. It is very easy to check given the max value. Also, when we increase the max allowed products the number of store needed would be non-increasing. so we can use binary search. This problem asks for minimum of the max allowed products. So we need to track the upper limit in binary search. so we have the following.

```java
    while (lo < hi) {
        int mid = lo + (hi - lo) / 2;
        if (isPossible(quantities, n, mid)) {
            hi = mid;
        } else {
            lo = mid + 1;
        }
    }
```

Now, let's talk the isPossible function. There are two ways to think about this problem. One is to find out how many stores required for a given max. If the number of stores exceeded n, The max should be increased. Otherwise it can be a possible answer.

```java
    private boolean isPossible (int[] quantities, int total, int max) {
        int cnt = 0;
        for (int q : quantities) {
            // each store would sell one product. Even if the residual is less than max, it would need a dedicated store.
            cnt += q / max + (q % max == 0 ? 0 : 1);
            if (cnt > total) {
                return false;
            }
        }
        return true;
    }
```

You can also check if we use up all quantities by checking the stores.

```java
    private boolean isPossible(int[] quantities, int total, int max) {
        int cur = 0;
        int id = 0;
        for (int i = 0; i < total; ++i) {
            if (cur == 0) {
                // get the next product
                if (id < quantities.length) {
                    cur = quantities[id++];
                } else {
                    // used all product
                    return true;
                }
            }
            cur -= Math.min(cur, max);
        }
        // check if anything left
        return cur == 0 && id == quantities.length;
    }
```

Clearly, in most cases the first one should be fasters.